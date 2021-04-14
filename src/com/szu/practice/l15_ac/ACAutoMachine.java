package com.szu.practice.l15_ac;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/28 20:00
 */

import java.util.List;

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
        boolean isEnd;

        public TrieTreeNode() {
            nextNodes = new TrieTreeNode[26];
            fail = null;
            end = null;
            isEnd = false;
        }
    }

    public static void main(String[] args) {
        ACAutoMachine ac = new ACAutoMachine();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();

//        List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
//        for (String word : contains) {
//            System.out.println(word);
//        }
    }

    private void build() {

    }

    private void insert(String word) {
        if (word == null || word.length() == 0)
            return;
        char[] str = word.toCharArray();
        int i = 0;
        TrieTreeNode node = root;
        while (i < str.length){
            int path = str[0] - 'a';
            node = node.nextNodes[path];
            if (node == null) {
                node = new TrieTreeNode();
            }
            i++;
        }
        node.isEnd = true;
        node.end = word;
    }
}
