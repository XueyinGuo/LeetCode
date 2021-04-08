package com.szu.leetcode.algorithms;

import com.szu.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
* 95. 不同的二叉搜索树 II
*
* 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
* */
public class L95_GenerateBiSearchTree {
    public List<TreeNode> generateTrees(int n) {
        return doGenerate(1, n);
    }

    private List<TreeNode> doGenerate(int s, int b) {
        // 用于存放所有的树的根节点
        ArrayList<TreeNode> res = new ArrayList<>();
        // 此时已经 左值 > 右值 ，此时已经不可能存在二叉搜索树
        if (s > b){
            res.add(null);
        }

        for (int i = s; i <= b; i++) {
            // 获取到下一层所有的左树和右树（此时左树右树都是排好顺序的，而且不包含左树的子树和右树的子树，只有子树的根节点）
            List<TreeNode> leftTree = doGenerate(s, i - 1);
            List<TreeNode> rightTree = doGenerate(i + 1, b);
            // 目前的左右子树集合中只有左右子树的根节点，遍历所有的根节点
            // 为每一种可能性制作一个单独的根节点放入 res
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new L95_GenerateBiSearchTree().generateTrees(5));
    }
}

