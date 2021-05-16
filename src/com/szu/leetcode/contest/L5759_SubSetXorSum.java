package com.szu.leetcode.contest;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 5759. 找出所有子集的异或总和再求和
一个数组的 异或总和 定义为数组中所有元素按位 XOR 的结果；如果数组为 空 ，则异或总和为 0 。

例如，数组 [2,5,6] 的 异或总和 为 2 XOR 5 XOR 6 = 1 。
给你一个数组 nums ，请你求出 nums 中每个 子集 的 异或总和 ，计算并返回这些值相加之 和 。

注意：在本题中，元素 相同 的不同子集应 多次 计数。

数组 a 是数组 b 的一个 子集 的前提条件是：从 b 删除几个（也可能不删除）元素能够得到 a 。
 *
 * @Date 2021/5/16 10:27
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class L5759_SubSetXorSum {

    public int subsetXORSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();

        Set<Integer> path = new HashSet<>();
        getAll(nums, lists, path, 0);
        int ans = 0;
        for (List<Integer> list : lists) {
            int xorSum = 0;
            for (Integer num : list) {
                xorSum ^= num;
            }
            ans += xorSum;
        }
        return ans;
    }

    private void getAll(int[] nums, List<List<Integer>> lists, Set<Integer> path, int index) {

        if (index == nums.length) {
//            ArrayList<Integer> integers = ;
            if (path.size() > 0)
                lists.add(new ArrayList<>(path));
            return;
        }


        path.add(nums[index]);
        getAll(nums, lists, path, index + 1);
        path.remove(nums[index]);
        getAll(nums, lists, path, index + 1);


    }


    public static void main(String[] args) {
        int[] nums = {1, 3};
        L5759_SubSetXorSum t1 = new L5759_SubSetXorSum();
        t1.subsetXORSum(nums);
    }
}
