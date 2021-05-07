package com.szu.training03.class04;
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

public class EggDrop {

    public int superEggDrop(int eggs, int floor) {
//        int[][] dp = new int[eggs+1][floor+1];
//        return violence(eggs, floor, dp);
        return violenceDP(eggs, floor);
    }

    private int violenceDP(int eggs, int floor) {
        int[][] dp = new int[floor + 1][eggs + 1];
        for (int c = 0; c <= eggs; c++) {
            dp[1][c] = 1;
        }
        for (int r = 1; r <= floor; r++) {
            dp[r][1] = r;
        }
        for (int f = 1; f <= floor; f++) {
            for (int e = 2; e <= eggs; e++) {
                dp[f][e] = Integer.MAX_VALUE;
                for (int i = 1; i <= f; i++) {
                    int cur = Math.max(dp[i - 1][e - 1], dp[floor - f][e]) + 1;
                    dp[f][e] = Math.min(dp[f][e], cur);
                }
            }
        }
        return dp[floor][eggs];
    }


    /*
     * 超级暴力的解，直接超时
     * */
    public int violence(int eggs, int floor, int[][] dp) {
        if (eggs == 1)
            return floor;
        if (floor == 0)
            return 0;

        if (dp[eggs][floor] != 0)
            return dp[eggs][floor];

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= floor; i++) {
            /*
             * 枚举每个楼层，
             * 1.如果我当前的楼层碎了鸡蛋，那我就往下找就可以了，那么 i - 1 作为子问题的楼层数，子问题有 i - 1 层楼需要搞定
             * 2.如果当前楼层没有碎，那么我往上找就好了，剩下的楼层数为 floor - i
             *
             * 两个返回值中求出最大的
             * */
            int cur = Math.max(
                    violence(eggs - 1, i - 1, dp),
                    violence(eggs, floor - i, dp)
            ) + 1;
            /*
             * 然后在所有最大的返回值中求出最小的即为答案
             * */
            if (cur < ans)
                ans = cur;
        }
        dp[eggs][floor] = ans;
        return ans;
    }


    public static void main(String[] args) {
        int eggs = 10;
        int floor = 100;
        EggDrop eggDrop = new EggDrop();
        eggDrop.superEggDrop(eggs, floor);
    }
}
