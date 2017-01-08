package com.leetcode.keyao.tree;

import java.util.LinkedList;

/**
 * Created by keyao on 11/8/16.
 * 297. Serialize/Desrialize Binary Tree.
 *
 * Note that his problem seems easy, but it is hard if you want to get it right
 * in the first time.
 */
public class BinaryTreeSerialization {
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

    BinaryTreeSerialization(TreeNode root) {
        this.root = root;
    }

    public void print() {
        if (root != null) {
            root.print();
        }
    }

    public String serialize() {
        String sep = ",";
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        if (root == null) return "";
        queue.add(root);
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode current = queue.removeFirst();
            if (current != root) {
                builder.append(sep);
            }
            if (current != null) {
                builder.append(String.valueOf(current.val));
            }
            else {
                builder.append('n');
                continue;
            }
            queue.add(current.left);
            queue.add(current.right);

        }
        return builder.toString();
    }

    public void deserialize(String input) {
        int count = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        int length = input.length();
        root = null;
        while (count < length) {
            String token = getNextToken(input, length, count);
            count += token.length() + 1;
            TreeNode node1 = null;
            if (!token.equals("n") && !token.equals("")) {
                int val = Integer.parseInt(token);
                node1 = new TreeNode(val);
            }
            if (root == null) {
                root = node1;
                queue.add(root);
                continue;
            }
            TreeNode parent = queue.removeFirst();
            TreeNode node2 = null;
            token = getNextToken(input,length, count);
            if (!token.equals("n") && !token.equals("") ) {
                int val = Integer.parseInt(token);
                node2 = new TreeNode(val);
            }

            parent.left = node1;
            parent.right = node2;
            count = count+token.length() + 1;
            queue.add(node1);
            queue.add(node2);

        }
    }

    String getNextToken(String input,int length, int start) {
        if (start >= length) return "";
        int i = start;
        while (i < length && input.charAt(i) != ',') {
            i++;
        }
        return input.substring(start, i);
    }

    public void deserialize2(String input) {
        String[] fields = input.split(",");
        LinkedList<TreeNode> queue = new LinkedList<>();
        int count = 0;
        while (count < fields.length) {
            if (count == 0) {
                root = new TreeNode(Integer.parseInt(fields[count]));
                queue.add(root);
                count++;
                continue;
            }

            TreeNode current = queue.removeFirst();
            TreeNode node1 = fields[count].equals("n") ? null : new TreeNode(Integer.parseInt(fields[count]));
            count++;
            TreeNode node2 = fields[count].equals("n") ? null : new TreeNode(Integer.parseInt(fields[count]));
            count++;
            current.left = node1;
            current.right = node2;
            queue.add(node1);
            queue.add(node2);
        }
    }

    static public void main(String[] arg) {
         BinaryTreeSerialization.TreeNode node1 = new BinaryTreeSerialization.TreeNode(1);
         BinaryTreeSerialization.TreeNode node2 = new BinaryTreeSerialization.TreeNode(2);
         BinaryTreeSerialization.TreeNode node3 = new BinaryTreeSerialization.TreeNode(3);
         BinaryTreeSerialization.TreeNode node4 = new BinaryTreeSerialization.TreeNode(4);
         BinaryTreeSerialization.TreeNode node5 = new BinaryTreeSerialization.TreeNode(5);
         BinaryTreeSerialization.TreeNode node6 = new BinaryTreeSerialization.TreeNode(6);
         BinaryTreeSerialization.TreeNode node7 = new BinaryTreeSerialization.TreeNode(7);

         node1.left = node2;
         node1.right = node5;
         node2.left = node3;
         node2.right = node4;
         node5.left = node6;
         node5.right = node7;

         BinaryTreeSerialization tree = new BinaryTreeSerialization(node1);
         String output = tree.serialize();
         System.out.println("tree = " + output);

         BinaryTreeSerialization tree2 = new BinaryTreeSerialization(null);
         tree2.deserialize(output);
         String output2 = tree2.serialize();
         System.out.println("tree = " + output2);

         BinaryTreeSerialization tree3 = new BinaryTreeSerialization(null);
         tree3.deserialize2(output);
         String output3 = tree3.serialize();
         System.out.println("tree = " + output3);

    }

}
