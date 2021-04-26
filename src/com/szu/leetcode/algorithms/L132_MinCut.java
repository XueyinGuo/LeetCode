package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 132. 分割回文串 II
    给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。

    返回符合要求的 最少分割次数 。
 *
 * @Date 2021/4/26 16:07
 */

import java.util.Arrays;

public class L132_MinCut {

    public int minCutRight(String s) {
        if (s == null || s.length() == 0)
            return 0;
        char[] str = s.toCharArray();
        int length = str.length;
        /*
        * 生成是否是回文子串 boolean DP表 的标准代码
        * */
        boolean[][] isP = new boolean[length][length];
        for (int r = 0; r < length; r++) {
            isP[r][r] = true;
        }
        for (int c = 1; c < length; c++) {
            if (str[c] == str[c - 1])
                isP[c - 1][c] = true;
        }


        for (int r = length - 2; r >= 0; r--) {
            for (int c = r + 2; c < length; c++) {
                if (str[r] == str[c])
                    isP[r][c] = isP[r + 1][c - 1];
            }
        }

        // f(i) 代表考虑下标为 i 的字符为结尾的最小分割次数
        int[] f = new int[length];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < length; i++) {
            // 如果 [0,j] 这一段直接构成回文，则无须分割
            if (isP[0][i]){
                f[i] = 0;
                continue;
            }
            // 如果无法直接构成回文
            // 先试试独立使用一次分割次数
            f[i] = f[i-1] + 1;
            for (int start = 1; start < i; start++) {
                // 第 i 个字符本身不独立使用分割次数，
                // 与前边的 从 start 开始构成 回文
                // 所以需要 从 start - 1 的那个切割次数 + 1
                if (isP[start][i] && f[start - 1] + 1 < f[i]){
                    f[i] = f[start - 1] + 1;
                }
            }
        }
        return f[length - 1];
    }

    /*
    * ==========
    * ==========
    * 下边这部分是错的解法
    * ==========
    * ==========
    * */
    public int minCut(String s) {
        if (s == null || s.length() == 0)
            return 0;
        char[] str = s.toCharArray();
        int headCut = getMinCut(str);
        /*
        * 因为有的字符串最长回文子串有可能是从结尾开始算的，
        * 所以逆序一次，计算一次逆序的DP，争取找到最大的回文子串长度，
        * 但是因为有可能【第一最长是在结束，第二最长就是从开头了，所以逆序也是不对，很蛋疼】
        * */
        char[] reverse = reverseString(str);
        int reverseCut = getMinCut(reverse);
        return Math.min(reverseCut, headCut);
    }

    private char[] reverseString(char[] str) {

        char[] reverse = new char[str.length];
        int i = 0;
        int j = str.length - 1;
        while (i < str.length) {
            reverse[i++] = str[j--];
        }
        return reverse;
    }

    private int getMinCut(char[] str) {

        int length = str.length;
        boolean[][] isP = new boolean[length][length];
        for (int r = 0; r < length; r++) {
            isP[r][r] = true;
        }
        for (int c = 1; c < length; c++) {
            if (str[c] == str[c - 1])
                isP[c - 1][c] = true;
        }

        for (int r = length - 2; r >= 0; r--) {
            for (int c = r + 2; c < length; c++) {
                if (str[r] == str[c])
                    isP[r][c] = isP[r + 1][c - 1];
            }
        }

        /*
        * 通过反向正向两个字符串，生成两个 dp 表，
        * 刚开始在第一行 找到一个最长的 回文串之后就让 行列 蹦到 回文串结束那一行那一列 都 + 1 的位置开始继续找
        * */
        int pIndex = 0;
        int r = 0;
        int c = 0;
        int cut = 0;
        while (r < length && c < length) {
            int index = c;
            while (index < length) {
                if (isP[r][index])
                    pIndex = index;
                index++;
            }
            r = pIndex + 1;
            c = pIndex + 1;
            if (r >= length)
                break;
            cut++;
        }
        return cut;
    }


    public static void main(String[] args) {
        L132_MinCut test = new L132_MinCut();
        int aab = test.minCutRight("abayuyioipop");
        System.out.println(aab);
    }
}
