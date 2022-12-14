import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


import java.util.*;
class Result {

    /*
     * Complete the 'performOperations' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY arr
     *  2. 2D_INTEGER_ARRAY operations
     */

    public static void reverseSubarray(List<Integer> origin, int begin, int end) {
        for (int i = 0; i < (end-begin+1)/2; i++) {
            int temp = origin.get(begin+i);
            origin.remove(begin+i);
            origin.add(begin+i, origin.get(end-i-1));
            origin.remove(end-i);
            origin.add(end-i, (temp));
        }
    }

    public static List<Integer> performOperations(List<Integer> arr, List<List<Integer>> operations) {
        // Write your code here
        for (List<Integer> operation : operations) {
            reverseSubarray(arr, operation.get(0), operation.get(1));
        }
        return arr;
    }


    public static void main(String[] args) {
        List<Integer> test = new ArrayList();
        test.add(0);
        test.add(1);
//        test.add(2);
        List<List<Integer>> operations = new ArrayList<>();
        List<Integer> operation1 = new ArrayList();
        operation1.add(0);
        operation1.add(1);
        operations.add(operation1);
        performOperations(test, operations);
    }
}
