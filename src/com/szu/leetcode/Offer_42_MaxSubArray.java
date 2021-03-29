package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      剑指 Offer 42. 连续子数组的最大和
        输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
        =================
        =================
        要求时间复杂度为O(n)。
        =================
        =================
 *
 * @Date 2021/3/28 22:46
 */

public class Offer_42_MaxSubArray {

    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int maxSum[] = new int[len];
        maxSum[len-1] = nums[len - 1];
        /*
        * 经过左老师的可能性舍弃的数组题目讲解
        * minSum[]  minSumEnd[] 那道题的精彩解法
        * 本题创建了 maxSum[] 数组
        *
        * 倒叙生成
        * 如果下一个位置为整数，则加上下一个位置的数字，因为有利可图
        * 否则，maxSum[] 数组中对应下标仅仅是自己 nums 中对应的值
        * */
        for(int i = len - 2; i >= 0; i--){

            if(maxSum[i + 1] > 0)
                maxSum[i] = nums[i] + maxSum[ i + 1];
            else
                maxSum[i] = nums[i];

        }
        // 最后遍历一遍 maxSum[] 数组 即可生成答案
        /* 时间复杂度 O(N) 拿下 */
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < len; i++){
            max = Math.max(max, maxSum[i]);
        }
        return max;
    }


    public static void main(String[] args) {
        new Offer_42_MaxSubArray().maxSubArray(new int[]{1});
    }
}
