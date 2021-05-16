package com.szu.leetcode.contest;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/5/16 10:27
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class T1 {

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
        T1 t1 = new T1();
        t1.subsetXORSum(nums);
    }
}
