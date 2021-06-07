package com.szu.practice.l21_graph;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 图的宽度有限遍历
 *
 * @Date 2021/6/7 20:25
 */

import com.szu.practice.l21_graph.data_structure.Graph;
import com.szu.practice.l21_graph.data_structure.GraphNode;

import java.util.*;

public class BFS {

    public Queue<GraphNode> bfs(Graph graph){
        /*
        * 获取图的 nodeMap 的迭代器
        * doneSet 用来防止重复
        * queue 用来记录层级
        * resList 用来返回结果
        * */
        Iterator<Map.Entry<Integer, GraphNode>> iterator = graph.nodeMap.entrySet().iterator();
        HashSet<GraphNode> doneSet = new HashSet<>();
        Queue<GraphNode> queue = new LinkedList<>();
        Queue<GraphNode> resList = new LinkedList<>();
        // 最外边还套一层迭代器是为了防止 森林
        // 如果没有森林， 少一层循环
        while (iterator.hasNext()){
            Map.Entry<Integer, GraphNode> nextEntry = iterator.next();
            GraphNode node = nextEntry.getValue();
            queue.add(node);
            // 取到一个起始点，然后开始广度有限遍历
            // 直到队列为空为止
            while (!doneSet.contains(node) && !queue.isEmpty()){
                doneSet.add(node);
                resList.add(node);
                GraphNode poll = queue.poll();
                for (GraphNode nextNode : poll.nextNodes) {
                    if (!doneSet.contains(nextNode)){
                        doneSet.add(nextNode);
                        resList.add(nextNode);
                    }
                }
            }
        }
        return resList;

    }

}
