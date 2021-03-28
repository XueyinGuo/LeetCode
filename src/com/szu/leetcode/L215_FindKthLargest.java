package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *          215. 数组中的第K个最大元素
             在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

             示例 1:

             输入: [3,2,1,5,6,4] 和 k = 2
             输出: 5
             示例 2:

             输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
             输出: 4

 *              TODO 为什么时间复杂度反而高了呢？？？
 *
 * @Date 2021/3/26 23:13
 */

import java.util.Random;

public class L215_FindKthLargest {

    public static void main(String[] args) {
        int nums[] = {3,2,1,5,6,4};
        int k = 2;
        int kthLargest = new L215_FindKthLargest().findKthLargest(nums, k);
        System.out.println(kthLargest);
    }

    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k > nums.length)
            return -1;

        return findKthLargest(nums, nums.length - k, 0, nums.length-1, new Random());
    }

    public int findKthLargest(int[] nums, int index, int low, int high, Random random){
        int pivot = nums[ low + random.nextInt( high - low + 1)];
        int range[] = getMid(low, high, pivot, nums);
        if(index >= range[0] && index <= range[1])
            return nums[index];
        else if(index > range[1])
            return findKthLargest(nums, index, range[1]+1, high, random);

        else
            return findKthLargest(nums, index, low, range[0]-1, random);
    }

    public int[] getMid(int low, int high, int pivot, int nums[]){

        int less = low;
        int more = high;
        int cur = low;
        while(  cur <= more ){

            if(nums[cur] > pivot){
                swap(nums, cur, more);
                more--;
            }else if(nums[cur] < pivot){
                swap(nums, cur, less);
                cur++;
                less++;
            }else{
                cur++;
            }

        }
        return new int[]{less, more};

    }

    public void swap(int []nums, int i1, int i2){
        if(i1 == i2) return;
        int tem = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tem;
    }

}
