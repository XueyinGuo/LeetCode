package com.szu.training02.class07;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 一棵二叉树原本是搜索二叉树，但是其中有两个节点调换了位置，使得这棵二叉树不再是二叉搜索树
 * 请找到这两个错误节点并返回
 *
 * 已知二叉树中所有节点的值都不一样，给定二叉树的头结点 head。返回一个长度为2的二叉树节点类型的数组 errs
 * errs[0]代表第一个错误节点   errs[1]代表第二个错误节点
 *
 * @Date 2021/5/4 9:50
 */

import com.szu.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class RecoverBST {

    public static TreeNode[] getTwoErrNodes(TreeNode head) {
        /*
        * 解题思路：
        * 中序遍历整棵树，生成中序遍历序列
        * 正常的搜索二叉树的中序序列是严格递增的
        *
        * 遍历两次序列，找到两对降序的数字位置
        * 第一个降序的前一个数字，第二个降序的后一个数字，就是 需要调换顺序的两个节点
        * */
        ArrayList<Integer> list = new ArrayList<>();
        // 生成中序序列
        inorderTravel(head, list);
        int firstVal = -1;
        int secondVal = -1;
        int size = list.size();
        // 遍历两次序列，找到 第一个降序的前一个数字，第二个降序的后一个数字
        for (int i = 0; i < size-1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                firstVal = list.get(i);
                break;
            }
        }

        for (int i = size - 1; i >= 1; i--) {
            if (list.get(i-1) > list.get(i)){
                secondVal = list.get(i);
                break;
            }
        }
        // 找到值对应的节点
        TreeNode first = getTreeNode(head, firstVal);
        TreeNode second = getTreeNode(head, secondVal);

        return new TreeNode[]{first, second};
    }

    /*
    * 找到值对应的节点
    * */
    private static TreeNode getTreeNode(TreeNode head, int val) {
        if (head == null || val == -1)
            return null;
        if (head.val == val)
            return head;
        TreeNode left = getTreeNode(head.left, val);
        TreeNode right = getTreeNode(head.right, val);
        return left == null ? right : left;
    }

    public static void inorderTravel(TreeNode head, ArrayList<Integer> list) {
        if (head == null)
            return;
        inorderTravel(head.left, list);
        list.add(head.val);
        inorderTravel(head.right, list);
    }

    /*
    * 十四种情况测试
    *
    * */
    public static void main(String[] args) {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.right = new TreeNode(7);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(4);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(8);
        head.left.left.left = new TreeNode(1);
        // printTree(head);
        System.out.println(isBST(head));

        // ���1, 7 -> e1, 5 -> e2
        System.out.println("situation 1");
        TreeNode head1 = new TreeNode(7);
        head1.left = new TreeNode(3);
        head1.right = new TreeNode(5);
        head1.left.left = new TreeNode(2);
        head1.left.right = new TreeNode(4);
        head1.right.left = new TreeNode(6);
        head1.right.right = new TreeNode(8);
        head1.left.left.left = new TreeNode(1);
        // printTree(head1);
        System.out.println(isBST(head1));
        TreeNode res1 = recoverTree(head1);
        // printTree(res1);
        System.out.println(isBST(res1));

        // ���2, 6 -> e1, 5 -> e2
        System.out.println("situation 2");
        TreeNode head2 = new TreeNode(6);
        head2.left = new TreeNode(3);
        head2.right = new TreeNode(7);
        head2.left.left = new TreeNode(2);
        head2.left.right = new TreeNode(4);
        head2.right.left = new TreeNode(5);
        head2.right.right = new TreeNode(8);
        head2.left.left.left = new TreeNode(1);
        // printTree(head2);
        System.out.println(isBST(head2));
        TreeNode res2 = recoverTree(head2);
        // printTree(res2);
        System.out.println(isBST(res2));

        // ���3, 8 -> e1, 5 -> e2
        System.out.println("situation 3");
        TreeNode head3 = new TreeNode(8);
        head3.left = new TreeNode(3);
        head3.right = new TreeNode(7);
        head3.left.left = new TreeNode(2);
        head3.left.right = new TreeNode(4);
        head3.right.left = new TreeNode(6);
        head3.right.right = new TreeNode(5);
        head3.left.left.left = new TreeNode(1);
        // printTree(head3);
        System.out.println(isBST(head3));
        TreeNode res3 = recoverTree(head3);
        // printTree(res3);
        System.out.println(isBST(res3));

        // ���4, 5 -> e1, 3 -> e2
        System.out.println("situation 4");
        TreeNode head4 = new TreeNode(3);
        head4.left = new TreeNode(5);
        head4.right = new TreeNode(7);
        head4.left.left = new TreeNode(2);
        head4.left.right = new TreeNode(4);
        head4.right.left = new TreeNode(6);
        head4.right.right = new TreeNode(8);
        head4.left.left.left = new TreeNode(1);
        // printTree(head4);
        System.out.println(isBST(head4));
        TreeNode res4 = recoverTree(head4);
        // printTree(res4);
        System.out.println(isBST(res4));

        // ���5, 5 -> e1, 2 -> e2
        System.out.println("situation 5");
        TreeNode head5 = new TreeNode(2);
        head5.left = new TreeNode(3);
        head5.right = new TreeNode(7);
        head5.left.left = new TreeNode(5);
        head5.left.right = new TreeNode(4);
        head5.right.left = new TreeNode(6);
        head5.right.right = new TreeNode(8);
        head5.left.left.left = new TreeNode(1);
        // printTree(head5);
        System.out.println(isBST(head5));
        TreeNode res5 = recoverTree(head5);
        // printTree(res5);
        System.out.println(isBST(res5));

        // ���6, 5 -> e1, 4 -> e2
        System.out.println("situation 6");
        TreeNode head6 = new TreeNode(4);
        head6.left = new TreeNode(3);
        head6.right = new TreeNode(7);
        head6.left.left = new TreeNode(2);
        head6.left.right = new TreeNode(5);
        head6.right.left = new TreeNode(6);
        head6.right.right = new TreeNode(8);
        head6.left.left.left = new TreeNode(1);
        // printTree(head6);
        System.out.println(isBST(head6));
        TreeNode res6 = recoverTree(head6);
        // printTree(res6);
        System.out.println(isBST(res6));

        // ���7, 4 -> e1, 3 -> e2
        System.out.println("situation 7");
        TreeNode head7 = new TreeNode(5);
        head7.left = new TreeNode(4);
        head7.right = new TreeNode(7);
        head7.left.left = new TreeNode(2);
        head7.left.right = new TreeNode(3);
        head7.right.left = new TreeNode(6);
        head7.right.right = new TreeNode(8);
        head7.left.left.left = new TreeNode(1);
        // printTree(head7);
        System.out.println(isBST(head7));
        TreeNode res7 = recoverTree(head7);
        // printTree(res7);
        System.out.println(isBST(res7));

        // ���8, 8 -> e1, 7 -> e2
        System.out.println("situation 8");
        TreeNode head8 = new TreeNode(5);
        head8.left = new TreeNode(3);
        head8.right = new TreeNode(8);
        head8.left.left = new TreeNode(2);
        head8.left.right = new TreeNode(4);
        head8.right.left = new TreeNode(6);
        head8.right.right = new TreeNode(7);
        head8.left.left.left = new TreeNode(1);
        // printTree(head8);
        System.out.println(isBST(head8));
        TreeNode res8 = recoverTree(head8);
        // printTree(res8);
        System.out.println(isBST(res8));

        // ���9, 3 -> e1, 2 -> e2
        System.out.println("situation 9");
        TreeNode head9 = new TreeNode(5);
        head9.left = new TreeNode(2);
        head9.right = new TreeNode(7);
        head9.left.left = new TreeNode(3);
        head9.left.right = new TreeNode(4);
        head9.right.left = new TreeNode(6);
        head9.right.right = new TreeNode(8);
        head9.left.left.left = new TreeNode(1);
        // printTree(head9);
        System.out.println(isBST(head9));
        TreeNode res9 = recoverTree(head9);
        // printTree(res9);
        System.out.println(isBST(res9));

        // ���10, 7 -> e1, 6 -> e2
        System.out.println("situation 10");
        TreeNode head10 = new TreeNode(5);
        head10.left = new TreeNode(3);
        head10.right = new TreeNode(6);
        head10.left.left = new TreeNode(2);
        head10.left.right = new TreeNode(4);
        head10.right.left = new TreeNode(7);
        head10.right.right = new TreeNode(8);
        head10.left.left.left = new TreeNode(1);
        // printTree(head10);
        System.out.println(isBST(head10));
        TreeNode res10 = recoverTree(head10);
        // printTree(res10);
        System.out.println(isBST(res10));

        // ���11, 6 -> e1, 2 -> e2
        System.out.println("situation 11");
        TreeNode head11 = new TreeNode(5);
        head11.left = new TreeNode(3);
        head11.right = new TreeNode(7);
        head11.left.left = new TreeNode(6);
        head11.left.right = new TreeNode(4);
        head11.right.left = new TreeNode(2);
        head11.right.right = new TreeNode(8);
        head11.left.left.left = new TreeNode(1);
        // printTree(head11);
        System.out.println(isBST(head11));
        TreeNode res11 = recoverTree(head11);
        // printTree(res11);
        System.out.println(isBST(res11));

        // ���12, 8 -> e1, 2 -> e2
        System.out.println("situation 12");
        TreeNode head12 = new TreeNode(5);
        head12.left = new TreeNode(3);
        head12.right = new TreeNode(7);
        head12.left.left = new TreeNode(8);
        head12.left.right = new TreeNode(4);
        head12.right.left = new TreeNode(6);
        head12.right.right = new TreeNode(2);
        head12.left.left.left = new TreeNode(1);
        // printTree(head12);
        System.out.println(isBST(head12));
        TreeNode res12 = recoverTree(head12);
        // printTree(res12);
        System.out.println(isBST(res12));

        // ���13, 6 -> e1, 4 -> e2
        System.out.println("situation 13");
        TreeNode head13 = new TreeNode(5);
        head13.left = new TreeNode(3);
        head13.right = new TreeNode(7);
        head13.left.left = new TreeNode(2);
        head13.left.right = new TreeNode(6);
        head13.right.left = new TreeNode(4);
        head13.right.right = new TreeNode(8);
        head13.left.left.left = new TreeNode(1);
        // printTree(head13);
        System.out.println(isBST(head13));
        TreeNode res13 = recoverTree(head13);
        // printTree(res13);
        System.out.println(isBST(res13));

        // ���14, 8 -> e1, 4 -> e2
        System.out.println("situation 14");
        TreeNode head14 = new TreeNode(5);
        head14.left = new TreeNode(3);
        head14.right = new TreeNode(7);
        head14.left.left = new TreeNode(2);
        head14.left.right = new TreeNode(8);
        head14.right.left = new TreeNode(6);
        head14.right.right = new TreeNode(4);
        head14.left.left.left = new TreeNode(1);
        // printTree(head14);
        System.out.println(isBST(head14));
        TreeNode res14 = recoverTree(head14);
        // printTree(res14);
        System.out.println(isBST(res14));
    }

    public static boolean isBST(TreeNode head) {
        if (head == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode pre = null;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (pre != null && pre.val > head.val) {
                    return false;
                }
                pre = head;
                head = head.right;
            }
        }
        return true;
    }


    public static TreeNode recoverTree(TreeNode head) {
        TreeNode[] errs = getTwoErrNodes(head);
        TreeNode[] parents = getTwoErrParents(head, errs[0], errs[1]);
        TreeNode e1 = errs[0];
        TreeNode e1P = parents[0];
        TreeNode e1L = e1.left;
        TreeNode e1R = e1.right;
        TreeNode e2 = errs[1];
        TreeNode e2P = parents[1];
        TreeNode e2L = e2.left;
        TreeNode e2R = e2.right;
        if (e1 == head) {
            if (e1 == e2P) { // ���һ
                e1.left = e2L;
                e1.right = e2R;
                e2.right = e1;
                e2.left = e1L;
            } else if (e2P.left == e2) { // �����
                e2P.left = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            } else { // �����
                e2P.right = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            }
            head = e2;
        } else if (e2 == head) {
            if (e2 == e1P) { // �����
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2;
                e1.right = e2R;
            } else if (e1P.left == e1) { // �����
                e1P.left = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1R;
            } else { // �����
                e1P.right = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1R;
            }
            head = e1;
        } else {
            if (e1 == e2P) {
                if (e1P.left == e1) { // �����
                    e1P.left = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                } else { // �����
                    e1P.right = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                }
            } else if (e2 == e1P) {
                if (e2P.left == e2) { // �����
                    e2P.left = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                } else { // ���ʮ
                    e2P.right = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                }
            } else {
                if (e1P.left == e1) {
                    if (e2P.left == e2) { // ���ʮһ
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.left = e1;
                    } else { // ���ʮ��
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.right = e1;
                    }
                } else {
                    if (e2P.left == e2) { // ���ʮ��
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.left = e1;
                    } else { // ���ʮ��
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.right = e1;
                    }
                }
            }
        }
        return head;
    }

    private static TreeNode[] getTwoErrParents(TreeNode head, TreeNode first, TreeNode second) {

        TreeNode[] parents = new TreeNode[2];
        parents[0] = getParent(head, first);
        parents[1] = getParent(head, second);
        return parents;
    }

    private static TreeNode getParent(TreeNode head, TreeNode node) {
        if (head == null)
            return null;
        if (head.left == node || head.right == node)
            return head;

        TreeNode left = getParent(head.left, node);
        TreeNode right = getParent(head.right, node);
        return left == null ? right : left;
    }


}
