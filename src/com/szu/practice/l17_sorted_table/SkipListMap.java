package com.szu.practice.l17_sorted_table;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      手撸一个跳跃表玩玩吧亲
 *
 * @Date 2021/3/28 20:04
 */

import java.util.ArrayList;

public class SkipListMap<K extends Comparable<K>, V> {
    public static final double POSSIBLE = 0.5;
    public SkipListNode<K, V> head;
    public int maxLevel;
    public int size;

    public SkipListMap() {
        this.head = new SkipListNode<>(null, null);
        head.nextNodes.add(null);
        maxLevel = 0;
        size = 0;
    }

    public void put(K key, V value){
        if (key == null) return;
        SkipListNode<K, V> less = mostRightLessNodeInTree(key);
        SkipListNode<K, V> next = less.nextNodes.get(0);
        if (next != null && next.key.compareTo(key) == 0){
            next.value = value;
            return;
        }
        size++;
        SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
        int newNodeLevel = 0;
        while (Math.random() < POSSIBLE)
            newNodeLevel++;
        while (newNodeLevel > maxLevel){
            head.nextNodes.add(null);
            maxLevel++;
        }
        for (int i = 0; i < newNodeLevel; i++) {
            newNode.nextNodes.add(null);
        }
        int level = maxLevel;
        SkipListNode<K, V> pre = head;
        while (level >= 0){
            pre = mostRightLessNodeInLevel(key, pre, level);
            if (level <= newNodeLevel){
                newNode.nextNodes.set(level, pre.nextNodes.get(level));
                pre.nextNodes.set(level, newNode);
            }
            level--;
        }
    }

    public void remove(K key){
        if (containsKey(key)){
            size--;
            SkipListNode<K, V> pre = head;
            int level = maxLevel;
            while (level >= 0){
                pre = mostRightLessNodeInLevel(key, pre, level);
                SkipListNode<K, V> next = pre.nextNodes.get(level);
                if (next != null && next.isKeyEqual(key)){
                    pre.nextNodes.set(level, next.nextNodes.get(level));
                }
                if (level != 0 && pre == head && pre.nextNodes.get(level) == null){
                    head.nextNodes.remove(level);
                    maxLevel--;
                }
                level--;
            }
        }
    }

    private boolean containsKey(K key) {
        if (key == null) return false;
        SkipListNode<K, V> node = mostRightLessNodeInTree(key);
        SkipListNode<K, V> next = node.nextNodes.get(0);
        return next != null && next.isKeyEqual(key);
    }

    private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
        SkipListNode<K ,V> cur = head;
        int level = maxLevel;
        while (level >= 0)
            cur = mostRightLessNodeInLevel(key, cur, level--);
        return cur;
    }

    private SkipListNode<K, V> mostRightLessNodeInLevel(K key, SkipListNode<K, V> cur, int level) {
        SkipListNode<K, V> next = cur.nextNodes.get(level);
        while (next != null && next.isLessKey(key)){
            cur = next;
            next = cur.nextNodes.get(level);
        }
        return cur;
    }
}

class SkipListNode<K extends Comparable<K>, V>{
    public K key;
    public V value;
    public ArrayList<SkipListNode<K, V>> nextNodes;

    public SkipListNode(K key, V value) {
        this.key = key;
        this.value = value;
        nextNodes = new ArrayList<>();
    }

    public boolean isLessKey(K otherKey) {

        return otherKey != null && ( key == null || key.compareTo(otherKey) < 0 );
    }

    public boolean isKeyEqual(K otherKey){
        return ( key == null && otherKey == null ) ||
                (key != null && otherKey != null && key.compareTo(otherKey) == 0);
    }
}
