package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 421. 数组中两个数的最大异或值
给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。

进阶：你可以在 O(n) 的时间解决这个问题吗？
 *
 * @Date 2021/5/16 21:55
 */

import com.szu.leetcode.utils.LeetCodes;

public class L421_FindMaxXor {
    /*
    * 前缀树求解
    *
    * 先让高位变 1 是永远的贪心
    *
    * 如果前缀树上没有合适的路，才不得已选 让高位变 0 的路
    *
    * 注意的一点是： 符号位永远选与自己想同的位， 这一位不参与异或
    *               剩余的低位 永远高位先变 1，是永远的准则
    *
    * 因为负数 使用补码表示
    * */
    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        TrieTree tree = new TrieTree();
        int max = 0;
        tree.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            TrieTreeNode node = tree.root;
            int curXor = 0;
            /* 先考虑符号位，符号位取相同的位 */
            int path = (nums[i] >> 31) & 1;
            int fit = path;
            fit = node.nextNodes[fit] == null ? fit ^ 1 : fit;

            curXor |= ((fit ^ path) << 31);
            node = node.nextNodes[fit];
            for (int move = 30; move >= 0; move--) {
                /* 再考虑低位，fit 表示最后选择的路，一开始 fit 默认为 path ^ 1，只有 合适的路不存在的时候，才去选择不得不走的路线 */
                path = (nums[i] >> move) & 1;
                fit = path ^ 1;
                if (node.nextNodes[fit] == null)
                    fit ^= 1;
                /* 或 上去 */
                curXor |= ((path ^ fit) << move);
                node = node.nextNodes[fit];
            }
            if (curXor > max)
                max =curXor;
            tree.add(nums[i]); /* 把当前值加进前缀树 */
        }

        return max;

    }

    class TrieTree {

        TrieTreeNode root;
        public TrieTree() {
            this.root = new TrieTreeNode();
        }
        /* 标准前缀树增加节点代码 */
        public void add(int num) {
            TrieTreeNode node = root;
            for (int move = 31; move >= 0; move--) {
                int path = (num >> move) & 1;
                if (node.nextNodes[path] == null)
                    node.nextNodes[path] = new TrieTreeNode();
                node = node.nextNodes[path];
            }
        }
    }
    /* 每个节点只需两条路， 0 和 1 */
    class TrieTreeNode {
        TrieTreeNode nextNodes[];
        public TrieTreeNode() {
            this.nextNodes = new TrieTreeNode[2];
        }
    }


    public static void main(String[] args) {
        int[] inputArray = LeetCodes.getInputArray(
                "[5,25]");
        L421_FindMaxXor test = new L421_FindMaxXor();
        test.findMaximumXOR(inputArray);
    }
}
