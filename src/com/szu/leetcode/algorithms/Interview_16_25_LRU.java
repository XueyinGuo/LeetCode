package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/16 17:54
 */

import java.util.HashMap;

public class Interview_16_25_LRU {



    HashMap<Integer, Node> memory;
    final int capacity;
    DoubleList list;

    public Interview_16_25_LRU(int capacity) {
        this.capacity = capacity;
        memory = new HashMap<>();
        list = new DoubleList();
    }

    public void put(Integer key, Integer value){
        Node node = memory.get(key);
        /* 先看缓存有没有这个节点，有的话就是修改值得操作 */
        if (node != null){
            node.value = value;
            list.moveToTail(node);
        } else {
            /* 缓存中没有这个节点 */
            /* 缓存有没有满 */
            if (memory.size() == capacity){
                list.removeMostUnused();
            }
            Node newNode = new Node(key, value);
            memory.put(key, newNode);
            list.addToTail(newNode);
        }
    }

    public Integer get(Integer key){
        /* 缓存有，就返回 v，而且修改为最近使用过的节点 */
        Node node = memory.get(key);
        if (node != null){
            list.moveToTail(node);
            return node.value;
        }
        return -1;
    }

    class DoubleList{
        public Node head;
        public Node tail;

        /* 最近使用过的节点，需要移动到尾巴 */
        public void moveToTail(Node node) {
            /* 如果节点就是尾巴，不用动 */
            if (node == tail)
                return;
            /* 如果动的头结点 */
            else if (node == head){
                head = head.next;
                head.pre = null;
                node.next = null;
                node.pre = tail;
                tail.next = node;
                tail = node;
            }else {
                /* 如果动的是一个普通节点 */
                node.pre.next = node.next;
                node.next.pre = node.pre;
                node.next = null;
                node.pre = tail;
                tail.next = node;
                tail = node;
            }
        }

        /* 把节点移动到链表尾巴，分情况讨论，如果头为空，如果头不是空 */
        public void addToTail(Node node) {
            if (head == null){
                head = node;
                tail = node;
            }else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }

        /* 删除最久没使用的节点，而且Map中也移除 */
        public void removeMostUnused() {
            Node res = head;
            head = head.next;
            res.next = null;
            memory.remove(res.key);
        }
    }

    class Node{
        public Integer key;
        public Integer value;
        public Node pre;
        public Node next;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Interview_16_25_LRU cache = new Interview_16_25_LRU(2);
        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);
        cache.put(3,3);
        cache.get(2);
        cache.put(4,4);
        cache.get(1);
        cache.get(3);
        cache.get(4);
    }
}

