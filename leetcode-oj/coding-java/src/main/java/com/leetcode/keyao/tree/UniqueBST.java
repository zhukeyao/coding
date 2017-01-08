package com.leetcode.keyao.tree;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by keyao on 11/2/16.
 */
public class UniqueBST {
    static public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
   }

   public List<TreeNode> generateTree(int n) {
       List<TreeNode> trees = new ArrayList<>();
       if (n <= 0) return trees;

       return generateTree(1, n);
   }

   public List<TreeNode> generateTree(int start, int end) {
       List<TreeNode> trees = new ArrayList<>();
       if (start > end) return trees;
       if (start == end) {
           trees.add(new TreeNode(start));
           return trees;
       }
       for (int i = start; i<=end; i++) {
           List<TreeNode> leftTree = generateTree(start, i-1);
           List<TreeNode> rightTree = generateTree(i+1, end);

           if (rightTree.size() == 0) {
               for (TreeNode node : leftTree) {
                    TreeNode root = new TreeNode(i);
                    root.left = node;
                    trees.add(root);
               }
           }
           else if (leftTree.size() == 0) {
               for (TreeNode node : rightTree) {
                    TreeNode root = new TreeNode(i);
                    root.right = node;
                    trees.add(root);
               }
           }
           else {
               for (TreeNode leftNode : leftTree) {
                   for (TreeNode rightNode : rightTree) {
                       TreeNode root = new TreeNode(i);
                       root.left = leftNode;
                       root.right = rightNode;
                       trees.add(root);
                   }
               }
           }
       }
       return trees;
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
       UniqueBST bstTree = new UniqueBST();
       List<UniqueBST.TreeNode> trees = bstTree.generateTree(3);
       System.out.println("total tree #: " + trees.size());

       int count = 0;
       for (UniqueBST.TreeNode root : trees) {
           System.out.println("print tree: " + count++);
           bstTree.printTree(root);
       }
   }
}
