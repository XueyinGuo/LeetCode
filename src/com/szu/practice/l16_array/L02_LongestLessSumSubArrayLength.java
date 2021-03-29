package com.szu.practice.l16_array;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *       查看有无单调性。如果数组中全是正数，那么这个数组肯定存在单调性，既然有单调性，那么这个题就适合使用 滑动窗口 或者 首尾指针 等来解。
 *       但是如果数组中有负数，则单调性已经不存在了，所以应该怎么解呢？
 *
 *      ===========================================================================
 *      ===========================================================================
 *      解决数组的子数组问题的时候常用的两个套路：
 *         1. 假设以每个位置开头的情况下，问题的答案是什么，如果每个位置都求出来，正确答案比在其中
 *         2. 假设以每个位置结尾的情况下，问题的答案是什么，如果每个位置都求出来，正确答案比在其中
 *      ===========================================================================
 *      ===========================================================================
 *      例题1：
 *          正数数组中，累加和等于 num 的子数组最长的长度
 *
 *      例题2.
 *          子数组中含有1和含有2的数量一样多的子数组称为达标数组，数组中寻找最长的达标子数组的长度。
 *                  就可以使用 这样解：
 *
 *                      遍历一遍数组，把所有非1 非2 的数变成 0， 把所有 1 不动， 把所有 2 变成 -1
 *                      查找所有子数组和为0的最长的
 *
 * @Date 2021/3/28 20:05
 */

import java.util.HashMap;

public class L02_LongestLessSumSubArrayLength {


    public static int maxLength(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        // map 中 放置的是 sum前缀和 出现的 位置，当我们要查询以某个数字结尾的子数组是否达标的时候
        // 直接去map 中找到 与 num的差值出现的第一个位置，即可判定当前数字结尾满足要求的最长子数组了
        // 我们再map中只记录第一次相同前缀和出现的位置
        HashMap<Integer, Integer> map = new HashMap<>();
        /*
        * 一开始的时候一定要放入 初始和为 0 的位置 为 -1，否则就会错过一些答案
        * 比如
        * 3  -3  7，  num = 7
        * 最长的数组长度为 3
        * */
        map.put(0, -1);
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (!map.containsKey(sum))
                map.put(sum, i);
            // 差值
            int dif = sum - k;
            Integer index;
            // 获取前缀和正好为 差值 的 下标
            if ( (index =  map.get(dif) ) != null){
                maxLen = Math.max(maxLen, i - index);
            }
        }
        return maxLen;

    }


    private static int right(int[] arr, int num) {
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == num)
                    maxLen = Math.max(j - i + 1, maxLen);
            }

        }
        return maxLen;
    }

    // for test
    public static int[] generateRandomArray(int size, int value) {
        int[] ans = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 500000;

        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int K = (int) (Math.random() * value) - (int) (Math.random() * value);
            int ans1 = maxLength(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                System.out.println(i);
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");

    }
}
