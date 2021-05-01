package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 647. 回文子串
    给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

    具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * @Date 2021/5/1 17:43
 */

public class L647_PalindromicSubstrings {

    public int countSubstrings(String s) {
        char[] str = s.toCharArray();
        int length = str.length;
        boolean[][] dp = new boolean[length][length];
        int ans = 0;
        /*
        * 范围上的尝试，超级简单
        * */
        for(int i = 0; i<length;i++){
            dp[i][i] = true;
        }
        ans += length;
        for(int i = 0; i<length-1;i++){
            if(str[i] == str[i+1]){
                dp[i][i+1] = true;
                ans++;
            }
        }

        for(int r = length-2; r >= 0; r--){
            for(int c = r+2; c < length; c++){
                if(str[r] == str[c] && dp[r+1][c-1]){
                    dp[r][c] = true;
                    ans++;
                }

            }
        }
        return ans;
    }

}
