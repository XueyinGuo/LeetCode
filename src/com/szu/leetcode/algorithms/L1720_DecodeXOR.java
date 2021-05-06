package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 1720. 解码异或后的数组
未知 整数数组 arr 由 n 个非负整数组成。

经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。

给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。

请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
 *
 * @Date 2021/5/6 21:55
 */

public class L1720_DecodeXOR {

    public int[] decode(int[] encoded, int first) {
        int[] decode = new int[encoded.length+1];
        decode[0] = first; /* 题目说了 这是第一个数字 */
        decode[1] = encoded[0] ^ first; /* 异或交换律 */

        for(int i = 1; i < encoded.length; i++){
            decode[i+1] = encoded[i] ^ decode[i]; /* 怼下标 */

        }
        return decode;
    }

}
