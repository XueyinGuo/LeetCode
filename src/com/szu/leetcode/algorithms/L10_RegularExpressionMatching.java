package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * https://leetcode-cn.com/problems/regular-expression-matching/
 *
 * @Date 2021/5/20 20:27
 */

public class L10_RegularExpressionMatching {

    public static boolean isMatch(String string, String expression) {

        if (string == null || string.length() == 0 && (expression == null || expression.length() == 0))
            return true;
        char[] str = string.toCharArray();
        char[] exp = expression.toCharArray();
        if (!isValid(str, exp))
            return false;

        return process(str, 0, exp, 0);

    }

    private static boolean process(char[] str, int si, char[] exp, int ei) {


        if (ei == exp.length)
            return si == str.length;
        /*
         * 如果此时剩下的 exp 从 ei开始 每隔一个都是 *， 那么让后边所有的字符都变成 0个，也可以匹配
         * */
        if (si == str.length) {
            return restExpression(exp, ei);
        }

        /* exp[ei] 的下一个位置不是 ‘*’，所以本位置必须匹配 */
        if (ei + 1 >= exp.length || exp[ei + 1] != '*') {
            return (exp[ei] == str[si] || exp[ei] == '.') && process(str, si + 1, exp, ei + 1);
        }
        /* exp[ei] 的下一个位置是 ‘*’ */
        /*
         * exp[ei] 当前位置 是什么？
         * */

        /* 二话不说直接让 *变成 0个字符 */
        if (process(str, si, exp, ei + 2))
            return true;

        /* 枚举每种情况 */
        while (si < str.length && (str[si] == exp[ei] || exp[ei] == '.')) {
            if (process(str, si + 1, exp, ei + 2))
                return true;
            si++;
        }
        return false;
    }

    private static boolean restExpression(char[] exp, int ei) {

        for (int i = ei + 1; i < exp.length; i += 2) {
            if (exp[i] != '*')
                return false;
        }
        return exp[exp.length - 1] == '*';
    }

    private static boolean isValid(char[] str, char[] exp) {

        for (int i = 0; i < str.length; i++) {
            if (str[i] == '.' || str[i] == '*')
                return false;
        }

        for (int i = 1; i < exp.length; i++) {

            if (exp[i] == '*' && exp[i - 1] == '*')
                return false;

        }
        return true;
    }


    public static boolean isMatchWithStupidMemory(String string, String expression) {

        if (string == null || string.length() == 0 && (expression == null || expression.length() == 0))
            return true;
        char[] str = string.toCharArray();
        char[] exp = expression.toCharArray();
        if (!isValid(str, exp))
            return false;
        int[][] dp = new int[str.length + 1][exp.length + 1];
        fullFill(dp, -1);
        return processWithStupidMemory(str, 0, exp, 0, dp);

    }

    private static void fullFill(int[][] dp, int value) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = value;
            }
        }
    }

    private static boolean processWithStupidMemory(char[] str, int si, char[] exp, int ei, int[][] dp) {


        if (ei == exp.length) {
            dp[si][ei] = 0;
            return si == str.length;
        }
        /*
         * 如果此时剩下的 exp 从 ei开始 每隔一个都是 *， 那么让后边所有的字符都变成 0个，也可以匹配
         * */
        if (si == str.length) {
            dp[si][ei] = restExpression(exp, ei) ? 1 : 0;
            return dp[si][ei] == 1;
        }

        if (dp[si][ei] != -1)
            return dp[si][ei] == 1;

        /* exp[ei] 的下一个位置不是 ‘*’，所以本位置必须匹配 */
        if (ei + 1 >= exp.length || exp[ei + 1] != '*') {
            dp[si][ei] = (exp[ei] == str[si] || exp[ei] == '.') && processWithStupidMemory(str, si + 1, exp, ei + 1, dp) ? 1 : 0;
            return dp[si][ei] == 1;
        }
        /* exp[ei] 的下一个位置是 ‘*’ */
        /*
         * exp[ei] 当前位置 是什么？
         * */

        /* 二话不说直接让 *变成 0个字符 */
        if (processWithStupidMemory(str, si, exp, ei + 2, dp)) {
            dp[si][ei] = 1;
            return true;
        }

        /* 枚举每种情况 */
        while (si < str.length && (str[si] == exp[ei] || exp[ei] == '.')) {
            if (processWithStupidMemory(str, si + 1, exp, ei + 2, dp)) {
                dp[si][ei] = 1;
                return true;
            }
            dp[si][ei] = 0;
            si++;
        }
        return false;
    }


    public static void main(String[] args) {
        String str = "aaaaa";
        String exp = "a*b*c";
        isMatch(str, exp);
    }

}
