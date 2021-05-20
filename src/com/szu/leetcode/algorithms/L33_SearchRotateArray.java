package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 33. 搜索旋转排序数组
整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为
[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * @Date 2021/5/20 21:20
 */

import com.szu.leetcode.utils.LeetCodes;

public class L33_SearchRotateArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int cliff = -1;
        /* 先找到断崖位置在哪 */
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                cliff = i;
        }
        /* 如果没有断崖位置，那么断崖位置设置到数组末尾 */
        cliff = cliff == -1 ? nums.length - 1 : cliff;
        if (target > nums[cliff])
            return -1;
        /* 如果没有断崖的话 || target > 第二段的最大的那个数  */
        if (cliff == nums.length - 1 || target > nums[nums.length - 1])
            return binSearch(nums, 0, cliff, target); /*  1.就从整个数组上搞二分， 2. 从开头到断崖上高二芬 */
        else
            return binSearch(nums, cliff + 1, nums.length -1, target); /* 不比第二段最后一个大的话，就从第二段上搞二分 */
    }

    private int binSearch(int[] nums, int start, int end, int target) {

        while (start < end){

            int mid = start + ((end - start) >> 1);

            if (target > nums[mid])
                start = mid + 1;
            else if (target < nums[mid])
                end = mid - 1;
            else
                return mid;
        }
        return target == nums[start] ? start : -1;
    }


    public static void main(String[] args) {
        int[] inputArray = LeetCodes.getInputArray("[15,17,18,20,22,25,1,2,4,5,7,9,12]");
        int target = 12;
        L33_SearchRotateArray test = new L33_SearchRotateArray();
        test.search(inputArray, target);

    }
}
