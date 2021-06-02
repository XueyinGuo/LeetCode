package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 127. 单词接龙
字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：

序列中第一个单词是 beginWord 。
序列中最后一个单词是 endWord 。
每次转换只能改变一个字母。
转换过程中的中间单词必须是字典 wordList 中的单词。
给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。
如果不存在这样的转换序列，返回 0。
 *
 * @Date 2021/5/30 16:52
 */

import java.util.*;

public class L127_WordLadder {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;
        /*
         * 首先需要先找到每个 word 的邻居
         * */
        wordList.add(beginWord);
        HashMap<String, List<String>> neighborListMap = getAllWordsNeighbors( wordList);
        /*
         * 其次计算出每个位置到开头的距离，而且需要有去重的计算
         * 需要使用宽度优先遍历，来保证每个字符串遇到第一次的时候，
         * 都是他到开始单词的最短编辑距离
         * */
        return bfs(beginWord, neighborListMap, endWord);
        /*
         * 第一次遇到endWord的时候就可以返回了
         * 如果一直没碰到再返回0
         * */
    }

    private static int bfs(String beginWord, HashMap<String, List<String>> neighborListMap, String endWord) {
        HashMap<String, Integer> distanceMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        distanceMap.put(beginWord, 1);
        queue.add(beginWord);

        while (!queue.isEmpty()){

            String poll = queue.poll();
            Integer distanceToMyself = distanceMap.get(poll);
            List<String> polledStringNeighbors = neighborListMap.get(poll);
            for (String curNeighbor : polledStringNeighbors) {
                if (curNeighbor.equals(endWord))
                    return distanceToMyself + 1;
                if (!distanceMap.containsKey(curNeighbor)){
                    distanceMap.put(curNeighbor, distanceToMyself + 1);
                    queue.add(curNeighbor);
                }

            }
        }
        return 0;
    }

    private static HashMap<String, List<String>> getAllWordsNeighbors( List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        HashMap<String, List<String>> neighborMap = new HashMap<>();
        for (String curWord : wordList) {

            char[] str = curWord.toCharArray();
            List<String> neighborList = getWordNeighbors(str, wordSet);
            neighborMap.put(curWord, neighborList);
        }
        return neighborMap;
    }

    private static List<String> getWordNeighbors(char[] str, HashSet<String> wordSet) {
        List<String> res = new LinkedList<>();
        for (int i = 0; i < str.length; i++) {
            char tem = str[i];
            for (char k = 'a'; k <= 'z'; k++) {
                if (k == tem) continue;
                str[i] = k;
                String cur = String.valueOf(str);
                if (wordSet.contains(cur)){
                    res.add(cur);
                }
            }
            str[i] = tem;
        }
        return res;
    }

    public static void main(String[] args) {
        String begin = "hit";
        String end = "cog";
        String[] words = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList = new LinkedList<>();
        Collections.addAll(wordList, words);
        System.out.println(ladderLength(begin, end, wordList));
    }
}
