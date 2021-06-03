package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 525. 连续数组
给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 * @Date 2021/6/3 12:40
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;
import java.util.HashMap;

public class L525_FindMaxLength {

    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
//        把 0 换成 -1 之后，找最长的和为 0 的子数组
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0)
                nums[i] = -1;
        }
        int preSum[] = new int[len];
        int maxSum = nums[0];
        preSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
            if (preSum[i] > maxSum)
                maxSum = preSum[i];
        }
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        /*
        * 0 这个前缀和 从 -1 位置出现的
        * 只要后来出现的这个前缀和 map 中有记录，那么这两个相同值中间的数组 肯定和 为 0
        * 题目要求最长的 0 1 个数相等的数组
        * 所以我们只要第一次出现的前缀和
        * */
        indexMap.put(0, -1);
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            Integer index = indexMap.get(preSum[i]);
            if (index != null) {
                maxLen = Math.max(maxLen, i - index);
            } else {
                indexMap.put(preSum[i], i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] inputArray = LeetCodes.getInputArray("[0,1]");
        L525_FindMaxLength test = new L525_FindMaxLength();
        System.out.println(test.findMaxLength(inputArray));
    }
}
