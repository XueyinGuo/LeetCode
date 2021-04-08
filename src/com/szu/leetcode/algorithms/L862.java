package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *      TODO
 *
 * @Date 2021/3/30 20:00
 */

import java.util.HashMap;

public class L862 {

    public static void main(String[] args) {
        int []A = {48, 99, 37, 4, -31, 140};
        int k = 167;
        new L862().shortestSubarray(A, k);
    }

    public int shortestSubarray(int[] A, int K) {
        HashMap<Integer, Integer> sumIndexMap = new HashMap<>();
        sumIndexMap.put(0, -1);
        int sum = 0;
        int i = 0;
        int minLen = Integer.MAX_VALUE;
        while(i < A.length){
            sum += A[i];
            int dif = sum - K;
            Integer l = sumIndexMap.get(dif);
            if(l != null)
                minLen = Math.min(i - l, minLen);
            sumIndexMap.put(sum, i);
            i++;
        }
        if(minLen == Integer.MAX_VALUE)
            return -1;
        return minLen;
    }
}
