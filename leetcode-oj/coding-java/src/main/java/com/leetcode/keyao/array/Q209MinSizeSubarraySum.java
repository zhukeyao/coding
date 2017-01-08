package com.leetcode.keyao.array;

/**
 * Created by keyao on 11/16/16.
 Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

 For example, given the array [2,3,1,2,4,3] and s = 7,
 the subarray [4,3] has the minimal length under the problem constraint.

 Note: Boundary condition of this problem is tricky. Be careful.


 */
public class Q209MinSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;
        int start = 0;
        int end = 0;
        Integer minLength = nums.length + 1;
        int sum = 0;
        while (start < nums.length) {
            // keeping increase end until sum > s
            if (sum < s && end < nums.length) {
                sum+= nums[end];
                end++;
                continue;
            }

            // keep decreass sum until s < sum
            if (sum >= s) {
                 minLength = end - start < minLength  ? end - start : minLength;
                 sum-=nums[start];
                 start++;
                 continue;
            }

            // end reach the input end, and sum is smaller than s, no need to continue;
            break;
        }

        return minLength == nums.length + 1 ? 0 : minLength;
    }

    static public void main(String[] arg) {
        Q209MinSizeSubarraySum solution = new Q209MinSizeSubarraySum();
        //int[] input = {2,3,1,2,4,3};
        //int[] input={1,4,4};
        int[] input={1,2,3,4,5};
        int ret = solution.minSubArrayLen(11, input);
        System.out.println("result = " + ret);
    }
}
