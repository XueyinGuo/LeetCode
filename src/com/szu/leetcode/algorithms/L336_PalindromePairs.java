package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 336. 回文对
给定一组 互不相同 的单词， 找出所有 不同 的索引对 (i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。

示例 1：

输入：words = ["abcd","dcba","lls","s","sssll"]
输出：[[0,1],[1,0],[3,2],[2,4]]
解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 *
 * @Date 2021/5/12 20:21
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class L336_PalindromePairs {


    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0)
            return new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++)
            map.put(words[i], i);

        List<List<Integer>> res = new ArrayList<>();
        // HashSet<String> set = new HashSet<>();
        for (int k = 0; k < words.length; k++) {
            String word = words[k];
            /* 获取一个逆序的字符串 */
            String reverse = getReverseString(word);
            /*
            * 如果map中存在逆序串，则直接把逆序串添加在后边这种情况也能组成回文
            *
            * 其实放在前边也能组成回文串，但是这个工作交给下次遇到逆序串的时候做吧
            * */
            Integer reverseIndex = map.get(reverse);
//            Integer nullIndex = map.get("");
            if (reverseIndex != null && reverseIndex != k) {
                addRes(res, k, reverseIndex);
                // addRes(res, reverseIndex, k);
                // set.add(word);
                // set.add(reverse);
            }
            // if (nullIndex != null && nullIndex != k){
            //     addRes(res, k, nullIndex);
            // }
            /*
            * 通过马拉车算法获取到，当前字符串从起始位置到哪些位置是回文串，
            * 直接找 回文串位置到结束位置的逆序，插到本字符之前一定能组成回文
            *
            * 比如 abbacd 这个字符串
            *
            * 1. a 是回文串 ，查找 bbacd的逆序串 放到整个字符串之前一定可以组成回文
            * 2. abba 是回文串， 查找 cc 的逆序，放到字符串开头也能组成回文
            * 【直接从 逆序串中切 就好了】
            * */
            List<Integer> pList = manacher(insertDummyChar(word));
            for (Integer pIndex : pList) {
                String key = reverse.substring(0, reverse.length() - pIndex);
                Integer i = map.get(key);
                if (i != null && i != k)
                    addRes(res, i, k);
            }
            /*
            * abbacd 的逆序串中有多少从头开始的回文
            * 也就是字符串 dcabba
            * 只有 d
            * 1. 找到 cabba 的逆序串 也就是 原字符串的 前几位 abbac
            * 放到 逆序串的前边，
            * 其实也就是相当于 abbacd + cabba 也能组成回文串
            * 看能不能在 map 中找到这个字符串
            * */
            List<Integer> reversePlist = manacher(insertDummyChar(reverse));
            for (Integer rpIndex : reversePlist) {
                String key = reverse.substring(rpIndex, word.length());
                Integer i = map.get(key);
                if (i != null && i != k)
                    addRes(res, k, i);
            }
        }
        return res;
    }

    private void addRes(List<List<Integer>> res, int k, int i) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(k);
        list.add(i);
        res.add(list);
    }

    private String getReverseString(String word) {
        char[] str = word.toCharArray();
        int i = 0;
        int j = str.length - 1;
        while (i < j) {
            char tem = str[i];
            str[i++] = str[j];
            str[j--] = tem;

        }
        return String.valueOf(str);
    }

    /*
     * 马拉车算法计算回文半径，并找记录下能一直延伸到开头位置的回文串
     * */
    public List<Integer> manacher(char[] str) {
        int center = -1;
        int rightBorder = -1;
        int[] radius = new int[str.length];
        for (int i = 0; i < str.length; i++) {

            if (rightBorder > i) {
                radius[i] = Math.min(radius[center * 2 - i], rightBorder - i);
            } else {
                radius[i] = 1;
            }

            while (i + radius[i] < str.length && i - radius[i] >= 0) {
                if (str[i + radius[i]] == str[i - radius[i]])
                    radius[i]++;
                else
                    break;
            }
            if (i + radius[i] > rightBorder) {
                rightBorder = i + radius[i];
                center = i;
            }
        }
        /*
         * 记录下所有开头能到达的回文串位置
         * */
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i < radius.length; i++) {
            if (radius[i] - i == 1)
                res.add(i);
        }
        return res;
    }

    private char[] insertDummyChar(String word) {

        char[] str = new char[2 * word.length() + 1];
        str[0] = '#';
        int index = 1;
        for (int i = 0; i < word.length(); i++) {
            str[index++] = word.charAt(i);
            str[index++] = '#';
        }
        return str;
    }


    public static void main(String[] args) {
        String[] s =
                {"a", ""};
        L336_PalindromePairs test = new L336_PalindromePairs();
        test.palindromePairs(s);
    }
}
