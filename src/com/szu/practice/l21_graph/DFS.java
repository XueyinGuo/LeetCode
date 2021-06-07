package com.szu.practice.l21_graph;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 图的深度优先遍历
 *
 * @Date 2021/6/7 20:25
 */

import com.szu.practice.l21_graph.data_structure.Graph;
import com.szu.practice.l21_graph.data_structure.GraphNode;

import java.util.*;

public class DFS {

    public Queue<GraphNode> dfs(Graph graph) {
        /*
         * 获取图的 nodeMap 的迭代器
         * doneSet 用来防止重复
         * queue 用来记录层级
         * resList 用来返回结果
         * */
        Iterator<Map.Entry<Integer, GraphNode>> iterator = graph.nodeMap.entrySet().iterator();
        HashSet<GraphNode> set = new HashSet<>();
        LinkedList<GraphNode> stack = new LinkedList<>();
        LinkedList<GraphNode> resList = new LinkedList<>();
        // 最外边还套一层迭代器是为了防止 森林
        // 如果没有森林， 少一层循环
        while (iterator.hasNext()){
            Map.Entry<Integer, GraphNode> next = iterator.next();
            GraphNode node = next.getValue();
            stack.addLast(node);
            set.add(node);
            resList.addLast(node);
            /*
            * 开始森林中当前这个图深度优先遍历
            * */
            while (!stack.isEmpty()) {
                // 弹出节点
                GraphNode cur = stack.pollLast();
                for (GraphNode nextNode : cur.nextNodes) {
                    if (!set.contains(nextNode)){
                        // 如果节点的下一个连接节点还没有被遍历过
                        // 把刚刚弹出的压回去
                        // 也把 当前没遍历到的节点压进栈中
                        // 然后直接 break
                        // 下一轮弹出的节点就是 刚刚遍历到的这个新节点了
                        set.add(nextNode);
                        stack.addLast(cur);
                        stack.addLast(nextNode);
                        break;
                    }
                }
            }
        }
        return resList;
    }
}
