package com.szu.training01.class06;

import com.szu.leetcode.utils.LeetCodes;

/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 求数组中 子数组的最大异或和
 *
 * @Date 2021/4/21 14:14
 */


class NumTrieNode {
    NumTrieNode[] nextNodes;

    public NumTrieNode() {
        this.nextNodes = new NumTrieNode[2];
    }
}

class NumTrie {
    NumTrieNode head;

    public NumTrie() {
        this.head = new NumTrieNode();
    }

    /* 增加前缀树 */
    public void add(int num) {
        NumTrieNode node = head;
        // 一个 int 一共 32位
        for (int move = 31; move >= 0; move--) {
            int path = (num >> move) & 1;
            if (node.nextNodes[path] == null)
                node.nextNodes[path] = new NumTrieNode();
            node = node.nextNodes[path];
        }
    }

    public int getMaxEor(int num) {
        NumTrieNode node = head;
        int res = 0;
        /*
         * 符号位，尽量要相同的，因为 异或只有相同的 才是 0
         * 是 0 才是正数
         * */
        int path = ((num >> 31) & 1);
        int fit = node.nextNodes[path] == null ? path ^ 1 : path;

        res |= (path ^ fit) << 31;
        node = node.nextNodes[fit];
        for (int move = 30; move >= 0; move--) {
            /*
             * 普通位数，就取相反的，因为不管正数还是负数，高位尽可能的变成 1 才是 尽可能大的数
             * */
            path = (num >> move) & 1;
            // 合适的路 是 相反的 路，只有这样才能变成 1
            fit = path ^ 1;
            // 相反的路上 有路吗？ 有路就走，没路只能走数字相同的路
            fit = node.nextNodes[fit] == null ? (fit ^ 1) : fit;
            res |= (path ^ fit) << move;
            node = node.nextNodes[fit];
        }
        return res;
    }
}


public class MaxEor {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int[] arr = LeetCodes.getRandomArray(100, 50);
            int violence = maxXorSubarrayViolence(arr);
            int right = maxXorSubarray1(arr);
            int trie = maxXorNumTrie(arr);
            if (violence != right || right != trie)
                System.out.println("FUCK");
        }

    }

    private static int maxXorNumTrie(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);
        int xor = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            int cur = numTrie.getMaxEor(xor);
            if (cur > max)
                max = cur;
            /*
             * =========================================================================
             * =========================================================================
             * 把 [0--0] 的前缀异或和 加入前缀树
             * 把 [0--1] 的前缀异或和 加入前缀树
             * 把 [0--2] 的前缀异或和 加入前缀树
             * 在求 3 结尾子数组的最大异或和 的时候， 就可以从这些已经加进去的数组中找到 最大的 异或和
             * 比如
             * 假设 [2--3]的异或和最大，就可以使用当前 [0--3] 的 总异或和 ^ [0--1] 的异或和
             * 省去了 遍历 子数组穷举的过程
             * =========================================================================
             * =========================================================================
             * */
            numTrie.add(xor);
        }
        return max;
    }

    private static int maxXorSubarrayViolence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int length = arr.length;
        int eor[] = new int[length];
        /* 求出前缀和数组 */
        eor[0] = arr[0];
        for (int i = 1; i < length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        int xor = 0;
        /*
         * 穷举以 i 结尾的子数组 的 所有异或和
         * */
        for (int i = 0; i < length; i++) {
            /*
             * 0............i
             * j == 0， 异或和为 eor[i] 自己
             * j == 1， 表示 从 1....i 的 异或和， 需要 eor[i] ^ eor[j-1]
             * */
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    xor = eor[i];
                } else {
                    xor = eor[i] ^ eor[j - 1];
                }
                if (xor > max)
                    max = xor;
            }
        }
        return max;
    }


    /*
     * 大神的正确版本
     * */
    public static int maxXorSubarray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 准备一个前缀异或和数组eor
        // eor[i] = arr[0...i]的异或结果
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        // 生成eor数组，eor[i]代表arr[0..i]的异或和
        for (int i = 1; i < arr.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i <= j; i++) { // 依次尝试arr[0..j]、arr[1..j]..arr[i..j]..arr[j..j]
                max = Math.max(max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1]);
            }
        }
        return max;
    }
}



