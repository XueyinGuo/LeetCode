package com.szu.training01.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      返回树种最大的路径和，只能从根节点开始到叶子节点
 *
 * @Date 2021/4/8 16:04
 */

import com.szu.leetcode.utils.LeetCodes;
import com.szu.leetcode.utils.TreeNode;

public class MaxSumFromRootToLeave {

    static int max = Integer.MIN_VALUE;

    public void getMaxInTree(TreeNode root, int curSum){
        if (root == null){
            max = Math.max(max, curSum);
            return;
        }
        getMaxInTree(root.left, curSum + root.val);
        getMaxInTree(root.right, curSum + root.val);
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,14,-4, 1, 5};
        TreeNode root = LeetCodes.arrayToTree(arr);
        new MaxSumFromRootToLeave().getMaxInTree(null, 0);
        if (max == Integer.MIN_VALUE)
            System.out.println(0);
        System.out.println(max);
    }
}
