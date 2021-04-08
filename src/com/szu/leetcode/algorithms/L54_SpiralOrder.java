package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      54. 螺旋矩阵
 *   给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 *      https://leetcode-cn.com/problems/spiral-matrix/
 *
 * @Date 2021/3/26 21:50
 */

import java.util.ArrayList;
import java.util.List;

public class L54_SpiralOrder {
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3}, {4, 5, 6}, {7, 9, 10 }};
        List<Integer> integers = spiralOrder(matrix);
        for (Integer i: integers
             ) {
            System.out.print(i + " ");
        }
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        int aRow = 0, aCol = 0;
        int bRow = matrix.length-1 , bCol = matrix[0].length-1;
        ArrayList<Integer> result = new ArrayList<>();
        /*
        * 一层一层的往里迭代
        *
        *   第一次
        *     【1】     2       3       4
        *       5     6       7       8
        *       9     10      11      【12】
        *   第二次
        *        1      2       3       4
        *       5    【6】    【7】     8
        *       9     10      11      12
        *
        * */
        while (aRow <= bRow && aCol <= bCol){
            getEdgeRes(aRow, aCol, bRow, bCol, result, matrix);
            aRow++;  aCol++;
            bRow--;  bCol--;
        }
        return result;
    }

    public static void getEdgeRes(int aRow, int aCol, int bRow, int bCol, ArrayList<Integer> result, int[][] matrix) {
        /*
        * 若只有一行，若只有一个数，也能命中这个条件
        * */
        if (aRow == bRow){
            for (int i = aCol; i <= bCol; i++) {
                result.add(matrix[aRow][i]);
            }
        /*
        * 若只有一列
        * */
        }else if ( aCol == bCol ){
            for (int i = aRow; i <= bRow ; i++) {
                result.add(matrix[i][aCol]);
            }
        }

        else{
            /*
            * 不止一行也不只一列
            * */
            int curRow = aRow;
            int curCol = aCol;
            /*
            *     [ 1     2       3 ]     4
            *       5     6       7       8
            *       9     10      11      12
            * */
            while (curCol < bCol)
                result.add(matrix[curRow][curCol++]);
            /*
             *                            +++
             *       1     2       3       4
             *       5     6       7       8
             *                            +++
             *       9     10      11      12
             * */
            while (curRow < bRow)
                result.add(matrix[curRow++][curCol]);
            /*
             *
             *       1     2       3       4
             *       5     6       7       8
             *       9  [ 10      11      12 ]
             * */
            while (curCol > aCol )
                result.add(matrix[curRow][curCol--]);
            /*
             *
             *       1     2       3       4
             *      +++
             *       5     6       7       8
             *       9    10      11      12
             *      +++
             * */
            while (curRow > aRow)
                result.add(matrix[curRow--][curCol]);
        }

    }
}
