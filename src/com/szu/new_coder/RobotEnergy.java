package com.szu.new_coder;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 11:45
 */

import java.util.Scanner;

public class RobotEnergy {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int stages[] = new int[x];
        for(int i = 0; i < x; i++){
            stages[i] = sc.nextInt();
        }
        int min = getMaxEnergy(stages);
        System.out.println(min);
    }
    public static int getMaxEnergy(int[] stages){
        int energy = 0;
        for(int i = 0;i<stages.length; i++){
            for(stages[i] > energy){
                energy++;
            }
            if(stages[i] < energy){

            }
        }
        return energy;
    }
}
