package com.szu.training03.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定字符串 str1 和 str2，求str1的字串中含有 str2 所有字符的最小串的长度
 * 【不用在意 str2 字符出现的顺序】
 *
 * @Date 2021/5/5 14:47
 */

import com.szu.leetcode.utils.LeetCodes;

public class MinWindowLength {

    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0)
            return t == null || t.length() == 0 ? "" : null;
        if (t == null || t.length() == 0)
            return "";
        char[] des = t.toCharArray();
        char[] str = s.toCharArray();

        /* 初始化欠账表， 是不是我欠下的那种字符表，总负债字符数量是 t 的长度 */
        int[] map = new int[26];
        boolean[] isTChar = new boolean[26];
        int all = des.length;
        for (int i = 0; i < des.length; i++){
            int index = des[i] - 'a';
            map[index]++;
            isTChar[index] = true;
        }
        /* 如果 t 中含有 str 中没有的字符，不可能有答案 */
        if (!isValid(str, isTChar))
            return null;

        /*
        * 滑动窗口，既然无次序要求，我就可以生成刚刚那样的 字符map，和记录某个字符是目标串中的字符
        * */
        int l = 0;
        int r = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        boolean shrink = false;
        /*
        * 每次 字符从 r 进入窗口，一旦 欠债被填满了，
        * 开始从 l 出字符，在 all 不重新变成 0 的情况下继续缩下去
        * 直到缩的不合法了，开始计算此时的 窗口长度，
        * 每次这样，一定能找到 最小的正好把账单清零的窗口，
        * 那个窗口就是答案
        * */
        while (r < str.length){
            int ri = str[r] - 'a';
            if (isTChar[ri] && map[ri] > 0)
                all--;
            map[ri]--;
            while (all == 0){
                shrink = true;
                int li = str[l] - 'a';
                if (isTChar[li] && map[li] == 0){
                    all++;
                }
                map[li]++;
                l++;
            }
            if (shrink){
                int curWindow = r - l + 2;
                if (curWindow < minLen){
                    minLen = curWindow;
                    start = l-1;
                    end = r;
                }
            }
            shrink = false;
            r++;
        }
        return s.substring(start, end+1);
    }


    public int minWindowLen(String s, String t) {
        if (s == null || s.length() == 0)
            return Integer.MAX_VALUE;
        if (t == null || t.length() == 0)
            return 0;
        char[] des = t.toCharArray();
        char[] str = s.toCharArray();

        /* 初始化欠账表， 是不是我欠下的那种字符表，总负债字符数量是 t 的长度 */
        int[] map = new int[26];
        boolean[] isTChar = new boolean[26];
        int all = des.length;
        for (int i = 0; i < des.length; i++){
            int index = des[i] - 'a';
            map[index]++;
            isTChar[index] = true;
        }
        /* 如果 t 中含有 str 中没有的字符，不可能有答案 */
        if (!isValid(str, isTChar))
            return Integer.MAX_VALUE;

        /*
         * 滑动窗口，既然无次序要求，我就可以生成刚刚那样的 字符map，和记录某个字符是目标串中的字符
         * */
        int l = 0;
        int r = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        boolean shrink = false;
        /*
         * 每次 字符从 r 进入窗口，一旦 欠债被填满了，
         * 开始从 l 出字符，在 all 不重新变成 0 的情况下继续缩下去
         * 直到缩的不合法了，开始计算此时的 窗口长度，
         * 每次这样，一定能找到 最小的正好把账单清零的窗口，
         * 那个窗口就是答案
         * */
        while (r < str.length){
            int ri = str[r] - 'a';
            if (isTChar[ri] && map[ri] > 0)
                all--;
            map[ri]--;
            while (all == 0){
                shrink = true;
                int li = str[l] - 'a';
                if (isTChar[li] && map[li] == 0){
                    all++;
                }
                map[li]++;
                l++;
            }
            if (shrink){
                int curWindow = r - l + 2;
                if (curWindow < minLen){
                    minLen = curWindow;
                    start = l-1;
                    end = r;
                }
            }
            shrink = false;
            r++;
        }
        return minLen;
    }

    private boolean isValid(char[] str, boolean[] tMap) {

        boolean[] strMap = new boolean[str.length];
        for (int i = 0; i < str.length; i++)
            strMap[str[i] - 'a'] = true;

        for (int i = 0; i < tMap.length; i++) {
            if (tMap[i] && !strMap[i])
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        MinWindowLength test = new MinWindowLength();
        for (int i = 0; i < 1000000; i++) {
            String s = LeetCodes.getRandomString(50);
            String t = LeetCodes.getRandomString(10);

            int my = test.minWindowLen(s, t);
            my = my == Integer.MAX_VALUE ? 0 : my;
            int right = test.minLength(s, t);
            if (my != right)
                System.out.println("FUCK");
        }

    }




    /*
    * 大神的right代码
    * */
    public int minLength(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return Integer.MAX_VALUE;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] map = new int[256]; // map[37] = 4  37  4次
        for (int i = 0; i != str2.length; i++) {
            map[str2[i]]++;
        }
        int left = 0;
        int right = 0;
        int all = str2.length;
        int minLen = Integer.MAX_VALUE;
        // [left, right)  [left, right-1]    [0,0)
        // R右扩   L ==0  R
        while (right != str1.length) {
            map[str1[right]]--;
            if (map[str1[right]] >= 0) {
                all--;
            }
            if (all == 0) { // 还完了
                while (map[str1[left]] < 0) {
                    map[str1[left++]]++;
                }
                // [L..R]
                minLen = Math.min(minLen, right - left + 1);
                all++;
                map[str1[left++]]++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
