package gitlet;

import java.io.File;
import java.util.HashMap;

/** this class is basically for adding and removing things, just to make things easier. */
public class StagingArea {

    /** some useful variables are listed here
     *
     */

    public static HashMap<String, Blob> addition;
    public static HashMap<String, Blob> removal;



    /**We should now list the methods which will be helpful storing information of files.
     * the contents needed to be stored are file's name,file's content
     */

    public static boolean containsName(String a) {
        if (addition == null) {
            return false;
        }
        return addition.containsKey(a);
    }

    public static boolean sameContents(String key, String a) {
        return a.equals(addition.get(key));
    }


}
