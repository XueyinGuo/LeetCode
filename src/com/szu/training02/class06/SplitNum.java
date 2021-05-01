package com.szu.training02.class06;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个正数 1 ，裂开的方式有1种 【1】，
 * 给定一个正数 2，裂开的方式有 2种 【1,1】 【2】
 * 给定一个正数 3  裂开的方式有3 种【1,1,1】 【1,2】 【3】
 * 给定一个正数 4 裂开的方式有 5 钟 【1,1,1,1】 【1,1,2】 【1,3】 【2,2】 【4】
 *
 * 求给定一个正数 n， 有多少种裂开的方式
 *
 * @Date 2021/4/30 12:25
 */

public class SplitNum {

    public static int violence(int n) {
        return violence(1, n);
    }

    public static int violence(int start, int rest) {

        if (rest == 0)
            return 1;
        /* 因为原题中说了，所有的分裂方式全部是升序【允许相等】，所以一旦start > rest 直接返回 0 种 */
        if (start > rest)
            return 0;
        int ways = 0;
        /*
         * 从start开始，i 传入下一层，
         * 并且传入下一层的时候 rest 每次减掉 i， 保证下一层不会比自己已经形成的数字小
         * */
        for (int i = start; i <= rest; i++) {
            ways += violence(i, rest - i);
        }
        return ways;
    }

    /*
     * 动态规划解法
     * */
    public static int dp(int n) {
        if (n < 1)
            return 0;
        if (n == 1)
            return 1;

        int[][] dp = new int[n + 1][n + 1];
        /* 因为start取不到0，所以 第0行直接废掉不填 */
        /* rest = 0 的时候，全部填 1 */
        for (int i = 1; i < n + 1; i++)
            dp[i][0] = 1;
        /* 对角线【也就是 start == rest 的时候】，我们发现 rest - start 传入下一层 直接返回 1， 所以对角线也是 1 */
        for (int i = 1; i < n + 1; i++)
            dp[i][i] = 1;
        /*
         * 普遍位置的依赖关系，
         * 根据暴力递归的推算，可以得如下状态转移
         * */
        for (int r = n - 1; r >= 1; r--) {
            for (int c = r + 1; c < n + 1; c++) {
                for (int k = r; c - k >= 0; k++) {
                    dp[r][c] += dp[k][c - k];
                }
            }
        }
        return dp[1][n];
    }

    /*
     * 最优的动态规划！！！
     * 斜率优化
     * */
    public static int dpAwesome(int n) {
        if (n < 1)
            return 0;
        if (n == 1)
            return 1;

        int[][] dp = new int[n + 1][n + 1];
        /* 因为start取不到0，所以 第0行直接废掉不填 */
        /* rest = 0 的时候，全部填 1 */
        for (int i = 1; i < n + 1; i++)
            dp[i][0] = 1;
        /* 对角线【也就是 start == rest 的时候】，我们发现 rest - start 传入下一层 直接返回 1， 所以对角线也是 1 */
        for (int i = 1; i < n + 1; i++)
            dp[i][i] = 1;
        /*
         * 省掉枚举行为！！！
         * */
        for (int r = n - 1; r >= 1; r--) {
            for (int c = r + 1; c < n + 1; c++) {
//                for (int k = r; c - k >= 0; k++) {
                dp[r][c] += dp[r + 1][c] + dp[r][c - r];
//                }
            }
        }
        return dp[1][n];
    }


    public static void main(String[] args) {
        int n = 100;
        int violence = violence(n);
        int dp = dp(n);
        int dpAwesome = dpAwesome(n);
        int right = ways1(n);
//        if (violence != right){
        System.out.println(violence);
        System.out.println(right);
        System.out.println(dp);
        System.out.println(dpAwesome);
//        }
    }


    /*
     * Right
     * */
    public static int ways1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int i = pre; i <= rest; i++) {
            ways += process(i, rest - i);
        }
        return ways;
    }
}
