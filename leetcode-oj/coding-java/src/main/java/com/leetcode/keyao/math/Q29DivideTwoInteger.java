package com.leetcode.keyao.math;

/**
 * Created by keyao on 12/29/16.
 * Divide two integers without using multiplication, division and mod operator.
 If it is overflow, return MAX_INT.

 Note:
    1. divide can be simulate as minus. We can try to minus
       divisor, divisor * 2, divisor * 4, through bit manupulation.
    2. aftr we find divisor << n  >  dividend, we can re-start the
       process to divident - divisor << n-1, and
       result = result + 1 << n-1
    3. Corner case to be handled
       a. if divisor == 0, return Integer.Max_VALUE;
       b. if divident = Integer.MIN_VALUE, and divisor = -1, since
          Integer.MIN_VALUE * -1 = Integer.MAX_VALUE+1, we need directly
          return Integer.MAX_VALUE
       c. We need check result sign, i.e., if divident and divisor are
          the same sign

          boolean negtive = dividend < 0 ^ divisor < 0 ? true : false;

    4. To handle possible int overflow case, we need use long in all temp
       variable during bit operation.

 */
public class Q29DivideTwoInteger {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {return Integer.MAX_VALUE;};
        if (divisor == 1) {return dividend;};
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean negtive = dividend < 0 ^ divisor < 0 ? true : false;

        long remaining = Math.abs((long)dividend);
        long absDivisor = Math.abs((long)divisor);
        int result = 0;
        while (remaining >= absDivisor) {
            int multiple = 1;
            long tempValue = absDivisor;
            while (remaining >= tempValue << 1) {
                tempValue = tempValue << 1;
                multiple = multiple << 1;
            }
            remaining = remaining - tempValue;
            result += multiple;
        }
        return negtive ? -result : result;
    }

    static public void main(String[] arg) {
        int n1 = -1010369383;
        int n2 = -2147483648;
        Q29DivideTwoInteger solution = new Q29DivideTwoInteger();
        int result = solution.divide(n1, n2);
        System.out.println("result = " + result);
    }
}
