package com.szu.leetcode.list.linked_list;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/11 17:57
 */

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}