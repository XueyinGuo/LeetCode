package com.szu;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 目标和：给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
 * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面，返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * @Date 2021/5/10 15:16
 */

import com.szu.leetcode.utils.LeetCodes;
import com.szu.leetcode.utils.ListNode;
import com.szu.training02.class03.KTimesOneTime;

import java.math.BigDecimal;
import java.util.*;

public class Test {
    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//
//        String next = scanner.next();
//        String substring = next.substring(1, next.length() - 1);
//        System.out.println(substring);

        ArrayList<Integer> list1 = new ArrayList<>();

        list1.add(1);
        list1.add(2);
        list1.add(3);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);

        int i = list1.hashCode();
        int j = list2.hashCode();
        System.out.println(i);
        System.out.println(j);

    }


}
