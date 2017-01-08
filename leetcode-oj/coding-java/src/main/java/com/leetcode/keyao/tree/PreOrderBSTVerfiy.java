package com.leetcode.keyao.tree;

import java.util.LinkedList;

/**
 * Created by keyao on 11/7/16.
 *You have an array of preorder traversal of Binary Search Tree ( BST). Your program should verify whether it is a correct sequence or not.
 Input: Array of Integer [ Pre-order BST ]
 Output: String “Yes” or “No”

 Keyao Note:
     1. recursive algorithm is easy.
     2. for iterative, the key observation is that, for a
        given value input[i], if there is any bigger value
        show up (input[j] > input[i]), all later input element
        should be bigger than input[i].

        As the bigger value is either in the right subtree of input[i],
        or its ancester's right subtree.

        This should be a key property of BST pre-order serialized
        array.


 */

public class PreOrderBSTVerfiy {
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

    PreOrderBSTVerfiy(TreeNode root) {
        this.root = root;
    }

    public void print() {
        if (root != null) {
            root.print();
        }
    }

    boolean verify1(int[] input) {
        if (input.length <= 0) {
            return false;
        }
        return doVerify1(input, input[0], 1, input.length - 1);
    }

    boolean doVerify1(int[] input, int rootValue, int start, int end) {
        int i;
        if (start >= end) {
            return true;
        }
        for (i=start; i<=end && input[i] < rootValue; i++) {
        }
        for (int j=i; j<=end; j++) {
            if (input[j] <= rootValue) {
                return false;
            }
        }
        boolean checkLeft = doVerify1(input, input[start], start+1, i-1);
        boolean checkRight =doVerify1(input, input[i], i+1, end);

        return checkLeft && checkRight;
    }

    boolean verify2(int input[]) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int min = Integer.MIN_VALUE;
        for (int i : input) {
            if (i <= min) {
                return false;
            }
            while (!stack.isEmpty() && i > stack.peek()) {
                min = stack.pop();
            }
            stack.push(i);
        }
        return true;
    }

    static public void main(String[] arg) {
         //int[] input = {5,3,1,4,6, 9};

         int[] input = {5,3,7,4,6, 9};

         PreOrderBSTVerfiy tree = new PreOrderBSTVerfiy(null);
         Boolean flag = tree.verify1(input);
         System.out.println("verify BST pre order = " + flag);

         flag = tree.verify2(input);
         System.out.println("verify BST pre order algo2 = " + flag);

    }

}
