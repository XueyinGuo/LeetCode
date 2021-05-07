package com.szu.training03.class04;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 一条直线上有居民点，邮局只能建立在居民点上。给定一个有序正数数组 arr，每个值表示居民点的一维坐标
 * 再给定一个正数 num，表示邮局数量。
 * 选择 num 个居民点建立 num 个邮局，使所有居民点到最近邮局的总距离最短，返回最短的总距离
 *
 * 【举例】
 * arr = [1,2,3,4,5,1000], num = 2
 *
 * 第一个邮局建立在 3 位置， 第二个邮局建立在 1000 位置。
 * (3-1) + (3-2) + (3-3) + (4-3) + (5-3) + (1000 - 1000) = 6
 *
 * @Date 2021/5/7 11:37
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;
import java.util.Random;

public class PostOffice {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
//            int[] arr = {1, 3, 8, 9, 10, 12, 15, 20,30,50,71,73,85,94};
//        int[] arr = {1, 3, 8, 10, 12};
            int[] arr = LeetCodes.getRandomArray(200, 500);
            Arrays.sort(arr);
            int num = random.nextInt(20) + 5;
            int violence = violenceDp(arr, num);
            int optimize = quadrangleOptimization(arr, num);
            int optimize2 = quadrangleOptimization2(arr, num);
            if (violence != optimize || optimize != optimize2)
                System.out.println("FUCK");
        }


    }

    /*
     * ==========================
     * ==========================
     * 四边形不等式优化版本 DP
     * ==========================
     * ==========================
     * */
    private static int quadrangleOptimization(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num >= arr.length)
            return 0;
        /*
         * 获取到了 一段距离中 只修一个居民点的 距离代价
         * */
        int[][] distanceMap = getDistanceMap(arr);
        int rows = arr.length;
        /*
         * 在 0--r 居民点上修建 c 个邮局
         * 第一列弃而不用
         * */
        int[][] dp = new int[rows][num + 1];
        int[][] choose = new int[rows][num + 1];
        for (int r = 0; r < rows; r++) {
            dp[r][1] = distanceMap[0][r]; // 初始化 dp， 表示 0--r 上修建 1 个邮局的最小距离代价
        }
        /* 0--1 上修一个，距离代价就是 0--1的距离嘛 */
        dp[1][1] = distanceMap[0][1];
        // 第二行填完一个就完了， 然而第一行 无论如何都是 0， 因为是在 0号居民点上 修 >= 1 个邮局。
        // 然后就可以从第三行开始了
        for (int r = 2; r < rows; r++) {
            for (int c = num; c >= 2; c--) {
                /*
                 * 尝试的上界和下界
                 *
                 * 假设 现在需要求解 dp[9][6]
                 * 我们依赖的位置 是 dp[0--8][5] + distanceMap[1--9][9]
                 *
                 * 此时我们一定已经求解过了 dp[8][5]， 假设 dp[8][5]的最优邮局建立点在 3号居民点，
                 * 那我们还有什么必要打破之前的最优？ 直接从 3 开始试起不就完了？
                 *
                 * 同样的，既然 dp 不依赖本行位置，我完全可以求出所求位置的的下一个位置 dp[9][7],
                 * 假设 dp[9][7] 的最优位置为 5 号居民点，那么我还有什么必要重试 后边的最优情况呢？
                 * 只尝试中间位置不就好了？
                 * */
                dp[r][c] = distanceMap[0][r];
                int up = choose[r - 1][c];
                up = up == 0 ? 1 : up;
                int down = c == num ? r : choose[r][c + 1]; // 如果 当前的列是我最右边的位置，就认为没有上界
                for (int x = up; x <= down; x++) {

                    int cur = dp[x - 1][c - 1] + distanceMap[x][r];
                    if (dp[r][c] > cur) {
                        dp[r][c] = cur;
                        choose[r][c] = x;
                    }

                }
            }
        }
        return dp[rows - 1][num];
    }


    /*
     * ==========================
     * ==========================
     * 四边形不等式优化版本 DP
     * ==========================
     * ==========================
     * */
    private static int quadrangleOptimization2(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num >= arr.length)
            return 0;
        /*
         * 获取到了 一段距离中 只修一个居民点的 距离代价
         * */
        int[][] distanceMap = getDistanceMap(arr);
        int rows = arr.length;
        /*
         * 在 0--r 居民点上修建 c 个邮局
         * 第一列弃而不用
         * */
        int[][] dp = new int[rows][num + 1];
        int[][] choose = new int[rows][num + 1];
        for (int r = 0; r < rows; r++) {
            dp[r][1] = distanceMap[0][r]; // 初始化 dp， 表示 0--r 上修建 1 个邮局的最小距离代价
        }
        /* 0--1 上修一个，距离代价就是 0--1的距离嘛 */
        dp[1][1] = distanceMap[0][1];
        // 第二行填完一个就完了， 然而第一行 无论如何都是 0， 因为是在 0号居民点上 修 >= 1 个邮局。
        // 然后就可以从第三行开始了
        for (int r = 2; r < rows; r++) {
            for (int c = num; c >= 2; c--) {
                /*
                 * 尝试的上界和下界
                 *
                 * 假设 现在需要求解 dp[9][6]
                 * 我们依赖的位置 是 dp[0--8][5] + distanceMap[1--9][9]
                 *
                 * 此时我们一定已经求解过了 dp[8][5]， 假设 dp[8][5]的最优邮局建立点在 3号居民点，
                 * 那我们还有什么必要打破之前的最优？ 直接从 3 开始试起不就完了？
                 *
                 * 同样的，既然 dp 不依赖本行位置，我完全可以求出所求位置的的下一个位置 dp[9][7],
                 * 假设 dp[9][7] 的最优位置为 5 号居民点，那么我还有什么必要重试 后边的最优情况呢？
                 * 只尝试中间位置不就好了？
                 * */
                dp[r][c] = dp[r - 1][c - 1];
                choose[r][c] = r;
                int up = choose[r - 1][c];
                up = up == 0 ? 1 : up;
                int down = c == num ? r : choose[r][c + 1]; // 如果 当前的列是我最右边的位置，就认为没有上界

                for (int x = up; x <= down; x++) {
                    int cur = dp[x - 1][c - 1] + distanceMap[x][r];
                    if (cur < dp[r][c]) {
                        dp[r][c] = cur;
                        choose[r][c] = x;
                    }

                }

            }
        }
        return dp[rows - 1][num];
    }


    private static int violenceDp(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num >= arr.length)
            return 0;
        /*
         * 获取到了 一段距离中 只修一个居民点的 距离代价
         * */
        int[][] distanceMap = getDistanceMap(arr);
        int rows = arr.length;
        /*
         * 在 0--r 居民点上修建 c 个邮局
         * 第一列弃而不用
         * */
        int[][] dp = new int[rows][num + 1];
        for (int r = 0; r < rows; r++) {
            dp[r][1] = distanceMap[0][r]; // 初始化 dp， 表示 0--r 上修建 1 个邮局的最小距离代价
        }
        /* 0--1 上修一个，距离代价就是 0--1的距离嘛 */
        dp[1][1] = distanceMap[0][1];
        // 第二行填完一个就完了， 然而第一行 无论如何都是 0， 因为是在 0号居民点上 修 >= 1 个邮局。
        // 然后就可以从第三行开始了
        for (int r = 2; r < rows; r++) {
            for (int c = 2; c <= num; c++) {
                /* 假设 dp[7][3],意思是 0--7号居民点上建立 3 个邮局，最小距离代价是多少 */
                /*
                 * 我上来二话不说，直接就把邮局修正了 7 号居民点，
                 * 则 dp 的值就是 【0--6号居民点上 修 2 个邮局的距离代价】 +【7号居民点 到 7号居民点的距离，也就是 0】
                 * dp[7][3] = dp[6][2] + distanceMap[7][7]
                 * */
                dp[r][c] = dp[r - 1][c - 1] + distanceMap[r][r];
                for (int x = r - 2; x >= 0; x--) {
                    int cur = dp[x][c - 1] + distanceMap[x + 1][r];
                    if (cur < dp[r][c])
                        dp[r][c] = cur;
                }
            }
        }

        return dp[rows - 1][num];
    }

    private static int[][] getDistanceMap(int[] arr) {
        int length = arr.length;
        int[][] distanceMap = new int[length][length];
        /* 每两个居民点修一个邮局的时候，距离总是两个点的差值 */
        for (int i = 0; i < length - 1; i++)
            distanceMap[i][i + 1] = arr[i + 1] - arr[i];

//        int x = arr.length - 3;
//        int r = x;
//        while (x >= 0) {
//            r = x;
//            int c = length - 1;
//            while (r >= 0){
//                distanceMap[r][c] = distanceMap[r+1][c] + distanceMap[r][c-1];
//                r--;
//                c--;
//            }
//            x--;
//        }
        for (int r = 0; r < length - 1; r++) {
            for (int c = r + 2; c < length; c++) {
                /* 多个点共享一个邮局的时候 */
                /*
                 * |---↓---|             0---2上的总距离，就是 0--1的距离 + 2号居民点 到 1号居民点的距离
                 * 1,  3,  8,  10,  12   dp[0][2] = dp[0][1] + arr[2] - arr[1]
                 *
                 * |---↓--------|        0---3上的总距离，就是 0--2的距离 + 3号居民点 到 1号居民点的距离
                 * 1,  3,  8,  10,  12   dp[0][3] = dp[0][2] + arr[3] - arr[1] = dp[0][2] + arr[3] - arr[2]
                 *
                 *     |---↓----|        1---3上的总距离，就是 1--2的距离 + 3号居民点 到 2号居民点的距离
                 * 1,  3,  8,  10,  12   dp[1][3] = dp[1][2] + arr[3] - arr[2]
                 *
                 * */
                distanceMap[r][c] = distanceMap[r][c - 1] + arr[c] - arr[r + ((c - r) / 2)];
            }
        }

        return distanceMap;
    }


}
