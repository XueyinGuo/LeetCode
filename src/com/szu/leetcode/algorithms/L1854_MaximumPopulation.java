package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 1854. 人口最多的年份
给你一个二维整数数组 logs ，其中每个 logs[i] = [birthi, deathi] 表示第 i 个人的出生和死亡年份。

年份 x 的 人口 定义为这一年期间活着的人的数目。第 i 个人被计入年份 x 的人口需要满足：x 在闭区间 [birthi, deathi - 1] 内。注意，人不应当计入他们死亡当年的人口中。

返回 人口最多 且 最早 的年份。
 *
 * @Date 2021/5/10 22:12
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;

public class L1854_MaximumPopulation {

    public int maximumPopulation(int[][] logs) {


        int length = logs.length;
        int[] birth = new int[length];
        int[] dies = new int[length];
        for (int k = 0; k < logs.length; k++) {
            birth[k] = logs[k][0];
            dies[k] = logs[k][1];
        }
        Arrays.sort(birth);
        Arrays.sort(dies);

        int i = 0;
        int j = 0;
        int cur = 0;
        int earlyest = 0;
        int max = 0;
        while (i < length && j < length) {

            if (birth[i] < dies[j]) {
                i++;
                cur++;
            }
            if (cur > max) {
                max = cur;
                earlyest = birth[i - 1];
            }
            if (i == length)
                break;
            if (birth[i] == dies[j]) {
                i++;
                j++;
            }
            if (i == length)
                break;

            if (birth[i] > dies[j]) {
                j++;
                cur--;
            }

        }
        return earlyest;
    }


    public static void main(String[] args) {
        int[][] inputMatrix = LeetCodes.getInputMatrix("[[1993,1999],[2000,2010]]");
        L1854_MaximumPopulation test = new L1854_MaximumPopulation();
        System.out.println(test.maximumPopulation(inputMatrix));
    }
}
