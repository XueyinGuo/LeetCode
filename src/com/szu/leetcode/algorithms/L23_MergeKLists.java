package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *          分治思想
 *
 *      23. 合并K个升序链表
 *      给你一个链表数组，每个链表都已经按升序排列。

         请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @Date 2021/3/17 0:02
 */

import com.szu.leetcode.utils.ListNode;

public class L23_MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {

        return mergeKLists(lists, 0, lists.length-1);

    }

    public ListNode mergeKLists(ListNode[] lists, int low, int high){
        // 跳出循环的base，当两个位置相等时，返回对应位置的节点，直接开始合并
        if(low == high)
            return lists[low];
        // 归并排序
        if( low < high){
            int mid = low + (high - low)/2;
            ListNode l1= mergeKLists(lists, low, mid);
            ListNode l2= mergeKLists(lists, mid+1, high);
            // 合并两个升序链表
            return merge2Lists( l1, l2);
        }
        return null;
    }
    /*
    * 合并两个升序链表
    * */
    ListNode merge2Lists(ListNode l1, ListNode l2){

        ListNode dummy = new ListNode();
        ListNode head = dummy;
        while(l1 != null && l2 != null){

            if(l1.val < l2.val){
                head.next = l1;
                head = head.next;
                l1 = l1.next;
            }else{
                head.next = l2;
                head = head.next;
                l2 = l2.next;
            }

        }

        while(l1 != null){
            head.next = l1;
            head = head.next;
            l1 = l1.next;
        }
        while(l2 != null){
            head.next = l2;
            head = head.next;
            l2 = l2.next;
        }
        return dummy.next;
    }

}
