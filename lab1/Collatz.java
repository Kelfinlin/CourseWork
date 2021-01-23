/** Class that prints the Collatz sequence starting from a given number.
 *  @author YOUR NAME HERE
 */
public class Collatz {
    public static int nextnumber(int t) {
        if (t % 2 == 0) {
            return (t / 2);
        }
        return (3 * t + 1);
    }
    public static void main(String[] args) {
        int n = 5;
        System.out.println(n);
        while (n!=1) {
            n = nextnumber(n);
            System.out.print(n + " ");
        }
    }

    }

