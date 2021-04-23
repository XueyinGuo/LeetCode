package com.szu.training.class08;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * int[] d, d[i] i 号怪兽的能力
 * int[] p, p[i] i 号怪兽要求的钱
 * 开始时，你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽
 * 如果你当前的能力小于 i 号怪兽的能力，你必须付出 相应的钱数才能贿赂这个怪兽，然后怪兽就会加入你
 * 怪兽的能力会累加到你自己的能力上
 *
 * 如果你的能力 大于这个怪兽，这个怪兽会被你吓跑，你的能力也不会损失，也不用多花钱。当然你也可以继续贿赂这个怪兽
 * 让他的能力继续累加到你自己的能力上
 *
 * 问 通关，需要最少的钱数是多少
 *
 * TODO 重新做！！！巩固练习
 *
 * @Date 2021/4/23 12:06
 */

import com.szu.leetcode.utils.LeetCodes;

public class MonstersCost {

    public static void main(String[] args) {
        int len = 10;
        int bound = 20;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[] monster = LeetCodes.getRandomArray(len, bound);
            int[] money = LeetCodes.getRandomArray(len, bound);
            long ans1 = violence(monster, money);
            long ans2 = abilityDp(monster, money);
            long ans3 = moneyDp(monster, money);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("FUCK!");
            }
        }

    }

    private static long moneyDp(int[] monster, int[] money) {
        int moneySum = 0;
        for (int i = 0; i < money.length; i++) {
            moneySum += money[i];
        }
        // dp[i][j]含义：
        // 能经过0～i的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
        // 如果dp[i][j]==-1，表示经过0～i的怪兽，花钱为j是无法通过的，或者之前的钱怎么组合也得不到正好为j的钱数
        int[][] dp = new int[money.length][moneySum + 1];
        for (int r = 0; r < dp.length; r++) {
            for (int c = 0; c <= moneySum; c++) {
                dp[r][c] = -1;
            }
        }
        dp[0][money[0]] = monster[0];
        for (int row = 1; row < money.length; row++) {
            for (int col = 0; col <= moneySum; col++) {
                // 可能性一，为当前怪兽花钱, 不管我现在的能力大于不大于，我都花钱去买怪兽能力
                if (col - money[row] >= 0 && dp[row - 1][col - money[row]] != -1) {
                    dp[row][col] = dp[row - 1][col - money[row]] + monster[row];
                }
                // 可能性二，不为当前怪兽花钱
                if (dp[row - 1][col] >= monster[row]) {
                    if (dp[row - 1][col] > dp[row][col])
                        dp[row][col] = dp[row - 1][col];
                }
            }
        }
        for (int i = 0; i <= moneySum; i++) {
            if (dp[money.length - 1][i] != -1)
                return i;
        }
        return 0;
    }

    private static long abilityDp(int[] monster, int[] money) {
        int abilitySum = 0;
        for (int i = 0; i < monster.length; i++) {
            abilitySum += monster[i];
        }
        /* abilitySum 长度的数组，只能表示 0--abilitySum-1 */
        long[][] dp = new long[monster.length + 1][abilitySum + 1];
        for (int row = monster.length - 1; row >= 0; row--) {
            for (int col = 0; col <= abilitySum; col++) {
                if (col + monster[row] > abilitySum)
                    continue;
                if (col < monster[row])
                    dp[row][col] = dp[row + 1][col + monster[row]] + money[row];
                else {
                    dp[row][col] = dp[row + 1][col + monster[row]] + money[row];
                    if (dp[row + 1][col] < dp[row][col])
                        dp[row][col] = dp[row + 1][col];
                }

            }
        }
        return dp[0][0];
    }

    private static long violence(int[] monster, int[] money) {
        min = Long.MAX_VALUE;
        violence(monster, money, 0, 0, 0);
        return min;
    }

    static long min = Long.MAX_VALUE;

    private static void violence(int[] monster, int[] money, int index, long cost, long power) {
        /* 剪枝 */
        if (cost > min)
            return;
        /* 来到了最后一个位置 */
        if (index == monster.length) {
            if (cost < min) {
                min = cost;
            }
            return;
        }
        /* 没得选，只能贿赂 */
        if (power < monster[index]) {
            violence(monster, money, index + 1, cost + money[index], power + monster[index]);
        } else {
            /* 可以选择贿赂，也可以不贿赂 */
            violence(monster, money, index + 1, cost + money[index], power + monster[index]);
            violence(monster, money, index + 1, cost, power);
        }
    }

}
