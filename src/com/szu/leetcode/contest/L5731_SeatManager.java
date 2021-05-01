package com.szu.leetcode.contest;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/5/1 23:00
 */

import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class L5731_SeatManager {


    TreeSet<Integer> set = new TreeSet<>();
    HashSet<Integer> booked = new HashSet<>();

    public L5731_SeatManager(int n) {
        for (int i = 1; i <= n; i++) {
            set.add(i);
        }
    }

    public int reserve() {
        Integer first = set.first();
        set.remove(first);
        booked.add(first);
        return first;
    }

    public void unreserve(int seatNumber) {
        booked.remove(seatNumber);
        set.add(seatNumber);
    }

}
