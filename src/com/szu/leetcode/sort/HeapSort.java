package com.szu.leetcode.sort;
/*
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
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            if (i == 10) System.out.println("size   :  "+size());
            push(random.nextInt(2000));
        }
        int total = size();
        for (int i = 0; i < total; i++) {
            System.out.println(pop());
        }
    }


    private static int pop() {
        int ans = heap[0];
        swap(0, size--);
        slipDown(0, size);
        return ans;
    }

    /* 弹出一个之后，尾巴上的东西来到头上，需要跟自己的儿子比较谁更小 */
    private static void slipDown(int index, int size) {
        // 先计算出自己的左子节点的位置，如果左子节点都没有，则必然没有右子节点
        int left = index * 2 + 1;
        // 有子节点的情况下进入循环
        while (left <= size){
            // 找到最小的子节点的下标
            int smallIndex = getSmallIndex(left);
            // 如果自己和儿子一样大，不用再往下比了
            if (heap[smallIndex] == heap[index]) return;
            // 否则，自己和自己的儿子交换位置，交换完之后，自己来到儿子的位置，继续跟孙子比
            swap(smallIndex, index);
            index = smallIndex;
            left = smallIndex * 2 + 1;
        }
    }

    private static int getSmallIndex(int left) {
        //    有右子节点吗？没有的话，左子节点就是最小 ： 比较左右大小（右边比左边小吗？返回右子节点的下标 ： 否则返回左子节点下标 ）
        return left + 1 > size ? left : ( heap[left+1] < heap[left] ? left + 1 : left );
    }

    public static void push(int val){
        size++;
        if (size == capacity) grow();
        heap[size] = val;
        slipUp(size);
    }

    /* 向上比较大小 */
    private static void slipUp( int index) {
        // 先用cur记录当前位置，因为有可能交换下来的，在后续过程中仍然不满足小根堆的定义
        int cur = index;
        // 开始向上比较大小，一直到当前的节点在每次交换完成之后都绝对是小根堆为止
        while (heap[cur] < heap[(cur - 1) / 2]){
            // 在一次交换完之后，有可能不满足小根堆，所以继续回到当前位置，继续向上滑
            index = cur;
            // 计算自己父亲的位置
            int father = (index - 1)  / 2;
            // 当自己比自己的父还要小的时候，交换位置，自己来到父的位置，继续跟爷爷比
            while(heap[index] < heap[father]){
                swap(index, father);
                index = father;
                father = (index - 1)  / 2;
            }
        }
    }

    private static void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private static void grow() {
        capacity = capacity << 1;
        int[] newHeap = new int[capacity];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    static int size(){
        return size + 1;
    }

}
