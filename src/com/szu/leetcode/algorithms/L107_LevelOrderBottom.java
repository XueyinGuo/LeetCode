package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *      107. 二叉树的层序遍历 II
 *
    给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
    https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 * @Date 2021/3/1 22:28
 */

import com.szu.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L107_LevelOrderBottom {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 如果当前树根节点为空，直接返回空数集合
        if(root == null) return new ArrayList();
        // 链表存放结果集，实现从底向上的结果只需要从顶向下的遍历结果头插法即可
        LinkedList<List<Integer>> res = new LinkedList<>();

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        // 根节点入队
        queue.offer(root);
        TreeNode node ; // help GC
        while(!queue.isEmpty()){
            // 层次节点个数
            int levelNum = queue.size();
            List<Integer> levelList = new ArrayList<>();
            // 遍历本层的节点
            for(int i = 0;i< levelNum; i++){
                // 本层节点挨个出队，把他们的左右子节点加入到队列中，并收集本层的结果进 levelList
                node = queue.poll();
                levelList.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            // 头插法进行结果集构造
            res.addFirst(levelList);

        }
        return res;
    }

}
