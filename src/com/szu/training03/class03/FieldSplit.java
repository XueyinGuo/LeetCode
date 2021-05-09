package com.szu.training03.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/5/9 20:39
 */

public class FieldSplit {

    /*
     * 生成子矩阵累加和，以后 O(1) 时间获得
     * */
    public static int[][] getSubMatrixSum(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] subMatrixSum = new int[rows][cols];
        int sum = 0;
        for (int c = 0; c < cols; c++) {
            sum += matrix[0][c];
            subMatrixSum[0][c] = sum;
        }
        sum = matrix[0][0];
        for (int r = 1; r < rows; r++) {
            sum += matrix[r][0];
            subMatrixSum[r][0] = sum;
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                subMatrixSum[r][c] = subMatrixSum[r - 1][c] + subMatrixSum[r][c - 1] - subMatrixSum[r - 1][c - 1] + matrix[r][c];
            }
        }

        return subMatrixSum;
    }

    public int getMaxMinField(int[][] matrix) {

        int cols = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for (int c1 = 0; c1 < cols - 3; c1++) {
            for (int c2 = 1; c2 < cols - 2; c2++) {
                for (int c3 = 2; c3 < cols - 1; c3++) {

                    res = Math.min(res, getBestDecision(matrix, c1, c2, c3));

                }
            }
        }
        return res;
    }

    public int getBestDecision(int[][] matrix, int c1, int c2, int c3) {
        int rows = matrix.length;
        // 0～i 切一刀，出来八块，最优指标，upChooses[i]
        int[] upChooses = getUpChooses(matrix, c1, c2, c3);
        // i ~ N-1 切一刀，出来八块，最优指标，down[i]
        int[] downChooses = getDownChooses(matrix, c1, c2, c3);

        int res = Integer.MIN_VALUE;
        for (int r = 1; r < rows - 2; r++) {
            res = Math.max( res, Math.min(upChooses[r], downChooses[r+1]) );
        }
        return res;
    }

    private int[] getDownChooses(int[][] matrix, int c1, int c2, int c3) {
        int length = matrix.length;
        int[] down = new int[length];
        down[length - 2] = Math.min(getValue(length - 1, length - 1, c1, c2, c3, matrix),
                getValue(length - 2, length - 2, c1, c2, c3, matrix));
        int split = length - 1;
        for (int i = length - 3; i >= 0; i--) {
            int minMax = twoSubMatrixMin(i, split - 1, length - 1, c1, c2, c3, matrix);
            while (split > i) {
                if (split == i + 1)
                    break;
                int cur = twoSubMatrixMin(i, split - 2, length - 1, c1, c2, c3, matrix);
                if (cur < minMax)
                    break;
                else {
                    minMax = cur;
                    split--;
                }
            }
            down[i] = minMax;
        }
        return down;
    }

    private int[] getUpChooses(int[][] matrix, int c1, int c2, int c3) {
        int length = matrix.length;

        int[] up = new int[length];
        up[1] = Math.min(getValue(0, 0, c1, c2, c3, matrix), getValue(1, 1, c1, c2, c3, matrix));

        int split = 0;
        for (int i = 2; i < length; i++) {
            int minMax = twoSubMatrixMin(0, split, i, c1, c2, c3, matrix);
            while (split < i) {
                if (split == i - 1)
                    break;
                int cur = twoSubMatrixMin(0, split + 1, i, c1, c2, c3, matrix);
                if (cur < minMax)
                    break;
                else {
                    minMax = cur; /* 记录穷举最小值中的最大值 */
                    split++;
                }
            }
            up[i] = minMax;
        }
        return up;
    }

    private int twoSubMatrixMin(int startRow, int split, int endRow, int c1, int c2, int c3, int[][] matrix) {

        return Math.min(getValue(startRow, split, c1, c2, c3, matrix), getValue(split + 1, endRow, c1, c2, c3, matrix));
    }

    private int getValue(int startRow, int endRow, int c1, int c2, int c3, int[][] matrix) {

        int part1 = getAreaValue(startRow, endRow, 0, c1, matrix);
        int part2 = getAreaValue(startRow, endRow, c1, c2, matrix);
        int part3 = getAreaValue(startRow, endRow, c2, c3, matrix);
        int part4 = getAreaValue(startRow, endRow, c3, matrix[0].length - 1, matrix);
        return Math.min(Math.min(part1, part2), Math.min(part3, part4));
    }

    private int getAreaValue(int startRow, int endRow, int startCol, int endCol, int[][] matrix) {

        int all = matrix[endRow][endCol];
        int left = startCol - 1 > 0 ? matrix[endRow][startCol - 1] : 0;
        int up = endRow - 1 > 0 ? matrix[endRow - 1][endCol] : 0;
        int extraAdd = (startRow - 1 > 0 && startCol - 1 > 0) ? matrix[startRow - 1][startCol - 1] : 0;
        return all - left - up - extraAdd;
    }


    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {2, 4, 6, 8},
                {1, 4, 5, 2}
        };
        getSubMatrixSum(matrix);
    }
}
