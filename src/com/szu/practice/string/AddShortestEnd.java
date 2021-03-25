package com.szu.practice.string;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      添加最少的字符串使得整个字符串变成一个回文子串
 *
 *      【马拉车】
 *
 * @Date 2021/3/22 20:42
 */

public class AddShortestEnd {
    public static void main(String[] args) {
        String s = "abdd";
        String maxSubContainsLastChar = getMaxSubContainsLastChar(s);
        System.out.println(maxSubContainsLastChar);
    }

    private static String getMaxSubContainsLastChar(String s) {

        char[] str = Manacher.insertDummyChar(s);
        int[] radius = new int[str.length];
        int rightBorder = -1;
        int center = -1;
        for (int i = 0; i < str.length; i++) {

            if (i < rightBorder)
                radius[i] = Math.min( radius[ 2 * center - i ], rightBorder-i );
            else
                radius[i] = 1;

            while (i + radius[i] < str.length && i - radius[i] >= 0){
                if ( str[i + radius[i]] == str[i - radius[i]])
                    radius[i]++;
                else
                    break;
            }

            if (radius[i] + i > rightBorder){
                rightBorder = radius[i] + i;
                center = i;
            }
            if (rightBorder == str.length-1)
                break;
        }
        int startIndex = rightBorder - center;
        StringBuffer sb = new StringBuffer();
        for (int i = s.length()-startIndex; i >= 0; i--) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}
