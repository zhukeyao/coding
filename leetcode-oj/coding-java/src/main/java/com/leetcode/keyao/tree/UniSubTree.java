package com.leetcode.keyao.tree;

/**
 * Created by keyao on 11/7/16.

250. Given a binary tree, count the number of uni-value subtrees.

 A Uni-value subtree means all nodes of the subtree have the same value.

 For example:
 Given binary tree,

    5
   / \
  1   5
 / \   \
 5   5  5


 return 4.

 Keyao:  There is two algorithms implemented. The reason the second one is more consice than the first one is that
       the second return Boolean flag to denote whether not not child node has the same value as parent node.
       The parent->child comparison is done in child recursive section. While, in Algo 1, the comparision is done
       in parent node recursive section, which requires we pass back child node value.

       We also need to be very careful not to put recursive call in the "and" operation as the second call may be
       skipped if the first one return false. (see line 123)

 */
public class UniSubTree {
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

    UniSubTree(TreeNode root) {
        this.root = root;
    }

    public void init() {
        count = 0;
    }

    public void print() {
        if (root != null) {
            root.print();
        }
    }

    int count = 0;

    public int countUniSubTree() {
        countUniSubTree(root);
        return count;
    }

    public int countUniSubTree(TreeNode node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null)
        {
            count++;
            return node.val;
        }

        int leftUniValue = -1;
        int rightUniValue = -1;

        leftUniValue = countUniSubTree(node.left);
        if (node.right == null) {
            if (node.val == leftUniValue) {
                count++;
                return leftUniValue;
            }
        }

        rightUniValue = countUniSubTree(node.right);
        if (node.left == null) {
            if (node.val == rightUniValue) {
                count++;
                return rightUniValue;
            }
        }

        if (node.val == leftUniValue && node.val == rightUniValue) {
            count++;
            return node.val;
        }

        return -1;
    }

    public int countUniSubTree2() {
        countUniSubTree2(root, -1);
        return count;
    }

    public Boolean countUniSubTree2(TreeNode node, int parentValue) {

        if (node == null) return true;
        Boolean checkLeft = countUniSubTree2(node.left, node.val);
        Boolean checkRight = countUniSubTree2(node.right, node.val);

        // note: following line is tricky, second condition may not be evaluated if first statement return is false.
        //       which may lead wrong resurive result.
        //if (countUniSubTree2(node.left, node.val) && countUniSubTree2(node.right, node.val))

        if (checkLeft && checkRight) {
            count++;
            return node.val == parentValue;
        }
        return false;
    }

    static public void main(String[] arg) {
         UniSubTree.TreeNode node1 = new UniSubTree.TreeNode(5);
         UniSubTree.TreeNode node2 = new UniSubTree.TreeNode(1);
         UniSubTree.TreeNode node3 = new UniSubTree.TreeNode(5);
         UniSubTree.TreeNode node4 = new UniSubTree.TreeNode(5);
         UniSubTree.TreeNode node5 = new UniSubTree.TreeNode(5);
         UniSubTree.TreeNode node6 = new UniSubTree.TreeNode(5);

         node1.left = node2;
         node1.right = node3;
         node2.left = node4;
         node2.right = node5;
         node3.right = node6;

         UniSubTree tree = new UniSubTree(node1);
         // revert node 2, node 5
         System.out.println("original tree");
         tree.print();

         int count = tree.countUniSubTree();
         System.out.println("uniSubtree = " + count);

         tree.init();
         int count2 = tree.countUniSubTree2();
         System.out.println("uniSubtree = " + count2);

    }

}
