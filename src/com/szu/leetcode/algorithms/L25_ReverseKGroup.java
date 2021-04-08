package com.szu.leetcode.algorithms;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/9 19:55
 */

import com.szu.leetcode.utils.ListNode;

public class L25_ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 0 ) return head;
        return doReverse(head, k);
    }

    public ListNode doReverse(ListNode head, int k){
        // 用于返回的新头结点
        ListNode newHead = head;
        int num = k;
        // K个一组，先找到从当前的头结点之后的第K个节点，作为新的头结点
        while(num > 1 && newHead != null){
            newHead = newHead.next;
            num--;
        }
        // 如果过程中发现 newHead 为空，则当前剩余个数不足一组，直接返回 head
        if(newHead == null) return head;
        // 定义三个指针，用这三个指针进行next指向关系的改变
        ListNode pre, cur, post;
        pre = cur = head;
        post = head.next;
        while( cur != newHead ){
            //                  newHead
            //                    |
            //k = 2         ① -> ② -> ③ -> ④ -> ⑤
            //             / \    |
            //          pre  cur  post
            cur = post;
            post = post.next;
            cur.next = pre;
            pre = cur;
        }
        // 此时这一轮次中只剩下原来的 head（也就是 ① ）的next的指针没改变，此时改变成下一次返回的头结点
        head.next = doReverse(post, k);
        return newHead;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode newHead = new L25_ReverseKGroup().doReverse(listNode, 2);
        while(newHead != null) {
            System.out.print(newHead.val + " -> ");
            newHead = newHead.next;
        }
    }
}
