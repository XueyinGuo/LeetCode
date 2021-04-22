package com.szu.training.class07;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个全是小写字母的字符串 str,删除多余字符，是的每种字符只保留一个，并让最终结果字符串的字典序最小
 *
 * @Date 2021/4/22 22:21
 */

import com.szu.leetcode.utils.LeetCodes;

public class RemoveDuplicateLettersLessLexi {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            String s = LeetCodes.getRandomString(20);
            String right = right(s);
            String my = myRemove(s);
            if (!my.equals(right)) {
                System.out.println("FUCK");
            }
        }
    }

    private static String myRemove(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int length = s.length();
        /* 记录词频， 每个字母出现的次数 */
        int map[] = new int[26];
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            map[c - 'a']++;
        }
        /*
        * 找到第一个 划过此处字符之后，后边再也没有这个字符的时候停止
        * c  b  a  b  c  b  c
        *       ↑
        * index = 2
        * */
        int index = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map[c-'a'] == 1){
                index = i;
                break;
            }
            map[c-'a']--;
        }
        /*
        * 找到 0----index 里边 字典序最小的字符，并记录这个字符的位置
        * */
        int minIndex = 0;
        char minChar = 'z';
        for (int i = 0; i <= index; i++) {
            char c = s.charAt(i);
            if(c < minChar){
                minIndex = i;
                minChar = c;
            }
        }
        /* 返回 minChar + 这个字符之后截取的字符串替换掉 minChar 这个字符的结果 */
        return minChar + myRemove( s.substring(minIndex + 1).replaceAll(String.valueOf(minChar), "") );
    }


    // 在str中，每种字符都要保留一个，让最后的结果，字典序最小 ，并返回
    public static String right(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        int[] map = new int[256];
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i)]++;
        }
        int minACSIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            minACSIndex = str.charAt(minACSIndex) > str.charAt(i) ? i : minACSIndex;
            if (--map[str.charAt(i)] == 0) {
                break;
            }
        }
        return String.valueOf(str.charAt(minACSIndex)) + right(
                str.substring(minACSIndex + 1).replaceAll(String.valueOf(str.charAt(minACSIndex)), ""));
    }

}
