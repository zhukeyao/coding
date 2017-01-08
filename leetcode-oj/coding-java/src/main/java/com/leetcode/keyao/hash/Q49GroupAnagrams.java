package com.leetcode.keyao.hash;

import java.util.*;

/**
 * Created by keyao on 12/10/16.
Given an array of strings, group anagrams together.

 For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Return:

 [
 ["ate", "eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]

 Note: this question is simple.  The keypoint here is getKey() funcition,
 i.e., in java, how to convert array to char[], how to sort char[], and how to
 get string from char[]
 */
public class Q49GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        List<List<String>> result = new ArrayList<List<String>>();
        for (String s : strs) {
            String key = getKey(s);
            if (groups.containsKey(key)) {
                groups.get(key).add(s);
            }
            else {
                List<String> group = new ArrayList<String>();
                group.add(s);
                groups.put(key, group);
            }
        }

        for (String key : groups.keySet()) {
            result.add(groups.get(key));
        }
        return result;
    }

    String getKey(String input) {
       char[] buf = input.toCharArray();
       Arrays.sort(buf);
       return new String(buf);
    }

    static public void main(String[] arg) {
        Q49GroupAnagrams solution = new Q49GroupAnagrams();
        String[] input = {"eat", "tea", "tan", "ate","nat", "bat"};
        List<List<String>> result = solution.groupAnagrams(input);
        System.out.println("result = " + result.toString());
        solution.hashCode();
    }
}
