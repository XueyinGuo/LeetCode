package com.szu.training.class04;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/15 21:38
 */

public class MaxSubMatrix {
    public int[] getMaxMatrix(int[][] matrix) {

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return new int[]{-1, -1, -1, -1};

        int rows = matrix.length;
        int cols = matrix[0].length;
        /*
         * 定义应该记录的值
         * */
        int max = Integer.MIN_VALUE;
        int r1 = -1;
        int c1 = -1;
        int r2 = -1;
        int c2 = -1;

        for(int r = 0 ; r < rows; r++){
            /* 先用 help数组 抓取 矩阵第一行，求出矩阵第一行的最大子数组  */
            int[] help = matrix[r];
            SubMatrixInfo info1 = getMaxSumInArray( help );
            if(info1.max > max){
                c1 = info1.c1;
                c2 = info1.c2;
                r1 = r;
                r2 = r;
                max = info1.max;
            }
            /* 然后从 help 与 第二行相加，继续寻找这个加完数组中的 最大子序列，此时子数组的最大值就代表了 第一行第二行的最大的矩阵 */
            /* 之后的逻辑就是 不断拿出新的一行 加到 help 上，此时子数组的最大值就代表了 第一行第二行 + 第三行的最大的矩阵 */
            /* 然后换一个第一行， 重新开始一边计算。相当于 计算了 Row^2 次最大子数组 */
            for(int rr = r + 1; rr < rows; rr++){
                for(int cc = 0; cc < cols; cc++){
                    help[cc] += matrix[rr][cc];
                }
                SubMatrixInfo info2 = getMaxSumInArray( help );
                if(info2.max > max){
                    c1 = info2.c1;
                    c2 = info2.c2;
                    r1 = r;
                    r2 = rr;
                    max = info2.max;
                }


            }
        }
        return new int[]{r1, c1, r2, c2};

    }

    /* 返回最大子数组的和 和 起始位置 结束位置 */
    public SubMatrixInfo getMaxSumInArray(int[] arr){
        int len = arr.length;
        int maxSubSum = Integer.MIN_VALUE;
        int curSum = 0;
        int c1 = 0;
        int c2 = 0;
        int start = 0;
        for(int i = 0; i < len; i++){
            if(curSum == 0)
                start = i;
            curSum += arr[i];
            if(curSum > maxSubSum){
                maxSubSum = curSum;
                c1 = start;
                c2 = i;
            }
            if(curSum < 0){
                curSum = 0;

            }


        }
        return new SubMatrixInfo(maxSubSum, c1, c2);

    }



    public static void main(String[] args) {
        int[][] matrix = {
                {9,-8,1,3,-2},
                {-3,7,6,-2,4},
                {6,-4,-4,8,-7}};
        int[] maxMatrix = new MaxSubMatrix().getMaxMatrix(matrix);
        System.out.println();
    }

}

class SubMatrixInfo {

    int max;
    /* 起始位置 结束位置 */
    int c1;
    int c2;

    public SubMatrixInfo(int max, int c1, int c2){
        this.max = max;
        this.c1 = c1;
        this.c2 = c2;
    }

}
