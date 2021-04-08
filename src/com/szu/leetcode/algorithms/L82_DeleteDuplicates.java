package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *              82. 删除排序链表中的重复元素 II
 *          给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

            示例 1:
            输入: 1->2->3->3->4->4->5
            输出: 1->2->5

            示例 2:
            输入: 1->1->1->2->3
            输出: 2->3

            来源：力扣（LeetCode）
            链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
            著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/3/17 13:09
 */

import com.szu.leetcode.utils.ListNode;

public class L82_DeleteDuplicates {

    public ListNode deleteDuplicates(ListNode head) {
        // 出现重复节点就要开始准备跳了
        boolean jump = false;
        if(head == null || head.next == null) return head;
        // 新建一个假头结点，假头结点指向 head
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        ListNode post = head.next;
        // 此时链表状态为
        /*
        * dummy -> 1  -> 2 ->  3 ->  3 ->  4 ->  4 ->  5
        *  pre    cur  post
        * */
        while(post != null){
            // 出现一样的值，则准备跳跃，post一直遍历到与cur不一样的值为止
            while(post != null && post.val == cur.val ){
                jump = true;
                post = post.next;
            }
            if(jump){
                // 如果需要跳跃，则直接跳跃到 post 所在的位置
                /*
                * 但是此时先不改 pre 的位置，因为不知道下边还是不是重复值
                * 比如 dummy -> 1 -> 1 -> 1 -> 2 -> 2 -> 2 -> 2 -> 3 -> 4
                *      pre    cur post
                * */
                pre.next = post;
                jump = false;
            }else{
                // 此时去改 pre 的时候，保证绝对是没有重复值的节点
                // 此时直接指向 3 节点
                /*
                 * 但是此时先不改 pre 的位置，因为不知道下边还是不是重复值
                 * 比如 dummy ->【 1 -> 1 -> 1 -> 2 -> 2 -> 2 -> 2 ->】 3  ->  4  -> null
                 *                       【已断开连接】                pre     cur   post
                 * */
                pre = cur;
            }
            if(pre == null) break;
            cur = pre.next;
            if(cur == null) break;
            post = cur.next;

        }
        return dummy.next;
    }

}
