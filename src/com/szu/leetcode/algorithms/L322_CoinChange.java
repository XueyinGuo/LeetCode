package com.szu.leetcode.algorithms;

public class L322_CoinChange {

    int min = Integer.MAX_VALUE;

    /*
    * TODO 运行内存超出限制
    * */
    public int coinChangeUseStupidMemory(int[] coins, int amount) {
        // 每次换钱的傻缓存大小设置为 amount
        int dp[][] = new int[amount + 1][amount + 1];
        // 把每个傻缓存设置为 -1， 表示没计算过
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = -1;
            }
        }
        min = getMinCoin(coins, amount, 0, dp);
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }
    /*
    * rest:  还剩多少钱没换
    * count: 已经换到了多少张
    * */
    public int getMinCoin(int[] coins, int rest, int count, int[][] dp) {
        // 钱换多了，人家银行肯定不愿意，给你一个 -1
        if (rest < 0) {
            return -1;
        }
        // 正好全部换完， 看看自己手里一共多少张
        if (rest == 0) {
            return count;
        }
        // 如果傻缓存中的值被修改过了，就返回已经设置好的值
        if (dp[rest][count] != -1) {
            return dp[rest][count];
        }
        for (int i = 0; i < coins.length; i++) {
            // 获取到当前余额使用当前金额硬币换到的最小钱数
            // rest - coins[i] ，留给下一层判断的时候需要减去刚刚换到的金额数
            // 而且要告诉下一层 现在已经换到的枚数 + 你刚刚换到的 1 枚
            int result = getMinCoin(coins, rest - coins[i], count + 1, dp);
            if (result != -1) {
                min = Math.min(min, result);
            }
        }
        if (min != Integer.MAX_VALUE) {
            dp[rest][count] = min;
        }
        return min;
    }


    /*
    * TODO coinChange 动态规划
    * */
    public int coinChange(int[] coins, int amount) {
        return 0;
    }

    public static void main(String[] args) {
        L322_CoinChange coinChange322 = new L322_CoinChange();
        System.out.println(coinChange322.coinChangeUseStupidMemory(new int[]{186,419,83,408}, 6249));
    }
}
