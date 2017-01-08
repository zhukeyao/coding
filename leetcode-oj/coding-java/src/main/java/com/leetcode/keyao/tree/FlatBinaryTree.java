package com.leetcode.keyao.tree;

import java.util.Map;

/**
 * Created by keyao on 11/4/16.
 * 114. Flatten Binary Tree to Linked List
        Given a binary tree, flatten it to a linked list in-place, using right child pointer as next pointer
        Note that, the link list should follow pre-order traverse sequence
 */
public class FlatBinaryTree {
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

    FlatBinaryTree(TreeNode root) {
        this.root = root;
    }

    public void print() {
        if (root != null) {
            root.print();
        }
    }

    public void flatTree() {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            return;
        }

        TreeNode nodes[] = new TreeNode[2];
        doFlatTree(root, nodes);
        if (nodes[1] != null) {
            nodes[1].right = null;
        }

        return;
    }

    public void doFlatTree(TreeNode current, TreeNode[] nodes) {
        if (current == null) return;
        if (current.left == null && current.right == null) {
            nodes[0] = current;
            nodes[1] = current;
            return;
        }

        TreeNode nodes1[] = new TreeNode[2];
        TreeNode nodes2[] = new TreeNode[2];
        doFlatTree(current.left, nodes1);
        doFlatTree(current.right, nodes2);

        current.left = null;
        if (nodes1[0] != null) {
            current.right = nodes1[0];
        }
        else {
            nodes1[1] = current;
        }

        if (nodes2[0] != null) {
            nodes1[1].right = nodes2[0];
        }

        nodes[0] = current;
        nodes[1] = nodes2[1] != null ? nodes2[1] : nodes1[1];

        return;
    }


   static public void main(String[] arg) {
         FlatBinaryTree.TreeNode node1 = new FlatBinaryTree.TreeNode(1);
         FlatBinaryTree.TreeNode node2 = new FlatBinaryTree.TreeNode(2);
         FlatBinaryTree.TreeNode node3 = new FlatBinaryTree.TreeNode(3);
         FlatBinaryTree.TreeNode node4 = new FlatBinaryTree.TreeNode(4);
         FlatBinaryTree.TreeNode node5 = new FlatBinaryTree.TreeNode(5);
         FlatBinaryTree.TreeNode node6 = new FlatBinaryTree.TreeNode(6);
         FlatBinaryTree.TreeNode node7 = new FlatBinaryTree.TreeNode(7);

         node1.left = node2;
         node1.right = node5;
         node2.left = node3;
         node2.right = node4;
         node5.left = node6;
         node5.right = node7;

         FlatBinaryTree tree = new FlatBinaryTree(node1);
         // revert node 2, node 5
         System.out.println("original tree");
         tree.print();

         tree.flatTree();
         System.out.println("flatted tree");
         tree.print();
    }


}
