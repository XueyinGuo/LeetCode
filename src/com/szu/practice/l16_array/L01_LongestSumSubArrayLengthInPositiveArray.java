package com.szu.practice.l16_array;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      查看有无单调性。如果数组中全是正数，那么这个数组肯定存在单调性，既然有单调性，那么这个题就适合使用 滑动窗口 或者 首尾指针 等来解。
 *
 *      例题：
 *          正数数组中，累加和等于 num 的子数组最长的长度
 *
 * @Date 2021/3/28 20:05
 */

public class L01_LongestSumSubArrayLengthInPositiveArray {



    public static void main(String[] args) {
        int[] ints = {7, 6, 1, 4, 4, 2, 6, 4, 1, 10, 2, 1, 4, 3, 4, 4, 4, 1, 4, 3};
        int k = 8;
        int ans11 = getMaxLength(ints, k);
        int ans22 = right(ints, k);
        int len = 20;
        int value = 10;
        int testTime = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generatePositiveArray(len, value);
            int K = (int) (Math.random() * 20) + 1;
            int ans1 = getMaxLength(arr, K);
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


    /*
     * 滑动窗口求解，遍历一次即可结束
     * */
    private static int getMaxLength(int[] arr, int num) {
        int l = 0;
        int r = 0;
        // 这是一个 左闭右闭的区间，为什么这么写呢？ 因为代码更简洁
        // 调试经验：当设定一个 左闭右开的区间的时候，代码将会异常丑陋
        int windowSum = arr[0];
        int maxLen = 0;
        while (r < arr.length){
            // 窗口和正好等于 num 的时候更新答案
            // 而且更新完答案之后需要 对当前的窗口做收缩操作，否则跳不出循环了
            if (windowSum == num){
                maxLen = Math.max(maxLen, r-l+1);
                windowSum -= arr[l++];
            }else if (windowSum < num){
                // 窗口向右扩张
                r++;
                if (r == arr.length)
                    break;
                windowSum += arr[r];
            }else
                // 窗口内和已经大了，所以收缩
                windowSum -= arr[l++];

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

    public static int[] generatePositiveArray(int size, int value) {
        int[] ans = new int[size];
        for (int i = 0; i != size; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
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
}
