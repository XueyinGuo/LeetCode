package com.szu.training01.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      124. 二叉树中的最大路径和
        路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

        路径和 是路径中各节点值的总和。

        给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * @Date 2021/4/8 16:17
 */

import com.szu.leetcode.utils.LeetCodes;
import com.szu.leetcode.utils.TreeNode;

public class MaxSumInTreeNodeToNode {

    static int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root){
        if (root == null){
            return 0;

        }
        // 每次往下层递归传递的时候进行一次判断：当前节点的值 与 当前节点的值 + 之前所有节点的值的和 哪个大，大的下传
        int left = Math.max( maxPathSum(root.left), 0 );
        int right = Math.max( maxPathSum(root.right), 0 );

        max = Math.max(max, left + right + root.val);

        return root.val + Math.max( left, right );
    }

    public static void main(String[] args) {
        Integer[] arr = {-10,9,20,null,null,15,7};
        TreeNode root = LeetCodes.integerArrayToTree(arr);
        System.out.println(new MaxSumInTreeNodeToNode().maxPathSum(root));
    }

}
