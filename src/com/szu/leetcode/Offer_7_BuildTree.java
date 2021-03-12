package com.szu.leetcode;


import com.szu.leetcode.utils.TreeNode;
/*
* 剑指 Offer 07. 重建二叉树
* */
public class Offer_7_BuildTree {

    /* 有bug，只通过8个案例 */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length != inorder.length || preorder.length == 0){
            return null;
        }
        return doBuild(preorder, inorder,0, preorder.length-1,0, inorder.length-1);
    }


    public  TreeNode doBuild(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preEnd < 0 || inEnd < 0 || preEnd - preStart != inEnd - inStart){
            return null;
        }
        int head = preorder[preStart];
        int headInInorderIndex = -1;
        for(int i = inStart; i < inEnd; i++){
            if(inorder[i] == head){
                headInInorderIndex = i;
                break;
            }
        }
        if (headInInorderIndex == -1){
            return new TreeNode(head);
        }
        TreeNode left = doBuild( preorder, inorder,
                preStart + 1, preStart + headInInorderIndex - inStart,
                    inStart, headInInorderIndex -1  );

        TreeNode right = doBuild( preorder, inorder,
                headInInorderIndex +1, preEnd,
               headInInorderIndex +1 , inEnd );
        TreeNode root = new TreeNode(head);
        root.left = left;
        root.right = right;
        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new Offer_7_BuildTree().buildTree(new int[]{3, 9, 20, 15, 7},
                new int[]{9, 3, 15, 20, 7});
        System.out.println();
    }


    /* 没bug，效率贼鸡儿低 */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {

        if (inorder.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        if (inorder.length == 1){
            return root;
        }
        //idx表示先序遍历的第一个数字位于中序的第几个位置， 0号为第一个位置
        int idx = 1;
        for(int i = 0; i < inorder.length; ++i){
            if (inorder[i] == preorder[0]){
                idx = i + 1;
            }
        }
        int[] before_pre = new int[idx - 1];
        int[] before_in = new int[idx - 1];

        int[] after_pre = new int[inorder.length - idx];
        int[] after_in = new int[inorder.length - idx];

        System.arraycopy(preorder, 1, before_pre, 0, idx - 1);
        System.arraycopy(inorder, 0, before_in, 0, idx - 1);

        System.arraycopy(preorder, idx, after_pre, 0, preorder.length - idx);
        System.arraycopy(inorder, idx, after_in, 0, inorder.length - idx);
        root.left = buildTree(before_pre, before_in);
        root.right = buildTree(after_pre, after_in);
        return root;
    }

    // TODO 只找单个节点也可以实现，没必要非得真个数组传递
}
