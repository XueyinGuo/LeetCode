package com.szu.training01.class01;
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

    public static boolean valid(String s) {
        char[] str = s.toCharArray();
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            count += str[i] == '(' ? 1 : -1;
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    public static int needParentheses(String s) {
        char[] str = s.toCharArray();
        int count = 0;
        int need = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                count++;
            } else { // 遇到的是')'
                if (count == 0) {
                    need++;
                } else {
                    count--;
                }
            }
        }
        return count + need;
    }

}
