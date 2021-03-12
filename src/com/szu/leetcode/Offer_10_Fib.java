package com.szu.leetcode;
/*
* 剑指 Offer 10- I. 斐波那契数列
*
*
* */
public class Offer_10_Fib {

    public int fib(int n) {
        // 因为存在重复计算，所以创建一个傻缓存，存储计算过的值
        // 缓存使用方式就是 数组下标用来当做key 数组值用来做 value
        int dp[] = new int[n+1];
        return doCount(n, dp);
    }

    public int doCount(int n, int[] dp){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(dp[n] != 0){
            return dp[n];
        }
        int pre = doCount(n - 1, dp);
        // 每次算完就把当前的值放到缓存中
        dp[n - 1] = pre;
        int post = doCount(n - 2, dp);
        dp[n - 2] = post;
        return (pre + post )  % 1000000007 ;
    }
}
