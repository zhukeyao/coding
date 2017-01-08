package com.leetcode.keyao.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by keyao on 11/18/16.
 * Note:
 *   1. how to sort an int[]
 *   2. do not forget the second loop
 */
public class Q229ThreeSumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length < 3) return 0;
        Arrays.sort(nums);
        int count = 0;
        for (int i=0; i<nums.length - 2; i++) {
            int sum = target - nums[i];
            if (sum <= 0) break;
            for (int start = i + 1; start < nums.length - 1; start++) {
                int end = nums.length - 1;
                while (start < end && nums[start] + nums[end] >= sum) {
                    end--;
                }
                count += end-start;
            }
        }
        return count;
    }

    static public void main(String[] arg) {
        Q229ThreeSumSmaller solution = new Q229ThreeSumSmaller();
        int[] nums = {1,2,3,4,5};
        int count = solution.threeSumSmaller(nums, 9);
        System.out.println(count);

    }
}
