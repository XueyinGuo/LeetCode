package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 56. 合并区间
 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

        示例 1：

        输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
        输出：[[1,6],[8,10],[15,18]]
        解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/merge-intervals
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/5/14 23:14
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class L56_MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return new int[][]{};
        if (intervals.length == 1)
            return intervals;


        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<List<Integer>> resList = new ArrayList<>();
        int i = 0;
        while (i < intervals.length) {

            int j = i + 1;
            while (j < intervals.length && intervals[i][1] >= intervals[j][0]) {
                j++;
            }


            ArrayList<Integer> list = new ArrayList<>();
            list.add(intervals[i][0]);
            list.add(intervals[j - 1][1]);
            resList.add(list);
            i = j;

        }

        int[][] res = new int[resList.size()][2];
        for (int j = 0; j < resList.size(); j++) {
            res[j][0] = resList.get(j).get(0);
            res[j][1] = resList.get(j).get(1);
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] inputMatrix = LeetCodes.getInputMatrix("[[1,4],[4,5]]", 2);
        L56_MergeIntervals test = new L56_MergeIntervals();
        test.merge(inputMatrix);
    }
}
