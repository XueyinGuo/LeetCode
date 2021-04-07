package com.szu.training.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      括号字符串是否合法，不合法的话最少补多少个就可以合法了呢？返回数字即可
 *
 *      字符串中全是小括号
 *
 * @Date 2021/4/7 12:50
 */

public class NeedParentheses {

    public boolean isValid(String s) {
        char[] str = s.toCharArray();
        int little = 0;
        int mid = 0;
        int large = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(')
                little++;
            else if (str[i] == '[')
                mid++;
            else if (str[i] == '{')
                large++;
            else if (str[i] == ')')
                little--;
            else if (str[i] == ']')
                mid--;
            else
                large--;
            if (little < 0 || mid < 0 || large < 0)
                return false;
        }
        if (large != 0 || little != 0 || mid != 0)
            return false;
        return true;
    }

}
