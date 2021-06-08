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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class L210_CourseScheduleII {
    /*
    * 拓扑排序，
    * 转换成最熟悉的 graph 表示
    * 直接开始判断能不能完成拓扑排序
    * */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if ((prerequisites == null || prerequisites.length == 0)) {
            int[] ints = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                ints[i] = i;
            }
            return ints;
        }
        Graph graph = new Graph();

        changeToMyGraph(prerequisites, graph, numCourses);

        HashMap<GraphNode, Integer> inMap = getNodeInMap(graph);

        Queue<GraphNode> zeroInNodes = new LinkedList<>();
        for (GraphNode node : graph.nodeMap.values()) {
            if (node.in == 0)
                zeroInNodes.add(node);
        }
        List<GraphNode> list = new LinkedList<>();
        while (!zeroInNodes.isEmpty()){
            GraphNode cur = zeroInNodes.poll();
            list.add(cur);
            for (GraphNode nextNode : cur.nextNodes) {
                Integer in = inMap.get(nextNode);
                inMap.put(nextNode, --in);
                if (in == 0)
                    zeroInNodes.add(nextNode);
            }
        }
        if (list.size() != numCourses)
            return new int[]{};
        int size = list.size();
        int res[] = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = list.get(i).val;
        }
        return res;
    }

    private HashMap<GraphNode, Integer> getNodeInMap(Graph graph) {
        HashMap<GraphNode, Integer> inMap = new HashMap<>();
        for (GraphNode node : graph.nodeMap.values()) {
            inMap.put(node, node.in);
        }
        return inMap;
    }

    private void changeToMyGraph(int[][] prerequisites, Graph graph, int numCourses) {
        for (int i = 0; i < numCourses; i++) {
            graph.nodeMap.put(i, new GraphNode(i));
        }

        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
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
        L210_CourseScheduleII test = new L210_CourseScheduleII();
        System.out.println(test.findOrder(num, inputMatrix));
    }
}
