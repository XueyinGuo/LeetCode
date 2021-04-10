package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description

        15. 三数之和

        给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

        注意：答案中不可以包含重复的三元组。


 * @Date 2021/2/17 23:32
 */

import java.util.*;

public class L15_ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        return threeSum(nums, 0);
    }

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> threeSum(int[] nums, int k) {
        Arrays.sort(nums);
        if (nums == null || nums.length < 3)
            return res;

        int length = nums.length;

        for (int i = 0; i < length - 2; i++) {
            /*
            * 为了防止重复答案出现，而且避免使用set增加额外的代码量和空间复杂度
            * 以及调用函数产生的额外的时间复杂度，在代码运行过程中充分判重复
            *
            * 首先 如果 i 位置 与 上一个 i 位置值相同直接跳过
            * */
            if (i > 0)
                if (nums[i] == nums[i - 1])
                    continue;
            // 剩余数组中找两个数相加为 k 的代码
            int l = i + 1;
            int r = length - 1;
            // 两数之和 为 差值 时， 则找到了 三元组
            int kk = k - nums[i];
            while ( l != r){
                /*
                 * 剩余位置的其实位置位 l
                 * r 始终为 数组最后一个数字
                 *
                 * 如果 [-1,0,1,2,-1] 排序完成之后为
                 * -1 -1 0 1 2
                 * 当 i 位于第一个 -1
                 * l 位于第二个 -1 的时候，有两个解，所以
                 * 也为了避免漏解，所以 l 与 i 中间至少间隔 1 个元素的时候才开始 判断 l 的重复
                 * */
                if (l > i + 1)
                    if ( nums[l] == nums[l - 1]){
                        l++;
                        continue;
                    }
                /*
                 * 避免 r 的重复
                 * */
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
//        int[] nums = {-4,-2,6,4,7,8,-4,66,9,2,-77,42,8};
        int[] nums = {0,0,0};
        new L15_ThreeSum().threeSum(nums);
    }

}
