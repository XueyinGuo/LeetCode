package com.szu.training01.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      最长公共字串
 *
 * @Date 2021/4/14 12:56
 */

public class LongestCommonSubString {

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "tcde";
        System.out.println(getLongestSubsequence(str1, str2));
        System.out.println(getLongestSubsequenceCompressSpace(str1, str2));

    }

    /*
     * 空间压缩版的动态规划，利用了有限的几个变量，而不用申请整个动态规划数组（矩阵）
     *          0    1    2    3    4
     *          a    b    c    d    e
     *    0 t   ↓            <----【!】start
     *    1 c
     *    2 d
     *    3 e
     * 到的每个位置 都直接往右下滑动， row++  &  col++
     * 计算最大长度
     * */
    public static String getLongestSubsequenceCompressSpace(String s1, String s2) {
        if (s1 == null || s1.equals("") || s2 == null || s2.equals(""))
            return "";
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int row = 0;
        int col = str2.length - 1;
        int len = 0;
        int max = 0;
        int end = 0;
        while ( row < str1.length ){
            int r = row;
            int c = col;
            while (r < str1.length && c < str2.length){

                if (str1[r] == str2[c]){
                    len++;
                    if (len > max){
                        max = len;
                        end = r;
                    }
                }
                else
                    len = 0;
                r++;
                c++;
            }
            if (col > 0)
                col--;
            else
                row++;
        }
        return s1.substring(end - max + 1 ,end + 1);
    }

    public static String getLongestSubsequence(String s1, String s2) {
        if (s1 == null || s1.equals("") || s2 == null || s2.equals(""))
            return "";
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int rows = str1.length;
        int cols = str2.length;
        int dp[][] = new int[rows][cols];
        int max = 0;
        int end = 0;
        /*
         * 用 str1 当行
         * str2 当列
         * 整个dp矩阵的意思就是 ： 以 str1[i] str2[j]两个位置结尾的字串的最长长度
         * 如果 两个字符不相等 直接赋值 0
         * 如果相等 看看 str1[i-1] str2[j-1] 的长度 + 1
         * */
        for (int r = 0; r < rows; r++) {
            dp[r][0] = str1[r] == str2[0] ? 1 : 0;
        }
        for (int c = 0; c < cols; c++) {
            dp[0][c] = str1[0] == str2[c] ? 1 : 0;
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (str1[r] != str2[c])
                    dp[r][c] = 0;
                else {

                    dp[r][c] = dp[r-1][c-1] + 1;
                    if (dp[r][c] > max){
                        max = dp[r][c];
                        end = r;
                    }
                }
            }
        }
        return s1.substring(end - max + 1 ,end + 1);
    }


}
