package com.leetcode.keyao.linklist;

/**
 * Created by keyao on 12/21/16.
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

 For example,
 Given 1->2->3->3->4->4->5, return 1->2->5.
 Given 1->1->1->2->3, return 2->3.

 Note: There are two approaches.
 1. in approach 1, we compare current with previous and next value, and add it to
    the new list if it is unique
 2. in second approach, we set pre->next to be next candidate and keep move current
    downward if its value is duplicated with current->next. we only move pre
    downwar when current = pre->next (i.e., current is not moved)

 */
public class Q82RemoveDuplicateFromSortList {
    static public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode current = head;
        ListNode parent = new ListNode(0);
        ListNode pre = parent;
        int lastValue = head.val - 1;
        while (current != null) {
            if (current.next == null) {
                if (current.val != lastValue )
                {
                    pre.next = current;
                    pre = current;
                }
                break;
            }
            if (current.val != lastValue && current.val != current.next.val) {
                pre.next = current;
                pre=current;
                lastValue = current.val;
                current = current.next;
                continue;
            }
            lastValue = current.val;
            current = current.next;
        }
        pre.next = null;
        return parent.next;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if(head==null) return null;
        ListNode FakeHead=new ListNode(0);
        FakeHead.next=head;
        ListNode pre=FakeHead;
        ListNode cur=head;
        while(cur!=null){
            while(cur.next!=null&&cur.val==cur.next.val){
                cur=cur.next;
            }
            if(pre.next==cur){
                // note, we only append current and move pre when the current node is
                // not moved downward. That means it does not duplidated with
                // previous node
                pre=pre.next;
            }
            else {
                // set pre.next to next candidate
                pre.next=cur.next;
            }
            cur=cur.next;
        }
        return FakeHead.next;
    }

}
