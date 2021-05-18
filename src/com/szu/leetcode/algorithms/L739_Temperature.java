package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 739. 每日温度
请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * @Date 2021/5/18 19:07
 */

import java.util.ArrayDeque;

public class L739_Temperature {
    /*
    * 单调栈求解即可
    *
    * 非常简单
    * */
    public int[] dailyTemperatures(int[] temperatures) {

        if(temperatures == null || temperatures.length == 0)
            return new int[]{};
        if(temperatures.length == 1)
            return new int[]{1};

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int[] ans = new int[temperatures.length];

        int i = 0;
        while(i < temperatures.length){

            while(!stack.isEmpty() && temperatures[ stack.peekLast()] < temperatures[i]  ){

                int polled = stack.pollLast();

                ans[polled] = i - polled;
            }
            stack.offerLast(i++);
        }

        while( !stack.isEmpty() ){

            ans[stack.pollLast()] = 0;

        }
        return ans;
    }

}
