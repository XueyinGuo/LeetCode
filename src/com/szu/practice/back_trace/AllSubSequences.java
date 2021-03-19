package com.szu.practice.back_trace;

import java.util.ArrayList;
import java.util.List;

/*
* 没找到LeetCode原题，代码练习而已
*
* 输入一个字符串，找到所有的字序列
* */
public class AllSubSequences {

    public static void main(String[] args) {
        String s = "acccc";
        List<String> res1 = findSubSequences(s);
        System.out.println(res1);
    }

    private static List<String> findSubSequences(String s) {
        // 方法接收到变量字符串之后，先
        char[] chars = s.toCharArray();
        List<String> stringList = new ArrayList<>();
        String subSequence = "";
        doFindSubSequences(chars,0 , stringList, subSequence);
        return stringList;
    }

    private static void doFindSubSequences(char[] chars, int index, List<String> stringList, String subSequence) {
        // 已经找到最后一个字母的时候，把当前结果加入到结果集中
        if (index == chars.length){
            stringList.add(subSequence);
            return;
        }
        // 子序列都是按照源字符串中字母的顺序的，否则不能算是子序列
        // 每次递归的时候，都有可能要当前的这个字母，也可能不要这个字母
        doFindSubSequences(chars, index + 1, stringList, subSequence);
        doFindSubSequences(chars, index + 1, stringList, subSequence + chars[index]);
    }
}
