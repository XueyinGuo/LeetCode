package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *      TODO
 *
 * @Date 2021/4/9 10:41
 */

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class L460_LFUCache {

    PriorityQueue<CacheUnit> cache;
    int capacity;
    int size;
    HashMap<Integer, CacheUnit> memory;

    public L460_LFUCache(int capacity){
        this.cache = new PriorityQueue<>(capacity, new Comparator<CacheUnit>() {
            @Override
            public int compare(CacheUnit o1, CacheUnit o2) {
                return o2.use - o1.use;
            }
        });
//        Field f = null;
//        try {
//            f = cache.getClass().getDeclaredField("queue");
//            f.setAccessible(true);
////            this.cacheArray = f.get(cache);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        f.setAccessible(true);
        this.capacity = capacity;
        this.size = 0;
        this.memory = new HashMap<>();
    }

    public int get(int key) {
        CacheUnit unit = memory.get(key);
        if (unit != null){
            unit.use++;
            return unit.val;
        }
        return -1;
    }

    public void put(int key, int value){
        CacheUnit unit = memory.get(key);
        if (unit != null){
            unit.val = value;
            unit.use++;
            return;
        }
        CacheUnit newUnit = new CacheUnit(key, value);
        if (size < capacity){
            memory.put(key, newUnit);
            cache.add(newUnit);
            size++;
            return;
        }
        cache.poll();
        memory.remove(key);
        cache.add(newUnit);
        memory.put(key, newUnit);
    }

    public static void main(String[] args) {
        L460_LFUCache test = new L460_LFUCache(5);
        test.put(5,5);
        test.put(6,6);
        test.put(7,7);
        test.put(8,8);
        test.put(1,1);
        test.get(5);
        test.get(8);
        test.get(6);
        test.get(7);
        test.get(6);
        test.put(2,2);
    }

}

class CacheUnit{
    int use;
    int key;
    int val;

    public CacheUnit(int key, int val) {
        this.key = key;
        this.val = val;
        this.use = 1;
    }
}
