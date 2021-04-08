package com.szu.training.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      给定一个数组arr，从左到右依次表示X轴上从左往右点的位置
 *      给定一个整数K，返回如果有一根长度为K的绳子，最多能盖住几个点，
 *      绳子的边缘触碰到x轴上的点就算是覆盖住
 *
 * @Date 2021/4/7 10:20
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;

public class CordCoverMaxPoint {

    public static int coverMaxPoint(int[] arr, int k){
        // 滑动窗口
        int l = 0 , r = 0;
        int count = 0; // 窗口中的数值数量
        int max = 0;    // 抓取最大值
        for (int i = 0; i < arr.length; i++) {
            /*
            * 因为题目中说  绳子的边缘触碰到x轴上的点就算是覆盖住, 所以不用 - 1
            * while 知道 r 位置的数字 - l位置的数字 > K 时候推出
            * */
            while (r < arr.length && arr[r] - arr[l] /* - 1 */ <= k){
                count++;
                r++;
            }
            // 抓取此时的最大值，然后窗口向左移动，窗口中的数值也要 - 1
            max = Math.max(count, max);
            l++;
            count--;
        }
        return max;
    }


    public static int maxPoint1(int[] arr, int L) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr, i, arr[i] - L);
            res = Math.max(res, i - nearest + 1);
        }
        return res;
    }

    public static int nearestIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }


    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    public static void main(String[] args) {
        int array[] = {48, 60, 155, 167, 168, 176, 196, 198, 220, 252, 262, 303, 315, 328, 345, 349, 413,
                428, 463, 464, 476, 494, 512, 596, 611, 618, 641, 675, 716, 724,
                747, 754, 755, 760, 801, 804, 837, 959, 972, 988};
        int K = 339;
        int ans11 = maxPoint1(array, K);
        int ans22 = coverMaxPoint(array, K);
        int ans33 = test(array, K);
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint1(arr, L);
            int ans2 = coverMaxPoint(arr, L);
            int ans3 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                /*
                 * 字符是0的情况下考虑都不考虑
                 * */
                LeetCodes.printArray(arr);
                System.out.println("K: " + L);
                System.out.println("oops!");
                break;
            }
        }

    }

}
