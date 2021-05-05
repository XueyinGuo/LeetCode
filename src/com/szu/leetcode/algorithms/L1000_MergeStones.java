package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个数组 arr，和一个正数 k
 * 这最终代表你可以把相邻的 k 个数字合成一个数
 * 每一次合并的最小代价是这些数字累加和
 * 最终你的目标是把所有的 arr 中的数字合成一个数，返回最小代价
 *
 *
   输入：stones = [3,2,4,1], K = 2
    输出：20
    解释：
    从 [3, 2, 4, 1] 开始。
    合并 [3, 2]，成本为 5，剩下 [5, 4, 1]。
    合并 [4, 1]，成本为 5，剩下 [5, 5]。
    合并 [5, 5]，成本为 10，剩下 [10]。
    总成本 20，这是可能的最小值。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/minimum-cost-to-merge-stones
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/5/4 9:54
 */

public class L1000_MergeStones {


    public int mergeStonesStupidMemory(int[] stones, int k) {

        if (stones == null || stones.length <= 1)
            return 0;
        /*
         * 经过观察， (stones.length - 1) % (k - 1) != 0 的不可能合并
         * */
        int length = stones.length;
        if (k > length || (length - 1) % (k - 1) != 0)
            return -1;

        int[] preSum = new int[length + 1];
        for (int i = 0; i < length; i++) {
            preSum[i + 1] = preSum[i] + stones[i];
        }

        /*
        * k 能取到值， 所以第三维度设置为 k+1
        * */
        int[][][] dp = new int[length][length][k + 1];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int t = 0; t <= k; t++) {
                    dp[i][j][t] = Integer.MAX_VALUE;
                }
            }
        }

        /*
         * 主函数调用的意义就是：
         * 整个数组上你给我搞成一份！！！
         * */
        return stupidMemory(0, length - 1, 1, k, stones, preSum, dp);
    }

    public int stupidMemory(int L, int R, int wantedPart, int k, int[] stones, int[] preSum, int[][][] dp) {

        if (L == R) {

            dp[L][R][wantedPart] = wantedPart == 1 ? 0 : -1;
            return dp[L][R][wantedPart]; // 如果范围上只剩一个数，而想要的份数也是 1 份，所以不需任何代价就已经合成一份了，反之不可能用一个数搞出别的份数
        }

        if (dp[L][R][wantedPart] != Integer.MAX_VALUE)
            return dp[L][R][wantedPart];

        /*
         * 如果想要的份数 为 1份
         * 那么在 start --- end 上 搞出 K 份来之后，就可以合并了嘛
         * 合并的代价是： 这个范围的累加和【所以我们需要前缀和数组】
         * */
        if (wantedPart == 1) {
            int next = stupidMemory(L, R, k, k, stones, preSum, dp);
            if (next == -1) {
                dp[L][R][wantedPart] = -1;
                return -1;
            }
            dp[L][R][wantedPart] = next + preSum[R + 1] - preSum[L];
            return dp[L][R][wantedPart];
        }

        /*
         * 如果这个范围想要的是 K 份
         * 那么开始枚举
         * */
        int min = Integer.MAX_VALUE;
        for (int i = L; i < R; /* i++ */ i += k - 1) { // i++ 当然也是对的，但是就会有一些无返回 -1 的尝试，比如 k = 3 的时候，i 和 i+1 上两个数搞出 一份，那么肯定不可能的，因为只有 k 个数才能一起合并
            /* 枚举 l...i 上作为第一份的时候 */
            int firstPart = stupidMemory(L, i, 1, k, stones, preSum, dp);
            /* 剩下的部分 搞出 wantedPart - 1 份 */
            int restParts = stupidMemory(i + 1, R, wantedPart - 1, k, stones, preSum, dp);
            /* 如果这样搞都是合法的，就开始计算最小值了 */
            if (firstPart != -1 && restParts != -1 && firstPart + restParts < min)
                min = firstPart + restParts;

        }
        dp[L][R][wantedPart] = min;
        return min;
    }


    public int mergeStonesViolence(int[] stones, int k) {
        if (stones == null || stones.length <= 1)
            return 0;
        /*
         * 经过观察， (stones.length - 1) % (k - 1) != 0 的不可能合并
         * */
        if (k > stones.length || (stones.length - 1) % (k - 1) != 0)
            return -1;

        int[] preSum = new int[stones.length + 1];
        for (int i = 0; i < stones.length; i++) {
            preSum[i + 1] = preSum[i] + stones[i];
        }

        /*
         * 主函数调用的意义就是：
         * 整个数组上你给我搞成一份！！！
         * */
        return violence(0, stones.length - 1, 1, k, stones, preSum);
    }

    public int violence(int L, int R, int wantedPart, int k, int[] stones, int[] preSum) {

        if (L == R)
            return wantedPart == 1 ? 0 : -1; // 如果范围上只剩一个数，而想要的份数也是 1 份，所以不需任何代价就已经合成一份了，反之不可能用一个数搞出别的份数
        /*
         * 如果想要的份数 为 1份
         * 那么在 start --- end 上 搞出 K 份来之后，就可以合并了嘛
         * 合并的代价是： 这个范围的累加和【所以我们需要前缀和数组】
         * */
        if (wantedPart == 1) {
            int next = violence(L, R, k, k, stones, preSum);
            if (next == -1)
                return -1;
            return next + preSum[R + 1] - preSum[L];
        }

        /*
         * 如果这个范围想要的是 K 份
         * 那么开始枚举
         * */
        int min = Integer.MAX_VALUE;
        for (int i = L; i < R; /* i++ */ i += k - 1) { // i++ 当然也是对的，但是就会有一些无返回 -1 的尝试，比如 k = 3 的时候，i 和 i+1 上两个数搞出 一份，那么肯定不可能的，因为只有 k 个数才能一起合并
            /* 枚举 l...i 上作为第一份的时候 */
            int firstPart = violence(L, i, 1, k, stones, preSum);
            /* 剩下的部分 搞出 wantedPart - 1 份 */
            int restParts = violence(i + 1, R, wantedPart - 1, k, stones, preSum);
            /* 如果这样搞都是合法的，就开始计算最小值了 */
            if (firstPart != -1 && restParts != -1 && firstPart + restParts < min)
                min = firstPart + restParts;

        }
        return min;
    }


    public static void main(String[] args) {
        L1000_MergeStones test = new L1000_MergeStones();
        int[] arr = {3, 2};
        int k = 2;
        test.mergeStonesViolence(arr, k);
    }
}
