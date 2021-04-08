package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *          394. 字符串解码
 *              https://leetcode-cn.com/problems/decode-string/
 * @Date 2021/3/23 23:25
 */

public class L394_DecodeString {

    public static void main(String[] args) {
        String s = "3[a2[c]]";
        new L394_DecodeString().decodeString(s);
    }

    public String decodeString(String s) {
        if(s == null || s.length() == 0)
            return new String();
        char[] chars = s.toCharArray();
        int i = 0;
        // 存放结果的字符缓冲区
        StringBuffer sb = new StringBuffer();
        while( i<chars.length ){
            // 有可能数字不止一位长，一直解析到数字结束，用来做重复次数
            StringBuffer numSb = new StringBuffer();
            while (chars[i] - '0' >= 0 && chars[i]-'0' <= 9)
                numSb.append(chars[i++]);
            int num = 0;
            if(numSb.length() > 0)
                num = Integer.valueOf(numSb.toString());
            // 如果碰见了 '['意味着要开始解码了
            if(chars[i] == '['){
                // Info 类型里边放着 解析出来的字符串，和 i 应该放到的位置，因为解析完的字符串不需要append了，则需要直接跳过
                Info info = decode(chars, i+1);
                int n = 0;
                // 重复的次数就是刚刚 解析到的数字的长度， numSb中存储的
                while( n < num ){
                    sb.append( info.str );
                    n++;
                }
                i = info.index;
            // 普通字符，直接append
            }else
                sb.append(chars[i++]);
        }
        return sb.toString();
    }

    public Info decode( char[] chars, int i ){
        StringBuffer sb = new StringBuffer();

        while(i < chars.length ){
            StringBuffer numSb = new StringBuffer();
            while (chars[i] - '0' >= 0 && chars[i]-'0' <= 9)
                numSb.append(chars[i++]);

            int num = 0;
            if(numSb.length() > 0)
                num = Integer.valueOf(numSb.toString());
            if(chars[i] == '['){
                Info info = decode(chars, i+1);
                int n = 0;

                while( n < num ){
                    sb.append( info.str );
                    n++;
                }
                i = info.index;
            }else if(chars[i] == ']'){
                return new Info(sb.toString(), i+1);
            }else{
                sb.append(chars[i++]);
            }

        }
        return null;
    }

}
class Info{
    String str;
    int index;

    public Info(String str, int index){
        this.str = str;
        this.index = index;
    }
}