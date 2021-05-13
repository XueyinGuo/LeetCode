package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 547. 省份数量
有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。

省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。

返回矩阵中 省份 的数量。
 *
 * @Date 2021/5/13 23:19
 */

import java.util.ArrayList;

public class L547_FindCircleNum {

    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0)
            return 0;

        UnionFind unionFind = new UnionFind(isConnected);
        /*
        * 小加速：
        * 既然每个省份都是连在一起的，那么必然 矩阵关于对角线对称的
        * 所以只需要关注上半区即可了
        *
        * 也是一个很简单的并查集使用即可解答
        * */
        for (int r = 0; r < isConnected.length; r++) {
            for (int c = r; c < isConnected[0].length; c++) {
                if (isConnected[r][c] == 1 && !unionFind.isSameSet(r, c))
                    unionFind.union(r, c);

            }
        }
        return unionFind.getNum();
    }

    class UnionFind {

        int num;
        int[] sizeMap;
        int[] parentMap;

        public UnionFind(int[][] isConnected) {
            num = isConnected.length;
            sizeMap = new int[isConnected.length];
            parentMap = new int[isConnected.length];
            initUnionFind();
        }

        private void initUnionFind() {

            for (int i = 0; i < num; i++) {
                sizeMap[i] = 1;
                parentMap[i] = i;
            }

        }

        public void union(int city1, int city2) {
            int father1 = findFather(city1);
            int father2 = findFather(city2);
            if (father1 != father2) {

                int size1 = sizeMap[father1];
                int size2 = sizeMap[father2];

                int small = size1 > size2 ? father2 : father1;
                int big = small == father1 ? father2 : father1;

                parentMap[small] = big;
                sizeMap[big] = size1 + size2;
                num--;
            }
        }

        private boolean isSameSet(int city1, int city2) {

            int father1 = findFather(city1);
            int father2 = findFather(city2);
            return father1 == father2;
        }

        private int findFather(int city) {

            ArrayList<Integer> path = new ArrayList<>();
            int father = parentMap[city];
            while (father != city) {

                path.add(father);
                city = father;
                father = parentMap[city];

            }

            for (int i = 0; i < path.size(); i++) {
                parentMap[path.get(i)] = father;
            }
            return father;
        }

        public int getNum() {

            return num;
        }
    }

}
