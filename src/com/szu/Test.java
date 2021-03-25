package com.szu;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 13:19
 */


import com.szu.leetcode.utils.LeetCodes;

import java.util.PriorityQueue;
import java.util.Random;

public class Test {
    public static void main(String[] args) {

        Random random = new Random();
        for (int j = 0; j < 100000; j++) {

            int[] arr = LeetCodes.getRandomArray(40, 50);
            int k = 10;
            int i = useHeap2(arr, k-1);
            int i1 = useQuickSortLike2(arr,0, arr.length-1 ,k-1, random);
            if (i != i1)
                System.out.println("FUCK");
        }
    }

    private static int useQuickSortLike2(int[] arr, int low, int high, int index, Random random) {
        int randomIndex = random.nextInt(low + (high - low + 1));
        int pivot = arr[randomIndex];
        int[] range = partition(arr, low, high, pivot);
        if (index >= range[0] && index <= range[1])
            return arr[index];
        else if (index > range[1])
            return useQuickSortLike2(arr, range[1]+1, high, index, random);
        else
            return useQuickSortLike2(arr, low,range[0]-1,index, random );
    }

    private static int[] partition(int[] arr, int low, int high, int pivot) {
        int less = low;
        int cur= low;
        int large = high;
        while (cur <= large){
            if (arr[cur] > pivot){
                swap2(arr, cur, large);
                large--;
            }else if (arr[cur] < pivot){
                swap2(arr, cur, less);
                cur++;
                less++;
            }else
                cur++;
        }
        return new int[]{less, large};
    }


    private static void swap2(int[] arr, int less, int high) {
        if (less == high) return;
        int tem = arr[less];
        arr[less] = arr[high];
        arr[high] = tem;
    }

    private static int useHeap2(int[] arr, int i) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int j = 0; j < arr.length; j++) {
            queue.offer(arr[j]);
        }
        while (i > 0 && !queue.isEmpty()){
            queue.poll();
            i--;
        }
        if (queue.isEmpty())
            return Integer.MIN_VALUE;
        return queue.poll();
    }

}