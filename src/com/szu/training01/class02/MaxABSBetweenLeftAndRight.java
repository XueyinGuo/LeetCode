package com.szu.training01.class02;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *      给定一个数组arr，长度为N，你可以把任意长度大于0 且小于N的前缀作为做部分，
 *      剩余作为右部分，但是每种划分下都有左部分最大值和右部分最大值，
 *
 *      请返回最大的 左部分的最大值减去右部分最大值的绝对值
 *
 * @Date 2021/4/10 13:42
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.ArrayDeque;

public class MaxABSBetweenLeftAndRight {


    public static int findMaxABS( int[] arr ){
        if (arr == null || arr.length < 2)
            return -1;
        ArrayDeque<Integer> rightQueue = new ArrayDeque<>();
        int index = 1;
        /*
         * 使用滑动窗口的求最大值法
         *
         * 从第二个数字开始的右侧全部加入 窗口最大值队列
         * */
        while (index < arr.length){

            while (index < arr.length && !rightQueue.isEmpty() && arr[rightQueue.peekLast()] <= arr[index])
                rightQueue.pollLast();
            rightQueue.offer(index++);

        }
        /*
         * 查看 rightQueue 最大值是否过期，过期就要弹出换下一个最大值
         *
         * leftMax 模拟左侧窗口找最大值
         * */
        int leftMax = arr[0];
        int ans = Math.abs(leftMax - arr[rightQueue.peek()] );
        for (int i = 1; i < arr.length - 1; i++) {
            leftMax = Math.max( leftMax, arr[i] );
            if ( rightQueue.peek() <= i )
                rightQueue.pollFirst();
            ans = Math.max( leftMax - arr[rightQueue.peek()], ans );
        }
        return ans;
    }

    /*
     * 直接找到数组中最大值，然后左右两边的值，谁小 返回 与谁的差值的绝对值
     *
     * 单调性： 随着左右两侧的扩充，最大值只可能比只有一个数字的时候大 或者 相等， 绝对不可能比一个数字的时候小
     * 然而整个数组的最大值必定在某一侧的部分
     *
     * 而且题目又让求的是 【与 某个最大值的 差值 的 最大值】所以最大值 最小的时候 肯定是问题的解，
     * 所以 直接找两侧的某一侧最小的部分即可
     * */
    public static int findMaxABSAwesome( int[] arr ){
        if (arr == null || arr.length < 2)
            return -1;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return arr[0] > arr[arr.length - 1] ? max - arr[arr.length - 1] : max - arr[0];
    }


    // for test
    public static void main(String[] args) {
        long windowTime = 0;
        long awesomeTime = 0;
        for (int i = 0; i < 1000000; i++) {
            int[] arr = LeetCodes.getRandomArray(50, 100);
            long windowStart = System.currentTimeMillis();
            int maxABS = findMaxABS(arr);
            long windowEnd = System.currentTimeMillis();
            windowTime += windowEnd - windowStart;


            long awesomeStart = System.currentTimeMillis();
            int maxABSAwesome = findMaxABSAwesome(arr);
            long awesomeEnd = System.currentTimeMillis();
            awesomeTime += awesomeEnd - awesomeStart;
            if (maxABS != maxABSAwesome)
                System.out.println("FUCK");
        }
        System.out.println("widowTime : "+ windowTime);
        System.out.println("awesomeTime : "+ awesomeTime);
    }
}
