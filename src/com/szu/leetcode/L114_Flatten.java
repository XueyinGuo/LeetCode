package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *          114. 把二叉树用右指针展开成一个链表，
 *
 *
 * @Date 2021/2/19 16:12
 */

import com.szu.leetcode.utils.TreeNode;

public class L114_Flatten {

    public void flatten(TreeNode root) {
        if(root == null)
            return;
        flatten(root.left);
        flatten(root.right);
        // 先用两个指针指向当前的根节点和右节点
        TreeNode node = root;
        TreeNode right = root.right;
        // 因为刚刚已经用了指针记录右节点，我们可以把右节点直接覆盖
        root.right = root.left;
        // 左节点已经转移到右节点，左节点直接置位 null
        root.left = null;
        // 要把刚刚记录下来的右节点插到现在右节点的后边
        // 所以找到最后一个右节点进行插入
        while(node.right != null){
            node = node.right;
        }
        node.right = right;
    }

}
