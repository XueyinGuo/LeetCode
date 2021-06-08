package com.szu.practice.l21_graph;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 拓扑排序
 *
 * @Date 2021/6/7 20:25
 */

import com.szu.practice.l21_graph.data_structure.Graph;
import com.szu.practice.l21_graph.data_structure.GraphNode;

import java.util.*;

public class TopologySort {

    public static List<GraphNode> sortedTopology(Graph graph) {
        /* 传入一张图 */
        HashMap<GraphNode, Integer> inMap = new HashMap<>();
        // 如果满足拓扑排序的要求，必定有至少一个点的入度 为 0
        // 而且入度为 0的点加到 队列中
        Queue<GraphNode> zeroInQueue = new ArrayDeque<>();

        for (GraphNode node : graph.nodeMap.values()) {
            inMap.put(node, node.in);
            if (node.in == 0)
                zeroInQueue.add(node);
        }

        List<GraphNode> res = new LinkedList<>();

        while (!zeroInQueue.isEmpty()){
            // 每个点 都删除自己的影响
            // 意思就是说，每个点的后继节点的入度 - 1
            // 继续找入度为 0 的点加入到 队列中
            GraphNode cur = zeroInQueue.poll();
            res.add(cur);
            for (GraphNode nextNode : cur.nextNodes) {
                Integer in = inMap.get(nextNode);
                in--;
                inMap.put(nextNode, in);
                if (in == 0)
                    zeroInQueue.add(nextNode);
            }
        }

        return res;
    }

}
