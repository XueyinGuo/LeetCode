package com.szu.practice.l06_fib;
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


public class ClimbStairs {

    public int climbStairs(int n) {
        if (n == 1 || n == 2)
            return n;
        int[][] res = getMatrix(n - 2);
        return res[0][0] + res[1][0];
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
