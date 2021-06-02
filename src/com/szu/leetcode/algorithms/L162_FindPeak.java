package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 162. 寻找峰值
峰值元素是指其值大于左右相邻值的元素。

给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。
 *
 * @Date 2021/6/2 18:41
 */

public class L162_FindPeak {
    /*
    * 这道题目完美的展示了在一个不是严格有序的数组上 怎么 利用二分查找 来快速定位
    * */
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1]) //* 关键点在这里 */
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

}
