package com.szu.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

// TODO 未完成
public class CoinChange_322 {
    List<Integer> minList ;


    public int coinChange(int[] coins, int amount) {
        minList = new ArrayList<>(1);
        minList.add(0, Integer.MAX_VALUE);
        doCoinChange(coins, amount, 0, 0);
        Integer min = minList.get(0);
        if (min == Integer.MAX_VALUE){
            return -1;
        }
        return min;
    }


    public int doCoinChange(int[] coins, int rest, int index, int coinNum) {

        if (rest < 0){
            return -1;
        }
        if (index == coins.length){
            return rest == 0 ? 1 : 0;
        }
        int valid = 0;
        Integer min = minList.get(0);
        for (int zhang = 1; coins[index] * zhang <= rest; zhang++) {
            valid = doCoinChange(coins, rest - coins[index] * zhang, index+1, coinNum + 1);
            if (valid == 1){
                min = coinNum + 1 < min ? coinNum + 1 : min;
            }
        }
        minList.set(0, min);
        return 0;
    }

    public static void main(String[] args) {
        CoinChange_322 coinChange322 = new CoinChange_322();
        System.out.println(coinChange322.coinChange(new int[]{1, 2, 5}, 25));
    }
}
