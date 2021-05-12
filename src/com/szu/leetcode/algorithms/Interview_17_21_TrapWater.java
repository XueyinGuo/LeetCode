package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 面试题 17.21. 直方图的水量
给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。



上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。

示例:

输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
 *
 * @Date 2021/5/12 16:22
 */

import java.util.LinkedList;
import java.util.Queue;

public class Interview_17_21_TrapWater {

    public int trap(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        /*
         * 双指针向中间滑动
         * */
        int left = 0;
        int right = height.length - 1;
        int water = 0;
        int bottleNeck = height[left] > height[right] ? height[right] : height[left];

        while (left <= right) {

            if (height[left] <= height[right]) {
                int add = bottleNeck - height[left];
                water += add > 0 ? add : 0;

                if (height[left] > bottleNeck)
                    bottleNeck = height[left];

                left++;

            } else {

                int add = bottleNeck - height[right];
                water += add > 0 ? add : 0;
                if (height[right] > bottleNeck)
                    bottleNeck = height[right];

                right--;

            }

        }
        return water;

    }

}
