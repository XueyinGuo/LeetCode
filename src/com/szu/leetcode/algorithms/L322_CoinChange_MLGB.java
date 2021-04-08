package com.szu.leetcode.algorithms;

import java.util.Arrays;

public class L322_CoinChange_MLGB {

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        return coinChange(coins, amount, 0, coins.length - 1);
    }

    public int coinChange(int[] coins, int amount, int coinNum, int index){

        if(amount == 0)
            return coinNum;
        if(amount < 0 || index < 0)
            return -1;
        int res = -1;
        for(int i = index; i >= 0; i--){
            int zhang = amount / coins[i];
            while(zhang > 0){
                int rest = amount - zhang * coins[i];
                res = coinChange(coins, rest, coinNum + zhang, index - 1);
                if(res != -1)
                    return res;
                zhang--;
            }


        }
        return res;
    }

    public static void main(String[] args) {
        L322_CoinChange_MLGB coinChange322 = new L322_CoinChange_MLGB();
        System.out.println(coinChange322.coinChange(new int[]{186,419,83,408}, 6249));
    }
}
