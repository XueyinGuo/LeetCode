package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  37. 解数独
 *  编写一个程序，通过填充空格来解决数独问题。
 *
 *  一个数独的解法需遵循如下规则：
 *
 *  数字 1-9 在每一行只能出现一次。
 *  数字 1-9 在每一列只能出现一次。
 *  数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *  空白格用 '.' 表示。
 *
 * @Date 2021/4/7 22:54
 */

public class L37_SolveSudoku {

    // 标识是否已经解析完毕，如果不设置这个变量的话，即使解析已经结束，数独仍然会被恢复成原样子
    boolean notSolved = true;

    private void solveSudoku(char[][] board) {
        solveSudoku(board, 0 , -1);
    }
    /*
    * 正式开始解析数独
    * */
    private void solveSudoku(char[][] board, int row, int col) {
        // 一行一行的填，如果来到了一行的最后一个位置，则行应该下移，且 列回到第一个位置
        row = ( col == board[0].length - 1 ? row + 1 : row );
        col = ( col == board[0].length - 1 ? 0 : col + 1);
        // 当最后一行解析完毕，行会越界，一旦行越界则整个数独都是合法的了，表示已经解析完毕，并且返回
        if (row == board.length){
            notSolved = false;
            return;
        }
        /*
        * 当每个位置为 . 的时候，才去尝试每个填法是否合法
        * */
        if (board[row][col] == '.'){
            /*
             * 穷举每个应该填的字符，并且判断这个字符是合法的，则填入这个字符，进行下一个位置的判断
             * */
            for( char blank = '1'; blank <= '9'; blank++){
                if (isValid(board, row, col, blank)){
                    board[row][col] = blank;
                    solveSudoku(board, row, col);
                }
            }
            /*
             *  如果当前位置穷举完所有的可能都不合法，而且现在整个数独是没有解析完成的情况，则是上一个位置填错了
             * 恢复此位置的 . ，然后回溯到上个位置继续穷举上个位置剩下的可能性
             * */
            if (notSolved)
                board[row][col] = '.';
        }
        else {
            solveSudoku(board, row, col);
        }
    }

    private boolean isValid(char[][] board, int row, int col, char blank) {
        /*
         * 这一【列】有没有重复值
         * */
        for (int i = 0; i < board[0].length; i++) {
            if(blank == board[row][i])
                return false;
        }
        /*
         * 这一【行】有没有重复值
         * */
        for (int i = 0; i < board.length; i++) {

            if (blank == board[i][col])
                return false;
        }
        /*
         * 这个九宫格内有无重复值
         * */
        int searchX = row / 3 * 3;
        int searchY = col / 3 * 3;
        for (int r = searchX; r < searchX + 3; r++) {
            for (int c = searchY; c < searchY + 3; c++) {
                if (board[r][c] == blank)
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
                {'4','.','.','8','.','3','.','.','.'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        new L37_SolveSudoku().solveSudoku(board);
        for (int i = 1; i <= board.length; i++) {
            for (int j = 1; j <= board.length; j++) {
                System.out.print(board[i-1][j-1] - '0' + "\t");
                if (j % 3 == 0)
                    System.out.print("\t");
            }
            System.out.println();
            if (i % 3 == 0)
                System.out.println();
        }
        System.out.println();
    }
}
