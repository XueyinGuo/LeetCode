package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
   741. 摘樱桃
    一个N x N的网格(grid) 代表了一块樱桃地，每个格子由以下三种数字的一种来表示：
    
    0 表示这个格子是空的，所以你可以穿过它。
    1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。
    -1 表示这个格子里有荆棘，挡着你的路。
    你的任务是在遵守下列规则的情况下，尽可能的摘到最多樱桃：

    从位置 (0, 0) 出发，最后到达 (N-1, N-1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为0或者1的格子）；
    当到达 (N-1, N-1) 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子；
    当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为0）；
    如果在 (0, 0) 和 (N-1, N-1) 之间不存在一条可经过的路径，则没有任何一个樱桃能被摘到。
 *
 * @Date 2021/4/26 15:18
 */

public class L741_CherryPickup {

    public static int cherryPickup(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int[][][] dp = new int[rows][cols][rows];
        int ans = cherryPickup(grid, 0, 0, 0, dp, rows, cols);
        return ans < 0 ? 0 : ans;

    }

    private static int cherryPickup(int[][] grid, int Ar, int Ac, int Br, int[][][] dp, int rows, int cols) {
        int Bc = Ar + Ac - Br;

        if (dp[Ar][Ac][Br] != 0)
            return dp[Ar][Ac][Br];

        if (Ar == rows - 1 && Ac == cols - 1) {
            dp[Ar][Ac][Br] = grid[Ar][Ac];
            return dp[Ar][Ac][Br];
        }

        int next = -1;
        // 右右
        int rightRight = -1;
        // 右下
        int rightDown = -1;
        // 下下
        int downDown = -1;
        // 下右
        int downRight = -1;
        if (Ac + 1 < cols && Bc + 1 < cols)
            rightRight = cherryPickup(grid, Ar, Ac + 1, Br, dp, rows, cols);
        if (Ac + 1 < cols && Br + 1 < rows)
            rightDown = cherryPickup(grid, Ar, Ac + 1, Br + 1, dp, rows, cols);
        if (Ar + 1 < rows && Br + 1 < rows)
            downDown = cherryPickup(grid, Ar + 1, Ac, Br + 1, dp, rows, cols);
        if (Ar + 1 < rows && Bc + 1 < cols)
            downRight = cherryPickup(grid, Ar + 1, Ac, Br, dp, rows, cols);

        if (rightRight > next)
            next = rightRight;
        if (rightDown > next)
            next = rightDown;
        if (downDown > next)
            next = downDown;
        if (downRight > next)
            next = downRight;
        /*
        * 但凡走到障碍物上，这条路就白费劲了，即使越过这个障碍之后可以收到樱桃，也全部放弃
        *  */
        if (grid[Ar][Ac] == -1 || grid[Br][Bc] == -1 || next == -1){
            dp[Ar][Ac][Br] = -1;
            return dp[Ar][Ac][Br];
        }

        if (Ar == Br) {
            dp[Ar][Ac][Br] = next + grid[Ar][Ac];
            return dp[Ar][Ac][Br];
        }
        dp[Ar][Ac][Br] = next + grid[Ar][Ac] + grid[Br][Bc];
        return dp[Ar][Ac][Br];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0,1,-1},
                {1,0,-1},
                {1,1,1}
        };
        System.out.println(cherryPickup(matrix));
    }
}
