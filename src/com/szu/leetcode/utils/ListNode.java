package com.szu.leetcode.utils;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/11 17:57
 */

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}