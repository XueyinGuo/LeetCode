package com.szu;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/21 14:58
 */

import com.szu.training02.class03.KTimesOneTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {

//        String a = "leetCode";
//        String b = "leet";
//        System.out.println(a.indexOf(b));
//        System.out.println(b.indexOf(a));
//
//        TreeMap<Integer, Integer> map = new TreeMap<>();
//        map.put(1,1);
//        Integer integer = map.ceilingKey(1);
//        System.out.println(integer);

//        System.out.println(5l / 6l);

        int quickPow = KTimesOneTime.getQuickPow(2, 4);
        System.out.println(quickPow);
    }
}
