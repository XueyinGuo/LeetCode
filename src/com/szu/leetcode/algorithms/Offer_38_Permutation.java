package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 剑指 Offer 38. 字符串的排列
输入一个字符串，打印出该字符串中字符的所有排列。



你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * @Date 2021/5/9 17:54
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Offer_38_Permutation {

    public String[] permutation(String s) {
        if (s == null || s.length() == 0)
            return new String[]{};

        HashSet<String> set = new HashSet<>();
        HashSet<Integer> indexSet = new HashSet<>();
        List<String> resList = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        getPermutation(s.toCharArray(), set, indexSet, resList, sb);
        String[] res = new String[resList.size()];
        int i = 0;
        for (String s1 : resList) {
            res[i++] = s1;
        }
        return res;
    }

    private void getPermutation(char[] str, HashSet<String> set, HashSet<Integer> indexSet, List<String> resList, StringBuffer sb) {

        if (indexSet.size() == str.length) {
            String res = sb.toString();
            if (!set.contains(res)) {
                set.add(res);
                resList.add(res);
            }
            return;
        }

        for (int i = 0; i < str.length; i++) {
            if (!indexSet.contains(i)) {
                indexSet.add(i);
                sb.append(str[i]);
                getPermutation(str, set, indexSet, resList, sb);
                sb.deleteCharAt(sb.length() - 1);
                indexSet.remove(i);
            }
        }
    }

    /*
    * TODO 效率比较高的版本
    * */
    char[] ch;
    List<String> ans = new ArrayList<>();
    int n;

    public String[] permutation2(String s) {
        ch = s.toCharArray();
        n = s.length();
        dfs(0);
        return ans.toArray(new String[ans.size()]);
    }

    void dfs(int u) {
        if (u == n) {
            ans.add(new String(ch, 0, n));
            return;
        }
        for (int i = u; i < n; i++) {
            if (i > u && check(u, i)) continue;
            swap(u, i);
            dfs(u + 1);
            swap(u, i);
        }
    }

    void swap(int i, int j) {
        char t = ch[i];
        ch[i] = ch[j];
        ch[j] = t;
    }

    boolean check(int st, int en) {
        for (int i = st; i < en; i++) {
            if (ch[i] == ch[en]) return true;
        }
        return false;
    }




    public static void main(String[] args) {
        String s = "aab";
        Offer_38_Permutation test = new Offer_38_Permutation();

        test.permutation(s);
    }
}
