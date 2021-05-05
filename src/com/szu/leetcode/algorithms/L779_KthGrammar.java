package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 779. 第K个语法符号
    在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。

    给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）

   https://leetcode-cn.com/problems/k-th-symbol-in-grammar/
 *
 * @Date 2021/5/4 11:24
 */

public class L779_KthGrammar {

    /*
    * 规律就是：
    * 本层的前一半 是 上一层原封不动的拷贝
    * 后一半 是 上一层 取反
    * */
    public int kthGrammar(int n, int k) {
        if (n == 1)
            return 0;

        int len = (int) Math.pow(2, n - 1);
        int half = len / 2;

        if (k > half) {
            return kthGrammar(n - 1, k - half) == 1 ? 0 : 1;
        } else
            return kthGrammar(n - 1, k);
    }

    public static void main(String[] args) {
        L779_KthGrammar test = new L779_KthGrammar();

        test.kthGrammar(12, 2999);
    }
}
