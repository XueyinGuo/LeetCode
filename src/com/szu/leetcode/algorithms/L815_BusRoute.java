package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 815. 公交路线
给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。

例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。

求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 *
 * @Date 2021/6/9 23:00
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class L815_BusRoute {

    public int numBusesToDestination(int[][] routes, int source, int target) {
        Graph graph = new Graph();
        for (int[] route : routes) {
            for (int i = 0; i < route.length; i++) {
                Node node = graph.nodeMap.get(route[i]);
                int nextIndex = i + 1 == route.length ? 0 : i + 1;
                Node nextNode = graph.nodeMap.get(route[nextIndex]);
                if (node == null) {
                    node = new Node(route[i]);
                    graph.nodeMap.put(route[i], node);
                }
                if (nextNode == null) {
                    nextNode = new Node(route[nextIndex]);
                    graph.nodeMap.put(route[nextIndex], node);
                }
                node.nextNodes.add(nextNode);
            }
        }
        /*
        * 深度优先遍历 最短的路径
        * */
//        LinkedList<Node> path =
//        if ()
        return 0;
    }

    class Node {
        int val;
        List<Node> nextNodes;

        public Node(int val) {
            this.val = val;
            this.nextNodes = new LinkedList<>();
        }
    }

    class Graph {
        HashMap<Integer, Node> nodeMap;

        public Graph() {
            this.nodeMap = new HashMap<>();
        }
    }
}
