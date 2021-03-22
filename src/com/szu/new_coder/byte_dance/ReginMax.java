package com.szu.new_coder.byte_dance;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 13:55
 */

import java.util.*;

public class ReginMax {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nums = sc.nextInt();
        int arr[] = new int[nums];
        for (int i = 0; i < nums; i++) {
            arr[i] = sc.nextInt();
        }
        int max = findRes(arr);
        System.out.println(max);
    }

    public static int findRes(int arr[]) {
        int sumArray[] = new int[arr.length];
        int max = Integer.MIN_VALUE;
        sumArray[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sumArray[i] = sumArray[i - 1] + arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = findMin(arr, i, j);
                int sum = i == j ? arr[j]: i==0 ? sumArray[j] : sumArray[j] - sumArray[i-1];
                if ((min * sum) > max) max = (min * sum);
            }
        }
        return max;
    }

    public static int findMin(int arr[], int start, int end) {
        int min = Integer.MAX_VALUE;
        for (int j = start; j <= end; j++) {
            if (arr[j] < min) min = arr[j];
        }
        return min;
    }

}
