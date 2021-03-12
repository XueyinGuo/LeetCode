package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/14 0:29
 */

import java.util.ArrayList;
import java.util.List;

public class L14_LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return null;
        }
        int minLen = Integer.MAX_VALUE;
        List<char[]> charList = new ArrayList<>();
        for(int i=0; i<strs.length; i++){
            char[] chars = strs[i].toCharArray();
            minLen = Math.min(chars.length, minLen);
            charList.add( chars );
        }
        char[] res = new char[minLen];
        boolean flag = false;
        for(int i = 0; i<minLen;i++){
            char cur1 = charList.get(0)[i];
            for(int j = 1;j<charList.size(); j++){
                char cur2 = charList.get(j)[i];
                if(cur1 != cur2){
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }
            res[i] = cur1;
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        String[] strings = {"flower", "flow", "flight"};
        System.out.println(new L14_LongestCommonPrefix().longestCommonPrefix(strings));
    }
}
