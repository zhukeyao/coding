package com.leetcode.keyao.linklist;

import com.leetcode.keyao.array.Q234ShortestWordDistance;

/**
 * Created by keyao on 12/28/16.
 */
public class Q234PalindromeLinkList {
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

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode dummyParent = new ListNode(0);
        dummyParent.next = head;
        ListNode n1 = dummyParent;
        ListNode n2 = dummyParent;

        // find the middle point
        while (n2 != null && n2.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }

        // revert second sub-list
        ListNode newHead = null;
        ListNode current = n1.next;
        n1.next = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = newHead;
            newHead = current;
            current = next;
        }

        // compare
        ListNode m1 = head;
        ListNode m2 = newHead;
        while (m1 != null && m2 != null) {
            if (m1.val != m2.val) {
                return false;
            }
            m1 = m1.next;
            m2 = m2.next;
        }
        return true;

    }

    static public void main(String[] arg) {
        Q234PalindromeLinkList solution = new Q234PalindromeLinkList();
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(1);

        n1.next = n2;

        Boolean flag = solution.isPalindrome(n1);
        System.out.print(flag);
    }
}
