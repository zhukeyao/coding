package com.leetcode.keyao.array;

/**
 * Created by keyao on 11/15/16.
 * A peak element is an element that is greater than its neighbors.

 Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

 The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

 You may imagine that num[-1] = num[n] = -∞.

 For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.


 Idea:  Compare input[mid] vs input[mid+1]
        if (input[mid] > input[mid+1)  recursively check range (low, mid)
        otherwise check (mid+1, high)

 */
public class Q165FindPeekElement {
}
