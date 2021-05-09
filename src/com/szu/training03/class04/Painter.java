package com.szu.training03.class04;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个正数数组 arr，数组中每个值都是正数，表示完成一幅画需要的时间，再给定一个整数 num，表示画匠的数量
 * 每个画匠只能画连在一起的画。所有的画家并行工作，请返回完成所有画作需要的最少时间
 *
 * 【举例】
 * arr = [3,1,4]   num = 2
 * 最好的分配范式就是 3 1 给 一号画匠， 4 给二号画匠，  所需最短的完成时间为 4
 *
 * @Date 2021/5/7 13:54
 */

import com.szu.leetcode.utils.LeetCodes;


public class Painter {

    /*
     * 最优解
     * */
    public int awesome(int[] arr, int num) {

        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        long l = 0;
        long r = sum;
        long ans = 0;
        while (l <= r) {
            long mid = (l + r) / 2;
            long cur = getNeedParts(arr, mid);
            if (cur <= num) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) ans;
    }

    public static int getNeedParts(int[] arr, long aim) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > aim) {
                return Integer.MAX_VALUE;
            }
        }
        int parts = 1;
        int all = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (all + arr[i] > aim) {
                parts++;
                all = arr[i];
            } else {
                all += arr[i];
            }
        }
        return parts;
    }


    /*
     * 暴力尝试
     *
     * 几个人画画，把数组切分成几份， 转换成切几刀的问题
     * */
    private int process(int[] arr, int r, int c) {
        if (r == arr.length)
            return 0;
        /*
         * 如果剩下 0 刀可切， 返回此时位置到 数组最后一位的累加和
         * */
        if (c == 0) {
            int res = 0;
            for (int i = r; i < arr.length; i++) {
                res += arr[i];
            }
            return res;
        }
        /*
         * 如果还能切，枚举能切的位置
         * */
        int min = Integer.MAX_VALUE;
        int sum = 0;
        /*
         * 第一刀从 r 位置切起，
         * 剩下的从 r + 1 的位置 ， 子过程你去给我切吧
         * */
        for (int x = r; x < arr.length; x++) {
            sum += arr[x];
            int nextPart = process(arr, x + 1, c - 1);
            int cur = Math.max(nextPart, sum);
            if (cur < min)
                min = cur;
        }
        return min;
    }


    private int dp(int[] arr, int rows, int cols) {
        int[][] dp = new int[rows + 1][cols];
//        dp[rows][all col] = 0
        int sum = 0;
//        int i = 0;
        /*
         * dp[r][c] 代表 arr[r---length] 上分c份， 总和是多少
         *
         * dp[r][1] 所以 就是反向求和
         * */
        for (int r = rows - 1; r >= 0; r--) {
            sum += arr[r];
            dp[r][1] = sum;
        }
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = 2; c < cols; c++) {
                int min = Integer.MAX_VALUE;
                sum = 0;
                for (int x = r; x < arr.length; x++) {
                    sum += arr[x];
                    int nextPart = dp[x + 1][c - 1];
                    int cur = Math.max(nextPart, sum);
                    if (cur < min)
                        min = cur;
                }
                dp[r][c] = min;
            }
        }
        return dp[0][cols - 1];
    }

    /*
     * TODO 四边形不等式优化
     * */
    private int quadrangleOptimization(int[] arr, int rows, int cols) {
        int[][] dp = new int[rows + 1][cols];
        int[][] choose = new int[rows + 1][cols];
//        dp[rows][all col] = 0
        int sum = 0;
//        int i = 0;
        /*
         * dp[r][c] 代表 arr[r---length] 上分c份， 总和是多少
         *
         * dp[r][1] 所以 就是反向求和
         * */
        for (int r = rows - 1; r >= 0; r--) {
            sum += arr[r];
            dp[r][1] = sum;
        }
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = 2; c < cols; c++) {
                int min = Integer.MAX_VALUE;
                sum = 0;

                int up = choose[r][c - 1] == 0 ? r : choose[r][c - 1];
                int down = choose[r + 1][c] == 0 ? rows : choose[r + 1][c];

                for (int x = r; x < rows; x++) {
                    sum += arr[x];
                    int nextPart = dp[x + 1][c - 1];
                    int cur = Math.max(nextPart, sum);
                    if (cur <= min) {
                        choose[r][c] = x;
                        min = cur;
                    }
                }
                dp[r][c] = min;
            }
        }
        return dp[0][cols - 1];
    }


    public static void main(String[] args) {

        Painter test = new Painter();
        int arr1[] = {13,6,3,2,5,7};
        int num1 = 5;
        test.awesome(arr1, num1);


        long violenceDpTimeCost = 0;
        long optimizeDpTimeCost = 0;
        for (int i = 0; i < 10000; i++) {
//            int[] arr = {1,3,5,2,6,6,8,10,1,15,6,17,6};
            int[] arr = LeetCodes.getRandomArray(1000, 100);

            int num = 10;


            long start = System.currentTimeMillis();
            int dp = test.dp(arr, arr.length, num + 1);
            violenceDpTimeCost += System.currentTimeMillis() - start;
            start = System.currentTimeMillis();
            int dpEdition2 = test.dpEdition2(arr, arr.length, num + 1);
            optimizeDpTimeCost += System.currentTimeMillis() - start;
            if (dp != dpEdition2)
                System.out.println("FUCK");

//            System.out.println(dp);
//
//            System.out.println(dpEdition2);
            //        int i = test.process(arr, 0, num - 1); // 暴力过程是切分几刀，num画家把数组切成 num-1份

//        int optimization = test.quadrangleOptimization(arr, arr.length, num + 1);
            //        System.out.println(i);
            //        System.out.println(optimization);

        }
        System.out.println("violenceDpTimeCost  " + violenceDpTimeCost);
        System.out.println("optimizeDpTimeCost  " + optimizeDpTimeCost);

    }

    private int dpEdition2(int[] arr, int rows, int cols) {
        /*
         * dp 新含义：
         * dp[r][c] 代表 0--r 的画 分给几个画家做
         * */
        int[][] dp = new int[rows + 1][cols];
        int[][] choose = new int[rows + 1][cols];
        int sum = 0;
        /*
         * 所有画 分给一个画家的时候
         * dp[length][1] = 数组累加和
         * */
        for (int r = 0; r < rows; r++) {
            sum += arr[r];
            dp[r][1] = sum;
        }
        /*
         * dp[0][num] 就是 0--0 的画分 num 个画家
         * 不管分多少个
         * 总是一个时间点完成
         * */
        for (int c = 1; c < cols; c++) {
            dp[0][c] = arr[0];
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 2; c < cols; c++) {
                int min = Integer.MAX_VALUE;

                int up = choose[r - 1][c] == 0 ? 1 : choose[r - 1][c];
                int down = c + 1 == cols ? r : choose[r][c + 1];

                for (int x = r; x >= up; x--) {
                    int cur = Math.max(dp[x - 1][c - 1], dp[r][1] - dp[x - 1][1]);
                    if (cur < min) {
                        choose[r][c] = x;
                        min = cur;
                    }
                }
                dp[r][c] = min;
            }
        }
        return dp[rows - 1][cols - 1];
    }

}
