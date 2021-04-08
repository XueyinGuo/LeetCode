package com.szu.leetcode.utils;

public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;
    TreeNode() {}
    public TreeNode(Integer val) { this.val = val; }
    public TreeNode(Integer val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode charToTreeNode(char c){
        if (c == '#') return null;
        return new TreeNode(c - '0');
    }
}
