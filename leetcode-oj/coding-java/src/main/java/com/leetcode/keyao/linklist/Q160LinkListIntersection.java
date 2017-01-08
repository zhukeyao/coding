package com.leetcode.keyao.linklist;

/**
 * Created by keyao on 12/28/16.
 * Write a program to find the node at which the intersection of two singly linked lists begins.


 For example, the following two linked lists:

 A:   a1 → a2
            ↘
             c1 → c2 → c3
             ↗
 B:   b1 → b2 → b3

 begin to intersect at node c1.

 Note:  two pointer iterate from the root of each node and loop back to the other root node.
        If there is a merge point, they should meet.


 */
public class Q160LinkListIntersection {
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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
       if (headA == null || headB == null)  return null;
       ListNode n1 = headA;
       ListNode n2 = headB;

       while (n1 != null && n2 != null && n1 != n2)  {
           n1 = n1.next;
           n2 = n2.next;
           if (n1 == n2) break;
           if (n1 == null) n1 = headB;
           if (n2 == null) n2 = headA;
       }
       return n1;
    }

}
