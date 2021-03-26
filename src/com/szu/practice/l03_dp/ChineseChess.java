package com.szu.practice.l03_dp;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *          马从(0,0)出发，跳 K 步，跳到 目标点（x, y）有多少种步数可以走？
 *
 * @Date 2021/3/20 14:30
 */

public class ChineseChess {

    public static void main(String[] args) {
        int x = 6;
        int y = 8;
        int k = 11;

        int dp = dpWays(x, y, k);
        int violent = violentWays(x, y, k);
        System.out.println(violent);
        System.out.println(dp);
    }

    private static int dpWays(int x, int y, int k) {
        /*
        * 因为计算时用到了 k， 所以动态规划数组用的就是 k+1
        * 中国象棋棋盘大小为 10 * 9
        * 相当于 k 个棋盘摞在一起
        * */
        int[][][] dp = new int[10][9][k+1];
        dp[0][0][0] = 1;
        for (int rest = 1; rest <= k ; rest++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][rest] =   getNextLevel(dp,i-2, j+1, rest-1)
                                     + getNextLevel(dp,i-1, j+2, rest-1)
                                     + getNextLevel(dp,i+1, j+2, rest-1)
                                     + getNextLevel(dp,i+2, j+1, rest-1)
                                     + getNextLevel(dp,i+2, j-1, rest-1)
                                     + getNextLevel(dp,i+1, j-2, rest-1)
                                     + getNextLevel(dp,i-1, j-2, rest-1)
                                     + getNextLevel(dp,i-2, j-1, rest-1);
                }
            }
        }
        return dp[x][y][k];
    }

    private static int getNextLevel(int[][][] dp, int x, int y, int k) {
        if ( x < 0 || x > 9 || y < 0 || y > 8 )
            return 0;
        return dp[x][y][k];
    }

    /*
    * 直接递归计算，每次计算周围8个点
    * */
    private static int violentWays(int x, int y, int k) {

        if (k == 0)
            return x == 0 && y == 0 ? 1 : 0;
        /* 中国象棋棋盘大小为 10 * 9，界外的点永远跳不过去，返回 0 */
        if ( x < 0 || x > 9 || y < 0 || y > 8 )
            return 0;

        return violentWays(x-2, y+1, k-1)
               + violentWays(x-1, y+2, k-1)
               + violentWays(x+1, y+2, k-1)
               + violentWays(x+2, y+1, k-1)
               + violentWays(x+2, y-1, k-1)
               + violentWays(x+1, y-2, k-1)
               + violentWays(x-1, y-2, k-1)
               + violentWays(x-2, y-1, k-1);
    }


}
