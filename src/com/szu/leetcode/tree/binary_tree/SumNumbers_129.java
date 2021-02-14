package com.szu.leetcode.tree.binary_tree;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *          129. 求根到叶子节点数字之和
 *              给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 *
 *               例如，从根到叶子节点路径 1->2->3 代表数字 123。
 *
 *               计算从根到叶子节点生成的所有数字之和。
 *
 *               来源：力扣（LeetCode）
 *               链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 *               著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/2/14 11:08
 */

import com.szu.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class SumNumbers_129 {

    List<Integer> list = new ArrayList<>();

    public int sumNumbers(TreeNode root) {
        if(root == null){
            return 0;
        }
        doGetPath(root, 0);
        int sum = 0;
        for(Integer i:list){
            sum += i;
        }
        return sum;
    }

    public void doGetPath(TreeNode root, int cur){
        if(root.left == null && root.right == null){
            // 当前节点已经是叶子节点，把累加之后的数字放到集合中待遍历加起来
            list.add(cur * 10 + root.val);
            return;
        }
        // 当前的节点只有左右节点单个节点的时候，只有一条到叶子的路径
        if(root.left == null && root.right!=null){
            doGetPath(root.right, cur*10 + root.val);
        }else if(root.right == null && root.left != null){
            doGetPath(root.left, cur*10 + root.val);
        }else{
            // 当前节点左右两个孩子都有的情况下，有两条路径
            doGetPath(root.right, cur*10 + root.val);
            doGetPath(root.left, cur*10 + root.val);
        }
    }

}
