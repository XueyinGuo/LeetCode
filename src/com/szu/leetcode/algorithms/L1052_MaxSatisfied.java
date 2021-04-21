package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 1052. 爱生气的书店老板
    今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。

    在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。

    书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。

    请你返回这一天营业下来，最多有多少客户能够感到满意的数量。

     示例：

        输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
        输出：16
        解释：
        书店老板在最后 3 分钟保持冷静。
        感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/grumpy-bookstore-owner
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 *
 * @Date 2021/4/21 10:00
 */

public class L1052_MaxSatisfied {

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int length = customers.length;
        /* 先计算不生气的时候能一共接多少客 */
        int notAngryCustomers = 0;
        for (int i = 0; i < length; i++) {
            if(grumpy[i] == 0)
                notAngryCustomers += customers[i];

        }
        /* 然后使用滑动窗口，窗口大小维持 X 大小，专门计算 grumpy == 1 的时候被赶走的客人数 */
        /* 找到最大的客人数 */
        int l = 0;
        int r = 0;
        int maxEscape = 0;
        int escape = 0;
        while ( r < length ){

            if (grumpy[r] == 1){
                escape += customers[r];
            }
            /* 当窗口够了 X 大小的时候才收集答案 */
            if (r >= X - 1){
//                看当前被赶走人数是否大于 之前收集的答案
                if (maxEscape < escape)
                    maxEscape = escape;
//                维持窗口的内的值， 注意左侧只有是在生气的时候才减去
                if (grumpy[l] == 1)
                    escape -= customers[l];
//                如果窗口左侧不生气，就不减了，但是窗口还是需要右移的
                l++;
            }

            r++;
        }

        return notAngryCustomers + maxEscape;
    }


    public static void main(String[] args) {
        L1052_MaxSatisfied test = new L1052_MaxSatisfied();
        int co[] = {1,0,1,2,1,1,7,5,2,7,4,8,9,2};
        int g[] = {0,0,0,1,1,0,0,1,0,1,1,0,0,0};
        int x = 3;
        int i = test.maxSatisfied(co, g, x);
        System.out.println(i);
    }
}
