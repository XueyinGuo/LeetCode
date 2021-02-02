package com.szu.leetcode.tree.bi_search_tree;


import java.util.*;

/*
* 96. 不同的二叉搜索树
*
* 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
*
* 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
* 内存消耗：35.2 MB, 在所有 Java 提交中击败了49.61%的用户
* */
public class NumBiSearchTrees_96 {

    public int numTrees(int n) {
        //如果传入的 n 小于 1 ，不可能有二叉搜索树个数，直接返回 0
        if (n < 1){
            return 0;
        }
        // 建立一个 傻缓存，因为之后有可能会有重复的计算，所以为了避免计算，设置一个傻缓存
        int dp[][] = new int[n+1][n+1];
        return doCountNums(1, n ,dp);
    }

    private int doCountNums(int s, int b, int[][] dp) {
        // 当左值 small 大于 右值 big的时候才跳出当前递归
        // s = b 的时候，s 或者 b 就是当前这层的根节点，往下一层递归的时候才跳出递归
        if (s > b){
            return 1;
        }
        // 如果缓存中表示已经计算过则直接返回缓存中的值
        if (dp[s][b] != 0){
            return dp[s][b];
        }
        int sum = 0;
        for (int i = s; i <= b; i++) {
            // 以 i 为根节点，递归穷举左右子树
            // 当 i 为
            int left = doCountNums(s, i - 1, dp);
            int right = doCountNums(i + 1, b, dp);
            // 子树个数为左右子树的数量乘积
            sum += (left * right);
        }
        // 放入缓存这次已经已经计算的值，防止多次计算
        dp[s][b] = sum;
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(new NumBiSearchTrees_96().numTrees(10));
        Stack<Object> objects = new Stack<>();

    }
}
