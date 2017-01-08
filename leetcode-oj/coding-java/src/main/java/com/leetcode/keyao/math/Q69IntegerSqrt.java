package com.leetcode.keyao.math;

/**
 * Created by keyao on 12/29/16.
 * Implement int sqrt(int x).

 Compute and return the square root of x.

 Note:
    1. algo2 is a simple binary search algo. The search range starts from
       [1, x].
    2. algo1 is from newton algo,
       https://en.wikipedia.org/wiki/Integer_square_root#Using_only_integer_division

       newton algo is the iterative algo to find solution for a given
       function y=f(x), such that f(x) = 0.
       x(n+1) = x(n) - f(x)/f'(x).

       In this case, f(x) = x^2 - n,  so f'(x) = 2x.
       Apply this into iterative x equation, we got algo 1

 */
public class Q69IntegerSqrt {
    public int mySqrt(int x) {
        int n = x;
        while (n*n>x) {
            n = (n + x/n) / 2;
        }
        return n;
    }
    public int mySqrt2(int x) {
        int min = 1;
        int max = x;
        int middle = (min + max) / 2;
        while (true) {
            if (middle * middle <= x) {
                if ((middle + 1) * (middle+1) > x)
                    break;
                min = middle;
            }
            else {
                max = middle;
            }
            middle = (min + max) / 2;
        }
        return middle;
    }

    static public void main(String[] arg) {
        Q69IntegerSqrt solution = new Q69IntegerSqrt();
        int result1 = solution.mySqrt(15);
        int result2 = solution.mySqrt(15);
        for (int i = 1; i<20; i++) {
           System.out.println("sqrt of " + i);
           System.out.println("    algo1 =  " + solution.mySqrt(i));
           System.out.println("    algo2 =  " + solution.mySqrt2(i));
        }

    }

}
