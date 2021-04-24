package com.szu.training01.class02;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/10 17:43
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> threeSum(int[] nums, int k) {
        Arrays.sort(nums);
        if (nums == null || nums.length < 3)
            return res;

        int length = nums.length;

        for (int i = 0; i < length - 2; i++) {
            if (i > 0)
                if (nums[i] == nums[i - 1])
                    continue;


            int l = i + 1;
            int r = length - 1;
            int kk = k - nums[i];
            while ( l != r){
                if (l > i + 1)
                    if ( nums[l] == nums[l - 1]){
                        l++;
                        continue;
                    }
                if (r < length - 1){
                    if (nums[r] == nums[r + 1]){
                        r--;
                        continue;
                    }
                }
                int cur = nums[l] + nums[r];
                if (cur < kk)
                    l++;
                else if (cur > kk)
                    r--;
                else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    res.add(list);
                    l++;
                }

            }

        }
        return res;

    }

    public static void main(String[] args) {
        int[] arr = {-1,0,1,2,-1,-4};
        List<List<Integer>> lists = new ThreeSum().threeSum(arr, 0);
        System.out.println();
    }
}
