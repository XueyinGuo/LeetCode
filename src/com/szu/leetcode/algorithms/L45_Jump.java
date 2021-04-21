package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/19 22:06
 */

public class L45_Jump {
    /*
    * [2,3,1,1,4]
    * 一开始 jump 为 0
    * 跳一次 来到 index = 0的位置， arr[0] = 2,代表这一跳最远来到 index = 2 的位置， 把 2 赋值给 next
    * 每次来到的位置，都看看自己是否已经越过了 这一跳 最远的位置 ，在这一跳里 收集下一跳能到的最远位置
    * */
    public int jump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int jump = 0;
        int cur = 0;
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                jump++;
                cur = next;
            }
            /*  */
            if(i + arr[i] > next)
                next = i + arr[i];
        }
        return jump;
    }

}
