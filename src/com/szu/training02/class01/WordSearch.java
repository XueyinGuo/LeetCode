package com.szu.training02.class01;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/25 20:48
 */

import com.szu.leetcode.algorithms.L212_WordSearchII;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordSearch {

    TrieNode head;

    public List<String> findWords(char[][] board, String[] words) {
        head = new TrieNode();
        /*
         * 把所有字符串加成前缀树
         * */
        buildTrieTree(words);
        /* 存放结果 */
        ArrayList<String> res = new ArrayList<>();
        /* 存放走过的路径 */
        ArrayList<Character> path = new ArrayList<>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                int index = board[r][c] - 'a';
                /*
                 * 只有在前缀树头结点有走向当前节点的路的时候，才进行递归
                 * */
                if (head.nextNodes[index] != null) {
//                    path.add(board[r][c]);
                    findWords(board, r, c, head.nextNodes[index], path, res);
//                    path.remove(path.size() - 1);
                }
            }
        }
        return res;
    }

    public int findWords(char[][] board, int r, int c, TrieNode cur, ArrayList<Character> path, ArrayList<String> res) {

        int rows = board.length;
        int cols = board[0].length;
        /*
         * 这一轮递归一共找到几个答案，优化关键中的关键！！！
         * */
        int finds = 0;
        /* 深度优先遍历经典的创造新现场 恢复现场操作 */
        path.add(board[r][c]);
        /* 防止走回头路，把走过的地方直接拆掉，后边重新补回来 */
        char tem = board[r][c];
        board[r][c] = 0;
        /* 一个位置不越界的话， 而且不是回头路，才进行下层搜索 */
        if (r + 1 < rows && board[r + 1][c] != 0) {
            int upWays = board[r + 1][c] - 'a';
            /* 当前节点有走向这个字母的路，而且跟这个字母有关系的答案还没有被收集干净，才进行下层递归 */
            if (cur.nextNodes[upWays] != null && cur.nextNodes[upWays].pass > 0)
                finds += findWords(board, r + 1, c, cur.nextNodes[upWays], path, res);
        }

        if (r - 1 >= 0 && board[r - 1][c] != 0) {
            int downWays = board[r - 1][c] - 'a';
            if (cur.nextNodes[downWays] != null && cur.nextNodes[downWays].pass > 0)
                finds += findWords(board, r - 1, c, cur.nextNodes[downWays], path, res);
        }

        if (c - 1 >= 0 && board[r][c - 1] != 0) {
            int leftWays = board[r][c - 1] - 'a';
            if (cur.nextNodes[leftWays] != null && cur.nextNodes[leftWays].pass > 0)
                finds += findWords(board, r, c - 1, cur.nextNodes[leftWays], path, res);
        }

        if (c + 1 < cols && board[r][c + 1] != 0) {
            int rightWays = board[r][c + 1] - 'a';
            if (cur.nextNodes[rightWays] != null && cur.nextNodes[rightWays].pass > 0)
                finds += findWords(board, r, c + 1, cur.nextNodes[rightWays], path, res);
        }
        /*
         * 如果这个字符是一个字符串的结尾，我就收集这个path作为答案，
         * 然后把这个字符串的end 清空，而且我找到一个答案，find++
         * */
        if (cur.end != 0) {
            res.add(createString(path));
            cur.end--;
            finds++;
        }
        /*
         * =========================
         * =========================
         * 把这个字母中找到的答案删掉
         * 如果经过这个字母的答案收集完了
         * pass 也就 减成 0 了，
         * 收集完答案之后的路再也不考虑进下层展开了
         * =========================
         * =========================
         * */
        cur.pass -= finds;
        board[r][c] = tem;
        path.remove(path.size() - 1);
        return finds;
    }

    private String createString(ArrayList<Character> path) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
        }
        return sb.toString();
    }

    /*
     * 标准前缀树构建代码
     * */
    private void buildTrieTree(String[] words) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            /* 每个字符串只加入一次前缀树 */
            if (!set.contains(words[i])) {
                set.add(words[i]);
                TrieNode node = head;
                char[] str = words[i].toCharArray();
                for (int j = 0; j < str.length; j++) {
                    int path = str[j] - 'a';
                    node.pass++;
                    if (node.nextNodes[path] == null) {
                        TrieNode newNode = new TrieNode(str[j]);
                        node.nextNodes[path] = newNode;
                    }
                    node = node.nextNodes[path];
                }

                node.end++;
            }

        }
    }

    static class TrieNode {
        int pass;
        int end;
        char c;
        TrieNode[] nextNodes;

        public TrieNode() {
            pass = 0;
            nextNodes = new TrieNode[26];
        }

        public TrieNode(char ch) {
            pass = 1;
            nextNodes = new TrieNode[26];
            c = ch;
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'a', 'b', 'c', 'e'}, {'x', 'x', 'c', 'd'}, {'x', 'x', 'b', 'a'}};
        String[] words = {"abc", "abcd"};
        L212_WordSearchII test = new L212_WordSearchII();
        List<String> words1 = test.findWords(board, words);
        System.out.println();
    }

}
