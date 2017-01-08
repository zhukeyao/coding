package com.leetcode.keyao.tree;

import java.util.Map;

/**
 * Created by keyao on 11/7/16.
 * Given a non-empty binary search tree and a target value,
 * find the value in the BST that is closest to the target.

 Note: Given target value is a floating point. You are guaranteed to have only
 one unique value in the BST that is closest to the target.
 */
public class BSTClosestValue {
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

    BSTClosestValue(TreeNode root) {
        this.root = root;
    }

    int findClosestValue(int input) {
       if (root == null)  {
           return -1;
       }
       return doFindClosetValue(root, input, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    int doFindClosetValue(TreeNode node, int input, int lowerBound, int upperBound) {
        if (node == null) {
            return Math.abs(lowerBound - input) > Math.abs(upperBound - input) ? upperBound : lowerBound;
        }
        if (node.val == input)
            return node.val;
        if (node.val < input) {
            lowerBound = node.val;
            return doFindClosetValue(node.right, input, lowerBound, upperBound);
        }

        upperBound = node.val;
        return doFindClosetValue(node.left, input, lowerBound, upperBound);
    }
    static public void main(String[] arg) {
         BSTClosestValue.TreeNode node1 = new BSTClosestValue.TreeNode(10);
         BSTClosestValue.TreeNode node2 = new BSTClosestValue.TreeNode(5);
         BSTClosestValue.TreeNode node3 = new BSTClosestValue.TreeNode(3);
         BSTClosestValue.TreeNode node4 = new BSTClosestValue.TreeNode(8);
         BSTClosestValue.TreeNode node5 = new BSTClosestValue.TreeNode(15);
         BSTClosestValue.TreeNode node6 = new BSTClosestValue.TreeNode(13);
         BSTClosestValue.TreeNode node7 = new BSTClosestValue.TreeNode(20);

         node1.left = node2;
         node1.right = node5;
         node2.left = node3;
         node2.right = node4;
         node5.left = node6;
         node5.right = node7;

         BSTClosestValue tree = new BSTClosestValue(node1);
         // revert node 2, node 5
         int value = tree.findClosestValue(19);
         System.out.println("BST closes value = " + value);

         value = tree.findClosestValue(2);
         System.out.println("BST closes value = " + value);
    }

}
