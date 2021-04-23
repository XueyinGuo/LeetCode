package com.szu.training.class08;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * TODO 重写，巩固练习
 * 给定一个字符串，如果可以在字符串任意位置添加字符，最少添加几个能让字符整体都是回文串
 *
 * 经典十
 * @Date 2021/4/23 14:01
 */

public class PalindromeMinAdd {

    public static void main(String[] args) {
        String str = "AB1CD2EFG3H43IJK2L1MN";
        System.out.println(getPalindrome(str));
    }

    private static int getPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return 0;
        if (s.length() == 2)
            return s.charAt(0) == s.charAt(1) ? 0 : 1;
        char[] str = s.toCharArray();
        int length = str.length;
        int[][] dp = getDP(str);
        return dp[0][length - 1];
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
         *
         * 左边是回文串吗？ 先搞定左边，然后把我自己添加到最左边
         * 先搞定右边，然后把我自己添加到最右边
         * 两头相等，只搞定我中间这部分就好了
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

}
