package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  695. 岛屿的最大面积
        给定一个包含了一些 0 和 1 的非空二维数组 grid 。

        一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

        找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *
 * @Date 2021/4/10 16:26
 */

public class L695_MaxAreaOfIsland {

    int maxArea = 0;
    int curArea = 0;
    public int maxAreaOfIsland(int[][] grid) {
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[0].length; col++){
                /*
                * 每次开始从遍历开始找到一个可以进入递归函数的位置，就意味着找到一个岛屿
                * 每次开始计算岛屿面积的时候，上一次计算的岛屿的面积先清零
                * */
                if(grid[row][col] == 1){
                    curArea = 0;
                    getArea(grid, row, col);
                }

            }
        }
        return maxArea;
    }

    /*
    * 计算岛屿面积
    * */
    public void getArea( int[][] grid, int row, int col){
        /*
         * 每次进入递归的时候先看是否越界，和是否是海洋，越界或者是海洋，抓取现在的面积 和 最大面积 去比较
         * */
        if(row >= 0 && row < grid.length
                && col >= 0 && col < grid[0].length
                && grid[row][col] == 1){
            /*
             * 感染一块陆地，面积应该 加一
             * 为了以后不再重复计算此位置，直接把这个位置改成海洋
             * */
            curArea++;
            grid[row][col] = 0;
            /*
             * 然后计算这个岛屿周围的四个位置
             * */
            getArea( grid, row - 1 , col);
            getArea( grid, row + 1 , col);
            getArea( grid, row , col + 1);
            getArea( grid, row , col - 1);

        }else{
            maxArea = Math.max(maxArea, curArea);
        }

    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1},
                {1, 0}
        };
        System.out.println(new L695_MaxAreaOfIsland().maxAreaOfIsland(grid));
    }

}
