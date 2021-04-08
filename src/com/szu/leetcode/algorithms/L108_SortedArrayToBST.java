package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      108. 将有序数组转换为二叉搜索树
 *
 *      将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 *      本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * @Date 2021/2/17 0:16
 */

import com.szu.leetcode.utils.TreeNode;

public class L108_SortedArrayToBST {



    public TreeNode sortedArrayToBST(int[] nums) {

        return doBuildTree(nums, 0, nums.length - 1);
    }

    public TreeNode doBuildTree(int[] nums, int start, int end){
        // base case ： start > end 不用递归其他节点了
        if(start > end){
            return null;
        }
        // 找到子问题的中间节点，继续递归解决子问题
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = doBuildTree(nums, start, mid - 1);
        root.right = doBuildTree(nums, mid + 1, end);
        return root;
    }
}
