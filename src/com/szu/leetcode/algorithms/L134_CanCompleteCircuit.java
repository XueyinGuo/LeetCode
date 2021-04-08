package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *          TODO 效率过低，待优化
 *          134. 加油站
             在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。

             你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。

             如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。

             https://leetcode-cn.com/problems/gas-station/

 * @Date 2021/2/18 23:44
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L134_CanCompleteCircuit {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int res = -1;
        new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
        // 遍历数组，看看从每个位置出发，能不能到所有的位置
        for(int i = 0; i < gas.length; i++){
            // 开始出发，发车之前邮箱还是 0，
            // 从第 i 个加油站出发，
            // 现在还没走过任何一个加油站所以 len = 0
            res = canI(gas, cost, 0,  i, 0);
            if(res != -1){
                return i;
            }
        }
        return res;

    }

    /*
    * capacity 当前汽油有多少
    * index 来到了那个加油站
    * len 走了多少加油站，如果 len == gas.length 的时候就表示走了一圈了
    * */
    public int canI(int[] gas, int[] cost,int capacity, int index, int len){

        if(len == gas.length) return 1;
        // 准备发车了，开始加油
        capacity += gas[index];
        // 加上油之后看看 自己能不能走到下一个加油站
        if(capacity < cost[index]) return -1;
        // 我到了下一个加油站了，容量已经减下去了
        capacity -= cost[index];
        // 下一个加油站，发车之前邮箱还是 刚刚跑完的油量，
        // 从第 i + 1 个加油站出发，
        // 走过一个加油站所以 len = len + 1
        return canI( gas, cost, capacity, (index+1)%gas.length, len + 1 );
    }

}
