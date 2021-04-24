package com.szu.training01.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 剑指 Offer 36. 二叉搜索树与双向链表
   输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 *
 * @Date 2021/4/15 20:56
 */

public class TreeToDoublyList {

    public Node treeToDoublyList(Node root) {
        if (root == null)
            return null;
        Info process = process(root);
        process.head.left = process.tail;
        process.tail.right = process.head;
        return process.head;
    }

    // 以 root 为头的整棵搜索二叉树，请全部以有序双向链表的方式，连好
    // 并且返回，整个有序双向链表的头节点和尾节点
    private Info process(Node root) {
        if (root == null)
            return new Info(null, null);;
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        /*
        * 我左树的信息 尾巴 是空 吗？
        * 是空就不连接了
        * */
        if (leftInfo.tail != null)
            leftInfo.tail.right = root;
        root.left = leftInfo.tail;
        root.right = rightInfo.head;
        /*
         * 我右树的信息 头 是空 吗？
         * 是空就不连接了
         * */
        if (rightInfo.head != null)
            rightInfo.head.left = root;
        /*
        * 我的左树头为空的话，那么我压根没左树，我自己上
        * 我的右树尾巴为空的话，那么我没右树，我自己上
        * */
        return new Info(
                leftInfo.head == null ? root : leftInfo.head,
                rightInfo.tail == null ? root : rightInfo.tail
        );
    }

    class Info{
        Node head;
        Node tail;

        public Info(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {4, 2, 5, 1, 3};

//        TreeNode root = LeetCodes.integerArrayToTree(arr);
//
//        TreeNode treeNode = new TreeToDoublyList().treeToDoublyList(root);
//        System.out.println();
    }
}
