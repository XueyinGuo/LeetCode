package com.szu.practice;
/*
* 假设有排成一行的N个位置，记为 1~N， N一定大于等于2
* 开始时机器人在其中的M位置上（M一定是 1~N中的一个）
* 如果机器人来到1，那么下一步能往右来到2
* 如果机器人来到N位置，那么下一步只能往 N-1 位置
* 如果机器人来到中间位置，机器人可以选择往左也可以选择往右走
* 规定机器人必须走K步，最终能来到P位置的方法有多少种
*
* N 个位置，初始位置 M， 走 K步， 到 P
* */
public class RobotWalk {
    public static void main(String[] args) {
//        int ways = walk(100, 10,300,4);
//        System.out.println(ways);

        int rest = 10;
        int n = 10;
        int dp[][] = new int[n + 1][rest + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= rest; j++) {
                dp[i][j] = -1;
            }
        }
        int wasWithStupidMemory = walkWithStupidMemory(n, 8, rest, 4, dp);
        int wasWithdp = walkWithDP(n,8,  rest,4);
        System.out.println(wasWithStupidMemory);
        System.out.println(wasWithdp);
    }

    /*
    * N 个位置，
    * 初始位置 cur，
    * 走 rest步，
    * 到 dest
    * dp[][] =  int[n + 1][rest + 1]
    * */
    private static int walkWithDP(int N, int cur, int rest,int dest) {
        int dp[][] = new int[N + 1][rest + 1];
        dp[dest][0] = 1;
        for (int r = 1; r <= rest; r++) {
            dp[1][r] = dp[2][r-1];
            for (int n = 1; n < N; n++) {
                dp[n][r] = dp[n - 1][r - 1] + dp[n + 1][r - 1];
            }
            dp[N][r] = dp[N-1][r-1];
        }


        return dp[cur][rest];
    }

    private static int walk(int n, int cur, int rest, int des) {
        // 没有剩余步数可以走了，走没走到 目的地呢？ 走到了就是一种方案，没走到就不是一种方案
        if (rest == 0){
            return des == cur ? 1:0;
        }
        // 当位于第一个位置时，只能向前走
        if (cur == 1){
            return walk(n, 2, rest - 1, des);
        }
        // 当位于最后一个位置时，只能往后走
        if (cur == n){
            return walk(n, cur - 1, rest - 1, des);
        }
        // 有可能往前走
        int forward = walk(n, cur + 1, rest - 1, des);
        // 有可能往后走
        int backward = walk(n, cur - 1, rest - 1, des);
        return forward + backward;
    }

    private static int walkWithStupidMemory(int n, int cur, int rest, int des, int[][] dp) {
        if (dp[cur][rest] != -1){
            return dp[cur][rest];
        }
        // 没有剩余步数可以走了，走没走到 目的地呢？ 走到了就是一种方案，没走到就不是一种方案
        if (rest == 0){
            return des == cur ? 1:0;
        }
        // 当位于第一个位置时，只能向前走
        int ans = -1;
        if (cur == 1){
            ans = walkWithStupidMemory(n, cur + 1, rest - 1, des,dp);
        }
        // 当位于最后一个位置时，只能往后走
        else if (cur == n){
            ans = walkWithStupidMemory(n, cur - 1, rest - 1, des,dp);
        }else{
            // 有可能往前走
            ans = walkWithStupidMemory(n, cur + 1, rest - 1, des,dp) +
                    // 有可能往后走
                    walkWithStupidMemory(n, cur - 1, rest - 1, des,dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

}
