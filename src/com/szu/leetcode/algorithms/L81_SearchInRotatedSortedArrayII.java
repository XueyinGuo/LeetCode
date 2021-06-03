package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 81. 搜索旋转排序数组 II
已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
* 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
* 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。

给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 *
 * @Date 2021/6/2 21:17
 */

import com.szu.leetcode.utils.LeetCodes;

public class L81_SearchInRotatedSortedArrayII {
    /*
     * TODO 二分失败！！！！
     * */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int l = 0;
        int r = nums.length - 1;
        int m;
        while (l < r) {
            m = (l + r) / 2;
            // 如果这个位置是断崖
            if (m - 1 >= 0 && nums[m] < nums[m - 1]) {
                if (target > nums[r]) {// 比断崖最后一个数字都大，去上一段找吧
                    r = m - 1;
                } else if (target > nums[m]) { // 可以在小得那段中找
                    l = m + 1;
                } else if (target < nums[m]) // 比小的那段最小的还小，肯定没答案了
                    return false;
                else
                    return true;
            } else {
                // 不是断崖,直接二分就好了
                if (target > nums[m])
                    l = m + 1;
                else if (target < nums[m])
                    r = m - 1;
                else
                    return true;
            }
        }
        return false;
    }


    /*
     * 老办法：
     * 找悬崖位置，判断 target 与 悬崖位置的值
     * */
    public boolean search2(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int length = nums.length;
        int cliff = length - 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == target)
                return true;
            if (nums[i] > nums[i + 1])
                cliff = i;
        }
        if (cliff == length - 1)
            return nums[length - 1] == target;
        return binSearch(nums, cliff, target);
    }

    private boolean binSearch(int[] nums, int cliff, int target) {
        int l, r;
        int length = nums.length;
        if (target > nums[length - 1]) {
            return false;
        }
        l = cliff + 1;
        r = length - 1;
        return binSearch(nums, l, r, target);
    }

    private boolean binSearch(int[] nums, int l, int r, int target) {
        int m;
        while (l < r) {
            m = (l + r) >> 1;
            if (target > nums[m])
                l = m + 1;
            else if (target < nums[m])
                r = m - 1;
            else
                return true;
        }

        return nums[l] == target;
    }

    public static void main(String[] args) {
        int[] inputArray = LeetCodes.getInputArray("[1,0,1,1,1]");
        int k = 0;
        L81_SearchInRotatedSortedArrayII test = new L81_SearchInRotatedSortedArrayII();
        test.search(inputArray, k);
    }
}
