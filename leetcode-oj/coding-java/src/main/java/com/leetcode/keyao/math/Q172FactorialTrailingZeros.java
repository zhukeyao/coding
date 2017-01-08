package com.leetcode.keyao.math;

/**
 * Created by keyao on 12/30/16.

 Given an integer n, return the number of trailing zeroes in n!.
 Note: Your solution should be in logarithmic time complexity.

 Note: This essentially is to count how many 5 there is in the number.
 And for 5^2, 5^3, 5^4, there is multiple 5s, Therefor, we need repeatly
 count number of 5 in  n/5^2, n/5^3, n/5^4 ....

 */
public class Q172FactorialTrailingZeros {
    public int trailingZerosAlgo1(int n) {
        return n == 0 ? 0 : n/5 + trailingZerosAlgo1(n/5);
    }
    public int trailingZerosAlgo2(int n) {
        int result = 0;
        for (int i=5; i<n; i*=5) {
            result += n/i;
        }
        return result;
    }
    static public void main(String[] arg) {
        int input = 169;
        Q172FactorialTrailingZeros solution = new Q172FactorialTrailingZeros();
        int ret1 = solution.trailingZerosAlgo1(input);
        int ret2 = solution.trailingZerosAlgo2(input);
        System.out.println(ret1);
        System.out.println(ret2);
    }
}
