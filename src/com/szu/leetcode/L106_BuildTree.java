package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      106. 从中序与后序遍历序列构造二叉树
 *      https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * @Date 2021/3/17 19:52
 */

import com.szu.leetcode.utils.TreeNode;

public class L106_BuildTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(postorder.length == 0){
            return null;
        }
        if(postorder.length == 1){
            return new TreeNode(postorder[0]);
        }
        int idx = -1;
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        for (int i = inorder.length - 1; i >= 0; --i){
            if (inorder[i] == postorder[postorder.length - 1]){
                idx = i + 1;
            }
        }
        int[] left_in = new int[idx - 1];
        int[] left_post = new int[idx - 1];
        int[] right_in = new int[inorder.length - idx];
        int[] right_post = new int[inorder.length - idx];
        System.arraycopy(inorder, 0, left_in, 0, idx - 1);
        System.arraycopy(inorder, idx, right_in, 0, inorder.length - idx);
        System.arraycopy(postorder, 0, left_post, 0, idx - 1);
        System.arraycopy(postorder, idx - 1, right_post, 0, inorder.length - idx);
        root.left = buildTree(left_in, left_post);
        root.right = buildTree(right_in, right_post);
        return root;
    }
}
