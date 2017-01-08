package com.leetcode.keyao.hash;

import java.util.*;

/**
 * Created by keyao on 12/10/16.

 You are given a string, s, and a list of words, words,
 that are all of the same length. Find all starting indices
 of substring(s) in s that is a concatenation of each word in
 words exactly once and without any intervening characters.

 For example, given:
 s: "barfoothefoobarman"
 words: ["foo", "bar"]

 You should return the indices: [0,9].

 Note: 1. assuming the word length = 3, we just need start searching from
          input[0], input[1], and input[2].  We do not need continue search
          from input[3]... as it has already been covered by input[0].
          Therefore, the the first level loop is from i=0 to i<word.length

       2. When a substring repetition is find, we need drop all the
          words before last occurance of the substring. We do not need
          keep tracking the begining index,
          as the beginning index = current_word_index + word_length - total_word_length

 */
public class Q30SubStringConcat {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words.length == 0) return result;
        int wordLength = words[0].length();
        Set<String> dict = new HashSet<>();
        for (String word : words) {
            dict.add(word);
        }
        int totalWordLength = wordLength * words.length;
        LinkedList<String> matched = new LinkedList<>();
        Set<String> count = new HashSet<String>();
        for (int i=0; i<wordLength; i++) {
            for (int j=i; j<=s.length() - wordLength;j+=wordLength) {
                String currentSubString = s.substring(j, j+wordLength);
                // if current sub string is not in dict,
                // reset everything
                if (!dict.contains(currentSubString)) {
                    matched.clear();
                    count.clear();
                    continue;
                }
                // if current sub string is in dict but has not been
                // counted yet, include it
                if (!count.contains(currentSubString)) {
                    count.add(currentSubString);
                    matched.add(currentSubString);
                    if (matched.size() == words.length) {
                        result.add(j+wordLength - totalWordLength);
                        String first = matched.removeFirst();
                        count.remove(first);
                    }
                    continue;
                }
                // if current substring is in dict, but has been
                // included, remove all substring which show up before
                // the current substring's repitition
                while (matched.size() > 0) {
                    String first = matched.removeFirst();
                    count.remove(first);
                    if (first.equals(currentSubString)) {
                        break;
                    }
                }
            }
        }
        return result;
    }
    static public void main(String[] arg) {
        Q30SubStringConcat solution = new Q30SubStringConcat();
        String s = "barfoothefoobarman";
        //String s = "foobar";
        String[] words={"foo", "bar"};
        List<Integer> result = solution.findSubstring(s, words);
        System.out.println("result = " + result.toString());
    }
}
