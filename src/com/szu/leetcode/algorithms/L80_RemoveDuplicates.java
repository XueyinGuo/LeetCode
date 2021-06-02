package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 80. 删除有序数组中的重复项 II
给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @Date 2021/6/2 20:51
 */

import com.szu.leetcode.utils.LeetCodes;

public class L80_RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 1;

        int length = nums.length;
//        找到第一个垃圾起始的地址（出现次数大于两次的第一个数字 的 第三号数字，就是垃圾起始位置）
        int garbage = findFirstGarbage(nums);
        if (garbage == -1)
            return length;
//        垃圾之后第一个有用的位置
        int useful = findFirstUsefulNum(garbage, nums);
//        每个有用的位置的数字都去和 nums[垃圾游标-2] 位置的数字比较，如果一样，那么这个数字就是垃圾
//        如果不同，那么这个数字就时可以收集作为答案的，然后垃圾游标向右移动
        for (int i = useful; i < length; i++) {
            if (nums[i] != nums[garbage - 2]) {
                nums[garbage] = nums[i];
                garbage++;
            }
        }

        return garbage + 1;
    }

    private int findFirstUsefulNum(int garbage, int[] nums) {

        int i = garbage + 1;
        for (; i < nums.length; i++) {
            if (nums[i] != nums[garbage])
                return i;
        }
        return nums.length;
    }

    private int findFirstGarbage(int[] nums) {

        int i = 2;

        while (i < nums.length) {
            if (nums[i - 2] == nums[i])
                return i;
            i++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] inputArray = LeetCodes.getInputArray("[1,1,1,2,2,3]");
        L80_RemoveDuplicates test = new L80_RemoveDuplicates();
        test.removeDuplicates(inputArray);
    }
}
