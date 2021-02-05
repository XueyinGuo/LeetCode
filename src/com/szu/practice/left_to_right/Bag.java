package com.szu.practice.left_to_right;
/*
 * 背包问题
 *
 * 给定两个长度都为N的数组 weights values
 * weights[i]  values[i] 分别代表i号货物的体积和价值
 *
 * 给定一个 bag 表示一个袋子的容量，装的货物的总量不能超过这个容量
 * 返回可以装下的最大价值
 * */
// TODO 转成动态规划
public class Bag {



    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 ,5};
        int[] values = { 5, 6, 3, 19, 12, 4, 2 ,10};
        int bag = 16;
        int maxValue = findMaxValue(weights, values, bag, 0, 0);
        System.out.println(maxValue);
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


