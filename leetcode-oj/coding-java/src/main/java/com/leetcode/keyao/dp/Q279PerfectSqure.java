package com.leetcode.keyao.dp;

/**
 * Created by keyao on 11/23/16.
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

 For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

 Note: start from 1 squre, 2 squre, 3 squre, ..... remember the intermediate number

 */
public class Q279PerfectSqure {
    public int numSquares(int n) {
        if (n<1) return 0;
        int count[] = new int[n+1];
        count[1] = 1;
        int current = 2;
        while (current <= n) {
            int minNum = Integer.MAX_VALUE;
            int i;
            for (i=1; i*i<current; i++) {
                minNum = minNum > count[current-i*i] + 1 ? count[current-i*i] + 1 : minNum;
            }
            if (i*i == current) {
                count[current] = 1;
            }
            else {
                count[current] = minNum;
            }
            current++;
        }
        return count[n];
    }
}
