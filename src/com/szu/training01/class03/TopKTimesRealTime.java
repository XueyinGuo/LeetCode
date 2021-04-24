package com.szu.training01.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      TopK 词频 实时获取
 *      一边往里加 一边获取
 *      要求 添加的 时间复杂度 O(log K)
 *      获取的时间复杂度 O(K)
 *
 * @Date 2021/4/14 14:04
 */

import java.util.HashMap;

public class TopKTimesRealTime {

    public TopRealTimeNode[] heap;
    public int curHeapLen;
    public int size;
    public HashMap<TopRealTimeNode, Integer> indexMap;
    public HashMap<String, TopRealTimeNode> timesMap;
    /*
    *   解题思路：
    *   indexMap : 记录每个元素在堆上的位置
    *   timesMap : 记录每个词 的词频
    *   heap ： 每次获取元素的时候，都是从这里获取遍历
    * */
    public TopKTimesRealTime(int topK) {
        this.size = topK;
        this.curHeapLen = 0;
        this.heap = new TopRealTimeNode[topK];
        this.indexMap = new HashMap<>();
        this.timesMap = new HashMap<>();
    }

    // str用户现在给我的
    public void add(String str) {
        TopRealTimeNode node = timesMap.get(str);
        /* 第一次加入这个词 */
        if (node == null){
            TopRealTimeNode newNode = new TopRealTimeNode(str, 1);
            timesMap.put(str, newNode);
            // 堆不满的话直接加进堆中， 加堆操作需要 在 indexMap 中加上的最后一个位置，然后调整堆结构
            if (curHeapLen != size){
                heap[curHeapLen] = newNode;
                indexMap.put(newNode, curHeapLen);
                slipUp(curHeapLen);
                curHeapLen++;
            }else {
                // 堆满 直接在indexMap中放 -1
                indexMap.put(newNode, -1);
            }
        }else {
        /* 之前加过这个词 */
            node.times++;
            // 频率加完之后 这个词 如果在堆上，判断是否需要调整堆结构
            Integer index = indexMap.get(node);
            if (index != -1){

                heapify(index);
            }
            // 如果不在堆上，看是否需要加到堆上
            // 加上去之后，弹下来的需要修改 indexMap
            else {
                if (node.times > heap[0].times){
                    indexMap.put(heap[0], -1);
                    heap[0] = node;
                    indexMap.put(node, 0);
                    heapify(0);
                }
            }
        }
    }

    private void heapify(int index) {
        int leftSon = index * 2 + 1;
        int rightSon = leftSon + 1;

        while (leftSon < curHeapLen){

            int smallest = rightSon < curHeapLen ?
                    (heap[leftSon].times < heap[rightSon].times ? leftSon : rightSon)
                    : leftSon;
            if (heap[index].times < heap[smallest].times)
                break;
            else{
                swap(index, smallest);
                index = smallest;
                leftSon = index * 2 + 1;
            }
        }
    }

    private void swap(int index1, int index2) {
        TopRealTimeNode tem = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tem;
        indexMap.put(heap[index1], index1);
        indexMap.put(heap[index2], index2);
    }

    private void slipUp(int index) {
        TopRealTimeNode node = heap[index];
        int father = (index - 1) / 2;
        while (node.times < heap[father].times){
            swap(index, father);
            index = father;
            father = (index - 1) / 2;
        }
    }

    public void printTopK() {
        System.out.println("TOP: ");
        for (int i = 0; i != heap.length; i++) {
            if (heap[i] == null) {
                break;
            }
            System.out.print("Str: " + heap[i].string);
            System.out.println(" Times: " + heap[i].times);
        }
    }

    public static void main(String[] args) {
        TopKTimesRealTime record = new TopKTimesRealTime(1);
        record.add("过");
        record.printTopK();
        record.add("郭");
        record.add("郭");
        record.printTopK();
        record.add("学");
        record.add("学");
        record.add("胤");
        record.add("学");
        record.printTopK();
        record.add("胤");
        record.add("胤");
        record.add("胤");
        record.printTopK();
        record.add("学");
        record.add("学");
        record.printTopK();
    }

}
class TopRealTimeNode {
    public String string;
    public int times;

    public TopRealTimeNode(String s, int t) {
        string = s;
        times = t;
    }
}