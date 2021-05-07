package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 554. 砖墙
你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和相等。

你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。

给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。
 *
 * @Date 2021/5/7 10:12
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class L554_BrickWall {
    /*
    * 最多穿过的砖数为 wall.size()
    *
    * 只需要统计每行砖 到边缘的距离，就可以统计出来 某个距离出现 最多的次数
    *
    * 如何统计砖到边缘的距离呢？ 哈希表存储遍历结果，遍历的时候用一个变量记录累计和就可以了，
    * 这就是砖到边缘的距离
    *
    * 然后，用 size 减去 这个最大的次数【但是不能是砖墙两边的边缘，也就是次之大的次数】，就是答案
    * */
    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() == 0)
            return 0;
        int wallHeight = wall.size();
        //      边缘位置   出现次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < wallHeight; i++) {
            /* 统计每一排的边缘都在哪个位置 */
            List<Integer> yiPai = wall.get(i);
            int len = yiPai.size();
            int edge = 0;
            for (int j = 0; j < len; j++) {
                edge += yiPai.get(j);

                Integer times = map.get(edge);
                if (times == null)
                    map.put(edge, 1);
                else {
                    map.put(edge, times + 1);
                }

            }

        }
        /* 砖墙整整齐齐的每一排都是一块砖杵着的时候，每块砖都是一样的长度，那么只能是全部穿过了 */
        if (map.size() == 1)
            return wallHeight;
        /* 开始找第二大的边缘出现次数了 */
        int largest = 1;
        int secondLarge = 1;
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()){
            Integer times = it.next().getValue();
            if (times >= largest){
                secondLarge = largest;
                largest = times;
            }
        }

        return wallHeight - secondLarge;
    }

}
