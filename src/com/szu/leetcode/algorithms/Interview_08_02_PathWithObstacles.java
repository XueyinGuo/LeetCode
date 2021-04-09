package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  面试题 08.02. 迷路的机器人
 * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。
 * 设计一种算法，寻找机器人从左上角移动到右下角的路径。
 *
 * @Date 2021/4/10 0:49
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Interview_08_02_PathWithObstacles {
    boolean arrived = false;
    LinkedList<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        boolean[][] visited = new boolean[row][col];
        pathWithObstacles(obstacleGrid, visited, 0, 0);
        return res;
    }

    public void pathWithObstacles(int[][] obstacleGrid, boolean[][] visited, int row, int col){
        /*
        * 回溯剪枝，非常迅速
        * */
        if(arrived) return;

        /*
         * 到达终点  addResult 采用头插法逐步插入走过的路径
         * 设置已经到达终点，然后方便之后剪枝操作
         * */
        if(row == obstacleGrid.length -1  && col == obstacleGrid[0].length - 1 && obstacleGrid[row][col] != 1){
            addResult(row, col);
            arrived = true;
            return;
        }

        if(row >= 0 && row < obstacleGrid.length &&
                col >= 0 && col < obstacleGrid[0].length &&
                obstacleGrid[row][col] != 1 && !visited[row][col]){

            visited[row][col] = true;
            if(!arrived)
                pathWithObstacles(obstacleGrid, visited, row + 1, col);
            if(!arrived)
                pathWithObstacles(obstacleGrid, visited, row, col + 1);
            /*
            * 只能向下或者向右移动
            * */
//            if(!arrived)
//                pathWithObstacles(obstacleGrid, visited, row - 1, col);
//            if(!arrived)
//                pathWithObstacles(obstacleGrid, visited, row, col - 1);
            if (arrived)
                addResult(row, col);

        }

    }

    public void addResult(int row, int col){
        List<Integer> list = new ArrayList<>();
        list.add(row);
        list.add(col);
        res.addFirst(list);
    }

    public static void main(String[] args) {
        int ob[][] = {
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {1,0},
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {1,0},
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {0,1},
                {0,0},
                {0,0},
                {1,0},
                {0,0},
                {0,0},
                {0,1},
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {0,1},
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {1,0},
                {0,0},
                {0,0},
                {0,0},
                {0,0}
        };

//        int[][] ob = {
//                {0 ,1}
//        };
        List<List<Integer>> lists = new Interview_08_02_PathWithObstacles().pathWithObstacles(ob);
        System.out.println();
    }
}
