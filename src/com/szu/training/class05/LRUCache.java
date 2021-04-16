package com.szu.training.class05;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * LRU
 *
 * @Date 2021/4/16 17:23
 */

import java.util.HashMap;

public class LRUCache<K, V> {

    HashMap<K, Node> memory;
    final int capacity;
    DoubleList list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        memory = new HashMap<>();
        list = new DoubleList();
    }

    public void put(K key, V value){
        Node node = memory.get(key);
        if (node != null){
            node.value = value;
            list.moveToTail(node);
        } else {
            if (memory.size() == capacity){
                list.removeMostUnused();
            }
            Node<K, V> newNode = new Node<>(key, value);
            memory.put(key, newNode);
            list.addToTail(newNode);
        }
    }

    public V get(K key){
        Node node = memory.get(key);
        if (node != null){
            list.moveToTail(node);
            return (V)node.value;
        }
        return null;
    }

    class DoubleList<K, V>{
        public Node<K, V> head;
        public Node<K, V> tail;


        public void moveToTail(Node node) {
            if (node == tail)
                return;
            else if (node == head){
                head = head.next;
                head.pre = null;
                node.next = null;
                node.pre = tail;
                tail.next = node;
                tail = node;
            }else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
                node.next = null;
                node.pre = tail;
                tail.next = node;
                tail = node;
            }
        }

        public void addToTail(Node<K, V> node) {
            if (head == null){
                head = node;
                tail = node;
            }else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }

        public void removeMostUnused() {
            Node res = head;
            head = head.next;
            res.next = null;
            memory.remove(res);
        }
    }

    class Node<K, V>{
        public K key;
        public V value;
        public Node<K, V> pre;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
