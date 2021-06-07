package com.szu.practice.l21_graph.data_structure;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/6/7 20:21
 */

import java.util.HashMap;
import java.util.HashSet;

public class Graph {

    public HashMap<Integer, GraphNode> nodeMap;
    public HashSet<Edge> edgeSet;

    public Graph() {
        this.nodeMap = new HashMap<>();
        this.edgeSet = new HashSet<>();
    }
}
