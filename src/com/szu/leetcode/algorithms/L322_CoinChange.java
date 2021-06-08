package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 322. 零钱兑换
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

你可以认为每种硬币的数量是无限的。
 *
 * @Date 2021/2/11 18:25
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;
import java.util.Random;

public class L322_CoinChange {
    /*
     * ===============================================
     * ===============================================
     * 背包解法暴力解法
     * ===============================================
     * ===============================================
     * */
    public int coinChangeBag(int[] coins, int amount) {
        int violence = violence(coins, 0, amount);
        return violence == Integer.MAX_VALUE ? -1 : violence;
    }

    private int violence(int[] coins, int index, int rest) {
        if (rest == 0)
            return 0;
        if (rest < 0 || index == coins.length)
            return Integer.MAX_VALUE;
        int useCur = violence(coins, index, rest - coins[index]);
        int notUseCur = violence(coins, index + 1, rest);
        if (useCur != Integer.MAX_VALUE)
            useCur++;
        return Math.min(notUseCur, useCur);
    }


    /*
     * ===============================================
     * ===============================================
     * 背包解法DP解法
     * ===============================================
     * ===============================================
     * */
    public int coinChangeBagDP(int[] coins, int amount) {
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
//        for (int r = 0; r <= len; r++) {
//            dp[r][0] = 0;
//        }
        for (int c = 1; c <= amount; c++) {
            dp[len][c] = Integer.MAX_VALUE;
        }
        for (int r = len - 1; r >= 0; r--) {
            for (int c = 0; c <= amount; c++) {
                int useCur = Integer.MAX_VALUE;
                if (c - coins[r] >= 0)
                    useCur =  dp[r][c - coins[r]];
                int notUseCur = dp[r + 1][c];
                if (useCur != Integer.MAX_VALUE)
                    useCur++;
                dp[r][c] = Math.min(useCur, notUseCur);
            }
        }
        return dp[0][amount];
    }


    /*
     * 贪心超时
     *
     * 贪心思路：
     * 硬币数组排序之后，从最大的硬币开始尝试
     * 拿最大的硬币拿到不能拿位置
     * */
    int min = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        min = Integer.MAX_VALUE;
        Arrays.sort(coins);
        coinChange(coins, amount, coins.length - 1, 0);
        if (min == Integer.MAX_VALUE)
            return -1;
        return min;
    }

    public void coinChange(int[] coins, int amount, int index, int curNum) {

        if (amount == 0) {
            min = Math.min(curNum, min);
        }
        if (index == -1 || curNum > min)
            return;
        int c = amount / coins[index];

        for (int i = c; i >= 0; i--) {
            int rest = amount - coins[index] * i;
            coinChange(coins, rest, index - 1, curNum + i);
        }
    }


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
    public int coinChangeDp(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (coins == null || coins.length == 0)
            return -1;
        Arrays.sort(coins);
        int rows = coins.length;
        int[][] dp = new int[rows][amount + 1];

        int min = Integer.MAX_VALUE;

        for (int r = 0; r < rows; r++) {
            for (int c = 1; c <= amount; c++) {
                dp[r][c] = Integer.MAX_VALUE;

            }
        }
        for (int c = 1; c <= amount; c++) {
            if (c % coins[0] == 0)
                dp[0][c] = c / coins[0];
        }
        min = dp[0][amount];
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c <= amount; c++) {


                if (c % coins[r] == 0) {
                    dp[r][c] = c / coins[r];
                    continue;
                }


                int useCurCoin = Integer.MAX_VALUE;
                int maxCur = c / coins[r];

                for (int curNum = maxCur; curNum > 0; curNum--) {
                    int done = curNum * coins[r];
                    if (dp[r - 1][c - done] != Integer.MAX_VALUE) {
                        useCurCoin = dp[r][done] + dp[r - 1][c - done];
                        break;
                    }

                }

                int notUseCur = dp[r - 1][c];

                dp[r][c] = Math.min(dp[r - 1][c], Math.min(useCurCoin, notUseCur));

            }
            min = Math.min(min, dp[r][amount]);
        }
        if (min == Integer.MAX_VALUE)
            return -1;
        return min;
    }

    public static void main(String[] args) {


        Random random = new Random();
        L322_CoinChange test = new L322_CoinChange();

        int[] coins = {1, 2, 5};
        int money = 11;
        System.out.println(test.coinChangeBagDP(coins, money));
        System.out.println(test.coinChangeDp(coins, money));

        for (int i = 0; i < 100000; i++) {
            int[] randomArray = LeetCodes.getRandomArray(5, 200);
            int amount = random.nextInt(1000);
            int i1 = test.coinChange(randomArray, amount);
            int dp = test.coinChangeDp(randomArray, amount);
            if (i1 != dp) {
                LeetCodes.printArray(randomArray);
                System.out.println(amount);
                System.out.println("violence " + i1);
                System.out.println("dp " + dp);
                break;
            }
        }
    }
}
