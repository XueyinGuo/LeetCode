package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 940. 不同的子序列 II
给定一个字符串 S，计算 S 的不同非空子序列的个数。

因为结果可能很大，所以返回答案模 10^9 + 7.
 *
 * @Date 2021/5/11 16:16
 */

import java.util.HashSet;

public class L940_DistinctSubsequence {

    public static void main(String[] args) {
        String s =
                "zchmliaqdgvwncfatcfivphddpzjkgyygueikthqzyeeiebczqbqhdytkoawkehkbizdmcnilcjjlpoeoqqoqpswtqdpvszfaksn";
//        int violence = violence(s);
        int dp = dp(s);
//        System.out.println(violence);
        System.out.println(dp);
    }

    private static int dp(String s) {
        if (s == null || s.length() == 0)
            return 0;
        char[] str = s.toCharArray();
        int[] map = new int[26];
        map[str[0] - 'a'] = 1;
        int all = 1;
        for (int i = 1; i < str.length; i++) {
//            int index = str[i] - 'a';
//            if (map[index] == 0) {
//                int curAll = all * 2 + 1;
//                map[index] = curAll - all;
//                all = curAll;
//            } else {
//
//                if (str[i] != str[i - 1]) {
//                    int curAll = all * 2;
//                    map[index] = curAll - all;
//                    all = curAll;
//                } else {
//                    int curAll = all * 2 - map[index];
//                    map[index] = curAll - all;
//                    all = curAll;
//                }
//            }
            /*
             * 经典 二十八
             * */
            int index = str[i] - 'a';
            int add = (all + 1 - map[index]);
            if (add < 0)
                add += 1000000007;
            all += add;
            map[index] += add;
            map[index] %= 1000000007;
            all %= 1000000007;
        }
        return all % 1000000007;
    }

    /*
     * 暴力尝试
     * */
    private static int violence(String s) {
        if (s == null || s.length() == 0)
            return 0;
        char[] str = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        HashSet<String> set = new HashSet<>();
        set.add(""); /* 空串不算答案！！ */
        return violence(str, 0, sb, set);
    }

    private static int violence(char[] str, int index, StringBuffer buffer, HashSet<String> set) {
        String toString = buffer.toString();
        int num = 0;
        if (!set.contains(toString)) {
            set.add(toString);
            num++;
        }
        for (int i = index; i < str.length; i++) {
            buffer.append(str[i]);
            num += violence(str, i + 1, buffer, set);
            buffer.deleteCharAt(buffer.length() - 1);
            num += violence(str, i + 1, buffer, set);
        }
        return num;
    }


}
