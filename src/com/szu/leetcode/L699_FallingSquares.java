package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      699. 掉落的方块
        在无限长的数轴（即 x 轴）上，我们根据给定的顺序放置对应的正方形方块。

        第 i 个掉落的方块（positions[i] = (left, side_length)）是正方形，其中 left 表示该方块最左边的点位置(positions[i][0])，
        side_length 表示该方块的边长(positions[i][1])。

        每个方块的底部边缘平行于数轴（即 x 轴），并且从一个比目前所有的落地方块更高的高度掉落而下。
        在上一个方块结束掉落，并保持静止后，才开始掉落新方块。

        方块的底边具有非常大的粘性，并将保持固定在它们所接触的任何长度表面上（无论是数轴还是其他方块）。
        邻接掉落的边不会过早地粘合在一起，因为只有底边才具有粘性。



        返回一个堆叠高度列表 ans 。每一个堆叠高度 ans[i] 表示在通过 positions[0], positions[1], ..., positions[i] 表示的方块掉落结束后，
        目前所有已经落稳的方块堆叠的最高高度。
 *
 * @Date 2021/3/25 14:37
 */


import java.util.*;

public class L699_FallingSquares {

    public static void main(String[] args) {
        int[][] positions = {
                {1,2},
                {2,3},
                {6,1}
        };
        new L699_FallingSquares().fallingSquares(positions);
    }

    public List<Integer> fallingSquares(int[][] positions) {
        HashMap<Integer, Integer> map = createPositionIndex(positions);
        // 100   -> 1    306 ->   2   403 -> 3
        // [100,403]   1~3
        int N = map.size(); // 1 ~ 	N
        SegTree segTree = new SegTree(map);
        int max = 0;
        List<Integer> res = new ArrayList<>();
        // 每落一个正方形，收集一下，所有东西组成的图像，最高高度是什么
        for (int[] arr : positions) {
            int left = map.get(arr[0]);
            int right = map.get(arr[0] + arr[1] - 1);
            /* 查一下范围内的当前高度是什么， 范围内的当前高度变 + 添加的正方形的高度 就是 此时的高度 */
            int height = segTree.query(left, right, 1, N, 1) + arr[1];
            max = Math.max(max, height);
            res.add(max);
            /* 把当前范围上全部刷成刚刚计算的高度 */
            segTree.updateArr(left, right, 1, N, height, 1);
        }
        return res;
    }

//    public List<Integer> fallingSquares(int[][] positions) {
//        if (positions == null || positions.length == 0) return new ArrayList<>();
//        HashMap<Integer, Integer> indexMap = createPositionIndex(positions);
//        ArrayList<Integer> result = new ArrayList<>();
//        SegTree segTree = new SegTree(indexMap);
//        int max = 0;
//        for (int i = 0; i < positions.length; i++) {
//            Integer left = indexMap.get(positions[i][0]);
//            Integer right = indexMap.get(positions[i][0] + positions[i][1] - 1);
//            segTree.updateArr( left, right, 1, indexMap.size(), positions[i][1], 1 );
//            int height = segTree.query(left, right, 1, indexMap.size(), 1);
//            max = Math.max(max, height);
//            result.add(max);
//        }
//        return result;
//    }


    private HashMap<Integer, Integer> createPositionIndex(int[][] positions) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < positions.length; i++) {
            treeSet.add(positions[i][0]);
            treeSet.add(positions[i][0] + positions[i][1] - 1);
        }
        Iterator iterator = treeSet.iterator();
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        int index = 0;
        while (iterator.hasNext()){
            indexMap.put((Integer) iterator.next(), ++index);
        }
        return indexMap;
    }
}
class SegTree{
    boolean update[];
    int arr[];
    int change[];
    int max[];
    int size;

    public SegTree(HashMap<Integer, Integer> map) {
        this.size = map.size();
        this.arr = new int[this.size + 1];
        this.update = new boolean[(this.size + 1)<<2];
        this.change = new int[(this.size + 1)<<2];
        this.max = new int[this.size << 2];
    }

    public void updateArr(Integer left, Integer right, int low, int high, int updateNum, int root) {
        if (left <= low && high <= right){
            this.update[root] = true;
            this.change[root] = updateNum;
            this.max[root] = updateNum;
            return;
        }
        int mid = (high + low) >> 1;
        pushDownLazyTask(root);
        if (left <= mid)
            updateArr(left, right, low, mid, updateNum, root << 1);
        if (right > mid)
            updateArr(left, right, mid + 1, high, updateNum ,root << 1 | 1);
        pushUpMax(root);
    }

    private void pushUpMax(int root) {
        max[root] = Math.max(max[root << 1], max[root << 1 | 1]);
    }

    private void pushDownLazyTask(int root) {
        if (update[root]){
            update[root << 1] = true;
            update[root << 1 | 1] = true;
            change[root << 1] = change[root];
            change[root << 1 | 1] = change[root];
            max[root << 1] = change[root];
            max[root << 1 | 1] = change[root];
            update[root] = false;
        }
    }


    public int query(Integer left, Integer right, int low, int high, int root) {
        if (left <= low && high <= right){
            return max[root];
        }
        int mid = (high + low) >> 1;
        pushDownLazyTask(root);
        int leftAns = 0, rightAns = 0;
        if (left <= mid){
            leftAns = query(left, right, low, mid, root << 1);
        }

        if (right > mid){
            rightAns = query(left, right, mid + 1, high ,root << 1 | 1);
        }

        return Math.max(leftAns, rightAns);

    }
}
