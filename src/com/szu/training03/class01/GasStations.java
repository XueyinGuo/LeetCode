package com.szu.training03.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 134. 加油站
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 求出所有的加油站是否可以环形行驶一周。
 *
 * @Date 2021/5/5 17:36
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.ArrayDeque;

public class GasStations {

    public static void main(String[] args) {
        int len = 30;
        int bound = 10;
        for (int i = 0; i < 5000000; i++) {
//            int[] gas = {3, 4, 4, 1, 4};
//            int[] distance1 = {4, 2, 2, 4, 4};
            int[] gas = LeetCodes.getRandomArray(len, bound);
            int[] distance1 = LeetCodes.getRandomArray(len, bound);
            int[] distance2 = LeetCodes.copyArray(distance1);
            int[] distance3 = LeetCodes.copyArray(distance1);
//            int[] ori = LeetCodes.copyArray(distance1);
            boolean[] res = violence(gas, distance1);
            boolean[] queueEdition = queueEdition(gas, distance2);
            boolean[] awesome = awesome(gas, distance3);
            for (int j = 0; j < res.length; j++) {
                if (awesome[j] != queueEdition[j] || awesome[j] != res[j ])
                    System.out.println("FUCK");
            }
        }
    }

    private static boolean[] awesome(int[] gas, int[] distance) {
        if (distance == null || gas == null || gas.length == 0 || gas.length != distance.length)
            return null;
        int length = distance.length;
        /* 获取纯值数组，直接用此数值判断下一站是否可达 */
        int init = getPureArrayAndInitIndex(gas, distance, length);
        /* 如果任何位置都是小于 0 的，那么不用求了，全部不能转一圈 */
        if (init == -1)
            return new boolean[length];
        boolean[] res = new boolean[length];

        int need = 0;
        int rest = 0;
        int head = init, end = init + 1;
        end = end == length ? 0 : end;
        rest = distance[head];

        GoHead:while (end != head) {
            /*
             * 剩余的油量够不够取到 end 位置的
             * */
            if (rest + distance[end] < 0) {
                /* 不够，那么这个位置已经不能转一圈了 */
//                res[head] = false;
                /* 倒着找加油站，逆时针 */
                int nextHead = head - 1 < 0 ? length - 1 : head - 1;
                while (nextHead != end) {
                    /* 逆时针的加油站现在能不能连接上 出发点，他的油量够不够到 */
                    if (distance[nextHead] >= need) { // 够
                        head = nextHead;
                        rest += distance[nextHead] - need; // 给你油，继续从刚才的位置往前冲吧
                        need = 0;
                        continue GoHead;
                    } else if (distance[nextHead] - need < 0) { // 不够
                        need -= distance[nextHead];
//                        res[nextHead] = false;
                    }
                    nextHead = nextHead - 1 < 0 ? length - 1 : nextHead - 1;
                }
                if (nextHead == end) // 往前找合适的加油站的时候，找到了现在的 end，也就是当前head冲不到的地方，那么已经任何加油站不可能冲过去了，都不可能转完一圈
                    return res;
            } else {
                rest += distance[end];
                end++;
                end = end == length ? 0 : end;
            }
        }
        /*
        * 有一个加油站可以转完一圈
        * 现在我们只需要关注 need 就可以了
        * 只要倒着找加油站的时候，这个加油站能连接上之前的头
        * 那么这个加油站一定可以玩一圈
        * */
        res[head] = true;
        head = head - 1 < 0 ? length - 1 : head - 1;
        while (head != end) {
            if (distance[head] >= need) {
                need = 0;
                res[head] = true;
            } else
                need -= distance[head];
            head = head - 1 < 0 ? length - 1 : head - 1;
        }
        return res;
    }

    /* 获取纯值数组，直接放到distance中，而且获取一个初始值大于 0 的位置，从这个位置开始转圈 */
    private static int getPureArrayAndInitIndex(int[] gas, int[] distance, int length) {
        int init = -1;
        for (int i = 0; i < length; i++) {
            distance[i] = gas[i] - distance[i];
            if (distance[i] >= 0)
                init = i;
        }
        return init;
    }

    /*
     * 优化版本，
     * */
    private static boolean[] queueEdition(int[] gas, int[] distance) {
        if (distance == null || gas == null || gas.length == 0 || gas.length != distance.length)
            return null;
        int length = distance.length;
        /* 获取纯值数组，直接用此数值判断下一站是否可达 */
        int init = getPureArrayAndInitIndex(gas, distance, length);
        /* 如果任何位置都是小于 0 的，那么不用求了，全部不能转一圈 */
        if (init == -1)
            return new boolean[length];
        boolean[] res = new boolean[length];
        /*
         * 生成辅助数组，辅助数组是 纯值数组的两倍长，足以满足转一圈的要求
         * 辅助数组前一半每个位置的值， 都是 纯值数组的累加前缀和
         *
         * 后一半 接着前缀和继续 从 纯值数组第一个数组累加起
         * */
        int[] help = new int[length * 2];
        int sum1 = 0, distanceIndex = 1;
        help[0] = distance[0];
        for (int i = 1; i < help.length; i++) {
            help[i] = help[i - 1] + distance[distanceIndex];
            distanceIndex = distanceIndex + 1 == length ? 0 : distanceIndex + 1;
        }
        /*
         * 鉴于生成的 help 数组的性质，
         * 窗口设置为 length 长度，就是转一圈的长度，
         * 窗口中的每个值 如何还原成之前的 纯值数前缀和呢？
         * 直接减去 前一个位置的数字就好了，
         * 如果这个前缀和中 有一个数字在减去前边的数字小于 0 了，那么这个窗口的起始位置就是不合法的
         *
         * 那么我们就需要找到窗口中的最小值！！！
         * 所以 滑动窗口中的最小值
         * */

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int windowSize = length;
        int l = 0, r = 0;
        while (r < help.length - 1) {
            while (!queue.isEmpty() && help[queue.peekLast()] >= help[r])
                queue.pollLast();
            queue.addLast(r);
            if (r - l + 1 == windowSize) {
                Integer peekIndex = queue.peek();
                res[l] = help[peekIndex] - (l - 1 < 0 ? 0 : help[l - 1]) < 0 ? false : true;
                if (peekIndex == l)
                    queue.poll();
                l++;
            }
            r++;
        }
        return res;
    }


    /*
     * =======
     * =======
     * 暴力求解
     * =======
     * =======
     * */
    private static boolean[] violence(int[] gas, int[] distance) {

        if (distance == null || gas == null || gas.length == 0 || gas.length != distance.length)
            return null;
        int length = distance.length;
        /* 获取纯值数组，直接用此数值判断下一站是否可达 */
        int init = getPureArrayAndInitIndex(gas, distance, length);
        /* 如果任何位置都是小于 0 的，那么不用求了，全部不能转一圈 */
        if (init == -1)
            return new boolean[length];

        boolean[] res = new boolean[length];
        int i = 0;
        /* n^2 的方式求解每个地方 */
        while (i < length) {
            int j = i + 1 == length ? 0 : i + 1;
            int sum = distance[i];
            while (j != i) {
                /* 一旦加上某个位置的值小于 0 了，那么 i 作为起点肯定不可转一圈 */
                if (sum < 0) {
                    res[i] = false;
                    break;
                }
                sum += distance[j];
                j = j + 1 == length ? 0 : j + 1;
                res[i] = true;
            }
            if (sum < 0) {
                res[i] = false;
            }
            i++;
        }
        return res;
    }

}
