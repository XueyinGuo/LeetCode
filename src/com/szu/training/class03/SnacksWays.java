package com.szu.training.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 背包容量为 W
 * 一共有 N袋子零食，第i袋子零食体积为 v[i]
 * 总体积不超过  背包容量的情况下
 * 一共有多少种零食的放法？（总体积为 0 也算一种）
 *
 * @Date 2021/4/13 11:49
 */

public class SnacksWays {

    public int violence(int[] v, int w){
        return violence(v, w, 0);
    }

    private int violence(int[] weights, int rest, int index) {

        if (rest < 0)
            return 0;
        /* 没零食可以选择，但是包包没装满 也算一种选择 */
        if (rest == 0)
            return 1;
        if (index == weights.length)
            return 1;

        int ways = 0;

        ways += violence(weights, rest - weights[index], index + 1);
        ways += violence(weights, rest, index + 1);
        return ways;
    }

    public int dpWays(int [] weights, int rest){
        int len = weights.length ;
        int[][] dp = new int[len + 1][rest + 1];

        for (int i = 0; i <= len; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i <= rest; i++) {
            dp[len][i] = 1;
        }

        for (int l = len - 1; l >= 0 ; l--) {
            for (int r = 1; r <= rest; r++) {
                dp[l][r] = dp[l + 1][r];
                if (r - weights[l] >= 0)
                    dp[l][r] += dp[l+1][r - weights[l]];
            }
        }

        return dp[0][rest];
    }


    public static void main(String[] args) {
        SnacksWays test = new SnacksWays();
        int[] arr = {1,4,6,2,3,6,8,7,2,2,1,7};
        int w = 30;
        int violence = test.violence(arr, w);
        int dpWays = test.dpWays(arr, w);
        System.out.println(violence + "  " + dpWays);

    }
}
