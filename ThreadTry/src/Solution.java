
import java.lang.reflect.Array;
import java.util.*;

class Solution {

    // Longest subarray
    public static int maxLength(List<Integer> a, int k) {
        int curr_sum = 0;
        int max_length = 0;
        int left = 0, right = 0;
        while (right < a.size()) {
            curr_sum+=a.get(right);
            while (curr_sum > k) {
                curr_sum-=a.get(left);
                left++;
            }
            max_length = Math.max(max_length, right-left+1);
            right++;
        }
        return max_length;
    }

    //smallest range
    public static int smallestRangeII(int[]a, int k) {
        Arrays.sort(a);
        int left_most = a[0]+k;
        int right_most = a[a.length-1]-k;
        int minimum = a[a.length-1]-a[0];
        for (int i = 0; i < a.length; i++) {
            int curr_left = Math.min(a[i+1]-k, left_most);
            int curr_right = Math.max(a[i]+k, right_most);
            int curr_diff = curr_right-curr_left;
            minimum = Math.min(curr_diff, minimum);
        }
        return minimum;
    }

    private static boolean isOdd(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) % 2 == 0){
                return true;
            }
        }
        return false;
    }

    public static String isOdd(List<String> s_arr, int k) {
        boolean odd = false;
        for (String s:s_arr) {
            if (isOdd(s)) {
                odd = !odd;
            }
        }
        if (odd) {
            return "Odd";
        } else {
            return "Even";
        }
    }

    //ksub divisible problem
    public long kSub(int k, int []nums) {
        int curr_sum = 0, result = 0;
        HashMap<Integer, Integer> seen = new HashMap<>();
        seen.put(0, 1);
        for (int i : nums) {
            curr_sum += i;
            curr_sum = curr_sum % k;
            if (seen.containsKey(curr_sum)) {
                result += seen.get(curr_sum);
            }
            seen.put(curr_sum, seen.getOrDefault(curr_sum, 0)+1);
        }
        return result;
    }

    

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        System.out.println(maxLength(arr, 3));
    }



}