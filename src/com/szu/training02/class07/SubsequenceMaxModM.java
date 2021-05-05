package com.szu.training02.class07;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个非负数数组和一个正数 m
 * 返回 arr 所有子序列中累加和 %m 之后的最大值
 *
 * @Date 2021/5/4 9:51
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class SubsequenceMaxModM {

    public int sumDp(int[] arr, int m) {
        if (arr == null || arr.length == 0)
            return 0;
        /*
         * 用累加和来做 列，适用于累加和不大的情况
         * */
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int length = arr.length;
        boolean[][] dp = new boolean[length][sum + 1];
        for (int r = 0; r < length; r++) {
            dp[r][0] = true;
        }
        dp[0][arr[0]] = true;

        for (int r = 1; r < length; r++) {
            for (int c = 1; c <= sum; c++) {
                /*
                 * 要或不要当前元素， 看能否能形成 当前累加和
                 *
                 * 不要这个元素能形成吗？
                 * 要这个元素能形成吗？
                 * */
                dp[r][c] = dp[r - 1][c];
                if (c - arr[r] > 0)
                    dp[r][c] = dp[r - 1][c] | dp[r - 1][c - arr[r]];
            }
        }
        int ans = 0;
        for (int c = 0; c <= sum; c++) {
            int cur = c % m;
            if (cur > ans)
                ans = cur;
        }
        return ans;
    }

    /*
     * 适用于 mod 的数字不是很大的情况
     * */
    public int modDp(int[] arr, int m) {
        if (arr == null || arr.length == 0)
            return 0;
        int length = arr.length;
        boolean[][] dp = new boolean[length][m];
        for (int r = 0; r < length; r++) {
            dp[r][0] = true;
        }
        dp[0][arr[0] % m] = true;

        for (int r = 1; r < length; r++) {
            for (int c = 1; c < m; c++) {
                /*
                 * 经过我的使劲观察，观察出了下列转移方程
                 * */
                int cur = arr[r] % m;

                dp[r][c] = dp[r - 1][c] | dp[r - 1][(m + c - cur) % m];
            }
        }
        int ans = 0;
        for (int r = 0; r < length; r++) {
            for (int c = m - 1; c >= 0; c--) {
                if (dp[r][c]) {
                    ans = c;
                    break;
                }
            }
        }
        return ans;
    }

    /*
     * 分治思想，从中间分开搞定两边所有的子序列，
     * 并把所有子序列和放入有序表中
     * */
    public int maxModGenerateAllSubSequenceCutFromMid(int[] arr, int m) {

        if (arr == null || arr.length == 0)
            return 0;
        if (arr.length == 1)
            return arr[0] % m;

        int mid = (arr.length - 1) / 2;
        /*
         * 回溯生成所有的子序列， 把每个子序列的和加入到 set 中
         *
         * 不过生成子序列和 用的是 分治思想，把一个长的数组，分成两半
         * */
        TreeSet<Integer> leftSumSet = new TreeSet<>();
        ArrayList<Integer> path = new ArrayList<>();
        generateAllSubSequence(arr, 0, mid, path, leftSumSet, m);

        TreeSet<Integer> rightSumSet = new TreeSet<>();
        generateAllSubSequence(arr, mid + 1, arr.length - 1, path, rightSumSet, m);
        int ans = 0;
        /*
        * =====================================
        * =====================================
        * 生成的子序列中必定 有 0， 因为子序列嘛，可以谁也不要形成 0 的
        *
        * 所以包含了 其中一边已经足够接近 m 的值的时候的情况，
        *
        * 足够接近的时候，dif 趋近于 0
        *
        * floor(dif) = 0，那是肯定的
        * =====================================
        * =====================================
        * */
        for (Integer leftMod : leftSumSet) {

            int dif = m - 1 - leftMod;
            Integer floor = rightSumSet.floor(dif);

            if ( leftMod + floor > ans )
                ans = leftMod + floor;
        }
        return ans;
    }

    private void generateAllSubSequence(int[] arr, int start, int end, ArrayList<Integer> path, TreeSet<Integer> sumSet, int mod) {

        if (start > end) {
            int sum = 0;
            for (int i = 0; i < path.size(); i++) {
                sum += path.get(i);
            }
            sumSet.add(sum % mod);
            return;
        }

        for (int i = start; i <= end; i++) {
            path.add(arr[i]);
            generateAllSubSequence(arr, i + 1, end, path, sumSet, mod);
            path.remove(path.size() - 1);
            generateAllSubSequence(arr, i + 1, end, path, sumSet, mod);
        }

    }


    public static void main(String[] args) {
        SubsequenceMaxModM test = new SubsequenceMaxModM();
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            int arr[] = LeetCodes.getRandomArray(20, 20);
            int k = random.nextInt(100) + 3;
            int sumDp = test.sumDp(arr, k);
            int modDp = test.modDp(arr, k);
            int maxMod = test.maxModGenerateAllSubSequenceCutFromMid(arr, k);
            if (sumDp != modDp || maxMod != sumDp)
                System.out.println("FUCK");
        }
    }


}
