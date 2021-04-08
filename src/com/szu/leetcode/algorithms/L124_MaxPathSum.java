package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/8 22:34
 */

import com.szu.leetcode.utils.TreeNode;

public class L124_MaxPathSum {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        doGetMax(root);
        return max;
    }
    public int doGetMax(TreeNode root){
        if(root == null){
            return 0;
        }
        // 只有当左右子树的返回值 > 0 的时候才会加到当前节点上，否则就是在帮倒忙
        int leftValue = Math.max(doGetMax(root.left), 0);
        int rightValue = Math.max(doGetMax(root.right), 0);

        // 把最大值放到 max 中
        max = Math.max(max, leftValue + rightValue + root.val);

        // 返回当前节点
        return root.val + Math.max(leftValue, rightValue);
    }

    public static void main(String[] args) {
        L124_MaxPathSum maxPathSum_124 = new L124_MaxPathSum();
        TreeNode root = new TreeNode(2, new TreeNode(-1), null);
        System.out.println(maxPathSum_124.maxPathSum(root));
    }
}
