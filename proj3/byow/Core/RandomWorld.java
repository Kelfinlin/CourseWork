package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

public class RandomWorld implements Serializable {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;
    private int roomNum;
    private long seed;
    private Random random;
    private Position door;
    private Position startPos;
    private boolean doorExist = false;

    /**
     * Return a random location on the map.
     */

    public RandomWorld(long seed) {
        this.seed = seed;
        this.random = new Random(seed);
    }


    private static Position randomFocus(long seed) {
        Random r = new Random(seed);
        return new Position(r.nextInt(HEIGHT), r.nextInt(WIDTH), 1);
    }

    /**
     * This is just skeleton code.
     */
    public TETile[][] worldGenerator() {
        long seed2 = turnPositive(this.seed);
        PriorityQueue<Position> exitsQueue = new PriorityQueue<>();
        LinkedList<RoomUnit> roomUnitLinkedList = new LinkedList<>();
        long pseudoSeed = seed2;
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        //ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        RoomUnit r = initialization(world, pseudoSeed, exitsQueue);
        int counter = 0;
        // generate the rooms by exits
        while (counter < 40) {
            Position exit = exitsQueue.poll();
            if (exit == null) {
                break;
            }
            r.reviseSeed();
            RoomUnit child = randomlyGeneration(this.random.nextInt((int) r.getSEED()),
                    world, newFocus(exit), 1, exitsQueue);
            if (child != null) {
                chisel(realExit(exit), world);
                chisel(realExit(realExit(exit)), world);
                roomUnitLinkedList.addLast(child);
            }
            counter += 1;
        }
        while (!doorExist) {
            Position possibleDoor = roomUnitLinkedList.getLast().getExits()[0];
            makeDoor(possibleDoor, world);
        }
        //generateWorld(pseudoSeed, world, randomFocus());
        // draws the world to the screen
        //ter.renderFrame(world);
        return world;
    }

    public static RoomUnit generateRoom(long seed, Position focus) {
        return new RoomUnit(focus.getDirection(), seed);
    }

    public static HallwayUnit generateHallway(long seed, Position focus) {
        return new HallwayUnit(focus.getDirection(), seed);
    }

    private RoomUnit randomlyGeneration(long seed2, TETile[][] world, Position focus,
                                        int tries, PriorityQueue exitsQueue) {
        long originSeed = turnPositive(seed2);
        Position getOrigin = new Position(focus.getX(), focus.getY(), focus.getDirection());
        int randomNum = (int) (originSeed % 3);
        RoomUnit newObject;
        if (randomNum < 1 && tries < 2) {
            newObject = generateRoom(originSeed, focus);
        } else {
            newObject = generateHallway(originSeed, focus);
        }
        newObject.setFocus(focus);
        if (newObject.checkIndexError(world) || newObject.checkOverlap(world)) {
            if (tries > 6) {
                return null;
            }
            newObject = randomlyGeneration(this.random.nextInt((int) originSeed), world,
                    getOrigin, tries + 1, exitsQueue);
        } else {
            newObject.generate(world);
            makeFlower(focus, world, this.seed, this.random);
            roomNum += 1;
            for (Position exit : newObject.getExits()) {
                exitsQueue.add(exit);
            }
        }
        return newObject;
    }

    private static Position realExit(Position exit) {
        int m;
        int n;
        int direction = exit.getDirection();
        switch (direction) {
            case 0: m = 0;
                n = 1;
                break;
            case 1: m = 0;
                n = -1;
                break;
            case 2: m = -1;
                n = 0;
                break;
            default: m = 1;
                n = 0;
        }
        Position returnFocus = new Position(exit.getX() + m, exit.getY() + n, exit.getDirection());
        return returnFocus;
    }

    private static void chisel(Position target, TETile[][] world) {
        world[target.getX()][target.getY()] = Tileset.FLOOR;
    }

    private static Position newFocus(Position exit) {
        return realExit(realExit(realExit(exit)));
    }

    private RoomUnit initialization(TETile[][] world,
                                    long seedNum, PriorityQueue<Position> exitsQueue) {
        Position focus = new Position(50, 20, 0);
        RoomUnit newObject = generateRoom(seedNum, focus);
        newObject.setFocus(focus);
        newObject.generate(world);
        for (Position exit : newObject.getExits()) {
            exitsQueue.add(exit);
        }
        this.startPos = new Position(focus.getX(), focus.getY(), focus.getDirection());
        return newObject;
    }

    public static long turnPositive(long seed) {
        if (seed < 0) {
            return turnPositive(-seed);
        }
        if (seed == 0) {
            return 28739;
        }
        if (seed > 2147483647) {
            return turnPositive(seed / 100);
        }

        return seed;
    }

    public static void makeFlower(Position focus, TETile[][] world, long seed, Random random) {
        world[focus.getX()][focus.getY()] = psychedelic(seed, random);
    }

    private void makeDoor(Position focus, TETile[][] world) {
        if (!doorExist) {
            Position newFocus = new Position(focus.getX(), focus.getY(), focus.getDirection());
            doorHelper(newFocus, world, 0);
        }
    }

    private void doorHelper(Position newFocus, TETile[][] world, int tries) {
        if (tries > 5) {
            return;
        }
        if (isWall(realExit(newFocus), world)) {
            Position realDoor = realExit(newFocus);
            world[realDoor.getX()][realDoor.getY()] = Tileset.LOCKED_DOOR;
            doorExist = true;
            this.door = new Position(realDoor.getX(), realDoor.getY(), realDoor.getDirection());
            return;
        }
        newFocus.changeDirection();
        if (newFocus.getDirection() > 3) {
            newFocus.setDirection();
        }
        doorHelper(newFocus, world, tries + 1);
    }

    private boolean isWall(Position focus, TETile[][] world) {
        if (world[focus.getX()][focus.getY()].equals(Tileset.WALL)) {
            return true;
        }
        return false;
    }

    public Position getDoor() {
        return door;
    }

    public Position getStartPos() {
        return startPos;
    }

    public static TETile psychedelic(long seed, Random random) {
        long realSeed = turnPositive(seed);
        int pseudoNum = random.nextInt((int) realSeed) % 5;
        if (pseudoNum < 1) {
            return Tileset.BLUEFLOWER;
        }
        return Tileset.FLOWER;
    }
    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int i = 0;
            int j = numbers.length - 1;
            while (i < j) {
                int compare = numbers[j] + numbers[i] - target;
                if (compare > 0) {
                    j -= 1;
                } else if (compare < 0) {
                    i += 1;
                } else {
                    return new int[] {i + 1, j + 1};
                }
            }
            return null;
        }
    }
    


}
