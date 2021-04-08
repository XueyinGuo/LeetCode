package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/30 20:23
 */

public class L91_NumberDecoding {
    public static void main(String[] args) {
        String nums = "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
//        int sum = count(nums);
//        int sumdp = countDp(nums.toCharArray());
        long sumdp2 = numberDecoding(nums.toCharArray());
//        System.out.println(sum);
        System.out.println(sumdp2);
    }

    public static long numberDecoding(char[] chars){
        int length = chars.length;
        long dp[] = new long[length + 1];
        dp[length] = 1;
        for (int i = length-1; i >= 0; i--) {
            /*
            * 字符是0的情况下考虑都不考虑
            * */
            if (chars[i] != '0'){
                long sum = dp[i+1];
                /*
                 * 字符不是0， 而且 这个字符与下一个字符的数字拼接起来 < 27
                 * */
                if (i + 1 < chars.length && (chars[i] - '0') * 10 + chars[i + 1] - '0' < 27){
                    sum += dp[i+2];
                }
                dp[i] = sum;
            }
        }
        return dp[0];
    }


    private static int count(String nums) {

        if (nums == null || nums.length() == 0){
            return 0;
        }
        char[] chars = nums.toCharArray();
        return doCount(chars, 0);
    }

    private static int doCount(char[] chars, int index) {
        if (index == chars.length){
            return 1;
        }
        if (chars[index] == '0'){
            return 0;
        }
        // 只用当前一个数字
        int sum = doCount(chars, index + 1);
        // 用当前加后边一个,但是需要判断当前两位数字加起来是否 大于 26
        if (index + 1 < chars.length && (chars[index] - '0')*10 + chars[index + 1] - '0' < 27){
            sum += doCount(chars, index + 2);
        }
        return sum;
    }

    public static int countDp(char[] chars){
        int length = chars.length;
        int dp[] = new int[length + 1];
        dp[length] = 1;
        for (int i = length-1; i >= 0; i--) {
            if (chars[i] != '0'){

                int sum = dp[i+1];
                if (i + 1 < chars.length && chars[i] - '0' < 3 && chars[i + 1] - '0' < 7){
                    sum += dp[i+2];
                }
                dp[i] = sum;
            }
        }
        return dp[0];
    }
}
