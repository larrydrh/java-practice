package binarytree.inverttree;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Deque<TreeNode> deque = new LinkedBlockingDeque<>();
        Deque<Optional<TreeNode>> treeNodeDeque = new LinkedBlockingDeque<>();
        Deque<TreeNode> rebuildTreeNodeDeque = new LinkedBlockingDeque<>();
        deque.add(root);
        treeNodeDeque.add(Optional.ofNullable(root));
        while (!deque.isEmpty()) {
            TreeNode treeNode = deque.poll();
            treeNodeDeque.add(Optional.ofNullable(treeNode.left));
            treeNodeDeque.add(Optional.ofNullable(treeNode.right));
            if (treeNode.left != null) {
                deque.add(treeNode.left);
            }
            if (treeNode.right != null) {
                deque.add(treeNode.right);
            }

        }
        if (!treeNodeDeque.peek().isEmpty()) {
            rebuildTreeNodeDeque.add(treeNodeDeque.poll().get());
        } else {
            return null;
        }
        TreeNode result = rebuildTreeNodeDeque.peek();
        while (!rebuildTreeNodeDeque.isEmpty()) {
            TreeNode treeNode = rebuildTreeNodeDeque.poll();
            treeNode.right = treeNodeDeque.peek().isEmpty() ? treeNodeDeque.poll().orElse(null) : treeNodeDeque.poll().get();
            treeNode.left = treeNodeDeque.peek().isEmpty() ? treeNodeDeque.poll().orElse(null) : treeNodeDeque.poll().get();
            if (treeNode.right != null) {
                rebuildTreeNodeDeque.add(treeNode.right);
            }
            if (treeNode.left != null) {

                rebuildTreeNodeDeque.add(treeNode.left);
            }
        }
        return result;

    }

    public TreeNode buildTree(List<Integer> treeList) {

        Deque<Optional<Integer>> treeDeque = new ArrayDeque<>();
        treeList.forEach(e-> treeDeque.add(Optional.ofNullable(e)));
        Deque<TreeNode> rebuildTree = new ArrayDeque<>();
        rebuildTree.add(new TreeNode(treeDeque.poll().get()));
        TreeNode root = rebuildTree.peek();
        while(!rebuildTree.isEmpty()) {
            TreeNode treeNode =rebuildTree.poll();
            if (!treeDeque.isEmpty()) {
                if (!treeDeque.peek().isEmpty()) {
                    treeNode.left = new TreeNode(treeDeque.poll().get());
                    rebuildTree.add(treeNode.left);
                }  else {
                    treeDeque.poll();
                }
            }
            if (!treeDeque.isEmpty()) {
                if (!treeDeque.peek().isEmpty()) {
                    treeNode.right = new TreeNode(treeDeque.poll().get());
                    rebuildTree.add(treeNode.right);
                } else {
                    treeDeque.poll();
                }
            }
        }
     return root;
    }

    public static void main(String[] args) {

        List<Integer> treeList = Arrays.asList(1, null, 2);
        Solution solution = new Solution();
        TreeNode result = solution.invertTree(solution.buildTree(treeList));

     }
}