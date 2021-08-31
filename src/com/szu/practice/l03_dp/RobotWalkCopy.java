package com.szu.practice.l03_dp;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

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
public class RobotWalkCopy {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()){
            int num1 = scanner.nextInt();
            int num2 = scanner.nextInt();
            Set<Integer> res = new TreeSet<>();
            for(int i = 0; i < num1; i++){
                res.add(scanner.nextInt());
            }

            for(int i = 0; i < num2; i++){
                res.add(scanner.nextInt());
            }
            StringBuffer sb = new StringBuffer();
            for(Integer i : res){
                sb.append(i + " ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb.toString());
        }

    }

    private static int justWalk(int locations, int start, int rest, int end) {
        if (rest == 0 && start == end)
            return 1;
        if (rest == 0)
            return 0;
        if (start == 1){
            return justWalk(locations, 2, rest - 1, end);
        }
        if (start == locations){
            return justWalk(locations, locations - 1, rest - 1, end);
        }

        int ways = 0;

        ways += justWalk(locations, start - 1, rest - 1, end);
        ways += justWalk(locations, start + 1, rest - 1, end);
        return ways;
    }

    private static int walkWithDP(int locations, int start, int rest, int end) {

        int[][] dp = new int[locations + 1][rest + 1];
        dp[end][0] = 1;
        for (int c = 1; c <= rest; c++) {
            dp[1][c] = dp[2][c-1];
            for (int r = 2; r <= locations - 1; r++) {
                dp[r][c] = dp[r+1][c-1] + dp[r-1][c-1];
            }
            dp[locations][c] = dp[locations-1][c-1];
        }
        return dp[start][rest];
    }

    private static int walkWithStupidMemory(int locations, int start, int rest, int end, int[][] dp) {

        if (rest == 0 && start == end){
            dp[start][rest] = 1;
            return 1;
        }
        if (rest == 0){
            dp[start][rest] = 0;
            return 0;
        }
        if (dp[start][rest] != -1)
            return dp[start][rest];

        if (start == 1){
            dp[start][rest] = justWalk(locations, 2, rest - 1, end);
            return dp[start][rest];
        }
        if (start == locations){
            dp[start][rest] = justWalk(locations, locations - 1, rest - 1, end);
            return dp[start][rest];
        }

        int ways = 0;

        ways += justWalk(locations, start - 1, rest - 1, end);
        ways += justWalk(locations, start + 1, rest - 1, end);
        dp[start][rest] = ways;
        return ways;

    }



}
