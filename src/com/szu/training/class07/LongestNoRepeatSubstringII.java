package com.szu.training.class07;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 最长无重复字串
 *
 * @Date 2021/4/22 14:06
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.HashMap;

public class LongestNoRepeatSubstringII {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            String str = LeetCodes.getRandomString(30);
            String max1 = maxUnique(str);
            String right = maxUniqueString(str);
            if (!right.equals(max1))
                System.out.println("FUCK");
        }
//        String str = "abcdefgbcdefghijkaaasdafweqsdsdd";

//        System.out.println(max1);
    }

    public static String maxUnique(String s) {
        if (s == null || s.length() == 0)
            return "";
        if (s.length() == 1)
            return s;
        char[] str = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int[] dp = new int[str.length];
        /* 第一个字符位置 的 最长无重复字串肯定为 1 */
        dp[0] = 1;
        map.put(str[0], 0);
        int endIndex = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < str.length; i++) {
            /* 获取上个字符出现的位置 */
            Integer lastIndex = map.get(str[i]);
            /* 为空 则没出现过 */
            if (lastIndex != null)
                dp[i] = Math.min(i - lastIndex, dp[i - 1] + 1); // 出现过， 前一个位置长度 + 1， 与 这个字符出现的位置 到现在的距离 的最小值
            else
                dp[i] = dp[i - 1] + 1; // 没出现过，直接就是前一个位置的长度 + 1
            // 更新最大长度
            if (dp[i] > max) {
                endIndex = i;
                max = dp[i];
            }
            map.put(str[i], i);

        }
        return s.substring(endIndex - max + 1, endIndex + 1);
    }


    /*
     * 大神
     * */
    public static String maxUniqueString(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = -1;
        int pre = -1;
        int cur = 0;
        int end = -1;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            if (cur > len) {
                len = cur;
                end = i;
            }
            map[chas[i]] = i;
        }
        return str.substring(end - len + 1, end + 1);
    }

}
