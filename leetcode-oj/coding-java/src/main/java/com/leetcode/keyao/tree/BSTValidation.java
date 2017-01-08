package com.leetcode.keyao.tree;

import java.util.LinkedList;

/**
 * Created by keyao on 11/3/16.
 */
public class BSTValidation {
   static public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
   }

   TreeNode root;
   Integer currentMin = Integer.MIN_VALUE;

   BSTValidation(TreeNode root)  {
       this.root = root;
   }
   public Boolean isValid() {
       if (root == null) return true;
       return traverse(root);
   }

   public boolean traverse(TreeNode node) {
        Boolean checkLeft = true;
        Boolean checkCurrent = true;
        Boolean checkRight = true;
        if (node.left != null) {
            checkLeft = traverse(node.left);
        }
        if (checkLeft && node.val <= currentMin) {
            checkCurrent = false;
        }
        currentMin = node.val;

        if (checkLeft && checkCurrent && node.right != null) {
            checkRight = traverse(node.right);
        }

        return checkLeft && checkCurrent && checkRight;

   }

   public void printTree(TreeNode root) {
       if (root == null) return;
       LinkedList<TreeNode> nodes = new LinkedList<>();
       nodes.add(root);

       TreeNode current;
       while (nodes.size() > 0 ) {
           current = nodes.removeFirst();
           System.out.println(current.val);
           if (current.left != null) {
               nodes.add(current.left);
           }
           if (current.right != null) {
               nodes.add(current.right);
           }
       }
   }

   public static void main(String[] arg) {
       BSTValidation.TreeNode node1 = new BSTValidation.TreeNode(5);
       BSTValidation.TreeNode node2 = new BSTValidation.TreeNode(3);
       BSTValidation.TreeNode node3 = new BSTValidation.TreeNode(7);
       BSTValidation.TreeNode node4 = new BSTValidation.TreeNode(4);
       BSTValidation.TreeNode node5 = new BSTValidation.TreeNode(8);

       node1.left = node2;
       node1.right = node3;
       node3.left = node4;
       node4.right = node5;

       BSTValidation tree = new BSTValidation(node1);
       if (tree.isValid())
           System.out.println("valid bst");
       else
           System.out.println("invalid bst");
   }
}
