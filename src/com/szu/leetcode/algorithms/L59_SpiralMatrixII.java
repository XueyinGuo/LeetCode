package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 59. 螺旋矩阵 II
给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * @Date 2021/5/27 23:03
 */

public class L59_SpiralMatrixII {

    public int[][] generateMatrix(int n) {
        if (n == 0)
            return new int[][]{};
        if (n == 1)
            return new int[][]{{1}};

        int[][] matrix = new int[n][n];
        int r1 = 0, c1 = 0;
        int r2 = n - 1, c2 = n - 1;
        int start = 1;
        /*
         * 只有 r1 严格 小于 r2 的时候才进入填写本次的外围
         * */
        while (r1 < r2) {

            start = fullFillAround(matrix, r1, c1, r2, c2, start);

            r1++;
            c1++;
            c2--;
            r2--;
        }
        /*
         * 当跳出循环的时候，有两种可能，如果两个位置相等了！
         * 把这个相等的位置的空填成 start
         * */
        if (r1 == r2)
            matrix[r1][c1] = start;
        return matrix;
    }

    private int fullFillAround(int[][] matrix, int r1, int c1, int r2, int c2, int start) {
        /*
         * 转一圈填完所有最外围的值
         * */
        int c = c1;
        while (c < c2) {
            matrix[r1][c++] = start++;
        }
        int r = r1;
        while (r < r2)
            matrix[r++][c2] = start++;

        c = c2;
        while (c > c1)
            matrix[r2][c--] = start++;
        r = r2;
        while (r > r1)
            matrix[r--][c1] = start++;
        return start;
    }

    public static void main(String[] args) {
        L59_SpiralMatrixII test = new L59_SpiralMatrixII();
        test.generateMatrix(4);
    }

}
