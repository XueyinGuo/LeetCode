package com.szu.leetcode.list.array;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/18 23:44
 */

public class CanCompleteCircuit_134 {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int res = -1;
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
