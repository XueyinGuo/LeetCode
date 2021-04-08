package com.szu.leetcode.thread;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  TODO 1195. 交替打印字符串
    编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：

    如果这个数字可以被 3 整除，输出 "fizz"。
    如果这个数字可以被 5 整除，输出 "buzz"。
    如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
    例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 *
 * @Date 2021/4/8 23:30
 */

import java.util.function.IntConsumer;

public class L1195_FizzBuzz {

    private int n;
    static Thread t1;
    static Thread t2;
    static Thread t3;
    static Thread t4;


    public L1195_FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {

    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {

    }

    public static void main(String[] args) {
        L1195_FizzBuzz test = new L1195_FizzBuzz(15);
        t1 = new Thread("t1");
        t2 = new Thread("t2");
        t3 = new Thread("t3");
        t4 = new Thread("t4");

        Runnable printFizz = () -> {
            // 被3整除
        };

        Runnable printBuzz = () -> {
            // 被5整除
        };

        Runnable printFizzBuzz = () -> {
            // 被 3 和 5 整除
        };

        Runnable printNumber = () -> {

        };

        for (int i = 1; i <= test.n ; i++) {

        }
    }

}
