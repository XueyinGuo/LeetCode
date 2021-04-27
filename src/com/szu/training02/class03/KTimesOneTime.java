package com.szu.training02.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个数组 arr, 已知除了一种数只出现一次之外，剩下的所有数字都出现了 K 次
 * 如何使用 O(1) 的空间找到这个数字
 *
 * @Date 2021/4/27 16:27
 */

import java.util.Random;

public class KTimesOneTime {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int k = random.nextInt(30);
            int len = k * 20 + 1;
            int index = 0;
            int[] arr = new int[len];
            while (index < len) {
                int randomNum = random.nextInt(1000);
                for (int dup = index; dup < index + 20; dup++) {
                    if (dup == len)
                        break;
                    arr[dup] = randomNum;
                }
                index += 20;
            }
            arr[len - 1] = random.nextInt(1000);
            int my = onceNum(arr, 20);
            int right = onceNumRight(arr, 20);
            if (my != right)
                System.out.println("FUCK");
        }


    }

    private static int onceNum(int[] arr, int k) {

        int[] help = new int[32];
        /*
         * 把每个数字转换成 K 进制，每一位都放入 help 数组
         *
         * 既然二进制的数字没有超过 32 位， 那么K进制也一定不超过 32位
         * */
        for (int i = 0; i < arr.length; i++) {
            int index = 0;
            int cur = arr[i];
            while (cur != 0) {
                int yuShu = cur % k;
                cur = cur / k;
                help[index++] += yuShu;
            }
            // 为了防止数组越界，每次做完之后都给每个位置取余数一次
            protectFromOutOfBound(help, k);
        }
        /*
        * 最后从一个 k 进制的数变回 十进制的 int
        *
        * 变化规则就是：每一位上的值 * (k ^ i)  累加起来
        * */
        int rest = 0;
        for (int i = 0; i < help.length; i++) {
            int pow = getQuickPow(k, i);
            rest += (help[i] * pow);
        }
        return rest;
    }

    /*
     * 快速幂
     * */
    public static int getQuickPow(int base, int power) {
        int t = base;
        int res = 1;
        while (power != 0) {
            if ((power & 1) != 0) {
                res = res * t;
            }
            t *= t;
            power >>= 1;
        }
        return res;
    }

    private static void protectFromOutOfBound(int[] help, int k) {
        for (int i = 0; i < 31; i++) {
            help[i] %= k;
        }
    }


    /*
     * 大神
     * */
    public static int onceNumRight(int[] arr, int k) {
        int[] eO = new int[32];
        for (int i = 0; i != arr.length; i++) {
            // 当前数是arr[i], 请把arr[i]变成K进制的形式，每一位累加到eO
            setExclusiveOr(eO, arr[i], k);
        }
        int res = getNumFromKSysNum(eO, k);
        return res;
    }

    public static void setExclusiveOr(int[] eO, int value, int k) {
        int[] curKSysNum = getKSysNumFromNum(value, k);
        for (int i = 0; i != eO.length; i++) {
            eO[i] = (eO[i] + curKSysNum[i]) % k;
        }
    }

    public static int[] getKSysNumFromNum(int value, int k) {
        int[] res = new int[32];
        int index = 0;
        while (value != 0) {
            res[index++] = value % k;
            value = value / k;
        }
        return res;
    }

    public static int getNumFromKSysNum(int[] eO, int k) {
        int res = 0;
        for (int i = eO.length - 1; i != -1; i--) {
            res = res * k + eO[i];
        }
        return res;


    }
}
