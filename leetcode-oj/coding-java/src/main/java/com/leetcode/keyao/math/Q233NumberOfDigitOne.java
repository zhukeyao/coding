package com.leetcode.keyao.math;

/**
 * Created by keyao on 12/30/16.
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

 For example:
 Given n = 13,
 Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

 Note: There are 3 algorithm

 Algo 1: recursively check last digit, and count how many 1's there is
         depends on the value of last digit.

 Algo 2: check the number digit by digit, and count the unique numbers,
         when if current digit is one.

 Algo 3: starting from the first digit, and rescurisely count the number
         depending on the value of first digit
 */
public class Q233NumberOfDigitOne {
    public int countDigitOne(int n) {
        if (n<=0) return 0;
        if (n<=9) return 1;
        if (n==10) return 2;
        int m = n % 10;
        int k = n / 10;
        if (m == 0) {
            return countDigitOne(k) + countDigitOne(k-1) * 8 + k + countDigitOne(k-1);
        }
        else {
            return k + 1 + countDigitOne(k) * (m+1) + countDigitOne(k-1) * (9-m);
        }
    }

    int countDigitOne2(int n) {
        int ones = 0;
        for (long m = 1; m <= n; m *= 10) {
            // note that following code can be interprete as

            // if n % m = 0, no need to add the remaining
            // if n % m = 1, need add n % m + 1;
            // if n % m >= 2, need add m
            long remaining =  n / m % 10 == 1 ? (n % m + 1) : 0;
            ones += (n / m + 8) / 10 * m + remaining;

        }
        return ones;
    }

    public int countDigitOne3(int n) {
        if(n<1) return 0;
        if(n>=1 && n<10) return 1;
        // x: first digit
        int y=1, x=n;
        while(!(x<10)){
            x/=10;
            y*=10;
        }
        if(x==1)
            return n-y+1+countDigitOne3(y-1)+countDigitOne3(n%y);
        else
            return y+x*countDigitOne3(y-1)+countDigitOne3(n%y);
    }
}
