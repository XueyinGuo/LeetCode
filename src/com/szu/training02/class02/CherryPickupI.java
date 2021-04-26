package com.szu.training02.class02;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 从matrix左上角，走到右下角，过程中只能向右或者向下
 * 到达后，回来，过程中只能向左或者向上，沿途数字只能获得一遍
 * 返回，最大路径和
 *
 * @Date 2021/4/26 15:18
 */

public class CherryPickupI {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1}};
        int violence = violence(matrix);
        int dp = dp(matrix);
        System.out.println(violence);
        System.out.println(dp);

    }

    /*
     * 带个傻缓存的 dp
     * */
    private static int dp(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][][] dp = new int[rows][cols][rows];


        for (int ar = 0; ar < rows; ar++) {
            for (int ac = 0; ac < cols; ac++) {
                for (int br = 0; br < rows; br++) {
                    dp[ar][ac][br] = Integer.MIN_VALUE;
                }
            }
        }
        return dp(matrix, 0, 0, 0, dp, matrix.length, matrix[0].length);
    }

    private static int dp(int[][] matrix, int Ar, int Ac, int Br, int[][][] dp, int rows, int cols) {
        int Bc = Ar + Ac - Br;
        if ( Ar >= rows || Ac >= cols ||  Br >= rows || Bc >= cols)
            return 0;
        if (dp[Ar][Ac][Br] != Integer.MIN_VALUE)
            return dp[Ar][Ac][Br];

        if (Ar == rows - 1 && Ac == cols - 1) {
            dp[Ar][Ac][Br] = matrix[Ar][Ac];
            return matrix[Ar][Ac];
        }
        // 然后就可以四处走了
        // 右右
        int next = -1;
        next = Math.max(next, dp(matrix, Ar, Ac + 1, Br, dp, rows, cols));
        //右下
        next = Math.max(next, dp(matrix, Ar, Ac + 1, Br + 1, dp, rows, cols));
        //下右
        next = Math.max(next, dp(matrix, Ar + 1, Ac, Br, dp, rows, cols));
        //下下
        next = Math.max(next, dp(matrix, Ar + 1, Ac, Br + 1, dp, rows, cols));

        if (Ar == Br){

            dp[Ar][Ac][Br] = next + matrix[Ar][Ac];
            return dp[Ar][Ac][Br];
        }
        dp[Ar][Ac][Br] = next + matrix[Ar][Ac] + matrix[Br][Bc];
        return dp[Ar][Ac][Br];
    }

    private static int violence(int[][] matrix) {

        return violence(matrix, 0, 0, 0);
    }

    private static int violence(int[][] matrix, int Ar, int Ac, int Br) {
        int cols = matrix[0].length;
        int rows = matrix.length;
        if (Ar == rows - 1 && Ac == cols - 1)
            return matrix[Ar][Ac];

        int Bc = Ar + Ac - Br;
        // 来回一趟，等于两个人从出发角一块走到右下角，
        // 四种情况
        // A 往右走 B往右走
        int ARightBRight = -1;
        if (Ac + 1 < cols && Bc + 1 < cols)
            ARightBRight = violence(matrix, Ar, Ac + 1, Br);
        // A 往右走 B往下走
        int ARightBDown = -1;
        if (Ac + 1 < cols && Br + 1 < rows)
            ARightBDown = violence(matrix, Ar, Ac + 1, Br + 1);
        // A 往下走 B往右走
        int ADownBRight = -1;
        if (Ar + 1 < rows && Bc + 1 < cols)
            ADownBRight = violence(matrix, Ar + 1, Ac, Br);
        // A 往下走 B往下走
        int ADownBDown = -1;
        if (Ar + 1 < rows && Br + 1 < rows)
            ADownBDown = violence(matrix, Ar + 1, Ac, Br + 1);

        int ans = ARightBRight;
        if (ARightBDown > ans)
            ans = ARightBDown;
        if (ADownBRight > ans)
            ans = ADownBRight;
        if (ADownBDown > ans)
            ans = ADownBDown;
        /* 如果 A 和 B 重合 那么只获得一份答案 */
        if (Ar == Br)
            return matrix[Ar][Ac] + ans;
        return matrix[Ar][Ac] + matrix[Br][Bc] + ans;
    }

}
