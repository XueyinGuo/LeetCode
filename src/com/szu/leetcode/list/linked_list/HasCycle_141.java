package com.szu.leetcode.list.linked_list;

import java.util.HashSet;
import java.util.Set;
/*
* 141. 环形链表
* */
public class HasCycle_141 {
    Set<ListNode> set = new HashSet<>();
    // 使用一个Set 记录，每次把新遇到的节点先看看在 set 中有没有，
    // 没有的话就加到set中，然后看下一个节点
    // 如果发现节点存在set 则有环， 如果到空一直没有，就没有环
    public boolean hasCycle(ListNode head) {
        if(set.contains(head)){
            return true;
        }
        if(head == null){
            return false;
        }
        set.add(head);
        return hasCycle(head.next);
    }

    // 双指针，一快一慢，如果有环，两个指针迟早相遇
    public boolean hasCycle2(ListNode head) {
        ListNode p = head;
        ListNode q = head;
        while(p!= null && p.next != null){
            p = p.next.next;
            q = q.next;
            if(p == q){
                return true;
            }
        }
        return false;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}