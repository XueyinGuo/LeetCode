package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *          34. 在排序数组中查找元素的第一个和最后一个位置

 *
 *          给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

 *          如果数组中不存在目标值 target，返回 [-1, -1]。

 *          进阶：

 *          你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？

 *          来源：力扣（LeetCode）
 *          链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 *          著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @Date 2021/2/14 0:09
 */

import java.util.ArrayList;
import java.util.List;

public class L34_SearchRange {

    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0){
            return  new int[]{-1, -1};
        }
        // 通过二分查找找到这个数的位置
        int start = doSearch(nums, target, 0, nums.length-1);
        // 如果压根没有这个数，start就是-1，直接返回两个 -1
        if(start == -1){
            return new int[]{-1, -1};
        }
        // 然后像两侧寻找（先往前找）
        int end = start;
        for(int i = start + 1; i < nums.length; i++){
            if(nums[i] != target){
                break;
            }
            end++;
        }
        // 再往后找
        for(int i = start - 1; i >= 0; i--){
            if(nums[i] != target){
                break;
            }
            start--;
        }
        return new int[]{start, end};
    }

    public int doSearch(int[] nums, int target, int l, int r){
        if(l >= r){
            if(nums[l] != target){
                return -1;
            }
            return l;
        }
        int mid = l + (r - l)/2;
        if( nums[mid] == target ){
            return mid;
        }else if(nums[mid] > target){
            return doSearch(nums, target, l ,mid - 1);
        }else{
            return doSearch(nums, target, mid + 1, r);
        }
    }

    public static void main(String[] args) {
        List<char[]> list = new ArrayList<>();

    }

}
