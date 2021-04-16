package com.szu.training.class05;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定两个字符串 s1 和 s2，问 s2 最少删除多少字符可以成为 s1 的字串
 * 比如 s1 = "abcde"  ,  s2 = "axbc"
 * 返回 1， 删掉 x 即可
 *
 * TODO
 *
 * @Date 2021/4/16 13:33
 */

public class EditCostDeleteOnly {

    public static void main(String[] args) {
        String s1 = "5dsfgs";
        String s2 = "5dsfgs9";

        int violence = minCostViolence(s1, s2);
        System.out.println(violence);
//        int dp = minCostDp(s1, s2);
//        int awesome = minCostAwesome(s1, s2);

    }

    private static int minCostViolence(String s1, String s2) {
        /*
        * 本题目中 s2 通过删除的方式， 变成 s1
        *
        * 当 s2 为空的时候，不用动，空串是任何串的字串
        *
        * 当 s1 为空的时候，需要删掉所有字符才可以
        * */
        if (s1 == null || s1.length() == 0)
            return s2.length();
        if (s2 == null || s2.length() == 0)
            return 0;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return minCostViolence(str1, str2, str1.length - 1, str2.length-1);
    }

    private static int minCostViolence(char[] str1, char[] str2, int index1, int index2) {
        return 0;
    }

}
