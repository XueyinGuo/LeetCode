package com.szu.practice.string;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/21 17:31
 */

import com.szu.leetcode.utils.LeetCodes;
import com.szu.leetcode.utils.TreeNode;

import java.util.ArrayList;

public class IsAllEqualSubTree {

    public static void main(String[] args) {
        // 构造出两颗树， 一颗大树，一颗小树
        int[] big = {5,9,7,8,3,6,2,7,8,1};
        int[] small = {3,1};
        TreeNode bigRoot = LeetCodes.arrayToTree(big);
        TreeNode smallRoot = LeetCodes.arrayToTree(small);
        /* 采用先序序列化的方式生成两棵树的字符串列表 */
        ArrayList<String> bigList = new ArrayList<>();
        ArrayList<String> smallList = new ArrayList<>();
        LeetCodes.preOrderedSerialTree(bigRoot, bigList);
        LeetCodes.preOrderedSerialTree(smallRoot, smallList);
        /* 如果小树是大树的子树，那么序列化之后的小字符串一定是大字符串的字串 */
        /* 使用KMP算法解决问题 */
        int index = getIndex(bigList, smallList);
        System.out.println(index);
    }

    private static int getIndex(ArrayList<String> bigList, ArrayList<String> smallList) {

        if (smallList.isEmpty() || bigList.isEmpty() || smallList.size() > bigList.size())
            return -1;
        int next[] = getNextArray(smallList);
        int i = 0;
        int j = 0;
        while(i < bigList.size() && j < smallList.size()){

            if (bigList.get(i).equals(smallList.get(j))){
                i++;
                j++;
            }else if ( next[j] != -1 ){
                j = next[j];
            }else {
                i++;
            }
        }
        return j == smallList.size() ? i - j : -1;
    }

    private static int[] getNextArray(ArrayList<String> smallList) {

        if (smallList.size() == 1)
            return new int[]{-1};

        int[] next = new int[smallList.size()];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while( i < smallList.size() ){

            if ( smallList.get(i-1).equals(smallList.get(cn)) ){
                next[i] = cn+1;
                i++;
                cn++;
            }else if ( cn > 0 ){
                cn = next[cn];
            }else
                next[i++] = 0;

        }
        return next;
    }

}
