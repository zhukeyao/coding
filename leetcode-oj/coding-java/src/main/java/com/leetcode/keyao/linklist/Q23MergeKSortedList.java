package com.leetcode.keyao.linklist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by keyao on 12/21/16.
 */
public class Q23MergeKSortedList {
    static public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    static public class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode n1, ListNode n2) {
            if (n1 == n2) return 0;
            return n1.val < n2.val ? -1 : 1;
        }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        //PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new ListNodeComparator());

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
               if (o1 == o2) return 0;
               return o1.val < o2.val ? -1 : 1;
            }
        });
        for (int i=0; i<lists.length; i++) {
            if (lists[i] == null) continue;
            queue.add(lists[i]);
        }
        if (queue.size() == 0) return null;
        ListNode head = queue.poll();
        if (head.next != null) {
            queue.add(head.next);
        }
        ListNode pre = head;
        while (queue.size() > 0) {
            ListNode current = queue.poll();
            if (current.next != null) {
                queue.add(current.next);
                pre.next = current;
                pre = current;
            }
        }
        return head;
    }

}
