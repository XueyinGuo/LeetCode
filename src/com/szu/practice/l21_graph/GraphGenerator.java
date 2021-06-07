package com.szu.practice.l21_graph;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/6/7 22:19
 */

public class GraphGenerator {

    // matrix 所有的边
    // N*3 的矩阵
    // [weight, from节点上面的值，to节点上面的值]
    //
    // [ 5 , 0 , 7]
    // [ 3 , 0,  1]
    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();

        for (int[] row : matrix) {
            int weight = row[0];
            int from = row[1];
            int to = row[2];
            /*
            * graph 中有没有两个点
            * 如果没有就创建这两个点
            * */
            if (!graph.nodeMap.containsKey(from))
                graph.nodeMap.put(from, new GraphNode(from));

            if (!graph.nodeMap.containsKey(to))
                graph.nodeMap.put(to, new GraphNode(to));
            /*
            * 从图中获取两个端点
            * */
            GraphNode fromNode = graph.nodeMap.get(from);
            GraphNode toNode = graph.nodeMap.get(to);
            /*
            * 新建两个端点之间的遍
            * */
            Edge newEdge = new Edge(weight, fromNode, toNode);
            /* 出点 连接的集合点中添加 到达点  */
            fromNode.nextNodes.add(toNode);
            /* 出度入度 ++ */
            fromNode.out++;
            toNode.in++;
            /* 出点的边集合中加入新边 */
            fromNode.edges.add(newEdge);
            /* 图的总边集合中添加新边 */
            graph.edgeSet.add(newEdge);
        }
        return graph;
    }

}
