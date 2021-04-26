package com.szu.training02.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个二维数组 matrix，在给定一个 k 值
 * 返回累加和小于等于 k ，但是离 k 最近的子矩阵累加和
 *
 * @Date 2021/4/26 10:23
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Random;
import java.util.TreeSet;

public class MaxSubMatrixSumNoLargerThanK {

    public static int getMaxLessOrEqualK(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        TreeSet<Integer> set = new TreeSet<>();
        int max = Integer.MIN_VALUE;
        /* 穷举每个矩阵，第一个矩阵就是必须包含第一行的 */
        for (int start = 0; start < rows; start++) {
            int[] arr = new int[cols];

            /* 穷举每个子矩阵，第一个矩阵就是有且仅有第一行的 */
            for (int end = start; end < rows; end++) {
                /* 如果来到下一行，就把两行压在一起，搞成一个数组，周而复始，最终整个矩阵都被压缩成一个数组 */
                for (int i = 0; i < cols; i++) {
                    arr[i] += matrix[end][i];
                }
                /**
                 * 在被压缩的数组中，搞定单个数组中如何查找 {@link MaxSubArraySumLessOrEqualK}
                 * */
                /*
                 * ===============================================================
                 * ===============================================================
                 * 每个子矩阵都当成压缩成一个数组，对待每个数组，需要把每个 set 和 sum 都要重置
                 * ===============================================================
                 * ===============================================================
                 * */
                int sum = 0;
                set.add(0);
                for (int i = 0; i < cols; i++) {
                    sum += arr[i];
                    int dif = sum - k;
                    Integer ceiling = set.ceiling(dif);
                    if (ceiling != null) {
                        int cur = sum - ceiling;
                        if (cur > max)
                            max = cur;
                    }
                    set.add(sum);
                }
                set.clear();
                /*
                 * ===============================================================
                 * ===============================================================
                 * ===============================================================
                 * ===============================================================
                 * */
            }
        }
        return max;
    }

    /*
    * 大神的正确代码
    * */
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix[0] == null)
            return 0;
        int row = matrix.length, col = matrix[0].length, res = Integer.MIN_VALUE;
        TreeSet<Integer> sumSet = new TreeSet<>();
        for (int s = 0; s < row; s++) { // s开始行
            int[] colSum = new int[col];
            for (int e = s; e < row; e++) { // e结束行
                // 子矩阵必须包含s~e行的数，且只包含s~e行的数
                sumSet.add(0);
                int rowSum = 0;
                for (int c = 0; c < col; c++) {
                    colSum[c] += matrix[e][c];
                    rowSum += colSum[c];
                    Integer it = sumSet.ceiling(rowSum - k);
                    if (it != null) {
                        res = Math.max(res, rowSum - it);
                    }
                    sumSet.add(rowSum);
                }
                sumSet.clear();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {

            int[][] matrix = {
                    LeetCodes.getRandomArrayWithNegative(30, 300),
                    LeetCodes.getRandomArrayWithNegative(30, 200),
                    LeetCodes.getRandomArrayWithNegative(30, 100),
                    LeetCodes.getRandomArrayWithNegative(30, 90),
                    LeetCodes.getRandomArrayWithNegative(30, 80),
                    LeetCodes.getRandomArrayWithNegative(30, 60),
                    LeetCodes.getRandomArrayWithNegative(30, 70),
                    LeetCodes.getRandomArrayWithNegative(30, 120),
                    LeetCodes.getRandomArrayWithNegative(30, 110),
                    LeetCodes.getRandomArrayWithNegative(30, 100),
                    LeetCodes.getRandomArrayWithNegative(30, 95),
            };
            int k = random.nextInt(10000);

            int my = getMaxLessOrEqualK(matrix, k);
            int right = maxSumSubmatrix(matrix, k);
            if (right != my)
                System.out.println("FUCK");
        }
    }

}
