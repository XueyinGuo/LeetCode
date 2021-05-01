package com.szu.training02.class06;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个整数 N，代表 1~N 所有的数字，在给定一个整数 K。你可以随意排列这些数字，但是每一种排列都有若干个逆序对。
 * 返回有多少种排列方式正好有 K 个逆序对
 *
 * 例1 ；
 * input  n=3  k=0
 * output ： 1
 * 解释：只有[1,2,3] 这种排列可以有 0 个逆序对
 *
 * 例2：
 * input： n=3  k=1
 * output : 2
 * 解释：[3,2,1] 有三个逆序对，不达标
 *      [1,3,2] 有一个逆序对 可以
 *      [2,1,3] 有一个逆序对 可以
 *
 * @Date 2021/5/1 11:01
 */

import java.util.ArrayList;
import java.util.List;

public class KInversePairs {

    public static void main(String[] args) {
        int n = 10;
        int k = 10;
        int violence = violenceGenerateAll(n, k);
        int dp = dp(n, k);
        int dpAwesome = dpAwesome(n, k);
        System.out.println(violence);
        System.out.println(dpAwesome);
        System.out.println(dp);
    }

    private static int dpAwesome(int n, int k) {
        if (k < 0)
            return 0;
        if (k < 1)
            return 1;
        /* n必须 从1 开始，所以虽然申请了 0~n行，但是 第0行弃而不用 */
        int[][] dp = new int[n + 1][k + 1];
        /*
         * 1 ~ r 上，想成 0 个逆序对，只有 1 ~ r 升序一种排列方式
         * */
        for (int r = 1; r < n + 1; r++)
            dp[r][0] = 1;
        /* 只用 1 一个数字，形成 1~k 个逆序对，满足要求的排列方式必然为 0 */
//      dp[1][c] = 0;
        for (int r = 2; r < n + 1; r++) {
            for (int c = 1; c < k + 1; c++) {
                /*
                 * 从第三行，第1列开始填起
                 *
                 * 此时需要分两种情况讨论：
                 * 假设 从 1~7个数字，形成 6 个逆序对
                 * dp[7][6] 需要依赖的位置是：
                 *       1~6 上帮我 搞定 6 个，我 7 放在倒数第一位置， 7所引发的逆序对个数 为 0， 可以满足要求
                 *       1~6 上帮我 搞定 5 个，我 7 放在倒数第二个位置，7所引发的逆序对个数 为 1， 可以满足要求
                 *       ...
                 *       1~6 上帮我 搞定 0 个，我 7 放在第一个位置，7所引发的逆序对个数 为 6， 可以满足要求
                 *
                 * 然后继续省掉枚举行为
                 * */
                if (r > c) {
//                    for (int s = 0; s <= c; s++)
//                        dp[r][c] += dp[r - 1][s];

                    dp[r][c] = dp[r][c-1] + dp[r-1][c];
                } else {
                    /*
                     * 但是 当 1~7 上需要形成 7 个 和以上的逆序对的时候，就上边这么搞了
                     *
                     * 因为 如果 dp[7][7] 继续依赖 dp[6][0]， 【1~7形成7个逆序对，依赖 6 上形成 0个，也就是 1~6顺序递增的数组】
                     * 【此时就算 7 在第一个位置，也最多形成 6组逆序对了，所以 当 c >= r 的时候】
                     * 【最多依赖到 dp[r-1][c - r + 1] 的位置】
                     * 【本例中也就是 dp[7][7] 最多依赖到 dp[6][7 - 7 + 1]】
                     * 【也就是说，至少需要 1~6 搞定 1 个逆序对，7放在开头 才能有 7个逆序对】
                     *
                     * 然后继续省掉枚举行为
                     * */
//                    for (int s = c - r + 1; s <= c; s++)
//                        dp[r][c] += dp[r - 1][s];
                    dp[r][c] = dp[r][c - 1] - dp[r - 1][c - r] + dp[r - 1][c];
                }

            }
        }
        return dp[n][k];
    }

    public static int dp(int n, int k) {
        if (k < 0)
            return 0;
        if (k < 1)
            return 1;
        /* n必须 从1 开始，所以虽然申请了 0~n行，但是 第0行弃而不用 */
        int[][] dp = new int[n + 1][k + 1];
        /*
         * 1 ~ r 上，想成 0 个逆序对，只有 1 ~ r 升序一种排列方式
         * */
        for (int r = 1; r < n + 1; r++)
            dp[r][0] = 1;
        /* 只用 1 一个数字，形成 1~k 个逆序对，满足要求的排列方式必然为 0 */
//      dp[1][c] = 0;
        for (int r = 2; r < n + 1; r++) {
            for (int c = 1; c < k + 1; c++) {
                /*
                 * 从第三行，第1列开始填起
                 *
                 * 此时需要分两种情况讨论：
                 * 假设 从 1~7个数字，形成 6 个逆序对
                 * dp[7][6] 需要依赖的位置是：
                 *       1~6 上帮我 搞定 6 个，我 7 放在倒数第一位置， 7所引发的逆序对个数 为 0， 可以满足要求
                 *       1~6 上帮我 搞定 5 个，我 7 放在倒数第二个位置，7所引发的逆序对个数 为 1， 可以满足要求
                 *       ...
                 *       1~6 上帮我 搞定 0 个，我 7 放在第一个位置，7所引发的逆序对个数 为 6， 可以满足要求
                 * */
                if (r > c) {
                    for (int s = 0; s <= c; s++)
                        dp[r][c] += dp[r - 1][s];

                } else {
                    /*
                     * 但是 当 1~7 上需要形成 7 个 和以上的逆序对的时候，就上边这么搞了
                     *
                     * 因为 如果 dp[7][7] 继续依赖 dp[6][0]， 【1~7形成7个逆序对，依赖 6 上形成 0个，也就是 1~6顺序递增的数组】
                     * 【此时就算 7 在第一个位置，也最多形成 6组逆序对了，所以 当 c >= r 的时候】
                     * 【最多依赖到 dp[r-1][c - r + 1] 的位置】
                     * 【本例中也就是 dp[7][7] 最多依赖到 dp[6][7 - 7 + 1]】
                     * 【也就是说，至少需要 1~6 搞定 1 个逆序对，7放在开头 才能有 7个逆序对】
                     * */
                    for (int s = c - r + 1; s <= c; s++)
                        dp[r][c] += dp[r - 1][s];

                }

            }
        }
        return dp[n][k];
    }


    /*
     * 超级暴力
     *
     * 生成所有的可能的 数组
     * 然后挨个数出 数组中逆序对 正好等于 k 的 【满足要求的数组个数】
     * */
    public static int violenceGenerateAll(int n, int k) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[n + 1];
        generateArr(n, path, res, used);
        int ans = 0;
        for (List<Integer> list : res) {
            if (getInversePairs(list) == k)
                ans++;
        }
        return ans;
    }

    private static int getInversePairs(List<Integer> list) {
        int length = list.size();
        int ans = 0;
        for (int i = 0; i < length; i++) {
            Integer a = list.get(i);
            for (int j = i + 1; j < length; j++) {
                if (list.get(j) > a)
                    ans++;
            }
        }
        return ans;
    }

    /*
     * 回溯算法生成所有的数组
     * */
    private static void generateArr(int n, List<Integer> path, List<List<Integer>> res, boolean[] used) {
        if (path.size() == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int k = 1; k <= n; k++) {
            if (!used[k]) {
                used[k] = true;
                path.add(k);
                generateArr(n, path, res, used);
                used[k] = false;
                path.remove(path.size() - 1);
            }
        }
    }

}
