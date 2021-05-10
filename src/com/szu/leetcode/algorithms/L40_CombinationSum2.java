package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      40. 组合总和 II
 *
 *      给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
        candidates 中的每个数字在每个组合中只能使用一次。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/combination-sum-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/3/16 21:26
 */

import java.util.*;

public class L40_CombinationSum2 {

    Set<List<Integer>> resultSet = new HashSet<>();
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        ArrayList<Integer> path = new ArrayList<>();
        getResult( candidates ,0, path, 0 , target);
        for(List<Integer> list : resultSet)
            res.add(list);
        return res;
    }

    public void getResult(int[] candidates, int index ,ArrayList<Integer> path, int curSum, int target ){

        if(curSum > target) return;

        if(curSum == target){
            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0; i<path.size(); i++){
                list.add(path.get(i));
            }
            resultSet.add(list);
            return;
        }
        if(index == candidates.length)
            return;

        for(int i = index; i < candidates.length; i++){

            path.add(candidates[i]);

            getResult(candidates, i+1, path, curSum+candidates[i], target);

            path.remove(path.size()-1);

        }

    }


    public static void main(String[] args) {
        int arr[] = {1,5,5};
        int num = 6;
        L40_CombinationSum2 test = new L40_CombinationSum2();
        test.combinationSum2(arr, num);
    }
}
