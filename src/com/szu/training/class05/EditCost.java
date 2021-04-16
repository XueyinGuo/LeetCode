package com.szu.training.class05;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  最小编辑距离问题
 *
 * @Date 2021/4/15 23:10
 */

public class EditCost {


    public static void main(String[] args) {
        EditCost test = new EditCost();
        String str1 = "sea";
        String str2 = "eat";
        System.out.println(test.minCostViolence(str1, str2, 1, 1, 1));
        System.out.println(test.minCostDP(str1, str2, 1, 1, 1));
//        System.out.println(minCostDp(str1, str2, 5, 3, 2));
    }

    public int minDistance(String word1, String word2) {
        // if(word1 == null || word1.length() == 0 || word2 == null || word2.length() == 0)
        //     return 0;
        return minCostViolence(word1, word2, 1, 1, 1);
    }

    public int minCostViolence(String s1, String s2, int ic, int dc, int rc) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        return minCostViolence(str1, str2, str1.length - 1, str2.length - 1, ic, dc, rc);

    }

    public int minCostViolence(char[] str1, char[] str2, int index1, int index2, int ic, int dc, int rc) {

        if(index1 == -1){
            return (index2 + 1) * ic;
        }
        if(index2 == -1){
            return (index1 + 1) * dc;
        }

        if (index1 == 0 && index2 == 0){

            return str1[index1] == str2[index2] ? 0 : Math.min(rc, ic + dc);
        }

        if (index1 == 0){
            /* str1[index1] 统一变成 str2[index2 - 1] 的样子 然后 加上一个 插入代价 */
            int cost1 = minCostViolence(str1, str2, index1, index2 - 1, ic, dc, rc) + ic;


            int cost5 = Integer.MAX_VALUE;
            if (str1[index1] == str2[index2])
                /* 保留最后一个字母， 两个位置的字母一样，保留不需要任何代价 ， str1[index1 - 1] 统一变成 str2[index2 - 1] 的样子 即可 */
                cost5 = minCostViolence(str1, str2, index1 - 1, index2 - 1, ic, dc, rc);

            return Math.min(cost1, cost5);
        }

        if (index2 == 0){
            int cost2 = minCostViolence(str1, str2, index1 - 1, index2, ic, dc, rc) + dc;
            int cost5 = Integer.MAX_VALUE;
            if (str1[index1] == str2[index2])
                /* 保留最后一个字母， 两个位置的字母一样，保留不需要任何代价 ， str1[index1 - 1] 统一变成 str2[index2 - 1] 的样子 即可 */
                cost5 = minCostViolence(str1, str2, index1 - 1, index2 - 1, ic, dc, rc);

            return Math.min(cost2, cost5);
        }

        /* str1[index1] 统一变成 str2[index2 - 1] 的样子 然后 加上一个 插入代价 */
        int cost1 = minCostViolence(str1, str2, index1, index2 - 1, ic, dc, rc) + ic;
        /* str1[index1 - 1] 统一变成 str2[index2] 的样子 然后 加上一个 删除代价 */
        int cost2 = minCostViolence(str1, str2, index1 - 1, index2, ic, dc, rc) + dc;
        /* 保留最后一个字母 ， str1[index1 - 1] 统一变成 str2[index2 - 1] 的样子 然后 加上一个 替换代价 */
        int cost3 = minCostViolence(str1, str2, index1 - 1, index2 - 1, ic, dc, rc) + rc;
        /* 保留最后一个字母 ， str1[index1 - 1] 统一变成 str2[index2 - 1] 的样子 然后 加上一个 删除代价 + 插入代价 */
        int cost4 = minCostViolence(str1, str2, index1 - 1, index2 - 1, ic, dc, rc) + ic + dc;
        int cost5 = Integer.MAX_VALUE;
        if (str1[index1] == str2[index2])
            /* 保留最后一个字母， 两个位置的字母一样，保留不需要任何代价 ， str1[index1 - 1] 统一变成 str2[index2 - 1] 的样子 即可 */
            cost5 = minCostViolence(str1, str2, index1 - 1, index2 - 1, ic, dc, rc);
        return Math.min( Math.min(Math.min(cost1, cost2), Math.min(cost3, cost4)), cost5);
    }

    public int minCostDP(String s1, String s2, int ic, int dc, int rc){
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int rows = str1.length + 1;
        int cols = str2.length + 1;
        int[][] dp = new int[rows][cols];

        for (int r = 1; r < rows; r++) {
            dp[r][0] = r * dc;
        }

        for (int c = 1; c < cols; c++) {
            dp[0][c] = c * ic;
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (str1[r - 1] == str2[c - 1]){
                    dp[r][c] = dp[r-1][c-1];
                }else {
                    int cost1 = dp[r][c-1] + ic;
                    int cost2 = dp[r-1][c] + dc;
                    int cost3 = dp[r-1][c-1] + rc;
                    int cost4 = dp[r-1][c-1] + rc + dc;
                    dp[r][c] = Math.min( Math.min(cost1, cost2), Math.min(cost3, cost4) );
                }

            }
        }
        return dp[rows-1][cols-1];
    }
}
