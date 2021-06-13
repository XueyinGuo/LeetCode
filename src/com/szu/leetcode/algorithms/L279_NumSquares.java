package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 279. 完全平方数
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 * @Date 2021/6/12 23:18
 */

import java.util.Arrays;

public class L279_NumSquares {

    public int numSquares(int n) {
        int limit = (int) Math.sqrt(n);
        int[][] dp = new int[n + 1][limit + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        return getNums(n, limit, dp);
    }

    /*
     * 把 limit 限制在 根号n 以内
     * */
    private int getNums(int rest, int limit, int[][] dp) {
        if (rest == 0) {
            dp[rest][limit] = 0;
            return 0;
        }
        if (rest < 0)
            return Integer.MAX_VALUE;
        if (dp[rest][limit] != Integer.MAX_VALUE)
            return dp[rest][limit];
        // 从 limit 开始枚举
        int nums = Integer.MAX_VALUE;
        for (int c = limit; c > 0; c--) {
            int i = rest - c * c;
            int n = getNums(i, (int) Math.sqrt(i), dp);
            if (n != Integer.MAX_VALUE)
                n++;
            nums = Math.min(n, nums);
        }
        dp[rest][limit] = nums;
        return nums;
    }

    /*
     * 直接一维 DP搞定
     * */
    public int numSquaresDp(int n) {
        int[] dp = new int[n + 1];

        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int limit = (int) Math.sqrt(i);
            int min = Integer.MAX_VALUE;
            for (int l = limit; l > 0; l--) {
                int pow = l * l;
                if (pow == i){ // 如果根号值相乘正好等于 i，那么这个数自己就是平方数，dp[4] = 1; dp[9] = 1
                    min = 1;
                    break;
                }
                // dp[8] = dp[4] + dp[4]
                // dp[19] = dp[16] + dp[3]         = 4
                // dp[19] = dp[9] + dp[9] + dp[1]  = 3
                min = Math.min(dp[pow] + dp[i - pow], min);
            }
            dp[i] = min;
        }

        return dp[n];
    }


    public static void main(String[] args) {
        L279_NumSquares test = new L279_NumSquares();
//        for (int i = 1; i < 10000; i++) {
//            if (test.numSquares(i) != test.numSquaresDp(i))
//                System.out.println("FUCK");
//        }
        System.out.println(test.numSquaresDp(7));
    }
}
