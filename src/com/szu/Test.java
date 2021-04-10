package com.szu;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 13:19
 */


import com.szu.leetcode.utils.LeetCodes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Test {
    class Solution {
        int[][] visit;
        ArrayList<Point>[] arrs;
        int row;
        int col;
        int[][] heightMap;
        public int trapRainWater(int[][] heightMap) {
            if(heightMap==null||heightMap.length<3||heightMap[0].length<3){
                return 0;
            }
            arrs = new ArrayList[20001];
            int row = heightMap.length;
            int col = heightMap[0].length;
            this.row = row;
            this.col = col;
            this.heightMap = heightMap;
            visit = new int[row][col];
            for(int i = 0;i<row;i++){
                ArrayList<Point> arr = arrs[heightMap[i][0]];
                if(arr==null){
                    arr = new ArrayList<>();
                    arrs[heightMap[i][0]] = arr;
                }
                arr.add(new Point(i,0));
                arr = arrs[heightMap[i][col-1]];
                if(arr==null){
                    arr = new ArrayList<>();
                    arrs[heightMap[i][col-1]] = arr;
                }
                arr.add(new Point(i,col-1));
                visit[i][0]=1;
                visit[i][col-1]=1;
            }
            for(int i = 1;i<col-1;i++){
                visit[0][i]=1;
                visit[row-1][i]=1;
                ArrayList<Point> arr = arrs[heightMap[0][i]];
                if(arr==null){
                    arr = new ArrayList<>();
                    arrs[heightMap[0][i]] = arr;
                }
                arr.add(new Point(0,i));
                arr = arrs[heightMap[row-1][i]];
                if(arr==null){
                    arr = new ArrayList<>();
                    arrs[heightMap[row-1][i]] = arr;
                }
                arr.add(new Point(row-1,i));
            }
            int ans = 0;
            for(int i = 0;i<arrs.length;i++){
                ArrayList<Point> arr = arrs[i];
                if(arr==null){
                    continue;
                }
                for(int j = 0;j<arr.size();j++){
                    Point p = arr.get(j);
                    int x = p.x;
                    int y = p.y;
                    int height = heightMap[p.x][p.y];
                    ans+=stream(x-1,y,height);
                    ans+=stream(x+1,y,height);
                    ans+=stream(x,y-1,height);
                    ans+=stream(x,y+1,height);
                }
            }
            return ans;
        }
        public int stream(int x,int y,int height){
            if(x<0||x>=row||y<0||y>=col||visit[x][y]==1){
                return 0;
            }
            visit[x][y]=1;
            if(height<=heightMap[x][y]){
                ArrayList<Point> arr = arrs[heightMap[x][y]];
                if(arr==null){
                    arr = new ArrayList<>();
                    arrs[heightMap[x][y]] = arr;
                }
                arr.add(new Point(x,y));
                return 0;
            }
            int water = height-heightMap[x][y];
            water+=stream(x-1,y,height);
            water+=stream(x+1,y,height);
            water+=stream(x,y-1,height);
            water+=stream(x,y+1,height);
            return water;
        }
    }
    class Point{
        int x;
        int y;
        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

}