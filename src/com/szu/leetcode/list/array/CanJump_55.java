package com.szu.leetcode.list.array;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *              55. 跳跃游戏
                给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。

                数组中的每个元素代表你在该位置可以跳跃的最大长度。

                判断你是否能够到达最后一个下标。

                https://leetcode-cn.com/problems/jump-game/
 *
 * @Date 2021/2/18 19:13
 */

public class CanJump_55 {

    public boolean canJump(int[] nums) {
        // 如果当前数组就是1，我就已经在数组最后一个了
        if(nums.length == 1){
            return true;
        }
        // 我还能跳多少步
        int step = nums[0];
        // 我的位置的下一个位置让我跳多少步
        int index = 1;
        // 当我还能继续往前跳，而且每到数组最后一个位置的时候
        // 继续往前跳，但是要看看途径的每个数字是不是可以让我走更多步数
        // 如果是 我就听最多的那个
        // 如果我看了下一个位置让我走的为0
        // 而且我自己的剩余步数也已经为0
        // 那我就走不动了
        while(step > 0 && index < nums.length){
            if(index == nums.length - 1){
                return true;
            }
            // 每往前走一步我就少一步
            step--;
            // 继续往前跳，但是要看看途径的每个数字是不是可以让我走更多步数
            // 如果是 我就听最多的那个
            step = step > nums[index] ? step : nums[index];
            // 如果我看了下一个位置让我走的为0
            // 而且我自己的剩余步数也已经为0
            // 那我就走不动了
            if(step == 0){
                return false;
            }
            // 当然继续往前看
            index++;
        }
        return false;
    }

}
