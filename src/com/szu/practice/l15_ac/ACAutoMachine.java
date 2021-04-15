package com.szu.practice.l15_ac;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/28 20:00
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ACAutoMachine {

    TrieTreeNode root;

    public ACAutoMachine() {
        root = new TrieTreeNode();
    }

    static class TrieTreeNode{
        /*
        * nextNodes : 下层前缀树节点
        * fail : AC自动机的 fail 指针
        * end : 为了遍历方便 在每个字符串结尾的节点上放一个字符串本身
        * isEnd : 只有 isEnd 为 true 的时候，上边的字符串才是有效的
        * */
        TrieTreeNode[] nextNodes;
        TrieTreeNode fail;
        String end;
        boolean used;
//        char c;

        public TrieTreeNode() {
            nextNodes = new TrieTreeNode[26];
            fail = null;
            end = null;
            used = false;
//            c = ' ';
        }
    }

    public static void main(String[] args) {
        ACAutoMachine ac = new ACAutoMachine();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("sfajsd");
        ac.insert("eiurow");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("erhkfjhieiurowawejdhekshdkassfajsddgfgsliuow");
        for (String word : contains) {
            System.out.println(word);
        }
    }

    public List<String> containWords(String article) {
        ArrayList<String> ans = new ArrayList<>();
        if (article == null || article.length() == 0)
            return ans;
        char[] str = article.toCharArray();
        TrieTreeNode cur = root;
        for (int i = 0; i < str.length; i++) {
            int path = str[i] - 'a';
            /*
            * 如果当前字符在这条路上没配出来，就随着fail方向走向下条路径
            * 如果当前cur节点，没有path的路，就通过fail，跳到别的前缀上去
            * */
            /* 如果不加上 cur != root 这个判断的话，cur 走到头结点 继续 cur.fail 会有空指针异常 */
            /*
            * ================================================
            * ================================================
            * ================================================
            * */
            while (cur.nextNodes[path] == null && cur != root)
                cur = cur.fail;
            /* 退出循环有两个条件，如果是用为 ！= null 跳出循环，则把cur指向头 */
            if (cur.nextNodes[path] == null)
                cur = root;
            else
                cur = cur.nextNodes[path];
            /*
             * ================================================
             * ================================================
             * ================================================
             * */
            /*
            * 使用 follow 收集一圈答案
            * */
            TrieTreeNode follow = cur;
            while (follow != root){
                /* 如果这圈已经被收集过答案了，所以直接跳出 */
                if (follow.used)
                    break;
                if (follow.end != null){
                    ans.add(follow.end);
                    follow.used = true;
                }
                follow = follow.fail;
            }
        }
        return ans;
    }

    private void build() {
        Queue<TrieTreeNode> queue = new LinkedList<>();
        queue.add(root);
        TrieTreeNode cur = null;
        while (!queue.isEmpty()){
            /*
            * 从队列中弹出一个节点，遍历这个节点的26个儿子path是否为空，如果不为空的话 就要设置儿子的路径了
            * */
            cur = queue.poll();
            for (int i = 0; i < 26; i++) {
                if (cur.nextNodes[i] != null){
                    /*
                    * 先让不为空的儿子路径指向 根节点
                    * */
                    cur.nextNodes[i].fail = root;
                    /* 抓取到 当前弹出节点的 fail 指针 */
                    TrieTreeNode curFail = cur.fail;
                    /* 如果此时的 fail 指针不为空，则需要开始设置路径了 */
                    while (curFail != null){
                        /* 如果 fail 指针指向的节点 有同样的儿子 path 路径 */
                        if (curFail.nextNodes[i] != null){
                            /* 当前节点的 path 就直接干过去 */
                            cur.nextNodes[i].fail = curFail.nextNodes[i];
                            break;
                        }
                        /*
                        * 如果 fail 指针上没有到 儿子path 的路，顺着fail指针指向的节点的fail指针一直跳下去
                        * 如果其中有一个不为空，通过while会设置好，否则 所有fail 都没有儿子path路，
                        * 所以当前节点的儿子节点指向头结点
                        *  */
                        curFail = curFail.fail;
                    }
                    queue.add(cur.nextNodes[i]);
                }
            }


        }
    }

    private void insert(String word) {
        if (word == null || word.length() == 0)
            return;
        char[] str = word.toCharArray();
        int i = 0;
        TrieTreeNode node = root;
        while (i < str.length){
            int path = str[i] - 'a';
            if (node.nextNodes[path] == null){

                node.nextNodes[path] = new TrieTreeNode();
//                node.nextNodes[path].c =  (char)( 'a' + path);
            }
            node = node.nextNodes[path];

            i++;
        }
        node.used = false;
        node.end = word;
    }
}
