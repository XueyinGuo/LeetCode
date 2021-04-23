package com.szu.training.class07;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定两个数组组成的 arrx，arry，长度都是 N，代表二位平面上的点，第i个点的x坐标和y左边分别为
 * arrx[i] arry[i],返回求一天直线最多能穿过几个点
 *
 * @Date 2021/4/23 10:13
 */

import com.szu.leetcode.algorithms.L149_MaxPointsOnALine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MaxPointsOneLine {

    public int maxPoints(int[][] points) {
        if (points.length == 0)
            return 0;
        if (points.length <= 2) {
            return points.length;
        }
        Point[] p = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            p[i] = new Point(points[i][0], points[i][1]);
        }
        return maxPoints(p);
    }

    public int maxPoints(Point[] points) {
        /* 每个点都要遍历一次点集，找到和自己重合，共水平线，共垂直线，共斜率的四种点，统计数量 */

        /* 因为斜率是分数，所以为了不损失精度，需要分子分母分开存储 */
        int max = 0;
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int curMax = 0;
            int samePosition = 1;
            int sameX = 0;
            int sameY = 0;
            int sameLine = 0;
            /* 从当前点的下一个 开始遍历 */
            map.clear();
            for (int j = i + 1; j < points.length; j++) {
                /* 重合 */
                if (points[j].x == points[i].x && points[j].y == points[i].y)
                    samePosition++;
                    /* 一条垂直线 */
                else if (points[j].x == points[i].x)
                    sameX++;
                    /* 一条水平线 */
                else if (points[j].y == points[i].y)
                    sameY++;
                else {
                    /* 开始计算斜率 */
                    int x1 = points[i].x;
                    int y1 = points[i].y;
                    int y2 = points[j].y;
                    int x2 = points[j].x;
                    /* 斜率计算公式 */
                    int fenZi = x1 - x2;
                    int fenMu = y1 - y2;
                    /* 获取分子分母的 最大公约数 */
                    int gcd = gcd(fenZi, fenMu);
                    /* 分子分母化简 */
                    fenMu /= gcd;
                    fenZi /= gcd;
                    if (fenZi < 0){
                        fenZi *= -1;
                        fenMu *= -1;
                    }

                    HashMap<Integer, Integer> info = map.get(fenZi);
                    /* 这个分子出现过吗？ */
                    if (info != null){
                        /* 分子出现过，分母出现过吗？ */
                        Integer alreadyShowedTimes = info.get(fenMu);
                        /* 分母也出现过，那么斜率肯定相等了 */
                        if (alreadyShowedTimes != null){
                            info.put(fenMu, alreadyShowedTimes + 1);
                        }else {
                            // 第一次出现这个分母，分母出现次数为 1
                            info.put(fenMu, 1);
                        }

                    }else{
                        /* 第一次出现这个分子 */
                        HashMap<Integer, Integer> newMap = new HashMap<>();
                        newMap.put(fenMu, 1);
                        map.put(fenZi, newMap);
                    }
                }
            }
            /*
             * 以上所有信息遍历一遍结束，该找的斜率，共x 共y 重合 都已经结束
             * 开始 遍历map 找 斜率中点数最多的那个
             * */
            Iterator<Map.Entry<Integer, HashMap<Integer, Integer>>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()){
                HashMap<Integer, Integer> nextMap = iterator.next().getValue();
                Iterator<Map.Entry<Integer, Integer>> nextIterator = nextMap.entrySet().iterator();
                while ( nextIterator.hasNext() ){

                    Map.Entry<Integer, Integer> next = nextIterator.next();
                    Integer times = next.getValue();
                    if ( times > sameLine)
                        sameLine = times;
                }
            }
            curMax = Math.max( sameLine, Math.max( sameX, sameY) );
            curMax += samePosition;
            if (curMax > max)
                max = curMax;
        }
        return max;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        L149_MaxPointsOnALine test = new L149_MaxPointsOnALine();
        int[][] points = {
                {1,1},
                {2,2},
                {3,3}
        };
        test.maxPoints(points);
    }
}
