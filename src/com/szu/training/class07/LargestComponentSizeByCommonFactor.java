package com.szu.training.class07;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 952. 按公因数计算最大组件大小
    给定一个由不同正整数的组成的非空数组 A，考虑下面的图：

    有 A.length 个节点，按从 A[0] 到 A[A.length - 1] 标记；
    只有当 A[i] 和 A[j] 共用一个大于 1 的公因数时，A[i] 和 A[j] 之间才有一条边。
    返回图中最大连通组件的大小。
 *
 * @Date 2021/4/22 21:46
 */

import java.util.ArrayList;
import java.util.HashMap;

public class LargestComponentSizeByCommonFactor {

    public int largestComponentSize(int[] arr) {
        int len = arr.length;
        UnionFind unionFind = new UnionFind(len);
        /*
        * 公因子 Map
        *
        * 用来存放所有出现过的公因子，和 拥有这个 公因子的 下标
        * */
        HashMap<Integer, Integer> fatorsMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int num = arr[i];
            /*
            * 枚举 所有可能的公因子， 枚举范围是 1---根号num的向下取整
            * */
            int limit = (int)Math.sqrt(num);
            for (int j = 1; j <= limit ; j++) {
                if (num % j == 0){
                    /* 公因子 不是 1 的时候 */
                    if (j != 1){
                        /* 如果 fatorsMap 中包含过 这个公因子，说明 这个公因子之前一定有位置有了，那么直接联合两个位置 */
                        if (!fatorsMap.containsKey(j))
                            fatorsMap.put(j, i);
                        else {
                            /* 直接联合 */
                            unionFind.union(fatorsMap.get(j), i);
                        }
                    }
                    /* 计算出一个 可以整除的值， 那么得到的除数也一定可以被整除，继续判断另一个数子 */
                    int another = num / j;
                    if (another != 1){

                        if (!fatorsMap.containsKey(another)){
                            fatorsMap.put(another, i);
                        }else {
                            unionFind.union(fatorsMap.get(another), i);
                        }

                    }
                }
            }
        }
        return unionFind.maxSize();
    }


    public static class UnionFind {
        public int[] parents;
        public int[] sizes;

        public UnionFind(int len) {
            parents = new int[len];
            sizes = new int[len];

            for (int i = 0; i < len; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        public void union(int i1, int i2) {
            /* 看看两个数字是否在一个集合中 */
            int father1 = findFather(i1);
            int father2 = findFather(i2);
            if (father1 != father2){
                /* 如果不是，就把两个人合成一个爹 */
                int f1Size = sizes[father1];
                int f2Size = sizes[father2];
                /* 小挂大 */
                int big = f1Size > f2Size ? father1 : father2;
                int small = big == father1 ? father2 : father1;

                parents[small] = big;
                sizes[big] = f1Size + f2Size;
            }
        }

        /* 找最头上的爹 */
        private int findFather(int index) {
            int father = parents[index];
            /* 这里用 ArrayList 记录找爹的路径 */
            ArrayList<Integer> path = new ArrayList<>();
            while (father != index){
                path.add(index);
                index = father;
                father = parents[index];
            }
            /* 把沿途找过的路径的爹 都改成一个爹，最头上的爹 */
            int size = path.size();
            for (int i = 0; i < size; i++) {
                parents[path.get(i)] = father;
            }
            return father;
        }

        /* 返回最大的集合中有多少个单元 */
        public int maxSize() {
            int ans = 0;
            for (int i = 0; i < sizes.length; i++) {
                if (sizes[i] > ans)
                    ans = sizes[i];
            }
            return ans;
        }
    }
}
