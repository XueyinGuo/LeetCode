package com.szu;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 13:19
 */


import com.szu.leetcode.utils.LeetCodes;

import java.lang.reflect.Field;
import java.util.*;

public class Test {

    int a;

    public static void main(String[] args) {
        Test test = new Test();
        test.a = -1;
        test.change(test.a);
        System.out.println();

        HashMap<Integer, Integer> map = new HashMap();
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
//            treeMap.put(next.)
        }
    }

    private void change(int a) {
        this.a = 1;
    }

}