package com.szu.practice.l16_array;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *          题目：数组有正有负， 子数组和 小于等于 num 为达标子数组， 求最长的达标子数组的长度
 *
 *          =============================================================
 *          =============================================================
 *          这道题的精髓就是 部分答案可能性可以直接舍弃掉！！！
 *          可能性丢弃的题目优化应该是难度超级难的题目！！！！
 *          =============================================================
 *          =============================================================
 *
 * @Date 2021/3/28 20:05
 */

public class L03_LongestSumSubArrayLength {

    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = {8, -3, 4, 5, 8, 7, 3, 4, 2, 5};
        int num = 3;
        int res = maxLengthAwesome(array, 3);
        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            int awesome = maxLengthAwesome(arr, k);
            int maxLength = maxLength(arr, k);
            int violence = maxLengthViolence(arr, k);
            if (awesome != maxLength || awesome != violence || violence != maxLength) {
                System.out.println(i);
                printArray(arr);
                System.out.println("FUCK!");
            }
        }
        System.out.println("test finish");
    }

    private static int maxLengthAwesome(int[] arr, int num) {
        int length = arr.length;
        int[] minSum = new int[length];
        int[] minSumEnd = new int[length];
        /*
        * 从后往前构建两个辅助数组
        * minSum 的每个位置是 对应arr位置加上后边可以使自己变小的后缀和，
        *       即 后边为负数或者0的后缀和，就加上他，然后把结果存在 minSum 的对应位置
        *
        * minSumEnd 存储的是 ： 如果后边为负数或者0， 则填后边位置的一样的下标值
        *               否则只记录自己的下标值
        *
        * 例如：
        *     index  0     1        2       3       4      5      6       7        8       9      10
        *       arr [7     3       -2      -4       5     -6      2       4       -1      -3      3]
        *    minSum [3    -4       -7      -5      -1     -6      2       0       -4      -3      3]
        * minSumEnd [5     5        5       5       5      5      9       9        9       9     10]
        * */
        minSum[length-1] = arr[length-1];
        minSumEnd[length-1] = length-1;
        for (int i = length - 2; i >= 0 ; i--) {
            if (minSum[i+1] <= 0){
                minSum[i] = arr[i] + minSum[i+1];
                minSumEnd[i] = minSumEnd[i+1];
            }else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }
        int right = 0;
        int left = 0;
        int curSum = 0;
        int maxLenSubArr = 0;
        while (right < length){
            /*
            * right不断右跳，跳过的部分的和，在 minSum 和 minSumEnd的加持下，
            * 每次右跳的位置都是以 left 开头和最小的位置
            *
            *     index  0     1        2       3       4      5      6       7        8       9      10
            *       arr [7     3       -2      -4       5     -6      2       4       -1      -3      3]
            *    minSum [3    -4       -7      -5      -1     -6      2       0       -4      -3      3]
            * minSumEnd [5     5        5       5       5      5      9       9        9       9     10]
            *           |                                             |
            *           left                                        right
            *
            * 跳出这个 while 之后， right 已来到【加上下一段之后不能再继续达标的】位置
            *
            * */
            while (right < length){
                int sum = curSum + minSum[right];
                if (sum <= num){
                    curSum = sum;
                    right = minSumEnd[right] + 1;
                }else
                    break;

            }
            /*
            * 跳出循环时已经是以 left 开头的最长的时候了
            *
            * 所以此时更新答案
            *
            * 所以此时 不再考虑 left + 1 到 right 的可能性，因为他绝对不可能再比 left -- right 长了
            * 所以 left 右滑， 看能否把 right 的下一段包住，包不住的话  left继续右滑
            *
            * 如果 left = right 的时候仍然不能包住下一段，则上一段已经无解了，
            * left right 同时右滑
            *
            * 所以 O(N)拿下
            * */
            maxLenSubArr = Math.max(right - left, maxLenSubArr);
            if (left == right){
                left ++;
                right++;
            }else
                curSum -= arr[left++];

        }
        return maxLenSubArr;
    }


    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }


    private static int maxLengthViolence(int[] arr, int num) {
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            int len = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                len++;
                if (sum <= num)
                    maxLen = Math.max(len, maxLen);
            }
        }
        return maxLen;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
