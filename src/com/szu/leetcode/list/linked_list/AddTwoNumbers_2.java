package com.szu.leetcode.list.linked_list;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *    2. 两数相加
 *   给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 *   请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 *   你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @Date 2021/2/15 10:46
 */

public class AddTwoNumbers_2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        return doAdd(l1, l2, false);

    }

    /*
    * plusOne 进一位的标志
    * */
    public ListNode doAdd(ListNode l1, ListNode l2, boolean plusOne){
        if(l1 == null && l2 == null){
            // 如果最后一位了，两个链表都已经为空了， 还有一位需要进位
            // 返回一个 1 节点
            if(plusOne){
                return new ListNode(1);
            }
            // 否则上一位就已经处理完了这时只要返回空就好了
            return null;
        }
        // 计算当前节点的值， l1 可能为空，l2也可能已经为空
        int curVal = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
        // 如果上一位加法过了10，本位需要进1
        if(plusOne){
            curVal = curVal + 1;
        }
        // 每位不能超过10， 需要 % 10创建节点
        ListNode node = new ListNode(curVal % 10);
        // 递归下一个节点， l1 可能为空，l2也可能已经为空，看看当前节点的值是否 > 9
        node.next = doAdd(l1 == null ? null : l1.next,
                l2 == null ? null : l2.next, curVal > 9);
        return node;
    }

    public static void main(String[] args) {
        AddTwoNumbers_2 test = new AddTwoNumbers_2();
        ListNode l1 = new ListNode(1, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9,
                new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))))))))));
        ListNode l2 = new ListNode(9);
        ListNode listNode = test.addTwoNumbers(l1, l2);
        System.out.println();
    }
}
