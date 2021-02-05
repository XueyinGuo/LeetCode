package com.szu.practice;

import java.util.ArrayList;
import java.util.List;

/*
 * 没找到 LeetCode 原题，代码练习而已
 *
 * 输入一个字符串，输出一个字符串的全排列
 * */
public class AllPermutations {


    public static void main(String[] args) {
        String s = "hello world";
        List<String> stringList = findPermutations(s);
//        for (String str : stringList) {
//            System.out.println(str);
//        }
        // 1 2 6 24 120 720 5040 40320 362880 3628800 39916800
        System.out.println(stringList.size());
    }

    private static List<String> findPermutations(String s) {

        if (s == null || s.length() == 0){
            return null;
        }
        char[] chars = s.toCharArray();
        ArrayList<Character> characters = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            characters.add(chars[i]);
        }
        List<String> result = new ArrayList<>();
        // 1. permutation 主函数调用递归的时候是先传入一个空的字符串，
        // 每次递归都 从 ArrayList<Character> 取出一个字母，并放入这个字符串中，
        String permutation = "";
        doFindPermutations(characters, result, permutation);
        return result;
    }


    private static void doFindPermutations(ArrayList<Character> rest, List<String> result, String permutation) {
        // 待到 ArrayList<Character> 所有的字母全部取出了， 一个全排列结果就已经制作好了
        if (rest.isEmpty()){
            result.add(permutation);
            return;
        }
        // 2. 每次从集合中取出一个元素，拼接到 permutation 后边，
        // 而且每次循环结束的时候，都再把这个元素放置回去
        // 从而防止下层递归的时候不会出现缺少字母的情况
        for (int i = 0; i < rest.size(); i++) {
            Character remove = rest.remove(i);
            doFindPermutations(rest, result, permutation + remove);
            rest.add(i, remove);
        }
    }


}