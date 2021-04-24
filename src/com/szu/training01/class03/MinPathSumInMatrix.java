package com.szu.training01.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  给定一个二维数组 matrix ,其中每个数都是正数，要求从左上到右下每一步只能向左或者向下，沿途经过的数字加起来
 *  请返回最小路径和
 *
 * @Date 2021/4/13 21:57
 */

public class MinPathSumInMatrix {

    int totalRow = 0;
    int totalCol = 0;
    public int getMinPathViolence(int[][] matrix){
        totalRow = matrix.length;
        totalCol = matrix[0].length;
        return getMinPathViolence(matrix, 0, 0, 0);
    }

    private int getMinPathViolence(int[][] matrix, int row, int col, int curSum) {
        if (row == matrix.length - 1 && col == matrix[0].length - 1)
            return curSum + matrix[row][col];
//        int min = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        int down = Integer.MAX_VALUE;
        if (row == totalRow - 1){

            right = getMinPathViolence(matrix, row, col + 1, curSum+matrix[row][col]);
        }
        else if (col == totalCol - 1)
            down = getMinPathViolence(matrix, row + 1, col, curSum + matrix[row][col]);
        else{
            down = getMinPathViolence(matrix, row + 1, col, curSum + matrix[row][col]);
            right = getMinPathViolence(matrix, row, col + 1, curSum+matrix[row][col]);
        }
//        min = Math
        return Math.min(right, down);
    }

    public int getMinPathDp(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;
        int dp[][] = new int[rows][cols];
        /* 最后一行 的 最后一列 只能等于 matrix对应值 自己 */
        dp[rows - 1][cols - 1] = matrix[rows - 1][cols - 1];
        /* 最后一行 只能向右走， 所以是 matrix 对应值 + dp 矩阵中右边的和 */
        for (int i = cols - 2; i >= 0; i--) {
            dp[rows - 1][i] = matrix[rows - 1][i] + dp[rows - 1][i + 1];
        }
        /* 最后一列只能向下走， 所以dp 值 是 matrix 对应值 + dp 矩阵中下边的和 */
        for (int i = rows - 2; i >= 0; i--) {
            dp[i][cols - 1] = matrix[i][cols - 1] + dp[i + 1][cols - 1];
        }
        /*
        * 任意一个 普遍位置 应该填的值 是 要么向下走 要么向右走
        * 所以 普遍位置的 值 是 右边和 下边 的最小值
        * */
        for (int r = rows - 2; r >= 0; r--) {
            for (int c = cols - 2; c >= 0; c--) {
                dp[r][c] = Math.min( matrix[r][c] + dp[r+1][c], matrix[r][c] + dp[r][c+1] );
            }
        }
//        返回 0 0 位置 就是 答案
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] m = {
                { 1, 3, 5, 9 },
                { 2, 1, 2, 3 },
                { 1, 1, 3, 4 },
                { 1, 1, 3, 4 },
                { 2, 0, 6, 1 },
                { 3, 8, 4, 8 },
                { 2, 1, 2, 3 },
                { 3, 8, 4, 8 },
                { 2, 1, 2, 3 },
                { 1, 3, 5, 9 },
                { 2, 1, 2, 3 },
                { 1, 3, 5, 9 },
                { 2, 1, 2, 3 }

        };
//        printMatrix(m);
        MinPathSumInMatrix test = new MinPathSumInMatrix();
        System.out.println(test.getMinPathViolence(m));
        System.out.println(test.getMinPathDp(m));
//        System.out.println(minPathSum3(m));

    }

}
