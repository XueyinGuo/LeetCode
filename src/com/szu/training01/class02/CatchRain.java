package com.szu.training01.class02;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      接雨水
 *
 * @Date 2021/4/10 14:48
 */

public class CatchRain {

    public int trap(int[] height) {
        if (height == null || height.length < 2)
            return 0;

        int left = 0;
        int right = height.length - 1;
        // 先ra瓶颈 = 两边最小的那个
        int bottleNeck = Math.min( height[left], height[right] );
        int rain = 0;
        // 双指针右移
        while (left != right){
            /*
            * 每次只关注 两边比较小的那个部分前移或者后退
            * */
            if (height[left] <= height[right]){
                // 如果左边高度比较小就让左边前移一个，计算左边前一个位置的盛水量
                left++;
                int cap = bottleNeck - height[left];
                rain += (cap > 0 ? cap : 0);
                // 看是否需要更新 瓶颈值
                if (height[left] > bottleNeck)
                    bottleNeck = Math.min( height[left], height[right] );

            }else {
                // 如果右边高度比较小就让右边后移一个，计算盛水量
                right--;
                int cap = bottleNeck - height[right];
                rain += (cap > 0 ? cap : 0);

                if (height[right] > bottleNeck)
                    bottleNeck = Math.min( height[left], height[right] );

            }
        }
        return rain;
    }

}
