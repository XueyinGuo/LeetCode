package com.szu.practice.slide_window;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/21 20:38
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class MaxValueInSlidingWindow {

    public static void main(String[] args) {
        int[] arr = {4,3,5,4,3,3,6,7};

        int window = 3;
        List<Integer> res = new MaxValueInSlidingWindow().maxSlidingWindow(arr, window);
        for (Integer i:res ) {
            System.out.println(i);
        }

    }

    private List maxSlidingWindow(int[] arr, int window) {

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> res = new ArrayList<>();
        // 固定窗口大小为 3，从第一个开始进窗口，不够三个就先等等
        for (int r = 0; r < arr.length; r++) {
            /* 这是一个单调递增的队列，单调递增队列判断窗口中额最大值，队列中只存储数组的下标 */
            // 当队列不为空，且准备新加到队列的值，比队列尾部的值 >= 的时候，队尾弹出元素
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[r])
                queue.pollLast();
            // 此时要么队列为空，要么队列中最后一个值已经比当前的值严格大于了，加到对列中
            queue.addLast(r);
            // 查看队头的元素是否已经过期了，如果队头元素和当前下标的距离 >=
            // 弹出该元素
            if ( r - queue.peek() >= window  )
                queue.poll();
            // 过期元素弹出之后，收集一次结果
            if (r >= window-1 )
                res.add(arr[queue.peek()]);
        }
        return res;

    }
}
