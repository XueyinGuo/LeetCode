package com.szu.practice.l09_bfprt;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/21 17:57
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.PriorityQueue;
import java.util.Random;

public class MinKth {

    public static void main(String[] args) {
        Random random = new Random();
        for (int j = 0; j < 100000; j++) {

            int[] arr = LeetCodes.getRandomArray(40, 50);
            int k = 10;
            int i = useHeap(arr, k-1);
            int i1 = useQuickSortLike(arr,0, arr.length-1 ,k-1, random);
            if (i != i1)
                System.out.println("FUCK");
        }
    }

    public static int useQuickSortLike(int[] arr, int l, int r, int index, Random random){
        if (l == r) return arr[l];
        int randomIndex = l + random.nextInt(r - l + 1);
        int pivot = arr[randomIndex];
        int[] range = partition(arr, l, r, pivot);
        /* 如果index位于找到的pivot区域内，则直接返回 */
        if (index >= range[0] && index <= range[1])
            return arr[index];
        /* 然后递归两个区域中的一个 log(n) */
        else if (index > range[1])
            return useQuickSortLike(arr, range[1]+1, r, index, random);
        else
           return useQuickSortLike(arr, l, range[0]-1,index,random );

    }

    private static int[] partition(int[] arr, int l, int r, int pivot) {
        int small = l;
        int large = r;
        int cur = l;
        while (cur <= large){
            if (arr[cur] > pivot){
                swap(arr, cur, large);
                large--;
            }
            else if ( arr[cur] < pivot ){
                /* 虽然会出现cur small有时候会相等的情况
                * 但是一旦出现 cur 的值与 pivot相等的情况，cur就会比small大了
                * 然后把 pivot 相等的值会放到一块 */
                swap(arr, cur, small);
                cur++;
                small++;
            }else
                cur++;
        }
        // 最终返回的位置是 ： 此时数组已经是 < pivot的值在small前边， > pivot的值放在 large 后边
        // 返回的数组是 pivot相等的值的起始位置 和 终点位置
        return new int[]{small, large};
    }

    private static void swap(int[] arr, int i, int j) {
        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }


    private static int useHeap(int[] array, int k) {
        int arr[] = new int[array.length];
        System.arraycopy(array, 0, arr, 0, array.length);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        /* 返回第 k 小的，就把更小的弹出去 */
        while ( k > 0){
            queue.poll();
            k--;
        }
        // 弹出 第k小 的返回
        return queue.poll();
    }




}
