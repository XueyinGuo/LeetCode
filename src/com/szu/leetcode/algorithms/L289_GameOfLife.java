package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  289. 生命游戏
    根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。

    给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

    如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
    如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
    如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
    如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 *
 * @Date 2021/4/20 17:19
 */

import java.util.ArrayList;

public class L289_GameOfLife {

    public void gameOfLife(int[][] board) {
        ArrayList<int[]> list = new ArrayList<>();
        getLiveOrDie(board, list);
        for(int[] arr : list){
            board[arr[0]][arr[1]] = arr[2];
        }
    }

    /*
    * list存放应该死亡或者复活的细胞
    * */
    public void getLiveOrDie(int[][] board, ArrayList<int[]> list ){

        for(int row = 0; row < board.length; row++){

            for(int col = 0; col < board[0].length; col++){
                /* 获取周围八个位置的活着细胞的数量，感染周围八个位置 */
                int lives = getInfo(board, row, col, true);
                /* 记录应该修改的位置和修改为什么值 */
                /* 活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡 */
                if(lives < 2 && board[row][col] == 1)
                    list.add(new int[]{row, col, 0});
                /* 活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡 */
                if(lives > 3  && board[row][col] == 1)
                    list.add(new int[]{row, col, 0});
                /* 死细胞周围正好有三个活细胞，则该位置死细胞复活 */
                if(lives == 3 && board[row][col] == 0)
                    list.add(new int[]{row, col, 1});
            }

        }

    }

    public int getInfo(int[][] board, int row, int col, boolean infect){
        if(row < 0 || row == board.length || col < 0 || col == board[0].length)
            return 0;
        if(infect){
            int lives = 0;
            /* 不在需要向外感染，boolean 设置为 false */
            lives += getInfo(board, row + 1, col - 1, false);
            lives += getInfo(board, row + 1, col, false);
            lives += getInfo(board, row + 1, col + 1, false);
            lives += getInfo(board, row, col + 1, false);
            lives += getInfo(board, row - 1, col + 1, false);
            lives += getInfo(board, row - 1, col, false);
            lives += getInfo(board, row - 1, col - 1, false);
            lives += getInfo(board, row, col - 1, false);
            return lives;
        }
        /* 不需要感染的点 直接返回自己是否存活 */
        return board[row][col];
    }

}
