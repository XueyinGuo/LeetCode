package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 354. 俄罗斯套娃信封问题
    给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。

    当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

    请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

    注意：不允许旋转信封。
    https://leetcode-cn.com/problems/russian-doll-envelopes/
 *
 * @Date 2021/4/15 20:04
 */

import java.util.Arrays;
import java.util.Comparator;

public class L354_MaxEnvelopes {
    /*
    * 解题思路：
    * 信封按照 第一维度 从 小到大 排序
    *        第二维度 从 大到小 排序
    *
    * 在此排序结果下，第二维度的严格最长递增子序列 就是 本题目解
    *
    * 假设第一维度为长， 第二维度为宽
    * 那么长 必定 按照从小到达 排序的， 因为 我们已经排序策略，
    * 然而 长一样的 宽 是 从大到小的 。
    *
    * 假设形成了这种数组【只把第二位 宽 拎出来】
    * [ [1,3] [2,5] [3,6] [3,5] [3,4] []  ]
    *   --------------↑---
    * 既然 第二维 比 他 小，那么必定 长和宽都比我小，因为 长和我一样 宽比我小的 在我后边，严格递增子序列肯定遇不到他
    *
    * */
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if (arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                return arr1[0] - arr2[0];
            }
        });

        int[] dp = getdp(envelopes);
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max)
                max = dp[i];
        }
        return max;
    }

    private int[] getdp(int[][] envelopes) {
        int length = envelopes.length;
        int[] dp = new int[length];
        int[] ends = new int[length];

        ends[0] = envelopes[0][1];
        dp[0] = 1;
        int right = 0, l = 0, r = 0, m = 0;
        for (int i = 1; i < length; i++) {
            l = 0;
            r = right;
            while (l <= r){
                m = (l + r) / 2;
                if (envelopes[i][1] > ends[m])
                    l = m + 1;
                else
                    r = m - 1;
            }
            right = Math.max(l, right);
            ends[l] = envelopes[i][1];
            dp[i] = l + 1;
        }
        return dp;
    }

    public static void main(String[] args) {
        int[][] e = {
                {1, 1},
                {1, 1},
                {1, 1}
        };
        L354_MaxEnvelopes test = new L354_MaxEnvelopes();
        test.maxEnvelopes(e);
    }
}
