package com.leetcode.keyao.hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by keyao on 12/11/16.
 * Given a string, find the length of the longest substring T that contains at most 2
 * distinct characters.

 */
public class Q159LongestSubstringWithTwoDistChar {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() < 1) return 0;
        char c1=0, c2=0;
        int c1Start=0, c1End=0;
        int c2Start=0, c2End = 0;
        int ret = 0;
        for (int i=0; i<s.length(); i++) {
            char a = s.charAt(i);
            if (c1 == 0) {
                c1 = a;
                c1Start = i;
            }
            if (c1 == a) {
                c1End = i;
                continue;
            }
            if (c2 == 0) {
                c2 = a;
                c2Start = i;
            }
            if (c2 == a) {
                c2End = i;
                continue;
            }
            int length = Math.max(c1End, c2End) - Math.min(c1Start, c2Start) + 1;
            ret = ret > length ? ret : length;
            if (c2End > c1End) {
                c1 = a;
                c1Start = i;
                c1End = i;
                c2Start = c1End+1;
                continue;
            }
            else {
                c2 = a;
                c2Start = i;
                c2End = i;
                c1Start = c2End+1;
                continue;
            }
        }
        return ret;
    }

    static public void main(String arg[]) {
        Q159LongestSubstringWithTwoDistChar solution = new Q159LongestSubstringWithTwoDistChar();
        String input = "eceecba";
        int ret = solution.lengthOfLongestSubstringTwoDistinct(input);
        System.out.println("ret = " + ret);
    }
}
