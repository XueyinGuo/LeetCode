package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 48. 旋转图像
给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。

你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * @Date 2021/5/20 23:07
 */

public class L48_RotateImage {

    public void rotate(int[][] matrix) {
        int left = 0, right = matrix.length - 1;
        int up = 0, down = matrix.length - 1;
        while (left < right) {

            rotate(matrix, left++, right--, up++, down--);
        }

    }

    private void rotate(int[][] matrix, int left, int right, int up, int down) {
        int limit = right;
        int top = up;
        int bottom = down;
        int mostLeft = left;
        int mostRigh = right;
        while (left < limit) {

            int tem = matrix[top][left];
            matrix[top][left] = matrix[down][mostLeft];
            matrix[down][mostLeft] = matrix[bottom][right];
            matrix[bottom][right] = matrix[up][mostRigh];
            matrix[up][mostRigh] = tem;
            left++;
            up++;
            right--;
            down--;

        }

    }

}
