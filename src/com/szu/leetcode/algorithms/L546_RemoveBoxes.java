package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 546. 移除盒子
给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。

你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k * k 个积分。

当你将所有盒子都去掉之后，求你能获得的最大积分和。【经典三十一】
 *
 * @Date 2021/6/1 14:00
 */

import com.szu.leetcode.utils.LeetCodes;

public class L546_RemoveBoxes {

    public int removeBoxes(int[] boxes) {
        int length = boxes.length;
        int[][][] dp = new int[length][length][length];
        return process(boxes, 0, length - 1, 0, dp);
    }

    private int process(int[] boxes, int l, int r, int k, int[][][] dp) {

        if (l > r)
            return 0;

        if (dp[l][r][k] != 0)
            return dp[l][r][k];

        if (l == r) {
            dp[l][r][k] = (k + 1) * (k + 1);
            return dp[l][r][k];
        }

        while (l < r && boxes[l] == boxes[l + 1]) {
            l++;
            k++;
        }
        /*
        * 就消掉当前所有一样的
        * */
        int ans = process(boxes, l + 1, r, 0, dp) + (k + 1) * (k + 1);
        int i = l + 1;
        while ( i <= r) {
            /*
            * 枚举后边所有位置，如果 i 位置上与 l 位置的数字一样，尝试 i 位置和前面的 l 一块消除掉
            * */
            if (boxes[l] == boxes[i]){
                ans = Math.max(ans, process(boxes, l+1, i-1, 0,dp) + process(boxes, i, r, k+1, dp));
            }
            i = findNextUnique(boxes, i, r);
        }
        dp[l][r][k] = ans;
        return ans;
    }

    private int findNextUnique(int[] boxes, int index, int r) {
        int i = index;
        while (i <= r && boxes[i] == boxes[index])
            i++;

        return i;
    }

    public static void main(String[] args) {
        int[] inputArray = LeetCodes.getInputArray("[1,2,2,1,1,1,2,1,1,2,1,2,1,1,2,2,1,1,2,2,1,1,1,2,2,2,2,1,2,1,1,2,2,1,2,1,2,2,2,2,2,1,2,1,2,2,1,1,1,2,2,1,2,1,2,2,1,2,1,1,1,2,2,2,2,2,1,2,2,2,2,2,1,1,1,1,1,2,2,2,2,2,1,1,1,1,2,2,1,1,1,1,1,1,1,2,1,2,2,1]");
        L546_RemoveBoxes test = new L546_RemoveBoxes();
        System.out.println(test.removeBoxes(inputArray));
    }
}
