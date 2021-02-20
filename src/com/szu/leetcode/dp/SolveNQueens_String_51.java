package com.szu.leetcode.dp;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *          51. N 皇后
 *
 *          n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *           给你一个整数 n ，返回所有不同的n皇后问题 的解决方案。
 *
 *           每一种解法包含一个不同的n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *
 *
 *           来源：力扣（LeetCode）
 *           链接：https://leetcode-cn.com/problems/n-queens
 *           著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/2/20 20:08
 */

import java.util.ArrayList;
import java.util.List;

public class SolveNQueens_String_51 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        /*
        * 假设 n 为 4
        * 创建一个数组表示皇后位置，其中数组下表代表所在的行，
        * 数组下表存储的值代表这一行皇后的位置
        * 【1，3,0,2】
        * 第0行的皇后位置摆在1位置
        * .Q..
        * ...Q
        * Q...
        * ..Q.
        * */
        int locations[] = new int[n];
        /*
        * row 代表现在来到了数组的第几行
        * 结果存在result中
        * */
        doSolveNQueens(n , locations, 0, result);
        return result;
    }

    public void doSolveNQueens(int n, int[] locations, int row, List<List<String>> result){
        /*
        * 能进入这个判断代表所有位置都已经判断好了
        * 现在的 locations 数组中都是合法的位置
        * 按照位置遍历构造字符串即可
        * */
        if (row == n){
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < n; j++) {
                    if (j == locations[i]){
                        sb.append("Q");
                    }else {
                        sb.append(".");
                    }
                }
                strings.add(sb.toString());
            }
            result.add(strings);
            return;
        }
        /*
        * 从第 row 行第 0 列开始判断
        * */
        for (int col = 0; col < n; col++) {
            //先把皇后摆在 col 来到的位置上
            locations[row] = col;
            // 然后判断这个位置合不合法
            // 不合法就 for 循环下一个位置
            // 当前位置合法，再去判断下一行，下一行又是从 第 0 列开始往后慢慢判断的
            if (isValid(locations, row, col)){
                doSolveNQueens(n, locations, row + 1, result);
            }
        }
    }

    private boolean isValid(int[] locations, int row, int col) {
        /*
        * 每个皇后按照这个逻辑下来肯定不会同行的
        * 所以只要保证不要同列和同斜线即可
        *
        * 现在只是看看第 row 行是否合法，row 之下的先不考虑
        * */
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
        List<List<String>> lists = new SolveNQueens_String_51().solveNQueens(4);
        for (List<String> stringList: lists             ) {
            for (String s: stringList                 ) {
                System.out.println(s);
            }
            System.out.println();
        }
    }
}
