package com.szu.leetcode.thread;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/8/30 23:55
 */

import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class RedPackage {
    volatile int people;
    int money;
    volatile int limit;
    Thread[] threads;
    Random random;
    BigDecimal oneHundred;
    final Object lock = new Object();

    public RedPackage(int people, int money) {
        this.people = people;
        this.money = money;
        this.threads = new Thread[people];
        this.limit = money * 100;
        this.random = new Random();
        oneHundred = new BigDecimal(100);
    }

    public BigDecimal[] getRedPack() throws InterruptedException {
        BigDecimal[] coins = new BigDecimal[people];
        AtomicInteger index = new AtomicInteger(0);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                synchronized (lock) {
                    int random;
                    if (people != 1)
                        random = this.random.nextInt(limit / people * 2 - 1) + 1;
                    else
                        random = limit;
                    limit -= random;
                    people--;
                    BigDecimal coin = new BigDecimal(random).divide(oneHundred);
                    coins[index.getAndIncrement()] = coin;
                }
            });
        }

        for (int i = threads.length - 1; i >= 0; i--) {
            threads[i].start();
        }
        for (int i = threads.length - 1; i >= 0; i--) {
            threads[i].join();
        }

        return coins;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入钱数");
        int money = scanner.nextInt();
        System.out.println("请输入人数");
        int people = scanner.nextInt();
        boolean flag = true;

        for (int i = 0; i < 100; i++) {
            RedPackage redPackage = new RedPackage(people, money);
            BigDecimal[] redPack = redPackage.getRedPack();
            BigDecimal sum = new BigDecimal(0);
            for (BigDecimal v : redPack) {
                sum = sum.add(v);
            }

            if (sum.doubleValue() != new BigDecimal(money).doubleValue()){
                System.out.println("FUCK");
                System.out.println(sum);
                flag = false;
                break;
            }
        }

        if (!flag)
            System.err.println("错误");
        else
            System.out.println("彳亍");
    }

}
