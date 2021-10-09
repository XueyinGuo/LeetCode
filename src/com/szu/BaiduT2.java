package com.szu;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;

public class BaiduT2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int start = scanner.nextInt();
            int[] nums = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                nums[i] = scanner.nextInt();
            }

            System.out.println(CoinPick(nums, start));
        }
    }

    private static int CoinPick(int[] nums, int start) {
        if (nums[start] == 0)
            return 0;

        int l = getPreOne(nums, start);
        int r = getPostOne(nums, start);
        if (nums[start] == 1) {
            return Math.max(halfSum(nums, l == -1 ? 1 : l, start), halfSum(nums, start, r == -1 ? nums.length - 1 : r));
        }

        return sum(nums, l == -1 ? 1 : l, r == -1 ? nums.length - 1 : r, start);
    }

    private static int halfSum(int[] nums, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            if (nums[i] == 0)
                sum = 0;

            sum += nums[i];
        }
        return sum;
    }

    private static int sum(int[] nums, int start, int end, int mid) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            if (i < mid && nums[i] == 0)
                sum = 0;
            if (i > mid && nums[i] == 0)
                break;
            sum += nums[i];
        }
        return sum;
    }


    private static int getPostOne(int[] nums, int start) {

        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i] == 1) {
                return i;
            }
        }
        return -1;
    }

    private static int getPreOne(int[] nums, int start) {

        for (int i = start - 1; i > 0; i--) {
            if (nums[i] == 1) {
                return i;
            }
        }
        return -1;
    }

}