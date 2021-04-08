package com.szu.training.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      计算树种的路径最大和，只能从一个节点出发， 到其下一层的某个节点为止，不一定非得是叶子节点
 *
 * @Date 2021/4/8 17:07
 */

import com.szu.leetcode.utils.LeetCodes;
import com.szu.leetcode.utils.TreeNode;

public class MaxSumInTreeNodeDown {

    static int max = Integer.MIN_VALUE;

    public int getMaxInTree(TreeNode root){

        if (root == null)
            return 0;
        // 每个节点都去抓取 自己左右树 的 路径和，如果某一个路径和为负数，那么他只会帮倒忙，就不把他们加到当前的最大和路径中去
        int left= Math.max(getMaxInTree(root.left), 0);
        int right = Math.max(getMaxInTree(root.right), 0);
        // 因为每个节点都只能往下查找最大的路径，所以只选取某一个单侧路径加到最大路径和中去，找就找最大的加
        int curSum = root.val + Math.max(left , right);
        // 每次计算完 ，更新最大值
        max = Math.max( max, curSum);

        return curSum;
    }

    public static void main(String[] args) {
        Integer[] arr = {10,9,20,null,null,15,-7, null, null, null, null,null, null, 30};
        TreeNode root = LeetCodes.integerArrayToTree(arr);
        new MaxSumInTreeNodeDown().getMaxInTree(root);
        if (max == Integer.MIN_VALUE)
            System.out.println(0);
        else
            System.out.println(max);
    }
}
