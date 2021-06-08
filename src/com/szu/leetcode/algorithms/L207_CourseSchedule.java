package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 207. 课程表
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * @Date 2021/6/8 16:29
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.*;

public class L207_CourseSchedule {
    /*
    * 拓扑排序，
    * 转换成最熟悉的 graph 表示
    * 直接开始判断能不能完成拓扑排序
    * */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if ((prerequisites == null || prerequisites.length == 0))
            return true;
        Graph graph = new Graph();

        changeToMyGraph(prerequisites, graph);

        HashMap<GraphNode, Integer> inMap = getNodeInMap(graph);

        Queue<GraphNode> zeroInNodes = new LinkedList<>();
        for (GraphNode node : graph.nodeMap.values()) {
            if (node.in == 0)
                zeroInNodes.add(node);
        }
        List<GraphNode> res = new LinkedList<>();
        while (!zeroInNodes.isEmpty()){
            GraphNode cur = zeroInNodes.poll();
            res.add(cur);
            for (GraphNode nextNode : cur.nextNodes) {
                Integer in = inMap.get(nextNode);
                inMap.put(nextNode, --in);
                if (in == 0)
                    zeroInNodes.add(nextNode);
            }
        }
        return res.size() == graph.nodes;
    }

    private HashMap<GraphNode, Integer> getNodeInMap(Graph graph) {
        HashMap<GraphNode, Integer> inMap = new HashMap<>();
        for (GraphNode node : graph.nodeMap.values()) {
            inMap.put(node, node.in);
        }
        return inMap;
    }

    private void changeToMyGraph(int[][] prerequisites, Graph graph) {
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            if (!graph.nodeMap.containsKey(from)){
                graph.nodeMap.put(from, new GraphNode(from));
                graph.nodes++;
            }

            if (!graph.nodeMap.containsKey(to)){
                graph.nodeMap.put(to, new GraphNode(to));
                graph.nodes++;
            }

            GraphNode fromNode = graph.nodeMap.get(from);
            GraphNode toNode = graph.nodeMap.get(to);
            fromNode.nextNodes.add(toNode);
            fromNode.out++;
            toNode.in++;
        }
    }

    class GraphNode{
        int val;
        int in;
        int out;
        LinkedList<GraphNode> nextNodes;

        public GraphNode(int val) {
            this.val = val;
            nextNodes = new LinkedList<>();
        }
    }

    class Graph{
        public Graph() {
            this.nodeMap = new HashMap<>();
            this.nodes = 0;
        }
        int nodes;
        HashMap<Integer, GraphNode> nodeMap;
    }

    public static void main(String[] args) {
        int[][] inputMatrix = LeetCodes.getInputMatrix("[[1,4],[2,4],[3,1],[3,2]]");
        int num = 5;
        L207_CourseSchedule test = new L207_CourseSchedule();
        System.out.println(test.canFinish(num, inputMatrix));
    }
}
