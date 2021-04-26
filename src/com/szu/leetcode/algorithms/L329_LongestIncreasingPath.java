package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 329. 矩阵中的最长递增路径
    给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。

    对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 *
 * @Date 2021/4/26 10:29
 */

public class L329_LongestIncreasingPath {

    public int longestIncreasingPath(int[][] matrix) {
        int ans = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        /* 为了不走回头路 */
        /* 注释掉是考虑到，既然已经比较大小了，迈向下一个位置之后，必然不会迈回来 */
//        boolean[][] visited = new boolean[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int cur = longest(matrix, r, c, dp);
                if (cur > ans)
                    ans = cur;
            }
        }
        return ans;
    }

    private int longest(int[][] matrix, int r, int c, int[][] dp) {
        /* 携带一个傻缓存，保证每个位置只访问一遍，计算出他的最长递增长度 */
        if (dp[r][c] != 0)
            return dp[r][c];
//        visited[r][c] = true;
        /* 往四个方向增加多长 */
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;
        /*
         * 如果对应方向的值 比 我当前大 我才迈向他，否则不予考虑
         * */
        if (r - 1 >= 0 && /*!visited[r - 1][c] &&*/ matrix[r - 1][c] > matrix[r][c])
            up = longest(matrix, r - 1, c, dp/*, visited*/);
        if (r + 1 < matrix.length /*&& !visited[r + 1][c]*/ && matrix[r + 1][c] > matrix[r][c])
            down = longest(matrix, r + 1, c, dp/*, visited*/);
        if (c + 1 < matrix[0].length/* && !visited[r][c + 1]*/ && matrix[r][c + 1] > matrix[r][c])
            right = longest(matrix, r, c + 1, dp/*, visited*/);
        if (c - 1 >= 0 /*&& !visited[r][c - 1]*/ && matrix[r][c - 1] > matrix[r][c])
            left = longest(matrix, r, c - 1, dp/*, visited*/);
        /*
         * 计算完四个方向的长度，求个最大，然后加上自己，如果四周都比自己小，那么自己的最长递增长度就是他自己， 也就是 1
         * */
        int ans = 1 + Math.max(Math.max(up, down), Math.max(right, left));
        dp[r][c] = ans;
//        visited[r][c] = false;
        return ans;
    }

}
