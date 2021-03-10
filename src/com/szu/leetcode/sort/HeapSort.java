package com.szu.leetcode.sort;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/10 21:45
 */

import java.util.Random;

public class HeapSort {
    static int capacity = 16;
    static int heap[] = new int[capacity];
    static int size = -1;

    public static void main(String[] args) {

        int round = 20;
        Random random = new Random();
        for (int i = 0; i < round; i++)
            push(random.nextInt(20));
        for (int i = 0; i < round; i++)
            System.out.print(pop() + " - ");

    }

    private static int pop() {
        int ans = heap[0];
        swap(0, size--);
        slipDown(0, size);
        return ans;
    }

    private static void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private static void slipDown(int index, int size) {
        int left = index * 2 + 1;
        while (left < size){
            int smallIndex = getSmallIndex(left);
            if (heap[smallIndex] == heap[index]) return;
            swap(left, index);
            index = smallIndex;
            left = smallIndex * 2 + 1;
        }

    }

    private static int getSmallIndex(int left) {
        return left + 1 > size ? left : ( heap[left+1] < heap[left] ? left + 1 : left );
    }

    public static void push(int val){
        size++;
        if (size == capacity) grow();
        heap[size] = val;
        slipUp(size);

    }

    private static void grow() {
        capacity = capacity << 1;
        int[] newHeap = new int[capacity];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    private static void slipUp(int index) {
        int father = (index - 1) / 2;
        while (heap[index] < heap[father]){
            swap(index, father);
            father = (father - 1) / 2;
            index = (index - 1) / 2;
        }
    }

    static int size(){
        return size + 1;
    }
}
