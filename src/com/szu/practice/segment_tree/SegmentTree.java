package com.szu.practice.segment_tree;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      线段树
 *
 * @Date 2021/3/23 20:24
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Random;

public class SegmentTree {
    //数组实际的大小
    private int size;
    //整体右移一位的数组
    private  int[] arr;
    //区间和数组,懒更新
    private int sum[];
    // 每次 add 操作使用lazy数组来做加速
    private  int lazy[];
    //每次更新的时候使用change数组来进行懒人操作
    private int change[];
    // 与 change数组一块使用，只有 i 位置为 true时，change[i]才是有效的
    private boolean update[];

    public SegmentTree(int[] original){
        this.size = original.length + 1;
        this.arr = new int[original.length+1];
        for (int i = 0; i <original.length; i++) {
            arr[i+1] = original[i];
        }
        this.lazy = new int[size<<2];
        this.change = new int[size<<2];
        this.sum = new int[size<<2];
        this.update = new boolean[size<<2];
    }

    public static void main(String[] args) {

        int[] origin = { 5,8,9,6,4,3,8,7,9,1,4,5,6,1,8};

        int left = 3;
        int right = 9;
        int low = 1;
        int high = origin.length;
        int root = 1;
        int updateNum = 6;
        int addNum = 8;
        SegmentTree segmentTree = new SegmentTree(origin);
        segmentTree.build(low, high, root);
        segmentTree.updateArray(left, right, low, high, root, updateNum);
        segmentTree.addArray(left, right, low, high, root, addNum);

        long query1 = segmentTree.query(left, right, low, high, root);

        ViolenceRight violenceRight = new ViolenceRight(origin);
        violenceRight.update(left, right, updateNum);
        violenceRight.add(left, right, addNum);

        long query = violenceRight.query(left, right);

        System.out.println(query == query1);
        test();
    }


    /* 构造线段树 */
    private void build(int low, int high, int root) {
        if (low == high){
            sum[root] = arr[low];
            return;
        }
        /* 递归构造线段树 */
        int mid = (low + high) >> 1;
        build(low, mid, root << 1);
        build(mid + 1, high, root << 1 | 1);
        /* 构造完子树之后向上累加 */
        pushUpAnswer(root);
    }

    /*
    * 查询区域累加和，从 left ----- right 这个区域的累加和
    * */
    private long query(int left, int right, int low, int high, int root) {
        // low 和 high 一开始是整个巨大的范围，随着递归越来越二分下去，当二分的整个区域被包括在
        // left ---- right 中时，不用递归下去了，就可以插到 sum[] 中 root 相对应的位置的值返回了
        // 刚开始调用方法的时候，low = 1， high = 数组长度， root 也是 1， 因为线段树的总和就在根位置1处
        // 随着递归的不断下沉，root 也要不断变成 自己左右孩子的位置下沉
        if (left <= low && high <= right){
            return sum[root];
        }
        // 计算中间点 和 左右孩子的数量
        int mid = (low + high) >> 1;
        int leftNodes = mid - low + 1;
        int rightNodes = high - mid;
        // 把当前积攒的懒任务下发！！！！！
        pushDownLazyTask(root, leftNodes, rightNodes);
        long ans = 0;
        // 如果没有被包住的话，递归进下一层，但是递归需要判断是否判断单侧子树就可以完成
        if (left <= mid)
            ans += query(left, right, low, mid, root<<1); // low -- mid, 根右移1位变成2
        if (right > mid)
            ans += query(left, right, mid + 1, high, root << 1 | 1); // mid+1 -- high, 根右移1位然后+1变成3
        return ans;
    }


    private void addArray(int left, int right, int low, int high, int root, int addNum) {
        if (left <= low && high <= right){
            // 被包住的话就在本层直接操作了，懒任务积攒在这一层
            lazy[root] += addNum; // lazy对应位置 加上 应该操作的数字
            sum[root] += addNum * ( high - low + 1); // sum 的位置直接 += 节点数量 * 操作数
            return;
        }
        int mid = (low + high) >> 1;
        int leftNodes = mid - low + 1;
        int rightNodes = high - mid;
        // 把当前积攒的懒任务下发！！！！！
        pushDownLazyTask(root, leftNodes, rightNodes);
        if (left <= mid)
            addArray(left, right, low, mid, root << 1, addNum);

        if (right > mid)
            addArray(left, right, mid+1, high, root<<1|1, addNum);
        // 更新本层的答案，递归结束之后重新计算本层的累加和
        pushUpAnswer(root);
    }

    private void updateArray(int left, int right, int low, int high, int root, int updateNum) {
        if (left <= low && high <= right){
            // 包住的话就在本层直接动手
            update[root] = true;
            change[root] = updateNum;
            // 因为是更新操作，所有之前的懒累加任务都全部失效
            lazy[root] = 0;
            sum[root] = updateNum * (high - low + 1);
            return;
        }
        int mid = (low + high) >> 1;
        int leftNodes = mid - low + 1;
        int rightNodes = high - mid;
        // 把当前积攒的懒任务下发！！！！！
        pushDownLazyTask(root, leftNodes, rightNodes);
        if (left <= mid)
            updateArray(left, right, low, mid, root<<1, updateNum);
        if (right > mid)
            updateArray(left, right, mid+1, high, root<<1|1, updateNum);
        // 更新本层的答案，递归结束之后重新计算本层的累加和
        pushUpAnswer(root);
    }

    private void pushUpAnswer(int root) {
        sum[root] = sum[root << 1] + sum[root << 1 | 1];
    }

    private void pushDownLazyTask(int root, int leftNodes, int rightNodes) {
        // root 位置有更新任务吗
        if (update[root]){
            // 有更新任务，就把更新任务下发
            // update 数组 change数组 的 左右孩子位置全部改成当前root位置的值
            update[root << 1] = true;
            update[root << 1 | 1] = true;
            change[root << 1] = change[root];
            change[root << 1 | 1] = change[root];
            // lazy 和 sum 被更新的时候逻辑是：
            /* 被更新了，之前所有的累加逻辑全部失效 */
            lazy[root << 1] = 0;
            lazy[root << 1 | 1] = 0;
            sum[root << 1] = change[root] * leftNodes;
            sum[root << 1 | 1] = change[root] * rightNodes;
            // 任务下发完成之后，把自己设置为false
            update[root] = false;
        }
        // 因为即使更新一次之后，还有多次 累加操作，所以先判断有没有更新，在判断有没有累加
        if (lazy[root] != 0){
            // 累加任务下发的时候，左右孩子的lazy sum 变动逻辑： +=
            lazy[root << 1] += lazy[root];
            sum[root << 1] += lazy[root] * leftNodes;
            lazy[root << 1 | 1] += lazy[root];
            sum[root << 1 | 1] += lazy[root] * rightNodes;
            // 下发完成，自己变成 0
            lazy[root] = 0;
        }
    }

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int low = 1;
            int high = origin.length;
            int root = 1;
            seg.build(low, high, root);
            ViolenceRight rig = new ViolenceRight(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * high) + 1;
                int num2 = (int) (Math.random() * high) + 1;
                int left = Math.min(num1, num2);
                int right = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    //(int left, int right, int low, int high, int root, int addNum)
                    seg.addArray( left, right,low, high, root, C);
                    rig.add(left, right, C);
                } else {
                    seg.updateArray( left, right,low, high, root, C);
                    rig.update(left, right, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * high) + 1;
                int num2 = (int) (Math.random() * high) + 1;
                int left = Math.min(num1, num2);
                int right = Math.max(num1, num2);
                long ans1 = seg.query( left, right,low, high, root);
                long ans2 = rig.query(left, right);
                if (ans1 != ans2) {
                    System.out.println("FUCK" + i +"  "+ k);
                }
            }
        }
        return true;
    }
}
class ViolenceRight {
    public int[] arr;

    public ViolenceRight(int[] origin) {
        arr = new int[origin.length + 1];
        for (int i = 0; i < origin.length; i++) {
            arr[i + 1] = origin[i];
        }
    }

    public void update(int L, int R, int C) {
        for (int i = L; i <= R; i++) {
            arr[i] = C;
        }
    }

    public void add(int L, int R, int C) {
        for (int i = L; i <= R; i++) {
            arr[i] += C;
        }
    }

    public long query(int L, int R) {
        long ans = 0;
        for (int i = L; i <= R; i++) {
            ans += arr[i];
        }
        return ans;
    }

}