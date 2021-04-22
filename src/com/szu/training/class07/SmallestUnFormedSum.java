package com.szu.training.class07;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 最小不可组成和
 *
 * TODO 背包解法
 *
 * @Date 2021/4/22 12:29
 */

import java.util.Arrays;
import java.util.HashSet;

public class SmallestUnFormedSum {

    public static void main(String[] args) {
        int[] arr = {9, 6, 16, 27, 2, 12, 18, 4, 13, 2, 1, 20, 3, 26, 28, 18, 20, 19, 7, 8, 24, 10, 11, 29, 15, 27, 6};
        int violence = unformedSumViolence(arr);
//        int dp = unformedSumDp(arr);
        int awesome = unformedSumAwesome(arr);
        System.out.println(violence);
        System.out.println(awesome);
    }


    private static int unformedSumAwesome(int[] arr) {
        if (arr == null || arr.length == 0)
            return 1;
        Arrays.sort(arr);
        int range = 1;
        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > range + 1)
                return range + 1;
            else
                range += arr[i];

        }
        return range + 1;

    }

//    private static int unformedSumDp(int[] arr) {
//        if (arr == null || arr.length == 0)
//            return 1;
//        int sum = 0;
//        int min = Integer.MAX_VALUE;
//        for (int i = 0; i != arr.length; i++) {
//            sum += arr[i];
//            min = Math.min(min, arr[i]);
//        }
//
//
//    }

    /*
     * 暴力递归，生成所有答案加到 set 中
     * */

    private static void violenceGenerateAll(int[] arr, int index, int sum, HashSet<Integer> set) {
        if (index == arr.length) {

            set.add(sum);
            return;
        }
        violenceGenerateAll(arr, index + 1, sum + arr[index], set);
        violenceGenerateAll(arr, index + 1, sum, set);

    }

    private static int unformedSumViolence(int[] arr) {

        if (arr == null || arr.length == 0)
            return 1;

        HashSet<Integer> set = new HashSet<>();
        violenceGenerateAll(arr, 0, 0, set);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i != arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        for (int i = min + 1; i != Integer.MIN_VALUE; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }


}
