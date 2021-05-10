package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 872. 叶子相似的树
请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。

 *
 * @Date 2021/5/10 17:12
 */

import com.szu.leetcode.utils.TreeNode;

import java.util.ArrayList;

public class L872_LeafSimilar {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> leaves1 = new ArrayList<>();
        ArrayList<Integer> leaves2 = new ArrayList<>();
        getLeaves(root1, leaves1);
        getLeaves(root2, leaves2);
        if (leaves1.size() != leaves2.size())
            return false;

        for (int i = 0; i < leaves1.size(); i++) {
            if (leaves1.get(i) != leaves2.get(i))
                return false;
        }
        return true;
    }


    private void getLeaves(TreeNode root, ArrayList<Integer> leaves) {

        if (root.left == null && root.right == null){
            leaves.add(root.val);
        }
        if (root.left != null)
            getLeaves(root.left, leaves);
        if (root.right != null)
            getLeaves(root.right, leaves);
    }

}
