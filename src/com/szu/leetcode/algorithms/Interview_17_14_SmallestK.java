package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      面试题 17.14. 最小K个数
 *      设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 *      https://leetcode-cn.com/problems/smallest-k-lcci/
 *
 * @Date 2021/3/27 19:38
 */

import java.util.Random;

public class Interview_17_14_SmallestK {
    /*
    * BFPRT like
    *
    * 拒绝排序！
    * 使用 O(N)时间复杂度解决问题
    *
    * */
    public int[] smallestK(int[] arr, int k) {
        return smallestK(arr, k-1, 0, arr.length-1, new Random());
    }

    private int[] smallestK(int[] arr, int index, int low, int high, Random random) {

        if (arr == null || arr.length == 0) return new int[]{};
        int pivot = arr[ low + random.nextInt(high - low + 1) ];
        int[] range = getRange(arr, low, high, pivot);
        if (index >= range[0] && index <= range[1])
            return makeResult(0, index, arr);
        if (index < range[0])
            return smallestK(arr, index, low, range[0]-1, random);
        else
            return smallestK(arr, index, range[0]+1, high, random);
    }

    private int[] makeResult(int start, int end, int[] arr) {
        int[] res = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    private int[] getRange(int[] arr, int low, int high, int pivot) {

        int small = low;
        int cur = low;
        int large = high;
        while (cur <= large){
            if (arr[cur] > pivot)
                swap(cur, large--, arr);
            else if ( arr[cur] < pivot )
                swap(cur++, small++, arr);
            else
                cur++;
        }
        return new int[]{small, large};
    }

    private void swap(int i1, int i2, int[] arr) {
        if (i1 == i2) return;
        int tem = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tem;
    }


    public static void main(String[] args) {
        new Interview_17_14_SmallestK().smallestK(new int[]{1,2,3}, 0);
    }
}
