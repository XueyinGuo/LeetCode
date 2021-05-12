package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 42. 接雨水
 *
 * @Date 2021/3/12 18:55
 */

public class L42_CatchRain {
    public int trap(int[] height) {
        int n = height.length;
        int ans = 0;
        // 暴力解， 每个柱子都去找左右边的最高柱子
        for(int i = 1; i<n-1;i++){
            int l_max = 0, r_max = 0;
            // 找到 i 柱子的左边的最高柱子
            for(int j = i; j<n; j++){
                r_max = Math.max(r_max, height[j]);
            }
            // 找到 i 柱子的又边的最高柱子
            for(int j = i; j>=0; j--){
                l_max = Math.max(l_max, height[j]);
            }
            // 这个地方能装的最多雨水就是， 两边最大值较小的那个 - 自己高度
            ans += Math.min(l_max, r_max) - height[i];
        }
        return ans;
    }

    public int trap2(int[] height) {
        int n = height.length;
        int ans = 0;
        // 记录左边最大值的辅助数组
        int[] l_max = new int[height.length];
        // 记录右边最大值的辅助数组
        int[] r_max = new int[height.length];
        // 两个数组都进行初始化自己，l_max[0] 左边肯定没有比他更大的值，所以他初始化为 高度本身
        l_max[0] = height[0];
        r_max[n-1] = height[n-1];
        // 不断比较当前高度和左边的最大值，保证当前位置存储的是这根柱子左边的最大值，如果自己比他高，这个地方肯定解不了水，就可以设置值为自己
        for (int i = 1; i < n; i++) {
            l_max[i] = Math.max(height[i], l_max[i-1]);
        }
        for (int i = n-2; i >= 0; i--) {
            r_max[i] = Math.max(height[i], r_max[i+1]);
        }
        for (int i = 1; i < n-1; i++) {
            ans += Math.min(l_max[i], r_max[i]) - height[i];
        }
        return ans;
    }
}
