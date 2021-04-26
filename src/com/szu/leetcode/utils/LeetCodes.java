package com.szu.leetcode.utils;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/14 23:03
 */


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class LeetCodes {

    static TreeNode Dummy = new TreeNode();

    /* int数组变成树 */
    public static TreeNode arrayToTree(int[] arr){
        if (arr == null || arr.length==0) return null;
        TreeNode root = new TreeNode(arr[0]);
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty() && index < arr.length){
            TreeNode node = queue.poll();
            if(index == arr.length) break;
            node.left = new TreeNode(arr[index++]);
            if(index == arr.length) break;
            node.right = new TreeNode(arr[index++]);
            queue.add(node.left);
            queue.add(node.right);
        }
        return root;
    }

    /* integer 数组变成树 */
    public static TreeNode integerArrayToTree(Integer[] arr){
        if (arr == null || arr.length==0) return null;
        TreeNode root = new TreeNode(arr[0]);
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int index = 1;
        try {
            while (!queue.isEmpty() && index < arr.length){
                TreeNode node = queue.poll();
                if(index == arr.length) break;
                if (arr[index] == null){
                    node.left = null;
                    index++;
                }
                else
                    node.left = new TreeNode(arr[index++]);
                if(index == arr.length) break;
                if (arr[index] == null){
                    node.right = null;
                    index++;
                }
                else
                    node.right = new TreeNode(arr[index++]);
                if (node.left == null)
                    queue.add(Dummy);
                else
                    queue.add(node.left);
                if (node.right == null)
                    queue.add(Dummy);
                else
                    queue.add(node.right);
            }
        }catch (Exception e){
            System.err.println("二叉树序列不合法!");
        }
        return root;
    }


    /* 字符数组转成树 */
    public static TreeNode charArrayToTree(char[] arr){
        if (arr.length==0 || arr == null) return null;
        TreeNode root = TreeNode.charToTreeNode(arr[0]);
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty() && index < arr.length){
            TreeNode node = queue.poll();
            if(index == arr.length) break;
            node.left = TreeNode.charToTreeNode(arr[index++]);
            if(index == arr.length) break;
            node.right =TreeNode.charToTreeNode(arr[index++]);
            queue.add(node.left);
            queue.add(node.right);
        }
        return root;
    }

    /* 数组转换为链表 */
    public static ListNode arrayToListNode(int arr[]){
        if (arr.length==0 || arr == null) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode lastInsert = head;
        for (int i = 1; i < arr.length; i++) {
            lastInsert.next = new ListNode(arr[i]);
            lastInsert = lastInsert.next;
        }
        return head;
    }

    /* 生成随机数组 */
    public static int[] getRandomArray(int num, int bound) {
        Random random = new Random();
        int[] ret = new int[num];
        for (int i = 0; i < num; i++) {
            ret[i] = random.nextInt(bound) + 1;
        }
        return ret;
    }

    /* 生成随机数组 */
    public static int[] getRandomArrayWithNegative(int num, int bound) {
        Random random = new Random();
        int[] ret = new int[num];
        for (int i = 0; i < num; i++) {
            ret[i] = random.nextInt(bound) - bound / 2;
        }
        return ret;
    }

    /* 打印当前数组中的值 */
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + ", ");
            }
            System.out.println();
        }
    }

    public static int[][] matrixPower(int[][] base, int power) {
        int res[][] = new int[base.length][base[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = base;
        /* 快速幂， log(n)级别 */
        while (power != 0){
            if ((power & 1) != 0){
                res = matrixMultiplication(res, t);
            }
            t = matrixMultiplication(t, t);
            power = power >> 1;
        }
        return res;
    }

    /* 矩阵乘法 */
    // TODO 矩阵不同维度的时候会报空指针异常: 比如 【2*3】 * 【3*2】
    public static int[][] matrixMultiplication(int[][] m1, int[][] m2) {
        int m[][] = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2[0].length; k++) {
                    m[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return m;
    }

    /* 先序序列化一棵树 */
    public static void preOrderedSerialTree(TreeNode root, ArrayList<String> list){
        if (root == null){
            list.add("#");
            return;
        }
        list.add(String.valueOf(root.val));
        preOrderedSerialTree(root.left, list);
        preOrderedSerialTree(root.right, list);
    }

    public static String getRandomString(int len){
        Random random = new Random();
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            int c = random.nextInt(5) + 'a';
            chars[i] = (char)c;
        }
        return String.valueOf(chars);
    }
}
