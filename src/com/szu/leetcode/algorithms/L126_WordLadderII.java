package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
126. 单词接龙 II
按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：

每对相邻的单词之间仅有单个字母不同。
转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词。
sk == endWord
给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。
 *
 *
 * @Date 2021/4/16 19:10
 */

import java.util.*;

public class L126_WordLadderII {

    public static void main(String[] args) {
        String start = "hot";
        String end = "dog";
        String[] test = { "hot","dog" };
        List<String> list = new ArrayList<>();
        for (int i = 0; i < test.length; i++) {
            list.add(test[i]);
        }
        List<List<String>> res = findMinPaths(start, end, list);
        for (List<String> obj : res) {
            for (String str : obj) {
                System.out.print(str + " -> ");
            }
            System.out.println();
        }

    }

    public static List<List<String>> findMinPaths(String start, String end, List<String> list) {
        if (!list.contains(end))
            return null;
        /* 首先我需要生成每个列表中字符串的邻居图 */
        list.add(start);
        HashMap<String, ArrayList<String>> nextMap = getNextMap(list);
        /*
        * 然后根据这个图，找到 end 到 start 的最短距离，为什么这个图可以找到最短距离，
        * =================================
        * =================================
        * 因为这是层次遍历，而且带有 set 的层次遍历，
        * 每个字符串第一次出现的时候就已经加进了set
        * 所以当 to 第一次出现的时候，肯定是层数最浅的时候，
        * 所以也就是最短的时候，
        * 然后路径记录在 distance 这个 map 中，
        * map中每个记录的意思就是：距离start的最短距离
        * =================================
        * =================================
        * */
        HashMap<String, Integer> distancesMap = getDistances(nextMap, start);
        /* 开始深度优先遍历，用 path 记录走过的路径，结果记录在 res 中 */
        ArrayList<String > path = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        Integer shortest = distancesMap.get(end);
        getShortestWay(start, end, distancesMap, nextMap, path, res, shortest == null ? 0 : shortest, 0);
        return res;
    }

    /*
    * 深度优先遍历，找到通向 end 的所有最短路径，
    * 最短路径已经在宽度优先遍历生成 distanceMap 的时候保证了
    * */
    private static void getShortestWay(String cur, String end, HashMap<String, Integer> distancesMap,
                                       HashMap<String, ArrayList<String>> nextMap,
                                       ArrayList<String> path, List<List<String>> res,
                                       int level, int curLevel) {
        /* 加快剪枝操作 */
        if (curLevel > level )
            return;
        // 修改现场 + 恢复现场
        path.add(cur);
        // 先从 当前节点到 start 的距离，在邻居图中找下层节点的时候，坚决找 距离开头 长的
        int distanceToStart = distancesMap.get(cur);
        // 当然添加结果了
        if (cur.equals(end))
            res.add( new LinkedList<>(path) );
        else {
            /*
            * 找到 cur 的邻居们
            * */
            ArrayList<String> nextList = nextMap.get(cur);
            for (String s : nextList) {
                /* 遍历邻居的时候，一定找距离start更远的，不能环形找回去 */
                if (distancesMap.get(s) == distanceToStart + 1){
                    getShortestWay(s, end, distancesMap, nextMap, path, res, level, curLevel + 1);
                }
            }
        }
        // 回复现场
        path.remove(path.size() - 1);
    }

    /*
    * 生成最短距离 map
    * */
    private static HashMap<String, Integer> getDistances(HashMap<String, ArrayList<String>> nextMap, String start) {

        HashMap<String, Integer> distanceMap = new HashMap<>();
        // 宽度有限遍历肯定使用队列完成，而且防止每个位置多次进出队列，需要用一个set保证
        Queue<String> queue = new ArrayDeque<>();
        HashSet<String> set = new HashSet<>();
        // 先放入自己到自己的距离 为 0
        distanceMap.put(start, 0);
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()){
            // 弹出 第一个 start
            String poll = queue.poll();
            // 获取到 start 的 邻居图
            ArrayList<String> nextList = nextMap.get(poll);
            // 开始遍历整个邻居图
            for (String s: nextList) {
                /*
                * 当邻居 还没被加入 set 的时候，也就是第一次遇到这个字符串的时候，
                * 加入到 distancesMap中作为最小距离
                * */
                if (!set.contains(s)){
                    /*
                    * 最小距离的计算方式是：从 当前的 distanceMap 中获取到 自己到自己的距离，
                    * +1 就是下一层到自己的距离
                    * */
                    Integer distanceToMySelf = distanceMap.get(poll);
                    distanceMap.put(s, distanceToMySelf + 1);
                    set.add(s);
                    queue.add(s);
                }

            }

        }
        return distanceMap;
    }

    /*
    * 生成每个字符串的 邻居表
    * */
    private static HashMap<String, ArrayList<String>> getNextMap(List<String> list) {
        HashMap<String, ArrayList<String>> nextMap = new HashMap<>();
        Set<String> set = new HashSet<>(list);
        for (String s: list) {
            ArrayList<String> nextList = getNext(s, list, set);
            nextMap.put(s, nextList);
        }
        return nextMap;
    }

    /*
    * 生成每个字符串，编辑距离为 1 ，切包含在 字符列表中的所有字符
    * 生成策略就是：穷举每个位置变换为别的字母，但是每次只变一个位置
    * */
    private static ArrayList<String> getNext(String wanted, List<String> strings, Set<String> stringSet) {
        char[] str = wanted.toCharArray();
        ArrayList<String> nextList = new ArrayList<>();
        // 每次把目标串 i 位置 从 'a' --- 'z' 穷举一遍，看看set中有没有， 有就加入结果集
        for (int i = 0; i < str.length; i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                char tem = str[i];
                if ( str[i] != j){
                    str[i] = j;
                    String s = String.valueOf(str);
                    if (stringSet.contains(s)){
                        nextList.add(s);
                    }
                }
                str[i] = tem;
            }
        }
        return nextList;
    }

}
