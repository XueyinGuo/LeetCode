package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/27 20:30
 */

import java.util.ArrayList;

public class L73_SetZeros {

    public void setZeroes(int[][] matrix) {
        int maxRow = matrix.length;
        int maxCol = matrix[0].length;
        ArrayList<Position> zeroPosition = new ArrayList<>();
        /*
        * 遍历一遍矩阵，找到所有初始为 0 的位置
        * */
        for(int row = 0; row < maxRow; row++){

            for(int col = 0; col < maxCol; col++){
                /*
                * 如果当前位置为 0，把当前位置记录下来
                * */
                if(matrix[row][col] == 0)
                    zeroPosition.add( new Position(row, col) );
            }

        }
        // 遍历所有为 0 的位置，然后每次去更改这一行列的值
        for(int i = 0; i < zeroPosition.size(); i++){
            setZeroes(matrix, zeroPosition.get(i).row, zeroPosition.get(i).col);
        }
    }

    public void setZeroes(int[][] matrix, int row, int col) {
        int a = 0;
        while( a < matrix.length )
            matrix[a++][col] = 0;
        int b = 0;
        while( b < matrix[0].length )
            matrix[row][b++] = 0;
    }
}


class Position {

    int row;
    int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }
}
