package com.szu.training01.class06;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * TODO 有点迷糊！！！！需要重新做几遍这道题
 * 一个字符串 怎么 分割 才能使分割出的回文串 最少
 *
 * @Date 2021/4/21 18:52
 */

public class PalindromeMinParts {

    public static void main(String[] args) {
        String test = "aba12321412321TabaKFKafa";
//        String test = "aaaa";
        System.out.println(palindromeMinParts(test));
//        System.out.println(palindromeMinPartsViolence(test));
    }



    private static int palindromeMinParts(String s) {
        if (s == null || s.length() == 0)
            return 0;
        if (s.length() == 1)
            return 1;

        char[] str = s.toCharArray();
        int length = str.length;
        boolean[][] isPalindrome = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 1; i < length; i++) {
            isPalindrome[i - 1][i] = str[i - 1] == str[i];
        }
        int ans = 0;
        for (int row = length - 3; row >= 0; row--) {
            for (int col = row + 2; col < length; col++) {
                if (str[row] == str[col])
                    isPalindrome[row][col] = isPalindrome[row + 1][col - 1];
            }
        }
        int dp[] = new int[length + 1];
        for (int i = 0; i < length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[length] = 0;
        for (int start = length - 1; start >= 0; start--) {
            for (int end = start; end < length; end++) {
                if (isPalindrome[start][end])
                    dp[start] = Math.min( dp[start], dp[end + 1] + 1 );
            }
        }

        return dp[0];
    }

}
