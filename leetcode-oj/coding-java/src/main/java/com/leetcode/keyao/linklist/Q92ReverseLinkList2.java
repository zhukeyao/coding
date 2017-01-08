package com.leetcode.keyao.linklist;

/**
 * Created by keyao on 12/21/16.
 * Note:
 *   The general set up to reverse a link list is as follow
 *
 *            dummy root
 *                |
 *                V
 *   n1 <- n2 <- n3    n4 -> n5 -> n6
 *
 *   Let pre = dummy node (i.e., the previous node of the link list to be reversed)
 *       start = n1 (i.e., the start node of the link list to be reversed)
 *       current = n4 (i.e., current root node of link sub-list to be reversed)
 *
 *       the procedure is
 *       pre.next = current.next
 *       current.next = dummy.next
 *       dummy.next = current
 *       current = pre.next
 *
 *       repeat until current = null or the end of sublist to be reversed is reached.
 *
 *  The problem if reverse a sublist at position (m, n) can be seen as
 *  an special case of the general problem.
 *
 *  Note 2: revert2 is another way which does not use dummy node,
 *  it uses a newRoot directly the remember current new Root.
 *
 *  Note 3: revertRecursive does the reverting resursively.
 *  The idea is to pass in the newHead as well as current Head as
 *  input parameter, and have
 *      a. head.next = newHead;
 *      b. newHead = head;
 *      c. head = next;
 *      then recursively call the same function
 *
 */
public class Q92ReverseLinkList2 {
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

    // use a dummy root
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyRoot = new ListNode(0);
        dummyRoot.next = head;
        ListNode start = head;
        ListNode current = head.next;
        while (current != null) {
            start.next = current.next;
            current.next = dummyRoot.next;
            dummyRoot.next = current;
            current = start.next;
        }
        return dummyRoot.next;
    }


    // does not use dummy root
    public ListNode reverse2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = newHead;
            newHead = current;
            current = next;
        }
        return newHead;
    }


    // do revert recursively
    public ListNode reverseRecursive(ListNode head) {
        if (head == null) return null;
        return doReverseRecursive(null, head);
    }
    // recursive
    public ListNode doReverseRecursive(ListNode newHead, ListNode head) {
        if (head == null) return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return doReverseRecursive(head, next);
    }


    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m==n || head==null) return head;
        ListNode fakeParent = new ListNode(0);
        fakeParent.next = head;
        ListNode current = fakeParent;
        ListNode pre = fakeParent;
        int count = 0;
        while (count < m) {
            pre = current;
            current = current.next;
            count++;
        }
        ListNode last = current;
        current = current.next;
        ListNode next = current.next;
        count++;
        while (count < n) {
            next = current.next;
            current.next = last;
            last = current;
            current = next;
            count++;
        }
        next = current.next;
        current.next = last;
        ListNode tmp = pre.next;
        pre.next = current;
        tmp.next = next;

        return fakeParent.next;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if(head == null) return null;
        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;
        ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
        for(int i = 0; i<m-1; i++) pre = pre.next;

        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed

        // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5

        for(int i=0; i<n-m; i++)
        {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)

        return dummy.next;

    }

    static public void main(String[] arg) {
        Q92ReverseLinkList2 solution = new Q92ReverseLinkList2();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);

        n1.next = n2;
        n2.next = n3;

        ListNode root = solution.reverseBetween(n1, 1, 3);
        root.print();
        //root.print();

        ListNode root2 = solution.reverse(root);
        root2.print();

    }
}
