package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/7 22:54
 */

public class L37_SolveSudoku {

    boolean flag = false;

    public void solveSudoku(char[][] board) {
        solveSudoku(board, 0, 0);

    }

    public void solveSudoku(char[][] board, int row, int col) {

        if(row == board.length) {
            flag = true;
            return;
        }

        if(board[row][col] == '.'){
            for(char blank = '1'; blank <= '9'; blank++){
                board[row][col] = blank;
                if(valid(board, row, col)){
                    if(col + 1 == board[0].length){
                        solveSudoku(board, row + 1, 0);
                        if (flag) return;
                    }
                    else{
                        solveSudoku(board, row, col + 1);
                        if (flag) return;
                    }
                }
            }
        }else{
            if(col + 1 == board[0].length){
                solveSudoku(board, row + 1, 0);
                if (flag) return;
            }

            else{

                solveSudoku(board, row, col + 1);
                if (flag) return;
            }
        }
        board[row][col] = '.';
    }

    public boolean valid(char[][] board, int row, int col){

        int i = 0;
        while(i < board[0].length){
            if(i == col) i++;
            if(i == board[0].length) break;
            if(board[row][i] == board[row][col])
                return false;
            i++;
        }
        i = 0;
        while(i < board.length){
            if(i == row) i++;
            if(i == board.length) break;
            if(board[i][col] == board[row][col])
                return false;
            i++;
        }

        int squareX = (row / 3) * 3;
        int squareY = (col / 3) * 3;
        for(int x = squareX; x < squareX + 3; x++){
            for(int y = squareY; y < squareY + 3; y++){

                if(x == row && y == col) continue;
                if(board[x][y] == board[row][col])
                    return false;

            }
        }

        return true;
    }


    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        new L37_SolveSudoku().solveSudoku(board);
        System.out.println();
    }
}
