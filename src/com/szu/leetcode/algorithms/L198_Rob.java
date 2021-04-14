package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  198. 打家劫舍
        你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
        如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

        给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * @Date 2021/4/12 17:54
 */

import com.szu.leetcode.utils.LeetCodes;

public class L198_Rob {

    int max = 0;
    public int rob(int[] nums) {
        rob(nums, 0, 0);
        return max;
    }

    public void rob(int[] nums, int index, int cash) {
        if(index >= nums.length)
            return;
        if(cash > max)
            max = cash;

        rob(nums, index + 1, cash);
        rob(nums, index + 2, cash + nums[index]);
    }


    public int robDp(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        // 偷第一个房间
        dp[0] = nums[0];
        // 要不要偷第二个房间，如果房间 2 比 房间 1值钱  我才偷
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i<nums.length; i++){
            // 之后每个房间 我都看看 隔一个的那个房间 + 当前房间 是不是 比 前一个房间的东西值钱，否则我就不偷
            dp[i] = Math.max(dp[i - 1] , dp[i - 2] + nums[i]);

        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        L198_Rob l198_rob = new L198_Rob();
        for (int i = 0; i < 10000; i++) {
            System.out.println(i + " ");
            int nums[] = LeetCodes.getRandomArray(50, 50);
            int rob = l198_rob.rob(nums);
            int i1 = l198_rob.robDp(nums);
            if (i1 != rob)
                System.out.println("FUCK");
        }
    }
}
