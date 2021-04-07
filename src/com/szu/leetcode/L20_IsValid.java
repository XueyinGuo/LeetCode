package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  20. 有效的括号
        给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

        有效字符串需满足：

        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。
 *
 * @Date 2021/4/7 13:25
 */

import java.util.Stack;

public class L20_IsValid {

    public boolean isValid(String s) {
        if(s.length() < 2)
            return false;
        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ;i < str.length; i++){
            /*
            * 左括号入栈
            * */
            if(str[i] == '(' || str[i] == '[' || str[i] == '{')
                stack.push(str[i]);
            else{
                /*
                 * 右括号使元素出栈， 如果栈为空，则为false，不匹配也为false
                 * */
                if(stack.isEmpty())
                    return false;
                Character c = stack.pop();
                if(c == '(' && str[i] != ')')
                    return false;
                if(c == '[' && str[i] != ']')
                    return false;
                if(c == '{' && str[i] != '}')
                    return false;
            }
        }
        /*
         * 遍历完括号字符串，如果栈不为空，这也为false
         * */
        if(!stack.isEmpty())
            return false;
        return true;
    }

}
