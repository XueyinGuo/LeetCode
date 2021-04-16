package com.szu.leetcode.algorithms;

import com.szu.leetcode.utils.TreeNode;

/*
* 222. 完全二叉树的节点个数
* */
class L222_CountNodes {

    public int countNodesAwesome(TreeNode root){
        if (root == null)
            return 0;
        /* 先求出整棵树的最大高度 */
        return bs(root, 1, mostLeftLevel(root));
    }

    public int bs(TreeNode root, int level, int h){
        if (root == null)
            return 0;
        /*
         * 查看自己右树高度，如果右树高度已经到了 最大高度，那么左树必定是一个满二叉树，直接计算节点个数
         * 左树的高度就是 最大层数 - 当前层数
         * 左树节点个数就是 2 ^ (左树高度) - 1， 再加上头结点本身， +1， 即为 2 ^ (左树高度)
         * 转换为 位运算 */
        if (level + mostLeftLevel(root.right) == h){
            return (1 << (h - level)) + bs(root.right, level + 1, h);
        }else{
            /*
             * 右树不是最大高度，右树肯定是满二叉树， 右树高度必定为 树高度 - 层数 - 1
             * */
            return (1 << (h - level - 1)) + bs(root.left, level + 1, h);
        }
    }

    /*
     * 获取整棵树的高度
     * （既然是完全二叉树，那么一直往左窜，一直窜到空肯定是最大高度）
     * */
    public int mostLeftLevel(TreeNode root){
        int level = 0;
        while (root != null){
            level++;
            root = root.left;
        }
        return level;
    }


    /*
    * 此解法过于暴力，以上解法为 O(logN ^ 2)
    * */
    public int countNodes(TreeNode root) {
        // 空树 直接返回0
        if(root == null){
            return 0;
        }

        return doCountNodes(root);
    }

    public int doCountNodes(TreeNode root){
        // 既然是完全二叉树，只要是左树为空，就肯定没有右树了
        if(root.left == null){
            return 1;
        }
        // 有左树无右树，说明这是最后一层的最后一个节点
        if(root.right == null){
            return 2;
        }
        int left = doCountNodes(root.left);
        int right = doCountNodes(root.right);
        // 返回节点的数的时候是 左树个数 + 右树个数 + 自己
        return left + right + 1;
    }
}

