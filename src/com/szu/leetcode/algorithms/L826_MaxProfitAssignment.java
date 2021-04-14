package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/13 11:26
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class L826_MaxProfitAssignment {


    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] peoples) {
        if(difficulty == null || difficulty.length == 0 || profit == null || profit.length == 0 || profit.length != difficulty.length)
            return 0;
        Job jobs[] = new Job[difficulty.length];
        for(int i = 0; i < difficulty.length; i++){
            jobs[i] = new Job(profit[i], difficulty[i]);
        }

        orderJob(jobs);
        /*
         * 键值对放入 treeMap，方便使用 ceilingKey floorKey 方法查询比当前元素小一点的key，进而获取到 value
         * */
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(jobs[0].hard, jobs[0].money);
        int curMaxMoney = jobs[0].money;
        /*
         * 过滤掉所有垃圾信息，让工作难度 和 工资 严格按照递增的排序，放入 treeMap
         * */
        for (int i = 1; i < jobs.length; i++) {
            if (jobs[i].hard == jobs[i-1].hard || jobs[i].money <= curMaxMoney){
                continue;
            }
            curMaxMoney = jobs[i].money;
            treeMap.put(jobs[i].hard, jobs[i].money);
        }
        /*
         * treeMap 中获取 floorKey 肯定是最优的 小于等于自己能力， 而且工资最高的一个
         * */
        int[] chooses = new int[peoples.length];
        for (int i = 0; i < peoples.length; i++) {
            if (treeMap.containsKey(peoples[i])){
                chooses[i] = treeMap.get(peoples[i]);
            }
            else{

                Integer floorKey = treeMap.floorKey(peoples[i]);
                chooses[i] = floorKey == null ? 0 : treeMap.get(floorKey);
            }
        }

        int ans = 0;
        for(int i = 0; i<peoples.length; i++)
            ans += chooses[i];
        return ans;
    }

    /*
     * 按照难度从小到大排序， 如果难度相等，按照工资从高到低排序
     * */
    public void orderJob(Job[] jobs){
        Arrays.sort(jobs, new Comparator<Job>(){
            public int compare(Job j1, Job j2){
                if (j1.hard == j2.hard)
                    return j2.money - j1.money;
                return j1.hard - j2.hard;
            }
        });
    }
}

class Job {
    int money;
    int hard;

    public Job(int money, int hard) {
        this.money = money;
        this.hard = hard;
    }


    public static void main(String[] args) {
        int[] diff = {66,1,28,73,53,35,45,60,100,44,59,94,27,88,7,18,83,18,72,63};
        int[] profit = {66,20,84,81,56,40,37,82,53,45,43,96,67,27,12,54,98,19,47,77};
        int[] worker = {61,33,68,38,63,45,1,10,53,23,66,70,14,51,94,18,28,78,100,16};
        L826_MaxProfitAssignment test = new L826_MaxProfitAssignment();
        test.maxProfitAssignment(diff, profit, worker);
    }
}
