package com.szu.training01.class07;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 最小不可组成和
 *
 * @Date 2021/4/22 13:36
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;

public class MinPatches {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] test = LeetCodes.getRandomArray(10, 30);
            int n = 2147483647;
            if (minPatches(test, n) != minPatchesRight(test, n)) {
                System.out.println("FUCK");
            }
        }


    }

    private static int minPatches(int[] arr, int dst) {
        /* 一开始需要补充的数 为 0个 */
        int patches = 0;
        /* 现在我已经有了 1...range 上的数可以全部组成了 */
        int range = 0;

        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            /*
            * 数组从 第0个开始遍历
            *
            * 如果 我当前可以形成的 全部组成范围 <  arr[i]
            *
            * 那么 我自己应该补充数字，来达到这个 arr[i]
            * 补充的数字 就是 当前的 range + 1，
            * 比如 我当前形成的 为 0， 补充 1，让我可以形成 1--1上可以任意形成
            * 然后补充 2， 让我可以 1--3 上任意组成， 然后补充 4， 1--7 ，再补充 8， 1--15
            * */
            while (arr[i] > range + 1){
                range += range + 1;
                patches++;
                if (range >= dst){
                    return patches;
                }
            }
            range += arr[i];
        }
        /* 数组中的数用完了，仍然没达到目标，继续补充 range + 1 */
        while (range + 1 < dst){
            range += range + 1;
            patches++;
        }
        return patches;
    }



    // arr请保证有序，且正数
    public static int minPatchesRight(int[] arr, int aim) {
        int patches = 0; // 缺多少个数字
        long range = 0; // 已经完成了1 ~ range的目标
        Arrays.sort(arr);
        for (int i = 0; i != arr.length; i++) {
            // 1~range
            // 1 ~ arr[i]-1
            while (arr[i] - 1 > range) { // arr[i] 1 ~ arr[i]-1
                range += range + 1; // range + 1 是缺的数字
                patches++;
                if (range >= aim) {
                    return patches;
                }
            }
            range += arr[i];
            if (range >= aim) {
                return patches;
            }
        }
        while (aim >= range + 1) {
            range += range + 1;
            patches++;
        }
        return patches;
    }
}
