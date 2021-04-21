package com.szu.new_coder.mei_tuan;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 多次交易股票，最大获利
 *
 * @Date 2021/4/18 13:01
 */

import java.util.Scanner;

public class Stocks {



        static int max;

        public static void main(String[] args){
            max = Integer.MIN_VALUE;
            Scanner sc = new Scanner(System.in);
            int len = sc.nextInt();
            int[] stocks = new int[len];
            for(int i = 0; i<len; i++){
                stocks[i] = sc.nextInt();
            }
            int k = sc.nextInt();
            getMaxProfit(stocks, k, 0, 0, true, 0);
            System.out.println(max);
        }

        public static void getMaxProfit(int[] stocks,int k, int index, int alreadyBuy, boolean canBuy, int curProfit){
            if(index >= stocks.length){
                max = Math.max(max, curProfit);
                return;
            }
            /* 如果今天能买 */
            if(canBuy){
                /*今天我就买了 */
                getMaxProfit(stocks, k, index +1,  alreadyBuy + stocks[index], false, curProfit);
                /* 明天再说 */
                getMaxProfit(stocks, k, index + 1, alreadyBuy , true, curProfit);

            }else{
                /* 现在手里有股票，不能新买入 */
                int sell = stocks[index] - alreadyBuy;
                /* 今天卖了 */
                getMaxProfit(stocks, k, index + k , 0, true, curProfit + sell);
                /* 明天再说 */
                getMaxProfit(stocks, k, index + 1, alreadyBuy, false, curProfit);
            }

        }


}
