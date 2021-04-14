package com.szu.training.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  每种工作都有难度和报酬，规定如下
 *  class Job {
 *      int money;
 *      int hard;
 * }
 * 给定一个Job类型的数组，表示所有岗位，每个岗位都可以提供无数份工作
 * 选工作的标准就是难度不超过自身能力的情况下工资最高的，
 * 给定一个int数组，表示所有求职者的能力，
 * 返回int数组，表示每个人按照标准选择的最高报酬
 *
 * @Date 2021/4/13 9:11
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class ChooseWork {



    public int[] chooseWork(Job[] jobs, int[] peoples){
        /*
         * 按照难度从小到大排序， 如果难度相等，按照工资从高到低排序
         * */
        orderJob(jobs);
        /*
         * 键值对放入 treeMap，方便使用 ceilingKey floorKey 方法查询比当前元素小一点的key，进而获取到 value
         * */
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(jobs[0].hard, jobs[0].money);
        int curMaxMoney = 0;
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
        return chooses;
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


    public static void main(String[] args) {
        Job jobs[] = { new Job(1,1),
                        new Job(2,2),
                        new Job(2, 3),
                        new Job(3, 4),
                        new Job(3, 2)};
        int[] peoples = {4, 2, 1, 0, 5};
        ChooseWork chooseWork = new ChooseWork();
        int[] ints = chooseWork.chooseWork(jobs, peoples);
        System.out.println();
    }



}

class Job {
    int money;
    int hard;

    public Job(int money, int hard) {
        this.money = money;
        this.hard = hard;
    }
}
