package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 1190. 反转每对括号间的子串
    给出一个字符串 s（仅含有小写英文字母和括号）。

    请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。

    注意，您的结果中 不应 包含任何括号。

 *
 * @Date 2021/5/3 13:06
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class L1190_ReverseParentheses {

    public String reverseParentheses(String s) {
        char[] str = s.toCharArray();
        LinkedList<Integer> stack = new LinkedList<>();
        LinkedList<Info> infoQueue = new LinkedList<>();
        ArrayList<Character> chars = new ArrayList<>();
        /*
        * 解题思路：
        * 把所有的字符搬运到一个新的 可以装下 char 类型的数据的容器中
        * 同时把所有括号的位置记录下来，在转移char的过程中不把括号转移过去，只记录每对括号括起来的位置
        *
        * 使用了 一个栈先记录第一个括号的位置
        *       然后每个 右括号 会让 左括号位置从栈中弹出
        *       二者组成一个info放入一个队列中，采用尾插法，保证队列的头 是 最里边一层的括号
        *
        * 然后逆序每个括号括起来的位置即可
        * */

        /* 使用额外的变量 index ，记录现在的字符位置 */
        int index = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(')
                stack.addLast(index);
            else if (str[i] == ')') {
                /* 右括号让最后一个左括号出栈，然后二者组成一队信息，进入队列 */
                Integer firstIndex = stack.pollLast();
                infoQueue.addLast(new Info(firstIndex, index));
            } else {
                index++;
                chars.add(str[i]);
            }

        }
        char[] res = new char[chars.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = chars.get(i);
        }
        /* 逆序每个范围 */
        while (!infoQueue.isEmpty()) {
            Info info = infoQueue.pollFirst();
            int first = info.first;
            int second = info.second - 1;
            while (first < second) {

                char tem = res[first];
                res[first++] = res[second];
                res[second--] = tem;
            }

        }
        return String.valueOf(res);
    }

    class Info {
        int first;
        int second;

        public Info(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }


    public static void main(String[] args) {
        String s = "(abcd)";
        L1190_ReverseParentheses test = new L1190_ReverseParentheses();
        test.reverseParentheses(s);
    }
}
