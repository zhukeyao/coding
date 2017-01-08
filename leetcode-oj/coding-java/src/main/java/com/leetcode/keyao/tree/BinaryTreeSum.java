package com.leetcode.keyao.tree;

/**
 * Created by keyao on 11/5/16.
 * 129. Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
        An example is the root-to-leaf path 1->2->3 which represents the number 123.
        Find the total sum of all root-to-leaf numbers.
 */
public class BinaryTreeSum {
    static public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }

      void print() {
         if (left!=null) {
            left.print();
         }
         System.out.println(val);
         if (right!=null) {
            right.print();
         }
      }
    }

    TreeNode root;

    BinaryTreeSum(TreeNode root) {
        this.root = root;
    }

    public void print() {
        if (root != null) {
            root.print();
        }
    }

    int sum = 0;
    public Integer sumNumber(BinaryTreeSum.TreeNode root) {
        if (root == null)
            return sum;
        visitChild(root, 0);
        return sum;
    }

    public void visitChild(TreeNode current, int val) {
        if (current == null) return;
        int value = val * 10 + current.val;
        if (current.left == null && current.right == null) {
            sum += value;
            return;
        }
        visitChild(current.left, value);
        visitChild(current.right, value);
        return;
    }

    static public void main(String[] arg) {
        BinaryTreeSum.TreeNode node1 = new BinaryTreeSum.TreeNode(1);
        BinaryTreeSum.TreeNode node2 = new BinaryTreeSum.TreeNode(2);
        BinaryTreeSum.TreeNode node3 = new BinaryTreeSum.TreeNode(3);
        BinaryTreeSum.TreeNode node4 = new BinaryTreeSum.TreeNode(4);
        BinaryTreeSum.TreeNode node5 = new BinaryTreeSum.TreeNode(5);
        BinaryTreeSum.TreeNode node6 = new BinaryTreeSum.TreeNode(6);
        BinaryTreeSum.TreeNode node7 = new BinaryTreeSum.TreeNode(7);

        /*
        node1.left = node2;
        node1.right = node5;
        node2.left = node3;
        node2.right = node4;
        node5.left = node6;
        node5.right = node7;*/

        BinaryTreeSum tree = new BinaryTreeSum(node1);
        int sum = tree.sumNumber(node1);
        // revert node 2, node 5
        System.out.println("original tree");
        tree.print();
        System.out.println("node sum = " + sum);
    }
}
