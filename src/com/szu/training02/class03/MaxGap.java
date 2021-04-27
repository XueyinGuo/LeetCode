package com.szu.training02.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个无序数组arr，返回如果排序之后，相邻最大的差值
 * {3 1 7 9}
 * 排序后的数足 为 1  3  7  9， 差值最大为 4
 *
 * 要求：不能真正的排序，并且要在 O(N) 时间复杂度解决
 *
 * @Date 2021/4/27 16:26
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;

public class MaxGap {

    private static int maxGap(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        /*
         * 找到数组中的最大值和最小值
         * */
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }
        // 如果最大值和最小值相等，那么差值必定是0
        if (min == max)
            return 0;
        /*
         * 创建桶，需要三个数组，桶的数量为 数组长度 + 1
         * boolean[] hasNum ， 编号为 i 的桶 是否放进过数字，大小有一个放进去就算放过
         * int[] small   落入这个桶的最小值
         * int[] big     落入这个桶的最大值
         * 0号 和 arr.length 两头的桶必定有数字的
         * 最大的差值，必定出现在 跨桶之间，桶内不可能有最大差值，
         * 而且有数字的两个桶 的 最大值和最小值 必定是源数组中挨着的两个值
         * */
        int buckets = arr.length + 1;
        boolean[] hasNum = new boolean[buckets];
        int[] small = new int[buckets];
        int[] big = new int[buckets];
        int bucketId = 0;
        // 往桶里边方数字
        for (int i = 0; i < arr.length; i++) {
            /* （当前数字 - 数组最小值） * 数组长度 / （数组最大值 - 最小值） */
            bucketId = getBucketId(arr[i], min, arr.length, max); /* 如何计算，数字位于哪一个桶内？ */
            small[bucketId] = hasNum[bucketId] ? Math.min(small[bucketId], arr[i]) : arr[i];
            big[bucketId] = hasNum[bucketId] ? Math.max(big[bucketId], arr[i]) : arr[i];
            hasNum[bucketId] = true;
        }
        int ans = Integer.MIN_VALUE;
        int last = big[0];
        for (int i = 1; i < buckets; i++) {
            if (hasNum[i]) {
                int cur = small[i] - last;
                last = big[i];
                if (cur > ans)
                    ans = cur;
            }
        }
        return ans;
    }

    /* （当前数字 - 数组最小值） * 数组长度 / （数组最大值 - 最小值） */
    private static int getBucketId(int curNum, int min, int length, int max) {
        return (curNum - min) * length / (max - min);
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = LeetCodes.getRandomArrayWithNegative(maxSize, maxValue);
            int[] arr2 = LeetCodes.copyArray(arr1);
//            int[] arr1 = {-3, -5, 3, -1, -2};
//            int[] arr2 = LeetCodes.copyArray(arr1);
            int my = maxGap(arr1);
            int right = comparator(arr2);
            if (my != right) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }


    private static int comparator(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        Arrays.sort(arr);
        int maxGap = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int cur = arr[i + 1] - arr[i];
            if (cur > maxGap)
                maxGap = cur;
        }
        return maxGap;
    }

}
