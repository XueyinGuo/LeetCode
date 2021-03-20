package com.szu.practice.fib;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      斐波那契数列的四种解法
 *                                                                       { | 1  1 | } ^ (n-2)
                 最普通的斐波那契数列递推式 | F(n) F(n-1) | = | F(2) F(1) | * { | 1  0 | }

 * @Date 2021/3/20 15:07
 */

import com.szu.leetcode.utils.LeetCodes;

public class Fibonacci {

    public static void main(String[] args) {
        int num = 20;
        int fibNormal = getFibNormal(num);
        int fibStupidMemory = getFibStupidMemory(num);
        int fibDp = getFibDP(num);
        /*                                                         { | 1  1 | } ^ (n-2) */
        /* 最普通的斐波那契数列递推式 | F(n) F(n-1) | = | F(2) F(1) | * { | 1  0 | }         */
        /* 然后在基于快速幂思想实现矩阵的 （n-2）次方运算 */
        int fibFastExponentiation = getFibFastExponentiation(num);
        System.out.println(fibNormal);
        System.out.println(fibFastExponentiation);
        System.out.println(fibDp);
        System.out.println(fibStupidMemory);
    }

    private static int getFibFastExponentiation(int num) {
        if (num < 1) {
            return 0;
        }
        if (num == 1 || num == 2) {
            return 1;
        }
        int[][] base = {
                {1,1},
                {1,0}
        };
        int[][] res = LeetCodes.matrixPower(base, num-2);
        return res[0][0] + res[1][0];
    }




    private static int getFibDP(int num) {
        int[] dp = new int[num+1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= num ; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[num];
    }

    private static int getFibStupidMemory(int num) {
        int[] dp = new int[num+1];
        getFibStupidMemory(num, dp);
        return dp[num];
    }

    private static int getFibStupidMemory(int num, int[] dp) {
        if(num == 1){
            dp[num] = 1;
            return 1;
        }
        if(num == 2){
            dp[num] = 1;
            return 1;
        }
        if (dp[num] != 0) return dp[num];
        int cur = getFibNormal(num-1) + getFibNormal(num - 2);
        dp[num] = cur;
        return cur;
    }

    private static int getFibNormal(int num) {
        if(num == 1) return 1;
        if (num == 2) return 1;
        return getFibNormal(num-1) + getFibNormal(num - 2);
    }
}
