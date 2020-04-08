package binarytree.depth;

/**
 * Definition for a binary tree node.

 */



class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left==null && root.right == null) {
            return 1;
        } else if (root.right == null) {
            return 1 + maxDepth(root.left);
        } else if (root.left == null) {
            return 1 + maxDepth(root.right);
        } else {
            int right = maxDepth(root.right);
            int left = maxDepth(root.left);
            return right > left ?  right+1: left+1;
        }
    }
}