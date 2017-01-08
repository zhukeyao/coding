package com.leetcode.keyao.dp;

import java.sql.SQLWarning;

/**
 * Created by keyao on 11/30/16.
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

 For example,
 Given [10, 9, 2, 5, 3, 7, 101, 18],
 The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

 Your algorithm should run in O(n2) complexity.

 Follow up: Could you improve it to O(n log n) time complexity?

 Note:  Algorithm 1 check subsequence starting from the end of input. It is a O(n^2) algorithm.
        Algorithm 2 is O(nlogn) algorithm. The idea is to use an array (result[]) to keep the smallest number for
          a given subsequence length. For example, result[1] store the smallest number we have seen so far for
          subsequence with length 1, result[2] store the smallest number we have seen so far for subsequence with
          length 2, etc.  For a new incoming number k, we can do binary search in existing result[1..x], if k is
          before than result[x], we got result[x+1] = k, which means we got a new subsequence with length x+1.

 */
public class Q300LongestIncreaseSeq {

    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 0 ) return 0;
        int[] result = new int[nums.length];
        result[nums.length-1] = 1;
        for (int i= nums.length-2; i>=0; i--) {
            int maxLength = 1;
            for (int j=i+1; j< nums.length; j++) {
                if (nums[i] < nums[j]) {
                    maxLength = Math.max(maxLength, result[j] + 1);
                }
            }
            result[i] = maxLength;
        }

        int maxLength = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++) {
            maxLength = maxLength > result[i] ? maxLength : result[i];
        }
        return maxLength;
    }

    public int lengthOfLIS2(int[] nums) {
        int[] result = new int[nums.length+1];
        int maxLength = Integer.MIN_VALUE;

        if (nums.length <= 0 ) return 0;
        int count = 1;
        result[1]  = nums[count-1];
        for (int i=1; i<nums.length;i++) {
            int index = binaryFindNum(result, 1, count, nums, nums[i]);
            result[index] = nums[i];
            if (index > count) {
                count = index;
            }
        }

        return count;
    }

    int binaryFindNum(int[] result, int start, int end, int[] nums, int target) {
        if (start == end) {
            if (result[start] >= target)
                return start;
            return start+1;
        }

        int midIndex = (start + end) / 2;
        if (result[midIndex] >= target)
            return binaryFindNum(result, start, midIndex, nums, target);
        else
            return binaryFindNum(result, midIndex + 1, end, nums, target);

    }

    static public void main(String[] arg) {
        Q300LongestIncreaseSeq solution = new Q300LongestIncreaseSeq();
        int input[] = {10, 9, 2, 5, 3, 7, 101, 18};

        int ret1 = solution.lengthOfLIS(input);
        System.out.println(ret1);
        int ret2 = solution.lengthOfLIS2(input);
        System.out.println(ret2);
    }

}
