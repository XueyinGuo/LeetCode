package com.szu.training.class06;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * PAGE 234
 *
 * 给定三个字符串 str1、str2 , aim， 如果 aim 中包含且仅包含str1、str2中所有的字符
 * 而且在 aim 中 属于str1 字符之间的 顺序 与原来保持一致， str2 也是
 * 那么说 aim 是 str1 与 str2 的 交错组成。
 *
 * 实现一个函数，判断 aim 是否是 str1 str2 的交错组成
 *
 * 举例 ：
 * str1 ： “AB”     str2:“12”
 * A12B  AB12   A1B2 都是交错组成
 *
 *
 *
 * @Date 2021/4/21 22:48
 */

public class StringCross {

    public static boolean isCross(String s1, String s2, String aim) {

        if (aim == null || aim.length() != (s1 == null ? 0 : s1.length()) + (s2 == null ? 0 : s2.length()))
            return false;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] strAim = aim.toCharArray();
        int rows = str1.length + 1;
        int cols = str2.length + 1;
        boolean dp[][] = new boolean[rows][cols];
        dp[0][0] = true;
        for (int c = 1; c < cols; c++) {
            if (str2[c - 1] == strAim[c - 1]) {

                dp[0][c] = true;
            }else
                break;
        }
        for (int r = 1; r < rows; r++) {
            if (str1[r - 1] == strAim[r - 1]) {
                dp[r][0] =  true;
            }else
                break;
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (dp[r-1][c] && strAim[r+c-1] == str1[r-1])
                    dp[r][c] = true;
                if (dp[r][c-1] && strAim[r+c-1] == str2[c-1])
                    dp[r][c] = true;
            }
        }
        return dp[rows-1][cols-1];

    }

    public static void main(String[] args) {
        String str1 = "AB";
        String str2 = "12";
        String aim = "A1B2";
        boolean cross = isCross(str1, str2, aim);
        System.out.println(cross);
    }

}
