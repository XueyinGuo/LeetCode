package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 1240. 铺瓷砖
你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。

房子的客厅大小为 n x m，为保持极简的风格，需要使用尽可能少的 正方形 瓷砖来铺盖地面。

假设正方形瓷砖的规格不限，边长都是整数。

请你帮设计师计算一下，最少需要用到多少块方形瓷砖？
 *
 * @Date 2021/6/13 0:15
 */

import java.util.Arrays;

public class L1240_TilingRectangle {

    public int tilingRectangle(int n, int m) {
        int small = Math.min(n, m);
        int large = small == m ? n : m;
        int total = m * n;
        int[] dp = new int[total + 1];
        dp[1] = 1;
        for (int i = 2; i <= total; i++) {
            int limit = (int) Math.sqrt(small);

        }
        return 0;
    }


    public static void main(String[] args) {
        L1240_TilingRectangle test = new L1240_TilingRectangle();
        System.out.println(test.tilingRectangle(9, 8));
    }
}
