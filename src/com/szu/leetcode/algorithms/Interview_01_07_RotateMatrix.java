package com.szu.leetcode.algorithms;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/26 20:35
 */

public class Interview_01_07_RotateMatrix {

    public static void rotate(int[][] matrix) {
        int x0 = 0, y0 = 0;
        int x1 = matrix.length-1, y1 = matrix[0].length-1;
        /* 每次不多转，就一圈，由外而内的把所有的转90度 */
        // x0 y0 是左上角坐标
        // x1 y1 是右下角坐标
        while ( x0 <= x1 && y0 <= y1 ){
            rotate(matrix, x0, y0, x1, y1);
            x0++;
            y0++;
            x1--;
            y1--;
        }
    }

    public static void rotate(int[][] matrix, int x0, int y0, int x1, int y1) {
        // 定义四个点，分别对应每次应该互换位置的四个点
        /*
        *         a ->             b
        *           1	2	3	4   ↓
                    5	6	7	8
                    9	10	11	12
                 ↑  13	14	15	16
        *         c             <- d
        * */
        int a1 = x0,b1 = y0;
        int a2 = x0,b2 = y1;
        int a3 = x1,b3 = y1;
        int a4 = x1,b4 = y0;
        for (int i = 0; i < y1 - y0; i++) {
            int tem = matrix[a1][b1];
            matrix[a1][b1++] = matrix[a4][b4];
            matrix[a4--][b4] = matrix[a3][b3];
            matrix[a3][b3--] = matrix[a2][b2];
            matrix[a2++][b2] = tem;
        }
    }

}
