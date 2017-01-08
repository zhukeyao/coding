package com.leetcode.keyao.array;

/**
 * Created by keyao on 11/15/16.
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.

 For example, given the array [2,3,-2,4],
 the contiguous subarray [2,3] has the largest product = 6.

 Note:  The key idea is to see how many negtive number there is.
      1. it's really about odd negative numbers or even negative numbers, if it's odd,
         either the left end one or the right end one should be counted, so it will be revealed by
         scanning from left and from right in 2 passes.
      2. 0 is a kind of delimiter, product accumulation will be reset to 1

 */
public class Q152MaxProductSubArray {
    public int maxProduct(int[] nums) {

        int max = Integer.MIN_VALUE;
        int productLeft = 1;
        int productRight = 1;
        for (int i=0; i<nums.length; i++) {
            productLeft *= nums[i];
            max = Math.max(max, productLeft);
            if (nums[i] == 0) {
                productLeft = 1;
            }

            int j = nums.length - 1 - i;
            productRight *= nums[j];
            max = Math.max(max, productRight);

            if (nums[j] == 0) {
                productRight = 1;
            }
        }
        if (max == Integer.MIN_VALUE)
            return 0;
        return max;
    }
}
