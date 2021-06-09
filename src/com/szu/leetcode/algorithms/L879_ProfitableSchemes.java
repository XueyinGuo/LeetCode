package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 879. 盈利计划
集团里有 n 名员工，他们可以完成各种各样的工作创造利润。

第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。

工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。

有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 *
 * @Date 2021/6/9 21:21
 */

import com.szu.leetcode.utils.LeetCodes;

public class L879_ProfitableSchemes {

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        if (group == null || profit == null || group.length == 0)
            return 0;
        return violence(n, minProfit, group, profit, 0);
    }



    /*
     * 暴力解，
     * 背包问题：只不过背包有两个
     * 人数背包 + 盈利背包
     * */
    public int violence(int n, int minProfit, int[] group, int[] profit, int index) {
        if (n < 0) {
            // 人数小于 0 时，没办法继续分配工作了，直接返回 0
            return 0;
        }
        if (index == group.length) { // 此时已经没有剩余任务，如果达到了盈利目标，返回 1 中方法，否则返回 0 中方法
            return minProfit <= 0 ? 1 : 0;
        }
        int ways = 0;
        ways += violence(n - group[index], minProfit - profit[index], group, profit, index + 1);
        ways += violence(n, minProfit, group, profit, index + 1);
        return ways;
    }

    /*
     * 根据暴力解写的动态规划
     * */
    public int profitableSchemesDp(int n, int minProfit, int[] group, int[] profit) {
        if (group == null || profit == null || group.length == 0 )
            return 0;
        int length = group.length;
        int[][][] dp = new int[n + 1][minProfit + 1][length + 1];
//        for (int i = 0; i <= group.length; i++) {
//            for (int j = 0; j <= n; j++) {
//                for (int k = 0; k <= minProfit; k++) {
//                    dp[i][j][k] = -1;
//                }
//            }
//        }

        for (int r = 0; r <= n; r++) {
            dp[r][0][group.length] = 1;
        }
        for (int h = group.length - 1; h >= 0; h--) {
            for (int r = 0; r <= n; r++) {
                for (int c = 0; c <= minProfit; c++) {
                    int ways = 0;
                    int restPeople = r - group[h];
                    if (restPeople >= 0)
                        ways += c - profit[h] < 0 ? dp[r - group[h]][0][h + 1] : dp[r - group[h]][c - profit[h]][h + 1];
                    ways += dp[r][c][h + 1];
                    dp[r][c][h] = ways % 1000000007;
                }
            }
        }
        return dp[n][minProfit][0];
    }

    public static void main(String[] args) {
        int n = 64;
        int minP = 0;
        int[] group = LeetCodes.getInputArray("[80,40]");
        int[] profit = LeetCodes.getInputArray("[88,88]");
        L879_ProfitableSchemes test = new L879_ProfitableSchemes();
        System.out.println(test.profitableSchemes(n, minP, group, profit));
        System.out.println(test.profitableSchemesDp(n, minP, group, profit));

    }
}
