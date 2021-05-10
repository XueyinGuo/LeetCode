package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 143. 重排链表
给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @Date 2021/5/10 23:37
 */

import com.szu.leetcode.utils.LeetCodes;
import com.szu.leetcode.utils.ListNode;

import java.util.HashMap;

public class L143_ReorderList {

    public void reorderList(ListNode head) {

        if (head == null || head.next == null)
            return;

        HashMap<ListNode, ListNode> preMap = new HashMap<>();
        ListNode node = head;
        ListNode right = head;
        while (node != null) {
            preMap.put(node.next, node);
            right = node;
            node = node.next;
        }
        ListNode left = head;

        if (left.next == right) {
            right.next = left;
            left.next = null;
            head = right;
            return;
        }

        while (left != right) {

            left = left.next;
            right = preMap.get(right);

            ListNode pre = preMap.get(left);
            pre.next = right.next;
            pre.next.next = left;
            if (left.next == right) {
                right.next = null;
                return;
            }

        }
        left.next = null;
    }


    public static void main(String[] args) {
        int[] arr = {1,2};
        ListNode head = LeetCodes.arrayToListNode(arr);
        L143_ReorderList test = new L143_ReorderList();
        test.reorderList(head);
        System.out.println();
    }
}
