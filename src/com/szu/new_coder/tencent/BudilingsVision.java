package com.szu.new_coder.tencent;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 22:17
 */

import java.util.Scanner;

public class BudilingsVision {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] budilings = new int[num];
        for (int i = 0; i < num; i++) {
            budilings[i] = sc.nextInt();
        }
        int[] res = findVision(budilings);
        System.out.println();
    }

    public static int[] findVision(int[] budilings){
        int len = budilings.length;
        int[] res = new int[len];
        for(int i = 0; i<len; i++){
            int lHigh = 0;
            int rHigh = len;
            for(int j = i; j>=0; j--){
                if(budilings[i] < budilings[j]){
                    lHigh = j;
                    break;
                }
            }
            for(int j = i; j<len; j++){
                if(budilings[i] < budilings[j]){
                    rHigh = j;
                    break;
                }
            }
            res[i] = rHigh - lHigh + 1;
        }
        return res;
    }

}
