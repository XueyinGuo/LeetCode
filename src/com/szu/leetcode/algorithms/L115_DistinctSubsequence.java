package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定两个字符串 S 和 T， 返回 S 子序列等于 T 的不同子序列个数有多少个？
 * 如果得到子序列 A 删除的位置与得到的子序列 B 删除的位置不同，那么认为 A 和 B就是不同的、
 *
 * 例子： S = "rabbbit"   T = "rabbit"
 * 返回 3
 * 是以下三个 S 的不同子序列，有 | 的位置便是删除的字符，因为删除的位置不同，所以这三个序列是不一样的
 *
 * r a b b b i t         r a b b b i t                r a b b b i t
 *     |                       |                              |
 *
 * @Date 2021/4/25 20:53
 */

import com.szu.leetcode.utils.LeetCodes;

public class L115_DistinctSubsequence {

    public static int violence(String s, String t) {
//        S 子序列等于 T 的不同子序列个数有多少个
        if (s == null || s.length() == 0)
            return t == null || t.length() == 0 ? 1 : 0; //如恰好 t 也是 0 长度，那么一个也不删除就是一种方案
        if (t == null || t.length() == 0)
            return 1; // 删掉所有的字符才行

        char[] str = s.toCharArray();
        char[] des = t.toCharArray();
        return violence(str, 0, des, 0);
    }

    private static int violence(char[] str, int originalIndex, char[] des, int destIndex) {
        if (destIndex == des.length)
            return 1;
        if (originalIndex == str.length)
            return 0;

        int ways = 0;
        /* 如果两个位置字符相等，同时下移一位的结果也要算上 */
        if (str[originalIndex] == des[destIndex])
            ways += violence(str, originalIndex + 1, des, destIndex + 1);
        /* 如果不等  那就相当于让 原串下移一位 */
        ways += violence(str, originalIndex + 1, des, destIndex);
        return ways;
    }

    public static int dp(String s, String t) {
        if (s == null || s.length() == 0)
            return t == null || t.length() == 0 ? 1 : 0; //如恰好 t 也是 0 长度，那么一个也不删除就是一种方案
        if (t == null || t.length() == 0)
            return 1; // 删掉所有的字符才行
        char[] str = s.toCharArray();
        char[] des = t.toCharArray();
        int rows = str.length;
        int cols = des.length;
        int[][] dp = new int[rows][cols];

        dp[0][0] = str[0] == des[0] ? 1 : 0;
        for (int r = 1; r < rows; r++) {
            dp[r][0] = dp[r-1][0];
            if (str[r] == des[0])
                dp[r][0]++;

        }
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                dp[r][c] = dp[r - 1][c];
                if (str[r] == des[c])
                    dp[r][c] += dp[r - 1][c - 1];
            }
        }
        return dp[rows - 1][cols - 1];
    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            String s = LeetCodes.getRandomString(20);
            String t = LeetCodes.getRandomString(6);
            int violence = violence(s, t);
            int right = numDistinct(s, t);
            int dp = dp(s, t);
            if (violence != right || violence != dp)
                System.out.println("FUCK");
        }

    }

    /*
    * right 代码
    * */
    public static int numDistinct(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        return process(s, t, s.length, t.length);
    }

    public static int process(char[] s, char[] t, int i, int j) {
        if (j == 0) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        int res = process(s, t, i - 1, j);
        if (s[i - 1] == t[j - 1]) {
            res += process(s, t, i - 1, j - 1);
        }
        return res;
    }
}
