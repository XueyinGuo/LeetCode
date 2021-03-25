package com.szu.leetcode;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/25 16:11
 */

import java.util.ArrayDeque;
import java.util.ArrayList;

public class L239_MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length )
            return new int[]{};
        ArrayList<Integer> res = new ArrayList<>();
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {

            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i] )
                deque.pollLast();
            deque.add(i);

            /* i - k + 1   正好是淘汰位置，如果窗口的第一个位置已经比现在窗口右边界小 k， 弹出他 */
            if (deque.peek() < i-k+1)
                deque.poll();
            // 窗口够大的时候开始收集答案
            if (i >= k-1){
                res.add(nums[deque.peek()]);
            }

        }
        int[] resArr = new int[res.size()];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }


    public static void main(String[] args) {
        int nums[] = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        new L239_MaxSlidingWindow().maxSlidingWindow(nums, k);
    }
}
