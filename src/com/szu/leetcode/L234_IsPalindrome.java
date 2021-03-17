package com.szu.leetcode;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/17 19:55
 */

import java.util.HashMap;
import java.util.Map;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class L234_IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        /*
         * head 为空，或者链表长度为1时之即返回
         * */
        if(head == null){
            return true;
        }
        if(head.next == null){
            return true;
        }
        ListNode p = head;
        Map<ListNode, ListNode> nodeMap = new HashMap<>();
        /*
         * 用Map存储父子节点关系
         * key    ->   value
         * 子节点  ->  父节点
         * */
        while(p != null && p.next != null){

            nodeMap.put(p.next, p);
            p = p.next;
        }
        p = head;
        boolean falg = false;
        while(p != null){
            /*
             * 如同回文子串问题，奇数个字符的话会有一个中间点，偶数个字符中间的两个是中间，以中间点往外扩散则可以找到回文子串
             *
             * 链表问题也是一样， 先用 p 指向当前的节点，以当前节点为中心向两边扩散，由于我们用了map存储父子关系，所以子节点可以直接从map中获取父节点
             *
             * 然后 用p 和 p的下一个节点当做中心，向两边扩散
             * */
            boolean res1 = isPalindrome(p, p, nodeMap);
            boolean res2 = isPalindrome(p, p.next, nodeMap);
            if(res1 || res2){
                falg = true;
                break;
            }
            p = p.next;
        }
        return falg;
    }

    boolean isPalindrome(ListNode cur1, ListNode cur2, Map<ListNode, ListNode> nodeMap){

        /*
         * 从中心向两边扩散
         * */
        while( cur1 != null && cur2 != null && cur1.val == cur2.val){
            cur1 = nodeMap.get(cur1);
            cur2 = cur2.next;
        }
        /*
         * 打破循环条件之后，如果已经扫描到链表的头尾，则链表是回文链表
         * */
        if(cur1 == null && cur2 == null){
            return true;
        }
        return false;
    }
}
