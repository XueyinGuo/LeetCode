package com.szu.training02.class08;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 312. 戳气球
    有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

    现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。

    求所能获得硬币的最大数量。
 *
 * @Date 2021/5/4 9:53
 */

import java.util.Map;
import java.util.TreeMap;

public class BurstBalloons {

    /*
     * 贪心失败
     * */
//    public int maxCoins(int[] nums) {
//        int[] newNums = new int[nums.length + 2];
//        newNums[0] = 1;
//        TreeMap<Integer, Integer> map = new TreeMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            newNums[i + 1] = nums[i];
//            map.put(nums[i], i + 1);
//        }
//
//        newNums[newNums.length - 1] = 1;
//        int coins = 0;
//        while (!map.isEmpty()) {
//
//            Map.Entry<Integer, Integer> first = map.firstEntry();
//            map.remove(first.getKey());
//            Integer index = first.getValue();
//            coins += newNums[index - 1] * first.getKey() * newNums[index + 1];
//
//        }
//        return coins;
//    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        int i = new BurstBalloons().maxCoins(nums);
        int stupidMemory = new BurstBalloons().maxCoinsWithStupidMemory(nums);
        int dp = new BurstBalloons().maxCoinsDp(nums);
        System.out.println(i);
    }

    /*
     * 动态规划版本
     * */
    public int maxCoinsDp(int[] a) {
        /*
         * 填充数组两侧，永远不会触碰的值
         * */
        int len = a.length + 2;
        int[] nums = new int[len];
        nums[0] = 1;
        for (int i = 0; i < a.length; i++) {
            nums[i + 1] = a[i];
        }
        nums[len - 1] = 1;
        int[][] dp = new int[len][len];
        for (int i = 1; i < len - 1; i++) {
            dp[i][i] = nums[i - 1] * nums[i] * nums[i + 1];
        }
        for (int L = len - 2; L >= 1; L--) {
            for (int R = L + 1; R < len - 1; R++) {
                dp[L][R] = nums[L - 1] * nums[L] * nums[R + 1] + dp[L + 1][R];
                dp[L][R] = Math.max(dp[L][R], nums[L - 1] * nums[R] * nums[R + 1] + dp[L][R - 1]);
                for (int i = L + 1; i < R; i++) {
                    dp[L][R] = Math.max(dp[L][R], nums[L - 1] * nums[i] * nums[R + 1]
                            + dp[i + 1][R] + dp[L][i - 1]);
                }
            }
        }
        return dp[1][len - 2];
    }

    public int maxCoins(int[] nums) {
        /*
         * 填充数组两侧，永远不会触碰的值
         * */
        int[] newNums = new int[nums.length + 2];
        newNums[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            newNums[i + 1] = nums[i];
        }
        newNums[newNums.length - 1] = 1;

        return process(newNums, 1, nums.length);
    }


    /*
     * 打爆arr[L..R]范围上的所有气球，返回最大的分数
     * 假设arr[L-1]和 arr[R+1]一定没有被打爆
     * */
    public int process(int[] nums, int l, int r) {
        /*
         * 区间只剩下一个数，则直接打爆就好了，打爆的分数是！！！！
         * */
        if (l == r)
            return nums[l - 1] * nums[l] * nums[r + 1];

        int ans = 0;
        /*
         * 最后一个打爆 l 位置的气球
         * */
        int leftCoins = nums[l - 1] * nums[l] * nums[r + 1] + process(nums, l + 1, r);
        /*
         * 最后一个打爆 r 位置的气球
         * */
        int rightCoins = nums[l - 1] * nums[r] * nums[r + 1] + process(nums, l, r - 1);
        ans = Math.max(leftCoins, rightCoins);
        /*
         * 每个中间位置 依次最后一个打爆
         * */
        for (int i = l + 1; i < r; i++) {
            int split = nums[l - 1] * nums[i] * nums[r + 1]
                    + process(nums, i + 1, r) + process(nums, l, i - 1);
            if (split > ans)
                ans = split;
        }
        return ans;
    }


    public int maxCoinsWithStupidMemory(int[] nums) {
        /*
         * 填充数组两侧，永远不会触碰的值
         * */
        int[] newNums = new int[nums.length + 2];
        newNums[0] = 1;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            newNums[i + 1] = nums[i];
            map.put(nums[i], i + 1);
        }
        newNums[newNums.length - 1] = 1;
        int[][] dp = new int[newNums.length][newNums.length];
        return processWithStupidMemory(newNums, 1, nums.length, dp);
    }


    /*
     * 打爆arr[L..R]范围上的所有气球，返回最大的分数
     * 假设arr[L-1]和 arr[R+1]一定没有被打爆
     * */
    public int processWithStupidMemory(int[] nums, int l, int r, int[][] dp) {
        /*
         * 区间只剩下一个数，则直接打爆就好了，打爆的分数是！！！！
         * */
        if (l == r) {
            dp[l][r] = nums[l - 1] * nums[l] * nums[r + 1];
            return dp[l][r];
        }
        if (dp[l][r] != 0)
            return dp[l][r];
        int ans = 0;
        /*
         * 最后一个打爆 l 位置的气球
         * */
        int leftCoins = nums[l - 1] * nums[l] * nums[r + 1] + processWithStupidMemory(nums, l + 1, r, dp);
        /*
         * 最后一个打爆 r 位置的气球
         * */
        int rightCoins = nums[l - 1] * nums[r] * nums[r + 1] + processWithStupidMemory(nums, l, r - 1, dp);
        ans = Math.max(leftCoins, rightCoins);
        /*
         * 每个中间位置 依次最后一个打爆
         * */
        for (int i = l + 1; i < r; i++) {
            int split = nums[l - 1] * nums[i] * nums[r + 1]
                    + processWithStupidMemory(nums, i + 1, r, dp) + processWithStupidMemory(nums, l, i - 1, dp);
            if (split > ans)
                ans = split;
        }
        dp[l][r] = ans;
        return ans;
    }
}
