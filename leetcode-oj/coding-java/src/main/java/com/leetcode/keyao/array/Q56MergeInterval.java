package com.leetcode.keyao.array;

import java.util.*;

/**
 * Created by keyao on 11/10/16.
 * Given a collection of intervals, merge all overlapping intervals.

 For example,
 Given [1,3],[2,6],[8,10],[15,18],
 return [1,6],[8,10],[15,18].
 */
public class Q56MergeInterval {
    static public class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
    }

    static public List<Interval> mergeInterval(List<Interval> input) {
        List<Interval> output = new ArrayList<>();
        if (input == null || input.size() <= 0) {
            return output;
        }
        if (input.size() == 1) {
            output.addAll(input);
            return output;
        }
        Collections.sort(input, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        int count = 0;
        Interval pre = input.get(count);
        count++;
        while (count < input.size()) {
           Interval current = input.get(count);
           if (pre.end < current.start ) {
               output.add(pre);
               pre = current;
               count++;
               continue;
           }
           if (pre.end >= current.start)
           {
               Interval merged = new Interval(pre.start, Math.max(pre.end,current.end));
               output.add(merged);
               count++;
               if (count == input.size()) {
                   pre = null;
                   break;
               }
               pre = input.get(count);
               count++;
           }
        }
        if (pre!= null) {
            output.add(pre);
        }
        return output;
    }

    static public void main(String[] arg) {
        Q56MergeInterval.Interval i1 = new Q56MergeInterval.Interval(1, 3);
        Q56MergeInterval.Interval i2 = new Q56MergeInterval.Interval(2, 6);
        Q56MergeInterval.Interval i3 = new Q56MergeInterval.Interval(8, 10);
        Q56MergeInterval.Interval i4 = new Q56MergeInterval.Interval(15, 18);

        List<Q56MergeInterval.Interval> input = new LinkedList<>();
        input.add(i1);
        input.add(i2);
        input.add(i3);
        input.add(i4);

        List<Q56MergeInterval.Interval> output = Q56MergeInterval.mergeInterval(input);
        for (Q56MergeInterval.Interval interval : output) {
            System.out.println("[" + interval.start + "," + interval.end + "]");
        }
    }

}
