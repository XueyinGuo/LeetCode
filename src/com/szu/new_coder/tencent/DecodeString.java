package com.szu.new_coder.tencent;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 21:32
 */

import java.util.Scanner;

public class DecodeString {

    public static void main(String[] args) {
        String s = new Scanner(System.in).next();
        char[] chars = s.toCharArray();
        String res = deCompose(chars, 0);
        System.out.println(res);
    }
    public static String deCompose(char[] chars, int index){
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while(i<chars.length){
            if(chars[i] == '['){
                Info info = deComposeCycle(chars, i+1);
                sb.append(info.append);
                i = info.index;
            }else{
                sb.append(chars[i]);
                i++;
            }

        }
        return sb.toString();
    }


    public static Info deComposeCycle(char[] chars, int index){
        StringBuffer numBuffer = new StringBuffer();
        while(chars[index] - '0' >= 0 && chars[index] - '0' <= 9){
            numBuffer.append(chars[index]);
            index++;
        }
        int num = Integer.valueOf(numBuffer.toString());
        StringBuffer sb = new StringBuffer();
        int returnIndex = 0;
        int i = index+1;
        while(i<chars.length){
            if(chars[i] == ']'){
                StringBuffer dupSb = new StringBuffer();
                while(num > 0){
                    dupSb.append(sb);
                    num--;
                }
                returnIndex = i+1;
                return new Info(dupSb.toString(), returnIndex);
            }else if(chars[i] == '['){
                Info info = deComposeCycle(chars, i + 1);
                sb.append(info.append);
                i = info.index;
            }else{
                sb.append(chars[i]);
                i++;
            }

        }
        return null;
    }

}
class Info{
    String append;
    int index;
    public Info(String append, int index){
        this.append = append;
        this.index = index;
    }
}