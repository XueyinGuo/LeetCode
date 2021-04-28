package com.szu.training02.class04;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定两个整数数组 A 和 B
 * A是长度 为 m
 * B长度为 n
 * 两个数组都已排好序
 * 希望从A 和 B 数组中找出 第 K 大的数字
 *
 * @Date 2021/4/27 17:53
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;
import java.util.Random;

public class FindKthMinNumberInTwoArray {

    public static int getUpMedian(int[] arr1, int[] arr2) {
        int length = arr1.length;
        int start1 = 0, start2 = 0;
        int end1 = length - 1, end2 = length - 1;
        int mid1, mid2;
        while (start1 < end1) { /* 在迭代中肯定能保证两个数组等长 */
            mid1 = start1 + (end1 - start1) / 2;
            mid2 = start2 + (end2 - start2) / 2;
            if (arr1[mid1] == arr2[mid2])
                return arr1[mid1];
            /* 偶数个元素的时候 */
            if ((start1 - end1 + 1) % 2 == 0) {
                /*
                 * 两个数组中 第一个数组中位数 > 第二个数组中位数
                 * */
                if (arr1[mid1] > arr2[mid2]) {
                    end1 = mid1;
                    start2 = mid2 + 1;
                } else if (arr1[mid1] < arr2[mid2]) {
                    start1 = mid1 + 1;
                    end2 = mid2;
                }
            } else {
                /* 奇数个元素的时候 */
                /*
                 * 两个数组中 第一个数组中位数 > 第二个数组中位数
                 * */
                if (arr1[mid1] > arr2[mid2]) {
                    if (arr2[mid2] >= arr1[mid1 - 1]) // 手动扣一下， 满足条件直接返回
                        return arr2[mid2];
                    end1 = mid1 - 1;
                    start2 = mid2 + 1;

                } else if (arr1[mid1] < arr2[mid2]) {
                    if (arr1[mid1] >= arr2[mid2 - 1]) // 手动扣一下  满足条件直接返回
                        return arr1[mid1];
                    start1 = mid1 + 1;
                    end2 = mid2 - 1;
                }
            }
        }
        return Math.min(arr1[start1], arr2[start2]);
    }











    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            int len = random.nextInt(1000) + 50;
            int bound = random.nextInt(1000) + 50;
            int[] arr1 = LeetCodes.getRandomArray(len, bound);
            int[] arr2 = LeetCodes.getRandomArray(len, bound);
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            int my = getUpMedian(arr1, arr2);
            int right = getUpMedian(arr1, 0, len - 1, arr2, 0, len - 1);
            if (my != right)
                System.out.println("FUCK");
        }
    }


    // A[s1...e1]
    // B[s2...e2]
    // 这两段一定等长且都有序
    // 求这两段整体的上中位数，上中位数值返回
    public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            if (A[mid1] == B[mid2]) {
                return A[mid1];
            }
            if (((e1 - s1 + 1) & 1) == 1) { // 奇数长度
                if (A[mid1] > B[mid2]) {
                    if (B[mid2] >= A[mid1 - 1]) {
                        return B[mid2];
                    }
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                } else { // A[mid1] < B[mid2]
                    if (A[mid1] >= B[mid2 - 1]) {
                        return A[mid1];
                    }
                    e2 = mid2 - 1;
                    s1 = mid1 + 1;
                }
            } else { // 偶数长度
                if (A[mid1] > B[mid2]) {
                    e1 = mid1;
                    s2 = mid2 + 1;
                } else {
                    e2 = mid2;
                    s1 = mid1 + 1;
                }
            }
        }
        return Math.min(A[s1], B[s2]);
    }

}
