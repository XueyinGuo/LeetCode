package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 1482. 制作 m 束花所需的最少天数
给你一个整数数组 bloomDay，以及两个整数 m 和 k 。

现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。

花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。

请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 *
 * @Date 2021/5/9 12:44
 */

public class L1482_BloomDay {

    public int minDays(int[] bloomDay, int m, int k) {
        /*
        * 过滤， 总共需要 m * k 朵花
        * 数组长度不够的话直接返回 -1
        * */
        if (bloomDay == null || bloomDay.length == 0)
            return -1;
        int length = bloomDay.length;
        if (m * k > length)
            return -1;

        /*
        * 采用二分的方式查找答案
        *
        * 完成工作的最大天数一定不会超过最后一朵花的开花时间
        * 所以答案必定在 第一朵花开 和 最后一朵花开的时间内
        * */
        int right = Integer.MIN_VALUE;
        int left = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            if (bloomDay[i] > right)
                right = bloomDay[i];
            if (bloomDay[i] < left)
                left = bloomDay[i];
        }

        int ans = -1;
        out:
        while (left <= right) {
            /* 从中间位置开始试 */
            int mid = (left + right) >> 1;
            int alreadyMade = 0; // 已经做成的花束的数量
            int serial = 0;      // 连续开花的朵数
            for (int i = 0; i < length; i++) {
                if (bloomDay[i] <= mid)
                    serial++;
                else
                    serial = 0; // 一旦花开的不连续了， 那就这几朵开的也不能用，所以直接连续数量 清零

                if (serial == k) { // 连续花朵的数量够了，就可以直接制作花束了，制作完花束之后，也把当前连续花朵数量清零
                    alreadyMade++;
                    serial = 0;
                }

                if (alreadyMade == m) {
                    ans = mid;
                    right = mid - 1;
                    continue out; /* 使用当前花开的时间完全可以完成工作要求，继续尝试缩小范围寻找更优答案 */
                }
            }
            left = mid + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] bloomDay = {1, 10, 3, 10, 2};
        int m = 3;
        int k = 1;
        L1482_BloomDay test = new L1482_BloomDay();
        System.out.println(test.minDays(bloomDay, m, k));
    }

}
