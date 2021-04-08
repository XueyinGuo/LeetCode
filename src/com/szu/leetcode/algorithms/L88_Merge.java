package com.szu.leetcode.algorithms;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/22 21:24
 */

public class L88_Merge {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] res = new int[nums1.length];
        System.arraycopy(nums1, 0, res, 0, nums1.length);
        int index = 0;
        int i1 = 0;
        int i2 = 0;
        while(i1 < m && i2 < n){

            if(res[i1] >= nums2[i2]) nums1[index++] = nums2[i2++];
            else nums1[index++] = res[i1++];


        }

        if(i1 < m){
            for(int i = i1; i < m; i++)
                nums1[index++] = res[i];
        }

        if(i2 < n) {
            for (int i = i2; i < n; i++)
                nums2[index++] = res[i];
        }

    }
}
