package com.szu.practice.range;
/*
* 给定一个整数数组，代表数值不同的纸牌排成一条线
* 玩家 A， 玩家 B 依次拿走纸牌
* 规定 A先拿， B后拿
* 每个玩家每次只能拿走最左或者最右的纸牌
* 玩家A 和 玩家 B都绝顶聪明。请返回最后获胜者的分数
* */
public class CardGame {
    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 ,100,6, 5,7,9,15,64,7};
        int score = getMaxScore(arr);
        System.out.println(score);
    }

    private static int getMaxScore(int[] arr) {

        int A = firstGet(arr, 0, arr.length - 1);
        int B = secondGet(arr, 0, arr.length - 1);
        return Math.max(A, B);
    }
    /*
    * 先手拿牌函数
    * */
    // arr[L..R]，先手获得的最好分数返回
    private static int firstGet(int[] arr, int begin, int end) {
        if (begin == end){
            return arr[begin];
        }
        int getLeft = arr[begin] + secondGet(arr, begin + 1, end); // 我自己拿走 begin 位置的牌 + 对手拿走对他最有益的牌
        int getRight = arr[end] + secondGet(arr, begin, end - 1);   // 我自己拿走 end 位置的牌 + 对手拿走对他最有益的牌
        return Math.max(getLeft, getRight);
    }
    /*
    * 后手拿牌函数
    * */
    // arr[L..R]，后手获得的最好分数返回
    private static int secondGet(int[] arr, int begin, int end) {
        /*
        * 后手拿牌的时候，牌堆里只剩一张牌了，后手就获取不到牌了，返回 0
        * */
        if (begin == end){
            return 0;
        }
        int getLeft = firstGet(arr, begin+1, end); // 对手拿走了 begin 位置的数
        int getRight = firstGet(arr, begin, end - 1);// 对手拿走了 end 位置的数
        return Math.min(getLeft, getRight); //对手一定会给你留下最差的分数
    }


}
