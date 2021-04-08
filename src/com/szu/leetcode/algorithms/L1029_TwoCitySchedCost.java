package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/2/18 20:48
 */

import java.util.Arrays;
import java.util.Comparator;

public class L1029_TwoCitySchedCost {

    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort( costs, new Comparator<int[]>(){

            public int compare(int[] c1, int[] c2){
                return  (c1[0] - c1[1]) - (c2[0] - c2[1]) ;
            }

        } );


        int total = 0;
        int n = costs.length / 2;
        for(int i = 0; i< n; i++){
            total += (costs[i][0] + costs[ i + n][1]);
        }
        return total;
    }

}
