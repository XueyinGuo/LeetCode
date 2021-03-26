package com.szu.practice.l08_string;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      马拉车，查找最长回文子串
 *
 * @Date 2021/3/22 17:22
 */

import com.szu.leetcode.utils.LeetCodes;

public class Manacher {

    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {
            String randomString = LeetCodes.getRandomString(50);
            int manacher = manacher(randomString);
            int lenViolence = getLenViolence(randomString);
            if (manacher != lenViolence)
                System.out.println(manacher == lenViolence);
        }

    }

    private static int manacher(String s) {
        // "12132" -> "#1#2#1#3#2#"
        char[] str = insertDummyChar(s);
        /*
        * 之前计算过的位置的回文串的半径数组
        * */
        int[] radius = new int[str.length];
        int center = -1;
        int rightBorder = -1;
        int max = Integer.MIN_VALUE;
        /*
         * 一 ：  i 在 rightBorder 内：
         *      但是 radius[i] 加速半径分为三种情况讨论：
         *
         *        1.【对称点位置的回文字符的左边界】 在 【当前center回文串的左边界【内】】
         *        str = a  [c  【a  b  a】  d  e  g  e  d  a  b  a  c]  f
         *                 |       |             |           |      |
         *               左边界   对称         center         i      右边界
         *            此时 i 都不用计算，直接赋值 对称点回文半径值
         *
         *        2.【对称点位置的回文字符的左边界】 在 【当前center回文串的左边界【上】】
         *             str =   a [【a  b  a】  d  e  g  e  d  a  b  a]  f
         *                       |    |             |           |   |
         *                     左边界  对称         center        i   右边界
         *
         *                  此时 i 可以跳过的长度为对称点的半径长度，可以直接比对右边界之外的值 与 其关于i对称的位置的值是否相等即可
         *
         *
         *
         *        3.【对称点位置的回文字符的左边界】 在 【当前center回文串的左边界【外】】
         *            str =   (a   [b   c   d   c   b   a)   t   k   t   a   b   c   d   c   b]   y
         *                    |     |       |            |       |                   |        |
         *                  i'左边  |        i'         i'右边    |                   i        |
         *                    center的左边界                      center                      center右边界
         *
         *           此时 i 的半径直接赋值为到center右边界的距离
         *
         * 二 ： i 在 右边界 之外
         * */

        for (int i = 0; i < str.length; i++) {
            if (rightBorder > i){
                /* 【2 * center - i】 的位置 就是 i 关于 center 对称点的坐标 */
                /*
                 * 先让 radius[i] 赋值成为 应该跳过的最短长度，这句能命中 1， 3 两种情况，因为 2 还得继续计算一下边界之外的值，反正至少都是能
                 * 跳过到 rightBorder 或者 对称点的回文半径最小值得长度 的
                 * */
                radius[i] = Math.min( radius[2 * center-i], rightBorder-i );
            }else {
                /* i 在 rightBorder 外，暴力解去吧 */
                radius[i] = 1;
            }
            while ( i + radius[i] < str.length && i - radius[i] > -1 ){

                if ( str[i + radius[i]] == str[i - radius[i]] )
                    radius[i]++;
                else
                    break;;

            }
            /* 如果扩张到了比现在的 边界更右边的位置    就更新信息 */
            if ( i + radius[i] > rightBorder ){
                rightBorder = i + radius[i];
                center = i;
            }
            max = Math.max(radius[i] - 1, max);
        }
        return max;
    }

    public static char[] insertDummyChar(String s) {
        int length = s.length();
        char[] res = new char[length * 2 + 1];
        res[0] = '#';
        int index = 1;
        int strIndex = 0;
        while (index < res.length - 1){
            res[index++] = s.charAt(strIndex++);
            res[index++] = '#';
        }
        return res;
    }

    public static int getLenViolence(String s){
        char[] str = insertDummyChar(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int right = i+1;
            int left = i-1;
            int curLen = 1;
            while (right < str.length && left >= 0){

                if (str[right] == str[left]){
                    curLen+=2;
                    left--;
                    right++;
                }else
                    break;

            }
            max = Math.max(curLen, max);
        }
        /* 因为这是插了冗余字符的字符串的最长字串的长度，所以稿纸上计算一下可知，这就是正确答案 */
        return max/2;
    }

}
