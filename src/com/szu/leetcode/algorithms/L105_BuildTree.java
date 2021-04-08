package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      105. 从前序与中序遍历序列构造二叉树
 *      https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * @Date 2021/3/17 19:50
 */

import com.szu.leetcode.utils.TreeNode;

public class L105_BuildTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (inorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        if (inorder.length == 1) {
            return root;
        }
        //idx表示先序遍历的第一个数字位于中序的第几个位置， 0号为第一个位置
        int idx = 1;
        for (int i = 0; i < inorder.length; ++i) {
            if (inorder[i] == preorder[0]) {
                idx = i + 1;
            }
        }
        //本层左子树的前序和中序
        int[] before_pre = new int[idx - 1];
        int[] before_in = new int[idx - 1];

        System.arraycopy(preorder, 1, before_pre, 0, idx - 1);
        System.arraycopy(inorder, 0, before_in, 0, idx - 1);
        //本层右子树的前序和中序数组长度和赋值
        int[] after_pre = new int[inorder.length - idx];
        int[] after_in = new int[inorder.length - idx];

        System.arraycopy(preorder, idx, after_pre, 0, preorder.length - idx);
        System.arraycopy(inorder, idx, after_in, 0, inorder.length - idx);

        root.left = buildTree(before_pre, before_in);
        root.right = buildTree(after_pre, after_in);
        return root;
    }

}
