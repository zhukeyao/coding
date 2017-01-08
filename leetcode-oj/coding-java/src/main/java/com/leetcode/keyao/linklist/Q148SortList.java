package com.leetcode.keyao.linklist;

/**
 * Created by keyao on 12/28/16.
 */
public class Q148SortList {
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

    public ListNode sortList(ListNode root) {
        if (root == null || root.next == null) return root;
        return doMergeSortList(root);
    }

    public ListNode doMergeSortList(ListNode root) {
        if (root == null || root.next == null) return root;
        ListNode fastPointer = root;
        ListNode slowPointer = root;
        ListNode preSlow = null;
        while (fastPointer != null && fastPointer.next != null) {
            preSlow = slowPointer;
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;
            if (fastPointer.next != null) {
                fastPointer = fastPointer.next;
            }
        }
        preSlow.next = null;

        ListNode n1 = doMergeSortList(root);
        ListNode n2 = doMergeSortList(slowPointer);

        ListNode newRoot = new ListNode(0);
        ListNode current = newRoot;
        while (n1 != null && n2 !=null) {
            if (n1.val < n2.val) {
                current.next = n1;
                n1 = n1.next;
                current = current.next;
            }
            else {
                current.next = n2;
                n2 = n2.next;
                current = current.next;
            }
        }
        if (n1 != null) {
            current.next = n1;
        }
        if (n2 != null) {
            current.next = n2;
        }
        return newRoot.next;
    }

    static public void main(String[] arg) {
        Q148SortList solution = new Q148SortList();
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(3);

        n1.next = n2;
        n2.next = n3;

        ListNode root = solution.sortList(n1);
        root.print();
    }
}
