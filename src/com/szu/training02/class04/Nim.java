package com.szu.training02.class04;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * Nim 博弈问题
 * 给定一个非负数组，每一个值代表位置上有几个铜板。
 * a 和 b 玩游戏， a先手，b后手
 * 轮到某个人的时候，只能在一个位置上拿任意数量的铜板
 * 但是不能不拿。谁先把铜板拿完谁就赢了。
 * 假设两人极度聪明，请返回获胜者的名字
 *
 * @Date 2021/4/29 15:11
 */

public class Nim {

    // 保证arr是正数数组
    public static void printWinner(int[] arr) {

        /*
        * 只要是一开始的数组 的异或和 为 0
        * 那么必定是后手赢，因为两人决定聪明，
        * 每次 异或和 不为 0，我都可以让异或和 为 0
        * 而另一个只能 面对 异或和为 0 ，再通过拿走一部分让异或和 继续不为 0
        *
        * 那么一开始面对 异或和不为 0 的先手必胜
        * 一开始面对 异或和 为 0 的先手必输
        * */
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        if (eor == 0)
            System.out.println("后手赢");
        else
            System.out.println("新手赢");

    }

}
