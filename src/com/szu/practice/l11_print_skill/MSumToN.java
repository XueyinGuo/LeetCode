package com.szu.practice.l11_print_skill;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      判断一个数是否是从 1开始一直累加得到的数字
 *      6 = 1 + 2 + 3
 *      10 = 1 + 2 + 3 + 4
 *      5 = 2 + 3
 *      ......
 *
 * @Date 2021/3/26 18:14
 */

import java.util.Random;

public class MSumToN {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomInt = random.nextInt(1000000);
            boolean flag = isMSumToN(randomInt);
            boolean flag2 = isMSumToNAwesome(randomInt);
            if (flag != flag2)
                System.out.println("FUCK");
        }

    }

    private static boolean isMSumToN(int num) {

        nextStart: for (int i = 0; i < num; i++) {
            int n = 0;
            for (int j = i; j < num; j++) {
                n += j;
                if (n > num)
                    continue nextStart;
                if (n == num)
                    return true;
            }
        }
        return false;
    }

    /* 找到了规律，2的幂就是不合格的数 */
    private static boolean isMSumToNAwesome(int num) {
        return (num & num-1) != 0;
    }
}
