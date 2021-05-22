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

import java.util.Arrays;

public class L1269_NumWays {

    public int numWays(int steps, int arrLen) {
        if (arrLen == 1)
            return 1;
        int[][] dp = new int[arrLen][steps + 1];
        for (int i = 0; i < arrLen; i++) {
            Arrays.fill(dp[i], -1);

        }
        return getWays(steps, arrLen, 0, dp);
    }

    private int getWays(int restSteps, int arrLen, int curLocation, int[][] dp) {

        if (restSteps == 0 && curLocation == 0)
            return 1;
        if (restSteps == 0)
            return 0;

        if (dp[curLocation][restSteps] != -1)
            return dp[curLocation][restSteps];

        int ways = 0;
        if (curLocation == 0) {

            ways += getWays(restSteps - 1, arrLen, curLocation, dp);
            ways = protectionFromOverFlow(ways);
            ways += getWays(restSteps - 1, arrLen, curLocation + 1, dp);
            ways = protectionFromOverFlow(ways);
        } else if (curLocation == arrLen - 1) {
            ways += getWays(restSteps - 1, arrLen, curLocation, dp);
            ways = protectionFromOverFlow(ways);
            ways += getWays(restSteps - 1, arrLen, curLocation - 1, dp);
            ways = protectionFromOverFlow(ways);
        } else {
            ways += getWays(restSteps - 1, arrLen, curLocation, dp);
            ways = protectionFromOverFlow(ways);
            ways += getWays(restSteps - 1, arrLen, curLocation - 1, dp);
            ways = protectionFromOverFlow(ways);
            ways += getWays(restSteps - 1, arrLen, curLocation + 1, dp);
            ways = protectionFromOverFlow(ways);
        }
        dp[curLocation][restSteps] = ways;
        return ways;
    }

    private int protectionFromOverFlow(int ways) {
        if (ways > 1000000007)
            ways %= 1000000007;
        return ways;
    }


    public static void main(String[] args) {
        int steps = 5;
        int len = 5;
        L1269_NumWays test = new L1269_NumWays();
        System.out.println(test.numWaysDp(steps, len));
        System.out.println(test.numWays(steps, len));
        System.out.println(test.numWaysDpAwesome(steps, len));
    }


    public int numWaysDp(int steps, int arrLen) {
        if (arrLen == 1)
            return 1;
        int[] dp1 = new int[arrLen];
        int[] dp2 = new int[arrLen];
        dp1[0] = 1;

        for (int restSteps = 1; restSteps <= steps; restSteps++) {
            for (int curLocation = 0; curLocation < arrLen; curLocation++) {
                int ways = 0;
                if (curLocation == 0) {

                    ways += dp1[curLocation];
                    ways = protectionFromOverFlow(ways);
                    ways += dp1[curLocation + 1];
                    ways = protectionFromOverFlow(ways);
                } else if (curLocation == arrLen - 1) {
                    ways += dp1[curLocation];
                    ways = protectionFromOverFlow(ways);
                    ways += dp1[curLocation - 1];
                    ways = protectionFromOverFlow(ways);
                } else {
                    ways += dp1[curLocation];
                    ways = protectionFromOverFlow(ways);
                    ways += dp1[curLocation - 1];
                    ways = protectionFromOverFlow(ways);
                    ways += dp1[curLocation + 1];
                    ways = protectionFromOverFlow(ways);
                }
                dp2[curLocation] = ways;
            }
            int[] tem = dp1;
            dp1 = dp2;
            dp2 = tem;
        }

        return dp1[0];
    }

    /*
    * 即使可以移动的范围很大， 最多也就可以移动到 (step / 2) + 1 的位置，
    * 其他位置都没答案啊
    * */
    public int numWaysDpAwesome(int steps, int arrLen) {
        if (arrLen == 1)
            return 1;
        int len = Math.min(steps / 2 + 1, arrLen);
        int[] dp1 = new int[len + 1];
        int[] dp2 = new int[len + 1];
        int mod = 1000000007;
        dp1[0] = 1;

        for (int restSteps = 1; restSteps <= steps; restSteps++) {
            for (int curLocation = 0; curLocation < len; curLocation++) {
                int ways = 0;
                if (curLocation == 0) {

                    ways = (dp1[curLocation] + dp1[curLocation + 1]) % mod;
                } else if (curLocation == arrLen - 1) {
                    ways = (dp1[curLocation] + dp1[curLocation - 1]) % mod;
                } else {
                    ways = ((dp1[curLocation] + dp1[curLocation - 1]) % mod) + dp1[curLocation + 1] % mod;

                }

                dp2[curLocation] = ways % mod;
            }
            int[] tem = dp1;
            dp1 = dp2;
            dp2 = tem;
        }

        return dp1[0];
    }


    public int numWaysRightMod(int steps, int arrLen) {
        int maxCol = Math.min(steps / 2 + 1, arrLen - 1);
        int dp[][] = new int[steps + 1][maxCol + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= maxCol; j++) {
                //位于最左边时，只能从上一步的当前位置或是右边一位转移过来
                if (j == 0) {
                    dp[i][j] = (dp[i - 1][j] + dp[i - 1][j + 1]) % 1000000007;
                    //位于最右边时，只能从上一步的当前位置或是左边一位转移过来
                } else if (j == maxCol) {
                    dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % 1000000007;
                } else {
                    dp[i][j] = ((dp[i - 1][j] + dp[i - 1][j - 1]) % 1000000007 + dp[i - 1][j + 1]) % 1000000007;
                }
            }
        }
        return dp[steps][0];
    }

}
