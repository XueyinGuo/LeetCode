package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 64. 最小路径和
给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。
 *
 * @Date 2021/5/27 23:19
 */

import com.szu.leetcode.utils.LeetCodes;

public class L64_MinPathSum {
    /*
    * 记忆化搜索直接怼就完事了
    * */
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                dp[r][c] = -1;
            }

        }
        return findMinPath(grid, 0, 0, dp);
    }

    private int findMinPath(int[][] grid, int r, int c, int[][] dp) {
        /*
        * 只有最下角的时候返回格子的值
        * */
        if (r == grid.length-1 && c == grid[0].length-1){
            dp[r][c] = grid[r][c];
            return grid[r][c];
        }
        if (dp[r][c] != -1)
            return dp[r][c];
        int pathLen = Integer.MAX_VALUE;
        if (r + 1 < grid.length) {
            pathLen = Math.min(pathLen, grid[r][c] + findMinPath(grid, r + 1, c, dp));
        }
        if (c + 1 < grid[0].length) {
            pathLen = Math.min(pathLen, grid[r][c] + findMinPath(grid, r, c + 1, dp));
        }
        dp[r][c] = pathLen;
        return pathLen;
    }


    public static void main(String[] args) {
        L64_MinPathSum test = new L64_MinPathSum();
        test.minPathSum(LeetCodes.getInputMatrix("[[1,3,1],[1,5,1],[4,2,1]]"));
    }
}
