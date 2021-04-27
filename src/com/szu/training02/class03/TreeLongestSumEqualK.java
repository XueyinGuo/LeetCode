package com.szu.training02.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个二叉树头结点 head，和一个数字 k
 * 路径的定义：
 * 可以从任意一个点开始，但是只能往下走，往下走可以走到任何节点停止
 * 返回路径累加和为K的所有路径中，最长的路径最多有多少个节点
 *
 * @Date 2021/4/27 16:27
 */

import com.szu.leetcode.utils.LeetCodes;
import com.szu.leetcode.utils.TreeNode;

import java.util.HashMap;

public class TreeLongestSumEqualK {



    private static int longest(TreeNode head, int k) {

        if (head == null)
            return 0;
        /* 前缀和 ， 层数 */
        HashMap<Integer, Integer> map = new HashMap<>();
        /* 还没开始遍历的时候，就已经有一个前缀和 0 了 */
        map.put(0, -1);
        return dfs(head, k, 0, 0, map);
    }

    private static int dfs(TreeNode head, int k, int curSum, int level, HashMap<Integer, Integer> map) {
        if (head == null)
            return 0;

        int ans = 0;
        // 从头节点出发，到当前X节点，总的前缀和是多少，curSum
        curSum += head.val;
        //  该前缀和，最早出现在哪一层
        // 只维持，从头节点出发到当前节点，这条路径上的前缀和
        Integer isShowed = map.get(curSum);
        if (isShowed == null)
            map.put(curSum, level);
        int left = dfs(head.left, k, curSum, level+1, map);
        int right = dfs(head.right, k, curSum, level+1, map);
        ans = left;
        if (right > ans)
            ans = right;

        /*
        * 查看这一条路线中有没有 dif 的前缀和，只要有 dif 我就能凑成 k 那么大的数字了
        * */
        int dif = curSum - k;
        Integer early = map.get(dif);
        int nodeNums = 0;
        /* 找到了 dif，出现在 early 层，计算节点个数，看看跟左右收集的答案 PK 一下，如果更长 */
        if (early != null)
            nodeNums = level - early;
        if (nodeNums > ans)
            ans = nodeNums;
        Integer addLevel = map.get(curSum);
        if (addLevel == level)
            map.remove(curSum);

        return ans;
    }

    public static void main(String[] args) {
        //                   3
        //           -2             3
        //        1      4      5      -7
        //       3 5   2 -5  -5  -3   1   5
//        int K = 0;
//        TreeNode head = new TreeNode(3);
//        head.left = new TreeNode(-2);
//        head.left.left = new TreeNode(1);
//        head.left.right = new TreeNode(4);
//        head.left.left.left = new TreeNode(3);
//        head.left.left.right = new TreeNode(5);
//        head.left.right.left = new TreeNode(2);
//        head.left.right.right = new TreeNode(5);
//
//        head.right = new TreeNode(3);
//        head.right.left = new TreeNode(5);
//        head.right.right = new TreeNode(-7);
//        head.right.left.left = new TreeNode(-5);
//        head.right.left.right = new TreeNode(-3);
//        head.right.right.left = new TreeNode(1);
//        head.right.right.right = new TreeNode(5);

        for (int i = 0; i < 1000000; i++) {

            int arr[] = LeetCodes.getRandomArrayWithNegative(500, 50);
            int k = 100;
            TreeNode root = LeetCodes.arrayToTree(arr);

            int my = longest(root, k);
            int right = longest2(root, k);
            if (my != right)
                System.out.println("FUCK");
        }

    }


    /*
    * 大神的 Right 代码
    * */
    public static int ans = 0; // 收集累加和为K的，最长路径有多少个节点

    public static int longest2(TreeNode head, int K) {
        ans = 0;
        // key ： 前缀和
        // value : 该前缀和，最早出现在哪一层
        // sumMap：只维持，从头节点出发到当前节点，这条路径上的前缀和
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, -1);
        process(head, 0, 0, K, sumMap);
        return ans;
    }

    // 节点X在level层，从头节点到X的父节点形成的累加和是preSum，
    // 从头节点到X的路径上，每一个前缀和都存在sumMap里(key)，记录的是该前缀和最早出现的层数(value)
    // 求出必须以X节点结尾的、累加和是K的所有路径中，含有最多的节点是多少？
    // 并看看能不能更新全局的ans
    public static void process(TreeNode X, int level, int preSum, int K, HashMap<Integer, Integer> sumMap) {
        if (X != null) {
            // 从头节点出发，到当前X节点，总的前缀和是多少，allSum
            int allSum = preSum + X.val;
            if (sumMap.containsKey(allSum - K)) {
                ans = Math.max(ans, level - sumMap.get(allSum - K));
            }
            if (!sumMap.containsKey(allSum)) {
                sumMap.put(allSum, level);
            }
            process(X.left, level + 1, allSum, K, sumMap);
            process(X.right, level + 1, allSum, K, sumMap);
            if (sumMap.get(allSum) == level) {
                sumMap.remove(allSum);
            }
        }
    }

}
