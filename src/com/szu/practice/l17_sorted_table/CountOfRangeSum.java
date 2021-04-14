package com.szu.practice.l17_sorted_table;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  327. 区间和的个数
    给定一个整数数组 nums 。区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

    请你以下标 i （0 <= i <= nums.length ）为起点，元素个数逐次递增，计算子数组内的元素和。

    当元素和落在范围 [lower, upper] （包含 lower 和 upper）之内时，记录子数组当前最末元素下标 j ，记作 有效 区间和 S(i, j) 。

    求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 有效 区间和的个数。
 *
 * @Date 2021/3/28 22:32
 */

import java.util.HashSet;

public class CountOfRangeSum {

    HashSet<Long> set = new HashSet<>();
    /*
    * 解题思路：
    * 把子数组的和在 lower -- upper 之间的转换成
    *
    * 子数组开头位置 的 累加和转换成为  【该位置 前边一直到 数组开头的】的子数组的前缀和 在 [当前总和 - upper] --- [当前总和 - lower]
    * 就转换成了 前缀和求解问题
    *
    * 前缀和的解法 比如 把所有的前缀和放入一个map，求目标和curSum的差值，看map中满足此差值范围的有多少个，也可以接解决
    * 但是时间复杂度过于高了
    *
    * 此时 我们可以把所有的前缀和加到 有序表中，比如本体中傻逼树
    *
    * 为了允许加入重复值，我们设计了 all字段， 来统计添加节点的时候 经过 该节点多少次，可以更方便的数出不大于某个数字的值一共加了多少个进来
    * */
    public int countRangeSum(int[] nums, int lower, int upper) {
        SBTree tree = new SBTree();
        long curSum = 0;
        int ans = 0;
        tree.root = tree.add(tree.root, 0, false);
        set.add(0L);
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            long l = curSum - upper;
            long r = curSum - lower + 1;
            long a = tree.getLessThanNum(l);
            long b = tree.getLessThanNum(r);
            ans += (b - a);
            tree.put(curSum);
        }
        return ans;
    }

    class SBTree{
        SBNode root;

        public SBTree() {
            root = null;
        }

        public SBNode add(SBNode cur, long curSum, boolean contains) {

            //空树
            if (cur == null)
                cur = new SBNode(curSum);
            else {
                // 不是空树
                // 左滑 右滑 all字段  size字段变化
                cur.all++;
                if(!contains)
                    cur.size++; // size 在只有 第一次加进节点的时候 +1， 之后加入相同值时不变， all 总是 +1
                if (cur.value > curSum)
                    cur.left = add(cur.left, curSum, contains);
                else if (cur.value < curSum)
                    cur.right = add(cur.right, curSum, contains);

            }
            return malongalongree(cur);
        }

        private SBNode malongalongree(SBNode cur) {
            if (cur == null) {
                return null;
            }
            long ls = cur.left == null ? 0 : cur.left.size;
            long rs = cur.right == null ? 0 : cur.right.size;
            long lls = cur.left == null || cur.left.left == null ? 0 : cur.left.left.size;
            long lrs = cur.left == null || cur.left.right == null ? 0 : cur.left.right.size;
            long rls = cur.right == null || cur.right.left == null ? 0 : cur.right.left.size;
            long rrs = cur.right == null || cur.right.right == null ? 0 : cur.right.right.size;
            if (ls < rls){
                 cur.right = rightRotate(cur.right);
                 cur = leftRotate(cur);
                 cur.left = malongalongree(cur.left);
                 cur.right = malongalongree(cur.right);
                 cur = malongalongree(cur);
            }else if (ls < rrs){
                cur = leftRotate(cur);
                cur.left = malongalongree(cur.left);
                cur = malongalongree(cur);
            }else if (rs < lls){
                cur = rightRotate(cur);
                cur.right = malongalongree(cur.right);
                cur = malongalongree(cur);
            }else if (rs < lrs){
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
                cur.left = malongalongree(cur.left);
                cur.right = malongalongree(cur.right);
                cur = malongalongree(cur);
            }
            return cur;
        }

        private SBNode rightRotate(SBNode cur) {
            /*
            * 对于树节点的更新策略： 先算出 本身重复添加的的次数； 然后在计算新的 all 值得时候，两边的 all + 重复次数
            * */
            long same = cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
            SBNode left = cur.left;
            left.size = cur.size;
            left.all = cur.all;

            cur.left = left.right;
            left.right = cur;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null? 0 : cur.right.size) + 1;
            cur.all = (cur.left == null ? 0 : cur.left.all) + (cur.right == null? 0 : cur.right.all) + same;
            return left;
        }

        private SBNode leftRotate(SBNode cur) {
            // 当前节点有多少个重复值
            long same = cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
            SBNode right = cur.right;

            right.size = cur.size;
            right.all = cur.all;

            cur.right = right.left;
            right.left = cur;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null? 0 : cur.right.size) + 1;
            cur.all = (cur.left == null ? 0 : cur.left.all) + (cur.right == null? 0 : cur.right.all) + same;
            return right;
        }

        public void put(long curSum) {
            boolean contains = set.contains(curSum);
            root = add(root, curSum, contains);
            set.add(curSum);
        }

        public long getLessThanNum(long num) {

            SBNode node = root;
            long ans = 0;
            while (node != null){

                if (node.value > num){
                    node = node.left;

                }else if (node.value < num){
                    ans += node.all - (node.right == null ? 0 : node.right.all);
                    node = node.right;
                }else {
                    ans += node.left == null ? 0 : node.left.all;
                    break;
                }

            }
            return ans;
        }
    }


    static class SBNode{
        long all;
        SBNode left;
        SBNode right;
        long size;
        long value;

        public SBNode(long value) {
            this.value = value;
            this.size = 1;
            this.all = 1;
        }
    }

    public static void main(String[] args) {
        CountOfRangeSum test = new CountOfRangeSum();


        int arr[] = {2147483647,-2147483648,-1,0};
        long i = test.countRangeSum(arr, -1, 0);
        System.out.println(i);
    }
}
