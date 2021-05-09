package com.szu.training03.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 803. 打砖块
有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：

一块砖直接连接到网格的顶部，或者
至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时
给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，
对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。
一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。

返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。

注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
 *
 * @Date 2021/5/9 17:03
 */

public class HitBricks {

    /*
    * What a stupid asshole!
    * */
//    public int[] hitBricksWrong(int[][] grid, int[][] hits) {
//        if (hits == null || hits.length == 0)
//            return new int[]{};
//
//        int res[] = new int[hits.length];
//        for (int i = 0; i < hits.length; i++) {
//            int hitR = hits[i][0];
//            int hitC = hits[i][1];
//            int hitNum = getHitNum(grid, hitR, hitC);
//            res[i] = hitNum == 0 ? 0 : hitNum - 1;
//        }
//        return res;
//    }
//
//    private int getHitNum(int[][] grid, int r, int c) {
//
//        if (r < 0 || r > grid.length || c < 0 || c > grid[0].length || grid[r][c] == 0)
//            return 0;
//
//        grid[r][c] = 0;
//
//        int ret = 1;
//        int down = getHitNum(grid, r + 1, c);
//        int left = getHitNum(grid, r, c - 1);
//        int right = getHitNum(grid, r, c + 1);
//        return ret;
//    }

}
