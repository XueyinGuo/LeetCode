package com.szu.new_coder.byte_dance;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *          TODO 没做出来
 * @Date 2021/3/11 18:16
 */

import java.util.Scanner;

public class Editor{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int examples = in.nextInt();
        while(examples > 0){
            String s = in.next();
            s = correct(s);
            System.out.println(s);
            examples--;
        }
    }

    public static String correct(String s){
        char[] chars = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        char first = '0', second = '2', third='0', fourth='1';
        int resIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            fourth = chars[i];
            if (i >= 1) third = chars[i - 1];
            if (i >= 2) second = chars[i-2];
            if (i >= 3) first = chars[i-3];

            if (fourth == third && second == third){
                continue;
            }else if (first == second && third == fourth){
                continue;
            }else {
                sb.append(fourth);
//                if (i >= 1)
//                    res[resIndex++] = second;
//                if (i >= 2)
//                res[resIndex++] = third;
//                if (i >= 3)
//                    res[resIndex++] = fourth;
            }
        }
        return sb.toString();
    }
}