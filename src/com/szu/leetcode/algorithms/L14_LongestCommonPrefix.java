package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/14 0:29
 */

import java.util.ArrayList;
import java.util.List;

public class L14_LongestCommonPrefix {

    public String longestCommonPrefix(String[] strings) {

        if (strings == null || strings.length == 0)
            return "";
        if (strings.length == 1)
            return strings[0];
        char[] first = strings[0].toCharArray();
        int end = first.length;
        for (int i = 1; i < strings.length; i++) {
            char[] cur = strings[i].toCharArray();
            for (int index = 0; index <= end; index++) {
                if (index == cur.length || index == first.length || first[index] != cur[index]){
                    end = index - 1;
                    break;
                }
            }
            if (end == -1)
                return "";

        }
        return strings[0].substring(0, end + 1);
    }

    public static void main(String[] args) {
        String[] strings = {
                "","b"};
        System.out.println(new L14_LongestCommonPrefix().longestCommonPrefix(strings));
    }
}
