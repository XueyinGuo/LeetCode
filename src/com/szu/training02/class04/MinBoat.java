package com.szu.training02.class04;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个数组arr，长度为 N 且每个值都是整数代表 N 个人的体重
 * 在给定一个整数limit。代表一个船的载重
 *
 * 以下是坐船规则：
 * 1.每艘船最多只能坐两人
 * 2.两个乘客的体重和 不能超过 limit
 * 返回如果同时让这 N 个人过河，最少需要几条船
 *
 * @Date 2021/4/29 15:16
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;

public class MinBoat {


    private static int minBoat(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
//        Arrays.sort(arr);
        if (arr[arr.length - 1] > limit) {
            return -1;
        }
        int l = -1;
        /*
         * l 为 最后一个 小于等于 限重一半的人的下标
         * */
        int half = limit / 2;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= half) {
                l = i;
                break;
            }
        }
        /*
         * r 为第一个 超过限重一半的人的下标
         * */
        int r = l + 1;
        int twoSkinShare = 0;
        int skinAndFatShare = 0;
        int fatExclusive = 0;
        int length = arr.length;
        while (l >= 0 && r < length) {
            /*
             * 如果此时 瘦 + 胖 的体重 大于了 限重
             * 那么瘦子们就要多一条 瘦子共享船
             * */
            if (arr[l] + arr[r] > limit) {
                twoSkinShare++;
                l--;
            } else {
                /*
                 * 胖子 + 瘦 不超重，
                 * 一直找到第一个超重的人为止
                 * */
                int rMove = 0; // 向右滑了几个人
                /*
                * 但是最多向右滑动 左边瘦子们 的人数
                *
                *                   瘦子 + 胖子体重不超过 limit      向右找最多不超过 还剩下的瘦子的人数
                * */
                while (r < length && arr[l] + arr[r] <= limit && rMove < l + 1) {
                    rMove++;
                    skinAndFatShare++; // 胖子 和瘦子的共享船
                    r++;
                }
                /* l 直接向右 滑动 右边找到的合适的人数 */
                if (rMove != 0) {
                    l -= rMove;
                }

            }
        }
        /* 在右滑的过程中，右边先越界了,但是瘦子还有人 */
        while (r >= length && l >= 0) {
            twoSkinShare++;
            l--;
        }
        /* 左边先没人了，只能胖子们独享船了 */
        while (r < length) {
            fatExclusive++;
            r++;
        }
                /* 两个瘦子可以共享一船，     胖子独享的船     瘦子和胖子共享的船 */
        return (twoSkinShare + 1) / 2 + fatExclusive + skinAndFatShare;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100000000; i++) {
            int[] arr = LeetCodes.getRandomArray(100, 100);
//            int[] arr = {5, 7, 8, 8, 8, 8, 9, 9, 10, 10};
            Arrays.sort(arr);
            int weight = 120;
            int my = minBoat(arr, weight);
            int right = minBoatRight(arr, weight);
            if (my != right)
                System.out.println("FUCK");
        }

    }


    /*
     * 大神
     * */
    // 请保证arr有序
    public static int minBoatRight(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // Arrays.sort(arr);
        if (arr[N - 1] > limit) {
            return -1;
        }
        int lessR = -1;
        // 所有的人体重都不超过limit，继续讨论,  <= limit / 2  最右的位置
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] <= (limit / 2)) {
                lessR = i;
                break;
            }
        }
        if (lessR == -1) {
            return N;
        }
        //  <= limit / 2
        int L = lessR;
        int R = lessR + 1;
        int noUsed = 0; // 画X的数量统计，画对号的量(加工出来的)
        while (L >= 0) {
            int solved = 0; // 此时的[L]，让R画过了几个数
            while (R < N && arr[L] + arr[R] <= limit) {
                R++;
                solved++;
            }
            // R来到又不达标的位置
            if (solved == 0) {
                noUsed++;
                L--;
            } else { // 此时的[L]，让R画过了solved（>0）个数
                L = Math.max(-1, L - solved);
            }
        }
        int all = lessR + 1;// 左半区总个数  <= limit /2 的区域
        int used = all - noUsed; // 画对号的量
        int moreUnsolved = (N - all) - used; // > limit/2 区中，没搞定的数量
        return used + ((noUsed + 1) >> 1) + moreUnsolved;
    }

}
