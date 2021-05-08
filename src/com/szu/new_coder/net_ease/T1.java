package com.szu.new_coder.net_ease;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/5/8 14:56
 */

import java.util.*;

public class T1 {

    public int specialStr(String inputStr, char[] cList) {
        // write code here

        if (inputStr == null || inputStr.length() == 0)
            return 0;
        boolean[] map = new boolean[26];
        for (int i = 0; i < cList.length; i++) {
            map[cList[i] - 'a'] = true;
        }
        int length = inputStr.length();
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int len1 = o1[1] - o1[0];
                int len2 = o2[1] - o2[0];
                return len2 - len1;
            }
        });
        boolean[][] dp = new boolean[length][length];
        char[] str = inputStr.toCharArray();
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
            queue.add(new int[]{i, i});
        }
        for (int i = 0; i < length - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1];
            if (dp[i][i+1])
                queue.add(new int[]{i, i+1});
        }
        for (int r = length - 2; r >= 0; r--) {
            for (int c = r + 2; c < length; c++) {
                if (str[r] == str[c] && dp[r + 1][c - 1]) {
                    dp[r][c] = true;
                    queue.add(new int[]{r, c});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            boolean isInMap = false;
            int start = poll[0];
            int end = poll[1];
            int i = start;
            while (i <= end) {
                if (map[str[i] - 'a']){
                    isInMap = true;
                    break;
                }
                i++;
            }
            if (!isInMap)
                return end - start + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        String s= "abbacaaca";
        char[] c = {'b','c'};
        T1 t1 = new T1();
        System.out.println(t1.specialStr(s, c));
    }
}
