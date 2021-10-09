package com.szu;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int[][] grid = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    grid[i][j] = scanner.nextInt();
                }
            }
            System.out.println(trapRainWater(grid));
        }
    }


    public static int trapRainWater(int[][] heightMap) {
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
        while (col < totalCols - 1) {
            queue.add(new CatchRainNode(heightMap[row][col], row, col));
            flag[row][col] = true;
            col++;
        }
        while (row < totalRows - 1) {
            queue.add(new CatchRainNode(heightMap[row][col], row, col));
            flag[row][col] = true;
            row++;
        }
        while (col > 0) {
            queue.add(new CatchRainNode(heightMap[row][col], row, col));
            flag[row][col] = true;
            col--;
        }
        while (row > 0) {
            queue.add(new CatchRainNode(heightMap[row][col], row, col));
            flag[row][col] = true;
            row--;
        }

        return trapRainWater(heightMap, flag, queue);
    }

    private static int trapRainWater(int[][] heightMap, boolean[][] flag, PriorityQueue<CatchRainNode> queue) {


        int bottleNeck = Integer.MIN_VALUE;
        int rain = 0;
        while (!queue.isEmpty()) {

            CatchRainNode poll = queue.poll();
            bottleNeck = Math.max(poll.height, bottleNeck);
            int pollUpRow = poll.row - 1, pollUpCol = poll.col;
            int pollDownRow = poll.row + 1, pollDownCol = poll.col;
            int pollLeftRow = poll.row, pollLeftCol = poll.col + 1;
            int pollRightRow = poll.row, pollRightCol = poll.col - 1;
            if (isValid(pollUpRow, pollUpCol, heightMap, flag)) {
                flag[pollUpRow][pollUpCol] = true;
                rain += getCapacity(heightMap, pollUpRow, pollUpCol, bottleNeck);
                queue.offer(new CatchRainNode(heightMap[pollUpRow][pollUpCol], pollUpRow, pollUpCol));
            }
            if (isValid(pollDownRow, pollDownCol, heightMap, flag)) {
                flag[pollDownRow][pollDownCol] = true;
                rain += getCapacity(heightMap, pollDownRow, pollDownCol, bottleNeck);
                queue.offer(new CatchRainNode(heightMap[pollDownRow][pollDownCol], pollDownRow, pollDownCol));
            }
            if (isValid(pollLeftRow, pollLeftCol, heightMap, flag)) {
                flag[pollLeftRow][pollLeftCol] = true;
                rain += getCapacity(heightMap, pollLeftRow, pollLeftCol, bottleNeck);
                queue.offer(new CatchRainNode(heightMap[pollLeftRow][pollLeftCol], pollLeftRow, pollLeftCol));
            }
            if (isValid(pollRightRow, pollRightCol, heightMap, flag)) {
                flag[pollRightRow][pollRightCol] = true;
                rain += getCapacity(heightMap, pollRightRow, pollRightCol, bottleNeck);
                queue.offer(new CatchRainNode(heightMap[pollRightRow][pollRightCol], pollRightRow, pollRightCol));
            }
        }
        return rain;
    }

    private static boolean isValid(int row, int col, int[][] heightMap, boolean[][] flag) {
        return row >= 0 && row < heightMap.length &&
                col >= 0 && col < heightMap[0].length &&
                !flag[row][col];

    }

    public static int getCapacity(int[][] heightMap, int row, int col, int bottleNeck) {
        int height = heightMap[row][col];
        int cap = bottleNeck - height;
        return Math.max(cap, 0);
    }

}

class CatchRainNode {

    int height;
    int row;
    int col;

    public CatchRainNode(int height, int row, int col) {
        this.height = height;
        this.row = row;
        this.col = col;
    }
}