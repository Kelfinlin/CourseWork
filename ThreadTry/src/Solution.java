class Solution {
    public static int romanToInt(String s) {
        if (s.equals("")) {
            return 0;
            /**
        } else if (s.startsWith("IV")) {
            return 4 + romanToInt(s.substring(2));
        } else if (s.startsWith("IX")) {
            return 9 + romanToInt(s.substring(2));
        } else if (s.startsWith("XL")) {
            return 40 + romanToInt(s.substring(2));
        } else if (s.startsWith("XC")) {
            return 90 + romanToInt(s.substring(2));
        } else if (s.startsWith("CD")) {
            return 400 + romanToInt(s.substring(2));
        } else if (s.startsWith("CM")) {
            return 900 + romanToInt(s.substring(2));
        }
         **/
        }
        if (s.length() == 1 || transferring(s.substring(0, 1)) > transferring(s.substring(1, 2))) {
            return transferring(s.substring(0, 1)) + romanToInt(s.substring(1));
        }
        return transferring(s.substring(1, 2)) - transferring(s.substring(0, 1)) + romanToInt(s.substring(2));
    }


    public static int transferring(String s) {
        return switch (s) {
            case "I" -> 1;
            case "V" -> 5;
            case "X" -> 10;
            case "L" -> 50;
            case "C" -> 100;
            case "D" -> 500;
            case "M" -> 1000;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
}