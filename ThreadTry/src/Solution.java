
import java.util.*;

class Solution {
    private boolean valid_index(int []fs_memory, int begin_index, int word_length, int list_length) {
        HashSet<Integer> validation = new HashSet();
        for (int i = 0; i < list_length; i++) {
            if (begin_index+i*word_length >= fs_memory.length) {
                return false;
            }
            if (fs_memory[begin_index+i*word_length] < 0 || validation.contains(fs_memory[begin_index+i*word_length])) {
                return false;
            }
            validation.add(fs_memory[begin_index+i*word_length]);
        }

//        for (int i = 0; i < word_length; i++) {
//            if (!validation.contains(i)) {
//                return false;
//            }
//        }
        return true;
    }


    public List<Integer> findSubstring(String s, String[] words) {
        int fs_memory[] = new int[s.length()];
        int word_length = words[0].length();
        List<Integer> result = new ArrayList();
        HashMap<String, Integer> words_index = new HashMap();
        for (int i = 0; i < words.length; i++) {
            words_index.put(words[i], i);
        }
        for (int i = 0; i < s.length()-word_length+1; i++) {
            String curr = s.substring(i, i+word_length);
            if (words_index.containsKey(curr)) {
                fs_memory[i] = words_index.get(curr);
            } else {
                fs_memory[i] = -1;
            }
        }
        for (int i = 0; i < s.length()-word_length+1; i++) {
            if (fs_memory[i] >= 0 && valid_index(fs_memory, i, word_length, words.length)) {
                result.add(i);
            }
        }
        return result;
    }

    public int[] twoSum(int[] nums, int target) {
        // fix the first element.
        HashMap<Integer, Integer> nums_index = new HashMap();
        for (int index = 0; index < nums.length; index++) {
            nums_index.put(nums[index], index);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums_index.containsKey(target-nums[i]) && ((nums_index.get(target-nums[i])) != i)) {
                return new int[]{i, nums_index.get(target-nums[i])};
            }
        }
        return new int[]{};
    }

    public int totalStrength(int[] strength) {
        // memoize the minimum value in the strength from i to j
        int[][] mins = new int[strength.length][strength.length];
        for (int i = 0; i < strength.length; i++) {
            for (int j = i; j<strength.length; j++) {
                if (i == j) {
                    mins[i][j] = strength[i];
                } else {
                    mins[i][j] = Math.min(mins[i][j-1], strength[j]);
                }
            }
        }
        long result = 0;
        int[][] sums = new int[strength.length][strength.length];
        for (int i = 0; i < strength.length; i++) {
            for (int j = i; j<strength.length;j++) {
                if (i == j) {
                    sums[i][j] = strength[i];
                } else {
                    sums[i][j] = sums[i][j-1] + strength[j];
                }
                result += ((long) sums[i][j] * mins[i][j]);
            }
        }

        return (int) (result%(Math.pow(10, 9)+7));
    }

    public static int min_net(int []stock) {
        int sum = 0;
        int minimum = Integer.MAX_VALUE;
        int result = -1;
        for (int i = 0; i < stock.length; i++) {
            sum += stock[i];
            int average = sum/(i+1);
            if (Math.abs(average-stock[i]) < minimum) {
                result = i;
                minimum = Math.abs(average-stock[i]);
            }
        }
        return result;
    }

    public static void main(String[] argv) {
        System.out.println(min_net(new int[]{1, 3, 2, 4}));
    }
}