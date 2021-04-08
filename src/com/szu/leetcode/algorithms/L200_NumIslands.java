package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      200. 岛屿数量
 *      给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 *      岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 *      此外，你可以假设该网格的四条边均被水包围。
 *      https://leetcode-cn.com/problems/number-of-islands/
 * @Date 2021/3/27 18:53
 */

public class L200_NumIslands {

    public static void main(String[] args) {
        char[][] grid = {
                          {'1','1','0','0','0'},
                          {'1','1','0','0','0'},
                          {'0','0','1','0','0'},
                          {'0','0','0','1','1'}
                        };
        int i = new L200_NumIslands().numIslands(grid);
        System.out.println(i);
    }
    
    public int numIslands(char[][] grid) {
        int row = grid.length - 1;
        int col = grid[0].length - 1;
        int res = 0;
        /*
        * 主方法调用了几次感染函数，就是有几个岛屿，
        * 因为岛屿的所有联通区域都在一次调用过程中改成了其他值，不会再从之后的遍历过程中访问得到
        * */
        for(int i = 0; i <= row; i++){
            for(int j = 0; j<= col; j++){

                if(grid[i][j] == '1'){
                    infect(i, j, grid);
                    res++;
                }
            }
        }
        return res;
    }


    /*
     * 定义感染函数，每次调用感染函数都会判断当前位置是否越界，以及是否是一个没动过的岛屿
     * 如果是岛屿，则把岛屿改成 2
     * 然后判断这块地方的周围是不是陆地，是陆地继续改成 2
     * 一个联通区域会在主方法调用一次之后，联通区域都会变成 2
     * */
    public void infect(int row, int col, char[][] grid) {
        if(row < 0 || row >= grid.length ||
                col < 0 || col >= grid[0].length ||
                grid[row][col] != '1')
            return;

        grid[row][col] = '2';
        infect(row-1, col, grid);
        infect(row+1, col, grid);
        infect(row, col+1, grid);
        infect(row, col-1, grid);
    }

}
