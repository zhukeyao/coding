package com.leetcode.keyao.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  99. Two elements of a binary search tree (BST) are swapped by mistake.

    Recover the tree without changing its structure.

    Note:
      A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

    See also: Morris Traversal - http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
       Idea: for O(n) no recursive and no stack solution,
             reuse each node's previous node's right pointer,
             as that pointer must be null, we can make it point to it's next in-order node in the BST (for in-order traverse)

 */
public class BSTRecover {
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
   TreeNode node1;
   TreeNode node2;
   TreeNode pre;
   int preValue = Integer.MIN_VALUE;

   BSTRecover(TreeNode root) {
      this.root = root;
   }

   public void print() {
      root.print();
   }

   public void recover() {
      // node -> parent
      if (root == null) return;
      if (root.left == null && root.right == null) return;

      inOrderTraverse(root);
      if (node1 != null && node2 != null) {
         int tmp = node1.val;
         node1.val = node2.val;
         node2.val = tmp;
      }
   }

   public void inOrderTraverse(TreeNode node) {
      if (node.left != null)
         inOrderTraverse(node.left);

      if (node.val < preValue) {
         if (node1 == null) {
            node1 = pre;
            node2 = node;
         }
         else {
            node2 = node;
         }
      }
      pre = node;
      preValue = node.val;

      if (node.right != null) {
         inOrderTraverse(node.right);
      }
   }

   static public void main(String[] arg) {
      BSTRecover.TreeNode node1 = new BSTRecover.TreeNode(5);
      BSTRecover.TreeNode node2 = new BSTRecover.TreeNode(3);
      BSTRecover.TreeNode node3 = new BSTRecover.TreeNode(4);
      BSTRecover.TreeNode node4 = new BSTRecover.TreeNode(6);
      BSTRecover.TreeNode node5 = new BSTRecover.TreeNode(7);


      node1.left = node2;
      node1.right = node4;
      node2.right = node3;
      node4.right = node5;

      BSTRecover tree = new BSTRecover(node1);
      // revert node 2, node 5
      node2.val = 7;
      node5.val = 3;
      System.out.println("original tree");
      tree.print();
      tree.recover();
      System.out.println("recovered tree");
      tree.print();

      node1.val = 3;
      node2.val = 5;
      System.out.println("original tree");
      tree.print();
      tree.recover();
      System.out.println("recovered tree");
      tree.print();



   }
}


