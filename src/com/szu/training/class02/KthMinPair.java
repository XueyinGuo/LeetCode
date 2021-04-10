package com.szu.training.class02;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      长度为N的数组Arr，一定可以组成 N^2 个数值对
 *      例如 [3, 1, 2]
 *      (3, 3) (3, 1) (3, 2) (1, 3) (1, 1) (1, 2) (2, 3) (2, 1) (2, 2)
 *
 *      数值对的排序规则 ： 第一个数字 由低到高， 第一个数字一样的话， 第二个数字 由低到高
 *
 *      给定一个数组，和整数 k ，返回第 k小的数值对
 *
 * @Date 2021/4/10 18:23
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class KthMinPair {

    public static void main(String[] args) {

        Random random = new Random();

        for (int i = 0; i < 1000000; i++) {
            int[] arr = LeetCodes.getRandomArray(1000, 1000);
            int k = random.nextInt(900000);
            int[] violence = getKMinPairViolence(arr, k);
            int[] sort = getKMinPairSort(arr, k);
            int[] awesome = getMinPairAwesome(arr, k);
            if (violence[0] != sort[0] || violence[1] != sort[1] ||  violence[1] != awesome[1] || violence[0] != awesome[0])
                System.out.println("FUCK");
        }
    }

    /*
    * bfprt + quick sort 直接查找 数组中 第K小的 元素
    * */
    public static int[] getMinPairAwesome(int[] arr, int k){
        if (arr == null || arr.length == 0)
            return null;
        int len = arr.length;
        if (k > len * len)
            return null;
        k = k - 1; // 想要第一小，其实就是想要数组的 0 号位置
        Random random = new Random();
        int firstNum = getKMin(arr, k / len, 0, len - 1, random); /* bfprt + quick sort 直接查找 数组中 第K小的 元素 */
        /*
         * 排序之后，计算对应的下变为
         * 第一个数字就是 ： k / len
         * 第二个数字 就是   k / len / equalSize， 因为对于重复的数字，每个位置都可以搞定 equalSize 个
         * */
        int lessThanFirst = 0;
        int equalFirst = 0;
        for (int i = 0; i < len && arr[i] <= firstNum; i++) {
            if (arr[i] < firstNum)
                lessThanFirst++;
            else
                equalFirst++;
        }
        int rest = k - lessThanFirst * len;
        int secondNum = getKMin(arr, rest / equalFirst, 0, len - 1, random);  /* bfprt + quick sort 直接查找 数组中 第K小的 元素 */
        return new int[]{firstNum, secondNum};
    }

    private static int getKMin(int[] arr, int k, int low, int high, Random random) {

        int pivot = arr[low + ( random.nextInt( high - low + 1)   ) ];
        int[] range = patition(arr, low, high, pivot);
        if (k >= range[0] && k <= range[1])
            return arr[k];

        if (k > range[1])
            return getKMin(arr, k, range[1] + 1, high, random);
        else
            return getKMin(arr, k, low, range[0] - 1, random);
    }

    private static int[] patition(int[] arr, int low, int high, int pivot) {

        int less = low;
        int large = high;
        int cur = less;
        while (cur != large){
            if (arr[less] > pivot)
                swapNum( arr, cur, large-- );
            else if (arr[less] < pivot){
                swapNum(arr, cur, less);
                cur++;
                less++;
            }else
                cur++;
        }
        return new int[]{less, large};
    }

    private static void swapNum(int[] arr, int i, int j) {
        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    /*
    * 排序之后，计算对应的下变为
    * 第一个数字就是 ： k / len
    * 第二个数字 就是   k / len / equalSize， 因为对于重复的数字，每个位置都可以搞定 equalSize 个
    * */
    public static int[] getKMinPairSort(int[] arr, int k) {
        if (arr == null || arr.length == 0)
            return null;
        int len = arr.length;
        if (k > len * len)
            return null;
        k = k - 1; // 想要第一小，其实就是想要数组的 0 号位置
        Arrays.sort(arr);
        int firstNum = arr[k / len];
        int lessThanFirst = 0;
        int equalFirst = 0;
        for (int i = 0; i < len && arr[i] <= firstNum; i++) {
            if (arr[i] < firstNum)
                lessThanFirst++;
            else
                equalFirst++;
        }
        int rest = k - lessThanFirst * len;
        int restSecond = rest / equalFirst;
        return new int[]{firstNum, arr[restSecond]};
        
    }

    /*
    * 暴力生成所有的对子， 然后对所有的对子排序，返回第 K 个对子
    * */
    public static int[] getKMinPairViolence(int[] arr, int k) {
        if (arr == null || arr.length == 0)
            return null;
        int len = arr.length;
        if (k > len * len)
            return null;
        k = k - 1; // 想要第一小，其实就是想要数组的 0 号位置
        Pair[] pairs = new Pair[len * len];
        int index = 0;
        // 暴力生成所有对子
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                pairs[index++] = new Pair(arr[i], arr[j]);
            }
        }
        // 对子的排序
        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.x != o2.x)
                    return o1.x - o2.x;
                return o1.y - o2.y;
            }
        });
        return new int[]{pairs[k].x, pairs[k].y};
    }

}

class Pair{

    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
