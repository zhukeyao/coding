package com.leetcode.keyao.array;

/**
 * Created by keyao on 11/10/16.
 *Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Your goal is to reach the last index in the minimum number of jumps.

 For example:
 Given array A = [2,3,1,1,4]

 The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

 */
public class Q45JumpGame {
    static public int jump(int[] nums) {
        if (nums.length <= 1 ) return 0;
        if (nums.length == 2 ) return 1;

        int[] steps = new int[nums.length];
        for (int i = nums.length-2; i>=0; i--) {
            if (nums[i] + i >= nums.length - 1) {
                steps[i] = 1;
                continue;
            }
            steps[i] = Integer.MAX_VALUE;
            //Be care for: j<=nums[i] in stead of j<nums[i]
            for (int j=1; j<=nums[i] && i+j<nums.length-1; j++) {
                if (steps[i+j] + 1 < steps[i]) {
                    steps[i] = steps[i+j] + 1;
                    if (steps[i] == 1) {
                        break;
                    }
                }
            }
        }
        return steps[0];
    }

    static public void main(String[] arg) {
        int[] input = {2,3,1,1,4,1,1,1,6};
        int step = Q45JumpGame.jump(input);
        System.out.println("total steps = " + step);
    }
}
