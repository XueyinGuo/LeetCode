package com.szu.training.class06;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定两个有序数组 arr1 arr2 ，在给定一个正数 K
 * 求两个数累加和最大的的前 K 个，两个数必须一个来自 arr1 一个来自 arr2
 *
 * @Date 2021/4/21 20:22
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TopKSumCrossTwoArrays {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int arr1[] = LeetCodes.getRandomArray(1000, 5000);
            int arr2[] = LeetCodes.getRandomArray(800, 4000);
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            int k = 5;
            int[] absolutelyRight = topKSumTest(arr1, arr2, k);
            int[] res = topKSum(arr1, arr2, k);
            for (int j = 0; j < res.length; j++) {
                if (absolutelyRight[j] != res[j])
                    System.out.println("FUCK");
            }
        }

    }

    private static int[] topKSum(int[] arr1, int[] arr2, int k) {
        /*
         * 解题思路：两个数组想象成其中一个当行 一个当列 的矩阵
         * 最开始最大的值一定是 最右下角的那个，
         * 把那个放进一个大根堆中，堆中元素按照 相加和来组织
         *
         * 弹出一个之后，就把弹出元素的 左边和上边的元素 加入到队列中，但是记得值加入一次
         * */
        int len1 = arr1.length;
        int len2 = arr2.length;
        boolean[][] isUsed = new boolean[len1][len2];
        int[] res = new int[k];

        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.sum - o1.sum;
            }
        });

        queue.add(new Node(len1 - 1, len2 - 1, arr1[len1 - 1] + arr2[len2 - 1]));
        isUsed[len1 - 1][len2 - 1] = true;
        for (int i = 0; i < k; i++) {
            Node poll = queue.poll();
            int i1 = poll.index1;
            int i2 = poll.index2;
            res[i] = poll.sum;
            if (i1 - 1 >= 0 && !isUsed[i1 - 1][i2]) {
                isUsed[i1 - 1][i2] = true;
                queue.add(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
            }
            if (i2 - 1 >= 0 && !isUsed[i1][i2 - 1]) {
                isUsed[i1][i2 - 1] = true;
                queue.add(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
            }
        }

        return res;
    }

    // For test, this method is inefficient but absolutely right
    public static int[] topKSumTest(int[] arr1, int[] arr2, int topK) {
        int[] all = new int[arr1.length * arr2.length];
        int index = 0;
        for (int i = 0; i != arr1.length; i++) {
            for (int j = 0; j != arr2.length; j++) {
                all[index++] = arr1[i] + arr2[j];
            }
        }
        Arrays.sort(all);
        int[] res = new int[Math.min(topK, all.length)];
        index = all.length - 1;
        for (int i = 0; i != res.length; i++) {
            res[i] = all[index--];
        }
        return res;
    }

    static class Node {
        int index1;
        int index2;
        int sum;

        public Node(int index1, int index2, int sum) {
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
        }
    }
}
