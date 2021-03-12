package com.szu.new_coder;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 11:01
 */

import java.util.Scanner;

public class MinCoinNums {

    static int min = Integer.MAX_VALUE;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cost = sc.nextInt();
        int rest = 1024-cost;
        int[] coins = new int[]{1,4,16,64};
        int[][] dp = new int[rest+1][coins.length+1];
        for(int i=0; i<=rest; i++){
            for(int j = 0; j<= coins.length; j++){
                dp[i][j] = -1;
            }
        }
        getMinCoin(rest, coins,  0);

        System.out.println(min);
    }
    public static void getMinCoin(int rest, int[] coins,  int curMin){
        if(rest == 0){
            if(curMin < min)
                min = curMin;
        }
        if(rest < 0)
            return;
        if(curMin > min)
            return;
        //if(dp[rest][index] != -1) return dp[rest][index];

        int ways = 0;
        for(int i = coins.length-1; i >=0 ; i--){
            getMinCoin(rest - coins[i], coins,  curMin+1);
        }
        //dp[rest][index] = ways;
    }

}
