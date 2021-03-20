package com.szu.practice.fib;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *          母牛每年生一只母牛，新出生的母牛成长三年后也能每年生一只母牛，假设不会死。
 *          第一年农场有一只成熟的母牛，从第二年开始，母牛开始生小母牛，求N年后，母牛的数量。
 *
 * @Date 2021/3/20 16:05
 */

import com.szu.leetcode.utils.LeetCodes;

public class FemaleCow {



    public static void main(String[] args) {
        int years = 19;
        /* F(n) = F(n-1) + F(n-3) */
        int normal = getFemaleCowCountNormal(years);
        /* log(n) 级别， 快速幂计算 */
        /* k阶问题的递推式 F(n) = C1 * F(n-1) + C2 * F(n-2) + ... + Ck * F(n-k) */

        /*                                          |  |^(n-k)         */
        /* F(n)F(n-1)...F(n-k+1) = |F1 F2 ... Fk| * |  | (k * k阶矩阵)         */
        int quick = getFemaleCowCountFastExponentiation(years);
        System.out.println(normal);
        System.out.println(quick);
    }

    private static int getFemaleCowCountFastExponentiation(int years) {

        int[][] base = {
                { 1, 1, 0 },
                { 0, 0, 1 },
                { 1, 0, 0 } };
        int[][] res = LeetCodes.matrixPower(base, years - 3);
        /*                           |  |^(n-3)   */
        /* 三阶问题就是 F(3)F(2)F(1) * |  |(3*3阶)   */
        /*             3  2  1                    */
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }



    private static int getFemaleCowCountNormal(int years) {
        if ( years == 1) return 1;
        if ( years == 2) return 2;
        if ( years == 3) return 3;
        return getFemaleCowCountNormal(years-1) + getFemaleCowCountNormal(years - 3);
    }

}
