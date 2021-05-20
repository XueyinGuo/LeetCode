package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * https://leetcode-cn.com/problems/wildcard-matching/
 *
 * @Date 2021/5/20 20:27
 */

public class L44_RegularExpressionMatching {

    public static boolean isMatch(String s, String p) {
        if ((s == null || s.length() == 0) && (p == null || p.length() == 0))
            return true;
        char[] str = s.toCharArray();
        char[] exp = p.toCharArray();
        int[][] dp = new int[str.length + 1][exp.length + 1];
        fullFill(dp, -1);
        return process(str, 0, exp, 0, dp);
    }

    private static boolean process(char[] str, int si, char[] exp, int ei, int[][] dp) {

        if (ei == exp.length) {
            dp[si][ei] = si == str.length ? 1 : 0;
            return dp[si][ei] == 1;
        }

        if (si == str.length) {
            dp[si][ei] = (ei == exp.length || checkRestExpression(exp, ei)) ? 1 : 0;
            return dp[si][ei] == 1;
        }

        if (dp[si][ei] != -1)
            return dp[si][ei] == 1;

        /* exp[ei] 位置不是 '*' */
        if (exp[ei] != '*'){
            dp[si][ei] = (str[si] == exp[ei] || exp[ei] == '?') && process(str, si + 1, exp, ei + 1, dp)? 1 : 0;
            return dp[si][ei] == 1;
        }

        /* ei 位置是'*' */
        if (process(str, si, exp, ei + 1, dp)){
            dp[si][ei] = 1;
            return true;
        }
        while (si < str.length) {
            if (process(str, si + 1, exp, ei + 1, dp)){
                dp[si][ei] = 1;
                return true;
            }
            dp[si][ei] = 0;
            si++;
        }
        return false;
    }

    private static boolean checkRestExpression(char[] exp, int ei) {

        for (int i = ei; i < exp.length; i++) {
            if (exp[i] != '*')
                return false;
        }
        return true;
    }


    private static void fullFill(int[][] dp, int value) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = value;
            }
        }
    }


    public static void main(String[] args) {
        String str = "mississippi";


        String exp = "m??*ss*?i*pi";
        System.out.println(isMatch(str, exp));
    }

}
