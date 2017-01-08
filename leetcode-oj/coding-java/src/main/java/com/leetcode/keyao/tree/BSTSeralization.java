package com.leetcode.keyao.tree;

/**
 * Created by keyao on 11/8/16.
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 Design an algorithm to serialize and deserialize a binary search tree.
 There is no restriction on how your serialization/deserialization algorithm
 should work. You just need to ensure that a binary search tree can be serialized
 to a string and this string can be deserialized to the original tree structure.

 The encoded string should be as compact as possible.

 Note: For BST, in-order serialization may not give unique tree representation
       without using extra information to denote the empty node during de-serialization.

       But, pre-order serialization can provide enough information for de-serialization
       The key is to keep track the lower bound and upper bound during serialization,
       as each sub-tree segment in pre-order serialization has valid range
 */


public class BSTSeralization {
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

    BSTSeralization(TreeNode root) {
        this.root = root;
    }

    public void print() {
        if (root != null) {
            root.print();
        }
    }

    public String serialize() {
        if (root == null) return "";
        StringBuilder builder = new StringBuilder();
        doSerialize(builder, root);
        return builder.toString();
    }

    public void doSerialize(StringBuilder builder, TreeNode current) {
        if (current == null) return;
        builder.append(String.valueOf(current.val));
        builder.append(" ");
        if (current.left != null) {
            doSerialize(builder, current.left);
        }
        if (current.right != null) {
            doSerialize(builder, current.right);
        }
    }

    public void deSerialize(String input) {
        root = null;
        String[] fields = input.split(" ");
        TreeNode rootParent = new TreeNode(-1);
        doDeserialize(fields, -1, rootParent, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        root = rootParent.left;
    }

    public int doDeserialize(String[] fields, int start, TreeNode parent, Boolean isLeft, int lowerBound, int upperBound) {
        if (start + 1 >= fields.length) return 0;
        TreeNode current = new TreeNode(Integer.parseInt(fields[start+1]));
        if (current.val > upperBound || current.val < lowerBound ) return start;
        if (isLeft) {
            parent.left = current;
        }
        else {
            parent.right = current;
        }
        start++;

        start = doDeserialize(fields, start, current, true, lowerBound, current.val);
        start = doDeserialize(fields, start, current, false, current.val, upperBound);
        return start;
    }

    static public void main(String[] arg) {
         BSTSeralization.TreeNode node1 = new BSTSeralization.TreeNode(10);
         BSTSeralization.TreeNode node2 = new BSTSeralization.TreeNode(5);
         BSTSeralization.TreeNode node3 = new BSTSeralization.TreeNode(3);
         BSTSeralization.TreeNode node4 = new BSTSeralization.TreeNode(8);
         BSTSeralization.TreeNode node5 = new BSTSeralization.TreeNode(15);
         BSTSeralization.TreeNode node6 = new BSTSeralization.TreeNode(13);
         BSTSeralization.TreeNode node7 = new BSTSeralization.TreeNode(20);

         node1.left = node2;
         node1.right = node5;
         node2.left = node3;
         node2.right = node4;
         node5.left = node6;
         node5.right = node7;

         BSTSeralization tree = new BSTSeralization(node1);
         String output = tree.serialize();
         System.out.println("tree = " + output);

         BSTSeralization tree2 = new BSTSeralization(null);
         tree2.deSerialize(output);
         String output2 = tree2.serialize();
         System.out.println("tree = " + output2);

    }

}
