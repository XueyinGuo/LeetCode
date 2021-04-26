package com.szu.training02.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个数组 arr，在给定一个 k 值
 * 返回累加和小于等于 k ，但是距离 k 最近的子数组累加和
 *
 * @Date 2021/4/26 10:21
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.TreeSet;

public class MaxSubArraySumLessOrEqualK {

    public static int getMaxLessOrEqualK(int[] arr, int k) {

        int sum = 0;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];

            int dif = sum - k;
            /* ceiling 返回的 不小于 参数值的 */
            Integer res = set.ceiling(dif);
            if (res != null) {
                int cur = sum - res;
                if (cur > max)
                    max = cur;
            }

            set.add(sum);
        }
        return max;
    }

    // 请返回arr中，求个子数组的累加和，是<=K的，并且是最大的。
    // 返回这个最大的累加和
    public static int getMaxLessOrEqualK2(int[] arr, int K) {
        // 记录i之前的，前缀和，按照有序表组织
        TreeSet<Integer> set = new TreeSet<Integer>();
        // 一个数也没有的时候，就已经有一个前缀和是0了
        set.add(0);

        int max = Integer.MIN_VALUE;
        int sum = 0;
        // 每一步的i，都求子数组必须以i结尾的情况下，求个子数组的累加和，是<=K的，并且是最大的
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // sum -> arr[0..i];
            if (set.ceiling(sum - K) != null) {
                max = Math.max(max, sum - set.ceiling(sum - K));
            }
            set.add(sum); // 当前的前缀和加入到set中去
        }
        return max;

    }


    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int[] arr = LeetCodes.getRandomArrayWithNegative(100, 30);
            int k = 30;
            int my = getMaxLessOrEqualK(arr, k);
            int right = getMaxLessOrEqualK2(arr, k);
            if (my != right)
                System.out.println("FUCK");
        }
    }

}
