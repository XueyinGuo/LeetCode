package com.szu.practice;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/21 20:38
 */

import java.util.ArrayDeque;
import java.util.ArrayList;

public class MaxValueInSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {

        if(k == 0 || nums.length == 0)
            return new int[0];

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < k; i++){
            enqueue(nums, i, nums[i], queue);
        }
        list.add(queue.peek());
        queue.poll();
        int l = 1, r = k;
        while(r < nums.length){
            enqueue(nums, r, nums[r], queue);
            dequeue(l, queue, list, k);

            r++;
            l++;
        }
        int[] res = new int[list.size()];
        for(int i = 0; i<list.size(); i++)
            res[i] = nums[list.get(i)];
        return res;

    }

    public void enqueue(int[] nums, int index,int cur, ArrayDeque queue ){


        while( !queue.isEmpty() && nums[(Integer) queue.peekLast()] <= cur ){

            queue.pollLast();

        }
        queue.offerLast(index);

    }

    public void dequeue(int index, ArrayDeque queue, ArrayList list, int max){
        while(queue.size() > max){
            queue.poll();
        }
        int i = (Integer)queue.peek();
        list.add(i);
        if(index == i){
            queue.poll();
        }
    }


    public static void main(String[] args) {
        int[] ints = {7,2,4};

        int k = 2;
        int[] ints1 = new MaxValueInSlidingWindow().maxSlidingWindow(ints, k);
        for (int i:ints1             ) {
            System.out.println(i);
        }

    }
}
