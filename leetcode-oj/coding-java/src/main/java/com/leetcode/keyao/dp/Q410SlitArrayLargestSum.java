package com.leetcode.keyao.dp;

/**
 * Created by keyao on 11/30/16.
 *
 * Note the idea is
 *    1. cut the first partition, and recursively try the remaining m-1 partition
 *    2. cache minMaxPartitionSum[i][m], where i is the starting index and m the partition we want to cut
 *       from i to input.length
 *    3. cache sum(i..input.length) into an array.
 *
 *    basically, we do greedy exhausively search, but with caching in 2 and 3, we can get result much faster
 */
public class Q410SlitArrayLargestSum {
    int splitMinSum(int input[], int m) {
       int minSum[][] = new int[input.length][m+1];
       int sumCache[] = new int[input.length];
       sumCache[input.length-1] = input[input.length-1];
       for (int i=input.length-2; i>=0; i--) {
           sumCache[i] = sumCache[i+1] +input[i];
       }

       /*
       int minMaxSum = Integer.MAX_VALUE;
       for (int i=1; i<input.length-m-1; i++) {
           int sum1 = doPartition(i,m-1,input, sumCache,minSum);
           int sum2 = sumCache[0] - sumCache[i];
           int sum3 = sum1 < sum2 ? sum1 : sum2;
           minMaxSum = minMaxSum < sum3 ? minMaxSum : sum3;
       }*/
       return doPartition(0, m, input, sumCache, minSum);
    }

    int doPartition(int startIndex, int m, int input[], int sumCache[], int minSum[][]) {
        if (minSum[startIndex][m] > 0) {
            return minSum[startIndex][m];
        }
        if (m == 1) {
            return sumCache[startIndex];
        }
        int minMaxSum = Integer.MAX_VALUE;
        for (int i=startIndex+1; i<input.length-m+1; i++) {
           int sum1 = doPartition(i,m-1,input,sumCache,minSum);
           int sum2 = sumCache[startIndex] - sumCache[i];
           int sum3 = sum1 < sum2 ? sum2 : sum1;
           minMaxSum = minMaxSum < sum3 ? minMaxSum : sum3;
       }
       minSum[startIndex][m] = minMaxSum;
       return minMaxSum;
    }

    static public void main(String[] arg) {
        Q410SlitArrayLargestSum solution = new Q410SlitArrayLargestSum();
        int input[]={7,2,5,10,8};
        int m=2;
        int ret = solution.splitMinSum(input, m);
        System.out.println("result = " + ret);
    }
}
