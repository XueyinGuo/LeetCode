package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
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

public class L123_BestTimeToSellStockIII {

    public int maxProfit(int[] prices) {
        return maxProfit(2, prices);
    }

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        /*
         * 如果 k > n/2， 那么就是没限制，因为上升区间顶多就 n/2 个
         * */
        int length = prices.length;
        if (k >= length / 2) {
            return noLimit(prices);
        }
        /*
         * 创建 dp 表， 数组长度做行，k作为列
         * dp的含义就是：在 prices[r] 上做 c 次数的交易
         * 初始位置第一行第一列
         * 第一列上为 做 0 次交易，所以必定是 0
         * 第一行上在 prices[0] 上做够 c 次交易，必定是买完就卖，也是获利 0
         * 那么可能性分为：
         * （1） prices[r] 压根没参与交易，就在 prices[r - 1]上做足 c 次交易： dp[r][c] = dp[r-1][c]
         * （2） prices[r] 参与了交易，此时可能性就更加分裂：
         *                  ① prices[r] 上做够了 c - 1 次，在 prices[r] 上愣凑 第 c 次， 我就 prices[r] 上买完再卖
         *                                          dp[r][c] = dp[r-1][c-1] + prices[r] - prices[r-1]
         *
         *                  ② prices[r - 1] 上做够了 c - 1 次，prices[r - 1] 买的， prices[r] 卖
         *                                          dp[r][c] = dp[r-1][c-1] + prices[r] - prices[r-1]
         *
         *                  ③ prices[r - 2] 上做够了 c - 1 次，prices[r - 2] 买的， prices[r] 卖
         *                                          dp[r][c] = dp[r-2][c-1] + prices[r] - prices[r-2]
         *
         *                  ......
         * 两种可能性的所有情况中选取最大的那个填入 dp[r][c]
         *
         * 为了省掉 第二种可能性的 枚举行为：
         * 我们可以做公因式提取 ：
         *      dp[r][c] = dp[r-1][c-1] + prices[r] - prices[r-1]
         *      dp[r][c] = dp[r-1][c-1] + prices[r] - prices[r-1]
         *      dp[r][c] = dp[r-2][c-1] + prices[r] - prices[r-2]
         * */
        int[][] dp = new int[length][k + 1];
        int ans = 0;
        for (int c = 1; c <= k; c++) {
            // 每次循环开始位置 r 都是从 1 开始， r-1 = 0，所以初始 t 设置就为 dp[0][c - 1] - prices[0]
            int t = dp[0][c - 1] - prices[0];
            for (int r = 1; r < length; r++) {
                // 每次来到新的一行就开始抓取 有没有最新的 比较大的 t 出现，存入 t 变量
                int newT = dp[r][c - 1] - prices[r];
                if (newT > t)
                    t = newT;
                // 然后比较 t 与 【此时的 r 位置不参与交易的情况比较】
                int cur = t + prices[r];
                if (cur > dp[r - 1][c])
                    dp[r][c] = cur;
                else
                    dp[r][c] = dp[r - 1][c];
                // 每一步抓取最大的 值
                if (dp[r][c] > ans)
                    ans = dp[r][c];

            }
        }
        return ans;
    }

    public int noLimit(int[] prices) {
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
