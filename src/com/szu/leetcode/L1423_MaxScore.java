package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/20 11:28
 */

public class L1423_MaxScore {

    public int maxScoreViolence(int[] cardPoints, int k) {
        return getMaxScore(cardPoints, k, 0, cardPoints.length-1);
    }

    public int getMaxScore(int[] cardPoints, int k, int start, int end){
        if(k == 0)
            return 0;
        if(start == end) return cardPoints[start];
        int left = 0, right = 0;
        /* 每次可以选择要头上的牌还是尾巴的牌，返回最大值 */
        left = getMaxScore(cardPoints, k-1, start+1, end) + cardPoints[start];
        right = getMaxScore(cardPoints, k-1, start, end-1) + cardPoints[end];
        return Math.max(left, right);
    }

    /* 滑动窗口最优解 */
    public int maxScore(int[] cardPoints, int k) {

        int len = cardPoints.length;
        /*
        * 拿 k 张牌的话，只能从首尾拿，所以可以弄一个 len-k 大小的窗口，
        * 只要找到窗口和的最小值就可以用数组总和 - min窗口和计算出拿牌的最大值了
        * */
        int window = len - k;
        int minSum = 0;
        /*
        * 先计算数组开头窗口的数组的和，如果后续窗口中比这个值大，
        * 那么这个从尾巴上拿走所有的牌就是牌的最大值了
        * */
        for(int i = 0; i<window; i++){
            minSum += cardPoints[i];
        }
        int sum = minSum;
        /*
        * 窗口慢慢右滑，每次窗口和 + 进窗口的牌 - 出窗口的牌 = 当前窗口的总和
        * 然后赋值给 minSum
        * */
        for(int i = window; i < len; i++){
            sum = sum - cardPoints[i - window] + cardPoints[i];
            minSum = Math.min(minSum, sum);
        }
        int totalSum = 0;
        for(int i = 0; i<len; i++){
            totalSum += cardPoints[i];
        }
        return totalSum - minSum;
    }

    public static void main(String[] args) {
        int[] arr = {1,79,80,1,1,1,200,1};
        int i = new L1423_MaxScore().maxScore(arr, 3);
        int i1 = new L1423_MaxScore().maxScoreViolence(arr, 3);
        System.out.println();
    }

}
