package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/14 23:54
 */

import java.util.ArrayList;
import java.util.List;

public class L77_Combine {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {

        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = i+1;
        }
        List<Integer> path = new ArrayList<>();
        combine( arr,0, k ,0, path);
        return res;
    }

    public void combine(int[] arr,int curLen ,int k, int index, List<Integer> path) {

        if( curLen == k ){
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i< path.size(); i++){
                Integer get = path.get(i);
                list.add( get );
            }
            res.add(list);
            return;
        }

        if( index == arr.length ) return;

        for(int i = index; i < arr.length ; i++){
            path.add(arr[i]);
            combine(arr, curLen+1, k, index+1, path);
            path.remove( path.size()-1 );
        }
    }


    public static void main(String[] args) {
        new L77_Combine().combine(4, 2);
    }
}
