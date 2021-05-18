package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      3. 无重复字符的最长子串
        给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *         https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @Date 2021/2/17 20:25
 */

public class L3_LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        // ASCII 码表长度的数组
        int[] mem = new int[128];
        int max = 0;
        char[] chars = s.toCharArray();

        // 从第一个开始往后找
        for (int i = 0; i < chars.length; i++) {
            int curLen = 0;
            //
            for (int j = i; j < chars.length; j++) {
                // 下标 chars[j] - 0 的值是否为 0
                // 不为0则代表这个字母已经出现在了刚刚的串中
                // 则进入else 跳出本次循环

                if (mem[chars[j] - 0] == 0) {
                    mem[chars[j] - 0] = 1;
                    curLen++;
                } else {
                    break;
                }
            }

            max = Math.max(max, curLen);
            mem = new int[128];
        }
        return max;
    }


    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0)
            return 0;
        char[] str = s.toCharArray();
        int[] map = new int[256];

        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        int max = 0;
        int pre = -1;
        int cur = 0;
        for (int i = 0; i < str.length; i++) {
            pre = Math.max(pre, map[str[i]]);
            cur = i - pre;
            max = Math.max(cur, max);
            map[str[i]] = i;
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(new L3_LengthOfLongestSubstring().lengthOfLongestSubstring2(s));
    }

}
