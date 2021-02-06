package com.szu.leetcode.dp;

public class ClimbStairs_70 {

    public int climbStairs(int n) {
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
}
