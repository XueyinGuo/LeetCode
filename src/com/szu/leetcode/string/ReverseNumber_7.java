package com.szu.leetcode.string;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/7 22:04
 */

public class ReverseNumber_7 {
    public int reverse(int x) {
        char chars[] = new Integer(x).toString().toCharArray();
        int i = 0;
        int j = chars.length - 1;
        char temp;
        if(chars[i] == '-'){
            i++;
        }
        while(i <= j){
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        StringBuffer sb = new StringBuffer();
        for(int index = 0; index < chars.length; index++){
            sb.append(chars[index]);
        }
        return Integer.parseInt(sb.toString());
    }

    public static void main(String[] args) {
        ReverseNumber_7 reverseNumber_7 = new ReverseNumber_7();
        int reverse = reverseNumber_7.reverse(1534236469);
        System.out.println(reverse);
    }
}
