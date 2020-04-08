package binarytree.ancestor;

import com.sun.source.tree.Tree;

import java.util.Stack;

public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
//    输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//    输出: 3
//    解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
//

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //结论0
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        //结论1
        if (left != null && left != q && left != p)
            return left;
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //结论3
        if (left != null && right != null) {
            return root;
        }

        //结论2 4
        else {
            return left == null ? right : left;
        }


    }
}
