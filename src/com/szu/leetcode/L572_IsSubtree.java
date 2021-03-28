package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      572. 另一个树的子树
 *      给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
 *      s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 *      https://leetcode-cn.com/problems/subtree-of-another-tree/
 *
 * @Date 2021/3/27 17:33
 */

import com.szu.leetcode.utils.TreeNode;

import java.util.ArrayList;

public class L572_IsSubtree {
    /*
    * 前序遍历生成字符串 + KMP
    * */

    public boolean isSubtree(TreeNode s, TreeNode t) {
        ArrayList<String> bigList = new ArrayList<>();
        ArrayList<String> smallList = new ArrayList<>();
        preOrderedSerialTree(s, bigList);
        preOrderedSerialTree(t, smallList);
        int index = getIndex(bigList, smallList);
        if(index == -1)
            return false;
        return true;
    }

    public static int getIndex(ArrayList<String> bigList, ArrayList<String> smallList) {

        if (smallList.isEmpty() || bigList.isEmpty() || smallList.size() > bigList.size())
            return -1;
        int next[] = getNextArray(smallList);
        int i = 0;
        int j = 0;
        while(i < bigList.size() && j < smallList.size()){

            if (bigList.get(i).equals(smallList.get(j))){
                i++;
                j++;
            }else if ( next[j] != -1 ){
                j = next[j];
            }else {
                i++;
            }
        }
        return j == smallList.size() ? i - j : -1;
    }

    public static int[] getNextArray(ArrayList<String> smallList) {

        if (smallList.size() == 1)
            return new int[]{-1};

        int[] next = new int[smallList.size()];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while( i < smallList.size() ){

            if ( smallList.get(i-1).equals(smallList.get(cn)) ){
                next[i] = cn+1;
                i++;
                cn++;
            }else if ( cn > 0 ){
                cn = next[cn];
            }else
                next[i++] = 0;

        }
        return next;
    }

    public static void preOrderedSerialTree(TreeNode root,  ArrayList<String> list){
        if (root == null){
            list.add("#");
            return;
        }
        list.add(String.valueOf(root.val));
        preOrderedSerialTree(root.left, list);
        preOrderedSerialTree(root.right, list);
    }
}
