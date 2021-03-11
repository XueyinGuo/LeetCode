package com.szu.leetcode.sort;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/11 13:54
 */

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {4,5,9,10,8,9,4};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high){
            int index = find(arr, low, high);
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }
    }

    private static int find(int[] arr, int low, int high) {
        int tem = arr[low];
        while(low < high){
            while (low < high && arr[high] > tem) high--;
            arr[low] = arr[high];
            while (low < high && arr[low] <= tem) low++;
            arr[high] = arr[low];
        }
        arr[low] = tem;
        return low;
    }
}
