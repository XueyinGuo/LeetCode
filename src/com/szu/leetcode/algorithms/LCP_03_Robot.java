package com.szu.leetcode.algorithms;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/18 11:36
 */

import java.util.HashMap;
import java.util.HashSet;

public class LCP_03_Robot {

    public boolean robot(String command, int[][] obstacles, int x, int y) {
        char[] commands = command.toCharArray();
        int curX = 0;
        int curY = 0;
        HashMap<Integer, HashSet<Integer>> traps = new HashMap<>();
        for (int i = 0; i < obstacles.length; i++) {
            HashSet<Integer> ySet = traps.get(obstacles[i][0]);
            if (ySet == null) {
                HashSet<Integer> ys = new HashSet<>();
                ys.add(obstacles[i][1]);
                traps.put(obstacles[i][0], ys);
            } else
                ySet.add(obstacles[i][1]);

        }
        int i = 0;
        int lastCommandIndex = commands.length - 1;
        while (true) {
            /* 向上走 */
            if (commands[i] == 'U')
                curY++;

            else
                /* 向右走 */
                curX++;


            if (isDamagedOrPassEnd(curX, curY, x, y, traps))
                return false;
            if (curX == x && curY == y)
                return true;

            i = i == lastCommandIndex ? 0 : i + 1;
        }
    }

    private boolean isDamagedOrPassEnd(int curX, int curY, int x, int y, HashMap<Integer, HashSet<Integer>> traps) {

        HashSet<Integer> ySet = traps.get(curX);
        if (ySet != null && ySet.contains(curY))
            return true;
        if (curX > x || curY > y)
            return true;
        return false;
    }

    public static void main(String[] args) {
        String c = "UR";
        int[][] ob = {};
        int x = 20;

        int y = 20;
        LCP_03_Robot test = new LCP_03_Robot();
        test.robot(c, ob, x, y);
        test.robotAwesome(c, ob, x, y);
    }


    public boolean robotAwesome(String command, int[][] obstacles, int x, int y) {
        //多次循环 找到模式
        //学到了新的存储坐标的方法  左坐标左移30 | 右坐标
        int xx = 0, yy = 0;
        HashSet<Long> set = new HashSet<>();
        set.add(((long) xx << 30) | yy);
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'U') {
                yy++;
            } else {
                xx++;
            }
            set.add(((long) xx << 30) | yy);
        }
        int cir = Math.min(x / xx, y / yy);
        if (set.contains(((long) (x - cir * xx) << 30) | (y - cir * yy)) == false) {
            return false;
        }
        for (int[] s : obstacles) {
            if (s.length != 2) continue;
            int x1 = s[0];
            int y1 = s[1];
            if (x1 > x || y1 > y) continue;
            cir = Math.min(x1 / xx, y1 / yy);
            if (set.contains(((long) (x1 - cir * xx) << 30) | (y1 - cir * yy)) == true) {
                return false;
            }
        }
        return true;
    }
}
