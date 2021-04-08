package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      121. 买卖股票的最佳时机

        给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

        你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

        返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * @Date 2021/3/1 23:24
 */


/*
*   我们只要用一个变量记录一个历史最低价格 minCost，我们就可以假设自己的股票是在那天买的。
*   那么我们在第 i 天卖出股票能得到的利润就是 prices[i] - minCost。反正买完才能卖
*
*   因此，我们只需要遍历价格数组一遍，记录历史最低点，然后在每一天考虑这么一个问题：
*   如果我是在历史最低点买进的，那么我今天卖出能赚多少钱？当考虑完所有天数之时，我们就得到了最好的答案。
*
* */
public class L121_MaxProfitOfStock {

    public int maxProfit(int[] prices) {
        int minCost = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i = 0;i<prices.length;i++){
            if(prices[i] < minCost) minCost = prices[i];
            int profit = prices[i] - minCost;
            if(profit > maxProfit) maxProfit = profit;
        }
        return maxProfit;
    }

}
