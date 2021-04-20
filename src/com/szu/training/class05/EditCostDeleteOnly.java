package com.szu.training.class05;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定两个字符串 s1 和 s2，问 s2 最少删除多少字符可以成为 s1 的字串
 * 比如 s1 = "abcde"  ,  s2 = "axbc"
 * 返回 1， 删掉 x 即可
 *
 * @Date 2021/4/16 13:33
 */

public class EditCostDeleteOnly {

    public static void main(String[] args) {
//        String s1 = "adcc";
//        String s2 = "dc";
//        int violence = minCostViolence(s1, s2);
//        int right = RIGHT_CODE(s1, s2);
//        int myDp0 = minCostDP(s1, s2);
//        System.out.println(violence);

        int str1Len = 30;
        int str2Len = 15;
        int v = 5;
        for (int i = 0; i < 100000; i++) {
            String str1 = generateRandomString(str1Len, v);
            String str2 = generateRandomString(str2Len, v);
            int ans3 = RIGHT_CODE(str1, str2);
            int my = minCostViolence(str1, str2);
            int myDp = minCostDP(str1, str2);
            if (ans3 != my || ans3 != myDp)
                System.out.println( i +"    FUCK  " + ans3 +  "   " +my+  "   " + myDp);
        }

//        int dp = minCostDp(s1, s2);
//        int awesome = minCostAwesome(s1, s2);

    }

    public static int minCostViolence(String s1, String s2) {
        /*
        * 本题目中 s2 通过删除的方式， 变成 s1
        *
        * 当 s2 为空的时候，不用动，空串是任何串的字串
        *
        * 当 s1 为空的时候，需要删掉所有字符才可以
        * */
        if (s1 == null || s1.length() == 0)
            return s2.length();
        if (s2 == null || s2.length() == 0)
            return 0;
        int min = Integer.MAX_VALUE;
        char[] str2 = s2.toCharArray();
        for (int start = 0; start < s1.length(); start++) {
            for (int end = start + 1; end <= s1.length(); end++) {
                char[] str1 = s1.substring(start, end).toCharArray();
                int cur = minCostViolence( str1, str2, str1.length - 1, str2.length - 1 );
                min = Math.min( min, cur );
            }
        }
        return min == Integer.MAX_VALUE ? s2.length() : min;
    }

    public static int minCostViolence(char[] str1, char[] str2, int index1, int index2) {
        /* 此时已经完全没有字符了，剩下了两个空串，必然是编辑代价 0 */
        if (index1 == -1 && index2 == -1)
            return 0;
        /* 此时 s1 已经没有字符了，所以 空串 插入 index2 长度的字符即可 */
        if (index1 == -1)
            return index2 + 1;
        /* 此时 s2 已经剩下空了，没有任何字符，任何有字符的串都不可能通过插入获得 “”，返回最大值 */
        if (index2 == -1)
            return Integer.MAX_VALUE;
        /* 此时剩下了普遍位置 */
        int cost1 = Integer.MAX_VALUE;
        /* 如果一个普遍位置，两个指针指向的位置相等， 直接判断各自前一个字符就好了 */
        if (str1[index1] == str2[index2])
            cost1 = minCostViolence(str1, str2, index1-1, index2-1);
        /*
        * 如果两个不等， 那么我们可以判断 index1 位置不动，让 index2 前移动
        * 意思就是说，如果 index2 位置的保留， 让 str1 变成 str2[0---index2 -1 ]位置的串 + 插入一个字符的代价
        * */
        int cost2 = minCostViolence(str1, str2, index1, index2 - 1);

        /* 如果有搞头，再让 cost2 + 1，防止整数越界 */
        if (cost2 != Integer.MAX_VALUE)
                cost2++;

        return Math.min(cost1, cost2);
    }

    public static int minCostDP(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int rows = str1.length + 1;
        int cols = str2.length + 1;
        int[][] dp = new int[rows][cols];
        // dp[0][0] = 0;  “” -> “” 编辑代价为 0
        int ans = cols - 1;

        for (int r = 1; r < rows; r++) {
            dp[r][0] = Integer.MAX_VALUE;
        }
        for (int start = 1; start < rows; start++) {
            for (int c = 1; c < cols; c++) {
                if (str1[start - 1] == str2[c - 1] || dp[start][c - 1] != Integer.MAX_VALUE)
                    dp[start][c] = c - 1;
                else
                    dp[start][c] = Integer.MAX_VALUE;
            }
            ans = Math.min(ans, dp[start][cols-1]);
            for (int end = start + 1; end < rows; end++) {
                for (int c = 1; c < cols; c++) {

                    if (str1[end - 1] == str2[c - 1])
                        dp[end][c] = Math.min(dp[end-1][c-1], c - 1);
                    else if (dp[end][c-1] != Integer.MAX_VALUE)
                        dp[end][c] = dp[end][c-1] + 1;
                    else
                        dp[end][c] = Integer.MAX_VALUE;
                }
                ans = Math.min(ans, dp[end][cols-1]);
            }
        }
        return ans;
    }




    /*
    * 大神的正确代码
    * */
    public static String generateRandomString(int l, int v) {
        int len = (int) (Math.random() * l);
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(str);
    }

    public static int RIGHT_CODE(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }
        char[] str2 = s2.toCharArray();
        char[] str1 = s1.toCharArray();
        int M = str2.length;
        int N = str1.length;
        int[][] dp = new int[M][N];
        int ans = M;
        for (int start = 0; start < N; start++) { // 开始的列数
            dp[0][start] = str2[0] == str1[start] ? 0 : M;
            for (int row = 1; row < M; row++) {
                dp[row][start] = (str2[row] == str1[start] || dp[row - 1][start] != M) ? row : M;
            }
            ans = Math.min(ans, dp[M - 1][start]);
            // 以上已经把start列，填好
            // 以下要把dp[...][start+1....N-1]的信息填好
            // start...end end - start +2
            for (int end = start + 1; end < N && end - start < M; end++) {
                // 0... first-1 行 不用管
                int first = end - start;
                dp[first][end] = (str2[first] == str1[end] && dp[first - 1][end - 1] == 0) ? 0 : M;
                for (int row = first + 1; row < M; row++) {
                    dp[row][end] = M;
                    if (dp[row - 1][end] != M) {
                        dp[row][end] = dp[row - 1][end] + 1;
                    }
                    if (dp[row - 1][end - 1] != M && str2[row] == str1[end]) {
                        dp[row][end] = Math.min(dp[row][end], dp[row - 1][end - 1]);
                    }
                }
                ans = Math.min(ans, dp[M - 1][end]);
            }
        }
        return ans;
    }


}
