package com.szu.leetcode.sort;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/10 21:45
 */

public class HeapSort {
    static int heap[] = new int[16];
    static int size = 0;

    public static void main(String[] args) {
        int[] arr = {};
        heapInsert(arr,5);
        pop();

    }

    private static int pop() {
        int ans = heap[0];
        swap(heap, 0, --size);
        heapify(heap, 0, size);
        return ans;
    }

    private static void heapify(int[] heap, int index, int size) {
        int left = index * 2 + 1;
        while (left < size){
            int large = left + 1 > size ? left : ( heap[left+1] > heap[left] ? left + 1 : left );
            

        }
    }

    public void push(int val){

        heapInsert(heap, val);
        size++;
    }

    private static void heapInsert(int[] arr, int num) {
        while(arr)

    }



}
