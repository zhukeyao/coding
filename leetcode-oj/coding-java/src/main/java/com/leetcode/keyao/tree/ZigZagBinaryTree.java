package com.leetcode.keyao.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by keyao on 11/4/16.
 *
   103. Given a binary tree, return the zigzag level order traversal of its nodes' values.
 (ie, from left to right, then right to left for the next level and alternate between).

  key point: 1. keep the same order child queue
             2. output value can be either
                a. write in reverse order into result list
                b. or insert at the first position into result list

             3. remember list.add(index, Element) interface
 */
public class ZigZagBinaryTree {
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

    ZigZagBinaryTree(TreeNode root) {
        this.root = root;
    }

    public void printTreeByLevel() {
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

   public void printTreeZigZag() {
      if (root == null) return;
      LinkedList<TreeNode> nodeList1 = new LinkedList<>();
      LinkedList<TreeNode> nodeList2 = new LinkedList<>();

      Boolean left2Right = true;
      nodeList1.add(root);
      TreeNode current;
      int count = 0;
      while (count < nodeList1.size()) {
          TreeNode topNode = nodeList1.get(count);
          if (left2Right) {
              current = nodeList1.get(count);
          } else {
              current = nodeList1.get(nodeList1.size() - count - 1);
          }
          System.out.println(current.val);
          if (topNode.left != null) {
              nodeList2.add(topNode.left);
          }
          if (topNode.right != null) {
              nodeList2.add(topNode.right);
          }
          count++;

          if (count == nodeList1.size() ) {
              nodeList1.clear();
              nodeList1.addAll(nodeList2);
              nodeList2.clear();
              left2Right = !left2Right;
              count = 0;
          }
      }
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
      if (root == null) return null;

      List<List<Integer>> result = new ArrayList<List<Integer>>();
      LinkedList<Integer> numList = new LinkedList<>();

      LinkedList<TreeNode> nodeList1 = new LinkedList<>();
      LinkedList<TreeNode> nodeList2 = new LinkedList<>();

      Boolean left2Right = true;
      nodeList1.add(root);
      while (nodeList1.size() > 0) {
          TreeNode current;
          current = nodeList1.removeFirst();
          if (left2Right) {
              numList.add(current.val);
          }
          else {
              numList.add(0, current.val);
          }
          if (current.left != null) {
              nodeList2.add(current.left);
          }
          if (current.right != null) {
              nodeList2.add(current.right);
          }

          if (nodeList1.size() == 0) {
              result.add(numList);
              numList = new LinkedList<>();
              nodeList1.addAll(nodeList2);
              nodeList2.clear();
              left2Right = !left2Right;
          }
      }
      return result;
  }


  static public void main(String[] arg) {
         ZigZagBinaryTree.TreeNode node1 = new ZigZagBinaryTree.TreeNode(1);
         ZigZagBinaryTree.TreeNode node2 = new ZigZagBinaryTree.TreeNode(2);
         ZigZagBinaryTree.TreeNode node3 = new ZigZagBinaryTree.TreeNode(3);
         ZigZagBinaryTree.TreeNode node4 = new ZigZagBinaryTree.TreeNode(4);
         ZigZagBinaryTree.TreeNode node5 = new ZigZagBinaryTree.TreeNode(5);
         ZigZagBinaryTree.TreeNode node6 = new ZigZagBinaryTree.TreeNode(6);
         ZigZagBinaryTree.TreeNode node7 = new ZigZagBinaryTree.TreeNode(7);

         node1.left = node2;
         node1.right = node5;
         node2.left = node3;
         node2.right = node4;
         node5.left = node6;
         node5.right = node7;

         ZigZagBinaryTree tree = new ZigZagBinaryTree(node1);
         // revert node 2, node 5
         System.out.println("original tree");
         tree.printTreeByLevel();

         System.out.println("zigzag tree");
         tree.printTreeZigZag();

         System.out.println("zigzag tree");
         List<List<Integer>> result = tree.zigzagLevelOrder(node1);
         for (List<Integer> numList : result) {
             for (Integer num : numList) {
                 System.out.println(num);
             }
         }
         return;
    }



}
