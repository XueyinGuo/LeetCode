package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 20:15
 */

import java.util.HashMap;

public class L146_LRUCache {

    int capacity;
    int length;
    HashMap<Integer, CacheNode> cache;
    CacheNode head;
    CacheNode tail;

    public L146_LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.length = 0;
        this.head = this.tail = null;
    }

    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;
        CacheNode node = cache.get(key);

        if (node == head) {
            // no op
            // 当前key 是尾巴节点，需要更新链表关系，更改使用过的需要放到头上
        } else if (node == tail) {

            tail = node.pre;
            node.pre.next = node.next;
            node.next = head;
            node.pre = null;
            head.pre = node;
            head = node;

        } else {
            // 链表既不是头也不是尾巴

            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre = null;
            node.next = head;
            head = node;

        }

        return node.value;
    }


    public void put(int key, int value) {

        CacheNode node;
        /* 缓存长度为 0 */
        if (length == 0) {
            node = new CacheNode(key, value);
            cache.put(key, node);
            head = tail = node;
            length++;
        } else {
            /* 有缓存 */
            /* 缓存包含想要放进的 key */
            if (cache.containsKey(key)) {
                node = cache.get(key);
                // 当前的key 是头结点，直接改动值即可，啥也不用调整
                if (node == head) {
                    node.value = value;
                    // 当前key 是尾巴节点，需要更新链表关系，更改使用过的需要放到头上
                } else if (node == tail) {
                    node.value = value;
                    tail = node.pre;
                    node.pre.next = node.next;
                    node.next = head;
                    node.pre = null;
                    head.pre = node;
                    head = node;

                } else {
                    // 链表既不是头也不是尾巴
                    node.value = value;
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                    node.pre = null;
                    node.next = head;
                    head = node;

                }
            } else {
                /* 缓存不包含想要放进的 key */
                /* 没有满 */
                node = new CacheNode(key, value);
                if (length < capacity) {

                    if (capacity == 1) {
                        cache.remove(tail.key);
                        head = node;
                        tail = node;
                        cache.put(key, node);
                    }else {
                        head.pre = node;
                        node.next = head;
                        head = node;
                        length++;
                    }
                }
                /* 满了 */
                else {
                    if (capacity == 1) {
                        cache.remove(tail.key);
                        head = node;
                        tail = node;
                        cache.put(key, node);
                    } else {
                        cache.remove(tail.key);
                        tail = tail.pre;
                        tail.next = null;
                        node.next = head;
                        head.pre = node;
                        head = node;
                        cache.put(key, node);
                    }
                }
            }
        }


    }

}


class CacheNode {
    CacheNode next;
    CacheNode pre;
    Integer key;
    Integer value;

    public CacheNode(Integer key, Integer value) {
        this.value = value;
        this.key = key;
    }

    public CacheNode(CacheNode pre, Integer value, CacheNode next) {
        this.next = next;
        this.pre = pre;
        this.value = value;
    }
}


class Test {
    public static void main(String[] args) {
        L146_LRUCache test = new L146_LRUCache(2);
        test.put(1, 1);
        test.put(2, 2);
        int i = test.get(1);
        test.put(3, 3);
        test.put(4, 4);
        test.get(1);
        test.get(3);
        test.get(4);
    }
}
