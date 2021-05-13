package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description

 128. 最长连续序列
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。


进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？

 * @Date 2021/5/12 22:43
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class L128_LongestConsecutive {

    /*
    * 类似于打印一连串消息的数据结构设计
    *
    * 用两个 Map ，一个用做 K：存储连续区间的开始数字 -> V： 连续区间长度
    *            一个用做 K: 存储每个连续取件的结束数字 -> V：连续区间长度
    *
    * 每次都用 遍历到的数字 去两个表中查 ：
    *   1.是否有比我大一号的开头，如果有那我可以接到这个开头前边
    *   2.是否有比我小一号的结尾，如果有，那么我可以接到这个结尾后边
    *
    * 接进去之后重新计算区间长度，然后删除之前的老头和老尾巴，
    * 为了防止重复处理，在 set 中记录处理过的数字
    * */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        HashMap<Integer, Integer> headMap = new HashMap<>();
        HashMap<Integer, Integer> tailMap = new HashMap<>();
        HashSet<Integer> processed = new HashSet<>();
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            if (!processed.contains(nums[i])) {
                headMap.put(nums[i], 1);
                tailMap.put(nums[i], 1);

                Integer nextHeadLen = headMap.get(nums[i] + 1);
                Integer lastTailLen = tailMap.get(nums[i] - 1);

                if (nextHeadLen != null) {
                    int newLen = 1 + nextHeadLen;
                    headMap.put(nums[i], newLen);
                    tailMap.remove(nums[i]);
                    tailMap.put(nums[i] + nextHeadLen, newLen);
                    headMap.remove(nums[i] + 1);
                    max = Math.max(newLen, max);
                }

                if (lastTailLen != null) {
                    tailMap.remove(nums[i] - 1);
                    Integer oldLen = headMap.remove(nums[i]);
                    int newLen = oldLen + lastTailLen;
                    headMap.put(nums[i] - lastTailLen, newLen);
                    tailMap.put(nums[i] + oldLen - 1, newLen);
                    max = Math.max(newLen, max);
                }
                processed.add(nums[i]);
            }

        }
        return max;
    }


    public static void main(String[] args) {
        int[] inputArray = LeetCodes.getInputArray("[-7,-1,3,-9,-4,7,-3,2,4,9,4,-9,8,-7,5,-1,-7]");
        int[] ints = LeetCodes.copyArray(inputArray);
        Arrays.sort(ints);
        L128_LongestConsecutive test = new L128_LongestConsecutive();
        int i = test.longestConsecutive(inputArray);
        System.out.println(i);
    }
}
