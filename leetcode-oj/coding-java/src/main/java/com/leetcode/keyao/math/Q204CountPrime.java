package com.leetcode.keyao.math;

/**
 * Created by keyao on 12/30/16.
 * Count the number of prime numbers less than a non-negative number, n.
 *
 * Note: The Sieve of Eratosthenes is one of the most efficient ways to find all prime numbers up to n
 *       The idea is to start from i=2 and mark i, 2i, 3i, to be non-prime.
 *       And repeat the procss for all number up to n.
 *       The optimization are as follows:
 *
 *       1. if  n = p * q, assuming p < q, we then know
 *          The stop condition for the first loop is that
 *          i < sqrt(n), i.e., i*i < n.
 *       2. The start condition for the second loop can be
 *          j = i*i, since 2*i, 3*i, 4*i, has already been
 *          executed for i=2, i=3, i=4 cases.
 *
 */

public class Q204CountPrime {
    public int countPrimes(int n) {
        int result = 0;
        int data[] = new int[n];
        for (int i=2; i*i<n; i++) {
            if (data[i] == 1) continue;
            for (int j=i*i; j<n; j+=i) {
                data[j] = 1;
            }
        }

        for (int i=2; i<n; i++) {
            if (data[i] == 0) {
                result++;
            }
        }
        return result;

    }
}
