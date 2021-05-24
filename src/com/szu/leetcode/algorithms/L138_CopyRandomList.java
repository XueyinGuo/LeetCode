package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/5/24 22:31
 */

import java.util.HashMap;

public class L138_CopyRandomList {

    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        /*
        * 在 map 中记录 老链表中每个 node对应的下标
        * */
        HashMap<Node, Integer> oldNodeIndexMap = new HashMap<>();
        /* 深拷贝的节点数组 */
        Node[] nodeList = new Node[1000];
        Node node = head;
        Node dummy = new Node(0);
        Node lastInsert = dummy;
        int index = 0;
        /*
        * 遍历整个老链表的时候，记录每个节点的 index
        * 并且把 新复制的节点 放置在 节点数组中
        * */
        while (node != null){
            Node newNode = new Node(node.val);
            lastInsert.next = newNode;
            lastInsert = lastInsert.next;
            oldNodeIndexMap.put(node, index);
            nodeList[index++] = newNode;
            node = node.next;
        }
        node = head;
        Node newNode = dummy.next;
        /*
        * 重新遍历老链表，并从 map 中取出所有的 random节点的下标
        * 然后直接从 新节点数组中拿值 赋值给新节点的 random 指针
        * */
        while (node != null){
            if (node.random != null){
                newNode.random = nodeList[oldNodeIndexMap.get(node.random)];
            }
            newNode = newNode.next;
            node = node.next;
        }
        return dummy.next;
    }


    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        Node node7 = new Node(7);
        node7.random = null;

        Node node13 = new Node(13);
        node7.next = node13;
        node13.random = node7;

        Node node11 = new Node(11);
        node13.next = node11;

        Node node10 = new Node(10);
        node11.next = node10;
        node10.random = node11;

        Node node1 = new Node(1);
        node10.next = node1;
        node1.random = node7;

        node11.random = node1;

        L138_CopyRandomList test = new L138_CopyRandomList();
        test.copyRandomList(node7);

    }
}
