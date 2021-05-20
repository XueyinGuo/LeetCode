package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 1269. 停在原地的方案数
有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。

每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。

给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。

由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
 *
 * @Date 2021/5/20 23:45
 */

public class L1269_NumWays {

    public int numWays(int steps, int arrLen) {
        if (arrLen == 1)
            return 1;
        int[][] dp = new int[arrLen][steps + 1];
        return getWays(steps, arrLen, 0, dp);
    }

    private int getWays(int restSteps, int arrLen, int curLocation, int[][] dp) {

        if (restSteps == 0 && curLocation == 0)
            return 1;
        if (restSteps == 0)
            return 0;

        if (dp[curLocation][restSteps] != 0)
            return dp[curLocation][restSteps];

        int ways = 0;
        if (curLocation == 0) {

            ways += getWays(restSteps - 1, arrLen, curLocation, dp);
            ways += getWays(restSteps - 1, arrLen, curLocation + 1, dp);
        } else if (curLocation == arrLen - 1) {
            ways += getWays(restSteps - 1, arrLen, curLocation, dp);
            ways += getWays(restSteps - 1, arrLen, curLocation - 1, dp);
        } else {
            ways += getWays(restSteps - 1, arrLen, curLocation, dp);
            ways += getWays(restSteps - 1, arrLen, curLocation - 1, dp);
            ways += getWays(restSteps - 1, arrLen, curLocation + 1, dp);
        }
        dp[curLocation][restSteps] = ways;
        return ways;
    }

}
