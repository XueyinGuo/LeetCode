package com.szu;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 13:19
 */


import com.szu.leetcode.utils.LeetCodes;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {



        for (int i = 0; i < 10000; i++) {

            String randomString = LeetCodes.getRandomString(30);
            String randomMatch = LeetCodes.getRandomString(6);
            int kmp = KMP(randomString, randomMatch);
            int i1 = randomString.indexOf(randomMatch);
            if (kmp != i1)
                System.out.println("FUCK!");

        }
    }

    private static int KMP(String randomString, String randomMatch) {

        char[] str = randomString.toCharArray();
        char[] match = randomMatch.toCharArray();
        int[] next = getNextArr(match);
        int i = 0;
        int j = 0;
        while (i < str.length && j < match.length){

            if (match[j] == str[i]){
                i++;
                j++;
            }else if (next[j] != -1)
                j = next[j];
            else
                i++;

        }
        return j == match.length ? i - j : -1;
    }

    private static int[] getNextArr(char[] match) {

        if (match.length == 1)
            return new int[]{-1};
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < match.length){

            if (match[i-1] == match[cn]){
                next[i] = cn+1;
                i++;
                cn++;
            }
            else if (cn > 0)
                cn = next[cn];
            else
                next[i++] = 0;
        }
        return next;
    }



}