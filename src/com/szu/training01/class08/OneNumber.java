package com.szu.training01.class08;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个正数 N ，表示你在纸上写下 1 ~ N 所有的数字，
 * 返回在书写过程中，一共写下了多少个 1
 *
 * 经典十一，最后
 * @Date 2021/4/23 18:59
 */

import java.util.Random;

public class OneNumber {

    public int countDigitOne(int num) {
        if (num < 1)
            return 0;
        if (num < 10)
            return 1;

        int weiShu = getWeiShu(num);
        /* 这是一个与 num 位数等长的 10 的 次方 */
        int base = getWeiShuPower(weiShu - 1);
        // 计算最高位到底是谁
        int firstNum = num / base;
        /* 计算第一位上是1 的时候，有多少个数 */
        int firstOneNum = 0;
        /*
         * 如果 第一位 是 1
         * 比如 1899 这个数， 千位为 1 的时候 就有 900 个
         * 计算方式如下： 查看 900 --- 1899 上有多少个【剩下数字的交给递归】：
         * 就是 1899 % 1000 = 899， 然后 +1 = 900
         *
         * 如果是 2899 呢？
         * 就是 看 900 --- 2899 有多少个
         * 千位为 1 的就是 1000 --- 1999 ， 共 1000 个， 也就是 base了
         * */
        if (firstNum == 1)
            firstOneNum = num % base + 1;
        else
            firstOneNum = base;

        /*
         * 剩余的就是计算 百位，十位，和个位上
         * 如果最高位 是 1：   还比如 1899
         *
         * 继续只关注 900 --- 1899 这个范围上【剩下的交给递归】
         * 百位 【1】100 -- 【1】199， 千位必须是 1， 否则 就是递归应该处理的范围了
         * 十位 【1】010 -- 【1】019， 【1】110 -- 【1】119， 【1】210 -- 【1】219 ........ 【0】910 -- 【0】919
         *                                                                            【这里需要换成 0 了， 否则就超出本来应该求的范围了，而且没落入递归处理的范围】
         * 个位 也是一样， 所以 这样算下来，除了 百位 十位 个位 加起来的 个数为   10^2 * 3 个
         * 抽象化 一下就是  10的(weiShu - 2 次方) * (weiShu - 1) * firstNum， 就是剩下 1 的个数
         * */
        int otherOneNum = getWeiShuPower(weiShu - 2) * (weiShu - 1) * firstNum;

        return firstOneNum + otherOneNum + countDigitOne(num % base);
    }

    private int getWeiShuPower(int weiShu) {
        return ((int) Math.pow(10, weiShu));
    }

    private int getWeiShu(int num) {
        int weiShu = 0;
        while (num != 0) {
            num /= 10;
            weiShu++;
        }
        return weiShu;
    }

    /*
     * 终极暴力解法
     * */
    public int violence(int num) {
        int ans = 0;
        for (int i = 1; i <= num; i++) {
            ans += get1NumViolence(i);
        }
        return ans;
    }

    private int get1NumViolence(int num) {
        int ans = 0;
        while (num != 0) {
            if (num % 10 == 1)
                ans++;
            num /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        Random random = new Random();
        OneNumber test = new OneNumber();
        long all1 = 0;
        long all2 = 0;
        for (int i = 0; i < 1000; i++) {
            int num = random.nextInt(50000000);
            long start1 = System.currentTimeMillis();
            int violence = test.violence(num);
            long end1 = System.currentTimeMillis();
            all1 += (end1 - start1);

            long start2 = System.currentTimeMillis();
            int awesome = test.countDigitOne(num);
            long end2 = System.currentTimeMillis();
            all2 += end2 - start2;
            if (violence != awesome)
                System.out.println("FUCK");
        }
        System.out.println("violence : " + all1);
        System.out.println("awesome : " + all2);

    }
}
