package com.szu.practice.slide_window;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *             给定一个整形数组 arr，和一个整数 num
 *              某个arr中的子数组如果想达标必须满足：sub中的最大值 - sub最小值 <= num
 *              返回arr中达标子数组的数量
 *
 * @Date 2021/3/19 13:56
 */

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Random;

public class ValidLessThanNumSubArrays {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int[] arr = getRandomArray(20, 50);
//            printArray(arr);
            int num = 8;
            int validNums = getValidNums(arr, num);
            int validNumsViolence = getValidNumsViolence(arr, num);
//            System.out.println(validNums +"    "+ validNumsViolence);
            if (validNums != validNumsViolence){
                System.out.println("Ur code is a piece of shit");
                break;
            }
        }
    }

    /* 滑动窗口解 最优解 */
    private static int getValidNums(int[] arr, int num) {
        if (arr == null || arr.length == 0) return 0;
        // 滑动窗口中最大值，最小值
        ArrayDeque<Integer> maxQueue = new ArrayDeque<>();
        ArrayDeque<Integer> minQueue = new ArrayDeque<>();
        int validNum = 0;
        // 先从 l 往右边滑动， l--r 窗口中的最大值 - 最小值 如果满足 <= num， 就继续往窗口中加入新值
        int l = 0;
        int r = 0;
        while (l < arr.length){

            while ( r < arr.length ){
                // 两个窗口最大值最小值队列加入新值的时候，需要先判断是否满足条件，保证队列的严格单调性
                // maxQueue 严格从小到大   minQueue严格从大到小
                // 不满足的值都要从尾部弹出，直到找到合适的位置才加入到队列
                while ( !maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[r] )
                    maxQueue.pollLast();
                while ( !minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[r])
                    minQueue.pollLast();
                maxQueue.addLast(r);
                minQueue.addLast(r);
                // 窗口中的最大值 - 最小值 如果 > num， 就跳出当前循环
                if ( arr[maxQueue.peek()] - arr[minQueue.peek()] > num )
                    break;
                r++;
            }
            // 满足最大最小值差小于num的 就是----当前刚刚不满足条件的 r下标 - l下标
            validNum += (r - l);
            // 弹出过期值
            if (minQueue.peek() == l) minQueue.poll();
            if (maxQueue.peek() == l) maxQueue.poll();
            // 准备计算 l+1位置满足要求的子数组个数
            l++;
        }
        return validNum;
    }

    /* 暴力解 */
    public static int getValidNumsViolence(int[] arr, int num) {
        int validNum = 0;
        if (arr == null || arr.length == 0) return 0;
        for (int l = 0; l < arr.length; l++) {
            for (int r = l; r < arr.length; r++) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int i = l; i <= r; i++) {
                    if (arr[i] < min) min = arr[i];
                    if (arr[i] > max) max = arr[i];
                }
                if ( max - min <= num )
                    validNum++;
            }
        }
        return validNum;
    }






    private static int[] getRandomArray(int num, int bound) {
        Random random = new Random();
        int[] ret = new int[num];
        for (int i = 0; i < num; i++) {
            ret[i] = random.nextInt(bound);
        }
        return ret;
    }
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}
