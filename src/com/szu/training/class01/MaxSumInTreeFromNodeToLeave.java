package com.szu.training.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      返回树种最大的路径和，只能从某一个节点开始到叶子节点
 *
 * @Date 2021/4/8 15:43
 */

import com.szu.leetcode.utils.LeetCodes;
import com.szu.leetcode.utils.TreeNode;

public class MaxSumInTreeFromNodeToLeave {

    static int max = Integer.MIN_VALUE;

    public void getMaxInTree(TreeNode root, int curSum){
        if (root == null){
            max = Math.max(max, curSum);
            return;
        }
        // 每次往下层递归传递的时候进行一次判断：当前节点的值 与 当前节点的值 + 之前所有节点的值的和 哪个大，大的下传
        getMaxInTree(root.left, Math.max(curSum + root.val, root.val));
        getMaxInTree(root.right, Math.max(curSum + root.val, root.val));
    }

    public static void main(String[] args) {
        int arr[] = {-1,-2,3,4,-4, 1, 5};
        TreeNode root = LeetCodes.arrayToTree(arr);
        new MaxSumInTreeFromNodeToLeave().getMaxInTree(root, 0);
        if (max == Integer.MIN_VALUE)
            System.out.println(0);
        else
            System.out.println(max);
    }

}
