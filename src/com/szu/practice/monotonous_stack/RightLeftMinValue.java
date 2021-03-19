package com.szu.practice.monotonous_stack;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      给定一个数组，返回数组中每个元素左右两边第一个比当前元素小的值的下标集合，无则用 -1 表示
 *
 * @Date 2021/3/19 15:20
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RightLeftMinValue {

    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {
            int[] randomArray = LeetCodes.getRandomArray(5, 30);
//        int arr[] = {4,8,4,6};
            int[][] resultViolence = getResultViolence(randomArray);
            int[][] resultWithStack = getResultWithStack(randomArray);
            for (int j = 0; j < randomArray.length; j++) {
                if (resultViolence[j][0] != resultWithStack[j][0]){
                    System.out.println("Ur code is a piece of shit");
                }
                if (resultViolence[j][1] != resultWithStack[j][1]){
                    System.out.println("Ur code is a piece of shit");
                }
            }
        }


    }

    private static int[][] getResultWithStack(int[] arr) {
        if (arr == null || arr.length == 0) return  null;
        /* 单调栈 找左右的最小值，此时栈中的值从底部到顶部 是 严格的 递增的 */
        Stack<List<Integer>> stack = new Stack<>();
        int res[][] = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            // 如果此时栈不为空，而且 栈中的顶部值 比我大，那么我入栈会破坏单调性
            // 此时往栈中弹出值
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i] ){
                // 栈中弹出的值 是一个列表
                List<Integer> popList = stack.pop();
                // 弹出列表中所有的右边第一个小值 为 让他弹出的 i
                // 左边的最小值 为 下边压着的列表中 最右边的值，如果栈空了那就是没有左边小值，则赋值为 -1
                int leftMinIndex = stack.isEmpty() ? -1 : stack.peek().get( stack.peek().size()-1 );
                for (Integer popI: popList ) {
                    res[popI][0] = leftMinIndex;
                    res[popI][1] = i;
                }
            }
            // 如果和栈顶元素相等 ，加到相等的列表中
            if ( !stack.isEmpty() && arr[stack.peek().get(0)] == arr[i] )
                stack.peek().add(i);
            // 如果比他大了， 则新建一个列表压入栈中
            if (stack.isEmpty() || arr[stack.peek().get(0)] < arr[i]){
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list );
            }
        }
        // 当数组遍历完之后，可能栈中还有值，此时的栈中所有元素，没有右边的小值，左边的小值不一定有（如果栈不空就有，栈空了也没有）
        while (!stack.isEmpty()){
            List<Integer> popList = stack.pop();
            int leftMinIndex = stack.isEmpty() ? -1 : stack.peek().get( stack.peek().size()-1 );
            for (Integer popI: popList ) {
                res[popI][0] = leftMinIndex;
                res[popI][1] = -1;
            }
        }
        return res;
    }

    /* 暴力解 */
    public static int[][] getResultViolence(int arr[]){
        if (arr == null || arr.length == 0) return  null;

        int len = arr.length;
        int res[][] = new int[len][2];
        for (int i = 0; i < len; i++) {
            int leftMin = -1;
            int rightMin = -1;
            int curRes[] = new int[2];
            if ( i > 0 ){
                for (int j = i - 1; j >= 0 ; j--) {
                    if ( arr[j] < arr[i] ){
                        //如果找到直接break，防止重复赋值
                        leftMin = j;
                        break;
                    }
                }
            }
            if (i < len-1){
                for (int j = i+1; j < len; j++) {
                    if (arr[j] < arr[i]){
                        //如果找到直接break，防止重复赋值
                        rightMin = j;
                        break;
                    }
                }
            }
            res[i][0] = leftMin;
            res[i][1] = rightMin;
        }
        return  res;
    }
}
