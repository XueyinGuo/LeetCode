package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *      560. 和为K的子数组
 *      给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 *      示例 1 :
 *
 *      输入:nums = [1,1,1], k = 2
 *      输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 *
 *
 * @Date 2021/4/9 12:44
 */

import java.util.HashMap;

public class L560_SubarraySum {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        /*
        * 前缀和 Map
        * 前缀和 -> 出现次数
        * 刚开始的时候 0 这个前缀和就已经出现了 1 次，就是还没开始加的时候
        * */
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0 , 1); // 刚开始的时候 0 这个前缀和就已经出现了 1 次，就是还没开始加的时候
        int ans = 0;
        for( int i = 0; i < nums.length; i++ ){
            /*
             * sum 用来计算前缀和 ， 来到 i 位置的时候，和为 sum
             * */
            sum += nums[i];
            /*
             * 如果以 i 位置为结尾的话，前边应该出现过 dif 这个前缀和，
             * 当前的 sum - dif 则 == k
             * 比如 [-1, -1, 1], 当数组来到第一个为止 sum = -1， 则 前缀和 map 中 应该 出现过 -1 的情况下
             * 此位置结尾的子数组，才有可能 出现 k 的情况
             * -1 - ( -1 ) == 0
             * */
            int dif = sum - k;
            Integer showedTimes = map.get(dif) ; // 为了尽量少的出现方法调用，所以只调用一次get方法，省去了 if(contains(dif))
                                                // 然后 get(dif) ，此处两个方法调用改为 一个方法调用
            ans += showedTimes == null ? 0 : showedTimes;
            /*
             * 是否出现过 sum 这个前缀和，如果出现过，出现次数 + 1
             * */
            Integer count = map.get(sum);
            if(count != null)
                map.put(sum, count + 1);
            else
                map.put(sum, 1);


        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {-1, -1 , 1};
        int k = 0;
        System.out.println(new L560_SubarraySum().subarraySum(arr, k));
    }
}
