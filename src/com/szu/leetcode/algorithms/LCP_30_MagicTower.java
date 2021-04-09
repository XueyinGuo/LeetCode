package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      LCP 30. 魔塔游戏
 *      小扣当前位于魔塔游戏第一层，共有 N 个房间，编号为 0 ~ N-1。每个房间的补血道具/怪物对于血量影响记于数组 nums，
 *      其中正数表示道具补血数值，即血量增加对应数值；负数表示怪物造成伤害值，即血量减少对应数值；0 表示房间对血量无影响。
 *
 *      小扣初始血量为 1，且无上限。假定小扣原计划按房间编号升序访问所有房间补血/打怪，为保证血量始终为正值，
 *      小扣需对房间访问顺序进行调整，每次仅能将一个怪物房间（负数的房间）调整至访问顺序末尾。请返回小扣最少需要调整几次，
 *      才能顺利访问所有房间。若调整顺序也无法访问完全部房间，请返回 -1。
 *
 * @Date 2021/4/10 0:13
 */

import java.util.PriorityQueue;

public class LCP_30_MagicTower {

    public int magicTower(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        // 先遍历一遍数组，如果数组和 都是 <= 0 的，则直接返回 -1
        if(sum + 1 <= 0)
            return -1;
        /*
        * 贪心策略：每次 剩余血量 <= 0 的时候，都把之前耗费血量最高的房间放到最后一个访问
        * 所以每当我们遇到一个 负数 房间的时候，就把这个数字放到优先级队列
        * 如果剩余血量不达标了，我们就从堆顶弹出（小根堆，堆顶为最小的元素，就是 耗费血量最高的房间）
        * 并且当前血量加回 弹出的 消耗的血量，
        * 弹出一次就意味着 移动了一次
        * */
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        long restBlood = 1;
        int moveTimes = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= 0)
                queue.add(nums[i]);

            restBlood += nums[i];

            if(restBlood <= 0){
                restBlood -= queue.poll();
                moveTimes++;
            }
        }
        return moveTimes;
    }

}
