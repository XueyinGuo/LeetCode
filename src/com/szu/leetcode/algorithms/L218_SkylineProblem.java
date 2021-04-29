package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 218. 天际线问题
    城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。

    每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：

    lefti 是第 i 座建筑物左边缘的 x 坐标。
    righti 是第 i 座建筑物右边缘的 x 坐标。
    heighti 是第 i 座建筑物的高度。
    天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。
    关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。
    此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。

    注意：输出天际线中不得有连续的相同高度的水平线。
    例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；
    三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 *
 * @Date 2021/4/29 15:15
 */

import java.util.*;

public class L218_SkylineProblem {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0)
            return new ArrayList<>();
        int length = buildings.length;
        /*
         * 把大楼的区间高度抽象化，
         * 大楼起始点相当于 在 index 位置 加上一个值
         * 大楼的结束点相当于在 index 位置减去一个 高度
         * */
        Op ops[] = new Op[length * 2];
        for (int i = 0; i < length; i++) {
            ops[2 * i] = new Op(buildings[i][0], buildings[i][2], true);
            ops[2 * i + 1] = new Op(buildings[i][1], buildings[i][2], false);
        }
        Arrays.sort(ops, new Comparator<Op>() {
            /*
             * 按照每个位置的操作顺序来排序
             * 如果操作位置一样，让所有的 操作按照 先上涨 再降落来排列，
             * 用以防止出现纸片形状的大楼，
             * */
            public int compare(Op o1, Op o2) {
                if (o1.index != o2.index)
                    return o1.index - o2.index;
                else if (o1.isUp != o2.isUp)
                    return o1.isUp ? -1 : 1;
                return 0;
            }
        });
        /* 用来存放 高度出现的次数 */
        TreeMap<Integer, Integer> heightTimesMap = new TreeMap<>();
        /* 用来存放结果，每个有高度变化的 index 位置对应的 高度是什么 */
        TreeMap<Integer, Integer> indexHeightMap = new TreeMap<>();
        Integer lastHigh = 0;
        int i = 0;
        while (i < ops.length) {
            Op cur = ops[i];
            Integer times = heightTimesMap.get(cur.height);
            // 楼起来的操作
            if (cur.isUp) {
                if (times == null)  // 之前出现过这个高度
                    heightTimesMap.put(cur.height, 1);
                else
                    heightTimesMap.put(cur.height, times + 1);
            } else {
                // 楼正好到了边界线，需要减去一个高度
                if (times == 1)  // 这样高的楼房高度最后一个出现了，现在就没有这么高的了
                    heightTimesMap.remove(cur.height);
                else
                    heightTimesMap.put(cur.height, times - 1);//出现次数 - 1
            }
            /* 同一个位置的所有操作结束，结算这个位置的最高高度，如果最高高度没变，那么这个轮廓线不再这里断开，也就是不做任何操作 */
            if (!heightTimesMap.isEmpty()) {
                Integer highest = heightTimesMap.lastKey();
                if (highest != lastHigh) {
                    lastHigh = highest;
                    indexHeightMap.put(cur.index, highest);
                }
            }else
                indexHeightMap.put(cur.index, 0); /* map 空的时候，就是所有的建筑都没了的时候，或者这一波没了，最后一个操作的位置 设置为 0 高度 */
            i++;
        }
//        indexHeightMap.put(ops[ops.length-1].index, 0);
        /*
        * 最后收集答案，每个 index 的值都被覆盖了，只留下唯一的一个最高高度
        * */
        List<List<Integer>> res = new ArrayList<>();
        Iterator<Map.Entry<Integer, Integer>> iterator = indexHeightMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            List<Integer> list = new ArrayList<>();
            list.add(next.getKey());
            list.add(next.getValue());
            res.add(list);
        }
        return res;
    }

    class Op {
        int index;
        int height;
        boolean isUp;

        public Op(int startIndex, int height, boolean isUp) {
            this.index = startIndex;
            this.height = height;
            this.isUp = isUp;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,38,219},{2,19,228},{2,64,106},{3,80,65},{3,84,8},{4,12,8},{4,25,14},{4,46,225},{4,67,187},{5,36,118},{5,48,211},{5,55,97},{6,42,92},{6,56,188},{7,37,42},{7,49,78},{7,84,163},{8,44,212},{9,42,125},{9,85,200},{9,100,74},{10,13,58},{11,30,179},{12,32,215},{12,33,161},{12,61,198},{13,38,48},{13,65,222},{14,22,1},{15,70,222},{16,19,196},{16,24,142},{16,25,176},{16,57,114},{18,45,1},{19,79,149},{20,33,53},{21,29,41},{23,77,43},{24,41,75},{24,94,20},{27,63,2},{31,69,58},{31,88,123},{31,88,146},{33,61,27},{35,62,190},{35,81,116},{37,97,81},{38,78,99},{39,51,125},{39,98,144},{40,95,4},{45,89,229},{47,49,10},{47,99,152},{48,67,69},{48,72,1},{49,73,204},{49,77,117},{50,61,174},{50,76,147},{52,64,4},{52,89,84},{54,70,201},{57,76,47},{58,61,215},{58,98,57},{61,95,190},{66,71,34},{66,99,53},{67,74,9},{68,97,175},{70,88,131},{74,77,155},{74,99,145},{76,88,26},{82,87,40},{83,84,132},{88,99,99}};
        L218_SkylineProblem test = new L218_SkylineProblem();
        List<List<Integer>> skyline = test.getSkyline(matrix);
        System.out.println();
    }
}
