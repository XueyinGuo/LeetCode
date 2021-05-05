package com.szu.training02.class08;
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
 * 输入：stones = [3,2,4,1], K = 2
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

public class MinimumCostToMergeStones {

    public int mergeStonesViolence(int[] stones, int k) {
        if (stones == null || stones.length == 0)
            return 0;
        /*
        * 经过观察， (stones.length - 1) % (k - 1) != 0 的不可能合并
        * */
        if (k > stones.length || (stones.length - 1) % (k - 1) != 0)
            return -1;
        /*
        * 把 整个 stones 先分成三段
        * */
        return violence(0, stones.length-1, 1, k, stones);
    }

    public int violence(int start, int end, int wantedPart, int totalParts, int[] stones) {

        if (start == end)
            return wantedPart == 1 ? stones[start] : -1;

        if (wantedPart == 1){
            int next = violence(start, end, totalParts, totalParts, stones);
            if (next == -1)
                return -1;
            return
        }

        for (int i = start; i <= end; i++) {

        }

    }

}
