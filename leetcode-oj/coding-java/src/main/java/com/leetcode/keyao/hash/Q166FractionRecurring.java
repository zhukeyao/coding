package com.leetcode.keyao.hash;

import java.util.*;

/**
 * Created by keyao on 12/11/16.
 */
public class Q166FractionRecurring {
    public String fractionToDecimal(int numerator, int denominator) {
        String ret = "";
        int a = numerator / denominator;
        ret = ret + a;
        int d = numerator % denominator;
        if (d == 0) return ret;

        ret += ".";

        Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        cache.put(d, 0);
        d = d * 10;
        while (true) {
            int fract1 = d / denominator;
            queue.add(fract1);
            d = d % denominator;
            if (d == 0) break;
            if (cache.containsKey(d)) {
                break;
            }
            cache.put(d, queue.size());
            d = d * 10;
        }

        if (d == 0) {
            for (Integer f : queue) {
                ret += f;
            }
            return ret;
        }

        for (Integer f : queue) {
            if (f == d*10 / denominator) {
               ret += "(";
            }
            ret += f;
        }
        ret += ")";

        return ret;
    }

    static public void main(String[] arg) {
        Q166FractionRecurring solution = new Q166FractionRecurring();
        int a = 2;
        int b = 3;
        String ret = solution.fractionToDecimal(2, 3);
        System.out.println("ret = " + ret);

        a = 1;
        b = 2;
        ret = solution.fractionToDecimal(a, b);
        System.out.println("ret = " + ret);

    }
}
