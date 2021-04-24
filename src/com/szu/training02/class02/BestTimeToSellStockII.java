package com.szu.training02.class02;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 无限制交易次数的交易股票最大利润
 *
 * 经典十二
 *
 * @Date 2021/4/24 11:45
 */

public class BestTimeToSellStockII {

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
