package com.szu.practice.l01_sort;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 数组小和问题，求数组中每个数 左侧 所有 比这个数小的 元素的累加和
 *
 * @Date 2021/5/11 19:21
 */

import com.szu.leetcode.utils.LeetCodes;

public class SmallSum {

    public static int smallSum(int[] arr){

        if (arr == null || arr.length == 0)
            return 0;

        return mergeSort(arr, 0, arr.length-1);

    }

    private static int mergeSort(int[] arr, int left, int right) {

        if (left == right)
            return 0;

        int mid = left + ((right - left) >> 1);

        return mergeSort(arr, left, mid) +
                mergeSort(arr, mid+1, right) +
                getSmallSumAfterMerge(arr, left,mid ,right);

    }

    private static int getSmallSumAfterMerge(int[] arr, int left, int mid,int right) {
        int help[] = new int[right-left+1];
        int index = 0;
        int i = left;
        int j = mid+1;
        int res = 0;
        while (i <= mid && j <= right){

            if (arr[i] < arr[j]){
                help[index++] = arr[i];
                /* 通过归并排序的性质，右边半边的有序性，直接榨取 左边小数字应该出现的数量 */
                res = res + (arr[i] * (right - j + 1));
                i++;
            }else {

                help[index++] = arr[j++];

            }

        }
        while (i <= mid){
            help[index++] = arr[i++];
        }
        while (j <= right){
            help[index++] = arr[j++];
        }
        index = 0;
        for (int k = left; k <= right ; k++) {
             arr[k] = help[index++];
        }
        return res;
    }


    public static void main(String[] args) {

        for (int i = 0; i < 1000000; i++) {
            int[] randomArray = LeetCodes.getRandomArray(5, 10);
            int[] copy = LeetCodes.copyArray(randomArray);
            int my = smallSum(randomArray);
            int right = smallSumRight(copy);
            if (my != right)
                System.out.println("FUCK");
        }

    }



    public static int smallSumRight(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]既要排好序，也要求小和返回
    // 所有merge时，产生的小和，累加
    // 左 排序   merge
    // 右 排序  merge
    // merge
    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return
                process(arr, l, mid)
                        +
                        process(arr, mid + 1, r)
                        +
                        merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int L, int m, int r) {
        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = m + 1;
        int res = 0;
        while (p1 <= m && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }
}
