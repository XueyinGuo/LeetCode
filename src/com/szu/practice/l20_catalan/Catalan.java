package com.szu.practice.l20_catalan;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/23 20:56
 */

/*
* K(n) = 【K(0) * K(n-1) 】 + 【K(1) * K(n-2) 】 + 【K(2) * K(n-3)】 + ... + 【 K(n-1) * K(0)】
*
* =
*
* K(n) = c(2n, n) - c(2n, n-1) = c(2n, n) - c(2n, n+1)
*
* =
*
* K(n) = c(2n, n) / (n + 1)
*
* 卡特兰数表达式
* */
public class Catalan {
}
