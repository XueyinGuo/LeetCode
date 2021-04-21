package com.szu.new_coder.mei_tuan;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/18 20:22
 */

import java.util.Scanner;

public class Letters {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int users = scanner.nextInt();
        int letters = scanner.nextInt();
        int[] times = new int[users];
        for (int i = 0; i < users; i++) {
            times[i] = scanner.nextInt();
        }
        for (int i = 1; i < Long.MAX_VALUE && letters > 0; i++) {
            for (int j = 0; j < times.length; j++) {
                if (i % times[j] == 0){
                    System.out.println(j+1);
                    letters--;
                }
            }
        }
    }
}
