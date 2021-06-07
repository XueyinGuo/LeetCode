package com.szu.practice.l21_graph.data_structure;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/6/7 20:21
 */

import java.util.ArrayList;

public class GraphNode {

    public int value;
    public int in;
    public int out;
    public ArrayList<GraphNode> nextNodes;
    public ArrayList<Edge> edges;

    public GraphNode(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nextNodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
