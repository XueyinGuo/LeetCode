package com.szu.leetcode.greedy;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description

        605. 种花问题

        假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。

        给你一个整数数组flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数n ，
        能否在不打破种植规则的情况下种入n朵花？能则返回 true ，不能则返回 false。
        
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/can-place-flowers
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
        
 * @Date 2021/2/17 20:03
 */

public class CanPlaceFlowers_605 {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        return doJudge(flowerbed, n, 0);
    }

    public boolean doJudge(int[] flowerbed, int n, int index) {

        // 花都种完了，不管有没有到头，都返回true
        if( n == 0){
            return true;
        }

        // 判断当前位置是否已经超出花坛长度
        // 如果超出了，而且跳到这里肯定还剩余花朵
        // 所以直接返回false
        if(index >= flowerbed.length ){
            return false;
        }
        // 当前花坛位置有花，二话不说看 index + 2 位置，而且剩余花数量不变
        if(flowerbed[index] == 1){
            return doJudge(flowerbed, n, index + 2);
        }
        else{
            // 当前位置没有花，但是 下一个位置有花也不能种花，直接到 index + 3 位置
            // 为了防止数组越界，需要判断一下先
            if(index < flowerbed.length - 1){
                if(flowerbed[index+1] == 1){
                    return doJudge(flowerbed, n, index + 3);
                }
            }
            // 能来到这里就是 当前位置不是花，下一个位置也不是花，能种！
            // 种上花之后去 index + 2 位置继续判断
            return doJudge(flowerbed, n - 1, index + 2);
        }
    }

}
