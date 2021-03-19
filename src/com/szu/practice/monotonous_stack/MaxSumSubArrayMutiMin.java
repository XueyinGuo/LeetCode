package com.szu.practice.monotonous_stack;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      给定一个只包含正整数的数组arr，arr中任何一个子数组sub一定否可以算出（sub累加和） * （sub中的最小值）
 *      那么所有子数组中，这个值最大是多少呢？
 *
 * @Date 2021/3/19 20:47
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.List;
import java.util.Stack;

public class MaxSumSubArrayMutiMin {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] randomArray = LeetCodes.getRandomArray(50, 50);
            int a = getMax(randomArray);
            int b = getMaxViolence(randomArray);
            if (a != b)
                System.out.println("FUCK!");
        }
    }

    private static int getMax(int[] arr) {
        int length = arr.length;
        int[] sums = getSum(arr);
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            /* 如果当前元素加入栈的话，会破坏栈的单调性 */
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                // 弹出一个值，这个值的右边的第一个小值是 想要加进栈中的元素，左边的小值是压着的元素
                /* 如果想以弹出的这个值当最小值 */
                // 则需要计算【前 i-1 项的累加】 - 【弹出值下边压的值得累加和】 * arr[j]
                int j = stack.pop();
                max = Math.max(max,  ( stack.isEmpty() ? sums[i-1] : ( sums[i-1] - sums[stack.peek()] ) ) * arr[j] );
            }
            stack.push(i);
        }
        while ( !stack.isEmpty() ){
            /* 即使遍历完了数组，栈中仍然可能存在值 */
            // 弹出一个值，这个值的左边的第一个小值是 栈顶的元素， 右边已经没有
            /* 如果想以弹出的这个值当最小值 */
            // 则需要计算【数组所有元素的累加和】 - 【弹出值下边压的值得累加和】 * arr[j]
            int j = stack.pop();
            max = Math.max( max, (stack.isEmpty() ? sums[length-1] : sums[length-1] - sums[stack.peek()]) * arr[j]);
        }
        return max;
    }


    private static int getMaxViolence(int[] arr) {
        int length = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                int sum = 0;
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j ; k++) {
                    sum += arr[k];
                    min = Math.min(min, arr[k]);
                }
                max = Math.max(min * sum, max);
            }
        }
        return max;
    }

    private static int[] getSum(int[] arr) {
        int[] sums = new int[ arr.length];
        sums[0] = arr[0];
        for (int i = 1; i <  arr.length; i++) {
            sums[i] = sums[i-1] + arr[i];
        }
        return sums;
    }
}
