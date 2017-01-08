package com.leetcode.keyao.math;

/**
 * Created by keyao on 12/29/16.
 */
public class Q8String2Int {
    public int myAtoi(String str) {
        //Integer intValue = Integer.parseInt(str);
        if (str == null) return 0;
        int len = str.length();
        if (len <= 0) return 0;

        boolean negtive = false;
        int pos = 0;
        int result = 0;

        while (str.charAt(pos) == ' ' && pos < len) {
            pos++;
        }

        if (str.charAt(pos) == '-') {
            negtive = true;
            pos++;
        }
        else if (str.charAt(pos) == '+') {
            pos++;
        }

        Integer limit1 = Integer.MAX_VALUE / 10;
        Integer limit2 = Integer.MAX_VALUE % 10;
        while (pos < len) {
            char v = str.charAt(pos);
            if (v < '0' || v > '9') break;
            int digit = v - '0';
            if (result > limit1) {
                result = Integer.MIN_VALUE;
                break;
            }
            if (result == limit1 && digit > limit2) {
                result = Integer.MIN_VALUE;
                break;
            }
            result = result * 10 + digit;
            pos++;
        }
        return negtive ? -result : result;
    }
    static public void main(String[] arg) {
        String input = "   21";
        Q8String2Int solution = new Q8String2Int();
        int result1 = Integer.parseInt(input);
        int result2 = solution.myAtoi(input);

        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);

    }
}
