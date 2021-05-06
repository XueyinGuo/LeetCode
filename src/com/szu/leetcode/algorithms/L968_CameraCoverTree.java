package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 968. 监控二叉树
    给定一个二叉树，我们在树的节点上安装摄像头。

    节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。

    计算监控树的所有节点所需的最小摄像头数量。

 *
 * @Date 2021/5/6 16:25
 */

import com.szu.leetcode.utils.LeetCodes;
import com.szu.leetcode.utils.TreeNode;

public class L968_CameraCoverTree {

    public int minCameraCover(TreeNode root) {

        Info process = process(root);
        process.uncover++;
        return (int)Math.min(process.noCameraButCovered, Math.min(process.uncover, process.coverWithCamera));

    }

    public Info process(TreeNode root) {

        if (root == null) /* 一个空节点，默认是被覆盖的状态，不能再空节点上放像机，空节点不可能不被覆盖 */
            return new Info(0, Integer.MAX_VALUE, Integer.MAX_VALUE);

        Info left = process(root.left);
        Info right = process(root.right);

        /*
         * 如何保证当前节点不被覆盖，那就是左树上 被覆盖了 但是没放相机
         *                           右树上被覆盖了， 但是没相机
         * */
        long uncovered = left.noCameraButCovered + right.noCameraButCovered;

        /*
         * root 下方的节点都被覆盖了，root 自己也被覆盖了，但是 root 上无放置相机
         * */
        long noCameraButCovered = Math.min(
                /* 第一种可能，左右两边的孩子都有相机，自己一定被覆盖 */
                Math.min(left.coverWithCamera + right.coverWithCamera, left.noCameraButCovered + right.coverWithCamera),
                /* 第二种可能，左右两边的孩子 有一个有相机，另一个没相机，也能保证 root 被覆盖，但是自己不放置相机 */
                right.noCameraButCovered + left.coverWithCamera
        );

        /* root放相机，root下方的所有节点也已经被覆盖了 */
        long coverWithCamera = Math.min(left.uncover, Math.min(left.coverWithCamera, left.noCameraButCovered)) +
                              Math.min(right.uncover, Math.min(right.coverWithCamera, right.noCameraButCovered))
                              + 1;

        return new Info(noCameraButCovered, coverWithCamera, uncovered);
    }

    class Info {
        long noCameraButCovered;
        long coverWithCamera;
        long uncover;

        public Info(long noCameraButCovered, long coverWithCamera, long uncover) {
            this.noCameraButCovered = noCameraButCovered;
            this.coverWithCamera = coverWithCamera;
            this.uncover = uncover;
        }
    }


    public static void main(String[] args) {
        Integer[] ints = {0, 1, null, 2, 3};
        TreeNode root = LeetCodes.integerArrayToTree(ints);
        L968_CameraCoverTree cameraCoverTree = new L968_CameraCoverTree();
        System.out.println(cameraCoverTree.minCameraCover(root));
    }
}
