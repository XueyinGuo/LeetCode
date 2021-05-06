package com.szu.training03.class02;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * TODO
 *
 * @Date 2021/5/6 20:13
 */

import com.szu.leetcode.utils.LeetCodes;

public class PathsToCapital {

    public static void pathsToNums(int[] paths) {
        if (paths == null || paths.length == 0) {
            return;
        }
        // citiesPath -> distancesArray
        pathsToDistances(paths);

        // distancesArray -> countsArray
//        distansToNums(paths);
    }

    public static void pathsToDistances(int[] paths) {
        int last = 0, next = 0;
        int i = 0;
        while (i < paths.length){
            int j = i;
            next = paths[i];
            while (paths[next] >= 0 && paths[next] != next){
                last = j;
                j = next;
                next = paths[next];
                paths[j] = last;
            }
            last = j;
            next = paths[last];
            paths[last] = -1;
            while (last != i){
                last = next;


            }


            i++;
        }
    }


    public static void main(String[] args) {
        int[] paths = { 9, 1, 4, 9, 0, 4, 8, 9, 0, 1 };
        LeetCodes.printArray(paths);
        pathsToNums(paths);
        LeetCodes.printArray(paths);

    }

}
