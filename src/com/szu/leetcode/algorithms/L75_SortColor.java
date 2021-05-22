package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/5/21 23:58
 */

public class L75_SortColor {

    public void sortColors(int[] arr) {
        int pivot = 1;
        int small = 0;
        int large = arr.length - 1;
        int cur = small;
        while (cur <= large) {
            if (arr[cur] > pivot) {
                swap(arr, cur, large);
                large--;
            } else if (arr[cur] < pivot) {
                /* 虽然会出现cur small有时候会相等的情况
                 * 但是一旦出现 cur 的值与 pivot相等的情况，cur就会比small大了
                 * 然后把 pivot 相等的值会放到一块 */
                swap(arr, cur, small);
                cur++;
                small++;
            } else
                cur++;
        }
    }


    private void swap(int[] arr, int i, int j) {
        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }



}
