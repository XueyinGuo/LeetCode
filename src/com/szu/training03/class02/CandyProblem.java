package com.szu.training03.class02;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 135. 分发糖果
    老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。

    你需要按照以下要求，帮助老师给这些孩子分发糖果：

    每个孩子至少分配到 1 个糖果。
    评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
    那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * @Date 2021/5/6 15:10
 */

import java.util.PriorityQueue;

public class CandyProblem {
    /*
     * 解题思路：
     * 找到每个上坡
     *
     * 坡底从 1 开始计算，上坡 + 1
     * 一旦开始下降 直接减成 1 重新开始
     *
     * 正向反向遍历两次
     *
     * 两个辅助数组求最大值
     * */
    public int candy(int[] rank) {
        if (rank == null || rank.length == 0)
            return 0;
        int length = rank.length;
        int[] help1 = new int[length];
        int[] help2 = new int[length];
        help1[0] = 1;
        help2[length - 1] = 1;
        for (int i = 1; i < length; i++) {
            if (rank[i] > rank[i - 1])
                help1[i] = help1[i - 1] + 1;
            else
                help1[i] = 1;
        }
        for (int i = length - 2; i >= 0; i--) {
            if (rank[i] > rank[i + 1])
                help2[i] = help2[i + 1] + 1;
            else
                help2[i] = 1;
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            if (help1[i] > help2[i])
                sum += help1[i];
            else
                sum += help2[i];
        }
        return sum;
    }
//    public int candy(int[] ratings) {
//
//        if (ratings == null || ratings.length == 0)
//            return 0;
//
//        PriorityQueue<Info> queue = new PriorityQueue<>((i1, i2) -> {
//            return i1.rank - i2.rank;
//        });
//        int len = ratings.length;
//        for (int i = 0; i < len; i++) {
//            queue.add(new Info(i, ratings[i]));
//        }
//
//        int[] res = new int[len];
//
//        while (!queue.isEmpty()) {
//
//            Info poll = queue.poll();
//            int pi = poll.index;
//            int rank = poll.rank;
//
//
//            res[pi] = 1;
//
//            int largeIndex = -1;
//
//
//            if (pi > 0 && ratings[pi - 1] < rank)
//                res[pi] = res[pi - 1] + 1;
//
//            if (pi < len - 1 && ratings[pi + 1] < rank)
//                res[pi] = res[pi + 1] + 1;
//
//
//        }
//
//        int sum = 0;
//        for (int i = 0; i < res.length; i++) {
//            sum += res[i];
//        }
//        return sum;
//    }
//
//    static class Info {
//        int index;
//        int rank;
//
//        public Info(int index, int rank) {
//            this.index = index;
//            this.rank = rank;
//        }
//    }


    public static void main(String[] args) {
        int[] rank = {1, 3, 4, 5, 2};
        CandyProblem test = new CandyProblem();
        test.candy(rank);
    }



}
