package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 剑指 Offer 13. 机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 * @Date 2021/5/6 23:48
 */

public class Offer_13_RobotMovingRange {
    /*
    * 一个没什么新意的题目，无非是不一样的判断逻辑而已
    * 多加了一个 isValid() 方法判断一个位置是否合法罢辽
    *
    * 这还好意思中等题？
    * */
    public int movingCount(int m, int n, int k) {


        boolean[][] isVisited = new boolean[m][n];

        return getMovingCount(isVisited, 0, 0, k);

    }

    private int getMovingCount(boolean[][] isVisited, int row, int col, int k) {

        if (row < 0 || row == isVisited.length || col < 0 || col == isVisited[0].length || isVisited[row][col] || !isValid(row, col, k))
            return 0;
        int ans = 0;
        isVisited[row][col] = true;

        ans += getMovingCount(isVisited, row + 1, col, k);
        ans += getMovingCount(isVisited, row - 1, col, k);
        ans += getMovingCount(isVisited, row, col - 1, k);
        ans += getMovingCount(isVisited, row, col + 1, k);

        return ans + 1;
    }

    private boolean isValid(int row, int col, int k) {
        int sum = 0;
        while (row != 0) {
            sum += row % 10;
            row /= 10;
        }
        while (col != 0) {
            sum += col % 10;
            col /= 10;
        }

        if (sum > k)
            return false;
        return true;

    }

    public static void main(String[] args) {
        Offer_13_RobotMovingRange tet = new Offer_13_RobotMovingRange();
        System.out.println(tet.movingCount(10, 10, 20));
    }

}
