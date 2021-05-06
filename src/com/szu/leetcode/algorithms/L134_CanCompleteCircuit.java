package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *          134. 加油站
             在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。

             你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。

             如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。

             https://leetcode-cn.com/problems/gas-station/

 * @Date 2021/2/18 23:44
 */

public class L134_CanCompleteCircuit {
    /*
    * O(N) + O(1)
    * 执行时间 1ms 是因为 求解了所有位置，
    * 有时间可以继续试试求出一个之后直接 return， 必定击败 双百！
    * */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        boolean[] can = awesome(gas, cost); // 每个位置是否可以转完一圈都求完了

        for(int i = 0; i < can.length; i++){
            if(can[i])
                return i;
        }
        return -1;
    }

    private static boolean[] awesome(int[] gas, int[] distance) {
        if (distance == null || gas == null || gas.length == 0 || gas.length != distance.length)
            return null;
        int length = distance.length;
        /* 获取纯值数组，直接用此数值判断下一站是否可达 */
        int init = getPureArrayAndInitIndex(gas, distance, length);
        /* 如果任何位置都是小于 0 的，那么不用求了，全部不能转一圈 */
        if (init == -1)
            return new boolean[length];
        boolean[] res = new boolean[length];

        int needToConnectHead = 0;
        int restGas = 0;
        int head = init, end = init + 1;
        end = end == length ? 0 : end;
        restGas = distance[head];

        GoHead:
        while (end != head) {
            /*
             * 剩余的油量够不够取到 end 位置的
             * */
            if (restGas + distance[end] < 0) {
                /* 不够，那么这个位置已经不能转一圈了 */
//                res[head] = false;
                /* 倒着找加油站，逆时针 */
                int nextHead = head - 1 < 0 ? length - 1 : head - 1;
                while (nextHead != end) {
                    /* 逆时针的加油站现在能不能连接上 出发点，他的油量够不够到 */
                    if (distance[nextHead] >= needToConnectHead) { // 够
                        head = nextHead;
                        restGas += distance[nextHead] - needToConnectHead; // 给你油，继续从刚才的位置往前冲吧
                        needToConnectHead = 0;
                        continue GoHead;
                    } else if (distance[nextHead] - needToConnectHead < 0) { // 不够

                        needToConnectHead -= distance[nextHead];
//                        res[nextHead] = false;
                    }
                    nextHead = nextHead - 1 < 0 ? length - 1 : nextHead - 1;
                }
                if (nextHead == end) // 往前找合适的加油站的时候，找到了现在的 end，也就是当前head冲不到的地方，那么已经任何加油站不可能冲过去了，都不可能转完一圈
                    return res;
            } else {
                restGas += distance[end];
                end++;
                end = end == length ? 0 : end;
            }
        }
        /*
         * 有一个加油站可以转完一圈
         * 现在我们只需要关注 need 就可以了
         * 只要倒着找加油站的时候，这个加油站能连接上之前的头
         * 那么这个加油站一定可以玩一圈
         * */
        res[head] = true;
        head = head - 1 < 0 ? length - 1 : head - 1;
        while (head != end) {
            if (distance[head] >= needToConnectHead) {
                needToConnectHead = 0;
                res[head] = true;
            } else
                needToConnectHead -= distance[head];
            head = head - 1 < 0 ? length - 1 : head - 1;
        }
        return res;
    }

    /* 获取纯值数组，直接放到distance中，而且获取一个初始值大于 0 的位置，从这个位置开始转圈 */
    private static int getPureArrayAndInitIndex(int[] gas, int[] distance, int length) {
        int init = -1;
        for (int i = 0; i < length; i++) {
            distance[i] = gas[i] - distance[i];
            if (distance[i] >= 0)
                init = i;
        }
        return init;
    }






    /*
    * =====================
    * =====================
    * 以下是垃圾代码，巨他妈垃圾
    * =====================
    * =====================
    * */
    public int canCompleteCircuitGarbage(int[] gas, int[] cost) {
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
