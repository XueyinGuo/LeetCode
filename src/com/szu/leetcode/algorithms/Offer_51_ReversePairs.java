//package com.szu.leetcode.algorithms;
///*
// * @Author 郭学胤
// * @University 深圳大学
// * @Description
// *
// *  剑指 Offer 51. 数组中的逆序对
//    在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
//
//    https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
// *
// * @Date 2021/4/10 14:00
// */
//
//import java.util.LinkedList;
//
//public class Offer_51_ReversePairs {
//
//    public int reversePairs(int[] nums) {
//        if (nums == null || nums.length == 0)
//            return 0;
//        int length = nums.length;
//
//        LinkedList<InfoReversePairs> linkedList = new LinkedList<>();
//        linkedList.add(new InfoReversePairs(nums[length - 1], 0));
//
//        for (int i = length - 2; i >= 0 ; i--) {
//            linkedList.
//        }
//    }
//
//}
//
//class InfoReversePairs{
//    int num;
//    int lessCount;
//
//    public InfoReversePairs(int num, int lessCount) {
//        this.num = num;
//        this.lessCount = lessCount;
//    }
//}
