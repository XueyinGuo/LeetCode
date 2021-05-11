package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 221. 最大正方形
在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * @Date 2021/5/11 13:45
 */

public class L221_MaxSquare {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        /*
         * dp 每个位置的值代表的是 从 matrix 的 r c 位置，向右上方看，最大的正方形是多大 边长
         * */
        int max = 0;
        for (int r = 0; r < rows; r++) {
            dp[r][0] = matrix[r][0] - '0';
            if (dp[r][0] == 1) max = 1;
        }
        for (int c = 0; c < cols; c++) {
            dp[0][c] = matrix[0][c] - '0';
            if (dp[0][c] == 1) max = 1;
        }
        /*
         * 每个位置向右上方看，自己三个依赖位置最小的那个 + 1【因为自己这一列一行也要算进去】
         * 能保证 这个边长内全部都是 1
         * */

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (matrix[r][c] == '1') {
                    int left = dp[r][c - 1];
                    int up = dp[r - 1][c];
                    int topLeft = dp[r - 1][c - 1];

                    dp[r][c] = Math.min(left, Math.min(up, topLeft)) + 1;
                    if (dp[r][c] > max)
                        max = dp[r][c];
                }
            }
        }
        return max * max;
    }

}
