package com.leetcode.keyao.math;

/**
 * Created by keyao on 12/29/16.
 */
public class Q65ValidateNumber {
    public boolean isNumber(String s) {
        String s1 = s.trim();
        int index = s1.indexOf('e');
        if (index == -1) return isDecimalNumber(s1);
        String f1 = s1.substring(0, index);
        String f2 = s1.substring(index+1);

        return isDecimalNumber(f1) && isIntNumber(f2, true, false);
    }

    public boolean isIntNumber(String s, boolean checkSign, boolean allowEmpty) {
        int length = s.length();
        if (length==0) return allowEmpty ? true : false;
        int pos = 0;
        if (checkSign) {
            if (s.charAt(pos) == '-' || s.charAt(pos) == '+') {
                pos++;
            }
        }
        if (pos == length) {
            return false;
        }

        while (pos < length) {
            if (s.charAt(pos) < '0' || s.charAt(pos) > '9')
                return false;
            pos++;
        }
        return true;
    }
    public boolean isDecimalNumber(String s) {
        String[] fields = s.split("\\.");
        int index = s.indexOf('.');
        if (index == -1) return isIntNumber(s, true, false);
        String f1 = s.substring(0, index);
        String f2 = s.substring(index+1);

        if (f1.equals("") && f2.equals("")) return false;
            return isIntNumber(f1, true, true) && isIntNumber(f2, false, true);

    }

    static public void main(String[] arg) {
        Q65ValidateNumber solution = new Q65ValidateNumber();
        String n1 = "0";
        System.out.println(" check 0 = " + solution.isNumber("0"));
        System.out.println(" check 0.1 = " + solution.isNumber(" 0.1"));
        System.out.println(" check .1 = " + solution.isNumber(" .1"));
        System.out.println(" check 3. = " + solution.isNumber("3."));
        System.out.println(" check . = " + solution.isNumber("."));
        System.out.println(" check 0.. = " + solution.isNumber("0.."));

        System.out.println(" check abc = " + solution.isNumber("abc"));
        System.out.println(" check 1 a = " + solution.isNumber("1 a"));
        System.out.println(" check 2e10 = " + solution.isNumber("2e10"));
        System.out.println(" check +2.0e-10 = " + solution.isNumber("+2.0e-10"));

    }
}
