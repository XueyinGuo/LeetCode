package com.szu.new_coder.tencent;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/18 20:12
 */

public class Cycle {

    public ListNode solve (ListNode s) {
        // write code here
        ListNode head = s;


        while(s.next != null)
            s = s.next;
        s.next = head;
        s = head.next;
        int maxNodeVal = s.val;
        ListNode maxNode = s;
        while (s != head){
            s = s.next;
            if (maxDicOrder(maxNodeVal+"", s.val+"")){
                maxNodeVal = s.val;
                maxNode = s;
            }
        }
        if (maxDicOrder(maxNodeVal+"", head.val+"")){
            maxNode = head;
        }
        ListNode res = maxNode.next;
        maxNode.next = null;
        return res;
    }

    private boolean maxDicOrder(String maxNodeVal, String val) {

        char[] str1 = maxNodeVal.toCharArray();
        char[] str2 = val.toCharArray();
        int i = 0;

        while (i < str1.length && i < str2.length){
            if (str1[i] < str2[i]){
                return true;
            }
            i++;
        }
        if (i == str2.length)
            return false;
        if (i == str1.length)
            return true;
        return false;
    }

    static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Cycle cycle = new Cycle();
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(23);

        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(32);
        ListNode node5 = new ListNode(2);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode solve = cycle.solve(node1);
        System.out.println();
    }
}
