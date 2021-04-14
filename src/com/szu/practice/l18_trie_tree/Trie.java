package com.szu.practice.l18_trie_tree;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/14 16:25
 */

public class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    /*
    * 前缀树添加字符串
    * 数组 0--25 分别代表 a--z
    * pass 代表 到达的次数
    * end 代表 以此节点字符结尾的字符串一共有多少个
    * */
    public void insert(String word) {
        if (word == null || word.length() == 0)
            return;
        TrieNode node = root;
        char[] str = word.toCharArray();
        for (int i = 0; i < str.length; i++) {
            int path = str[i] - 'a';
            node.pass++;
            if (node.nextNodes[path] == null){
                node.nextNodes[path] = new TrieNode();

            }
            node = node.nextNodes[path];
        }
        node.pass++;
        node.end++;
    }

    public boolean search(String word) {
        if (word == null || word.length() == 0)
            return false;
        TrieNode node = root;
        char[] str = word.toCharArray();
        int i = 0;
        while (i < str.length){
            node = node.nextNodes[str[i] - 'a'];
            if (node == null)
                return false;
            i++;
        }
        return node.end != 0;
    }

    /*
    * startsWith 和 search 的唯一不一样的地方在于
    * startsWith : node.pass
    * search : node.end
    * */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0)
            return false;
        TrieNode node = root;
        char[] str = prefix.toCharArray();
        int i = 0;
        while (i < str.length){

            node = node.nextNodes[str[i] - 'a'];
            if (node == null)
                return false;

            i++;
        }
        return node.pass != 0;
    }


    public void delete(String word) {
        if (word == null || word.length() == 0 || !search(word))
            return;

        TrieNode node = root;
        char[] str = word.toCharArray();
        int i = 0;
        while (i < str.length){
            int path = str[i] - 'a';
            /*
            * 当在删除过程中，发现一个节点在 到达次数 - 1 之后变成了 0
            * 直接把之后的路径全部置空
            * */
            if (--node.pass == 0){
                node.nextNodes[path] = null;
                return;
            }
            node = node.nextNodes[path];
            i++;
        }
        /*
        * 删除完成之后需要把最后一个节点的到达次数 和 结尾次数 都 - 1
        * */
        node.pass--;
        node.end--;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abc");
        trie.delete("abc");
    }

    static class TrieNode{
        TrieNode[] nextNodes;
        int pass;
        int end;

        public TrieNode() {
            nextNodes = new TrieNode[26];
            pass = 0;
            end = 0;
        }
    }
}


