package com.szu.leetcode;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/18 11:36
 */

import java.util.HashMap;

public class LCP_03_Robot {

    public boolean robot(String command, int[][] obstacles, int x, int y) {
        int a = 0;
        int b = 0;
        char[] commands = command.toCharArray();
        int len = commands.length;
        HashMap<Integer, Integer> damagedLocation = new HashMap<>();
        createDamagedMap(obstacles, damagedLocation);
        for(int k = 0; k < len; k = (k+1)%len){
            if(commands[k] == 'U') {
                b++;
                Integer yres = damagedLocation.get( a );
                if( yres != null && yres == b )
                    return false;
            }
            if(commands[k] == 'R'){
                a++;
                Integer yres = damagedLocation.get( a );
                if( yres != null && yres == b )
                    return false;
            }
            if(a == x && b == y)
                return true;
            if(a > x && b > y )
                break;
        }

        return false;
    }

    public void createDamagedMap(int[][] obstacles, HashMap<Integer, Integer> damagedLocation){
        for(int i = 0; i < obstacles.length; i++){

            damagedLocation.put(obstacles[i][0],obstacles[i][1] );

        }

    }


    public static void main(String[] args) {

        String command = "RRU";
        int[][] obstacles =       {{5, 5}, {9, 4}, {9, 7}, {6, 4}, {7, 0}, {9, 5}, {10, 7}, {1, 1}, {7, 5}};
        int x = 1486;
        int y = 743;
        new LCP_03_Robot().robot(command, obstacles, x, y);
    }
}
