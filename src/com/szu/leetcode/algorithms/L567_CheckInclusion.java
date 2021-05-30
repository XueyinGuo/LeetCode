package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * TODO 567. 字符串的排列
    给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

    换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 *
 * @Date 2021/4/26 18:24
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class L567_CheckInclusion {


    public static void main(String[] args) {

        String s1 = "adc";
        String s2 = "dcda";
        L567_CheckInclusion test = new L567_CheckInclusion();
        boolean b = test.checkInclusion(s1, s2);
        System.out.println(b);
    }

    public boolean checkInclusion(String s1, String s2) {
        /*
         * 题目的意思就是在问，s1 的排列 是否是 s2的字串
         * */
        int[] map = new int[256];
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        for (int i = 0; i < str1.length; i++)
            map[str1[i]]++;

        int i = -1;
        while (i < str2.length) {
            int j = i + 1;
            int all = str1.length;
            int payMap[] = new int[256];
            while (j < str2.length && map[str2[j]] != 0){
                if (payMap[str2[j]] < map[str2[j]]){
                    payMap[str2[j]]++;
                    all--;
                }else
                    break;

                if (all == 0)
                    return true;

                j++;
            }
            i = j;
        }
        return false;
    }
}
