package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *          52. N 皇后 II
 *
 *          n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *          给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *
 *          来源：力扣（LeetCode）
 *          链接：https://leetcode-cn.com/problems/n-queens-ii
 *          著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/2/20 20:08
 */

public class L52_SolveNQueens {

    public int totalNQueens(int n) {

        int locations[] = new int[n];

        return doSolveNQueens(n , locations, 0);
    }

    public int doSolveNQueens(int n, int[] locations, int row){
        if (row == n){
            return 1;
        }
        int num = 0;
        for (int col = 0; col < n; col++) {
            locations[row] = col;
            if (isValid(locations, row, col)){
                num += doSolveNQueens(n, locations, row + 1);
            }
        }
        return num;
    }



    private boolean isValid(int[] locations, int row, int col) {

        for (int i = 0; i < row; i++) {
            // 遍历看看之前摆过的位置是不是和现在同列（数组中记录的都是列值，因为行使用下标表示的）
            if (locations[i] == col ||
                    // 同斜线，每一行的行号 减去 当前判断的行号 == 行对应列的值 - 当前判断的列
                    // 如果二者绝对值相等，那么二者共斜线
                 Math.abs( i - row) == Math.abs(locations[i] - col)  ){
                // 满足上面任意一种情况，返回false，不合法
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new L52_SolveNQueens().totalNQueens(10));

    }
}
