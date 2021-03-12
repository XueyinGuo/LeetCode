package com.szu.leetcode;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description

        15. 三数之和

        给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

        注意：答案中不可以包含重复的三元组。


 * @Date 2021/2/17 23:32
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class L15_ThreeSum {
    Set<List<Integer>> set = new HashSet<>();
    public List<List<Integer>> threeSum(int[] nums) {
        sort(nums, 0, nums.length-1);
        int positiveStart = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0){
                positiveStart = i;
                break;
            }
        }
        findResult(nums, positiveStart);
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> l : set) {
            res.add(l);
        }
        return res;
    }

    private void findResult(int[] nums, int positiveStart) {

        if(nums.length == 0){
            return;
        }

        if (nums[0] > 0 || nums[nums.length-1] < 0){
            return;
        }
        for (int i = 0; i < nums.length-1; i++) {
            int cur = nums[i] + nums[i + 1];
            for (int j = positiveStart; j < nums.length; j++) {
                if (cur + nums[j] == 0){
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[i + 1]);
                    list.add(nums[j]);
                    set.add(list);
                    continue;
                }
                if (cur + nums[j] > 0){
                    continue;
                }
            }
        }
    }

    private void sort(int[] nums, int low, int high) {
        if (low < high){
            int index = partition(nums, low, high);
            sort(nums,low, index-1);
            sort(nums,index+1, high);
        }
    }

    private int partition(int[] nums, int low, int high) {

        int pivot = nums[low];
        while (low < high){

            while (low < high && nums[high] >= pivot ) high--;
            nums[low] = nums[high];
            while (low < high && nums[low] <= pivot) low++;
            nums[high] = nums[low];

        }
        nums[low] = pivot;
        return low;
    }




    public static void main(String[] args) {
//        int[] nums = {-4,-2,6,4,7,8,-4,66,9,2,-77,42,8};
        int[] nums = {0,0,0};
        new L15_ThreeSum().threeSum(nums);
    }

}
