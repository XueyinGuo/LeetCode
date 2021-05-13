package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 11. 盛最多水的容器
给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器。
 *
 * @Date 2021/5/13 23:12
 */

public class L11_MaxArea {

    public int maxArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;

        /*
        * 超级简单的双指针即可解决
        * */
        int i = 0;
        int j = height.length - 1;
        int max = Integer.MIN_VALUE;
        while (i < j) {

            int shorter = height[i] > height[j] ? height[j] : height[i];
            int area = shorter * (j - i);
            if (area > max)
                max = area;

            if (shorter == height[i])
                i++;
            else
                j--;

        }
        return max;
    }

}
