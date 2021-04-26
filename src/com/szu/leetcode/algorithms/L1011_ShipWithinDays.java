package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * TODO
 * 1011. 在 D 天内送达包裹的能力
    传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。

    传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。

    返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 * @Date 2021/4/26 11:00
 */

import java.util.TreeMap;

public class L1011_ShipWithinDays {

    public int shipWithinDays(int[] weights, int D) {
        int sum = 0;
//        int[] help = new int[weights.length];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            map.put(sum, i);
        }
        int low = sum / D + 1;
        int high = sum;
        int ans = 0;
        while (low < high){
            int i = 0;
            int cap = low;
            while (i < weights.length){
                Integer index = map.ceilingKey(cap);
                index++;
                cap += low;
                ans++;
            }
            if (ans == D)
                return ans;

            int mid = low + (high - low) / 2;
            if (ans < D)
                low = mid;
            else
                high = mid;


        }
        return ans;
    }

}
