package com.szu.practice.l11_print_skill;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      强迫症买苹果，要用袋子装，每个袋子都需要装满，如果有一个袋子装不满，他就不买了
 *      给定一个苹果数量，看看这个傻逼会不会买，如果买 就返回 他使用的袋子的最小数量
 *      如果不买，就返回 -1
 *
 *      袋子的容量为 6， 8，小袋子装6个 大袋子装8个，用大用小都算用一个袋子
 *
 * @Date 2021/3/26 16:52
 */

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BuyApple {

    public static void main(String[] args) {
//        for (int i = 0; i < 200; i++) {
//            System.out.println(i + "  "+ minBags(i));
//        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 50,
                60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 100000; i++) {
            Random random = new Random();
            int randomApples = random.nextInt(Integer.MAX_VALUE);
            Runnable runnable = ()->{
                int i1 = minBagAwesome(randomApples);
                int i2 = minBags(randomApples);
                if (i1 != i2)
                    System.out.println("FUCK");
                System.out.println(Thread.currentThread().getName());
            };
            threadPoolExecutor.submit(runnable);
        }

    }

    private static int minBags(int apples) {

        if (apples <= 5 || apples == 7)
            return -1;
        // 使用大袋子数量
        int bigBag = apples / 8;
        // 大袋子装完剩下的的数量
        int rest = apples - bigBag * 8;
        while (bigBag >= 0){
            if (rest % 6 == 0)
                return bigBag + rest / 6;
            // 每次装不下，就让大袋子数量 -1，更新rest，然后下一轮计算 是否可以被 6 整除，如果可以就是使用袋子的数量
            bigBag--;
            rest = apples - bigBag * 8;
        }
        return -1;
    }


    /* 打表找规律直接淦 */
    public static int minBagAwesome(int apples){
        if (apples % 2 == 1 || apples <= 5 || apples == 10)
            return -1;
        if (apples == 6 || apples == 8 )
            return 1;
        if (apples == 12 || apples == 14 || apples == 16)
            return 2;
        return apples % 8 == 0 ? apples / 8 : apples / 8 + 1;
    }

}
