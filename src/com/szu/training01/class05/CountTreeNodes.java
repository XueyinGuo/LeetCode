package com.szu.training01.class05;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
   给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。

    完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，
    其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~2h个节点。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/4/16 15:09
 */

import com.szu.leetcode.utils.TreeNode;

public class CountTreeNodes {

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

    public static void main(String[] args) {

    }
    /* 递归数出所有节点个数 */
    public int countNodesViolence(TreeNode root) {
        return countViolence(root);
    }

    public int countViolence(TreeNode node){
        if (node == null)
            return 0;

        int left = countViolence(node.left);
        int right = countViolence(node.right);
        return 1 + left + right;
    }
}
