package com.szu.training.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      有一些排成一行的正方形。每个正方形都已经被染成了红色或者绿色，
 *      现在需要对任意一个正方形进行染色，染完色之后使得每个红色 R 都比每个绿色 的 G
 *      距离左侧位置更近，返回需要最少染色几个正方形才能达成目标
 *
 * @Date 2021/4/10 11:53
 */

import java.util.Random;

public class PaintColor {
    /*
     * 我自己的思路
     * */
    public static int paint(String s){
        char[] str = s.toCharArray();
        /*
         * 首先计算完成整个字符串中所有的 R G 数量
         * */
        int totalR = 0;
        int totalG = 0;
        int minPaint = Integer.MAX_VALUE;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'G')
                totalG++;
            if (str[i] == 'R')
                totalR++;
        }
        /*
         *  每次以 i 为分割线，
         * i 左边的 红色不用管
         * i 右边的 绿色不用管
         * 也就是说 i 左边的 绿色 统一染色为 红色， i 左边的 需要变动的数量 就是 totalG - restG （总绿色数量 - 剩余绿色数量）
         * i 右边的 红色 需要统一编程绿色，变动数量为 restR（剩余红色数量）
         * */
        int restR = totalR;
        int restG = totalG;

        minPaint = Math.min(restR + totalG - restG, minPaint);

        for (int i = 0; i < str.length; i++) {
            if(str[i] == 'G' )
                restG-- ;
            else
                restR--;
            minPaint = Math.min(restR + totalG - restG, minPaint);
        }
        return minPaint;
    }

    /*
    * 大神的正确答案
    * */
    public static int minPaint(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int rAll = 0;
        for (int i = 0; i < N; i++) {
            rAll += str[i] == 'R' ? 1 : 0;
        }
        int ans = rAll; // 如果数组所有的范围，都是右侧范围，都变成G
        int left = 0;
        for (int i = 0; i < N - 1; i++) { // 0..i 左侧 n-1..N-1
            left += str[i] == 'G' ? 1 : 0;
            rAll -= str[i] == 'R' ? 1 : 0;
            ans = Math.min(ans, left + rAll);
        }
        // 0...N-1 左全部 右无
        ans = Math.min(ans, left + (str[N - 1] == 'G' ? 1 : 0));
        return ans;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000000; i++) {
            String s = generateString();
            if (paint(s) != minPaint(s))
                System.out.println("FUCK");
        }
    }

    public static String generateString(){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        int len = random.nextInt(30);
        for (int i = 0; i < len; i++) {
            if (random.nextBoolean())
                stringBuffer.append('R');
            else
                stringBuffer.append('G');
        }
        return stringBuffer.toString();
    }
}
