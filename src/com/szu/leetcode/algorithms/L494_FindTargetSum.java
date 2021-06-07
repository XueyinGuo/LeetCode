package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 494. 目标和
给你一个整数数组 nums 和一个整数 target 。

向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * @Date 2021/6/7 12:19
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class L494_FindTargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;

        return violence(nums, 0, 0, target);
    }

    public int violence(int[] nums, int index, int curSum, int target) {
        if (index == nums.length)
            return curSum == target ? 1 : 0;

        int ways = 0;
        ways += violence(nums, index + 1, curSum + nums[index], target);
        ways += violence(nums, index + 1, curSum - nums[index], target);

        return ways;
    }


    public int findTargetSumWaysMemory(int[] nums, int target) {
        Map<String, Integer> mem = new HashMap<>();
        return dfs(nums, target, 0, 0, mem);
    }

    int dfs(int[] nums, int target, int index, int cur, Map<String, Integer> mem) {
        String key = index + "_" + cur;
        Integer ways = mem.get(key);
        if (ways != null)
            return ways;
        if (index == nums.length) {
            mem.put(key, cur == target ? 1 : 0);
            return mem.get(key);
        }
        int way = 0;
        way += dfs(nums, target, index + 1, cur + nums[index], mem);
        way += dfs(nums, target, index + 1, cur - nums[index], mem);
        mem.put(key, way);
        return way;
    }

}
