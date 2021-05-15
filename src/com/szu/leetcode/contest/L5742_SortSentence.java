package com.szu.leetcode.contest;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 5742. 将句子排序 显示英文描述
一个 句子 指的是一个序列的单词用单个空格连接起来，且开头和结尾没有任何空格。每个单词都只包含小写或大写英文字母。
我们可以给一个句子添加 从 1 开始的单词位置索引 ，并且将句子中所有单词 打乱顺序 。

比方说，句子 "This is a sentence" 可以被打乱顺序得到 "sentence4 a3 is2 This1" 或者 "is2 sentence4 This1 a3" 。
给你一个 打乱顺序 的句子 s ，它包含的单词不超过 9 个，请你重新构造并得到原本顺序的句子。


示例 1：

输入：s = "is2 sentence4 This1 a3"
输出："This is a sentence"
解释：将 s 中的单词按照初始位置排序，得到 "This1 is2 a3 sentence4" ，然后删除数字。
 *
 * @Date 2021/5/15 22:31
 */


public class L5742_SortSentence {

    public String sortSentence(String s) {
        /*
        * 根据空格切割出所有的子串
        *
        * 然后申请一个等长的 字符串数组
        *
        * 每个切下来的字符串从后往前遍历，查看是位于哪个位置的单词
        * 然后放置到正确顺序的数组中
        *
        * 最后用一个 sb 收集答案
        * */
        String[] strs = s.split(" ");
        String[] wordList = new String[strs.length];
        for (int i = 0; i < strs.length; i++) {
            char[] str = strs[i].toCharArray();
            int j = str.length - 1;
            int index = 0;
            while (j >= 0 && str[j] >= '0' && str[j] <= '9') {
                index = index * 10 + str[j--] - '0';
            }

            wordList[index - 1] = String.valueOf(str, 0, j + 1);
        }
        StringBuffer sb = new StringBuffer();
        for (String s1 : wordList) {
            sb.append(s1 + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    public static void main(String[] args) {
        String s = "is2 sentence4 This1 a3";
        L5742_SortSentence test = new L5742_SortSentence();
        test.sortSentence(s);
    }
}
