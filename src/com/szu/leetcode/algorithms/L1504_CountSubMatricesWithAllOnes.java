package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 1504. 统计全 1 子矩形
给你一个只包含 0 和 1 的 rows * columns 矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
 *
 * @Date 2021/6/2 22:32
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.HashMap;

public class L1504_CountSubMatricesWithAllOnes {


    public int numSubmat(int[][] mat) {

        int row = mat.length;
        int col = mat[0].length;
        /*
         * 先统计每个位置往左看的话，最多在本行能组成几个矩形
         * 经过这个循环之后
         * [                           [
         *      [1,1,1],                    [1,2,3],
         *      [1,0,1],     ---->          [1,0,1],
         *      [1,1,1]                     [1,2,3]
         * ]                            ]
         * */
        for (int r = 0; r < row; r++) {
            for (int c = 1; c < col; c++) {
                if (mat[r][c] > 0)
                    mat[r][c] = mat[r][c - 1] + 1;
            }
        }
        /*
        * [
        *     [1,2,3],
        *     [1,0,1],
        *     [1,2,3]
        * ]
        * 此时每个位置已经表示：【在本行中】，以所在位置位最右下角的元素，能形成几个子矩阵
        * 此时 我们统计每行的值时，只需要把此值加起来，即得到了每行形成的矩阵个数
        *
        * 但是除此之外还需要统计在列上能形成多少个
        * */
        int ans = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                ans += mat[r][c];  // 统计每行形成的矩阵个数
                int leftNums = mat[r][c]; // 统计完每行的个数之后需要统计列上能形成的个数
                /*
                * 此时开始往上看，只有上边的数字 大于 0 的时候才能向上形成矩阵
                * */
                for (int k = r - 1; k >= 0; k--) {
                    if (mat[k][c] > 0){
                        /*
                        * [1,2,3],
                        * [1,0,1],
                        * [1,2,3]
                        *
                        * 假设我们现在来到了 mat[2][2] 位置
                        * 此时在[2][2]本行上能形成 3 个，但是在列上能形成的个数 是受上边数字影响的，
                        * 此时 [1][2] 位置位 1，从第三行到第二行上只能形成 1 个矩阵
                        *
                        *
                        * [1,2,3,4],
                        * [1,0,1,2],
                        * [1,2,3,4]
                        *
                        * 从第三行第三列往上捅， 通道第二行形成 1 个，通道第一行形成 2 个
                        * 从第三行第四列往上捅， 通道第二行形成 2 个，通道第一行形成 4 个
                        * */
                        leftNums = Math.min(leftNums, mat[k][c]);
                        ans += leftNums;
                    }
                    else // 往上已经等于 0 了，直接退出循环
                        break;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {

        int[][] inputMatrix = LeetCodes.getInputMatrix("[[1,1,1],[1,0,1],[1,1,1]]");
        L1504_CountSubMatricesWithAllOnes test = new L1504_CountSubMatricesWithAllOnes();
        int i = test.numSubmat(inputMatrix);
        System.out.println(i);

    }
}
