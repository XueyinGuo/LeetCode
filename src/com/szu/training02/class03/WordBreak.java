package com.szu.training02.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 假设所有字符都是小写字母   长字符串为 str
 * arr 是 去重的单词表
 * 每个单词都是空字符串而且可以使用任意次数
 * 使用arr中的单词有多少种 拼接str 的方式
 * 返回方法数
 *
 * @Date 2021/4/27 16:27
 */

import java.util.HashSet;

public class WordBreak {

    /* 暴力尝试 */
    public static int violence(String s, String[] words) {
        HashSet<String> set = new HashSet<>();
        for (String word : words) {
            set.add(word);
        }
        return violence(s, 0, set);
    }

    private static int violence(String s, int index, HashSet<String> set) {
        /* 在正常来到 s 的结尾的时候，说明这个划分是有效的，返回一种方法 */
        if (index == s.length())
            return 1;
        int ans = 0;
        for (int end = index; end < s.length(); end++) {
            /* 只有在子串在单词表中的时候才进入递归 */
            String substring = s.substring(index, end + 1);
            if (set.contains(substring)) {
                ans += violence(s, end + 1, set);
            }
        }
        return ans;
    }


    static class TrieNode {
        TrieNode[] nextNodes;
        int end;

        public TrieNode() {
            nextNodes = new TrieNode[26];
            end = 0;
        }
    }

    static TrieNode root;

    public static int DPWithTrieTree(String s, String[] words) {
        root = new TrieNode();
        /* 构建前缀树，为了查询子串是否为单词表中单词时加速 */
        buildTree(words);
        char[] str = s.toCharArray();
        int length = str.length;
        int[] dp = new int[length + 1];
        dp[length] = 1;
        for (int start = length - 1; start >= 0; start--) {
            for (int end = start; end < length; end++) {
                /*
                * 如果这个子串是 位于单词表中的，那么这个位置是合理切分
                * dp依赖 end+1 位置
                * */
                if (isInWords(str, start, end, root)) {
                    dp[start] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }

    /*
    * 判断是否在单词表中，从前缀树的根节点开始查起，如果没到 start end 结尾
    * 就没有节点了，那么单词表中 绝逼没有这个单词， 也不用继续尝试后续的了，直接返回 false
    *
    * 如果到了结尾，但是 end == 0，那么单词表中野没有这个单词 也返回 false
    *
    * 最后才返回 true
    * */
    private static boolean isInWords(char[] str, int start, int end, TrieNode root) {
        TrieNode node = root;
        for (int i = start; i <= end; i++) {
            int path = str[i] - 'a';
            if (node.nextNodes[path] == null)
                return false;
            node = node.nextNodes[path];
        }
        if (node.end == 0)
            return false;
        return true;
    }

    /*
    * 标准前缀树构建代码
    * */
    private static void buildTree(String[] words) {
        HashSet<String> set = new HashSet<>();
        for (String word : words) {
            if (!set.contains(word)) {
                set.add(word);
                TrieNode node = root;
                char[] str = word.toCharArray();
                for (int i = 0; i < str.length; i++) {
                    int path = str[i] - 'a';
                    if (node.nextNodes[path] == null)
                        node.nextNodes[path] = new TrieNode();
                    node = node.nextNodes[path];
                }
                node.end++;
            }
        }
    }





















    public static void main(String[] args) {
        char[] candidates = {'a', 'b'};
        int num = 20;
        int len = 4;
        int joint = 5;
        int testTimes = 300000;
        boolean testResult = true;
        long time1 = 0;
        long time2 = 0;
        long timedp = 0;

        for (int i = 0; i < testTimes; i++) {
            RandomSample sample = generateRandomSample(candidates, num, len, joint);
            long startTime = System.currentTimeMillis();
            int ans1 = violence(sample.str, sample.arr);
            time1 += System.currentTimeMillis() - startTime;
            startTime = System.currentTimeMillis();
            int ans2 = waysRight(sample.str, sample.arr);
            time2 += System.currentTimeMillis() - startTime;
            startTime = System.currentTimeMillis();
            int ans3 = DPWithTrieTree(sample.str, sample.arr);
            timedp += System.currentTimeMillis() - startTime;
//            int ans4 = ways4(sample.str, sample.arr);
            if (ans1 != ans2 || ans3 != ans1 /* || ans2 != ans4 */) {
                System.out.println("FUCK");
            }

        }
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(timedp);

    }


    // 以下的逻辑都是为了测试
    public static class RandomSample {
        public String str;
        public String[] arr;

        public RandomSample(String s, String[] a) {
            str = s;
            arr = a;
        }
    }

    public static RandomSample generateRandomSample(char[] candidates, int num, int len, int joint) {
        String[] seeds = randomSeeds(candidates, num, len);
        HashSet<String> set = new HashSet<>();
        for (String str : seeds) {
            set.add(str);
        }
        String[] arr = new String[set.size()];
        int index = 0;
        for (String str : set) {
            arr[index++] = str;
        }
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < joint; i++) {
            all.append(arr[(int) (Math.random() * arr.length)]);
        }
        return new RandomSample(all.toString(), arr);
    }

    public static String[] randomSeeds(char[] candidates, int num, int len) {
        String[] arr = new String[(int) (Math.random() * num) + 1];
        for (int i = 0; i < arr.length; i++) {
            char[] str = new char[(int) (Math.random() * len) + 1];
            for (int j = 0; j < str.length; j++) {
                str[j] = candidates[(int) (Math.random() * candidates.length)];
            }
            arr[i] = String.valueOf(str);
        }
        return arr;
    }


    /*
     * 正确的暴力代码
     * */
    public static int waysRight(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        HashSet<String> map = new HashSet<>();
        for (String s : arr) {
            map.add(s);
        }
        return f(str, map, 0);
    }

    public static int f(String str, HashSet<String> map, int index) {
        if (index == str.length()) {
            return 1;
        }
        int ways = 0;
        for (int end = index; end < str.length(); end++) {
            if (map.contains(str.substring(index, end + 1))) {
                ways += f(str, map, end + 1);
            }
        }
        return ways;
    }

}
