package com.leetcode.keyao.linklist;

/**
 * Created by keyao on 12/28/16.
 */
public class Q147InsertSortList {
    static public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        public void print() {
            System.out.println("val=" + val);
            if (next != null) {
                next.print();
            }
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyRoot = new ListNode(0);
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            ListNode start = dummyRoot;
            while (start.next != null && start.next.val < current.val) {
                start = start.next;
            }
            current.next = start.next;
            start.next = current;
            current = next;
        }
        return dummyRoot.next;

    }

    static public void main(String[] arg) {
        Q147InsertSortList solution = new Q147InsertSortList();
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(3);

        n1.next = n2;
        n2.next = n3;

        ListNode root = solution.insertionSortList(n1);
        root.print();


    }


}
