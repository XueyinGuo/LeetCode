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
        /*
         * 想法是 找到下一个区间的开头 比 上一个区间的结尾 小于等于的 直接合并，
         * 但是这样的一个问题就是 不能找到完全包住的
         *
         * 所以接下来的逻辑更简单，继续比较两个 区间的第二个数， 大的那个放入结果集
         * */
        if (intervals == null || intervals.length == 0)
            return new int[][]{};
        if (intervals.length == 1)
            return intervals;


        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o2[1] - o1[1];
                return o1[0] - o2[0];
            }
        });

        List<int[]> arrList = new ArrayList<>();
        int i = 0;
        while (i < intervals.length) {
            /*
            * 目前冲到的最右边界，如果现在来到的区间 小的数字都比 最右边界大，
            *
            * 那么就需要手机答案了
            * */
            int curMaxRight = intervals[i][1];
            int j = i + 1;
            while (j < intervals.length) {
                if (intervals[j][1] > curMaxRight && intervals[j][0] > curMaxRight)
                    break;
                /*
                * 现在来到的其中一个冲过了【或者都没冲过】 最右边界，那么这必定是个重复区间
                * 扩充最右边界的长度
                * */
                curMaxRight = Math.max(curMaxRight, intervals[j][1]);
                j++;
            }
            arrList.add(new int[]{intervals[i][0], curMaxRight});
            i = j;
        }
        int[][] ans = new int[arrList.size()][2];
        for (int j = 0; j < arrList.size(); j++) {
            int[] ints = arrList.get(j);
            ans[j][0] = ints[0];
            ans[j][1] = ints[1];
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] inputMatrix = LeetCodes.getInputMatrix("[[1,4],[0,2],[3,5]]");
        L56_MergeIntervals test = new L56_MergeIntervals();
        int[][] merge = test.merge(inputMatrix);
        System.out.println();
    }
}
