package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 130. 被围绕的区域
给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
*
* 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
* 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/surrounded-regions
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/5/22 11:09
 */

import java.util.ArrayList;
import java.util.List;

public class L130_SurroundedRegions {
    /*
     * 并查集好像有点复杂
     *
     * 初始化并查集让所有的 O 各自独立
     *
     * 然后慢慢合并所有相邻的 集合
     *
     * 在合并的过程中，如果有一个集合能连接到边缘， 则合并的另一个集合也一定可以连接到边缘，并做好标记
     * */
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        int row = board.length;
        int col = board[0].length;
        UnionFind unionFind = new UnionFind(board);
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[r][c] == 'O') {
                    unionFind.union(r, c, r - 1, c);
                    unionFind.union(r, c, r + 1, c);
                    unionFind.union(r, c, r, c - 1);
                    unionFind.union(r, c, r, c + 1);
                }
            }
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[r][c] == 'O' && !unionFind.isOnBorder[unionFind.getFather(r * col + c)])
                    board[r][c] = 'X';
            }
        }
    }

    static class UnionFind {

        int[] parentMap;
        int[] sizeMap;
        boolean[] isOnBorder;
        int row;
        int col;
        char[][] board;

        public UnionFind(char[][] board) {
            row = board.length;
            col = board[0].length;
            int len = row * col;
            this.parentMap = new int[len];
            this.sizeMap = new int[len];
            this.isOnBorder = new boolean[len];
            this.board = board;
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c] == 'O') {
                        int index = r * col + c;
                        parentMap[index] = index;
                        sizeMap[index] = 1;
                        if (r == 0 || r == row - 1 || c == 0 || c == col - 1)
                            isOnBorder[index] = true;
                    }
                }
            }

        }

        public void union(int r1, int c1, int r2, int c2) {
            if (isValid(r2, c2)) {
                int index1 = r1 * col + c1;
                int index2 = r2 * col + c2;

                int father1 = getFather(index1);
                int father2 = getFather(index2);
                if (father1 != father2) {
                    int size1 = sizeMap[father1];
                    int size2 = sizeMap[father2];
                    int small = size1 > size2 ? father2 : father1;
                    int big = small == father1 ? father2 : father1;

                    sizeMap[big] = size1 + size2;
                    parentMap[small] = big;
                    if (isOnBorder[big] ^ isOnBorder[small]) /* 做好标记能连接到边缘的标记 */
                        isOnBorder[big] = true;
                }
            }
        }

        private int getFather(int index) {
            int father = parentMap[index];
            List<Integer> path = new ArrayList<>();
            while (father != index) {
                path.add(father);
                index = father;
                father = parentMap[index];
            }

            for (Integer integer : path) {
                parentMap[integer] = father;
            }
            return father;
        }

        private boolean isValid(int r2, int c2) {
            return r2 >= 0 && r2 < row && c2 >= 0 && c2 < col && board[r2][c2] == 'O';
        }
    }

    public static void main(String[] args) {
        L130_SurroundedRegions test = new L130_SurroundedRegions();
        char[][] board = {
                {'X', 'O', 'X', 'X'},
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X'}
        };

        test.solve(board);
        test.solveAwesome(board);
    }

    /*
     * 从边缘开始往里感染
     * 感染到的地方 设置为 '.'
     *
     * 感染完一遍之后，已经所有边可达的 'O' 改成了 '.'
     *
     * 再从头开始遍历所有的位置， 'O' -> 'X'    '.'->'O'
     * 因为现在仍然是 O 的 必然边不可达， 现在是 . 的 不需要变成 X
     * */
    public void solveAwesome(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int c = 0; c < col; c++) {
            if (board[0][c] == 'O')
                infect(0, c, board, visited);
            if (board[row - 1][c] == 'O')
                infect(row - 1, c, board, visited);
        }

        for (int r = 0; r < row; r++) {
            if (board[r][0] == 'O')
                infect(r, 0, board, visited);
            if (board[r][col - 1] == 'O')
                infect(r, col - 1, board, visited);
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[r][c] == 'O')
                    board[r][c] = 'X';
                if (board[r][c] == '.')
                    board[r][c] = 'O';
            }
        }
    }

    private void infect(int r, int c, char[][] board, boolean[][] visited) {
        if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && !visited[r][c] && board[r][c] == 'O') {
            visited[r][c] = true;
            infect(r - 1, c, board, visited);
            infect(r + 1, c, board, visited);
            infect(r, c + 1, board, visited);
            infect(r, c - 1, board, visited);
            board[r][c] = '.';
        }

    }


}
