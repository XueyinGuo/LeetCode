package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *          6. Z 字形变换
            将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。

            比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：

            P   A   H   N
            A P L S I I G
            Y   I   R
            之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

            https://leetcode-cn.com/problems/zigzag-conversion/
 *
 * @Date 2021/3/23 22:57
 */

import java.util.ArrayList;

public class L6_Convert {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        new L6_Convert().convert(s, numRows);
    }

    public String convert(String s, int numRows) {
        if(s == null || s.length() == 0) new String();
        // 准备一个 numRows 行的 ArrayList 数组
        ArrayList[] lists =  new ArrayList[numRows];
        for (int i = 0; i < numRows; i++) {
            lists[i] = new ArrayList<Character>();
        }
        char[] str = s.toCharArray();
        // 这个东西负责控制数组向上还是向下移动，控制 |/| 的移动轨迹
        boolean reverse = false;
        int i = -1;
        int charIndex = 0;
        while(charIndex < str.length){
            // 当 i = 0的时候，说明 应该向下移动了
            if(i == 0 || i == -1){
                i++;
                reverse = false;
            }
            // 当 i = numRows 的时候说明 应该向上移动了
            if(i == numRows){
                reverse = true;
                i--;
            }
            // 通过向上还是向下移动的变量，判断应该往哪个位置的 ArrayList 中放入 字符
            if(reverse){
                i--;
                lists[i].add(str[charIndex++]);
            }else{
                lists[i].add(str[charIndex++]);
                i++;
            }
        }
        // 最后从上到下收集答案
        StringBuffer sb = new StringBuffer();
        for(int k = 0; k<numRows; k++){
            for(int j = 0; j<lists[k].size(); j++){
                sb.append( lists[k].get(j) );
            }
        }
        return sb.toString();
    }
}
