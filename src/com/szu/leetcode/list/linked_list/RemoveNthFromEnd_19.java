package com.szu.leetcode.list.linked_list;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description

    19. 删除链表的倒数第 N 个结点

    给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

    进阶：你能尝试使用一趟扫描实现吗？



 * @Date 2021/3/1 0:13
 */

public class RemoveNthFromEnd_19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode fast = head;
        /*
        * 新建一个新的节点指向头结点，slow一开始在这里，
        * fast一开始在头结点
        * */
        ListNode newHead = new ListNode(-1, head);
        ListNode slow = newHead;
        // 保证 fast 比 slow 快 n 个
        while(n > 0){
            fast = fast.next;
            n--;
        }
        // 同时前移
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        /*
         * 如果 slow 位于一开始的位置都没有动过，很容易通过这个newHead改变应该返回哪个头结点
         * 如果 slow 动了，那么头结点肯定不会改变，newHead.next 还是头结点
         * */
        slow.next = slow.next.next;
        return newHead.next;
    }

}
