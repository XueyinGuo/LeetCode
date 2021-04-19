package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  241. 为运算表达式设计优先级
 *  给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
 *
 * @Date 2021/4/19 22:53
 */

import java.util.ArrayList;
import java.util.List;

public class L241_Expression {

    public List<Integer> diffWaysToCompute(String expression) {
        char[] str = expression.toCharArray();

        return getResults(str, 0, str.length - 1);

    }

    private List<Integer> getResults(char[] str, int start, int end) {
        List<Integer> res = new ArrayList<>();
//        默认没有运算符
        boolean hasNoExpression = true;
        /*
        * 穷举每个位置是否是运算符
        * */
        for (int i = start; i <= end ; i++) {
            if (str[i] == '+' || str[i] == '-' || str[i] == '*'){
//                找到了运算符
                hasNoExpression = false;
                /* 两边去递归，限定区域，分别返回自己的结果集 */
                List<Integer> leftRes = getResults(str, start, i-1 );
                List<Integer> rightRes = getResults(str, i + 1, end);
                /* 穷举每个结果，使用对应的运算符进行计算，并加到本层结果集中 */
                if (str[i] == '+'){
                    for (int j = 0; j < leftRes.size(); j++) {
                        for (int k = 0; k < rightRes.size(); k++)
                            res.add(leftRes.get(j) + rightRes.get(k));

                    }
                }
                if (str[i] == '-'){
                    for (int j = 0; j < leftRes.size(); j++) {
                        for (int k = 0; k < rightRes.size(); k++)
                            res.add(leftRes.get(j) - rightRes.get(k));

                    }
                }

                if (str[i] == '*'){
                    for (int j = 0; j < leftRes.size(); j++) {
                        for (int k = 0; k < rightRes.size(); k++)
                            res.add(leftRes.get(j) * rightRes.get(k));

                    }
                }

            }
        }
        /*
        * 如果这一层没有找到运算符就代表没有表达式，没有表达式就直接返回本层一个数就好了
        * String.valueOf ，从哪个字符开始，往后数几个字符转成字符串 */
        if (hasNoExpression)
            res.add( Integer.valueOf(String.valueOf(str, start, end - start + 1)) );
        return res;
    }

    public static void main(String[] args) {
        List<Integer> integers = new L241_Expression().diffWaysToCompute("2*3-4*5");
        System.out.println();
    }
}
