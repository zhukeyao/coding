package com.leetcode.keyao.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by keyao on 11/15/16.
 *
 Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 For example,
 Given [100, 4, 200, 1, 3, 2],
 The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 Your algorithm should run in O(n) complexity

 Note: the key is
    1. put everything into hashset
    2. only start checking if current-1 is not in the hashset, i.e., it is the first number of the sequence
 */
public class Q128LongestConsequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int i : nums) {
            numSet.add(i);
        }
        int maxSeq = 0;
        for (Integer i : numSet) {
            if (numSet.contains(i-1)) {
                continue;
            }
            int m = i;
            while (numSet.contains(m)) {
                maxSeq = Math.max(maxSeq, m-i+1);
                m++;
            }
        }
        return maxSeq;
    }

    static public void main(String[] arg) {
        Q128LongestConsequence solution = new Q128LongestConsequence();
        int[] input = {100,4,200,1,3,2};
        int result = solution.longestConsecutive(input);
        System.out.println("result = " + result);
    }
}
