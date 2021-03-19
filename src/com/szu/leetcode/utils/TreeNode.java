package com.szu.leetcode.utils;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode charToTreeNode(char c){
        if (c == '#') return null;
        return new TreeNode(c - '0');
    }
}
