package com.szu.training03.class06;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description

 128. 最长连续序列
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。


进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？

 * @Date 2021/5/12 22:43
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.HashMap;
import java.util.HashSet;

public class LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        HashMap<Integer, Integer> headMap = new HashMap<>();
        HashMap<Integer, Integer> tailMap = new HashMap<>();
        HashSet<Integer> processed = new HashSet<>();
        int max = 1;
        for (int i = 0; i < nums.length; i++) {

            int larger = nums[i] + 1;
            Integer nextHeadNum = headMap.get(larger);
            if (nextHeadNum != null) {
//                headMap.remove(larger);
                int newLength = nextHeadNum + 1;
                headMap.put(nums[i], newLength);
                tailMap.put(larger + nextHeadNum - 1, newLength);
                max = Math.max(max, newLength);
            } else
                headMap.put(nums[i], 1);


            int smaller = nums[i] - 1;
            Integer preTailNum = tailMap.get(smaller);
            Integer oldLength = headMap.get(nums[i]);
            if (preTailNum != null) {
//                tailMap.remove(smaller);
                int newLength = preTailNum + oldLength;
                headMap.put(smaller - preTailNum + 1, newLength);
                tailMap.put(nums[i], newLength);
                max = Math.max(newLength, max);
            } else
                tailMap.put(nums[i], 1);

        }
        return max;
    }


    public static void main(String[] args) {
        int[] inputArray = LeetCodes.getInputArray("[0,3,2,4,1]");
        LongestConsecutive test = new LongestConsecutive();
        test.longestConsecutive(inputArray);
    }
}
