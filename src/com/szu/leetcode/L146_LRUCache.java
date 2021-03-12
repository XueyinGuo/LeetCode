package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 20:15
 */

import java.util.HashMap;

public class L146_LRUCache {

    HashMap<Integer, Cache> map;
    int maxCap;
    int curCap;
    CahceList cahceList;

    public L146_LRUCache(int capacity) {
        map = new HashMap<>();
        maxCap = capacity;
        cahceList = new CahceList(capacity);
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Cache cache = map.get(key);
            cahceList.remove(cache);
            cahceList.addFirst(cache);
            return cache.val;
        }
        return -1;
    }

    public void put(int key, int value) {

        if (map.containsKey(key)) {
            Cache remove = cahceList.remove(map.get(key));
            cahceList.addFirst(remove);
        }
        Cache curCache = new Cache(key, value);
        if (curCap < maxCap) {
            map.put(key , curCache);
            cahceList.addFirst(curCache);
            curCap++;
        } else {
            Cache remove = cahceList.removeLast();
            map.remove(remove.key);
            map.put(key, curCache);
            cahceList.addFirst(curCache);
        }

    }
}

class Cache {
    public int key, val;
    public Cache next, prev;

    public Cache(int k, int v) {
        this.key = k;
        this.val = v;
    }
}

class CahceList {
    // 在链表头部添加节点 x
    private Cache head;
    private Cache tail;
    private Cache lastInsert;
    private int cap;

    public CahceList(int cap) {
        this.cap = cap;
    }

    public void addFirst(Cache cur) {
        if (head == null && tail == null) {
            head = cur;
            tail = cur;
            lastInsert = cur;
            return;
        }
        head = cur;
        head.prev = null;
        head.next = lastInsert;
        lastInsert.prev = head;
        lastInsert = head;
    }

    // 删除链表中的 x 节点（x 一定存在）
    public Cache remove(Cache cur) {
        Cache pre = cur.prev;
        Cache next = cur.next;
        if (next != null){
            cur.next.prev = cur.prev;
        }
        if(pre != null)
            cur.prev.next = cur.next;
        if (tail == cur)
            tail = pre;
        if (cur == head)
            head = null;
        if (cap == 1)
            head = tail = null;
        return cur;
    }

    // 删除链表中最后一个节点，并返回该节点
    public Cache removeLast() {
        Cache res = tail;
        Cache prev = tail.prev;
        if(prev != null)
            prev.next = null;
        tail.prev = null;
        tail = prev;

        return res;
    }


}



class Test{
    public static void main(String[] args) {
        L146_LRUCache test = new L146_LRUCache(2);
        test.put(2,1);
        test.put(2,2);
        int i = test.get(2);
        test.put(1,1);
        test.put(4,1);
        test.get(2);
    }
}
