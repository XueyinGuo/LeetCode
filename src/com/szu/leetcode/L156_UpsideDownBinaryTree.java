package com.szu.leetcode;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/14 23:01
 */

import com.szu.leetcode.utils.LeetCodes;
import com.szu.leetcode.utils.TreeNode;

public class L156_UpsideDownBinaryTree {

    public TreeNode upsideDownBinaryTree(TreeNode root) {

        return doUpsideDownBinaryTree(root, root.left, root.right);
    }

    public TreeNode doUpsideDownBinaryTree(TreeNode root, TreeNode left, TreeNode right){

        if(left == null) return root;
//        if(root.left == null && root.right == null) return root;

        TreeNode newParent = left;
        TreeNode oldLeft = newParent.left;
        TreeNode oldRight = newParent.right;
        newParent.left = right;
        newParent.right = root;
        root.right = null;
        right.left = null;
        return doUpsideDownBinaryTree( newParent, oldLeft, oldRight);

    }

    public static void main(String[] args) {
        TreeNode root = new LeetCodes().arrayToTree(new int[]{1, 2, 3, 4, 5});
        new L156_UpsideDownBinaryTree().upsideDownBinaryTree(root);
    }

}
