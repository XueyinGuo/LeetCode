package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * TODO【动态规划版本】 87. 扰乱字符串
    使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
    如果字符串的长度为 1 ，算法停止
    如果字符串的长度 > 1 ，执行下述步骤：
    在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
    随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
    在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
    给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。

    https://leetcode-cn.com/problems/scramble-string/
 *
 * @Date 2021/5/5 17:25
 */

public class L87_ScrambleString {

    public boolean isScramble(String s1, String s2) {
        /* 检验合法性 */
        if (!isValid(s1, s2))
            return false;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        /* 傻缓存 */
        int[][][] dp = new int[str1.length][str1.length][str1.length + 1];
        /*
        * str1 从 0 字符开始， 与 str2 从 0 字符开始， 全部长度 是否是 扰乱字符串
        * */
        return process(str1, 0, str2, 0, str1.length, dp);
    }

    private boolean process(char[] str1, int index1, char[] str2, int index2, int size, int[][][] dp) {

        if (size == 1)
            return str1[index1] == str2[index2];

        if (dp[index1][index2][size] != 0)
            return dp[index1][index2][size] == 1;

        boolean ans = false;
        /*
        * 枚举 str1 左部分
        * */
        for (int leftPart = 1; leftPart < size; leftPart++) {

            if (       /* 不旋转的情况下， 左边的搞定左边的 */
                    (process(str1, index1 + leftPart, str2, index2 + leftPart, size - leftPart, dp) &&
                            process(str1, index1, str2, index2, leftPart, dp)) /* 右边的搞定右边的，是否为扰乱串 */

                            || /* 或者 */
                            /* 扰乱的情况下，也就是两边交换了位置 去 搞定 */
                            /* str1 左边搞定 str2 等长度的 右边 */
                            (process(str1, index1 + leftPart, str2, index2, size - leftPart, dp) &&
                                    /* str1 右部分 搞定 等长度的 str2 的左部分 */
                                    process(str1, index1, str2, index2 + size - leftPart, leftPart, dp))

            ){
                /* 有一个为 true， 就返回 true */
                ans = true;
                break;
            }

        }
        dp[index1][index2][size] = ans ? 1 : -1;
        return ans;
    }

    private boolean isValid(String s1, String s2) {
        /*
         * 字符串不等长，以及里边的字符种类 和 每个字符的数量 有一个不一样
         *
         * 直接返回 false
         * */
        if (s1.length() != s2.length())
            return false;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] map1 = new int[26];
        int[] map2 = new int[26];

        for (int i = 0; i < str1.length; i++)
            map1[str1[i] - 'a']++;

        for (int i = 0; i < str2.length; i++)
            map2[str2[i] - 'a']++;


        for (int i = 0; i < 26; i++) {
            if (map1[i] != map2[i])
                return false;
        }
        return true;
    }

}
