package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *
 *
 * @Date 2021/5/21 23:26
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class L49_GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String curStr : strs) {

            int[] chars = new int[26];

            char[] str = curStr.toCharArray();
            for (int i = 0; i < str.length; i++) {
                chars[str[i] - 'a']++;
            }

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                while (chars[i] != 0) {
                    sb.append(i + 'a');
                    chars[i]--;
                }
            }
            String sortedString = sb.toString();
            List list = map.get(sortedString);
            if (list == null) {
                ArrayList<String> strList = new ArrayList<>();
                strList.add(curStr);
                map.put(sortedString, strList);
            } else
                list.add(curStr);
        }
        return new ArrayList<List<String>>(map.values());
    }


    public static void main(String[] args) {
        String[] strings =
                {"eat", "tea", "tan", "ate", "nat", "bat"};
        L49_GroupAnagrams test = new L49_GroupAnagrams();
        test.groupAnagrams(strings);
    }
}
