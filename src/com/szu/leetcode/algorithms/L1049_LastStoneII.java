package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 1049. 最后一块石头的重量 II
有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。

每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：

如果 x == y，那么两块石头都会被完全粉碎；
如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 *
 * @Date 2021/6/8 12:19
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.LinkedList;
import java.util.List;

public class L1049_LastStoneII {


    /*
     * 贪心：能形成的最大的和，直接返回
     * */
    public int lastStoneWeightIIAwesome(int[] stones) {
        if (stones == null || stones.length == 0)
            return 0;
        int length = stones.length;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += stones[i];
        }
        /* 让 i 从 sum/2 开始枚举，找到第一个能形成的最大的和即可 */
        for (int i = sum >> 1; i >= 0; i--) {
            if (canGenerateTarget(stones, 0, 0, i))
                return sum - 2 * i;
        }
        return 0;
    }

    private boolean canGenerateTarget(int[] stones, int index, int curSum, int target) {
        if (curSum == target)
            return true;
        if (index == stones.length)
            return false;

        return canGenerateTarget(stones, index + 1, curSum, target) ||
                canGenerateTarget(stones, index + 1, curSum + stones[index], target);
    }



    /*
    * 枚举了每个可以形成的和
    * */
    public int lastStoneWeightII(int[] stones) {
        if (stones == null || stones.length == 0)
            return 0;
        int length = stones.length;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += stones[i];
        }
        // 分解为01背包问题，题以即为 一大堆石头，分为两拨，每波的重量都尽量逼近 sum/2
        // mem 用来防止形成相同的和，避免过多的重复计算
        // 每个之前形成的和 都存在 list 中
        // 每次到达一个新的数组元素，需要判断的是【取当前元素能形成的和， 不取当前元素能形成的和】
        int cap = sum / 2;
        /*
        * dp 含义：
        * row ： 数组长度， col：能够形成的 和，从 0 --- sum/2+1
        * dp[r][c] = true : stones 的 0--r 号元素，能形成 c 的和
        * */
        boolean[][] dp = new boolean[length][cap + 1];
        int[] mem = new int[sum + 1];
        List<Integer> list = new LinkedList<>();
        list.add(0);
        mem[0] = 1;
        // 判断第一个位置 可不可取
        if (stones[0] <= cap) {
            dp[0][stones[0]] = true;
            list.add(stones[0]);
            mem[stones[0]] = 1;
        }
        // 从第二个位置开始，取当前元素形成的和 不取当前元素形成的和 都放到 list 中，
        // dp 的对应位置 置位 true，表示 从 第0个元素到第r个元素 可以组成 c 这个和
        for (int r = 1; r < length; r++) {
            int c = stones[r];
            int size = list.size();
            for (int i = 0; i < size; i++) {
                int nextCol = c + list.get(i);
                if (nextCol <= cap && mem[nextCol] == 0) {
                    mem[nextCol] = 1;
                    dp[r][nextCol] = true;
                    list.add(nextCol);
                }

            }
        }
        /* 找到能形成的最大的和 */
        int max = 0;
        out:
        for (int c = dp[0].length - 1; c >= 0; c--) {
            for (int r = 0; r < dp.length; r++) {
                if (dp[r][c]) {
                    max = c;
                    break out;
                }
            }
        }

        return sum - 2 * max;
    }


    public static void main(String[] args) {
        int[] inputArray = LeetCodes.getInputArray("[1,1,1,1,8]");
        L1049_LastStoneII test = new L1049_LastStoneII();
        System.out.println(test.lastStoneWeightII(inputArray));
    }
}
