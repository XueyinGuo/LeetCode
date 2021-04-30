package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个字符串 str， 表示一个表达式
 * 式子中有整数、加减乘除符号 和 左右括号
 * 返回表达式计算结果
 *
 * 举例：
 * str = "48*((70-65)-43)+8*1" , 返回 -1816
 * str = "3+1*4"                 返回 7
 * str = "3+(1*4)"               返回 7
 *
 * 字符串都合法， 负数需要用括号括起来 “7*（-3）”，
 * 但是 -3*4 和 （-3*4） 都是合法的，就是说负号前边没有任何运算符的时候，不用括起来
 *
 * @Date 2021/4/29 15:36
 */

import java.util.LinkedList;

public class L224_Calculator {

    public static void main(String[] args) {
//        String str = "48*((70-65)-43)+8*1";
        String str = "3/2";
        int res = calculate(str);
        System.out.println(res);
    }

    public static int calculate(String s) {
        if (s == null || s.length() == 0)
            return 0;

        char[] expression = s.toCharArray();
        return calculate(expression, 0).result;
    }

    private static Info calculate(char[] expression, int index) {

        int i = index;
        LinkedList<String> stack = new LinkedList<>();

        while (i < expression.length && expression[i] != ')') {
            if (expression[i] == ' '){
                i++;
                continue;
            }

            int curNum = 0;
            /* 获取当前数字 */
//            int weiShu = 0;
            boolean hasNum = false;
            while ( i< expression.length && expression[i] >= '0' && expression[i] <= '9') {
                hasNum = true;
                curNum = curNum * 10 + expression[i++] - '0';
            }

            /* 如果这个位置是括号，我需要展开递归 */
            if (i< expression.length && expression[i] == '(') {
                Info info = calculate(expression, i + 1);
                curNum = info.result;
                hasNum = true;
                i = info.index + 1;
            }
            /* 获取完当前数字时候，看看栈定是否是乘号或者除号，如果是直接计算,算完放回栈中 */
            String op = stack.peekLast();
            if (op != null && op.equals("*")) {
                stack.pollLast();
                Integer poll = Integer.valueOf(stack.pollLast());
                stack.addLast(String.valueOf(poll * curNum));
                continue;
            }
            if (op != null && op.equals("/")) {
                stack.pollLast();
                Integer poll = Integer.valueOf(stack.pollLast());
                stack.addLast(String.valueOf(poll / curNum));
                continue;
            }
            if (hasNum)
                stack.addLast(String.valueOf(curNum));
            /* 计算符号放入栈中 */
            if (i == expression.length)
                break;
            if (expression[i] == '+' || expression[i] == '-' ||
                    expression[i] == '*' || expression[i] == '/') {
                /* 比较恶心的题，除了负数减号开头之外，还有正数加号开头，为了防止转换错误，在 开头是符号的时候，栈中先加一个 0 */
                if (stack.isEmpty() && (expression[i] == '-' || expression[i] == '+')  )
                    stack.addLast(String.valueOf(0));
                stack.addLast(String.valueOf(expression[i++]));
                continue;
            }

        }
        while (stack.size() > 1) {
            String first = stack.poll();
            String op = stack.poll();
            String second = stack.poll();
            if (op.equals("+"))
                stack.addFirst(String.valueOf(Integer.valueOf(first) + Integer.valueOf(second)));
            else
                stack.addFirst(String.valueOf(Integer.valueOf(first) - Integer.valueOf(second)));
        }
        return new Info(Integer.valueOf(stack.peek()), i);
    }

    static class Info {
        int result;
        int index;

        public Info(int result, int index) {
            this.result = result;
            this.index = index;
        }
    }

}
