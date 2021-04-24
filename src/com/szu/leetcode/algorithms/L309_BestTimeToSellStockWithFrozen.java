package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 带有冷冻期的买卖股票最大收益
 *
 * @Date 2021/4/24 16:03
 */

public class L309_BestTimeToSellStockWithFrozen {

    public int maxProfit(int[] prices) {
        return getMaxProfit(prices, 0, 0, true);

    }

    /*
    * TODO WRONG ANSWER！！！
    * */
    public int maxProfitDp(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int length = prices.length;
        int canBuy[] = new int[length];
        int notBuy[] = new int[length];

        notBuy[0] = -prices[0];
        for (int i = 1; i < length; i++) {

            // 昨天没有，今天仍然不持有
            int p1 = canBuy[i - 1];
            // 昨天持有 今天要卖出
            int p2 = notBuy[i - 1] + prices[i];
            if (p2 > p1 && i + 1 < length){

                canBuy[i + 1] = p2;
                canBuy[i ] = p2;
            }
            if (p1 > p2)
                canBuy[i] = p1;

            // 昨天持有，今天不卖
            int p3 = notBuy[i-1];
            // 昨天没有  今天买入
            int p4 = canBuy[i-1] - prices[i];
            notBuy[i] = Math.max(p3, p4);
        }
        return canBuy[length-1];
    }

    /* 暴力 */
    public int getMaxProfit(int[] stocks, int index, int cost, boolean canBuy) {
        if (index >= stocks.length) {
            return 0;
        }
        if (canBuy) {
            // 今天买
            int p1 = getMaxProfit(stocks, index + 1, stocks[index], false);
            // 今天不买
            int p2 = getMaxProfit(stocks, index + 1, cost, true);
            return Math.max(p1, p2);
        } else {
            // 今天卖
            int p3 = stocks[index] - cost + getMaxProfit(stocks, index + 2, 0, true);
            // 明天在说
            int p4 = getMaxProfit(stocks, index + 1, cost, false);
            return Math.max(p3, p4);
        }
    }

}
