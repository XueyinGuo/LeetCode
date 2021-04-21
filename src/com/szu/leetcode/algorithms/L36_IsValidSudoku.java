package com.szu.leetcode.algorithms;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/21 9:27
 */

public class L36_IsValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[0].length; c++){
                if(board[r][c] != '.' && !isValid(board, r, c))
                    return false;
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int r, int c){
        for(int rr = 0; rr < board.length ; rr++){
            if(rr == r)
                continue;
            if(board[rr][c] == board[r][c])
                return false;
        }
        for(int cc = 0; cc < board.length; cc++){
            if(cc == c)
                continue;
            if(board[r][cc] == board[r][c])
                return false;
        }
        int x = r / 3 * 3;
        int y = c / 3 * 3;
        for(int xx = x; xx < x+3; xx++){
            for(int yy = y; yy < y+3; yy++){
                if(xx != r && yy != c && board[xx][yy] == board[r][c])
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
        new L36_IsValidSudoku().isValidSudoku(board);
    }
}
