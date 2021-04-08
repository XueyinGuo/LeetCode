package com.szu.leetcode.algorithms;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/8 12:57
 */
/*
 * 面试题 17.16. 按摩师
 *
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-masseuse-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
public class Interview_17_16_Massage {




    public int massage(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        return getMaxBusy(nums, 0, 0);
    }

    /*
     * index : 当前来到第几个预约请求
     * max   : 之前所有做过的决策的服务时长值
     * */
    public int getMaxBusy(int[] nums, int index, int max) {
        // 已经没有任何预约了，返回当前的所有预约请求加起来的值
        if (index >= nums.length) {
            return max;
        }
        // 来到了最后一个预约，返回这个时长和当前的总时长的和
        if (index == nums.length - 1) {
            return max + nums[index];
        }
        // 接收当前订单，直接跳到 index + 2 位置去继续判断， 并且当前的最大服务时间也加上当前的这个接受时长
        int p1 = getMaxBusy(nums, index + 2, max + nums[index]);
        // 不接收当前预约， 跳到下一个去继续决策， 当前最大时长不动
        int p2 = getMaxBusy(nums, index + 1, max);
        // 返回接受当前请求和接受下一个请求的最大值
        return Math.max(p1, p2);
    }

    public int massageDp(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        return getMaxBusyWithDP(nums);
    }

    public int getMaxBusyWithDP(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N + 1];
        dp[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
        }
        return dp[0];
    }

    public static void main(String[] args) {
        Interview_17_16_Massage test = new Interview_17_16_Massage();
        int i = test.massage(new int[]{2, 7, 9, 3, 1, 3});
        int j = test.massageDp(new int[]{2, 7, 9, 3, 1, 3});
        System.out.println(i);
        System.out.println(j);
    }

}

