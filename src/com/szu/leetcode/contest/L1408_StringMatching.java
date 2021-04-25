package com.szu.leetcode.contest;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给你一个字符串数组 words ，数组中的每个字符串都可以看作是一个单词。请你按 任意 顺序返回 words 中是其他单词的子字符串的所有单词。

    如果你可以删除 words[j] 最左侧和/或最右侧的若干字符得到 word[i] ，那么字符串 words[i] 就是 words[j] 的一个子字符串。



    示例 1：

    输入：words = ["mass","as","hero","superhero"]
    输出：["as","hero"]
    解释："as" 是 "mass" 的子字符串，"hero" 是 "superhero" 的子字符串。
    ["hero","as"] 也是有效的答案。
 *
 * @Date 2021/4/25 11:32
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class L1408_StringMatching {
    public List<String> stringMatching(String[] words) {
        ArrayList<String> res = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (j == i)
                    continue;
                int index = words[i].indexOf(words[j]);
                if (index != -1 && !set.contains(words[j])){
                    set.add(words[j]);
                    res.add(words[j]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"mass","as","hero","superhero"};
        L1408_StringMatching test = new L1408_StringMatching();
        List<String> stringList = test.stringMatching(words);
        System.out.println();
    }
}
