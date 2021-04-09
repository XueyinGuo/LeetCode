package com.szu;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 13:19
 */


import com.szu.leetcode.utils.LeetCodes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) throws Exception {

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(3);
        queue.add(5);
        queue.add(7);
        queue.add(3);
        queue.add(9);
        queue.add(9);
        queue.add(9);
        queue.add(9);
        queue.add(9);
        queue.add(9);
        queue.add(2);
        queue.add(2);
        queue.add(2);
        Field field = queue.getClass().getDeclaredField("queue");

        field.setAccessible(true);

        Object o = field.get(queue);
        System.out.println();
        queue.add(2);
        queue.add(2);

    }

}