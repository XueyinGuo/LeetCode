package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  179. 最大数
    给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

    注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * @Date 2021/4/20 17:45
 */

import java.util.Arrays;
import java.util.Comparator;

public class L179_MaxNumber {

    public String largestNumber(int[] nums) {
        int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }
        /* 一个比较器 */
        Arrays.sort(numsArr, new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                long start = 10;
                /* 计算两个数字的位数 */
                int weiShu1 = 1;
                int weiShu2 = 1;
                while (start <= i1){
                    start *= 10;
                    weiShu1++;
                }
                start = 10;
                while (start <= i2){
                    start *= 10;
                    weiShu2++;
                }
                /* 得到两个位数之后，分别使用 Long 型数组成对应的数字，进行大小比较 */
                long i1i2 = i1 * (long)Math.pow(10, weiShu2) + i2;
                long i2i1 = i2 * (long)Math.pow(10, weiShu1) + i1;
                if (i1i2 > i2i1)
                    return -1;
                return 1;
            }
        });
        if(numsArr[0] == 0)
            return "0";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numsArr.length; i++) {
            sb.append(numsArr[i]);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        int[] arr = {999999998,999999997,999999999};
        new L179_MaxNumber().largestNumber(arr);
    }

}
