package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 523. 连续的子数组和
给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
子数组大小 至少为 2 ，且
子数组元素总和为 k 的倍数。
如果存在，返回 true ；否则，返回 false 。
如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。

示例 1：

输入：nums = [23,2,4,6,7], k = 6
输出：true
解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
 *
 * @Date 2021/6/2 16:09
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.HashMap;

public class L523_ContinuousSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return false;
        /* 又连续的两个 0 的话直接返回 true */
        if (checkSerialZero(nums))
            return true;
        /*
        * 思路是这样的：
        * 1. 如果从头开始的前缀和中出现了正好可以是 k 的整数倍的数字，那么这个数字 % k == 0
        * 2. 如果某个子数组前缀和 正好是 k 的倍数，那么 模完之后的数字 必定之前出现过，所以我们需要记录每个 模完值的位置【因为子数组长度必须大于2】
        *  */
        int length = nums.length;
        int preSum[] = new int[length];
        /*
        * 记录    模值 K : V 下标
        * */
        HashMap<Integer, Integer> map = new HashMap<>();
        preSum[0] = nums[0] % k;
        map.put(preSum[0], 0);
        for (int i = 1; i < length; i++) {
            preSum[i] = (preSum[i - 1] + nums[i]) % k;
            if (nums[i] == 0) /* 如果 nums[i] == 0， 直接跳过不管 */
                continue;
            Integer showedPreSumIndex = map.get(preSum[i]);
            if ((showedPreSumIndex != null && i - showedPreSumIndex >= 2) || preSum[i] == 0)
                return true;
            map.put(preSum[i], i);
        }
        return false;

    }

    private boolean checkSerialZero(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i] && nums[i] == 0)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] inputArray = LeetCodes.getInputArray("[1,2,12]");
        int k = 6;
        L523_ContinuousSubarraySum test = new L523_ContinuousSubarraySum();
        boolean b = test.checkSubarraySum(inputArray, k);
        System.out.println(b);
    }
}
