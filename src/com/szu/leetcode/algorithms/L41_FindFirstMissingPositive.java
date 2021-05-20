package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 41. 缺失的第一个正数
给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 * @Date 2021/5/20 21:41
 */

import com.szu.leetcode.utils.LeetCodes;

public class L41_FindFirstMissingPositive {

    public int firstMissingPositive(int[] nums) {

        if (nums == null || nums.length == 0)
            return 1;

        for (int i = 0; i < nums.length; i++)
            nums[i] = (nums[i] < 0 || nums[i] >= nums.length) ? Integer.MAX_VALUE : nums[i];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {

                int index = nums[i] < 0 ? -nums[i] : nums[i];
                nums[index] = nums[index] < 0 ? nums[index] : -nums[index];

            }
        }

        int i = 1;
        while (i < nums.length){
            if (nums[i] > 0)
                return i;
            i++;
        }
        return i;
    }


    public static void main(String[] args) {
        L41_FindFirstMissingPositive test = new L41_FindFirstMissingPositive();
        test.firstMissingPositive(LeetCodes.getInputArray(                "[3,4,-1,1]"));
    }
}
