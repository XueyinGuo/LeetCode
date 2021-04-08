package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/13 21:01
 */

public class L518_CoinChange_II {
    public int change(int amount, int[] coins) {
        return doGetChange(amount,coins , 0);
    }

    public int doGetChange(int rest, int[] coins, int index){
        // 已经换完所有钱，这就是一种方法
        if(rest == 0){
            return 1;
        }
        // 已经没剩余可选的钱了，但是还没还完
        if(index == coins.length){
            return 0;
        }
        int ways = 0;
        // 枚举张数，保证张数*当前选择的面额 < 剩余没换的总金额 ，传到下层决策
        for(int coinNums = 0; coinNums * coins[index] <= rest; coinNums++){
            ways += doGetChange(rest - coinNums * coins[index], coins, index + 1);

        }
        return ways;
    }

    public int dp(int amount, int[] coins) {
        int N = coins.length;
        int dp[][] = new int[N + 1][amount + 1];
        dp[N][0] = 1;
        for (int i = N - 1; i >= 0 ; i--) {
            for (int rest = 0; rest <= amount; rest++) {
                int ways = 0;
                // 枚举张数，保证张数*当前选择的面额 < 剩余没换的总金额 ，传到下层决策
                for(int coinNums = 0; coinNums * coins[i] <= rest; coinNums++){
                    ways += dp[ i + 1][rest - coinNums * coins[i] ];
                }
                dp[i][rest] = ways;
            }
        }
        return dp[0][amount];
    }


    public static void main(String[] args) {
        L518_CoinChange_II test = new L518_CoinChange_II();
        System.out.println(test.change(5, new int[]{1, 2, 5}));
        System.out.println(test.dp(5, new int[]{1, 2, 5}));
    }
}
