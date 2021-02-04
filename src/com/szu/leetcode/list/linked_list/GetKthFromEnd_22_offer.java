package com.szu.leetcode.list.linked_list;

/*
 * 剑指 Offer 22. 链表中倒数第k个节点
 * */

public class GetKthFromEnd_22_offer {

    public ListNode getKthFromEnd(ListNode head, int k) {
        int total = 0;
        ListNode p = head;
        // 先扫描一遍，查看一共有多少个节点
        while(p != null){
            p = p.next;
            total++;
        }
        // 如果节点数还不足 k 个， 直接返回空
        if(total < k){
            return null;
        }
        // 从头开始找 倒数第 K 个节点
        for(int i = 1; i <= total - k; i++ ){
            head = head.next;
        }
        return head;
    }
}
