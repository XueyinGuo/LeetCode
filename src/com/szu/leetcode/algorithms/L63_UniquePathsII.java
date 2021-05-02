package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 63. 不同路径 II
    一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

    现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * @Date 2021/5/2 22:38
 */

public class L63_UniquePathsII {
    /*
    * 记忆化搜索
    * */


    private int getUniquePaths(int r, int c, int m, int n, int[][] dp, int[][] obstacleGrid) {
        if (r == m - 1 && c == n - 1)
            return 1;
        if (dp[r][c] != 0)
            return dp[r][c];
            int ways = 0;

        if (obstacleGrid[r][c] == 1){
            dp[r][c] = -1;
            return dp[r][c];
        }
        if (r + 1 < m)
            ways += getUniquePaths(r + 1, c, m, n, dp, obstacleGrid);

        if (c + 1 < n)
            ways += getUniquePaths(r, c + 1, m, n, dp, obstacleGrid);
        dp[r][c] = ways;
        return ways;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        return getUniquePaths(0, 0, m, n, dp, obstacleGrid);
    }

}
