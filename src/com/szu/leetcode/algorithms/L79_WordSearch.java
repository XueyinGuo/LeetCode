package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 79. 单词搜索
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * @Date 2021/4/25 16:05
 */

public class L79_WordSearch {

    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0)
            return true;
        char[] str = word.toCharArray();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                /* 等于的时候才跳进递归 */
                if (board[r][c] == str[0] && exist(board, r, c, str, 1))
                    return true;

            }
        }
        return false;
    }

    private boolean exist(char[][] board, int r, int c, char[] str, int index) {
        if (index == str.length)
            return true;

        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;
        /* 为了不走回头路 */
        char cur = board[r][c];
        board[r][c] = 0;

        if (r - 1 >= 0 && board[r - 1][c] != 0 && board[r - 1][c] == str[index])
            up = exist(board, r - 1, c, str, index + 1);

        if (r + 1 < board.length && board[r + 1][c] != 0 && board[r + 1][c] == str[index])
            down = exist(board, r + 1, c, str, index + 1);

        if (c - 1 >= 0 && board[r][c - 1] != 0 && board[r][c - 1] == str[index])
            left = exist(board, r, c - 1, str, index + 1);

        if (c + 1 < board[0].length && board[r][c + 1] != 0 && board[r][c + 1] == str[index])
            right = exist(board, r, c + 1, str, index + 1);
        /*
         * 最后再恢复成原样子
         * */
        board[r][c] = cur;
        return up || down || left || right;
    }



    public static void main(String[] args) {
//        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        char[][] board = {{'A','A'}};
        String word = "AA";
        L79_WordSearch test = new L79_WordSearch();
        boolean exist = test.exist(board, word);
        System.out.println(exist);
    }

}
