public class Solution {
    public static int myAtoi(String s) {
        s = s.replace(" ", "");
        boolean negative = false;
        if (s.contains("-")) {
            negative = true;
            s = s.replace("-", "");
        }
        int index = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                index = i;
                break;
            }
        }
        s = s.substring(0, index);
        if (negative) {
            s = "-" + s;
        }
        int returnStaff;
        try {
            returnStaff = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            if (returnStaff < )
        }
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        String s = "777we";
        System.out.println(myAtoi(s));
    }
}