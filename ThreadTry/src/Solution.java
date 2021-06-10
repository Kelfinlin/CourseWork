class Solution {
    public static String longestCommonPrefix(String[] strs) {
        String shortest = "";
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < shortest.length()) {
                shortest = strs[i];
            }
        }
        String output = "";
        for (int i = 0; i < shortest.length(); i++) {
            for (int j = 0; j < shortest.length() - i; j++) {
                String curr = output.substring(i, i + j);
                if (judgement(strs, curr) && curr.length() > output.length()) {
                    output = curr;
                }
            }
        }
        return output;
    }

    public static boolean judgement(String[] strs, String prefix) {
        for (int i = 0; i < strs.length; i++) {
            if (!strs[i].contains(prefix)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[] {"flower","flow","flight"}));
    }
}