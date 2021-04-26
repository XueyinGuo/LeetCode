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

    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.length() == 0)
            return true;
        if (s2 == null || s2.length() == 0 || s2.length() < s1.length())
            return false;
        char[] str1 = s1.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> otherMap = new HashMap<>();
        for (int i = 0; i < str1.length; i++) {
            Integer times = map.get(str1[i]);
            if (times != null)
                map.put(str1[i], times + 1);
            else
                map.put(str1[i], 1);
        }

        char[] str2 = s2.toCharArray();

        int i2 = 0;
        while (i2 < str2.length) {
            int helpIndex = i2;
            while (helpIndex < str2.length && map.containsKey(str2[helpIndex])) {
                Integer times = map.get(str2[helpIndex]);
                Integer movedTimes = otherMap.get(str2[helpIndex]);
                if (times == 1) {
                    map.remove(str2[helpIndex]);
                } else
                    map.put(str2[helpIndex], times - 1);
                if (movedTimes == null)
                    otherMap.put(str2[helpIndex], 1);
                else
                    otherMap.put(str2[helpIndex], movedTimes + 1);
                helpIndex++;
            }
            if (map.isEmpty())
                return true;

            moveBack(otherMap, map);
            i2++;
        }
        return false;
    }

    private void moveBack(HashMap<Character, Integer> otherMap, HashMap<Character, Integer> map) {

        Iterator<Map.Entry<Character, Integer>> iterator = otherMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> next = iterator.next();
            Character key = next.getKey();
            Integer movedTimes = next.getValue();

            Integer times = map.get(key);
            map.put(key, times == null ? movedTimes : times + movedTimes);

        }
        otherMap.clear();

    }


    public static void main(String[] args) {

        String s1 = "adc";
        String s2 = "dcda";
        L567_CheckInclusion test = new L567_CheckInclusion();
        boolean b = test.checkInclusion(s1, s2);
        System.out.println(b);
    }
}
