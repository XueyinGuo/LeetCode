package com.szu.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

// TODO 未完成
public class CoinChange_322 {
    List<Integer> list = new ArrayList<Integer>();

    public int coinChange(int[] coins, int amount) {
        int min = Integer.MAX_VALUE;
        doCoinChange(coins, amount, min, 0);

//        if (min == Integer.MAX_VALUE){
//            return -1;
//        }
        return list.get(0);
    }


    public int doCoinChange(int[] coins, int rest, int min, int coinNums) {
        // 如果已经剩余金额小于0，则当前方案无效
        if (rest < 0){
            return -1;
        }
        if (rest == 0){
            return 1;
        }

        for (int coin: coins) {
            int valid = doCoinChange(coins, rest - coin, min, coinNums + 1);
            if (valid != -1){
                min = coinNums + 1 < min ? coinNums + 1 : min;
                if (!list.isEmpty()){
                    list.remove(0);
                }
                list.add(min);
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        CoinChange_322 coinChange322 = new CoinChange_322();
        System.out.println(coinChange322.coinChange(new int[]{1, 2, 5}, 25));
    }
}
