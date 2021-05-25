package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 152. 乘积最大子数组
给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * @Date 2021/5/25 22:52
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.TreeSet;

public class L152_MaxProductOfSubArr {

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        /*
         * 前缀积数组，每当碰到 0 就重新开始计数
         * */
        int preProduct[] = new int[nums.length];
        preProduct[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (preProduct[i - 1] == 0) {
                preProduct[i] = nums[i];
                continue;
            }
            preProduct[i] = preProduct[i - 1] * nums[i];
        }
        /*
         * 开始使用有序表查找之前计算出的与当前前缀积同符号的最小的前缀积
         * */
        int max = Integer.MIN_VALUE;
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(1); /* 哨兵 */
        for (int i = 0; i < nums.length; i++) {
            int smallestForCur;
            /*
             * 如果当前的前缀积小于 0 ，直接查找当前 小于 0 的最大负数
             * 如果不存在负数，则查找当前最大的正数作为 合适的 除数
             * 因为当前是前缀积负数，应该除以一个更大的正数
             * */
            if (preProduct[i] < 0) {
                Integer smallThan0 = treeSet.floor(0);
                smallestForCur = smallThan0 == null ? treeSet.last() : smallThan0;
            } else {
                smallestForCur = treeSet.ceiling(0); /* 有哨兵存在，所以直接取得 1 */
            }
            /*
            * 计算最大的前缀积
            * */
            int curMaxProduct = preProduct[i] / smallestForCur;
            max = Math.max(max, curMaxProduct);
            /*
             * 因为还得保证是连续的子数组的乘积， 所以需要没碰到一个 0 ，就清空当前所有留存的 有序表
             * */
            if (curMaxProduct == 0) {
                treeSet.clear();
                treeSet.add(1);
                continue;
            }
            /* 树中加入当前前缀积 */
            treeSet.add(preProduct[i]);
        }
        return max;
    }


    public static void main(String[] args) {
        int[] arr = LeetCodes.getInputArray("[2,3,-2,4]");
        L152_MaxProductOfSubArr test = new L152_MaxProductOfSubArr();
        test.maxProduct(arr);
    }

}
