package com.szu.leetcode.list.linked_list;

public class DetectCycle_142 {

    public ListNode detectCycle(ListNode head) {
        // 如果当前的头结点是空，或者只有一个节点，直接返回空
        if(head == null || head.next == null){
            return null;
        }
        // 定义快慢指针
        ListNode fast = head;
        ListNode slow = head;
        // 如果有环快慢指针总会相遇
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                break;
            }
        }
        // 跳出循环可能是快慢指针相遇了，也有可能是fast指针为空了
        // 先把慢指针归零，慢指针从头往前走，快指针原地往前走，再次相遇就是循环开始的点
        slow = head;
        while(fast != slow){
            // 判断是否是因为 fast 为空跳出了循环
            if(fast == null){
                return null;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        DetectCycle_142 detectCycle_142 = new DetectCycle_142();
        ListNode listNode = new ListNode(3);
        detectCycle_142.detectCycle(listNode);
    }
}
