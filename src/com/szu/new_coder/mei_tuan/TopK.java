package com.szu.new_coder.mei_tuan;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 13:19
 */


import java.util.*;

public class TopK {

    public static void main(String[] args) {
        TopKRealTime top = new TopKRealTime(10);
        Scanner sc = new Scanner(System.in);
        int opTimes = sc.nextInt();
        for (int i = 0; i < opTimes; i++) {
            String op = sc.next();
            if (op.equals("query")) {

                TopKRealTimeNode[] newHeap = new TopKRealTimeNode[top.curHeapLen];
                System.arraycopy(top.heap, 0, newHeap, 0, top.curHeapLen);
                Arrays.sort(newHeap, new Comparator<TopKRealTimeNode>() {
                    @Override
                    public int compare(TopKRealTimeNode o1, TopKRealTimeNode o2) {

                        if (o1.times == o2.times)
                            return Integer.valueOf(o1.string) - Integer.valueOf(o2.string);
                        return o2.times - o1.times;
                    }
                });
                int len = newHeap.length;

                if (len == 0)
                    System.out.println("null");
                for (int j = 0; j < len; j++) {
                    if (j != len - 1) {
                        System.out.print(newHeap[j].string + " ");
                    } else
                        System.out.print(newHeap[j].string);
                }


                System.out.println();
            } else {
                int news = sc.nextInt();
                int hot = sc.nextInt();
                top.add(news + "", hot);
            }
        }
    }

    static class TopKRealTime {
        public TopKRealTimeNode[] heap;
        public int curHeapLen;
        public int size;
        public HashMap<TopKRealTimeNode, Integer> indexMap;
        public HashMap<String, TopKRealTimeNode> timesMap;

        public TopKRealTime(int topK) {
            this.size = topK;
            this.curHeapLen = 0;
            this.heap = new TopKRealTimeNode[topK];
            this.indexMap = new HashMap<>();
            this.timesMap = new HashMap<>();
        }

        public void add(String str, int hot) {
            TopKRealTimeNode node = timesMap.get(str);
            if (node == null) {
                TopKRealTimeNode newNode = new TopKRealTimeNode(str, hot);
                timesMap.put(str, newNode);
                if (curHeapLen != size) {
                    heap[curHeapLen] = newNode;
                    indexMap.put(newNode, curHeapLen);
                    slipUp(curHeapLen);
                    curHeapLen++;
                } else {
                    indexMap.put(newNode, -1);
                }
            } else {
                node.times += hot;
                Integer index = indexMap.get(node);
                if (index != -1) {
                    heapify(index);
                } else {
                    if (node.times > heap[0].times) {
                        indexMap.put(heap[0], -1);
                        heap[0] = node;
                        indexMap.put(node, 0);
                        heapify(0);
                    }
                }
            }
        }

        public void heapify(int index) {
            int leftSon = index * 2 + 1;
            int rightSon = leftSon + 1;
            while (leftSon < curHeapLen) {
                int smallest = rightSon < curHeapLen ? (heap[leftSon].times < heap[rightSon].times ? leftSon : rightSon) : leftSon;

                if (heap[index].times < heap[smallest].times)
                    break;
                else {
                    swap(index, smallest);
                    index = smallest;
                    leftSon = index * 2 + 1;
                    rightSon = leftSon + 1;
                }
            }
        }

        public void swap(int index1, int index2) {
            TopKRealTimeNode tem = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tem;
            indexMap.put(heap[index1], index1);
            indexMap.put(heap[index2], index2);
        }

        public void slipUp(int index) {
            TopKRealTimeNode node = heap[index];
            int father = (index - 1) / 2;
            while (node.times < heap[father].times) {
                swap(index, father);
                index = father;
                father = (index - 1) / 2;
            }
        }
    }

    static class TopKRealTimeNode {
        public String string;
        public int times;

        public TopKRealTimeNode(String s, int t) {
            this.string = s;
            this.times = t;
        }
    }


}