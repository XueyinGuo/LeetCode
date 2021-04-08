package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *    77. 组合
 *       给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * @Date 2021/3/14 23:54
 */

import java.util.ArrayList;
import java.util.List;

public class L77_Combine {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if( k > n || n <= 0 ) return res;
        generate(n , k, new ArrayList<Integer>(), 1);
        return res;
    }

    public void generate(int n, int k, List<Integer> curList, int start){
        if(curList.size() == k){
            List<Integer> list = new ArrayList<>();
            for(Integer i : curList)
                list.add(i);
            res.add(list);
            return;
        }
        /*
        * 剪枝操作
        *
        * 当起始位置 到 终点位置 的距离 + 已经加到列表中的 长度 无论如何都不够 k 长度的时候直接就不搞了，直接返回吧亲
        *
        * 尝试下一种组合结果
        * */
        if(n - start + curList.size() + 1 < k)
            return;

        for(int i = start; i <= n; i++){

            curList.add(i);
            generate(n, k, curList, i + 1);
            curList.remove( curList.size() - 1 );
        }

    }


    public static void main(String[] args) {
        List<List<Integer>> combine = new L77_Combine().combine(4, 2);
        System.out.println();
    }
}
