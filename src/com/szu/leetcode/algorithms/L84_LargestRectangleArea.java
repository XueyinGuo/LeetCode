package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 84. 柱状图中最大的矩形
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

    求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @Date 2021/5/12 17:05
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.LinkedList;

public class L84_LargestRectangleArea {
    /*
     * 使用单调栈找到左右两边第一个比 当前数字小的数
     * */
    public int largestRectangleArea(int[] heights) {

        if (heights == null || heights.length == 0)
            return 0;

        LinkedList<Integer> stack = new LinkedList<>();
        /*
         * 某个元素都找两边比自己小的元素,然后用 【两边比较矮的那个元素 * 两个矮柱子的距离】
         * */
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {


            while (!stack.isEmpty() && heights[stack.peekLast()] > heights[i]) {

                Integer h = heights[stack.pollLast()];

                int w;
                if (stack.isEmpty())
                    /*
                     * ================================
                     * ================================
                     * 下标换算 搞死你爹了！
                     * 如果此时栈空了，那么当前弹出的这个高度可以一直延伸到 ，使这个元素弹出的位置
                     * ================================
                     * ================================
                     * */
                    w = i;
                else
                    /*
                    * ================================
                    * ================================
                    * 下标换算 搞死你爹了！
                    * ================================
                    * ================================
                    * */
                    w = i - stack.peekLast() - 1;

                max = Math.max(max, w * h);
            }

            stack.addLast(i);

        }

        while (!stack.isEmpty()) {

            Integer h = heights[stack.pollLast()];

            int w;
            if (stack.isEmpty())
                w = heights.length;
            else
                w = heights.length - stack.peekLast() - 1;

            max = Math.max(max, w * h);
        }

        return max;
    }


    public static void main(String[] args) {
        int[] inputArray = LeetCodes.getInputArray("[0,3,2]");
        L84_LargestRectangleArea test = new L84_LargestRectangleArea();
        System.out.println(test.largestRectangleArea(inputArray));
    }
}
