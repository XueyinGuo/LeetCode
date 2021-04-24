package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 714. 买卖股票的最佳时机含手续费
    给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。

    你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

    返回获得利润的最大值。

    注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。

        121. 买卖股票的最佳时机
        122. 买卖股票的最佳时机 II
        123. 买卖股票的最佳时机 III
        188. 买卖股票的最佳时机 IV
        309. 最佳买卖股票时机含冷冻期
        714. 买卖股票的最佳时机含手续费
 *
 * @Date 2021/4/24 12:17
 */

public class L714_BestTimeToSellStockWithFee {


    public int maxProfitGreedy(int[] prices, int fee) {
        if (prices == null || prices.length == 0)
            return 0;
        /*
         * 我们用 buy 表示在最大化收益的前提下，如果我们手上拥有一支股票，
         * 那么它的最低买入价格是多少。在初始时，buy 的值为 prices[0] 加上手续费 fee。
         */
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            /*
            * 如果当前的股票价格 prices[i] 大于 buy，那么我们直接卖出股票并且获得 prices[i]−buy 的收益。
            * 但实际上，我们此时卖出股票可能并不是全局最优的（例如下一天股票价格继续上升），因此我们可以提供一个反悔操作，
            * 看成当前手上拥有一支买入价格为 prices[i] 的股票，将 buy 更新为 prices[i]。
            * 这样一来，如果下一天股票价格继续上升，我们会获得 prices[i+1]−prices[i] 的收益，
            * 加上这一天 prices[i]−buy 的收益，恰好就等于在这一天不进行任何操作，而在下一天卖出股票的收益；
            * */
            if (prices[i] > buy) {
                profit += prices[i] - buy;
                buy = prices[i];
            }
            /*
             * 如果当前的股票价格 prices[i] 加上手续费 fee 小于 buy，
             * 那么与其使用 buy 的价格购买股票，我们不如以 prices[i]+fee 的价格购买股票，
             * 因此我们将 buy 更新为 prices[i]+fee；
             * */
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            }


        }
        return profit;
    }


    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0)
            return 0;

        return maxProfit(prices, fee, 0, true, 0);
    }

    public int maxProfit(int[] prices, int fee, int index, boolean canBuy, int cost) {

        if (index == prices.length)
            return 0;

        if (canBuy) {
            // 今天我买， 卖出之前我就不能继续买了
            int p1 = maxProfit(prices, fee, index + 1, false, prices[index]);
            // 今天不买，明天再说
            int p2 = maxProfit(prices, fee, index + 1, true, cost);
            return Math.max(p2, p1);
        } else {
            // 之前买了，今天卖掉
            int p3 = prices[index] - cost - fee + maxProfit(prices, fee, index + 1, true, 0);
            // 今天不卖 明天再说
            int p4 = maxProfit(prices, fee, index + 1, false, cost);
            return Math.max(p3, p4);
        }
    }

    /*
     * 答案版的动态规划
     * */
    public int maxProfitDp2(int[] prices, int fee) {
        if (prices == null || prices.length == 0)
            return 0;
        int length = prices.length;
        int[] canBuyDp = new int[length];
        int[] notBuyDp = new int[length];
//        dp[length] = 0;
        canBuyDp[0] = 0;
        notBuyDp[0] = -prices[0];
        for (int i = 1; i < length; i++) {
            // 昨天持有，今天卖出
            /*       昨天花了多少钱买的 */
            int p1 = notBuyDp[i - 1] + prices[i] - fee;
            // 今天没买
            int p2 = canBuyDp[i - 1];
            canBuyDp[i] = Math.max(p2, p1);

            // 昨天也持有，今天也不卖
            int p3 = notBuyDp[i - 1];
            // 昨天不持有，今天买入
            int p4 = canBuyDp[i - 1] - prices[i];
            notBuyDp[i] = Math.max(p3, p4);
        }

        return canBuyDp[length - 1];
    }

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4};
        int fee = 2;
        L714_BestTimeToSellStockWithFee test = new L714_BestTimeToSellStockWithFee();
        test.maxProfitDp(prices, fee);
    }


    /*
     * TODO WRONG!!!!!!
     *  为什么根据递归改的动态规划就是有问题？
     *  */
    public int maxProfitDp(int[] prices, int fee) {
        if (prices == null || prices.length == 0)
            return 0;
        int length = prices.length;
        int[] canBuyDp = new int[length + 1];
        int[] notBuyDp = new int[length + 1];
//        dp[length] = 0;
        for (int i = length - 1; i >= 0; i--) {
            int p1 = notBuyDp[i + 1] - prices[i];
            int p2 = canBuyDp[i + 1];
            int p3 = 0;
            if (i + 1 < length)
                p3 = prices[i] - prices[i + 1] - fee + canBuyDp[i + 1];
            int p4 = notBuyDp[i + 1];

            canBuyDp[i] = Math.max(p1, p2);
            notBuyDp[i] = Math.max(p3, p4);
        }
        return canBuyDp[0];
    }

}
