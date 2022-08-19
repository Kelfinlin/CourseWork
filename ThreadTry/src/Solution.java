
import java.util.*;

class Solution {

    public static int[] traffic(int []arrival, int[]street) {
        LinkedList<Integer> main_str = new LinkedList();
        LinkedList<Integer> avenue = new LinkedList();
        // we store the index in the array, so that arrival[i] represent the time.
        for (int i = 0; i < arrival.length; i++) {
            if (street[i] == 0) {
                main_str.add(i);
            } else {
                avenue.add(i);
            }
        }

        int curTime = -1;
        boolean first_priority = true;
        int []result = new int[arrival.length];
        while (!main_str.isEmpty() && !avenue.isEmpty()) {
            int first_main = arrival[main_str.peek()];
            int first_aven = arrival[avenue.peek()];
            if ((curTime < first_main && !first_priority) || (curTime < first_aven && first_priority)) {
                curTime = Math.max(Math.min(first_aven, first_main), curTime);
                first_priority = (first_aven<=first_main);
            }

            if (first_priority && first_aven-curTime <= 1) {
                result[avenue.poll()] = curTime;
                curTime++;
                first_priority = true;
            } else {
                result[main_str.poll()] = curTime;
                curTime++;
                first_priority = false;
            }
        }
        if (main_str.isEmpty()) {
            while (!avenue.isEmpty()) {
                int curr_index = avenue.poll();
                result[curr_index] = Math.max(curTime, arrival[curr_index]);
                curTime = Math.max(arrival[curr_index], curTime+1);
            }
        } else {
            while (!main_str.isEmpty()) {
                int curr_index = main_str.poll();
                result[curr_index] = Math.max(curTime, arrival[curr_index]);
                curTime = Math.max(arrival[curr_index], curTime+1);
            }
        }
        return result;
    }

    HashSet<String> seen;

    public boolean reach_helper(int x1, int y1, int x2, int y2, int c) {
        if (x1 > x2 || y1>y2) {
            return false;
        }
        String curr = String.valueOf(x1) + "," + String.valueOf(y1);
        if (seen.contains(curr)) {
            return false;
        }

        if (x1 == x2 && y1 == y2) {
            return true;
        }

        seen.add(curr);

        return reach_helper(x1+y1, y1, x2, y2, c) || reach_helper(x1, x1+y1, x2, y2, c) || reach_helper(x1+c, y1+c, x2, y2, c);
    }


    public static boolean reachingPoints(int x1, int y1, int x2, int y2, int c) {
        if (x1 > x2 || y1>y2) {
            return false;
        }

    }

    public static void main(String[] args) {
//        int []arrival = new int[]{0, 0, 1, 4};
//        int []street = new int[] {0, 1, 1, 0};
        boolean test = reachingPoints(1, 1, 3, 5);
    }

}