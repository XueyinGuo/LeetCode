package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      407. 接雨水 II
        给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 *
 * @Date 2021/4/10 15:59
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L407_CatchRainII {


    public static int trapRainWater(int[][] heightMap) {
        /*
        * 结题思路。
        * 先让整个矩阵的最外圈进入优先级队列，堆顶就是此时最薄弱的 瓶颈
        * 从一个瓶颈开始算起，每一个瓶颈必定连接一片湖（至于湖泊的水会不会在瓶颈处泄露出去就 + 0 就好了）
        *
        * 每个位置都会把自己四周的位置拉入队列，但是每个位置只能进入一次队列
        * 所以需要一个boolean矩阵来记录每个位置是否进入过队列
        *
        * TODO 但是此段代码过于垃圾，只击败了 双 10% 以内，有待于优化！
        * */
        PriorityQueue<CatchRainNode> queue = new PriorityQueue<>(new Comparator<CatchRainNode>() {
            @Override
            public int compare(CatchRainNode o1, CatchRainNode o2) {
                return o1.height - o2.height;
            }
        });

        int totalCols = heightMap[0].length;
        int totalRows = heightMap.length;
        boolean[][] flag = new boolean[totalRows][totalCols];


        int row = 0, col = 0;
        while (col < totalCols - 1){
            queue.add(new CatchRainNode(heightMap[row][col], row, col));
            flag[row][col] = true;
            col++;
        }
        while (row < totalRows - 1){
            queue.add( new CatchRainNode(heightMap[row][col], row, col) );
            flag[row][col] = true;
            row++;
        }
        while (col > 0){
            queue.add( new CatchRainNode(heightMap[row][col], row, col) );
            flag[row][col] = true;
            col--;
        }
        while (row > 0){
            queue.add( new CatchRainNode(heightMap[row][col], row, col) );
            flag[row][col] = true;
            row--;
        }

        return trapRainWater(heightMap, flag, queue);
    }

    private static int trapRainWater(int[][] heightMap, boolean[][] flag, PriorityQueue<CatchRainNode> queue) {



        int bottleNeck = Integer.MIN_VALUE;
        int rain = 0;
        while (!queue.isEmpty()){

            CatchRainNode poll = queue.poll();
            /* 元素出队列的时候需要更新 瓶颈值 */
            bottleNeck = Math.max( poll.height, bottleNeck );
            /*
            * 点出队列的时候 四个位置需要进入队列
            * */
            int pollUpRow = poll.row - 1, pollUpCol = poll.col;
            int pollDownRow = poll.row + 1, pollDownCol = poll.col;
            int pollLeftRow = poll.row, pollLeftCol = poll.col + 1;
            int pollRightRow = poll.row, pollRightCol = poll.col - 1;
            if (isValid(pollUpRow, pollUpCol, heightMap, flag)){ /* 判断某个位置是否合法，是否越界，是否已经进入过队列 */
                flag[pollUpRow][pollUpCol] = true;
                rain += getCapacity(heightMap, pollUpRow, pollUpCol, bottleNeck); /* 计算这个位置的容量，要么 > 0 , 要么 为 0 */
                queue.offer( new CatchRainNode(heightMap[pollUpRow][pollUpCol] , pollUpRow, pollUpCol ) );
            }
            if (isValid(pollDownRow, pollDownCol, heightMap, flag)){
                flag[pollDownRow][pollDownCol] = true;
                rain += getCapacity(heightMap, pollDownRow, pollDownCol, bottleNeck);
                queue.offer( new CatchRainNode(heightMap[pollDownRow][pollDownCol] , pollDownRow, pollDownCol ) );
            }
            if (isValid(pollLeftRow, pollLeftCol, heightMap, flag)){
                flag[pollLeftRow][pollLeftCol] = true;
                rain += getCapacity(heightMap, pollLeftRow, pollLeftCol, bottleNeck);
                queue.offer( new CatchRainNode(heightMap[pollLeftRow][pollLeftCol] , pollLeftRow, pollLeftCol ) );
            }
            if (isValid(pollRightRow, pollRightCol, heightMap, flag)){
                flag[pollRightRow][pollRightCol] = true;
                rain += getCapacity(heightMap, pollRightRow, pollRightCol, bottleNeck);
                queue.offer( new CatchRainNode(heightMap[pollRightRow][pollRightCol] , pollRightRow, pollRightCol ) );
            }
        }
        return rain;
    }

    private static boolean isValid(int row, int col, int[][] heightMap, boolean[][] flag) {
        if (row >= 0 && row < heightMap.length &&
                col >= 0 && col < heightMap[0].length &&
                !flag[row][col])
            return true;
        return false;

    }

    public static int getCapacity(int[][] heightMap, int row, int col, int bottleNeck){
        int height = heightMap[row][col];
        int cap = bottleNeck - height ;
        return cap > 0 ? cap : 0;
    }

}
// 每个位置都会把自己建立成一个节点，节点信息包含自己高度信息和 行列为止信息
// 方便自己弹出队列的时候把周围另外的元素也拉进队列
class CatchRainNode{

    int height;
    int row;
    int col;

    public CatchRainNode(int height, int row, int col) {
        this.height = height;
        this.row = row;
        this.col = col;
    }
}

