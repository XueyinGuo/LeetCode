package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 131. 分割回文串
    给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。

    回文串 是正着读和反着读都一样的字符串。
 *
 * @Date 2021/4/26 18:14
 */

import java.util.ArrayList;
import java.util.List;

public class L131_GenerateAllPalindrome {

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0)
            return new ArrayList<>();
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
        List<String> path = new ArrayList<>();
        getAllPalindrome(str, isP, 0, path);
        return res;
    }

    private void getAllPalindrome(char[] str, boolean[][] isP, int start, List<String> path) {

        if (start == str.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < str.length; i++) {
            if (isP[start][i]) {
                /*
                * 只有在 当前的开始 到 现在的 i 位置 为 回文串的时候，才添加到 path 中，也要记得之后恢复现场
                * */
                path.add(new String(str, start, i - start + 1));
                getAllPalindrome(str, isP, i + 1, path);
                path.remove(path.size() - 1);
            }
        }

    }

}
