package com.szu.new_coder.byte_dance;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *              TODO 没做出来
 * @Date 2021/3/12 11:45
 */

import java.util.Scanner;

public class RobotEnergy {
    static int initEnergy = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int stages[] = new int[x];
        for(int i = 0; i < x; i++){
            stages[i] = sc.nextInt();
        }
        int min = getMaxEnergy(stages, 0, 1);
        System.out.println(min);
    }

    public static int getMaxEnergy(int[] stages, int energy, int index){
        if (index == stages.length) return energy;
        if (energy - stages[index] > 0){
            energy += (energy - stages[index]);
            return getMaxEnergy(stages, energy, index+1);
        }

        while (energy - (stages[index] -energy) < 0)
            energy++;
        initEnergy += energy;
        return getMaxEnergy(stages, 0, index + 1);
    }
}
