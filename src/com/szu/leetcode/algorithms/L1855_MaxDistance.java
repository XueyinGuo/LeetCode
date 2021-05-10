package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 1855. 下标对中的最大距离
给你两个 非递增 的整数数组 nums1​​​​​​ 和 nums2​​​​​​ ，数组下标均 从 0 开始 计数。

下标对 (i, j) 中 0 <= i < nums1.length 且 0 <= j < nums2.length 。如果该下标对同时满足 i <= j 且 nums1[i] <= nums2[j] ，则称之为 有效 下标对，该下标对的 距离 为 j - i​​ 。​​

返回所有 有效 下标对 (i, j) 中的 最大距离 。如果不存在有效下标对，返回 0 。

一个数组 arr ，如果每个 1 <= i < arr.length 均有 arr[i-1] >= arr[i] 成立，那么该数组是一个 非递增 数组。
 *
 * @Date 2021/5/10 22:43
 */

public class L1855_MaxDistance {

    public int maxDistance(int[] nums1, int[] nums2) {

        int i = 0;
        int j = 0;
        int max = 0;
        while (i < nums1.length && j < nums2.length){

            if (nums1[i] > nums2[j]){
                i++;
                j++;
            }else {

                int cur = j - i;
                if (cur > max)
                    max = cur;

                j++;
            }

        }
        return max;
    }

}
