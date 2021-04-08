package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
        57. 插入区间
        给你一个 无重叠的 ，按照区间起始端点排序的区间列表。

        在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

 * @Date 2021/3/17 11:40
 */

import java.util.ArrayList;

public class L57_Insert {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        ArrayList<int[]> ansList = new ArrayList<>();
        for (int[] interval: intervals ) {
            // 如果 newInterval 【-100， -50】 ->插入  【 【1,3】，【6,9】 】
            // 则直接插入即可
            if (interval[0] > right){
                if (!placed){
                    placed = true;
                    ansList.add(new int[]{left, right});
                }
                // 【1,3】 【6,9】放入结果集
                ansList.add(interval);
            }
            // 如果包含在内， 则也直接插入 原数组即可
            else if (left>=interval[0] && right <= interval[1]){
                placed = true;
                ansList.add(interval);
                // 跟第一个条件正好相反，【4,5】 ->插入  【 【1,3】，【6,9】 】
                // 【1,3】先放入结果集
            }else if (left > interval[1]){
                ansList.add(interval);
            }
            else {
            /* 命中这个条件就是 【2,5】 ->插入  【 【1,3】，【6,9】 】  -> 此时 left 变为 1， right变为 5 */
            /* 或者  【-1,2 】 ->插入  【 【1,3】，【6,9】 】      -> 此时 left 变为 -1， right变为 3 */
            /* 两个区间合并成一个区间，进行下一轮判断 */
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }

        }
        /* 遍历完所有的区间之后，都没能把新合成的区间或者本来就像插入但是跨度巨大的空间加入到结果集中，则在此处加入 */
        // 比如【 【1,3】，【6,9】 】 中 插入 【-1， 20】
        if (!placed)
            ansList.add(new int[]{left, right});

        int ans[][] = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i][0] = ansList.get(i)[0];
            ans[i][1] = ansList.get(i)[1];
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] intervals = {
                {1,5}

        };
        int[][] insert = new L57_Insert().insert(intervals, new int[]{2, 7});
        System.out.println();
    }
}
