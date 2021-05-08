package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 887. 鸡蛋掉落
给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。

已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。

每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。
如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。

请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 *
 * @Date 2021/5/7 13:53
 */

public class L887_EggDrop {

    public int superEggDrop(int eggs, int floor) {
        return awesomeDP(eggs, floor);
    }


    public int awesomeDP(int eggs, int floor) {
        /*
         * 奇怪的试法：
         * r 为 鸡蛋数， c 为 扔的次数
         * r枚鸡蛋 扔 一次，都只能确定一层楼
         * 所以 dp[r][1] = 1
         * 【第 0 列依然弃而不用】
         *
         * dp[r][c]， r 为 鸡蛋数， c 为 扔的次数
         * 1.如果鸡蛋碎了， 我还剩 r-1 枚鸡蛋， c-1 次数
         * 2.如果鸡蛋没碎， 我还 r 枚鸡蛋， c-1 次
         *
         * dp[r][c] 能搞定的楼层数 就是 dp[r-1][c-1] + dp[r][c-1] 层
         * */
        int[] dp1 = new int[eggs + 1];
        int[] dp2 = new int[eggs + 1];
        for (int r = 1; r <= eggs; r++) {
            dp1[r] = 1;
        }
        while (dp1[eggs] < floor) {
            int i = 2;
            dp2[1] = dp1[1] + 1;
            while (i <= eggs) {
                /*
                * ===================================
                * ===================================
                * 为什么加这个 1 呢？ 是因为自己也搞定了一层
                * ===================================
                * ===================================
                * */
                dp2[i] = dp1[i] + dp1[i - 1] + 1;
                i++;
            }
            int[] tem = dp2;
            dp2 = dp1;
            dp1 = tem;
        }
        return dp1[1];
    }


    private int quadrangleOptimization(int eggs, int floor) {
        int dp[][] = new int[floor + 1][eggs + 1];
        int[][] choose = new int[floor + 1][eggs + 1];
        for (int c = 1; c <= eggs; c++) {
            dp[1][c] = 1;
            choose[1][c] = 1;
        }
        for (int r = 1; r <= floor; r++)
            dp[r][1] = r;
        for (int r = 2; r <= floor; r++) {
            for (int c = eggs; c >= 2; c--) {
                int up = choose[r - 1][c];
                int down = c == eggs ? r : choose[r][c + 1];
                int min = Integer.MAX_VALUE;
                for (int i = up; i <= down; i++) {
                    int cur = Math.max(dp[i - 1][c - 1], dp[r - i][c]);
                    if (cur <= min) { // 小于等于！！！
                        choose[r][c] = i;
                        min = cur;
                    }
                }
                dp[r][c] = min + 1;
            }
        }
        return dp[floor][eggs];
    }


    private int violenceDP(int eggs, int floor) {
        int dp[][] = new int[floor + 1][eggs + 1];

        for (int c = 1; c <= eggs; c++) {
            dp[1][c] = 1;
        }
        for (int r = 1; r <= floor; r++) {
            dp[r][1] = r;
        }

        for (int r = 2; r <= floor; r++) {
            for (int c = 2; c <= eggs; c++) {
                int min = Integer.MAX_VALUE;
                for (int i = 1; i <= r; i++) {
                    int cur = Math.max(dp[i - 1][c - 1], dp[r - i][c]) + 1;
                    if (cur < min) // 找到最大值中的最小值
                        min = cur;
                    /* int cur = Math.max(
                            violence(c - 1, i - 1, dp),
                            violence(c, r - i, dp)
                    ) + 1; */
                }
                dp[r][c] = min;
            }
        }


        return dp[floor][eggs];
//        return dp;
    }


    /*
     * 超级暴力的解，直接超时
     * */
    public int violence(int c, int r, int[][] dp) {
        if (c == 1)
            return r;
        if (r == 0)
            return 0;

        if (dp[c][r] != 0)
            return dp[c][r];

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= r; i++) {
            /*
             * 枚举每个楼层，
             * 1.如果我当前的楼层碎了鸡蛋，那我就往下找就可以了，那么 i - 1 作为子问题的楼层数，子问题有 i - 1 层楼需要搞定
             * 2.如果当前楼层没有碎，那么我往上找就好了，剩下的楼层数为 floor - i
             *
             * 两个返回值中求出最大的
             * */
            int cur = Math.max(
                    violence(c - 1, i - 1, dp),
                    violence(c, r - i, dp)
            ) + 1;
            /*
             * 然后在所有最大的返回值中求出最小的即为答案
             * */
            if (cur < ans)
                ans = cur;
        }
        dp[c][r] = ans;
        return ans;
    }


    public static void main(String[] args) {
        int eggs = 5;
        int floor = 200;
        L887_EggDrop eggDrop = new L887_EggDrop();
        int quadrangleOptimization = eggDrop.quadrangleOptimization(eggs, floor);
        int violenceDP = eggDrop.violenceDP(eggs, floor);
        int awesomeDP = eggDrop.awesomeDP(eggs, floor);
//        int i = eggDrop.violence(eggs, floor);
        System.out.println(quadrangleOptimization);
        System.out.println(violenceDP);
        System.out.println(awesomeDP);
    }


}
