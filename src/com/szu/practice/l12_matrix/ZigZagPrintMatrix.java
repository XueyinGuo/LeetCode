package com.szu.practice.l12_matrix;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *              打印
 *          1   →   2       3   →   4
 *              ↙       ↗        ↙
 *          5       6       7       8
 *              ↗       ↙       ↗   ↓
 *          9       10   →  11      12
 *
 * @Date 2021/3/26 20:45
 */

public class ZigZagPrintMatrix {

    public static void main(String[] args) {

        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }

    private static void printMatrixZigZag(int[][] matrix) {
        int aRow = 0, aCol = 0;
        int bRow = 0, bCol = 0;
        boolean reverse = false;
        int cols = matrix[0].length - 1;
        int rows = matrix.length - 1;
        while (aRow <= rows && aCol <= cols){

            /*
            *           a → → → a → → → a → → → → a →
            *       b  1   →   2       3   →   4    ↓
            *       ↓       ↙       ↗        ↙      ↓
            *       ↓   5       6       7       8   a
            *       b       ↗       ↙       ↗   ↓   ↓
            *       ↓   9       10   →  11      12  ↓
            *       ↓ b → → → b → → → b → → → → → → b a 重合 结束
            *   每次打印都打印一跳斜线
            *
            * */

            printMatrix(aRow, aCol, bRow, bCol, reverse, matrix);
            System.out.println();
            reverse = !reverse;
            aRow = aCol == cols ? aRow + 1 : aRow;
            aCol = aCol == cols ? aCol : aCol + 1;

            bCol = bRow == rows ? bCol + 1 : bCol;
            bRow = bRow == rows ? bRow : bRow + 1;
        }
    }

    private static void printMatrix(int aRow, int aCol, int bRow, int bCol, boolean reverse, int[][] matrix) {
        if (reverse){

            while (true){
                System.out.print(matrix[aRow++][aCol--] + " ");
                if (aRow == bRow + 1)
                    break;
            }

        }else {

            while (true){
                System.out.print(matrix[bRow--][bCol++]+" ");
                if (bCol == aCol + 1)
                    break;
            }


        }
    }

}
