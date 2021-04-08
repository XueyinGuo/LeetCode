package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      17. 电话号码的字母组合
 *
        给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

        给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * @Date 2021/3/12 16:18
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L17_PhoneNumberLetterCombinations {
    List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0)
            return res;
        // 把字母和电话号码对应关系放入map
        Map<Character, String> phoneMap = new HashMap<Character, String>();
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");
        // 然后拆开电话号码
        char[] numbers = digits.toCharArray();
        // 用于存放组成答案的字符
        StringBuffer sb = new StringBuffer();
        // 开始进行回溯算法
        backTrace(numbers, phoneMap, 0, sb);
        return res;
    }
    /*
    * index 是当前来到第几个电话号码
    * */
    public void backTrace(char[] numbers, Map<Character, String> phoneMap ,int index, StringBuffer sb){
        // 当来到最后一个电话号码的时候，就可以把sb中的数据放入结果集中了
        if(index == numbers.length){
            res.add(sb.toString());
            return;
        }
        // 通过 数字的char类型找到map中对应的字母们
        String s = phoneMap.get(numbers[index]);
        char[] curNumChars = s.toCharArray();
        // 挨个把字母放入sb，进行结果集的组成
        for(int i = 0; i<curNumChars.length; i++){
            sb.append(curNumChars[i]);
            backTrace(numbers, phoneMap, index + 1, sb);
            sb.deleteCharAt(index);
        }

    }
}
