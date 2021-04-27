package com.szu.training02.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个数组 arr 和整数 K
 * arr 长度 为 N
 *
 * 如果有某些数字出现了超过 N/K 次
 * 打印这些数字，如果没有则不打印
 *
 * @Date 2021/4/27 16:28
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.*;

public class FindMajorityII {

    public static List<Integer> printKMajor(int[] arr, int k) {
        if (arr == null || k > arr.length || arr.length == 0)
            return null;
        /*
        * 假设 k = 3，也就是说 找元素出现次数 > N / 3 个，
        * 那么这个元素肯定至多 2 个
        * */
        int len = arr.length;
        int[] candidates = new int[k - 1];
        int cIndex = 0;
        int size = k - 1;
        int candidateNums = 0;
        int[] HPs = new int[k - 1];
        List<Integer> alreadyDied = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            /* 候选人还没够 K-1 个 */
            if (candidateNums < size) {
                candidates[cIndex] = arr[i];
                HPs[cIndex++] = 1;
                candidateNums++;
            } else if (!alreadyDied.isEmpty()){
                Integer diedIndex = alreadyDied.remove(alreadyDied.size() - 1);
                candidates[diedIndex] = arr[i];
                HPs[diedIndex]++;
            }else
                allCandidatesMinesOne(HPs, alreadyDied);
        }
        /* 再把每个候选人放到 map 中， key为 数字， value 后续用来存储出现次数 */
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < candidates.length; i++) {
            if (HPs[i] != 0)
                map.put(candidates[i], 0);
        }
        for (int i = 0; i < len; i++) {
            Integer can = map.get(arr[i]);
            if (can != null)
                map.put(arr[i], can+1);

        }
        ArrayList<Integer> res = new ArrayList<>();
        int x = len / k;
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            Integer times = next.getValue();
            if (times > x)
                res.add(next.getKey());
        }
        return res;
    }

    private static void allCandidatesMinesOne(int[] HPs, List<Integer> alreadyDied) {
        for (int i = 0; i < HPs.length; i++) {
            HPs[i]--;
            if (HPs[i] == 0)
                alreadyDied.add(i);
        }
    }


    public static void main(String[] args) {
        for (int t = 0; t < 1000000; t++) {
            int K = 20;
            int[] arr = LeetCodes.getRandomArray(1000, 5);
            ArrayList<Integer> rights = printKMajorRight(arr, K);

            List<Integer> my = printKMajor(arr, K);

            for (int i = 0; i < my.size(); i++) {
                if ( my.get(i) != rights.get(i) )
                    System.out.println("FUCK");
            }
        }

    }


    /*
    * right 代码
    * */
    public static ArrayList<Integer>  printKMajorRight(int[] arr, int K) {
        if (K < 2) {
            System.out.println("the value of K is invalid.");
            return null;
        }
        HashMap<Integer, Integer> cands = new HashMap<Integer, Integer>();
        for (int i = 0; i != arr.length; i++) {
            if (cands.containsKey(arr[i])) {
                cands.put(arr[i], cands.get(arr[i]) + 1);
            } else {
                if (cands.size() == K - 1) {
                    allCandsMinusOne(cands);
                } else {
                    cands.put(arr[i], 1);
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> reals = getReals(arr, cands);
        boolean hasPrint = false;
        for (Map.Entry<Integer, Integer> set : cands.entrySet()) {
            Integer key = set.getKey();
            if (reals.get(key) > arr.length / K) {
                res.add(key);
            }
        }
        return res;
    }

    public static void allCandsMinusOne(HashMap<Integer, Integer> map) {
        List<Integer> removeList = new LinkedList<Integer>();
        for (Map.Entry<Integer, Integer> set : map.entrySet()) {
            Integer key = set.getKey();
            Integer value = set.getValue();
            if (value == 1) {
                removeList.add(key);
            }
            map.put(key, value - 1);
        }
        for (Integer removeKey : removeList) {
            map.remove(removeKey);
        }
    }

    public static HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> cands) {
        HashMap<Integer, Integer> reals = new HashMap<Integer, Integer>();
        for (int i = 0; i != arr.length; i++) {
            int curNum = arr[i];
            if (cands.containsKey(curNum)) {
                if (reals.containsKey(curNum)) {
                    reals.put(curNum, reals.get(curNum) + 1);
                } else {
                    reals.put(curNum, 1);
                }
            }
        }
        return reals;
    }

}
