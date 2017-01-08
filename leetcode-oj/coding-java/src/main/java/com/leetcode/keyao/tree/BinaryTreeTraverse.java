package com.leetcode.keyao.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 *
 *
 145. Given a Bhinary tree, return the postorder traversal of its nodes' values.
 For example:
 Given binary tree {1,#,2,3},
 1
 \
  2
 /
 3
 return [3,2,1].

 Note:
      algorithm 1:
        1.  Use stack, pop up the top of current,
            the output sequence should be left child tree -> right child tree -> current
        2.  Compare current node with stack top, if top.right == current, that means we can immediate
            that means all current's child has been handled, put current into result list
        3.  if top.left = current, that means we need push current right child tree into stack, so we
            a. push null into the stack to denote this current left tree has been handled.
            b. push current back to stack
            c. push current.right's left tree into the stack (start to handle current.right tree)
        4. if parent == null, that means current's left tree has been handled, and its waiting for its
           right tree to be handled, when we reach here, it is safe to put current.val into result list

      algorithm 2: This is a better algorithm!  Basically, there is 3 possiblities,
        1. going down all the way to the left and push all node into stack.
        2. come back from left (peek opertion, do not touch the top node yet), then we need peek the top of the stack, and going down all the way from
           top.right.
        3. come back from right (pop operation) , in this case, we need add the value into result list
           Note that, here we need keep popping until we are not going back from right anymore,
           i.e., top.right != current

 */

public class BinaryTreeTraverse {
   static public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
   }

   BinaryTreeTraverse() {
   }

   synchronized  static public List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> result = new ArrayList<>();
      if (root == null) {
         return result;
      }
      traverse(root, result);
      return result;
   }

   static public void traverse(TreeNode root, List<Integer> result) {
       if (root.left != null) {
         traverse(root.left, result);
      }
      result.add(root.val);
      if (root.right != null) {
         traverse(root.right, result);
      }
   }

   static public List<Integer> postorderTraversal(TreeNode root) {
      List<Integer> result = new ArrayList<>();
      LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
      if (root == null) {
         return result;
      }
      TreeNode rootParent = new TreeNode(-1);
      rootParent.left = root;
      pushLeftChild(rootParent, stack);
      while (stack.size() > 0)  {
         TreeNode current = stack.removeFirst();
         if (current == rootParent) break;
         if (current == null) continue;
         TreeNode parent = stack.getFirst();
         // this means
         if (parent == null)
         {
           result.add(current.val);
           continue;
         }

         if (parent.left == current) {
            // this meas current right is not handled yet
            stack.push(null);
            stack.push(current);
            if (current.right != null) {
               pushLeftChild(current.right, stack);
            }
         }
         else if (parent.right == current) {
            result.add(current.val);
         }
      }
      return result;
   }

   static public List<Integer> postorderTraversal2(TreeNode root) {
      List<Integer> result = new ArrayList<Integer>();
      TreeNode current = root;
      LinkedList<TreeNode> stack = new LinkedList<>();
      while (!stack.isEmpty() || current != null) {

         // step 1: push left children into stack
         while (current != null) {
            stack.push(current);
            current = current.left;
         }
         // step 2: come back from left side
         current = stack.peek();
         if (current.right != null) {
            current = current.right;
            continue;
         }

         // step 3: come back from right side
         current = stack.pop();
         result.add(current.val);
         // repeat pop stack if current is top's right node
         while (!stack.isEmpty() && stack.peek().right == current) {
            current = stack.pop();
            result.add(current.val);
         }

         if (!stack.isEmpty()) {
            current = stack.peek().right;
         }
         else {
            current = null;
         }
      }
      return result;
   }


   static public void pushLeftChild(TreeNode current, LinkedList<TreeNode> stack) {
      while (current != null) {
         stack.push(current);
         current = current.left;
      }
   }

   static TreeNode findInOrderSucc(TreeNode root, TreeNode input) {
      if (input == null) return null;
      if (input.right != null) {
         return findLeftMost(input.right);
      }
      TreeNode prev = null;
      TreeNode current = root;
      while (current != null ) {
         if (current.val > input.val) {
            prev = current;
            current = current.left;
         }
         if (current.val < input.val) {
            prev = current;
            current = current.right;
         }
      }
      return prev;
   }

   static TreeNode findLeftMost(TreeNode current) {
      if (current == null) return null;
      if (current.left != null) {
         current = current.left;
      }
      return current;
   }

   static public void main(String[] arg) {
      BinaryTreeTraverse.TreeNode node1 = new BinaryTreeTraverse.TreeNode(1);
      BinaryTreeTraverse.TreeNode node2 = new BinaryTreeTraverse.TreeNode(2);
      BinaryTreeTraverse.TreeNode node3 = new BinaryTreeTraverse.TreeNode(3);
      BinaryTreeTraverse.TreeNode node4 = new BinaryTreeTraverse.TreeNode(4);

      //node1.right = node2;
      //node2.left = node3;

      node1.left = node2;
      node1.right = node3;
      node2.right = node4;

      System.out.println("in order traverse");
      List<Integer> result = BinaryTreeTraverse.inorderTraversal(node1);
      for (Integer value : result) {
         System.out.println(value);
      }

      System.out.println("post order traverse 1");
      result = BinaryTreeTraverse.postorderTraversal(node1);

      for (Integer value : result) {
         System.out.println(value);
      }

      System.out.println("post order traverse 2");
      result = BinaryTreeTraverse.postorderTraversal2(node1);

      for (Integer value : result) {
         System.out.println(value);
      }


   }
}
