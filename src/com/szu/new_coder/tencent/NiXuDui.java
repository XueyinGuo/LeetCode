package com.szu.new_coder.tencent;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/13 21:55
 */

import java.util.Scanner;

public class NiXuDui {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int nums = (int) Math.pow(2, n);
        int[] numArr = new int[nums];
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int order[] = new int[m];
        for (int i = 0; i < m; i++) {
            order[i] = (int) Math.pow(2, scanner.nextInt());
        }
        Node head = buildNodeList(numArr);
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            Node node = reverseK(order[i], head);
            head = node;
            res[i] = doCount(head);
        }
        for (int i = 0; i < m; i++) {
            System.out.println(res[i]);
        }


    }

    private static int doCount(Node head) {
        int res = 0;
        Node node = head;
        while (node != null){
            int cur = node.val;
            Node next = node;
            while (next != null){
                if(next.val < cur) res++;
                next = next.next;
            }
            node = node.next;
        }
        return res;
    }

    private static Node reverseK(int k, Node head) {
        int group = k-1;
        Node newHead = head;
        while (group > 0 && newHead != null){
            newHead = newHead.next;
            group--;
        }
        if (newHead == null) return head;
        Node pre = head, cur = head, post = head.next;

        while (cur != newHead){
            cur = post;
            post = post.next;
            cur.next = pre;
            pre = cur;
        }

        head.next = reverseK(k, post);
        return newHead;
    }

    private static Node buildNodeList(int[] numArr) {
        Node head = null;
        if (numArr!= null && numArr.length > 0 )
            head = new Node(numArr[0], null);
        Node lastInsert = head;
        for (int i = 1; i < numArr.length; i++) {
            Node node = new Node(numArr[i], null);
            lastInsert.next = node;
            lastInsert = node;
        }
        return head;
    }
}
class Node{

    int val;
    Node next;

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}