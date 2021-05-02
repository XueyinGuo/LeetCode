package com.szu.leetcode.contest;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 5746. 到目标元素的最小距离
 * 给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数 target 和 start ，
 * 请你找出一个下标 i ，满足 nums[i] == target 且 abs(i - start) 最小化 。注意：abs(x) 表示 x 的绝对值。

    返回 abs(i - start) 。

    题目数据保证 target 存在于 nums 中。
 *
 * @Date 2021/5/2 10:28
 */

public class L5746_GetMinDistance {
    public int getMinDistance(int[] nums, int target, int start) {
        int minAbs = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target && Math.abs(i - start) < minAbs){
                minAbs = Math.abs(i - start);
            }
        }
        if (minAbs == Integer.MAX_VALUE)
            return 0;
        return minAbs;
    }
}
