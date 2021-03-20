package com.szu.practice.dp;
/*
 * 背包问题
 *
 * 给定两个长度都为N的数组 weights values
 * weights[i]  values[i] 分别代表i号货物的体积和价值
 *
 * 给定一个 bag 表示一个袋子的容量，装的货物的总量不能超过这个容量
 * 返回可以装下的最大价值
 * */
// TODO 动态规划有BUG
public class Bag {



    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 7,  8  , 4};
        int[] values = { 5, 6, 3, 19, 16, 12,  6};
        int bag = 17;
        int maxValue = findMaxValue(weights, values, bag, 0, 0);
        int maxValue1 = findMaxValueUseRest(weights, values, bag,0 , 0);
        int maxValue3 = findMaxValueUseDp(weights, values, bag);

        int maxValue2 = useDp(weights, values, bag);
        System.out.println(maxValue);
        System.out.println(maxValue1);
        System.out.println(maxValue2);
        System.out.println(maxValue3);
    }

    /*
     * 动态规划做法
     * rest : 剩余空间大小
     * */
    private static int useDp(int[] weights, int[] values, int bag) {
        int index = weights.length;
        int dp[][] = new int[bag+1][index+1];
        for (int i = index-1; i >= 0 ; i--) {
            for (int rest = 0; rest <= bag ; rest++) {

                int no = dp[rest][i+1];
                int yes = -1;
                if (rest - weights[i] >= 0){
                    yes = values[i] + dp[rest - weights[i]][i + 1];
                }
                dp[rest][i] = Math.max(yes, no);
            }
        }
        return dp[bag][0];
    }


    /*
     * index : 当前来到哪个货物做决策
     * rest : 剩余空间大小
     * */
    private static int findMaxValueUseRest(int[] weights, int[] values, int rest, int curValue, int index) {
        if (rest < 0){
            return -1;
        }
        if (index == values.length){
            return curValue;
        }
        int yes = findMaxValueUseRest(weights, values, rest - weights[index], curValue + values[index] , index + 1);
        int no = findMaxValueUseRest(weights, values, rest, curValue,index + 1);
        return yes != -1 ? Math.max(yes, no) : no;
    }

    public static int findMaxValueUseDp(int[] w, int[] v, int bag){
        if (w.length != v.length || bag <= 0){
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N+1][bag+1];

        for (int n = N-1; n >= 0 ; n--) {
            for (int r = 0; r <= bag; r++) {
                int no = dp[n+1][bag];
                int yes = -1;
                if (r - w[n] >= 0){
                    yes = dp[n+1][r - w[n]] + v[n];
                }
                dp[n][r] = Math.max(yes, no);
            }
        }
        return dp[0][bag];
    }

    /*
    * curWeight : 当前已经装的重量
    * index : 当前来到哪个货物做决策
    * bag : 初始的总容量
    * */
    private static int findMaxValue(int[] weights, int[] values, int bag, int curWeight, int index) {
        // 当前重量 大于 总容量 ， 无效方案，返回 -1
        if (curWeight > bag){
            return -1;
        }
        // 如果当前还有剩余空间， 但是没有其余货物了，则直接返回0
        if (index == weights.length){
            return 0;
        }
        // 不要当前货物后续所产生的的价值
        int no = findMaxValue(weights, values, bag, curWeight, index + 1);
        // 要了当前货物后续所产生的的价值
        int yes = findMaxValue(weights, values, bag, curWeight + weights[index], index + 1);
        int yesValue = -1;
        // 因为要了当前货物之后，有可能会超重，超重base case则返回-1
        // 如果要了当前货物之后是有效的决策（返回值不是 -1），则当前所要的货物的价值，加上之后的价值，
        if (yes != -1){
            yesValue = yes + values[index];
        }
        // 返回 两种情况的最大值
        return Math.max(no, yesValue);
    }
}


