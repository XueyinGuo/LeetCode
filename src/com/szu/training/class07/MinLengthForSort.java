package com.szu.training.class07;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/22 12:10
 */

import com.szu.leetcode.utils.LeetCodes;

public class MinLengthForSort {

    /*
    * a 为 每个位置遍历到的数字
    *
    * 1. 先从左往右遍历
    *
    * max左 > a   叉号
    * max左 <= a  对号   更新
    * 记录最右侧的叉号（因为最右侧叉号之后全是对号，后边的顺序都是不用动的）
    *
    * 2. 再从右往左遍历
    *
    * min右 < a   叉号
    * min右 >= a  对号   更新
    * 记录最左侧的叉号（在左都是对号，也是不用动的）
    *
    * */

    public static void main(String[] args) {
        for (int i = 0; i < 10000000; i++) {
            int[] arr = LeetCodes.getRandomArray(20, 200);
            if (getMinSortLength(arr) != getMinLength(arr)) {
                System.out.println("FUCK");
            }
        }


    }

    private static int getMinSortLength(int[] arr) {

        if (arr == null || arr.length == 0)
            return 0;

        int noMaxIndex = 0;
        int leftMax = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= leftMax)
                leftMax = arr[i];
            else
                noMaxIndex = i;
        }
        if (noMaxIndex == 0)
            return 0;

        int rightMin  = arr[arr.length-1];
        int noMinIndex = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] <= rightMin)
                rightMin = arr[i];
            else
                noMinIndex = i;
        }
        return noMaxIndex - noMinIndex + 1;
    }

    /*
    * 大神
    * */
    public static int getMinLength(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int min = arr[arr.length - 1];
        int noMinIndex = -1;
        for (int i = arr.length - 2; i != -1; i--) {
            if (arr[i] > min) {
                noMinIndex = i;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        if (noMinIndex == -1) {
            return 0;
        }
        int max = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i != arr.length; i++) {
            if (arr[i] < max) {
                noMaxIndex = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }

}
