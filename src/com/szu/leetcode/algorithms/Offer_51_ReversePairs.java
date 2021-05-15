package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  剑指 Offer 51. 数组中的逆序对
    在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

    https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 *
 * @Date 2021/4/10 14:00
 */

import java.util.LinkedList;

public class Offer_51_ReversePairs {

    /*
    * 使用二分法求解逆序对个数
    *
    * 每次都使 数组二分下去，使两个比较的部分 分别都是有序的
    *
    * 就可以直接通过下标计算出 当前两个范围的逆序对个数
    * */
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1)
            return 0;
//        int ans = 0;
//        int length = nums.length;
//        int max = nums[length - 1];
//        int less = 0;
//        for (int i = length - 2; i >= 0; i--) {
//
//            if (nums[i] > max) {
//                max = nums[i];
//                less++;
//            }
//            ans += less;
//
//        }
//
//        return ans;

        return mergeSort(nums, 0, nums.length - 1);

    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left == right)
            return 0;
        int mid = left + ((right - left) >> 1);
        return mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right) +
                merge(nums, left, mid, right);
    }

    public int merge(int[] nums, int left, int mid, int right) {

        int i = left;
        int j = mid + 1;
        int[] help = new int[right - left + 1];
        int index = 0;
        int ans = 0;
        while (i <= mid && j <= right) {

            if (nums[i] > nums[j]){
                help[index++] = nums[i++];
                ans += right - j + 1;
            }else {
                help[index++] = nums[j++];
            }

        }

        while (i <= mid){
            help[index++] = nums[i++];
        }
        while (j <= right)
            help[index++] = nums[j++];

        for (int k = 0; k < help.length; k++) {
            nums[left + k] = help[k];
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] arr = {7, 5, 6, 4};

//        System.out.println(i);
    }
}
