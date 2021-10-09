package com.szu;

import java.util.Scanner;

public class BaiduT3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.next();
            int length = s.length();
            int[][] dp = new int[length][length];
            System.out.println(getSubNum(s.toCharArray(), 0, length - 1, dp));
        }
    }

    private static int getSubNum(char[] str, int start, int end, int[][] dp) {
        if (isAllCharSame(str, start, end)) {
            dp[start][end] = end - start + 1;
            return dp[start][end];
        }
        if (dp[start][end] != 0)
            return dp[start][end];

        int ways = 0;
        for (int i = start + 1; i <= end; i++) {
            ways += getSubNum(str, start, i - 1, dp) % 1000000007 * getSubNum(str, i, end, dp) % 1000000007;
        }
        dp[start][end] = ways % 1000000007;
        return ways;
    }

    private static boolean isAllCharSame(char[] str, int start, int end) {
        if (start == end)
            return true;

        for (int i = start + 1; i <= end; i++) {
            if (str[i] != str[i - 1])
                return false;
        }
        return true;
    }

}