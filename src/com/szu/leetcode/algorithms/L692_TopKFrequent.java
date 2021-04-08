package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *      692. 前K个高频单词
 *      给一非空的单词列表，返回前 k 个出现次数最多的单词。

        返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。


        示例 1：

        输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
        输出: ["i", "love"]
        解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
            注意，按字母顺序 "i" 在 "love" 之前。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/top-k-frequent-words
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/3/17 18:30
 */

import java.util.*;
// 每个字符串包装成  【字符串 + 出现次数 】
class WordsInfo {
    String s;
    int freq;

    public WordsInfo(String s){
        this.s = s;
        this.freq = 0;
    }

}
public class L692_TopKFrequent {
    public List<String> topKFrequent(String[] words, int k) {
        // 保存字符串和出现次数的 Map
        Map<String, WordsInfo> map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            // 如果 map 中没放进去过该字符串，初始化一个 WordsInfo 对象，放入map
            // 每次如果加同样的字符， 让出现次数 + 1
            WordsInfo cur = map.get(words[i]);
            if(cur == null){
                cur = new WordsInfo(words[i]);
            }
            cur.freq++;
            map.put(words[i], cur);
        }
        Iterator it = map.keySet().iterator();
        // 优先级队列，按照给定的规则进行排序
        PriorityQueue<WordsInfo> queue = new PriorityQueue<>(new Comparator<WordsInfo>(){
            @Override
            public int compare(WordsInfo i1, WordsInfo i2){
                // 如果出现次数不一样，直接按照出现次数排序，出现次数多的在前
                if (i1.freq != i2.freq)
                    return -(i1.freq - i2.freq);
                // 如果出现次数一样，则安排每个字母的字典序排序
                return dictOrder(i1.s, i2.s);
            }

            public int dictOrder(String s1, String s2){

                char[] c1 = s1.toCharArray();
                char[] c2 = s2.toCharArray();
                int i= 0, j=0;
                // 当字母不一样的时候返回应该排序的顺序
                while(i<c1.length && j<c2.length){

                    if(c1[i] != c2[j])
                        return c1[i] - c2[j];
                    i++;
                    j++;

                }
                if(i == c1.length) return -1;
                return 1;
            }
        });
        // 安排排序规则入队
        while(it.hasNext()){
            queue.offer(map.get(it.next()));
        }
        List<String> res = new ArrayList<>();
        // 出队给定的次数个
        for(int i = 0; i<k; i++){
            res.add(queue.poll().s);
        }
        return res;
    }
}
