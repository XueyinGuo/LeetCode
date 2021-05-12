package com.szu.training03.class06;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/5/12 20:07
 */

import java.util.LinkedList;

public class LargestRectangleInMatrix {

    /*
    * 把矩阵的每一行，压在一起做成一个直方图类似的东西
    * 然后单调栈求解直方图中最大的矩形
    * */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int max = Integer.MIN_VALUE;
        int[] help = new int[matrix[0].length];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < help.length; c++) {
                if (matrix[r][c] == '0')
                    help[c] = 0;
                else
                    help[c]++;

            }

            max = Math.max(largestRectangleArea(help), max);
        }

        return max;
    }


    /*
    * 直方图中的最大矩形
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

}
