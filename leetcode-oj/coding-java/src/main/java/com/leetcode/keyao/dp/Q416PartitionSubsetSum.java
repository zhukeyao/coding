package com.leetcode.keyao.dp;

/**
 * Created by keyao on 11/30/16.
 */
public class Q416PartitionSubsetSum {
    Boolean hasEqualPartition(int input[]) {
        int sum[] = new int[input.length];
        int currentSum = 0;
        for (int i=input.length-1; i>=0; i--) {
            currentSum += input[i];
            sum[i] = currentSum;
        }
        if (sum[0] % 2 != 0) {
            return false;
        }
        int target = sum[0] / 2;
        return findSum(1, input, sum, target) || findSum(1, input, sum, target-input[0]);
    }

    Boolean findSum(int startIndex, int input[], int sum[], int target) {
        if (target < 0) return false;
        if (target == 0) return true;
        if (sum[startIndex] == target) return true;
        if (sum[startIndex] < target) return false;
        return findSum(startIndex + 1, input, sum, target) ||
               findSum(startIndex+1, input, sum, target-input[startIndex]);
    }

    static public void main(String[] arg) {
        Q416PartitionSubsetSum solution = new Q416PartitionSubsetSum();
        //int input[] = {1,5,11,5};
        int input[] = {1,2,3,5};
        Boolean ret = solution.hasEqualPartition(input);
        if (ret) {
            System.out.println("Has equal partition");
        }
        else {
            System.out.println("Do not have equal partition");
        }
    }
}
