package com.leetcode.keyao.math;

/**
 * Created by keyao on 12/29/16.
 * Given two numbers represented as strings, return multiplication of the numbers as a string.

 Note:
 The numbers can be arbitrarily large and are non-negative.
 Converting the input string to integer is NOT allowed.
 You should NOT use internal library such as BigInteger.

 Note:
    1.  to muliple an number length m with a number length n, the result can be stored in an array length result[m+n]
    2.  the result of pos i time pos j can be stored in  pos i+j and i+j+1
    3.  result[i+j]'s value can be bigger than 10, as it will be set to be below 10 in next loop. this is the key
 */
public class Q43MultiplyString {
    public String multiply(String num1, String num2) {
        int length1 = num1.length();
        int length2 = num2.length();
        int result[] = new int[length1+length2];
        for (int j=length2-1; j>=0; j--) {
            for (int i=length1-1;i>=0;i--) {
                int v = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = result[i+j+1] + v;
                result[i+j+1] = sum % 10;
                result[i+j] += sum / 10;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i=0; i<length1+length2; i++) {
            if (builder.length()==0 && result[i] ==0) continue;
            builder.append(""+result[i]);
        }
        return builder.toString();
    }

    static public void main(String[] arg) {
        Q43MultiplyString solution = new Q43MultiplyString();
        String s1 = "123";
        String s2 = "123";
        String result = solution.multiply(s1, s2);
        System.out.println(result);
    }
}
