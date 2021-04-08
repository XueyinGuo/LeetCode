package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *          46. 全排列
 *          给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * @Date 2021/2/13 11:11
 */

import java.util.ArrayList;
import java.util.List;

public class L46_NumsPermute {

    List<Integer> list;
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if(nums.length == 0){
            return null;
        }
        doFindPermute(nums, 0);
        return res;
    }

    public void doFindPermute(int[] nums, int index){
        // 当最后一个交换完成，把所有的数字加入到 list 中
        if(index == nums.length){
            addToResult(nums);
            return;
        }
        // 开始两两交换数组中的数字，遍历每种数字在每个位置的情况
        for(int i = index; i<nums.length; i++){
            swap(nums, index, i);
            doFindPermute(nums, index + 1);
            // 每次遍历完成当前的数组之后，找到了这个情况下数字的排列情况
            // 为了不妨碍下次用到数组的时候的结果，当前每次遍历完之后都要恢复现场
            swap(nums, i, index);
        }
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void addToResult(int[] nums){
        list = new ArrayList<>() ;
        for(int i = 0; i < nums.length; i++){
            list.add(nums[i]);
        }
        res.add(list);

    }

}
