package com.szu.training02.class02;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 交易一次股票所获得的最大利润
 *
 * 经典十二
 *
 * @Date 2021/4/24 11:45
 */

public class BestTimeToSellStockI {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        /* 第一个位置就是当前的最小值 */
        int min = prices[0];
        int maxProfit = 0;
        /* 遍历数组从第 2 个位置开始，每次都去抓取是否是最小值，每次都去抓取当前的最大利润 */
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min)
                min = prices[i];
            int curProfit = prices[i] - min;
            if (curProfit > maxProfit)
                maxProfit = curProfit;
        }
        return maxProfit;
    }

}
