package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 62. 不同路径
    一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

    问总共有多少条不同的路径？
 *
 * @Date 2021/5/2 22:38
 */

public class L62_UniquePaths {
    /*
    * 记忆化搜索
    * */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        return getUniquePaths(0, 0, m, n, dp);
    }

    private int getUniquePaths(int r, int c, int m, int n, int[][] dp) {
        if (r == m - 1 && c == n - 1)
            return 1;
        if (dp[r][c] != 0)
            return dp[r][c];
            int ways = 0;
        if (r + 1 < m)
            ways += getUniquePaths(r + 1, c, m, n, dp);

        if (c + 1 < n)
            ways += getUniquePaths(r, c + 1, m, n, dp);
        dp[r][c] = ways;
        return ways;
    }


}
