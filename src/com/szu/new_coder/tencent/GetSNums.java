package com.szu.new_coder.tencent;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 目标和：给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
 * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面，返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * @Date 2021/5/10 15:16
 */

public class GetSNums {
    public static void main(String[] args) {

        int[] arr = {1,1,1,1,1,1};
        int S = 10;

        int ways = getWays(arr, 0, S, 0);
        System.out.println(ways);
    }



    public static int getWays(int[] arr, int index ,int s, int curVal) {

        if (index == arr.length){
            return curVal == s ? 1 : 0;
        }

        int ways = 0;

        if (curVal == s)
            ways += 1;

        ways += getWays(arr, index + 1, s, curVal + arr[index]);
        ways += getWays(arr, index + 1, s, curVal - arr[index]);

        return  ways;
    }


}
