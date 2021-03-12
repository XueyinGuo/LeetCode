package com.szu.leetcode;

import com.szu.leetcode.utils.TreeNode;

/*
 * 110. 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 * */
public class L110_IsBalanced {

    // 判断是否是平衡二叉树的返回值，默认是true
    boolean flag = true;
    // 已经判定这个树不是平衡树，以后的递归可以直接跳过
    boolean decided = false;

    public boolean isBalanced(TreeNode root) {

        judge(root, 0);
        return flag;
    }

    /*
     * deep： 当前树的深度
     * */
    public int judge(TreeNode root, int deep) {
        if (decided) {
            return -1;
        }
        // 节点为空了，返回当前的深度
        if (root == null) {
            return deep;
        }
        // 递归判断子树是否是平衡树，并把深度 + 1
        // 分别得到了左右子树的深度
        int left = judge(root.left, deep + 1);
        int right = judge(root.right, deep + 1);
        // 如果左右两颗子树的高度差的绝对值大于1，就不是平衡树
        if (Math.abs(left - right) > 1) {
            flag = false;
            decided = true;
        }
        return Math.max(left, right);
    }

}

