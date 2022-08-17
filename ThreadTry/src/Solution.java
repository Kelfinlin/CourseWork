
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

    public static int phone(String s) {
        HashMap<Character, Integer> frequency = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            frequency.put(s.charAt(i), frequency.getOrDefault(s.charAt(i), 0)+1);
        }
        int counter = 0;
        int[] frequency_list = new int[frequency.size()];
        for (Character c:frequency.keySet()) {
            frequency_list[counter] = frequency.get(c);
            counter++;
        }
        Arrays.sort(frequency_list);
        counter = 0;
        for (int i = frequency_list.length-1; i>=0; i--) {
            if (i >= frequency_list.length-9) {
                counter += frequency_list[i];
            } else if (i >= frequency_list.length-18) {
                counter += (2*frequency_list[i]);
            } else {
                counter += (3*frequency_list[i]);
            }
        }
        return counter;
    }

    public static int select(String log, String t) {
        HashMap<Character, Integer> log_counts = new HashMap();
        HashMap<Character, Integer> t_counts = new HashMap();
        for (int i = 0; i < log.length(); i++) {
            log_counts.put(log.charAt(i), log_counts.getOrDefault(log.charAt(i), 0)+1);
        }
        for (int i = 0; i < t.length(); i++) {
            t_counts.put(t.charAt(i), t_counts.getOrDefault(t.charAt(i), 0)+1);
        }
        int counter = Integer.MAX_VALUE;
        for (char c: t_counts.keySet()) {
            counter = Math.min(counter, log_counts.getOrDefault(c, 0)/t_counts.get(c));
        }
        return counter;
    }

    public static int uniqueMorseRepresentations(String[] words) {
        String[] dict = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        HashSet<String> set = new HashSet();
        for (String word:words) {
            StringBuilder curr = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                curr.append(dict[(int)(word.charAt(i))-97]);
            }
            set.add(curr.toString());
        }
        return set.size();
    }

    private static String line(StringBuilder sb, int width) {
        StringBuilder result = new StringBuilder("*");
        result.append("(".repeat((width-sb.length())/2));
        result.append(sb);
        result.append(")".repeat(width-(result.length()-1)));
        result.append("*");
        return result.toString();
    }

    public static String[] method(String[][] paragraphs, int width) {
        List<String> result = new ArrayList();
        String header = "*".repeat(width+2);
        result.add(header);
        for (String[] paragraph : paragraphs) {
            StringBuilder curr = new StringBuilder();
            int word_count = 0;
            for (String s : paragraph) {
                if (word_count == 0) {
                    curr.append(s);
                } else if (word_count + s.length() + 1 <= width) {
                    curr.append(" ");
                    curr.append(s);
                    word_count++;
                } else {
                    String curr_line = line(curr, width);
                    result.add(curr_line);
                    curr = new StringBuilder(s);
                    word_count = 0;
                }
                word_count += s.length();
            }
            if (word_count != 0) {
                String curr_line = line(curr, width);
                result.add(curr_line);
            }
        }
        result.add(header);
        String[] result_arr = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            result_arr[i] = result.get(i);
        }
        return result_arr;
    }

    public int[][] diagonalSort(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            int []curr_arr = new int[(int)Math.floor(Math.sqrt(2)*Math.min(mat.length, mat[0].length))];
            int row = i, col = 0;
            while (row < mat.length && col < mat[0].length) {
                curr_arr

                row++;
                col++;
            }
        }
    }

    public static void main(String[] args) {
        String header = "*".repeat(16);
        String[][] test= new String[3][3];
        test[0] = new String[] {"hello", "world", ""};
        test[1] = new String[] {"how", "areyou", "doing"};
        test[2] = new String[] {"Please look", "and align", "to center"};
        String[] result = method(test, 16);
        for (int i = 0; i < result.length;  i++) {
            System.out.println(result[i]);
        }
    }

}