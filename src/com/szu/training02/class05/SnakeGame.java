package com.szu.training02.class05;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * TODO 给定一个二维数组 matrix，每个单元都是一个整数，有正有负。最开始的时候小 Q 操纵一跳长度为 0 的蛇蛇
 * 从矩阵的左侧任选一个格子空降进入地图。
 * 蛇每次指正到达当前位置的右上相邻，右侧相邻、右下相邻的格子。
 * 舍得长度到达下一个单元格之后自身长度就会 加上格子上的数字，任何情况下，蛇长度小于 0 的时候游戏结束。
 * 或者来到了 最右侧一列 游戏也结束。
 *
 * 这孩子是个天才，拥有一个超能力，可以在游戏过程中把一个格子的数字变成相反数，但是这个能力只能使用一次。
 * 问游戏过程中蛇的最长长度能有多长
 *
 * @Date 2021/4/29 15:29
 */

//import com.szu.leetcode.utils.LeetCodes;
//
//import java.util.Arrays;
//
//public class SnakeGame {
//
//
//    public static int violence(int[][] matrix) {
//        int ans = Integer.MIN_VALUE;
//        for (int r = 0; r < matrix.length; r++) {
//            int cur = 0;
//            if (matrix[r][0] < 0) {
//                cur = violence(matrix, r, 1, false, -matrix[r][0]);
//            }else
//                cur = violence(matrix, r, 1, true, matrix[r][0]);
//
//            if (cur > ans)
//                ans = cur;
//        }
//        return ans;
//    }
//
//    public static int violence(int[][] matrix, int r, int c, boolean canUseMagic, int hp) {
//        /* 列越界 返回 0 */
//        if (c == matrix[0].length)
//            return hp;
//
//        int ans = Integer.MIN_VALUE;
//        /* 有下一行 */
//        if (r + 1 < matrix.length) {
//            if (canGo(r + 1, c + 1, matrix, hp)) {
//                /* 下一条路上 不会让我的血量变成负数，可以走 */
//                int cur = violence(matrix, r + 1, c + 1, canUseMagic, hp + matrix[r][c]);
//                if (cur > ans)
//                    ans = cur;
//            } else if (canUseMagic) {
//                /* 下条路会让我死，在我能使用能力的情况下，我选择使用能力 */
//                int cur = violence(matrix, r + 1, c + 1, false, hp + (-matrix[r][c]));
//                if (cur > ans)
//                    ans = cur;
//            }
//        }
//        /* 有上一行 */
//        if (r - 1 >= 0) {
//            if (canGo(r - 1, c + 1, matrix, hp)) {
//                /* 下一条路上 不会让我的血量变成负数，可以走 */
//                int cur = violence(matrix, r - 1, c + 1, canUseMagic, hp + matrix[r][c]);
//                if (cur > ans)
//                    ans = cur;
//            } else if (canUseMagic) {
//                /* 下条路会让我死，在我能使用能力的情况下，我选择使用能力 */
//                int cur = violence(matrix, r - 1, c + 1, false, hp + (-matrix[r][c]));
//                if (cur > ans)
//                    ans = cur;
//            }
//        }
//
//        if (canGo(r, c + 1, matrix, hp)) {
//            /* 下一条路上 不会让我的血量变成负数，可以走 */
//            int cur = violence(matrix, r, c + 1, canUseMagic, hp + matrix[r][c]);
//            if (cur > ans)
//                ans = cur;
//        } else if (canUseMagic) {
//            /* 下条路会让我死，在我能使用能力的情况下，我选择使用能力 */
//            int cur = violence(matrix, r, c + 1, false, hp + (-matrix[r][c]));
//            if (cur > ans)
//                ans = cur;
//        }
//        return ans;
//    }
//
//    private static boolean canGo(int r, int c, int[][] matrix, int hp) {
//        if (c == matrix[0].length)
//            return false;
//        if (hp + matrix[r][c] < 0)
//            return false;
//        return true;
//    }
//
//
//    public static void main(String[] args) {
//        int times = 1000000;
//        for (int i = 0; i < times; i++) {
//            int[][] matrix = {
//                    LeetCodes.getRandomArrayWithNegative(5, 5),
//                    LeetCodes.getRandomArray(5, 5),
//                    LeetCodes.getRandomArrayWithNegative(5, 5),
//
//            };
//            int ans1 = walk1Right(matrix);
//            int ans2 = violence(matrix);
//            if (ans1 != ans2) {
//                for (int j = 0; j < matrix.length; j++) {
//                    System.out.println(Arrays.toString(matrix[j]));
//                }
//                System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
//                break;
//            }
//        }
//        System.out.println("finish");
//    }
//
//
//    /*
//     * 大神的暴力尝试代码
//     * */
//    public static int walk1Right(int[][] matrix) {
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
//            return 0;
//        }
//        int res = Integer.MIN_VALUE;
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                int[] ans = process(matrix, i, j);
//                res = Math.max(res, Math.max(ans[0], ans[1]));
//            }
//        }
//        return res;
//    }
//
//    // 从假想的最优左侧到达(i,j)的旅程中
//    // 0) 在没有使用过能力的情况下，返回路径最大和，没有可能到达的话，返回负
//    // 1) 在使用过能力的情况下，返回路径最大和，没有可能到达的话，返回负
//    public static int[] process(int[][] m, int i, int j) {
//        if (j == 0) { // (i,j)就是最左侧的位置
//            return new int[]{m[i][j], -m[i][j]};
//        }
//        int[] preAns = process(m, i, j - 1);
//        // 所有的路中，完全不使用能力的情况下，能够到达的最好长度是多大
//        int preUnuse = preAns[0];
//        // 所有的路中，使用过一次能力的情况下，能够到达的最好长度是多大
//        int preUse = preAns[1];
//        if (i - 1 >= 0) {
//            preAns = process(m, i - 1, j - 1);
//            preUnuse = Math.max(preUnuse, preAns[0]);
//            preUse = Math.max(preUse, preAns[1]);
//        }
//        if (i + 1 < m.length) {
//            preAns = process(m, i + 1, j - 1);
//            preUnuse = Math.max(preUnuse, preAns[0]);
//            preUse = Math.max(preUse, preAns[1]);
//        }
//        // preUnuse 之前旅程，没用过能力
//        // preUse 之前旅程，已经使用过能力了
//        int no = -1; // 之前没使用过能力，当前位置也不使用能力，的最优解
//        int yes = -1; // 不管是之前使用能力，还是当前使用了能力，请保证能力只使用一次，最优解
//        if (preUnuse >= 0) {
//            no = m[i][j] + preUnuse;
//            yes = -m[i][j] + preUnuse;
//        }
//        if (preUse >= 0) {
//            yes = Math.max(yes, m[i][j] + preUse);
//        }
//        return new int[]{no, yes};
//    }
//}
