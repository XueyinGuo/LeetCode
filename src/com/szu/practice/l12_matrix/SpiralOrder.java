package com.szu.practice.l12_matrix;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      54. 螺旋矩阵
 *   给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * @Date 2021/3/26 21:50
 */

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {
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
        while (aRow <= bRow && aCol <= bCol){
            getEdgeRes(aRow, aCol, bRow, bCol, result, matrix);
            aRow++;  aCol++;
            bRow--;  bCol--;
        }
        return result;
    }

    public static void getEdgeRes(int aRow, int aCol, int bRow, int bCol, ArrayList<Integer> result, int[][] matrix) {

        if (aRow == bRow){
            for (int i = aCol; i <= bCol; i++) {
                result.add(matrix[aRow][i]);
            }
        }else if ( aCol == bCol ){
            for (int i = aRow; i <= bRow ; i++) {
                result.add(matrix[i][aCol]);
            }
        }

        else{
            int curRow = aRow;
            int curCol = aCol;
            while (curCol < bCol)
                result.add(matrix[curRow][curCol++]);

            while (curRow < bRow)
                result.add(matrix[curRow++][curCol]);

            while (curCol > aCol )
                result.add(matrix[curRow][curCol--]);

            while (curRow > aRow)
                result.add(matrix[curRow--][curCol]);
        }

    }
}
