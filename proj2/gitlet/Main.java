package gitlet;


import java.io.File;
import java.io.IOException;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author Kaifeng Lin
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                File initial = Repository.GITLET_DIR;
                if (initial.exists()) {
                    Utils.exitWithError("A Gitlet version-control system already exists in the current directory.");
                }else {
                    Repository.setupPersistence();
                }
                // TODO: handle the `init` command
                break;
            case "add":
                Repository.add(args[1]);
                // TODO: handle the `add [filename]` command
                break;
            // TODO: FILL THE REST IN
            case "commit":
                Repository.finalCommit(args);
                break;

                /** to be revised
            case "rm":
                String fileName = args[1];
                if (!Repository.removeFile(fileName)) {
                    Utils.exitWithError("No reason to remove the file.");
                }
                break;

                 */
            case "log":
                Repository.log();
                break;
            case "checkout":
                Repository.checkout(args);
                break;
            case "global-log":
                Repository.globalLog();
                break;
            case "find":
                String commitMess = args[1];
                Repository.find(commitMess);
                break;
            case "branch":
                Repository.branchFunc(args[1]);
            default:
                Utils.exitWithError("No command with that name exists.");
        }
    }
}
