package com.szu.leetcode.dp;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/7 22:43
 */
/*
* 121. 买卖股票的最佳时机
* 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
*
* 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
*
* 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
* 
* 来源：力扣（LeetCode）
* 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
* 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
public class StockSell_121 {


    public int maxProfit(int[] prices) {
        int max = 0;
        int cur = 0;
        for (int i = 0; i < prices.length; i++) {
            // 递归计算第 i 天买入股票，至少i+1天之后卖出的最大收益
            // prices[i] = 花费本金， i 为第几天买的
            cur = sell(prices, prices[i],i+1);
            max = max < cur ? cur : max;
        }
        return max;
    }

    /*
    * cost, 买入价格
    * sellDay,至少为买入的下一天
    * */
    private int sell(int[] prices, int cost, int sellDay) {

        if (sellDay == prices.length){
            return 0;
        }
        //选择当天卖出的收益
        int profit = prices[sellDay] - cost;
        //下一天卖出的最大收益
        int nextProfit = sell(prices, cost, sellDay + 1);
        // 返回当天卖出和下一天卖出的最大收益
        return Math.max(profit, nextProfit);
    }

    // TODO
    private int maxProfitWithDp(int[] prices){
        int length = prices.length;
        int dp[] = new int[length + 1];
        dp[length] = 0;
        int max = 0;
        for (int i = 0; i < length - 1; i++) {
            dp[i] = Math.max(prices[i+1]-prices[i], dp[i+2]);
            max = max < dp[i] ? dp[i] : max;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new StockSell_121().maxProfit(new int[]{7,1,5,3,6}));
        System.out.println(new StockSell_121().maxProfitWithDp(new int[]{7,1,5,3,6}));
    }
}
