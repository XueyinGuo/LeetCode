package com.szu.training.class08;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 现有 n1 + n2 中面值的硬币，其中前 n1 枚 为普通币，可以取任意枚
 * 后 n2 中为纪念币，最多只能取一枚，每种硬币有一个面值
 * 问能有多少种方法拼出 m 的面值？
 *
 * 经典十一
 *
 * @Date 2021/4/23 19:47
 */

public class MoneyWays {

    public static void main(String[] args) {

        int[] ordinary = {10, 50, 5,20};
        int[] special = {2, 3, 7, 5, 9};
        int money = 2000;

        int myWays = getWays(ordinary, special, money);
        int right = moneyWays(ordinary, special, money);
        System.out.println(myWays);
        System.out.println(right);
        if (myWays != right){

            System.out.println("FUCK");
        }
    }

    public static int getWays(int[] ordinary, int[] special, int money) {

        int[][] ordinaryDp = getOrdinaryDp(ordinary, money);
        int[][] specialDp = getSpecialDp(special, money);
        if (ordinaryDp == null)
            return specialDp[special.length - 1][money];
        if (specialDp == null)
            return ordinaryDp[ordinary.length - 1][money];
        int ans = 0;
        for (int i = 0; i <= money; i++) {
            ans += ordinaryDp[ordinary.length - 1][i] * specialDp[special.length - 1][money - i];
        }
        return ans;
    }

    public static int[][] getSpecialDp(int[] special, int money) {
        if (special == null || special.length == 0)
            return null;
        int length = special.length;
        int[][] dp = new int[length][money + 1];
        dp[0][0] = 1;
        dp[0][special[0]] = 1;
        for (int r = 0; r < length; r++) {
            dp[r][0] = 1;
        }
        for (int r = 1; r < length; r++) {
            for (int c = 1; c <= money; c++) {
                dp[r][c] =  dp[r - 1][c];
                if (c - special[r] >= 0)
                    dp[r][c] += dp[r-1][c - special[r]];

            }
        }
        return dp;
    }

    public static int[][] getOrdinaryDp(int[] ordinary, int money) {
        if (ordinary == null || ordinary.length == 0)
            return null;
        int length = ordinary.length;
        int[][] dp = new int[length][money + 1];

        for (int c = 0; c <= money; c += ordinary[0]) {
            dp[0][c] = 1;
        }
        for (int r = 0; r < length; r++) {
            dp[r][0] = 1;
        }

        for (int r = 1; r < length; r++) {
            for (int c = 1; c <= money; c++) {
                dp[r][c] = dp[r - 1][c];
                if (c - ordinary[r] >= 0)
                    dp[r][c] += dp[r][c - ordinary[r]]; /* 可以使用任意张，在本行依赖，省掉了枚举行为 */
            }
        }
        return dp;
    }


    /*
    * 大神
    * */
    public static int moneyWays(int[] arbitrary, int[] onlyone, int money) {
        if (money < 0) {
            return 0;
        }
        if ((arbitrary == null || arbitrary.length == 0) && (onlyone == null || onlyone.length == 0)) {
            return money == 0 ? 1 : 0;
        }
        // 任意张 的数组， 一张的数组，不可能都没有
        int[][] dparb = getDpArb(arbitrary, money);
        int[][] dpone = getDpOne(onlyone, money);
        if (dparb == null) { // 任意张的数组没有，一张的数组有
            return dpone[dpone.length - 1][money];
        }
        if (dpone == null) { // 任意张的数组有，一张的数组没有
            return dparb[dparb.length - 1][money];
        }
        int res = 0;
        for (int i = 0; i <= money; i++) {
            res += dparb[dparb.length - 1][i] * dpone[dpone.length - 1][money - i];
        }
        return res;
    }

    public static int[][] getDpArb(int[] arr, int money) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] dp = new int[arr.length][money + 1];
        // dp[i][j] 0..i券 自由选择张数， 搞定j元， 有多少方法？

        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        // [0] 5元 0元 5元 10元 15元 20元
        for (int j = 1; arr[0] * j <= money; j++) {
            dp[0][arr[0] * j] = 1;
        }
        // 0行 0列 填完了
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp;
    }

    public static int[][] getDpOne(int[] arr, int money) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] dp = new int[arr.length][money + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        if (arr[0] <= money) {
            dp[0][arr[0]] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : 0;
            }
        }
        return dp;
    }

}
