package com.szu.practice.l19_union;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 并查集
 *
 * @Date 2021/4/22 21:18
 */

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind<V> {

    static class Node<V>{
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public HashMap<V, Node> nodes;
    public HashMap<Node<V>, Node<V>> parents;
    public HashMap<Node<V>, Integer> sizeMap;

    public UnionFind(List<V> values) {
        nodes = new HashMap<>();
        parents = new HashMap<>();
        sizeMap = new HashMap<>();

        for (V v: values) {
            Node node = new Node(v);
            nodes.put(v, node);
            parents.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public Node<V> findFather(Node<V> cur) {
        Node<V> father = parents.get(cur);
        Stack<Node<V>> stack = new Stack<>();
        /*
        * 当 father 不等于 cur 的时候，继续找最头上的爹，
        * 因为最头上的爹的爹，是他自己
        * 并且一路记录下来 找过的所有位置，放到栈中
        * */
        while (father != cur){
            stack.push(father);
            cur = father;
            father = parents.get(cur);
        }
        /*
        * 栈中所有的值 依次 pop ，把他们的爹设置为头上的爹
        * */
        while (!stack.isEmpty()){
            parents.put(stack.pop(), father);
        }
        /* 最后把头上的爹返回 */
        return father;
    }

    public boolean isSameSet(V v1, V v2){
        Node node1 = nodes.get(v1);
        Node node2 = nodes.get(v2);
        return findFather(node1) == findFather(node2);
    }

    /*
    * 联合方法
    * */
    public void union(V a, V b) {
        /* 找到两个东西的 head */
        Node aHead = findFather(nodes.get(a));
        Node bHead = findFather(nodes.get(b));
        if (aHead != bHead){
            /* 如果两个 head 不是 一个head */
            /* 获取到两个 头 的 size */
            int aSetSize = sizeMap.get(aHead);
            int bSetSize = sizeMap.get(bHead);
            /* 选出大的 和 小的 分别是哪个 */
            Node big = aSetSize > bSetSize ? aHead : bHead;
            Node small = big == aHead ? bHead :aHead;
            /* 把小的挂在大的身上， 大的 size 增加， 并且把小的在 sizemap 中删除 */
            parents.put(small, big);
            sizeMap.put(big, aSetSize + bSetSize);
            sizeMap.remove(small);

        }
    }
}
