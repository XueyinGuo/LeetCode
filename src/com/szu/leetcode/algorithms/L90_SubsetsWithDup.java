package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * @Date 2021/9/16 14:17
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class L90_SubsetsWithDup {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        dfs(nums, 0, path, res, false);

        return res;
    }

    private void dfs(int[] nums, int index, List<Integer> path, List<List<Integer>> result, boolean choosePre) {
        if (index == nums.length){
            result.add(new LinkedList<>(path));
            return;
        }

        dfs(nums, index + 1, path, result, false);

        if (!choosePre && index > 0 && nums[index - 1] == nums[index]) {
            return;
        }

        path.add(nums[index]);
        dfs(nums, index + 1, path, result, true);
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        int[] inputArray = LeetCodes.getInputArray(" [1,2,2]");
        L90_SubsetsWithDup test = new L90_SubsetsWithDup();
        List<List<Integer>> lists = test.subsetsWithDup(inputArray);
        System.out.println();
    }

}
