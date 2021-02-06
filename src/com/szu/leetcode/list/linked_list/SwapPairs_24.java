package com.szu.leetcode.list.linked_list;
/*
* 24. 两两交换链表中的节点
*
* 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
*
* https://leetcode-cn.com/problems/swap-nodes-in-pairs/
* */
public class SwapPairs_24 {
    public ListNode swapPairs(ListNode head) {
        // 当前节点时空，或者当前节点的下一个节点已经是空，直接返回当前节点
        if(head == null || head.next == null){
            return head;
        }
        // 两两交换，所以两步一跳
        ListNode nextHead = swapPairs(head.next.next);
        //交换当前的节点和下一个节点
        // 没交换之前先找到两个节点自己的位置
        ListNode cur = head;
        ListNode pos = head.next;
        // 后一个next指针指向前一个
        pos.next = cur;
        // 前一个的next指针指向下一层返回的节点
        cur.next = nextHead;
        // 返回这一层交换 原来位置的第二个节点
        return pos;
    }
}
