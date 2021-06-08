package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 305. 岛屿数量 II
假设你设计一个游戏，用一个 m 行 n 列的 2D 网格来存储你的游戏地图。

起始的时候，每个格子的地形都被默认标记为「水」。我们可以通过使用 addLand 进行操作，将位置 (row, col) 的「水」变成「陆地」。

你将会被给定一个列表，来记录所有需要被操作的位置，然后你需要返回计算出来 每次 addLand 操作后岛屿的数量。

注意：一个岛的定义是被「水」包围的「陆地」，通过水平方向或者垂直方向上相邻的陆地连接而成。你可以假设地图网格的四边均被无边无际的「水」所包围。

请仔细阅读下方示例与解析，更加深入了解岛屿的判定。
 *
 * @Date 2021/6/8 14:41
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L305_NumOfIslandII {

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] grid = new int[m][n];
        UnionFind unionFind = new UnionFind(grid);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            int ir = positions[i][0];
            int ic = positions[i][1];
            if (grid[ir][ic] == 0) {
                grid[ir][ic] = 1;
                /* 先把土地建出来 */
                unionFind.buildLand(ir, ic);
                /*
                * 然后向四个方向合并
                * */
                unionFind.union(ir, ic, ir - 1, ic);
                unionFind.union(ir, ic, ir + 1, ic);
                unionFind.union(ir, ic, ir, ic - 1);
                unionFind.union(ir, ic, ir, ic + 1);
            }
            res.add(unionFind.islandNum);
        }
        return res;
    }


    class UnionFind {
        int[] fatherMap;
        int[] sizeMap;
        int[][] grid;
        int islandNum;
        int row;
        int col;

        public UnionFind(int[][] grid) {
            this.row = grid.length;
            this.col = grid[0].length;
            this.grid = grid;
            this.fatherMap = new int[row * col];
            this.sizeMap = new int[row * col];
            this.islandNum = 0;
        }

        public void union(int r1, int c1, int r2, int c2) {
            if (isValid(r1, c1, r2, c2)) {
                int father1 = findFather(r1,c1);
                int father2 = findFather(r2, c2);
                if (father1 != father2){
                    int size1 = sizeMap[father1];
                    int size2 = sizeMap[father2];

                    int big = size1 > size2 ? father1 : father2;
                    int small = big == father1 ? father2 : father1;

                    fatherMap[small] = big;
                    sizeMap[big] = size1 + size2;
                    islandNum--;
                }
            }
        }

        private int findFather(int r, int c) {
            int index = getIndex(r, c);
            int father = fatherMap[index];
            List<Integer> list = new LinkedList<>();
            while (father != index){
                list.add(father);
                index = father;
                father = fatherMap[index];
            }
            for (Integer i : list) {
                fatherMap[i] = father;
            }
            return father;
        }

        /* 数组下标换算 */
        private int getIndex(int r, int c) {
            return r * col + c;
        }

        public boolean isValid(int r1, int c1, int r2, int c2) {
            return r1 >= 0 && c1 >= 0 && r1 < row && c1 < col &&
                    r2 >= 0 && c2 >= 0 && r2 < row && c2 < col &&
                    grid[r2][c2] == 1;
        }

        public void buildLand(int r, int c) {
            int index = getIndex(r, c);
            fatherMap[index] = index;
            sizeMap[index] = 1;
            islandNum++;
        }
    }
}
