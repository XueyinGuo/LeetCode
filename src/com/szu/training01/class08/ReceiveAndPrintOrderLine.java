package com.szu.training01.class08;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 一种消息接收并打印的结构设计
 *
 * 一直一个消息流会不断的吐出正数 1~N
 * 但是不一定按顺序吐出、如果上次打印的数为 i，那么当 i + 1 出现时，请打印 i + 1 及其之后接收过的并且连续的所有数字
 * 直到 1 ~ N 全部打印完毕，请设计这种接收并打印结构
 *
 * @Date 2021/4/23 16:16
 */

import java.util.HashMap;

public class ReceiveAndPrintOrderLine {

    HashMap<Integer, Node> head;
    HashMap<Integer, Node> tail;
    int lastPrint = 1;

    public ReceiveAndPrintOrderLine() {
        head = new HashMap<>();
        tail = new HashMap<>();
    }

    public void put(int val, String info){
        Node newNode = new Node(val, info);
        head.put(val, newNode);
        tail.put(val, newNode);
        /* 有没有以 val + 1 开始的开头 */
        Node nextHead = head.get(val + 1);
        /* 有没有以 tail - 1 的结尾 */
        Node lastTail = tail.get(val - 1);

        /* 有以 val + 1 开始的头结点 */
        if (nextHead != null){
            // 穿起来之后， val 尾巴失效，因为自己已经连向了下一个节点
            // val - 1 的头失效， 因为有是上一个节点连向了他
            newNode.next = nextHead;
            tail.remove(val);
            head.remove(val + 1);
        }
        /* 有 val - 1 结尾的点 */
        if (lastTail != null){
            // 穿起来之后， val - 1 尾巴失效，因为自己已经连向了下一个节点
            // val 的头失效， 因为有是上一个节点连向了他
            lastTail.next = newNode;
            tail.remove(val - 1);
            head.remove(val);
        }
        /* 终于等到你 */
        if (lastPrint == val)
            printVal();
    }

    private void printVal() {
        /* 头中删除 */
        Node node = head.get(lastPrint);
        head.remove(lastPrint);
        while (node.next != null){
            System.out.print(node.info + " ");
            node = node.next;
        }
        /* 尾巴中移除最后一个来到的链条位置 */
        /* 等待的位置变成 当前来到的节点 数值 + 1 */
        tail.remove(node.val);
        lastPrint = node.val + 1;
        System.out.println(node.info);
    }

    class Node{
        int val;
        String info;
        Node next;

        public Node(int val, String info) {
            this.val = val;
            this.info = info;

        }
    }

    public static void main(String[] args) {
        // MessageBox only receive 1~N
        ReceiveAndPrintOrderLine box = new ReceiveAndPrintOrderLine();

        box.put(2,"B"); // - 2"
        box.put(1,"A"); // 1 2 -> print, trigger is 1

        box.put(4,"D"); // - 4
        box.put(5,"E"); // - 4 5
        box.put(7,"G"); // - 4 5 - 7
        box.put(8,"H"); // - 4 5 - 7 8
        box.put(6,"F"); // - 4 5 6 7 8
        box.put(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3

        box.put(9,"I"); // 9 -> print, trigger is 9

        box.put(10,"J"); // 10 -> print, trigger is 10

        box.put(12,"L"); // - 12
        box.put(13,"M"); // - 12 13
        box.put(11,"K"); // 11 12 13 -> print, trigger is 11

    }

}
