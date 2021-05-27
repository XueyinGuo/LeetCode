package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *43. 字符串相乘
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

示例 1:

输入: num1 = "123", num2 = "456"
输出: "56088"
 *
 * @Date 2021/5/27 23:12
 */

public class L43_StringMultiply {

    public static String multiply(String num1, String num2) {
        if (num1 == null || num2 == null)
            return "0";

        char[] str2 = num2.toCharArray();
        /*
        * 根据平时做乘法的原则，从 num2 的最后一位乘起
        * */
        int i = str2.length - 1;
        String ans = "";
        while (i >= 0) {
            /*
            * 每次计算当前的 num2 的最后一位 与 当前的 num1 的乘积
            * */
            String curProduct = getCurProduct(num1, str2[i] - '0');
            /* 把每次的乘积 加到 ans 上 */
            ans = add(ans, curProduct);
            /* num1 每次 扩大 10倍 */
            num1 += '0';
            i--;
        }
        if (ans.startsWith("0"))
            return "0";
        return ans;
    }

    private static String add(String num1, String curProduct) {
        /*
        * 两个字符串数字的相加
        * */
        StringBuffer sb = new StringBuffer();
        char[] str1 = num1.toCharArray();
        char[] str2 = curProduct.toCharArray();
        int i = str1.length - 1, j = str2.length - 1;
        int jinWei = 0;
        while (i >= 0 && j >= 0) {
            int sum = str1[i] - '0' + str2[j] - '0' + jinWei;
            jinWei = sum >= 10 ? 1 : 0;
            sb.append(sum % 10);
            i--;
            j--;
        }
        while (i >= 0) {
            int sum = str1[i] - '0' + jinWei;
            sb.append(sum % 10);
            jinWei = sum >= 10 ? 1 : 0;
            i--;
        }
        while (j >= 0) {
            int sum = str2[j] - '0' + jinWei;
            sb.append(sum % 10);
            jinWei = sum >= 10 ? 1 : 0;
            j--;
        }
        if (jinWei != 0)
            sb.append(1);
        return sb.reverse().toString();
    }

    private static String getCurProduct(String num1, int k) {
        /*
        * 计算两个数字的乘积
        * */
        char[] str = num1.toCharArray();
        StringBuffer sb = new StringBuffer();
        int i = str.length - 1;
        int jinWei = 0;
        while (i >= 0) {
            /* 每次的当前乘积都是 （当前这一位 * k + 上一位的进位数字） */
            int product = (str[i] - '0') * k + jinWei;
            jinWei = product / 10;
            sb.append(product % 10);
            i--;
        }
        if (jinWei != 0)
            sb.append(jinWei);
        return sb.reverse().toString();
    }

}
