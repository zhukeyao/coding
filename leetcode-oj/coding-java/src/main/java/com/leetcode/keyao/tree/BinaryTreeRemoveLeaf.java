package com.leetcode.keyao.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keyao on 11/8/16.
 */
public class BinaryTreeRemoveLeaf {
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

    BinaryTreeRemoveLeaf(TreeNode root) {
        this.root = root;
    }

    public void print() {
        if (root != null) {
            root.print();
        }
    }

    public List<List<Integer>> removeLeave() {
        List<List<Integer>>  result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        while (root.left!=null || root.right !=null) {
            List<Integer> leaves = new ArrayList<Integer>();
            doRemoveLeave(root, leaves);
            result.add(leaves);
        }
        List<Integer> leaves = new ArrayList<Integer>();
        leaves.add(root.val);
        result.add(leaves);
        return result;
    }

    Boolean doRemoveLeave(TreeNode current, List<Integer> leaves) {
        if (current == null) return true;
        if (current.left == null && current.right == null) {
            leaves.add(current.val);
            return true;
        }
        if (doRemoveLeave(current.left, leaves)) {
            current.left = null;
        }
        if (doRemoveLeave(current.right, leaves)) {
            current.right = null;
        }

        return false;

    }

    static public void main(String[] arg) {
         BinaryTreeRemoveLeaf.TreeNode node1 = new BinaryTreeRemoveLeaf.TreeNode(1);
         BinaryTreeRemoveLeaf.TreeNode node2 = new BinaryTreeRemoveLeaf.TreeNode(2);
         BinaryTreeRemoveLeaf.TreeNode node3 = new BinaryTreeRemoveLeaf.TreeNode(3);
         BinaryTreeRemoveLeaf.TreeNode node4 = new BinaryTreeRemoveLeaf.TreeNode(4);
         BinaryTreeRemoveLeaf.TreeNode node5 = new BinaryTreeRemoveLeaf.TreeNode(5);
         BinaryTreeRemoveLeaf.TreeNode node6 = new BinaryTreeRemoveLeaf.TreeNode(6);
         BinaryTreeRemoveLeaf.TreeNode node7 = new BinaryTreeRemoveLeaf.TreeNode(7);

         node1.left = node2;
         node1.right = node5;
         node2.left = node3;
         node2.right = node4;
         node5.left = node6;
         node5.right = node7;

         BinaryTreeRemoveLeaf tree = new BinaryTreeRemoveLeaf(node1);
         // revert node 2, node 5
         System.out.println("original tree");
         tree.print();

         List<List<Integer>> leaves = tree.removeLeave();
         for (List<Integer> l : leaves) {
             System.out.println("leaves - " + l.toString());
         }
    }

}
