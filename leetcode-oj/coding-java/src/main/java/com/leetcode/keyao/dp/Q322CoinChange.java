package com.leetcode.keyao.dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by keyao on 12/16/16.
 */
public class Q322CoinChange {
    public int count(int[] coins, int target) {
        Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
        Arrays.sort(coins);

        int ret=doCount(coins, target, cache);
        return ret==Integer.MAX_VALUE ? -1 : ret;
    }

    private int doCount(int[] coins, int target, Map<Integer, Integer> cache) {
        if (cache.containsKey(target)) {
            return cache.get(target);
        }
        int cMin = Integer.MAX_VALUE;
        for (int i=coins.length-1; i>=0; i--) {
            if (coins[i] > target) continue;
            if (coins[i] == target) {
                cMin = 1;
                break;
            }
            int c = doCount(coins,target-coins[i],cache);
            if (c == Integer.MAX_VALUE) continue;
            cMin = cMin > c+1 ? c+1 : cMin;
        }
        if (cache.containsKey(target)){
            cache.put(target, cMin < cache.get(target) ? cMin : cache.get(target));
        }
        else
            cache.put(target, cMin);
        return cMin;
    }

    static public void main(String[] arg) {
        int coins[] = {5,3,2};
        int target = 7;
        Q322CoinChange solution = new Q322CoinChange();
        int ret = solution.count(coins, target);
        System.out.println("result = " + ret);
    }
}
