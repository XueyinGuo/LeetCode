package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      148. 排序链表
        给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

        TODO 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 这个垃圾代码只有 8.68%  9.44% 的击败率

 *
 * @Date 2021/3/25 23:37
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L148_SortList {
    /*
    * 虽然遍历数组，加入优先级队列，
    * 确实是 O(logN * N)级别，
    * 但是空间复杂度是很蛋疼的
    * 而且调用方法导致创建栈帧使得常数项比较大
    *
    * 目测是使用二分
    * */
    public ListNode sortList(ListNode head) {
        PriorityQueue<ListNode> queue = new PriorityQueue(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode node1, ListNode node2){
                return node1.val - node2.val;
            }
        });
        while( head != null ){
            queue.add(head);
            head = head.next;
        }

        ListNode newHead = null;
        ListNode lastInsert = null;
        if(!queue.isEmpty()){
            newHead = queue.poll();
            lastInsert = newHead;
        }


        while(!queue.isEmpty()){
            lastInsert.next = queue.poll();
            lastInsert = lastInsert.next;
        }
        if(lastInsert != null)
            lastInsert.next = null;
        return newHead;
    }

}
