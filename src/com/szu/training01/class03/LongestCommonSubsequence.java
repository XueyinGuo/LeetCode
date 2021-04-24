package com.szu.training01.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      最长公共子序列
 *
 * @Date 2021/4/14 12:55
 */

public class LongestCommonSubsequence {

    public static int lcsViolence(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return lcsViolence(str1, str2, str1.length - 1, str2.length - 1);
    }

    public static int lcsViolence(char[] str1, char[] str2, int index1, int index2) {
        /*
        * 如果只剩下一个字符了：看看两个相等否，相等返回 1，不等返回 0
        * */
        if (index1 == 0 && index2 == 0)
            return str1[index1] == str2[index2] ? 1 : 0;
        if (index1 == 0){
            /* 如果现在 str1 只剩下一个字符了， str2还剩很多，看看当前剩的这一个 和 其他的剩余有没有相等，有相等的 返回 1 */
            return (( str1[index1] == str2[index2] ) || lcsViolence(str1, str2, index1, index2 - 1) == 1 ) ? 1 : 0;
        }
        /* 同理 str2 */
        if (index2 == 0){
            return (( str1[index1] == str2[index2] ) || lcsViolence(str1, str2, index1 - 1, index2) == 1 ) ? 1 : 0;
        }
        /*
        * 此时，str1 str2 都还剩很多字符
        * 1.子序列与当前两个字符彻底无关
        *   abc
        *   abd   那么扔掉两个字符串的最后一个字符又有什么关系呢？
        *
        * 2.
        * ab
        * abc
        * 那么扔掉 第二个字符串的最后一个字符又有什么关系呢？
        *
        * 3.
        * abc
        * ab
        * 那么扔掉 第一个字符串的最后一个字符又有什么关系呢？
        *
        * 4.
        * 如果两个字符串现在的两个结尾位置相等
        * abc
        * abc
        * 那么就是 都扔掉最后一个字符的长度 +1， 也就是第一种可能性的长度 + 1
        * */
        int l1 = lcsViolence(str1, str2, index1 - 1, index2 - 1);
        int l2 = lcsViolence(str1, str2, index1 - 1, index2);
        int l3 = lcsViolence(str1, str2, index1, index2 - 1);
        int l4 = 0;
        if (str1[index1] == str2[index2])
            l4 = l1 + 1;
        return Math.max( Math.max(l1, l2), Math.max(l3, l4) );
    }

    /*
    * 根据递归改动态规划
    * */
    public static int lcsDp(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int rows = str1.length;
        int cols = str2.length;

        int[][] dp = new int[rows][cols];
        if (str1[0] == str2[0])
            dp[0][0] = 1;
        for (int c = 1; c < cols; c++) {
            dp[0][c] = (( str1[0] == str2[c] ) || dp[0][ c - 1] == 1 ) ? 1 : 0;
        }
        for (int r = 1; r < rows; r++) {
            dp[r][0] = (( str1[r] == str2[0] ) || dp[r- 1][ 0] == 1 ) ? 1 : 0;
        }
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                int l1 = dp[ r - 1] [c - 1];
                int l2 = dp[ r - 1] [c ];
                int l3 = dp[ r ] [c - 1];
                int l4 = 0;
                if (str1[r] == str2[c])
                    l4 = l1 + 1;
                dp[r][c] = Math.max( Math.max(l1, l2), Math.max(l3, l4) );
            }
        }
        return dp[rows-1][cols-1];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence test = new LongestCommonSubsequence();
        String s1 = "abc23456";
        String s2 = "tabcyui23qw456";
        int violence = lcsViolence(s1, s2);
        int dp = lcsDp(s1, s2);
        System.out.println(violence);
        System.out.println(dp);
    }
}
