package com.leetcode.keyao.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by keyao on 11/30/16.
 */
public class Q446ArithmeticSliceII {

    static public class ArithmeticEntry {
        public Integer endIndex;
        public Integer gap;
        ArithmeticEntry(int endIndex, int gap) {
            this.endIndex = endIndex;
            this.gap = gap;
        }

        @Override
        public int hashCode() {
            String hash = endIndex +":" + gap;
            return hash.hashCode();
        }
    }

    public int countSubSequence(int[] input) {
       Map<ArithmeticEntry, List<Integer>> cache = new HashMap<ArithmeticEntry, List<Integer>>();
       for (int i=0; i<input.length; i++)
           for (int j = i-1; j>=0; j--) {
               int gap = input[i] - input[j];
               ArithmeticEntry entry = new ArithmeticEntry(j, gap);
               List<Integer> sequenceSize = cache.get(entry);

               List<Integer> sequenceSize2 = new ArrayList<Integer>();
               ArithmeticEntry entry2 = new ArithmeticEntry(i, gap);

               if (null != sequenceSize) {
                   for (Integer size : sequenceSize) {
                        sequenceSize2.add(i+1);
                   }
                   cache.put(entry2, sequenceSize2);
               }
               else {
                    sequenceSize2.add(1);
                    cache.put(entry2, sequenceSize2);
               }
           }

       int count = 0;
       for (ArithmeticEntry entry : cache.keySet()) {
           List<Integer> sequenceSize = cache.get(entry);
           for (Integer size : sequenceSize) {
               if (size < 3) continue;
               count+= size - 3;
           }
       }
       return count;
    }

    public int countSubSequence2(int[] input) {
       Map<String, List<Integer>> cache = new HashMap<String, List<Integer>>();
       for (int i=0; i<input.length; i++)
           for (int j = i-1; j>=0; j--) {
               int gap = input[i] - input[j];
               String entry = j + ":" + gap;
               List<Integer> sequenceSize = cache.get(entry);

               List<Integer> sequenceSize2 = new ArrayList<Integer>();
               String entry2 = i + ":" + gap;

               if (null != sequenceSize) {
                   for (Integer size : sequenceSize) {
                       sequenceSize2.add(size + 1);
                   }
               }
               else {
                    sequenceSize2.add(1);
               }
               if (!cache.containsKey(entry2))
                  cache.put(entry2, sequenceSize2);
               else
                   cache.get(entry2).addAll(sequenceSize2);
           }

       int count = 0;
       for (String entry : cache.keySet()) {
           List<Integer> sequenceSize = cache.get(entry);
           for (Integer size : sequenceSize) {
               if (size < 2) continue;
               count+= size - 1;
           }
       }
       return count;
    }

    static public void main(String[] arg) {
        Q446ArithmeticSliceII solution = new Q446ArithmeticSliceII();
        int input[] = {7,7,7,7};
        int result = solution.countSubSequence2(input);
        System.out.println("result=" + result);

        int input2[] = {2,4,6,8,10};
        int result2 = solution.countSubSequence2(input2);
        System.out.println("result=" + result2);

    }

}
