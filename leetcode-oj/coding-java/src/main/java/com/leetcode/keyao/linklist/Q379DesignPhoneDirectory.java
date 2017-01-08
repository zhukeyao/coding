package com.leetcode.keyao.linklist;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by keyao on 12/28/16.
 */
public class Q379DesignPhoneDirectory {
    int capacity;
    int currentMax = 0;
    PriorityQueue<Integer> recycle = new PriorityQueue<>();
    Set<Integer> assigned = new HashSet<Integer>();
    Q379DesignPhoneDirectory(int capacity) {
        capacity = capacity;
    }

    public Integer get() {
        if (recycle.size() != 0) {
            Integer n = recycle.poll();
            assigned.add(n);
            return n;
        }
        if (currentMax == capacity)
            return -1;
        currentMax++;
        assigned.add(currentMax);
        return currentMax;
    }

    public boolean check(Integer n) {
        return assigned.contains(n);
    }

    public void release(Integer n) {
        assigned.remove(n);
        recycle.add(n);
    }
}
