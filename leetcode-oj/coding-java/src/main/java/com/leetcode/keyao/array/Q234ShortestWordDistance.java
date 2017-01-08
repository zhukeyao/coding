package com.leetcode.keyao.array;

/**
 * Created by keyao on 11/18/16.
 */
public class Q234ShortestWordDistance {

    String[] words;
    Q234ShortestWordDistance(String[] words) {
        this.words = words;
    }
    public int shortestDistance(String w1, String w2) {
        int pos1 = -1;
        int pos2 = -1;
        Boolean same = false;
        if (w1.equals(w2)) {
            same = true;
        }
        int distance = Integer.MAX_VALUE;
        for (int i=0; i<words.length; i++) {
            if (same) {
                if (words[i].equals(w1)) {
                    if (pos1 < pos2) {
                        pos1 = i;
                    }
                    else {
                        pos2 = i;
                    }
                }
            }
            else {
                if (words[i].equals(w1)) {
                    pos1 = i;
                }
                if (words[i].equals(w2)) {
                    pos2 = i;
                }
            }
            if (pos1!=-1 && pos2!=-1 &&  Math.abs(pos2-pos1) < distance)  {
                distance = Math.abs(pos2-pos1);
            }
        }
        if (distance == Integer.MAX_VALUE) {
            return 0;
        }
        return distance;
    }

    public static void main(String[] arg) {
       String[] input = {"abc", "abc1", "abc2", "abc3", "abc4", "abc5","abc2"};
       Q234ShortestWordDistance solution = new Q234ShortestWordDistance(input);
       int dis = solution.shortestDistance("abc", "abc2");
       System.out.println(dis);
       int dis2 = solution.shortestDistance("abc2", "abc2");
       System.out.println(dis2);
    }
}
