package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 1449. 数位成本和为目标值的最大数字
给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数：

给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。
总成本必须恰好等于 target 。
添加的数位中没有数字 0 。
由于答案可能会很大，请你以字符串形式返回。

如果按照上述要求无法得到任何整数，请你返回 "0" 。
 *
 * @Date 2021/6/12 22:02
 */

public class L1499_LargestNumber {

    public String largestNumber(int[] cost, int target) {
        StringBuffer minNumber = getMinNumber(cost, 0, target);
        return minNumber.reverse().toString();
    }

    public StringBuffer getMinNumber(int[] cost, int index, int rest) {
        if (index == cost.length || rest < 0)
            return null;
        if (rest == 0)
            return new StringBuffer().append(cost[index]);
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();

        StringBuffer addCur = getMinNumber(cost, index + 1, rest - cost[index]);
        StringBuffer notAddCur = getMinNumber(cost, index + 1, rest);
        sb1.append(addCur == null ? "" : addCur);
        sb2.append(notAddCur == null ? "" : notAddCur);
        return min(sb1, sb2);
    }

    private StringBuffer min(StringBuffer num1, StringBuffer num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 == 0)
            return num2;
        if (len2 == 0)
            return num1;

        int i1 = 0, i2 = 0;
        while (i1 < len1 && i2 < len2) {
            if (num1.charAt(i1) > num2.charAt(i2))
                return num2;
            else if (num1.charAt(i1) < num2.charAt(i2))
                return num1;
            i1++;
            i2++;
        }
        if (i2 == len2)
            return num2;
        if (i1 == len1)
            return num1;

        return null;
    }

}
