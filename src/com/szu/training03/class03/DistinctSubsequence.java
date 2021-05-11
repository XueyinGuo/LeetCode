package com.szu.training03.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/5/11 16:16
 */

import java.util.HashSet;

public class DistinctSubsequence {

    public static void main(String[] args) {
        String s = "aaab";
        int violence = violence(s);
        System.out.println(violence);
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
