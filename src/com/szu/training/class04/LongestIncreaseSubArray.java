package com.szu.training.class04;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  最长递增子序列
 *
 * ends[i] 找到的所有长度为 i+1 的递增子序列中 最小的结尾是什么
 *
 * @Date 2021/4/15 18:12
 */

import com.szu.leetcode.utils.LeetCodes;

import static com.szu.leetcode.utils.LeetCodes.printArray;

public class LongestIncreaseSubArray {

    public static int[] lis(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getdp(arr);
        return generateLIS(arr, dp);
    }

    private static int[] generateLIS(int[] arr, int[] dp) {
        int maxLen = 0;
        int end = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > maxLen){

                maxLen = dp[i];
                end = i;
            }
        }
        int[] res = new int[maxLen];
        res[--maxLen] = arr[end];
        int curMax = arr[end];
        for (int i = end - 1; i >= 0; i--) {
            /*
            * 收集答案的时候可不能见到比 curMax 小的就收进结果集，还得注意他的 最长递增子序列长度
            * 只有在 等于 当前数组 位置的情况下 才收集进结果集！！！！！
            * */
            if (arr[i] < curMax && dp[i] == maxLen){
                res[--maxLen] = arr[i];
                curMax = arr[i];
            }
            if (maxLen == 0)
                break;

        }
        return res;
    }

    private static int[] getdp(int[] arr) {

        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        /* ends[i] 找到的所有长度为 i+1 的递增子序列中 最小的结尾是什么 */
        ends[0] = arr[0];
        dp[0] = 1;
        /* right 右边 无效区域 */
        int right = 0;
        int l = 0, r = 0, m = 0;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r){
                m = (l + r) / 2;
                /*
                * =======================
                * 此处的比较顺序也必须是 arr > ends
                * =======================
                * */
                if (arr[i] > ends[m])
                    l = m + 1;
                else
                    r = m - 1;
            }
            /*
            * 如果此时没有任何数 >= arr[i]，此时就要扩充有效区域
            * 也就是 让 right 扩大一下
            *
            * 此处的写法就是【如果没有找到值，l 最终回来到 right + 1 的位置】
            * 再把 l 赋值给 right，起到了扩充有效区域的作用
            * */
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = l + 1;
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] arr = { 1,1,1 };
        printArray(arr);
        printArray(lisRight(arr));
        printArray(lis(arr));

        for (int i = 0; i < 100000; i++) {
            int[] nums = LeetCodes.getRandomArray(50, 100);
            int[] right = lisRight(nums);
            int[] my = lis(nums);
            if (right.length != my.length)
                System.out.println("FUCK Length");
            for (int j = 0; j < right.length; j++) {
                if (right[j] != my[j])
                    System.out.println("FUCK not equal");
            }
        }


    }

    /*
    * 以下代码是完全正确版
    * */
    public static int[] lisRight(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getdpRight(arr);
        return generateLISRight(arr, dp);
    }

    public static int[] getdpRight(int[] arr) {
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    public static int[] generateLISRight(int[] arr, int[] dp) {
        int len = 0;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }
        int[] lis = new int[len];
        lis[--len] = arr[index];
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                lis[--len] = arr[i];
                index = i;
            }
        }
        return lis;
    }
}
