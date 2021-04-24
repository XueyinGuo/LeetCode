package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 无限制交易次数的交易股票最大利润
 *
 * 经典十二
 *
 *      121. 买卖股票的最佳时机
        122. 买卖股票的最佳时机 II
        123. 买卖股票的最佳时机 III
        188. 买卖股票的最佳时机 IV
        309. 最佳买卖股票时机含冷冻期
        714. 买卖股票的最佳时机含手续费
 *
 * @Date 2021/4/24 11:45
 */

public class L122_BestTimeToSellStockII {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        /* 进行超级短线交易，只要发现后一天的股票价格高于前一天， 那么我就前一天买，后一天卖 */
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            int duanXian = prices[i] - prices[i - 1];
            if (duanXian > 0)
                ans += duanXian;
        }
        return ans;
    }

}
