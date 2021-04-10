package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      517. 超级洗衣机
        假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。

        在每一步操作中，你可以选择任意 m （1 ≤ m ≤ n） 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。

        给定一个非负整数数组代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的最少的操作步数。
        如果不能使每台洗衣机中衣物的数量相等，则返回 -1。
 *
 * @Date 2021/4/10 13:37
 */

public class L517_PackingMachine {

    public static int findMinMoves(int[] machines) {
        if(machines == null || machines.length == 0)
            return 0;
        int sum = 0;
        int len = machines.length;

        for (int i = 0; i < len; i++) {
            sum += machines[i];

        }
        /*
         * 先看总和是否能被 长度 整除，如果整除都不能，直接返回 -1
         * */
        if (sum % len != 0)
            return -1;
        // 每个位置的数量， avg
        int avg = sum / len;
        int ans = 0;
        // i位置 左边现有的衣服数量
        int leftSum = 0;
        for (int i = 0; i < len; i++) {
            /*
             *  计算 左边的需要移动的衣服数量： leftRest < 0 代表需要搬进来衣服。
             *  > 0 代表需要搬出去衣服
             * 右边同理
             * */
            int leftRest = leftSum -  i * avg;
            int rightSum = sum - leftSum - machines[i];
            int rightRest = rightSum - (len - 1 - i ) * avg;
            /*
             *  如果i位置 两边的数量都小于 0
             *  则都需要从 i 位置 搬出去
             *  然而每次只能向一个方向搬出一个，所以都搬出去的需要两边需要的数量的绝对值相加
             *
             * else
             *      只需要i位置 单侧移动到 包括i 位置在内的另一侧
             *      故只需要计算 单侧的绝对值最大值即可
             * */
            if (leftRest < 0 && rightRest < 0){
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            }else
                ans = Math.max(ans, Math.max( Math.abs(leftRest) , Math.abs(rightRest) ));

            leftSum += machines[i];
        }
        return ans;
    }

}
