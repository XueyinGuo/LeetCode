package com.szu.training03.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 803. 打砖块
有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：

一块砖直接连接到网格的顶部，或者
至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时
给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，
对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。
一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。

返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。

注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
 *
 * @Date 2021/5/9 17:03
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class HitBricks {

    /*
     * What a stupid asshole!
     * */
//    public int[] hitBricksWrong(int[][] grid, int[][] hits) {
//        if (hits == null || hits.length == 0)
//            return new int[]{};
//
//        int res[] = new int[hits.length];
//        for (int i = 0; i < hits.length; i++) {
//            int hitR = hits[i][0];
//            int hitC = hits[i][1];
//            int hitNum = getHitNum(grid, hitR, hitC);
//            res[i] = hitNum == 0 ? 0 : hitNum - 1;
//        }
//        return res;
//    }
//
//    private int getHitNum(int[][] grid, int r, int c) {
//
//        if (r < 0 || r > grid.length || c < 0 || c > grid[0].length || grid[r][c] == 0)
//            return 0;
//
//        grid[r][c] = 0;
//
//        int ret = 1;
//        int down = getHitNum(grid, r + 1, c);
//        int left = getHitNum(grid, r, c - 1);
//        int right = getHitNum(grid, r, c + 1);
//        return ret;
//    }

    public int[] hitBricks(int[][] grid, int[][] hits) {
        if (grid == null || grid.length == 0 || hits == null || hits.length == 0)
            return new int[]{};
        /*
         * 把所有炮弹打到的地方变成 2【如果不越界的话】
         * */
        processHit(grid, hits);
        UnionFind unionFind = new UnionFind(grid);
        int[] res = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int preSize = unionFind.getCeilingSize();
            int r = hits[i][0];
            int c = hits[i][1];
            if (grid[r][c] != 2)
                continue;
            unionFind.createDot(r, c);
            unionFind.union(r, c, r - 1, c);
            unionFind.union(r, c, r, c - 1);
            unionFind.union(r, c, r + 1, c);
            unionFind.union(r, c, r, c + 1);
            int addNum = unionFind.getCeilingSize() - preSize;
            res[i] = addNum == 0 ? 0 : addNum - 1;
        }
        return res;
    }

    private void processHit(int[][] grid, int[][] hits) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < hits.length; i++) {
            int r = hits[i][0];
            int c = hits[i][1];
            if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 1)
                grid[r][c] = 2;
        }
    }

    class UnionFind {
        /*
         * 并查集的 代表点表  size表
         * 此处比较特殊的就是这个 ceilingSize 天花板上的砖块数量
         * 和
         * 某个集合是否是接在 天花板上的
         * */
        HashMap<Dot, Dot> parentMap;
        HashMap<Dot, Integer> sizeMap;
        int ceilingSize;
        HashMap<Dot, Boolean> ceilingSet;
        Dot[][] dots;

        public UnionFind(int[][] grid) {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            ceilingSize = 0;
            ceilingSet = new HashMap<>();
            dots = new Dot[grid.length][grid[0].length];
            /*
             * 初始化 并查集，所有的 1 自己先成立自己的小集合
             * */
            initUnionFind(grid);
            firstUnion();
        }

        /*
         * 刚刚初始化结束之后，现在要把所有的经过炮弹处理之后的剩下的所以 1， 能合并的就合并起来
         * */
        private void firstUnion() {
            for (int i = 0; i < dots.length; i++) {
                for (int j = 0; j < dots[0].length; j++) {
                    if (dots[i][j] != null) {
                        union(i, j, i - 1, j);
                        union(i, j, i, j - 1);
                        union(i, j, i + 1, j);
                        union(i, j, i, j + 1);
                    }
                }
            }
        }

        private void union(int r1, int c1, int r2, int c2) {
            /* 判断下标是否越界 */
            if (isValid(r1, c1) && isValid(r2, c2)) {
                Dot father1 = findFather(dots[r1][c1]);
                Dot father2 = findFather(dots[r2][c2]);
                /* 如果两个点不属于同一个集合 */
                if (father1 != father2) {
                    /* 小集合挂在大集合身上 */
                    Integer size1 = sizeMap.get(father1);
                    Integer size2 = sizeMap.get(father2);

                    Dot big = size1 > size2 ? father1 : father2;
                    Dot small = big == father1 ? father2 : father1;

                    parentMap.put(small, big);
                    sizeMap.put(big, size1 + size2);

                    /*
                     * 如果一个是接在天花板上的集合，另一个不是，
                     * 此时需要修改 连接在天花板上的数量了
                     * 因为有新的砖块通过其他元素粘在了天花板上
                     * */
                    boolean contains1 = ceilingSet.get(father1) != null;
                    boolean contains2 = ceilingSet.get(father2) != null;
                    if (contains1 ^ contains2) {
                        if (contains1) {
                            ceilingSet.put(father2, true);
                            ceilingSize += size2;
                        } else {
                            ceilingSet.put(father1, true);
                            ceilingSize += size1;
                        }
                    }
                }
            }
        }

        /*
         * 一路找爹的时候，记得把沿途的路径记下来，然后最后找到最大的爹，把所有爹的爹设置为 大爹
         * */
        private Dot findFather(Dot dot) {
            Dot father = parentMap.get(dot);
            Queue<Dot> queue = new LinkedList<>();
            while (father != dot) {
                queue.add(father);
                dot = father;
                father = parentMap.get(dot);
            }
            while (!queue.isEmpty()) {
                parentMap.put(queue.poll(), father);
            }
            return father;
        }


        private boolean isValid(int r, int c) {
            if (r < 0 || r >= dots.length || c < 0 || c >= dots[0].length || dots[r][c] == null)
                return false;
            return true;
        }

        private void initUnionFind(int[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        Dot dot = new Dot();
                        parentMap.put(dot, dot);
                        sizeMap.put(dot, 1);
                        dots[i][j] = dot;
                    }
                    /* 如果是 第一行，就是正好接在天花板上的那一行，ceilingSize加一，天花板集合中也放入这个点 */
                    if (i == 0 && dots[i][j] != null) {
                        ceilingSet.put(dots[i][j], true);
                        ceilingSize++;
                    }
                }
            }
        }

        /*
         * 指定位置创建 dot
         * */
        public void createDot(int r, int c) {
            if (r >= 0 && r < dots.length && c >= 0 && c < dots[0].length) {
                Dot dot = new Dot();
                dots[r][c] = dot;
                parentMap.put(dot, dot);
                sizeMap.put(dot, 1);
                if (r == 0) {
                    ceilingSize++;
                    ceilingSet.put(dot, true);
                }
            }
        }

        public int getCeilingSize() {
            return ceilingSize;
        }
    }

    class Dot {
    }


    public static void main(String[] args) {


        int[][] grid = LeetCodes.getInputMatrix("[[1,1,1],[0,1,0],[0,0,0]]");
        int[][] hits = LeetCodes.getInputMatrix("[[0,2],[2,0],[0,1],[1,2]]");
        HitBricks test = new HitBricks();
        int[] ints = test.hitBricks(grid, hits);
        System.out.println();

    }
}
