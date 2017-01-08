package com.leetcode.keyao.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keyao on 11/18/16.
 * Created by keyao on 11/17/16.
 * Given an integer array of size n,
 * find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
 *
 * Note: This is famouse Boyer-Moore Algorithm for majority vote.
 * https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html
 *
 * The key idea is to have a bucket for each majority candidate, and increase the count
 * for a number if the number show up again. Otherwise, decrease the count for all candidate.
 * The majority element will survive (count>1) at the end.
 *
 * Then, we need go through the list again to check if the number are real majority element.

*/

public class Q229MajorityElement {

    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        if (nums.length <= 0) {
            return result;
        }

        int[] bucket = {Integer.MIN_VALUE, 0};
        for (int n : nums) {
            if (n == bucket[0]) {
                bucket[1]++;
                continue;
            }
            if (bucket[1] == 0) {
                bucket[0] = n; bucket[1] = 1;
                continue;
            }
            bucket[1]--;
        }

        if (bucket[1] > 0) {
            int count = 0;
            for (int n : nums) {
                if (n == bucket[0]) {
                    count++;
                }
            }
            if (count > nums.length / 2) {
                result.add(bucket[0]);
            }
        }

        return result;
    }

    public List<Integer> majorityElement3(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        if (nums.length <= 0) {
            return result;
        }

        int[] bucket = {Integer.MIN_VALUE, 0};
        int[] bucket2 = {Integer.MIN_VALUE, 0};

        for (int n : nums) {
            if (n == bucket[0]) {
                bucket[1]++;
                continue;
            }
            if (n == bucket2[0]) {
                bucket2[1]++;
                continue;
            }
            if (bucket[1] == 0) {
                bucket[0] = n; bucket[1] = 1;
                continue;
            }
            if (bucket2[1] == 0) {
                bucket2[0] = n; bucket2[1] = 1;
                continue;
            }

            bucket[1]--;
            bucket2[1]--;
        }

        bucket[1] = 0;
        bucket2[1] = 0;
        for (int n : nums) {
            if (n == bucket[0]) {
                bucket[1]++;
                continue;
            }
            if (n == bucket2[0]) {
                bucket2[1]++;
                continue;
            }
        }

        if (bucket[1] > nums.length / 3) {
            result.add(bucket[0]);
        }
        if (bucket2[1] > nums.length / 3) {
            result.add(bucket2[0]);
        }

        return result;
    }

    static public void main(String[] arg) {
        int[] input = {1,1,2,3,1,5,6,1,1};
        Q229MajorityElement solution = new Q229MajorityElement();
        List<Integer> m = solution.majorityElement2(input);
        System.out.println(m.toString());

        int[] input2 = {1,1,2,3,1,5,6,1,2,2,2};
        List<Integer> m1 = solution.majorityElement3(input2);
        System.out.println(m1.toString());


    }
}