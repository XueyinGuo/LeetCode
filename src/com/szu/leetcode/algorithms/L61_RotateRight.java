package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description

        61. 旋转链表

        给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

 * @Date 2021/3/11 17:51
 */

import com.szu.leetcode.utils.ListNode;

import java.util.HashMap;

public class L61_RotateRight {

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        int len = 1;
        /* 用 HashMap 记录前后关系 */
        HashMap<ListNode, ListNode> map = new HashMap<>();
        ListNode tail , node = head;
        while(node.next != null){
            map.put(node.next, node);
            node = node.next;
            /* 顺表计算出长度 */
            len++;
        }
        /* 如果K>链表长度，取模即可减少无用功 */
        k = k % len;
        tail = node;
        while(k > 0){
            /* map 中取到前一个节点 */
            ListNode pre = map.remove(tail);
            /* 断开前驱，并且把当前节点的next指向head */
            pre.next = null;
            tail.next = head;
            /* 更新 map 中的引用关系 */
//            map.put(head, tail);  反正最多更新一轮，用不着再把引用关系更新了
            head = tail;
            tail = pre;
            k--;
        }
        return head;
    }
}
