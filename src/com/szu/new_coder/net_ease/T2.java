package com.szu.new_coder.net_ease;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/5/8 14:56
 */

import java.util.*;

public class T2 {

    public String getMostStr(String s0, String s1, char[] cList) {
        // write code here

        char[] str1 = s0.toCharArray();
        char[] str2 = s1.toCharArray();

        char[] big = str1.length > str2.length ? str1 : str2;
        char[] small = big == str1 ? str2 : str1;

        int rows = big.length;
        int cols = small.length;
        int[][] dp = new int[rows][cols];

        boolean[] map = new boolean[256];
        for (int i = 0; i < cList.length; i++) {
            map[cList[i]] = true;
        }

        if (str1[0] == str2[0] || map[str1[0]] || map[str2[0]])
            dp[0][0] = 1;

        for (int c = 1; c < cols; c++) {
            dp[0][c] = ((str1[0] == str2[c]) || dp[0][c - 1] == 1 || map[str2[c]]) ? 1 : 0;
        }

        for (int r = 1; r < rows; r++) {
            dp[r][0] = ((str1[r] == str2[0] || dp[r - 1][0] == 1 || map[str1[r]])) ? 1 : 0;
        }
        int end = 0;
        int maxLen = 0;
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                int l1 = dp[r - 1][c - 1];
                int l2 = dp[r][c - 1];
                int l3 = dp[r - 1][c];
                int l4 = 0;
                if (str1[r] == str2[c] || map[str1[r]] || map[str2[c]])
                    l4 = l1 + 1;

                dp[r][c] = Math.max(Math.max(l1, l2), Math.max(l3, l4));
                if (dp[r][c] > maxLen){
                    maxLen =
                    end = c;
                }
            }
        }
        String shorter = String.valueOf(small, end, maxLen);
        boolean[] otherMap = new boolean[256];
        for (int i = end; i < end + maxLen - 1; i++) {
            otherMap[small[i]] = true;
        }
        int maxmaxLen = 0;
        String res = "";
        for (int i = 0; i < rows; i++) {
            if (big[i] == small[end]) {
                int j = i;
                while (j < big.length) {
                    if (otherMap[big[j]] || map[big[j]])
                        j++;
                }
                if (j - i + 1 > maxmaxLen) {
                    maxmaxLen = j - i + 1;
                    res = String.valueOf(big, i, maxmaxLen);
                }
            }
        }

        if (res.length() > shorter.length())
            return res;

        return shorter;
    }


    public static void main(String[] args) {
        String s0 = "zxab%c%%", s1 = "abc%";
        char[] cList = {'%', '#'};
        T2 t2 = new T2();
        System.out.println(t2.getMostStr(s0, s1, cList));
    }
}
