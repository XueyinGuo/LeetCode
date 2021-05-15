package com.szu.leetcode.contest;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/5/15 22:31
 */

import com.szu.leetcode.utils.LeetCodes;

public class L5744_RotateTheBox {
    /*
    * 先把整个盒子旋转一次
    *
    * 然后从上往下遍历每列 ，碰到一个 障碍物 或者触底了，就让上边的所有东西降落下来
    * */
    public static char[][] rotateTheBox(char[][] box) {
        char[][] rotate = rotate(box);
        for (int i = 0; i < rotate[0].length; i++) {
            fall(rotate, i);
        }
        return rotate;
    }

    /*
    * 遍历每一列
    * */
    private static void fall(char[][] box, int col) {
        int start = 0;
        int row = 0;

        while (row <= box.length) {
            if (row == box.length  || box[row][col] == '*' )
                /*
                 * 让从start开始的到 障碍物之间的东西 往下掉
                 *
                 * 或者中间没有任何障碍物， 就从 start 掉到底
                 * */
                start = moveDown(box, start, row, col) + 1;

            row++;
        }
    }

    /*
    * 统计这一段有多少个石头
    * 直接从下往上修改 够石头数量
    * 剩下的就都是空隙了
    * */
    private static int moveDown(char[][] box, int start, int end, int c) {
        int stones = 0;
        for (int r = start; r < end; r++) {
            if (box[r][c] == '#')
                stones++;
        }
        int r = end - 1;
        while (stones > 0) {
            box[r--][c] = '#';
            stones--;
        }
        while (r >= start)
            box[r--][c] = '.';

        return end;
    }

    public static char[][] rotate(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        char[][] res = new char[col][row];
        int c = row - 1;
        for (int r = 0; r < row; r++) {
            rotate(matrix, r, res, c--);
        }
        return res;
    }

    private static void rotate(char[][] matrix, int matrixR, char[][] res, int resCol) {
        int r = 0;
        for (int c = 0; c < matrix[0].length; c++) {
            res[r++][resCol] = matrix[matrixR][c];
        }

    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'#','.','*','.'},
                {'#','#','*','.'}
        };

        rotateTheBox(matrix);
    }
}
