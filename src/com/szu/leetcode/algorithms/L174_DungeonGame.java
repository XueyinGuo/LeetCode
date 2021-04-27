package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个二维数组，含义是一张地图例如
 * -2  -3   3
 * -5  -10  1
 * 0   30   -5
 *
 * 游戏规则如下：其实从右上角触发，每次只能向右或者向下走，最后到右下角见到公主
 * 地图每个位置代表骑士遇到的事情，
 * 如果是负数，表示需要打怪，骑士损失血量
 * 如果是非负数，说明此处有血瓶，可以让骑士回复血量
 *
 * 骑士从左上角到右下角的过程中，走到任意一个位置的时候，血量都不能少于 1，
 * 初始血量应该如何设置？
 *
 * @Date 2021/4/26 13:02
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Random;

public class L174_DungeonGame {

    public static int getMinHPDp(int[][] map) {
        int row = map.length;
        int col = map[0].length;
        int[][] dp = new int[row][col];

        dp[row - 1][col - 1] = map[row - 1][col - 1] < 0 ? 1 - map[row - 1][col - 1] : 1;

        for (int r = row - 2; r >= 0; r--) {
            /* 下一个格子 需要 额外的血量才能上去 */
            if (dp[r + 1][col - 1] != 1) {
                /* 我自己的格子 需要血量吗 */
                if (map[r][col - 1] < 0)
                    dp[r][col - 1] =  - map[r][col - 1] + dp[r + 1][col - 1];
                else {
                    /* 我是补血的格子 */
                    int recovery = map[r][col - 1] - dp[r + 1][col - 1];
                    dp[r][col - 1] = recovery >= 0 ? 1 : -recovery;
                }
                /* 下个格子不需要额外的血量 */
            } else {
                if (map[r][col - 1] < 0)
                    dp[r][col - 1] = 1 - map[r][col - 1];
                else
                    dp[r][col - 1] = 1;
            }
        }

        for (int c = col - 2; c >= 0; c--) {
            /* 下一个格子 需要 额外的血量才能上去 */
            if (dp[row - 1][c + 1] != 1) {
                /* 我自己的格子 需要血量吗 */
                if (map[row - 1][c] < 0)
                    dp[row - 1][c] =  - map[row - 1][c] + dp[row - 1][c + 1];
                    /* 我是来个给你补血的 */
                else {
                    int recovery = map[row - 1][c] - dp[row - 1][c + 1];
                    dp[row - 1][c] = recovery >= 0 ? 1 :  - recovery;
                }
            } else {
                /* 下个格子不需要额外的血量 */
                /* 但是我自己需要血量吗？ */
                if (map[row - 1][c] < 0)
                    dp[row - 1][c] = 1 - map[row - 1][c];
                else { /* 我自己也不需要血量,给你补血 */
                    dp[row - 1][c] = 1;
                }
            }
        }

        for (int r = row - 2; r >= 0; r--) {
            for (int c = col - 2; c >= 0; c--) {
                int min = Math.min(dp[r + 1][c], dp[r][c + 1]);
                /* 下一个格子 需要 额外的血量才能上去 */
                if (min != 1) {
                    /* 我自己的格子 需要血量吗 */
                    if (map[r][c] < 0)
                        dp[r][c] =  - map[r][c] + min;
                    /* 我是来个给你补血的 */
                    else {
                        int recovery = map[r][c] - min;
                        dp[r][c] = recovery >= 0 ? 1 :  - recovery;
                    }
                } else {
                    /* 下个格子不需要额外的血量 */
                    /* 但是我自己需要血量吗？ */
                    if (map[r][c] < 0)
                        dp[r][c] = 1 - map[r][c];
                    else { /* 我自己也不需要血量,给你补血 */
                        dp[r][c] = 1;
                    }
                }
            }
        }
        return dp[0][0];
    }

    public static int getMinHP(int[][] map) {
        if (map == null || map.length == 0)
            return 1;
        int min = getMinHPViolence(map, 0, 0);
        if (min > 0)
            return 1;
        return 1 - min;
    }

    /*
    * TODO 自己的暴力解 还有 Bug
    * */
    public static int getMinHPViolence(int[][] map, int row, int col) {

        if (row == map.length - 1 && col == map[0].length - 1)
            return map[row][col];

        int down = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;
        if (row + 1 < map.length)
            down = map[row + 1][col];
        if (col + 1 < map[0].length)
            right = map[row][col + 1];
        int max = down;
        int direction1 = row + 1;
        int direction2 = col;
        if (right > max) {
            max = right;
            direction1 = row;
            direction2 = col + 1;
        }
        int res = getMinHPViolence(map, direction1, direction2);
        if (res > 0)
            return map[row][col];
        return res + map[row][col];
    }


    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10000000; i++) {

            int[][] matrix = {
                    LeetCodes.getRandomArrayWithNegative(100, 100),
                    LeetCodes.getRandomArrayWithNegative(100, 100),

            };
//            int[][] matrix = {
//                    {3, -5, -1, -2, -1},
//                    {-5, -3, -2, -3, -3}
//
//            };

            int my = getMinHP(matrix);
            int right = minHP1(matrix);
            int right2 = minHP2(matrix);
            int myDp = getMinHPDp(matrix);
            if (right != myDp || right2 != myDp)
                System.out.println("FUCK");
        }
//        int[][] map = {
//                {10, 30, -5, 9, -3},
//                {-5, -10, 1, -2, -9},
//                {-2, -3, 3, -1, 7},
//        };
//        System.out.println(getMinHP(map));
//        System.out.println(minHP2(map));

    }


    /*
     * 大神的正确码
     * */
    // 来到了matrix[row][col]，还没登上去，到达右下角，返回至少的初始血量
    public static int process(int[][] matrix, int N, int M, int row, int col) {
        if (row == N - 1 && col == M - 1) { // 已经达到右下角了
            return matrix[N - 1][M - 1] < 0 ? (-matrix[N - 1][M - 1] + 1) : 1;
        }
        if (row == N - 1) {
            int rightNeed = process(matrix, N, M, row, col + 1);
            if (matrix[row][col] < 0) { // 3    -7    10
                return -matrix[row][col] + rightNeed;
            } else if (matrix[row][col] >= rightNeed) {  // 3    3    1
                return 1;
            } else { //  3   1    2
                return rightNeed - matrix[row][col];
            }
        }
        if (col == M - 1) {
            int downNeed = process(matrix, N, M, row + 1, col);
            if (matrix[row][col] < 0) { // 3    -7    10
                return -matrix[row][col] + downNeed;
            } else if (matrix[row][col] >= downNeed) {  // 3    3    1
                return 1;
            } else { //  3   1    2
                return downNeed - matrix[row][col];
            }
        }
        int minNextNeed = Math.min(process(matrix, N, M, row, col + 1), process(matrix, N, M, row + 1, col));
        if (matrix[row][col] < 0) { // 3    -7    10
            return -matrix[row][col] + minNextNeed;
        } else if (matrix[row][col] >= minNextNeed) {  // 3    3    1
            return 1;
        } else { //  3   1    2
            return minNextNeed - matrix[row][col];
        }
    }

    public static int minHP1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 1;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row--][col--];
        dp[row][col] = m[row][col] > 0 ? 1 : -m[row][col] + 1;
        for (int j = col - 1; j >= 0; j--) {
            dp[row][j] = Math.max(dp[row][j + 1] - m[row][j], 1);
        }
        int right = 0;
        int down = 0;
        for (int i = row - 1; i >= 0; i--) {
            dp[i][col] = Math.max(dp[i + 1][col] - m[i][col], 1);
            for (int j = col - 1; j >= 0; j--) {
                right = Math.max(dp[i][j + 1] - m[i][j], 1);
                down = Math.max(dp[i + 1][j] - m[i][j], 1);
                dp[i][j] = Math.min(right, down);
            }
        }
        return dp[0][0];
    }

    public static int minHP2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 1;
        }
        int more = Math.max(m.length, m[0].length);
        int less = Math.min(m.length, m[0].length);
        boolean rowmore = more == m.length;
        int[] dp = new int[less];
        int tmp = m[m.length - 1][m[0].length - 1];
        dp[less - 1] = tmp > 0 ? 1 : -tmp + 1;
        int row = 0;
        int col = 0;
        for (int j = less - 2; j >= 0; j--) {
            row = rowmore ? more - 1 : j;
            col = rowmore ? j : more - 1;
            dp[j] = Math.max(dp[j + 1] - m[row][col], 1);
        }
        int choosen1 = 0;
        int choosen2 = 0;
        for (int i = more - 2; i >= 0; i--) {
            row = rowmore ? i : less - 1;
            col = rowmore ? less - 1 : i;
            dp[less - 1] = Math.max(dp[less - 1] - m[row][col], 1);
            for (int j = less - 2; j >= 0; j--) {
                row = rowmore ? i : j;
                col = rowmore ? j : i;
                choosen1 = Math.max(dp[j] - m[row][col], 1);
                choosen2 = Math.max(dp[j + 1] - m[row][col], 1);
                dp[j] = Math.min(choosen1, choosen2);
            }
        }
        return dp[0];
    }

}
