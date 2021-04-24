package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * 
 * 给定两个单词word1和word2，找到使得word1和word2相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。

    示例：

    输入: "sea", "eat"
    输出: 2
    解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @Date 2021/4/24 21:12
 */

import java.util.HashSet;

public class L583_MinDistanceOfTwoWords {

    /*
    * 最长公共子序列就可以解决了
    *
    * 解题思路：
    * 两个字符串既然要变成一样的字符串，那么也需要顺序肯定一样，
    * 这样就联想到了 最长公共子序列问题
    *
    * 找到最长公共长度之后，两个字符串的长度减掉最长公共子序列长度即为答案
    * */
    public int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() == 0)
            return word2.length();
        if (word2 == null || word2.length() == 0)
            return word1.length();

        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        int rows = str1.length;
        int cols = str2.length;
        int dp[][] = new int[rows][cols];

        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int c = 1; c < cols; c++) {
            if (str1[0] == str2[c] || dp[0][c-1] != 0)
                dp[0][c] = 1;

        }
        for (int r = 1; r < rows; r++) {
            if (str1[r] == str2[0] || dp[r-1][0] != 0)
                dp[r][0] = 1;

        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {

                int p1 = dp[r - 1][c];
                int p2 = dp[r][c - 1];
                int p3 = dp[r - 1][c - 1];
                int p4 = 0;
                if (str1[r] == str2[c])
                    p4 = p3 + 1;
                dp[r][c] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }

        return rows - dp[rows - 1][cols - 1] + cols - dp[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        String word1 = "a";
        String word2 = "ab";
        L583_MinDistanceOfTwoWords test = new L583_MinDistanceOfTwoWords();
        int i = test.minDistance(word1, word2);
        System.out.println(i);
    }
}


