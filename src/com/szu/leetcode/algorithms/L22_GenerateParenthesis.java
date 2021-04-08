package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      22. 括号生成
 *      数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * @Date 2021/4/7 13:32
 */

import java.util.ArrayList;
import java.util.List;

public class L22_GenerateParenthesis {

    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        // 执行深度优先遍历，搜索可能的结果
        dfs("", n, n, res);
        return res;
    }

    /**
     * curStr 当前递归得到的结果
     * left   左括号还有几个可以使用
     * right  右括号还有几个可以使用
     * res    结果集
     */
    private void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝（如果想要保证生成的字符串比合法，则 剩余的左括号数量 一定要小于 剩余的右括号数量）
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }

    public static void main(String[] args) {
        new L22_GenerateParenthesis().generateParenthesis(3);
    }
}

