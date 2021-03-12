package com.szu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class L118_PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        // 0层的杨辉三角就是空集合
        if(numRows == 0){
            return res;
        }
        // 第一层就一个1
        List<Integer> first = new ArrayList<>();
        first.add(1);
        res.add(first);
        if (numRows == 1){
            return res;
        }
        // 第二层两个1
        List<Integer> second = new ArrayList<>();
        second.add(1);
        second.add(1);
        res.add(second);
        if (numRows == 2){
            return res;
        }
        // 不止两层 创建一个动态规划数组，大小 是 层数*层数
        int[][] dp = new int[numRows][numRows];
        // 先把前两层设置进去， 第一层一个1，第二层两个1
        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[1][1] = 1;
        // 开始生成动态规划数组
        // numRows 是 准备多少行的杨辉三角，（每一行的个数 = 第几行）
        doGenerate( dp,numRows);
        //把dp数组遍历一遍加到集合中待返回
        for (int i = 2; i < numRows; i++) {
            ArrayList<Integer> integers = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                integers.add(dp[i][j]);
            }
            res.add(integers);
        }
        return res;
    }

    public void doGenerate( int[][] dp,int numRows) {
        // 从第三行开始生成 ， （每一行的个数 = 第几行）
        // 从数组的 第三行开始生成，
        for (int i = 2; i < numRows; i++) {
            // 每行开头的那个都是1
            dp[i][0] = 1;
            // 每行从第二个开始生成，依赖上一行的下标生成
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
            // 每行结尾的那个都是1
            dp[i][i] = 1;
        }

    }
}
