package com.szu.training01.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个整数 K
 * 给定一个 由字符串组成的数组 strs
 *
 * 返回 词频最大的前K个字符串
 *
 * @Date 2021/4/14 13:39
 */

import java.util.*;

public class TopKTimes {
    /*
    * 解题思路：
    * 先用map记录所有的词频出现的次数
    * 新建一个小根堆，小根堆的元素数量最多保持 topK个
    * 然后用迭代器遍历map，堆不满时直接加入
    * 如果堆满了，弹出堆顶元素（此时堆顶是出现频率的最多的中最小的）
    * 加进 当前元素
    * */
    public static List<String> getTopKAndRank(String[] strs, int topK) {
        if (strs == null || strs.length == 0)
            return new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            Integer times = map.get(strs[i]);
            if (times == null)
                map.put(strs[i], 1);
            else
                map.put(strs[i], times + 1);
        }

        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        PriorityQueue<TopNode> queue = new PriorityQueue<>( new Comparator<TopNode>(){
            public int compare(TopNode node1, TopNode node2){
                return node1.times - node2.times;
            }
        });
        int size = 0;

        List<String> res = new ArrayList<>();

        while (it.hasNext()){
            Map.Entry<String, Integer> next = it.next();
            if (size != topK){
                queue.add( new TopNode(next.getKey(), next.getValue() ));
                size++;
            }else{
                int nextTimes = next.getValue();
                if ( nextTimes > queue.peek().times ){
                    queue.poll();
                    queue.add( new TopNode(next.getKey(), nextTimes));
                }
            }

        }

        while (! queue.isEmpty())
            res.add(queue.poll().word);
        return res;
    }


    public static void main(String[] args) {
        String[] strs = { "A", "B", "A", "C","C","C","C","C","C", "C", "A", "C", "B", "B", "K", "K", "K", "K", "K", "K", "K" };
        List<String> topKAndRank = getTopKAndRank(strs, 2);
        for (String s:topKAndRank             ) {
            System.out.println(s);
        }
    }
}

class TopNode{

    String word;
    int times;

    public TopNode(String word, int times) {
        this.word = word;
        this.times = times;
    }
}
