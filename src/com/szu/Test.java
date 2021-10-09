package com.szu;

import com.szu.leetcode.algorithms.L37_SolveSudoku;
import com.szu.leetcode.utils.LeetCodes;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;

public class Test {
    /*
    * 第一题
    * 矩阵中到指定位置代价最小的路径，只能往右和往下走
    *
    * x 是列
    * y 是行
    * */
    int row;
    int col;

    public int solution(int[][] grid, int x, int y) {
        row = y + 1;
        col = x + 1;
        int[][] dp = new int[row][col];
        return process(grid, 0, 0, dp);
    }

    private int process(int[][] grid, int r, int c, int[][] dp) {
        if (r == row - 1 && c == col - 1)
            return grid[r][c];

        int right = Integer.MAX_VALUE;
        int down = Integer.MAX_VALUE;

        if (dp[r][c] != 0)
            return dp[r][c];
        int cur = grid[r][c];
        if (isValid(r + 1, c)) {
            int process = process(grid, r + 1, c, dp);
            down = process == Integer.MAX_VALUE ? process : process + cur;
        }

        if (isValid(r, c + 1)) {
            int process = process(grid, r, c + 1, dp);
            right = process == Integer.MAX_VALUE ? process : process + cur;
        }
        dp[r][c] = Math.min(right, down);
        return dp[r][c];
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < col;
    }

    /*
     * 第二题
     *
     * 第一个ip数组中的元素是否在黑名单中，黑名单也是一个ip数组
     * 在黑名单 为 true
     * 不在是 false
     * 返回 [true, false, true]形式的结果
     * */
    public boolean[] isBlackIp(String[] ipArr, String[] blackIpArr) {
        Set<String> blacks = new HashSet<>();
        blacks.addAll(Arrays.asList(blackIpArr));
        boolean[] res = new boolean[ipArr.length];
        for (int i = 0; i < ipArr.length; i++) {
            boolean contains = blacks.contains(ipArr[i]);
            if (contains)
                res[i] = true;
        }
        return res;
    }

    /*
     * 第三题
     * 给一个long数，移动一个数字到某一位置，使这个数字变成所有全排列结果中最小的一个
     * */
    public long smallest(long randomData) {
        char[] str = (randomData + "").toCharArray();
        Info info = getSmallest(str);
        StringBuffer sb = new StringBuffer();
        sb.append(str[info.index]);
        for (int i = 0; i < str.length; i++) {
            if (i != info.index) {
                sb.append(str[i]);
            }
        }
        return Long.parseLong(sb.toString());
    }

    private Info getSmallest(char[] str) {
        char res = '9';
        int index = 0;
        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            if (c < res) {
                index = i;
                res = c;
            }
        }
        return new Info(res, index);
    }

    static class Info {
        char c;
        int index;

        public Info(char c, int index) {
            this.c = c;
            this.index = index;
        }
    }
}


