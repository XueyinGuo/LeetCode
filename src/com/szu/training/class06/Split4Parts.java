package com.szu.training.class06;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个正数数组 arr,返回该数组能不能切成四个部分，并且每个部分的累加和相等，
 * 切分的位置不要
 *
 * arr = [3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2]
 * 返回 true
 * 三个切割点 下边为 2  5  7
 * 切出的 四个子数组 [3，2]  [1, 4] [5] [1,2,2],累加和 为 5
 *
 * TODO 有BUG
 *
 * @Date 2021/4/21 20:59
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.HashMap;
import java.util.HashSet;

public class Split4Parts {

    public static void main(String[] args) {
        int testTime = 3000000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = LeetCodes.getRandomArray(10, 5  );
            for (int j = 0; j < arr.length; j++) {
                arr[j] += 1;
            }
//            int[] arr = {3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2};
//            int[] arr = {4, 0, 2, 2, 1, 4, 0,  3, 3, 1};
            if (canSplits1(arr) ^ myCanSplits(arr)) {
                System.out.println("FUCK");
            }
        }
    }

    private static boolean myCanSplits(int[] arr) {

        if (arr == null || arr.length == 0)
            return false;

        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            map.put(sum, i);
        }

        int leftSum = 0;
        int left = 0;
        int i = 0;
        int parts = 1;

        while (i < arr.length){

            left += arr[i];
            Integer index = map.get(left);
            while (index != null && parts <= 4){
                index++;
                if (index >= arr.length)
                    break;
                leftSum += left + arr[index];
                index = map.get(leftSum + left);
                parts++;
            }
            if (parts == 4)
                return true;
            parts = 1;
            leftSum = 0;
            i++;
        }
        
        return false;
    }


    /*
    * 大神的正确代码
    * */
    public static boolean canSplits1(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        HashSet<String> set = new HashSet<String>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int leftSum = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            set.add(String.valueOf(leftSum) + "_" + String.valueOf(sum - leftSum - arr[i]));
            leftSum += arr[i];
        }
        int l = 1;
        int lsum = arr[0];
        int r = arr.length - 2;
        int rsum = arr[arr.length - 1];
        while (l < r - 3) {
            if (lsum == rsum) {
                String lkey = String.valueOf(lsum * 2 + arr[l]);
                String rkey = String.valueOf(rsum * 2 + arr[r]);
                if (set.contains(lkey + "_" + rkey)) {
                    return true;
                }
                lsum += arr[l++];
            } else if (lsum < rsum) {
                lsum += arr[l++];
            } else {
                rsum += arr[r--];
            }
        }
        return false;
    }

}
