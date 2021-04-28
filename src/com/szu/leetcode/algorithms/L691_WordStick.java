package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 691. 贴纸拼词
    我们给出了 N 种不同类型的贴纸。每个贴纸上都有一个小写的英文单词。

    你希望从自己的贴纸集合中裁剪单个字母并重新排列它们，从而拼写出给定的目标字符串 target。

    如果你愿意的话，你可以不止一次地使用每一张贴纸，而且每一张贴纸的数量都是无限的。

    拼出目标 target 所需的最小贴纸数量是多少？如果任务不可能，则返回 -1。
 *
 * @Date 2021/4/27 16:27
 */

import java.util.HashMap;
import java.util.HashSet;

public class L691_WordStick {

    public int minStickers(String[] stickers, String target) {
        if(target  == null || target.length() == 0)
            return 0;
        int map[][] = new int[stickers.length][26];
        HashSet<Character> set = new HashSet<>();
        for (int t = 0; t < stickers.length; t++) {
            char[] chars = stickers[t].toCharArray();
            for (int i = 0; i < chars.length; i++) {
                map[t][chars[i]-'a']++;
                set.add(chars[i]);
            }
        }
        char[] str = target.toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (!set.contains(str[i]))
                return -1;
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return getMinStickers(map, target, dp);
    }

    private int getMinStickers(int[][] map, String s, HashMap<String, Integer> dp ) {
        Integer val = dp.get(s);
        if (val != null)
            return val;

        char[] str = s.toCharArray();
        int[] target = new int[26];
        for (int i = 0; i < str.length; i++) {
            target[str[i]-'a']++;
        }
        int min = Integer.MAX_VALUE;
        /*
         * 每次递归都从头开始枚举
         * */
        for (int i = 0; i < map.length; i++){
            // 每次在开始删除字符之前，都查看一下当前这个贴纸 包不包含我当前字符串的第一个字符
            int firstCharIndex = str[0] - 'a';
            // 如果包含当前字符串的第一个字符，我才进行删除这个 字符！！！！！！！！，否则会爆栈溢出（因为 字符串始终没有改变过，无限递归下去了）
            if (map[i][firstCharIndex] > 0){
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < 26; j++) {
                    /* 当前字符串有多少个这个字符  -  贴纸有多少个 */
                    int afterDelete = target[j] - map[i][j];
                    int rest = afterDelete > 0 ? afterDelete : 0;
                    /* 构造删除这个贴纸之后的字符串 */
                    for (int k = 0; k < rest; k++)
                        sb.append((char)(j+'a'));
                }
                /*
                 * 构造好应该传入下一层的字符串之后，开始进行下一层递归
                 *
                 * 下一层继续拿有下一层字符串第一个字符的贴纸，继续这个过程，最终字符串被删空，
                 * 返回的是下一层的使用的张数，加上我的这一层使用了一张！
                 *
                 *
                 * 然后换下一张贴纸，包含当前字符串第一个字符的贴纸 继续尝试！
                 * */
                int next = getMinStickers(map, sb.toString(), dp);
                if (next != Integer.MAX_VALUE)
                    next += 1;
                if (next < min)
                    min = next;
            }

        }
        /* 这一轮尝试完毕，放入啥缓存中，如果继续遇到相同的字符串，不在暴力展开 */
        dp.put(s, min);
        return min;

    }

    public static void main(String[] args) {
        L691_WordStick test = new L691_WordStick();
        String[] stickers = {"with", "example", "science"};
        String target = "thehat";
        int i = test.minStickers(stickers, target);
    }

}
