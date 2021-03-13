package com.szu.new_coder.tencent;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 22:17
 */

import java.util.Scanner;

public class BuildingsVision {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] buildings = new int[num];
        for (int i = 0; i < num; i++) {
            buildings[i] = sc.nextInt();
        }
        int[] res = findVision(buildings);
        for (int i = 0; i < res.length-1; i++) {
            System.out.print(res[i]+" ");
        }
        System.out.print(res[res.length-1]);
    }

    public static int[] findVision(int[] buildings) {
        int len = buildings.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int l_max = buildings[i], r_max = buildings[i];
            int vision = 1;

            if (i > 0) {
                // 往左
                vision += findLeftVision(buildings, i);
            }
            if (i < len - 1) {
                // 往右
                vision += findRightVision(buildings, i);
            }

            res[i] = vision;
        }
        return res;
    }

    private static int findRightVision(int[] buildings, int index) {
        int curMax = buildings[index + 1];
        int vision = 0;
        for (int i = index + 1; i < buildings.length; i++) {
            if (buildings[i] >= curMax) {
                vision++;
                curMax = buildings[i];
            }
        }
        return vision;


    }

    private static int findLeftVision(int[] buildings, int index) {


        int curMax = buildings[index - 1];
        int vision = 0;
        for (int i = index - 1; i >= 0; i--) {
            if (buildings[i] >= curMax) {
                vision++;
                curMax = buildings[i];
            }
        }
        return vision;
    }
}
