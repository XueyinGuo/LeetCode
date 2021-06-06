package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 474. 一和零
给你一个二进制字符串数组 strs 和两个整数 m 和 n 。

请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。

如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 * @Date 2021/6/6 20:17
 */

import java.util.Arrays;
import java.util.Comparator;

public class L474_FindMaxForms {

    /*
     * TODO 动态规划
     * */
    public int findMaxForm(String[] strs, int zeroNums, int onesNums) {

        if (zeroNums == 0 && onesNums == 0)
            return 0;
        int length = strs.length;
        int[] zeros = new int[length];
        int[] ones = new int[length];

        for (int i = 0; i < length; i++) {
            Info info = getInfo(strs[i]);
            zeros[i] = info.zeros;
            ones[i] = info.ones;
        }

        int[][][] dp = new int[length + 1][zeroNums + 1][onesNums + 1];
        for (int i = length - 1; i >= 0; i--) {
            for (int start = i; start >= 0; start--) {
                int zeroLeft = zeroNums;
                int oneLeft = onesNums;
                while (zeroLeft > 0 && oneLeft > 0) {
                    dp[start][zeroLeft][oneLeft] = Math.max(
                            (zeroLeft - zeros[start] >= 0 && oneLeft - ones[start] >= 0) ?
                                    dp[start + 1][zeroLeft - zeros[start]][oneLeft - ones[start]] + 1 : 0,

                            dp[start + 1][zeroLeft][oneLeft]
                    );

                    zeroLeft -= zeros[start];
                    oneLeft -= ones[start];

                }
            }

        }
        return dp[0][zeroNums][onesNums];
    }

    /*
     * 两个背包装两种商品
     * 暴力尝试 + 缓存
     * */
    public int findMaxFormViolence(String[] strs, int zerosNum, int onesNum) {
        if (zerosNum == 0 && onesNum == 0)
            return 0;
        int length = strs.length;
        int zeros[] = new int[length];
        int ones[] = new int[length];
        int[][][] dp = new int[length + 1][zerosNum + 1][onesNum + 1];
        for (int i = 0; i <= length; i++) {
            for (int j = 0; j <= zerosNum; j++) {
                for (int k = 0; k <= onesNum; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        for (int i = 0; i < length; i++) {
            Info info = getInfo(strs[i]);
            zeros[i] = info.zeros;
            ones[i] = info.ones;
        }
        return violence(zeros, ones, 0, zerosNum, onesNum, dp);
    }

    private int violence(int[] zeros, int[] ones, int index, int zeroLeft, int oneLeft, int[][][] dp) {

        if (zeroLeft < 0 || oneLeft < 0){
            return -1;
        }
        if (index == zeros.length ){
            dp[index][zeroLeft][oneLeft] = 0;
            return 0;
        }

        if (dp[index][zeroLeft][oneLeft] != -1)
            return dp[index][zeroLeft][oneLeft];

        int useCur = 1 + violence(zeros, ones, index + 1, zeroLeft - zeros[index], oneLeft - ones[index], dp);
        int notUseCur = violence(zeros, ones, index + 1, zeroLeft, oneLeft, dp);
        dp[index][zeroLeft][oneLeft] = Math.max(useCur, notUseCur);
        return dp[index][zeroLeft][oneLeft];
    }

    private Info getInfo(String str) {

        char[] chars = str.toCharArray();
        int ones = 0;
        int zeros = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1')
                ones++;
            else
                zeros++;
        }
        return new Info(zeros, ones);
    }

    class Info {

        int zeros;
        int ones;

        public Info(int zeros, int ones) {
            this.zeros = zeros;
            this.ones = ones;
        }
    }

    /*
     * 解答错误
     *
     * 按照长度排序之后，遍历字符串数组
     * 把每个字符串加到答案中
     * 满足要求继续就加到结果集中，
     * 否则跳过这个字符串
     * */
    public int findMaxFormWrong(String[] strs, int m, int n) {
        if (m == 0 && n == 0)
            return 0;

        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        int len = 0;
        int payedOnes = 0;
        int payedZeros = 0;
        for (String str : strs) {

            Info info = getInfo(str);
            int newZeros = info.zeros + payedZeros;
            int newOnes = info.ones + payedOnes;
            if (newOnes > n || newZeros > m)
                continue;
            payedOnes = newOnes;
            payedZeros = newZeros;
            len++;
        }
        return len;
    }


    public static void main(String[] args) {
        String strs[] = {"10", "0001", "111001", "1", "0"};
        int zeros = 5;
        int ones = 3;
        L474_FindMaxForms test = new L474_FindMaxForms();
        System.out.println(test.findMaxFormViolence(strs, zeros, ones));
    }

}
