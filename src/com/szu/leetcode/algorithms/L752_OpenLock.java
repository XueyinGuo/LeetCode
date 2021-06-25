package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 752. 打开转盘锁
你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。

锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。

列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。

字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 *
 * @Date 2021/6/25 22:06
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class L752_OpenLock {

    public int openLock(String[] deadEnds, String target) {
        String start = "0000";
        // 压根不用动
        if (target.equals( start))
            return 0;
        // 如果 dead 中包含目标串或者起始串，直接 -1
        HashSet<String> shouldIgnore = new HashSet<>();
        for (String s : deadEnds) {
            shouldIgnore.add(s);
        }

        if (shouldIgnore.contains(target) || shouldIgnore.contains(start))
            return -1;
        /*
        * 开始深度优先遍历
        * 每次都可以取出本层变成的字符串
        * 从本层中 继续变化下一层
        * 一旦 equals 了，那就是找到了
        * 返回现在的层数即可
        * */
        LinkedList<String> queue = new LinkedList<>();
        queue.offer(start);
        shouldIgnore.add(start); // 为了防止重复遍历
        int distance = 0;
        while (!queue.isEmpty()){
            distance++;
            int size = queue.size(); // 就像二叉树的宽度优先遍历，本层的个数就是 目前 队列中元素的个数
            for (int i = 0; i < size; i++) {
                for (String nextStatus : getNextStatus(queue.pollFirst(), shouldIgnore)) { // 弹出本层的字符，并且把下一层的字符串加入到队列中
                    if (nextStatus.equals(target))
                        return distance; // 如果equals， 返回当前层数
                    queue.addLast(nextStatus);
                }
            }
        }
        return -1;
    }

    /*
    * 把每个字符串的 每个字符都前后转一下，没遍历到的结果加入结果集返回
    * */
    private List<String> getNextStatus(String cur, HashSet<String> shouldIgnore) {
        List<String> res = new LinkedList<>();
        char[] str = cur.toCharArray();
        for (int i = 0; i < 4; i++) {
            char tem = str[i];
            String pre = preRotate(str, i);
            if (!shouldIgnore.contains(pre)){
                shouldIgnore.add(pre);
                res.add(pre);
            }
            str[i] = tem;
            String post = postRotate(str, i);
            if (!shouldIgnore.contains(post)){
                shouldIgnore.add(post);
                res.add(post);
            }
            str[i] = tem;
        }
        return res;
    }

    private String postRotate(char[] str, int i) {
        str[i] = str[i] == '9' ? '0' : (char) (str[i] + 1);
        return new String(str);
    }

    private String preRotate(char[] str, int i) {
        str[i] = str[i] == '0' ? '9' : (char) (str[i] - 1);
        return new String(str);
    }

}
