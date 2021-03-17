package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *          50. Pow(x, n)
 *
 *          实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 *
 * @Date 2021/3/17 19:23
 */

public class L50_MyPow {

    public static void main(String[] args) {
        double x = 1.00000;

        int n = -2147483648;

        new L50_MyPow().myPow(x, n);
    }

    public double myPow(double x, int n) {
        long a = n;
        // 0 的任何次幂 都为 1
        if( n == 0)
            return 1;
        // 负数次幂 是 正数幂的倒数
        else if( n < 0 )
            return 1 / calculate(x, -a);
        else
            return calculate(x, n);
    }

    public double calculate(double x, long n){

        if(n == 1)
            return x;
        // 相当于 傻缓存
        double half = calculate(x, n >> 1);
        // x^5 = x^(2 * 2 + 1)
        if( n % 2 != 0 ){
            return half * half * x;
        }
        return half * half;
    }
}
