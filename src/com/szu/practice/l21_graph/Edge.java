package com.szu.practice.l21_graph;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/6/7 20:21
 */

public class Edge {

    public int weight;
    public GraphNode from;
    public GraphNode to;

    public Edge(int weight, GraphNode from, GraphNode to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
