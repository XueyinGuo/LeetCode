package com.szu.training.class08;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个字符串，如果可以在字符串任意位置添加字符，最少添加几个能让字符整体都是回文串
 *
 * 经典十
 * @Date 2021/4/23 14:01
 */

import com.szu.leetcode.utils.LeetCodes;

public class PalindromeMinAddII {

    public static void main(String[] args) {
        for (int t = 0; t < 10000; t++) {
            String str = LeetCodes.getRandomString(20);
            String my = getPalindrome(str);
            int i = 0;
            int j = my.length() - 1;
            while (i <= j) {
                if (my.charAt(i++) != my.charAt(j--))
                    System.out.println("FUCK");
            }
        }


    }

    private static String getPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return null;
        if (s.length() == 2)
            return s.charAt(0) == s.charAt(1) ? s : s + s.charAt(0);
        char[] str = s.toCharArray();
        int length = str.length;
        int[][] dp = getDP(str);
        int add = dp[0][length - 1];
        /* 生成一种字符串返回 ， 利用动态规划表回溯决策路径 */
        char[] res = new char[s.length() + add];
        int resIndexTail = res.length - 1;
        int resIndexHead = 0;
        int r = 0;
        int c = length - 1;
        while (r <= c) {
            int left = dp[r][c - 1];
            int down = dp[r + 1][c];
            if (str[r] == str[c]) {
                res[resIndexHead++] = str[r++];
                res[resIndexTail--] = str[c--];
            } else if (left > down) {
                res[resIndexHead++] = str[r];
                res[resIndexTail--] = str[r];
                r++;
            } else {
                res[resIndexHead++] = str[c];
                res[resIndexTail--] = str[c];
                c--;
            }
        }
        return String.valueOf(res);
    }

    private static int[][] getDP(char[] str) {
        int length = str.length;
        int[][] dp = new int[length][length];

//        for (int i = 0; i < length; i++) {
//            dp[i][i] = 0;
//        }
        for (int i = 0; i < length - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }

        /*
         * 一个样本做行，一个样本做列的对应模型，往往只讨论结尾如何如何！
         * 范围上的尝试模型讨论的是开头和结尾共同的可能性如何如何！
         *
         * 可能性的组织情况就是：
         * 按照 开头 和 结尾分开组织，
         * 一般就是 开头最后处理，结尾最后处理， 开头和结尾相同处理中间的
         * */

        for (int r = length - 2; r >= 0; r--) {
            for (int c = r + 2; c < length; c++) {

                if (str[r] == str[c])
                    dp[r][c] = dp[r + 1][c - 1];
                else {
                    dp[r][c] = dp[r + 1][c];
                    if (dp[r][c - 1] < dp[r][c])
                        dp[r][c] = dp[r][c - 1];
                    dp[r][c] += 1;
                }

            }
        }
        return dp;
    }


    /*
     * 大神
     * */
    public static String getPalindrome1(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        char[] chas = str.toCharArray();
        int[][] dp = getDP1(chas);
        System.out.println(dp[0][chas.length - 1]);
        char[] res = new char[chas.length + dp[0][chas.length - 1]];
        int i = 0;
        int j = chas.length - 1;
        int resl = 0;
        int resr = res.length - 1;
        while (i <= j) {
            if (chas[i] == chas[j]) {
                res[resl++] = chas[i++];
                res[resr--] = chas[j--];
            } else if (dp[i][j - 1] < dp[i + 1][j]) {
                res[resl++] = chas[j];
                res[resr--] = chas[j--];
            } else {
                res[resl++] = chas[i];
                res[resr--] = chas[i++];
            }
        }
        return String.valueOf(res);
    }

    public static int[][] getDP1(char[] str) {
        int[][] dp = new int[str.length][str.length];
        for (int j = 1; j < str.length; j++) {
            dp[j - 1][j] = str[j - 1] == str[j] ? 0 : 1;
            for (int i = j - 2; i > -1; i--) {
                if (str[i] == str[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp;
    }

}
