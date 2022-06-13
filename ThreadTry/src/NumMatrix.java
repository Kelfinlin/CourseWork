import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class NumMatrix {

    private int[][] sumGrid;

    public NumMatrix() {
        this.sumGrid = null;
    }

    public NumMatrix(int[][] matrix) {
        sumGrid = new int[matrix.length+1][matrix[0].length+1];
        for (int i = 0; i <= matrix.length; i++) {
            sumGrid[i][0] = 0;
        }
        for (int i = 0; i <= matrix[0].length; i++) {
            sumGrid[0][i] = 0;
        }
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                sumGrid[i][j] = sumGrid[i-1][j] + sumGrid[i][j-1] + matrix[i-1][j-1] - sumGrid[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sumGrid[row2+1][col2+1] - sumGrid[row1][col2+1] - sumGrid[row2+1][col1] + sumGrid[row1][col1];
    }

    public int[][] transpose(int[][] matrix) {
        int [][] result = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int []result = new int[m+n];
        int first = 0;
        int second = 0;
        int index = 0;
        while (first < m && second < n) {
            if (nums1[first] < nums2[second]) {
                result[index] = nums1[first];
                first++;
            } else {
                result[index] = nums2[second];
                second++;
            }
            index++;
        }
        if (first < m) {
            for (int counter = first; counter < m; counter++) {
                result[index] = nums1[counter];
                index++;
            }
        } else {
            for (int counter = second; counter < n; counter++) {
                result[index+counter-1] = nums2[counter];
                index++;
            }
        }

        for (int i =0 ; i < m+n; i++) {
            nums1[i] = result[i];
        }
    }

    public int longestPalindromeSubseq(String s) {
        int [][]lps = new int[s.length()][s.length()];
        for (int i = s.length()-1; i >= 0; i--) {
            for (int j = 0; j < s.length(); j++) {
                if (i > j) {
                    lps[i][j] = 0;
                } else if (i == j) {
                    lps[i][j] = 1;
                } else if (s.charAt(i) == s.charAt(j)) {
                    lps[i][j] = 2+lps[i+1][j-1];
                } else {
                    lps[i][j] = Math.max(lps[i+1][j], lps[i][j-1]);
                }
            }
        }
        return lps[0][s.length()-1];
    }

    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> mapping = new HashMap<>();
        for (int index = 0; index < numbers.length; index++) {
            mapping.put(numbers[index], index);
        }

        for (int index = 0; index < numbers.length/2+1; index++) {
            if (mapping.get(target-numbers[index]) != null) {
                if (target == 2*numbers[index] && numbers[index] == numbers[index+1]) {
                    return new int[]{index+1, index+2};
                } else if (target != 2*numbers[index]) {
                    return new int[]{index+1, mapping.get(target-numbers[index])+1};
                }
            }
        }
        return null;
    }

    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int length = 0;
        int temp = 0;
        boolean repetition = false;
        for (int i = 0; i < s.length(); i++) {
            Character curr = s.charAt(i);
            if (i != 0 && set.contains(curr)) {
                length = Math.max(length, temp);
                set.clear();
                set.add(curr);
                temp = 1;
            } else {
                temp++;
                set.add(curr);
            }
        }
        length = Math.max(length, temp);
        return length;
    }

    public boolean canPartition(int[] nums) {
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            counter+=nums[i];
        }
        if (counter % 2 == 1) {
            return false;
        }
        return partition_helper(nums, counter/2);
    }

    public boolean partition_helper(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        if (target == nums[0]) {
            return true;
        }
        int []sub = Arrays.copyOfRange(nums, 1, nums.length);
        return partition_helper(sub, target) || partition_helper(sub, target-nums[0]);
    }

    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int [][]subsum = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i > j) {
                    subsum[i][j] = 0;
                } else if (i == j) {
                    subsum[i][j] = nums[i];
                } else {
                    subsum[i][j] = subsum[i][j-1] + nums[j];
                }
            }
        }
        int minimum = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (subsum[i][j] == sum - x) {
                    minimum = Math.min(minimum, i + nums.length - j - 1);
                }
            }
        }
        return minimum == Integer.MAX_VALUE ? -1 : minimum;
    }

    public int maximumUniqueSubarray(int[] nums) {
        int init = 0;
        int sum = Integer.MIN_VALUE;
        int sum_arr[] = new int[nums.length];
        sum_arr[0] = nums[0];
        HashMap<Integer, Integer> mapping = new HashMap<>();
        mapping.put(nums[0], 0);
        for (int curr = 1; curr < nums.length; curr++) {
            int prev = mapping.getOrDefault(nums[curr], -1);
            if (prev != -1 && prev >= init) {
                init = prev+1;
                sum_arr[curr] = sum_arr[curr-1] - sum_arr[init-1] + nums[curr];
            } else {
                sum_arr[curr] = sum_arr[curr-1] + nums[curr];
            }
            mapping.put(nums[curr], curr);
            sum = Math.max(sum, sum_arr[curr]);
        }
        return sum;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> prev = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        prev.add(triangle.get(0).get(0));
        int minimum = prev.get(0);
        int curr_index = 0;
        int num = 2; // denote the number of the element in the curr list. should increase in each while iteration.
        while (curr != null) {
            for (curr_index = 0; curr_index < num; curr_index++) {
                if (curr_index == 0) {
                    curr.add(triangle.get(num-1).get(0) + prev.get(0));
                } else if (curr_index == num-1) {
                    curr.add(triangle.get(num-1).get(num-1) + prev.get(num-2));
                } else {
                    curr.add(Math.min(triangle.get(num-1).get(curr_index) + prev.get(curr_index), triangle.get(num-1).get(curr_index) + prev.get(curr_index-1)));
                }
            }
            num++;
            prev = curr;
            curr = new ArrayList<>();
        }
        for (int i = 0; i < num; i++) {
            minimum = Math.min(minimum, prev.get(i));
        }
        return minimum;
    }


}