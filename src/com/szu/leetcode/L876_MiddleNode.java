package com.szu.leetcode;

import com.szu.leetcode.utils.ListNode;

public class L876_MiddleNode {

    public ListNode middleNode(ListNode head) {
        if(head == null){
            return null;
        }
        // 快慢指针
        ListNode fast = head;
        ListNode slow = head;
        // 快指针两倍速前移，慢指针一步一个脚印
        // 奇数个节点的时候返回正中间的节点，偶数个的时候，返回中间两个节点的第二个节点
        while( fast!=null && fast.next != null ){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
