package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 491. 递增子序列
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是 2 。
 *
 *
 *
 * @Date 2021/4/22 20:36
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class L491_FindSubsequences {

    List<List<Integer>> res = new ArrayList<>();
       /*
       * 臭傻逼 去重用什么 Set， LOW爆炸
       * 直接判断最后一个数字相等不等不就完了？
       * 刷了这么多题，刷你妈傻逼脑子去了
       * */
//    HashSet<String> set = new HashSet<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            ArrayList<Integer> path = new ArrayList<>();
            path.add(nums[i]);
            findSubsequences(nums, i + 1, path);
        }
        return res;
    }

    private void findSubsequences(int[] nums, int index, ArrayList<Integer> path) {

        if (index == nums.length){
            // 通过去重，进行过滤
            if (path.size() >= 2){
//                String curPath = path.toString();
//                if (!set.contains(curPath)) {
//                    res.add(new ArrayList<>(path));
//                    set.add(curPath);
//                }
                res.add(new ArrayList<>(path));
            }
            return;
        }

        /*
        * 如果这个数字 >= 当前 path 最后一个数字
        * 才把他加到 path 中
        * */
        if (nums[index] >= path.get(path.size()-1)){
            path.add(nums[index]);
            findSubsequences(nums, index + 1, path);
            path.remove(path.size()-1);
        }
        /* 否则 不加入path， 直接去下一个位置 */
        if (nums[index] != path.get(path.size()-1)){

            findSubsequences(nums, index + 1, path);
        }
    }


    public static void main(String[] args) {
        L491_FindSubsequences test = new L491_FindSubsequences();
        test.findSubsequences(new int[]{4,6,3,7,7});
        test.findSubsequencesAwesome(new int[]{4,6,3,7,7});
    }



    /*
    * 效率超高的代码
    * */
    List<Integer> temp = new ArrayList<>();

    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> findSubsequencesAwesome(int[] nums) {
        dfs(0, Integer.MIN_VALUE, nums);
        return ans;
    }

    public void dfs(int cur, int last, int[] nums) {
        if (cur == nums.length) {
            if (temp.size() >= 2) {
                ans.add(new ArrayList<>(temp));
            }
            return;
        }

        if (nums[cur] >= last) {
            temp.add(nums[cur]);
            dfs(cur + 1, nums[cur], nums);
            temp.remove(temp.size() - 1);
        }

        if (nums[cur] != last) {
            dfs(cur + 1, last, nums);
        }
    }
}
