package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      70. 爬楼梯
        假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

        每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @Date 2021/3/20 17:33
 */
public class L70_ClimbStairs {

    public int climbStairsDP(int n) {
        if( n <= 0){
            return 1;
        }
        int dp[] = new int[n + 1];
        return doClimbStairs(n, dp);
    }

    public int doClimbStairs(int n, int[] dp){
        if(dp[n] != 0 ){
            return dp[n];
        }
        if(n <= 0){
            return 1;
        }
        // 迈一阶楼梯，n - 1
        int oneStairs = doClimbStairs(n - 1, dp);
        // 如果当前不是最后一节楼梯，才能往上迈两步，否则迈两步的方案数就是0
        int twoStairs = 0;
        if(n > 1){
            twoStairs = doClimbStairs(n - 2, dp);
        }
        // 把剩n节楼梯的方法数放入傻缓存
        dp[n] = oneStairs + twoStairs;
        return dp[n];
    }

    /* 快速幂思想 */
    /*                                                         { | 1  1 | } ^ (n-2) */
    /* 最普通的斐波那契数列递推式 | F(n) F(n-1) | = | F(2) F(1) | * { | 1  0 | }         */
    /* 然后在基于快速幂思想实现矩阵的 （n-2）次方运算 */
    public int climbStairs(int n) {
        if (n == 1 || n == 2)
            return n;
        // 去算矩阵的 n-2 次方返回结果
        int[][] res = getMatrix(n - 2);
        // 然后返回 | F(2) F(1) | * 矩阵第一列 即为 F(n)
        //            |   |
        //       此时  2   1，因为在只有一节楼梯的时候只有 1 中方法，在有两节楼梯的时候有两种方法：【迈两次1个】+ 【迈一次2个】
        return 2 * res[0][0] + res[1][0];
    }

    private int[][] getMatrix(int n) {

        int base[][] = {
                {1,1},
                {1,0}
        };
        int res[][] = new int[base.length][base.length];
        for (int i = 0; i < base.length; i++) {
            res[i][i] = 1;
        }
        int[][] tem = base;
        /* 矩阵的快速幂 */
        while(n != 0){
            if ((n & 1) != 0){
                res = matrixMulti(res, tem);
            }
            tem = matrixMulti(tem, tem);
            n = n >> 1;

        }
        return res;
    }

    private int[][] matrixMulti(int[][] m1, int[][] m2) {

        int res[][] = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2[0].length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }
}
