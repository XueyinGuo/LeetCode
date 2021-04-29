package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 516. 最长回文子序列
给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 *
 * @Date 2021/4/29 15:24
 */

public class L516_LongestPalindromeSubsequence {

    public int longestPalindromeSubsequence(String s) {
        if (s == null || s.length() == 0)
            return 0;
        char[] str = s.toCharArray();
        int length = str.length;
        int[][] dp = new int[length][length];
        /*
        * 范围尝试
        *
        * 回文 对角线肯定都是 1
        * */
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }
        /*
        * 第二条对角线 两个字符相等就是 2， 否则 回文长度就是 1
        * */
        for (int i = 0; i < length - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }

        /*
        * 每个普遍位置：
        * 在两端字符不相等的情况下，要么保留前一个，要么保留后一个，要么两个都不保留：三种情况选最大
        *
        * 两端字符相等的情况下，去掉两头的字符的回文长度 + 2 即为解
        * */
        for (int r = length - 2; r >= 0; r--) {
            for (int c = r + 2; c < length; c++) {
                if (str[r] == str[c])
                    dp[r][c] = dp[r + 1][c - 1] + 2;
                else {

                    dp[r][c] = dp[r + 1][c];
                    if (dp[r][c - 1] > dp[r][c])
                        dp[r][c] = dp[r][c - 1];
                }
            }
        }
        return dp[0][length-1];
    }

}
