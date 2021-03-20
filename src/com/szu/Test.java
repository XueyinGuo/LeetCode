package com.szu;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 13:19
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.HashSet;

public class Test {

    public static int[][] matrixMulti1(int[][] m1, int[][] m2){
        int[][] m = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2[0].length; k++) {
                    m[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return m;
    }

    public static int[][] matrixMultiplication(int[][] m1, int[][] m2) {
        int m[][] = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2[0].length; k++) {
                    m[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return m;
    }

    public static void main(String[] args) {
        int[][] m1 = {
                {1,1},
                {1,1},
                {1,1}
        };
        int[][] m2 = {
                {2,2,2},
                {2,2,2}
        };
        int[][] m3 = {
                {1,1},
                {1,1}
        };
        int[][] m4 = {
                {21,2},
                {2,2}
        };
        int[][] ints3 = matrixMulti1(m3, m4);
        int[][] ints1 = matrixMultiplication(m1, m2);
        int[][] ints2 = matrixMulti1(m1, m2);
        System.out.println();
    }
}