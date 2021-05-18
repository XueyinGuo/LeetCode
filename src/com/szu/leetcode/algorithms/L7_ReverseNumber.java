package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 7. 整数反转
给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。

如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。

假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * @Date 2021/2/7 22:04
 */

public class L7_ReverseNumber {
    public int reverse(int x) {

        boolean negative = ((x >> 31) & 1) == 1;
        if (!negative)
            x = -x;
        int ans = 0;
        /*
        * 防止溢出的两个变量
        * */
        int m = Integer.MIN_VALUE / 10;
        int n = Integer.MIN_VALUE % 10;
        int cur;
        while (x != 0) {
            /*
            * 依然使用负数操作
            *
            * 负数 % 10 之后 = 负数！！！！！！！！！！
            * */
            cur = x % 10;
            if (ans < m || (ans == m && cur < n))
                return 0;
            ans = ans * 10 + cur;
            x /= 10;
        }
        return negative ? ans : -ans;
    }

    public static void main(String[] args) {
        L7_ReverseNumber reverseNumber_7 = new L7_ReverseNumber();
        int reverse = reverseNumber_7.reverse(2147483647);
        System.out.println(reverse);
    }
}
