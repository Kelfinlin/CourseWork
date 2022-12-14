package byow.Core;

import java.io.File;
import java.io.IOException;

import static byow.Core.Utils.*;

public class SaveLoad {
    /**
     * Save the current world, essentially TETile[][]
     * and quit.
     */
    public static final File CWD = new File(System.getProperty("user.dir"));

    public static void save(Avatar avatar) {
        File targetAvatar = Utils.join(CWD, "NewestAvatar.txt");
        try {
            targetAvatar.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeObject(targetAvatar, avatar);
    }

    /**
     * Load a previous saved world.
     * I think we can modify this so we can choose which one to load.
     * If no previous save, simply quit and the UI interface should close with no errors produced
     */

    public static Avatar loadAvatar() {
        Avatar target;
        File targetAvatar = join(CWD, "NewestAvatar.txt");
        if (!targetAvatar.exists()) {
            return null;
        }
        target = readObject(targetAvatar, Avatar.class);
        return target;
    }

}
