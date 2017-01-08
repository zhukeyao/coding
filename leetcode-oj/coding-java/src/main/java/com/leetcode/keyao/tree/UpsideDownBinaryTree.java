package com.leetcode.keyao.tree;

/**
 * Created by keyao on 11/5/16.
 */
public class UpsideDownBinaryTree {
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

    UpsideDownBinaryTree(TreeNode root) {
        this.root = root;
    }

    public void print() {
        if (root != null) {
            root.print();
        }
    }

    public void flip() {
        if (root == null) return;
        visit(root);
    }

    public void visit(TreeNode current) {
        if (current.left == null) {
            root = current;
            return;
        }
        visit(current.left);
        current.left.right = current;
        if (current.right != null) {
            current.left.left = current.right;
        }
        current.left = null;
        current.right = null;
        return;
    }

    static public void main(String[] arg) {
         UpsideDownBinaryTree.TreeNode node1 = new UpsideDownBinaryTree.TreeNode(1);
         UpsideDownBinaryTree.TreeNode node2 = new UpsideDownBinaryTree.TreeNode(2);
         UpsideDownBinaryTree.TreeNode node3 = new UpsideDownBinaryTree.TreeNode(3);
         UpsideDownBinaryTree.TreeNode node4 = new UpsideDownBinaryTree.TreeNode(4);
         UpsideDownBinaryTree.TreeNode node5 = new UpsideDownBinaryTree.TreeNode(5);
         UpsideDownBinaryTree.TreeNode node6 = new UpsideDownBinaryTree.TreeNode(6);
         UpsideDownBinaryTree.TreeNode node7 = new UpsideDownBinaryTree.TreeNode(7);

         node1.left = node2;
         node1.right = node3;
         node2.left = node4;
         node2.right = node5;
         /*node5.left = node6;
         node5.right = node7; */

         UpsideDownBinaryTree tree = new UpsideDownBinaryTree(node1);
         // revert node 2, node 5
         System.out.println("original tree");
         tree.print();

         tree.flip();
         System.out.println("flipped tree");
         tree.print();
    }


}
