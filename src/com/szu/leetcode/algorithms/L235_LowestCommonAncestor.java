package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  236. 二叉树的最近公共祖先
     给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

     百度百科中最近公共祖先的定义为：

     “对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
     满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

 *
 * @Date 2021/4/14 15:45
 */

import com.szu.leetcode.utils.TreeNode;

public class L235_LowestCommonAncestor {

    public static TreeNode lowestAncestor(TreeNode head, TreeNode a, TreeNode b) {
        return process(head, a, b).ans;
    }

    public static class Info {
        public boolean findA;
        public boolean findB;
        public TreeNode ans;

        public Info(boolean fA, boolean fB, TreeNode an) {
            findA = fA;
            findB = fB;
            ans = an;
        }
    }

    public static Info process(TreeNode x, TreeNode a, TreeNode b) {
        if (x == null) {
            return new Info(false, false, null);
        }
        /*
        * 询问自己的左右树 有没有 a 和 b
        * */
        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);
        /*
        * 看看自己有没有找到 a 或者 b
        * 自己就是 a 或者 左树发现 a 或者 右树发现 a 都是发现了 a
        * 同理 b 也是
        * */
        boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
        boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;
        TreeNode ans = null;
        /*
        * 如果左右树上已经找到了 最低的公共父节点， 不要动答案了，就是他了
        * */
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            /*
            * 但是 左树右树上都没找到，而且现在也已经找到了 a b， 那么当前的节点肯定是 最低公共父节点
            * */
            if (findA && findB) {
                ans = x;
            }
        }
        return new Info(findA, findB, ans);
    }

}
