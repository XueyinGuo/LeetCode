package com.szu;

import java.util.Scanner;

public class BaiduT1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int len = n * n;
            int[] nums = new int[len];
            for (int i = 0; i < len; i++) {
                nums[i] = scanner.nextInt();
            }

            System.out.println(sum(nums));
        }
    }

    private static long sum(int[] nums) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }


}