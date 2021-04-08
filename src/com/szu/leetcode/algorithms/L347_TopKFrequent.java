package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *     347. 前 K 个高频元素
 *      给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 *       https://leetcode-cn.com/problems/top-k-frequent-elements/
 *
 * @Date 2021/3/27 19:26
 */

import java.util.*;

public class L347_TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        // 先使用 map 统计所有元素出现的频率
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){

            Integer frequnet = map.get(nums[i]);
            if(frequnet != null)
                map.put(nums[i], frequnet+1);
            else
                map.put(nums[i], 0);

        }
        // 如果遍历完 map size 为0，则数组为空，没任何元素
        if(map.size() == 0)
            return new int[]{};
        /*
        * 优先级队列，存储的是Map中的所有记录，
        * 排序规则为 出现频率最高的在上边
        *
        * map 中所有的 Entry 加入到 优先级队列中
        * */
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue(new Comparator<Map.Entry<Integer, Integer>>(){
            @Override
            public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2){
                return entry2.getValue() - entry1.getValue();
            }
        });
        // 队列中前 K 个弹出加入结果数组
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while(it.hasNext())
            queue.add(it.next());
        int[] res = new int[k];
        int i = 0;
        while(!queue.isEmpty() && k > 0){
            res[i++] = queue.poll().getKey();
            k--;
        }
        return res;
    }
}
