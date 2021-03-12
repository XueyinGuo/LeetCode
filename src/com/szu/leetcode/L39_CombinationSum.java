package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      39. 组合总和
 *
 *      给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

        candidates 中的数字可以无限制重复被选取。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/combination-sum
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/2/28 22:58
 */

import java.util.*;

public class L39_CombinationSum {

    List<List<Integer>> res = new ArrayList<>();
    Set<List<Integer>> set = new HashSet<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        LinkedList<Integer> path = new LinkedList<>();
        findCombinationSum( candidates, target, path,0 );
        for(List<Integer> list : set)
            res.add(list);
        return res;
    }

    public void findCombinationSum(int[] candidates, int target, LinkedList<Integer> path, int curSum){

        if (curSum > target) return;

        if(curSum == target){
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i<path.size(); i++){
                list.add(candidates[path.get(i)]);
            }
            list.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            set.add(list);
            return;
        }

        for(int i = 0; i<candidates.length; i++){
            path.addLast(i);
            findCombinationSum(candidates, target, path, curSum + candidates[i]);
            path.removeLast();
        }

    }

    public static void main(String[] args) {
        L39_CombinationSum combinationSum = new L39_CombinationSum();
        List<List<Integer>> lists = combinationSum.combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println();
    }

}
