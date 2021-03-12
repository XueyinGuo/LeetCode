package com.szu.leetcode;

import com.szu.leetcode.utils.TreeNode;

/*
* 222. 完全二叉树的节点个数
* */
class L222_CountNodes {
    public int countNodes(TreeNode root) {
        // 空树 直接返回0
        if(root == null){
            return 0;
        }

        return doCountNodes(root);
    }

    public int doCountNodes(TreeNode root){
        // 既然是完全二叉树，只要是左树为空，就肯定没有右树了
        if(root.left == null){
            return 1;
        }
        // 有左树无右树，说明这是最后一层的最后一个节点
        if(root.right == null){
            return 2;
        }
        int left = doCountNodes(root.left);
        int right = doCountNodes(root.right);
        // 返回节点的数的时候是 左树个数 + 右树个数 + 自己
        return left + right + 1;
    }
}

