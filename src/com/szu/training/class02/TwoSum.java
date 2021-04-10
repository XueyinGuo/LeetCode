package com.szu.training.class02;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/10 17:21
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSum {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> twoSum(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return res;
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length-1;

        while (l != r){
            if (l > 0)
                if ( nums[l] == nums[l - 1] ){
                    l++;
                    continue;
                }

            if (r < nums.length - 1)
                if (nums[r] == nums[r + 1]){
                    r--;
                    continue;
                }

            int sum = nums[l] + nums[r];
            if (sum > k)
                r--;
            else if (sum < k)
                l++;
            else{

                List<Integer> list = new ArrayList<>();
                list.add(nums[l]);
                list.add(nums[r]);
                res.add(list);
                l++;
            }

        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 17, 4, 7, 5 ,9, 3, 8};
        List<List<Integer>> lists = new TwoSum().twoSum(nums, 12);
        System.out.println();
    }
}
