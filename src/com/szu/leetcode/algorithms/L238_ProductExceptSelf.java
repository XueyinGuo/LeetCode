package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 238. 除自身以外数组的乘积
给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * @Date 2021/5/26 19:56
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.ArrayList;
import java.util.List;

public class L238_ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0)
            return new int[]{};
        int length = nums.length;
        int[] ans = new int[length];
        /*
         * 数组中可能不止一个 0
         * */
        int product = 1;
        int zeroIndex = -1;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0) {
                /* 有两个 0 的时候，直接返回皆可以了 */
                if (zeroIndex != -1)
                    return new int[length];
                zeroIndex = i;
                continue;
            }
            product *= nums[i];
        }
        /* 有一个 0， 直接返回也可以了 */
        if (zeroIndex != -1) {
            ans[zeroIndex] = product;
            return ans;
        }
        /* 用除法求解 */
        for (int i = 0; i < length; i++) {
            ans[i] = product / nums[i];
        }
        return ans;
    }


    public int[] productExceptSelfWithOutDiv(int[] nums) {
        if (nums == null || nums.length == 0)
            return new int[]{};
        int length = nums.length;
        int[] ans = new int[length];
        /*
         * 数组中可能不止一个 0
         * */
        int zeroIndex = -1;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0) {
                /* 有两个 0 的时候，直接返回皆可以了 */
                if (zeroIndex != -1)
                    return new int[length];
                zeroIndex = i;

            }
            ans[i] = (i - 1 < 0) ? 1 : ans[i - 1] * nums[i - 1];
        }
        /* 继续求解后缀积 */
        int postProduct = 1;
        for (int i = length - 1; i >= 0; i--) {
            postProduct = (i + 1 == length) ? 1 : postProduct * nums[i + 1];
            ans[i] = ans[i] * postProduct;
        }
        return ans;
    }


    public static void main(String[] args) {
        L238_ProductExceptSelf test = new L238_ProductExceptSelf();
        int[] ints = test.productExceptSelfWithOutDiv(LeetCodes.getInputArray(                "[1,2,0,-4,3,4]"));
        System.out.println();
    }

}
