package com.szu.training02.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个二维数组 matrix ,可以从任意位置出发，每一步可以走向上 下 左 右
 * 四个方向，返回最大递增链的长度
 *
 * matrix = {
 *      {5,4,3},
 *      {3,1,2},
 *      {2,1,3}
 * };
 * 中间的 1 出发： 12345 长度为 5
 *
 * @Date 2021/4/25 23:46
 */

import com.szu.leetcode.utils.LeetCodes;

public class LongestIncreasingPath {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[][] matrix = {
                    LeetCodes.getRandomArray(10, 30),
                    LeetCodes.getRandomArray(10, 30),
                    LeetCodes.getRandomArray(10, 30),
                    LeetCodes.getRandomArray(10, 30),
                    LeetCodes.getRandomArray(10, 30),
                    LeetCodes.getRandomArray(10, 30),
                    LeetCodes.getRandomArray(10, 30)
            };
            int longest = longest(matrix);
            int right = maxPath2(matrix);
            if (longest != right)
                System.out.println("FUCK");
        }


    }

    /*
     * 傻缓存的动态规划
     * */
    public static int longest(int[][] matrix) {
        int ans = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int cur = longest(matrix, r, c, dp, visited);
                if (cur > ans)
                    ans = cur;
            }
        }
        return ans;
    }

    private static int longest(int[][] matrix, int r, int c, int[][] dp, boolean[][] visited) {
        if (dp[r][c] != 0)
            return dp[r][c];
        visited[r][c] = true;

        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;
        if (r - 1 >= 0 && !visited[r - 1][c] && matrix[r - 1][c] > matrix[r][c])
            up = longest(matrix, r - 1, c, dp, visited);
        if (r + 1 < matrix.length && !visited[r + 1][c] && matrix[r + 1][c] > matrix[r][c])
            down = longest(matrix, r + 1, c, dp, visited);
        if (c + 1 < matrix[0].length && !visited[r][c + 1] && matrix[r][c+1] > matrix[r][c])
            right = longest(matrix, r, c + 1, dp, visited);
        if (c - 1 >= 0 && !visited[r][c - 1] && matrix[r][c - 1] > matrix[r][c])
            left = longest(matrix, r, c - 1, dp, visited);
        int ans = 1 + Math.max(Math.max(up, down), Math.max(right, left));
        dp[r][c] = ans;
        visited[r][c] = false;
        return ans;
    }


    /*
     * 大神的right代码
     * */
    public static int maxPath2(int[][] matrix) {
        int ans = Integer.MIN_VALUE;

        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                ans = Math.max(ans, process(matrix, row, col, dp));
            }
        }
        return ans;
    }

    // 假设在matrix中，从i行，j列出发，能走出的最长递增路径，返回
    // dp[i][j] == 0 process(i,j) 之前没遇到过
    // dp[i][j] != 0 process(i,j) 之前已经算过了
    public static int process(int[][] matrix, int i, int j, int[][] dp) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
            return -1;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int next1 = 0;
        int next2 = 0;
        int next3 = 0;
        int next4 = 0;
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            next1 = process(matrix, i - 1, j, dp);
        }
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
            next2 = process(matrix, i + 1, j, dp);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            next3 = process(matrix, i, j - 1, dp);
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
            next4 = process(matrix, i, j + 1, dp);
        }
        int ans = 1 + Math.max(Math.max(next1, next2), Math.max(next3, next4));
        dp[i][j] = ans;
        return ans;
    }

}
