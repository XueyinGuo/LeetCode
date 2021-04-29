package com.szu.training02.class05;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个字符串， 移除部分（0 个 或者多个）字符使其变成回文串
 * 有多少种移除方案。【空串不是回文串！！！】
 *
 * 比如 "XXY" 有四种 ： 分别是 "X"【第一个 X】     "X"【第二个 X】      "XX"     "Y"
 *      "ABA"  五种   ：   "A"   "B"   "A"   "AA"   "ABA"
 *
 * @Date 2021/4/29 15:41
 */

import com.szu.leetcode.utils.LeetCodes;

public class PalindromeWays {

    public static int palindromeWays(String s) {
        if (s == null || s.length() == 0)
            return 0;
        char[] str = s.toCharArray();
        int length = str.length;
        int[][] dp = new int[length][length];
        /*
         * 范围尝试
         *
         * 回文 对角线肯定都是 1,而且不允许空串出现，所以必定都是 1
         * */
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }
        /*
         * 第二条对角线 两个字符相等就是 3 ，比如 "XX"   可以分别保留第一、二个 x,为两种方案，也可全部保留 作为三种方案
         *
         * 如果不同的字符，只能分别保留两个位置， 所以为两种方案
         * */
        for (int i = 0; i < length - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 3 : 2;
        }
        /*
         * 对于任意一个普遍位置
         * =====================================================
         * =====================================================
         * =====================================================
         * =====================================================
         * 不保留 c 位置的字符有多少种方案 ： 【a种】
         * 不保留 r 位置的字符有多少种方案 ： 【b种】
         * 两个位置都不保留有多少种方案    ： 【c种】
         * 两个位置都保留的方案数         ： 【d种】
         *
         * 这四种类型是互斥的，没有任何的重复部分，
         *
         * 1.如果 两个位置不相等 。一个位置应该变成回文的方法数 应该是 a + b + c 的累加和
         * 【要么不需要 c 位置， 要么 不需要 r 位置， 要么两个位置都删掉 】
         *
         * 如果 两个位置不相等 dp[r][c] = dp[r][c - 1] + dp[r + 1][c] - dp[r + 1][c - 1];
         *                              【a + c】       【b + c】            【c】
         *                          不要 c 位置字符      不要 r 位置的字符       两个位置都不要
         *
         * 因为上边的表达式 加起来就是 a + b + 2c 了，所以我们要减掉一个 c
         *
         * 2.如果两个位置相等
         * 那么方法数会变成   【   （1）要么不需要 c 位置，
         *                      （2） 要么 不需要 r 位置，
         *                      （3）要么两个位置都删掉，
         *                      （4）要么两个位置都保留
         *                      （5）中间全部删除 只保留两头的字符 】
         *
         * 转移方程就变成了 ： dp[r][c] = dp[r][c - 1] + dp[r + 1][c] + 1;
         *                           = 【a + b + c + d + 1】
         *
         * d = dp[r + 1][c - 1]， 为什么呢？
         * d成立的情况是 两边字符都相等的时候，两边都相等了就只需要看中间了，即 dp[r + 1][c - 1] 的值
         * 最后 + 1 的情况是 ：
         * 【 完全可以把中间的字符全部删掉，只保留两头字符的情况，也完全是回文串 】
         *
         * 所以转移方程 如下代码所示
         *
         * =====================================================
         * =====================================================
         * =====================================================
         * =====================================================
         * */
        for (int r = length - 2; r >= 0; r--) {
            for (int c = r + 2; c < length; c++) {
                if (str[r] == str[c]) {

                    dp[r][c] = dp[r][c - 1] + dp[r + 1][c] + 1;

                } else
                    dp[r][c] = dp[r][c - 1] + dp[r + 1][c] - dp[r + 1][c - 1];
            }
        }
        return dp[0][length - 1];
    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            String randomString = LeetCodes.getRandomString(30);
            int my = palindromeWays(randomString);
            int right = way1(randomString);
            if (right != my)
                System.out.println("FUCK");
        }
    }

    public static int way1(String str) {
        char[] s = str.toCharArray();
        int len = s.length;
        int[][] dp = new int[len + 1][len + 1];
        for (int i = 0; i <= len; i++) {
            dp[i][i] = 1;
        }
        // dp[i][j]，在空串不算回文串的情况下，求str[i..j]有多少不同的回文子序列
        // index -> dp
        // str[index-1]
        // dp 1 str 0
        // dp 2 str 1
        for (int subLen = 2; subLen <= len; subLen++) {
            for (int l = 1; l <= len - subLen + 1; l++) {
                int r = l + subLen - 1;
                dp[l][r] += dp[l + 1][r];
                dp[l][r] += dp[l][r - 1];
                if (s[l - 1] == s[r - 1])
                    dp[l][r] += 1;
                else
                    dp[l][r] -= dp[l + 1][r - 1];
            }
        }
        return dp[1][len];
    }

}
