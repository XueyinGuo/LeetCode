package com.szu.leetcode.contest;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 5730. 将所有数字用字符替换 显示英文描述

给你一个下标从 0 开始的字符串 s ，它的 偶数 下标处为小写英文字母，奇数 下标处为数字。

定义一个函数 shift(c, x) ，其中 c 是一个字符且 x 是一个数字，函数返回字母表中 c 后面第 x 个字符。

比方说，shift('a', 5) = 'f' 和 shift('x', 0) = 'x' 。
对于每个 奇数 下标 i ，你需要将数字 s[i] 用 shift(s[i-1], s[i]) 替换。

请你替换所有数字以后，将字符串 s 返回。题目 保证 shift(s[i-1], s[i]) 不会超过 'z' 。
 *
 * @Date 2021/5/1 22:33
 */

import java.util.ArrayList;

public class L5730_ReplaceDigits {

    public String replaceDigits(String s) {
        char[] str = s.toCharArray();
        ArrayList<Character> res = new ArrayList<>();
        int i = 1;
        res.add(str[0]);
        while (i < str.length) {

            if (str[i] >= 'a' && str[i] <= 'z') {
                res.add(str[i++]);
            }
            if (i == str.length)
                break;
            int cur = 0;
            int start = i - 1;
            while (i < str.length && str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            }
            res.add(getChar(str[start], cur));

        }
        StringBuffer sb = new StringBuffer();
        for (Character re : res) {
            sb.append(re);
        }
        return sb.toString();
    }

    public char getChar(char c, int k) {
        return (char) (c + k);
    }

    public static void main(String[] args) {
        String s = "a1b2c3d4e";
        L5730_ReplaceDigits l5730 = new L5730_ReplaceDigits();
        System.out.println(l5730.replaceDigits(s));
    }

}
