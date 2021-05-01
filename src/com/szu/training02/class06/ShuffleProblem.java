package com.szu.training02.class06;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * TODO 【下标转的我头疼】
 * 给定一个长度为偶数的数组 arr，长度记为 2 * N
 * 前 N个位左部分，后N个为右部分。
 * arr 可以表示为 {L1, L2, L3, ..., Ln, R1, R2, R3...., Rn}
 * 请将数组调整为 {R1, L1, R2, L2, .... Rn, Ln }的样子
 *
 * @Date 2021/5/1 11:02
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;
import java.util.Random;

public class ShuffleProblem {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 5000000; i++) {
            int[] arr1 = LeetCodes.getRandomArray(random.nextInt(100) * 2, 100);
//            int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
//            Arrays.sort(arr1);
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);
            wiggleSort(arr1);
            int[] violence = violence(arr2);
            for (int k = 0; k < arr1.length; k++) {
                if (arr1[k] != violence[k])
                    System.out.println("FUCK");
            }
        }
    }

    public static void wiggleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        if (arr.length % 2 == 0) {
            shuffle(arr, 0, arr.length - 1);
        } else {
            shuffle(arr, 0, arr.length - 2);
        }
    }

    public static void shuffle(int[] arr, int l, int r) {
        /*
         * 假设一个数组长度为 14 ，那么他的一半长度就为 7
         * L1, L2, L3, L4, L5, L6, L7 【←-|-→】 R1, R2, R3, R4, R5, R6, R7
         *
         * 然后我就用 (3^k) - 1 去找第一个小于等于 数组长度的数字 【2,8,26...】
         * 结果找到了 8，第一个小于 14【数组长度的】
         *
         * 然后我通过区间逆序法把数组调整为 如下形式
         * L1, L2, L3, L4, R1, R2, R3, R4 【←|→】 L5, L6, L7, R5, R6, R7
         * 左半区通过下标循环 怼出  R1, L1, R2, L2, R3, L3, R4, L5
         *
         * 剩下的右半区 继续通过这种方式找出下一个 区间，不断分解
         * 满足 (3^k) - 1 长度的下标循环位置 为 3^(k-1) 的位置
         *
         * 比如区间长度为 8的时候， 下标循环点的位置为 0，3
         * 比如区间长度为 26的时候， 下标循环点的位置为 0，3，9
         * */
        while (l <= r) {
            int len = r - l + 1;

            int leftPartLastIndex = l + len / 2 - 1;
            int rightPartFirstIndex = leftPartLastIndex + 1;

            int pow = 1;
            int floor = 2;
            int k = 0;
            /* 找到最合适的 (3^k) - 1， 赋值给 floor */
            while ((pow = (3 * pow)) - 1 <= len) {
                floor = pow - 1;
                k++;
            }
            /* 用来定位 左半部分应该逆序的位置 和 右半部分应该逆序的部分 */
            int half = floor / 2;
            /* 指定位置上的左半区 挪到右半区， 右半区挪到左半区 */
            rotate(arr, l + half, leftPartLastIndex, rightPartFirstIndex, rightPartFirstIndex + half - 1);
            /* 循环怼 */
            cycle(arr, l, floor, k);

            l = l + floor;
        }
    }

    /*
     * 从start位置开始，往右len的长度这一段，做下标连续推
     * 出发位置依次为1,3,9...
     * */
    private static void cycle(int[] arr, int start, int len, int k) {
        // 找到每一个出发位置trigger，一共k个
        // 每一个trigger都进行下标连续推
        // 出发位置是从1开始算的，而数组下标是从0开始算的。
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int preValue = arr[trigger + start - 1];
            int cur = modifyIndex2(trigger, len);
            while (cur != trigger) {
                int tmp = arr[cur + start - 1];
                arr[cur + start - 1] = preValue;
                preValue = tmp;
                cur = modifyIndex2(cur, len);
            }
            arr[cur + start - 1] = preValue;
        }
    }

    public static int modifyIndex2(int i, int len) {
        return (2 * i) % (len + 1);
    }

    /*
     * 整体逻辑就是，左部分逆序一下
     * 右部分逆序一下
     * 整体逆序一下，就交换完成了
     * */
    private static void rotate(int[] arr, int left, int leftEnd, int right, int rightEnd) {
        reverse(arr, left, leftEnd);
        reverse(arr, right, rightEnd);
        reverse(arr, left, rightEnd);
    }

    private static void reverse(int[] arr, int left, int right) {
        while (left < right) {
            int tem = arr[left];
            arr[left++] = arr[right];
            arr[right--] = tem;
        }
    }


    public static int[] violence(int[] arr) {
        int length = arr.length;
        int res[] = new int[length];
        int leftLast = length / 2 - 1;
        int resIndex = 0;
        int i = 0, j = leftLast + 1;
        while (i <= leftLast) {

            res[resIndex++] = arr[j++];
            res[resIndex++] = arr[i++];
        }
        return res;
    }
}
