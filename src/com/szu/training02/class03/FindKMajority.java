package com.szu.training02.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个数组 arr,如果有某个数出现了次数超过了数组长度一半
 * 打印这个数， 如果没有就不打印
 * 额外空间 O(1)
 *
 * @Date 2021/4/27 16:28
 */

import com.szu.leetcode.utils.LeetCodes;

public class FindKMajority {

    public static int getMoreThanHalf(int arr[]) {
        if (arr == null || arr.length == 0)
            return Integer.MIN_VALUE;
        /*
        * 每当数字的"血量"==0 的时候，更换candidate，
        * 遍历到的数字与 candidate 不一样的时候，candidate扔掉
        * 同时 HP--，
        * 模拟两个数字全部扔掉，每次从数组删除两个数字
        * 最终 如果 HP == 0 肯定没有超过一半数量的数字
        * 如果HP！= 0
        * 也不能保证这个数字肯定大于一半次数出现
        * 需要重新遍历一遍数组，确定这个数字是否真的出现了一半次数以上 */
        int candidate = 0;
        int HP = 0;
        for (int i = 0; i < arr.length; i++) {
            if (HP == 0){
                HP = 1;
                candidate = arr[i];
            }
            else if (candidate == arr[i])
                HP++;
            else
                HP--;
        }
        HP = 0;
        /*
        * 重新遍历一次，如果最后统计也不大于一半，返回系统最小值当做无效
        * */
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == candidate)
                HP++;
        }
        if (HP > arr.length / 2)
            return candidate;
        return Integer.MIN_VALUE;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10000000; i++) {
            int[] arr = LeetCodes.getRandomArray(15, 3);
            int moreThanHalf = getMoreThanHalf(arr);
            int right = printHalfMajor(arr);
            if (right != moreThanHalf)
                System.out.println("FUCK");
        }
    }


    /*
    * right 代码
    * */
    public static int printHalfMajor(int[] arr) {
        int cand = 0;
        int HP = 0;
        for (int i = 0; i < arr.length; i++) {
            if (HP == 0) {
                cand = arr[i];
                HP = 1;
            } else if (arr[i] == cand) {
                HP++;
            } else {
                HP--;
            }
        }
        if(HP == 0) {

            return Integer.MIN_VALUE;
        }
        HP = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == cand) {
                HP++;
            }
        }
        if (HP > arr.length / 2) {
            return cand;
        } else {
            return Integer.MIN_VALUE;
        }
    }
}
