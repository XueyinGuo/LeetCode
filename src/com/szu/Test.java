package com.szu;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *
 *
 * @Date 2021/5/10 15:16
 */

import com.szu.leetcode.algorithms.L37_SolveSudoku;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Test {
    public static void captureScreen(String fileName, String folder) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        //保存路径
        File screenFile = new File(fileName);
        if (!screenFile.exists()) {
            screenFile.mkdir();
        }
        File f = new File(screenFile, folder);
        ImageIO.write(image, "png", f);
        //自动打开
        if (Desktop.isDesktopSupported()
                && Desktop.getDesktop().isSupported(Desktop.Action.OPEN))
            Desktop.getDesktop().open(f);
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
        new Test().solveSudoku(board);


        try {
            Thread.sleep(1000);
//            captureScreen("e:\\截图", "kafka分区之后数据应该如何分散107.png");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    int row;
    int col;
    boolean done = false;

    public void solveSudoku(char[][] board) {
        row = board.length;
        col = board[0].length;
        if (row != 9 || col != 9)
            return;
        solveSudoku(board, 0, 0);
    }

    private void solveSudoku(char[][] board, int r, int c) {
        r = c == col ? r + 1 : r;
        c = c == col ? 0 : c;

        if (r == row){
            done = true;
            return;
        }

        if (board[r][c] == '.'){
            for (char ch = '1'; ch <= '9'; ch++) {
                if (valid(board, r, c, ch)){
                    board[r][c] = ch;
                    solveSudoku(board, r, c + 1);

                }
            }
            if (!done)
                board[r][c] = '.';
        }else
            solveSudoku(board, r, c + 1);

    }

    private boolean valid(char[][] board, int r, int c, char ch) {

        for (int i = 0; i < col; i++) {
            if (board[r][i] == ch)
                return false;
        }
        for (int i = 0; i < row; i++) {
            if (board[i][c] == ch)
                return false;
        }
        int X = c / 3 * 3;
        int Y = r / 3 * 3;
        for (int i = X; i < X + 3; i++) {
            for (int j = Y; j < Y + 3; j++) {
                if (board[i][j] == ch)
                    return false;
            }
        }
        return true;

    }
}


