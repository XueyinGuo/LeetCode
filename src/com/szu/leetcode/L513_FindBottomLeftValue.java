package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *          513. 找树左下角的值
 *              给定一个二叉树，在树的最后一行找到最左边的值。
 *
 * @Date 2021/2/14 13:01
 */

import com.szu.leetcode.utils.TreeNode;

public class L513_FindBottomLeftValue {
    // 记录当前找到的最大深度
    // 每当深度更新的时候顺便更新刚刚找到的更深层第一个元素
    int bottom = -1;
    TreeNode bottomLeft;

    public int findBottomLeftValue(TreeNode root) {
        bottomLeft = root;
        doFind(root, 0);
        return bottomLeft.val;
    }
    public void doFind(TreeNode root, int level){
        if(root == null){
            return;
        }
        // 记录当前找到的最大深度
        // 每当深度更新的时候顺便更新刚刚找到的更深层第一个元素
        if(root.left == null && root.right == null){
            if(level > bottom){
                bottom = level;
                bottomLeft = root;
            }
        }
        // 因为是找最后一层的最左侧元素，所以先往左递归，
        // 并且加上当前所在层数，让下一层递归知道自己的层级
        doFind(root.left, level + 1);
        doFind(root.right, level + 1);
    }


}
