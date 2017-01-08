package com.leetcode.keyao.linklist;

/**
 * Created by keyao on 12/21/16.
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

 If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

 You may not alter the values in the nodes, only nodes itself may be changed.

 Only constant memory is allowed.

 For example,
 Given this linked list: 1->2->3->4->5

 For k = 2, you should return: 2->1->4->3->5

 For k = 3, you should return: 3->2->1->4->5

 Note: there is two approach
 1. get every subgruop head,tail pair, reverse one by one
 2. recursively reverse  root,  root-n, root-2*n, ...
 */
public class Q25ReverseNodeKGroup {
    static public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    static public class NodePair {
        ListNode head;
        ListNode tail;
    };
    public NodePair reverseList(ListNode start, ListNode end) {
        NodePair nodePair = new NodePair();
        if (start == null) {
            return nodePair;
        }
        ListNode pre = null;
        ListNode current = start;
        ListNode next = null;
        while (pre != end) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        nodePair.head = end;
        nodePair.tail = start;
        start.next = null;
        return nodePair;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k==1) return head;
        ListNode groupHead = head;
        ListNode groupTail = head;

        ListNode newHead = null;
        ListNode newTail = null;
        while (groupTail != null) {
            int count = 0;
            while (groupTail!=null && count < k-1) {
                groupTail = groupTail.next;
                count++;
            }
            if (groupTail == null) break;

            ListNode nextHead = groupTail.next;

            if (count == k-1) {
                NodePair nodeSubGroup = reverseList(groupHead, groupTail);
                if (newHead == null) {
                    newHead = nodeSubGroup.head;
                    newTail = nodeSubGroup.tail;
                }
                else {
                    newTail.next = nodeSubGroup.head;
                    newTail = nodeSubGroup.tail;
                }
            }
            groupHead = nextHead;
            groupTail = nextHead;
        }
        if (newHead == null) return head;
        if (groupHead != null && newTail !=null) {
            newTail.next = groupHead;
        }
        return newHead;
    }

    public ListNode reverseKGroupRecursive(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroupRecursive(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }

    static public void main(String[] arg) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        Q25ReverseNodeKGroup solution = new Q25ReverseNodeKGroup();
        ListNode root = solution.reverseKGroup(n1, 2);
        while (root!=null) {
            System.out.println(root.val);
            root = root.next;
        }
    }
}
