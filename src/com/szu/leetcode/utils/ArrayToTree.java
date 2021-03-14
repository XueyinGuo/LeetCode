package com.szu.leetcode.utils;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/14 23:03
 */

import java.util.ArrayDeque;

public class ArrayToTree {

    public TreeNode arrayToTree(int[] arr){

        if (arr.length==0 || arr == null) return null;

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

}
