package com.szu.leetcode.contest;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 5733. 最近的房间
一个酒店里有 n 个房间，这些房间用二维整数数组 rooms 表示，其中 rooms[i] = [roomIdi, sizei] 表示有一个房间号为 roomIdi 的房间且它的面积为 sizei 。每一个房间号 roomIdi 保证是 独一无二 的。

同时给你 k 个查询，用二维数组 queries 表示，其中 queries[j] = [preferredj, minSizej] 。第 j 个查询的答案是满足如下条件的房间 id ：

房间的面积 至少 为 minSizej ，且
abs(id - preferredj) 的值 最小 ，其中 abs(x) 是 x 的绝对值。
如果差的绝对值有 相等 的，选择 最小 的 id 。如果 没有满足条件的房间 ，答案为 -1 。

请你返回长度为 k 的数组 answer ，其中 answer[j] 为第 j 个查询的结果。
 *
 *
 * @Date 2021/5/1 23:10
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class L5733_ClosestRoom {

    public int[] closestRoom(int[][] rooms, int[][] queries) {
        TreeSet<Integer> set = new TreeSet<>();

        Arrays.sort(rooms, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return -Integer.compare(a[1], b[1]);
            }
        });

        int loc = 0;
        int k = queries.length;
        int[] res = new int[k];
        int[][] query = new int[k][3];
        for(int i = 0; i < k; i++){
            query[i][0] = queries[i][0];
            query[i][1] = queries[i][1];
            query[i][2] = i;
        }

        Arrays.sort(query, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return -Integer.compare(a[1], b[1]);
            }
        });

        for(int i = 0; i < k; i++){
            int[] cur = query[i];
            while(loc < rooms.length && rooms[loc][1] >= cur[1]){
                set.add(rooms[loc++][0]);
            }

            //System.out.println(Arrays.toString(cur));
            //System.out.println(set);
            //System.out.println();
            Integer floor = set.floor(cur[0]);
            Integer ceil = set.ceiling(cur[0]);
            if(floor == null && ceil == null){
                res[cur[2]] = -1;
            }
            else if(floor == null){
                res[cur[2]] = ceil;
            }
            else if(ceil == null){
                res[cur[2]] = floor;
            }
            else if(cur[0] - floor <= ceil - cur[0]){
                res[cur[2]] = floor;
            }
            else{
                res[cur[2]] = ceil;
            }
        }

        return res;
    }


    public static void main(String[] args) {
        int rooms[][] = {{23, 22}, {6, 20}, {15, 6}, {22, 19}, {2, 10}, {21, 4}, {10, 18}, {16, 1}, {12, 7}, {5, 22}};
        int requires[][] = {{12, 5}, {15, 15}, {21, 6}, {15, 1}, {23, 4}, {15, 11}, {1, 24}, {3, 19}, {25, 8}, {18, 6}};
        L5733_ClosestRoom test = new L5733_ClosestRoom();
        test.closestRoom(rooms, requires);
    }

}
