package com.leetcode.keyao.math;

/**
 * Created by keyao on 12/29/16.
 *
 * Implement pow(x, n).
 * Note:
 *   1. we can compute this recursively using x*x and n/2
 *   2. We need handle n < 0 case, i.e., x=1/x, n=-n
 *   3. in case n==Integer.MIN, we cannot just make n=-n, it will overflow.
 *      Since Integer.MIN_VALUE is a even number (-2147483648), we can simply make
 *      x = x*x, n=n/2
 */
public class Q50Pow {
    public double myPow(double x, int n) {
        if (n == Integer.MIN_VALUE) {
            x = x*x;
            n = Integer.MIN_VALUE/2;
        }

        if (n<0) {
            x = 1/x;
            n = -n;
        }

        if (n==0) return 1;

        return n%2==1 ? x*myPow(x*x, n/2) : myPow(x*x, n/2);
    }


}
