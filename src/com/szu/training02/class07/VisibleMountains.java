package com.szu.training02.class07;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 一个不含有负数的数组可以代表一圈环形山，每个位置的值代表山的高度
 * {3,1,2,4,5} = {4,5,3,1,2} = {1,2,4,5,3} 都代表同样结构的环形山
 *
 * 山峰A和山峰B能够互相看见的条件为：
 * 1.如果 A B是同一座山，不能互相看见
 * 2.如果是相邻的山，肯定可以相互看见
 * 3.如果不相邻，假设两座山的最小值 为 min，最大值为 max，
 *   在环中 的两个方向上【顺时针、逆时针】沿途中都没有比 min 大的值，则两个山峰可见
 *
 * 给定数组 arr， 返回有多少对山峰可以互相看见
 *
 * @Date 2021/5/1 11:13
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.HashSet;
import java.util.LinkedList;

public class VisibleMountains {
    /*
     * 使用单调栈的解法
     * */
    // 栈中放的记录，
    // value就是指，times是收集的个数
    public static class Record {
        public int value;
        public int times;

        public Record(int value) {
            this.value = value;
            this.times = 1;
        }
    }

    public static int getVisibleNum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int length = arr.length;
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                maxIndex = i;
                max = arr[i];
            }
        }
        LinkedList<Record> stack = new LinkedList<>();
        /* stack中先加入最大值，确保栈中一定至少有一个保底 */
        stack.addLast(new Record(max));
        int res = 0;
        int start = maxIndex + 1 == length ? 0 : maxIndex + 1;
        for (int i = start; i != maxIndex; i = i + 1 == length ? 0 : i + 1) {
            while (!stack.isEmpty() && arr[i] > stack.peekLast().value) {
                /* 栈中元素需要严格递增，所以现在想要压栈的元素比下边的大时 需要弹出元素 */
                int times = stack.pollLast().times;
                /*
                 * 弹出时，结算弹出的元素可见对数
                 * 对外可见对数就是 2 * times
                 * 如果出现次数不为 1 的话，对外可见对数就是  C(k,2)
                 *  */
                res += 2 * times;
                if (times != 1) {
                    res += internalPaires(times);
                }
            }
            if (!stack.isEmpty() && stack.peekLast().value == arr[i]) {
                stack.peekLast().times++;
            } else
                stack.addLast(new Record(arr[i]));

        }
        /*
         * 单独结算栈中的所有元素
         * */
        while (stack.size() > 2) {
            /* 栈中元素需要严格递增，所以现在想要压栈的元素比下边的大时 需要弹出元素 */
            int times = stack.pollLast().times;
            /*
             * 弹出时，结算弹出的元素可见对数
             * 对外可见对数就是 2 * times
             * 如果出现次数不为 1 的话，对外可见对数就是  C(k,2)
             *  */
            res += 2 * times;
            if (times != 1) {
                res += internalPaires(times);
            }
        }
        /*
         * 清算第二小，如果栈中的最后一个元素个数为一个，那么顺逆时针两种情况都只能看见这一座山
         * 如果最后一个元素的个数不为 1， 那么就还是 2 * times，两边都能看见高山
         *  */
        if (stack.size() == 2) {
            int times = stack.pollLast().times;
            res += stack.peekLast().times == 1 ? times : 2 * times;
            if (times != 1) {
                res += internalPaires(times);
            }
        }
        res += internalPaires(stack.pollLast().times);
        return res;
    }

    private static int internalPaires(int times) {
        return ((times - 1) * times) / 2;
    }



















    /*
    * 测试代码 + 大神的 right 代码
    * */

    // 环形数组中当前位置为i，数组长度为size，返回i的下一个位置
    public static int nextIndex(int i, int size) {
        return i < (size - 1) ? (i + 1) : 0;
    }

    // 环形数组中当前位置为i，数组长度为size，返回i的上一个位置
    public static int lastIndex(int i, int size) {
        return i > 0 ? (i - 1) : (size - 1);
    }

    // for test, O(N^2)的解法，绝对正确
    public static int rightWay(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        HashSet<String> equalCounted = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            // 枚举从每一个位置出发，根据“小找大”原则能找到多少对儿，并且保证不重复找
            res += getVisibleNumFromIndex(arr, i, equalCounted);
        }
        return res;
    }

    // for test
    // 根据“小找大”的原则返回从index出发能找到多少对
    // 相等情况下，比如arr[1]==3，arr[5]==3
    // 之前如果从位置1找过位置5，那么等到从位置5出发时就不再找位置1（去重）
    // 之前找过的、所有相等情况的山峰对，都保存在了equalCounted中
    public static int getVisibleNumFromIndex(int[] arr, int index,
                                             HashSet<String> equalCounted) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) { // 不找自己
                if (arr[i] == arr[index]) {
                    String key = Math.min(index, i) + "_" + Math.max(index, i);
                    // 相等情况下，确保之前没找过这一对
                    if (equalCounted.add(key) && isVisible(arr, index, i)) {
                        res++;
                    }
                } else if (isVisible(arr, index, i)) { // 不相等的情况下直接找
                    res++;
                }
            }
        }
        return res;
    }

    // for test
    // 调用该函数的前提是，lowIndex和highIndex一定不是同一个位置
    // 在“小找大”的策略下，从lowIndex位置能不能看到highIndex位置
    // next方向或者last方向有一个能走通，就返回true，否则返回false
    public static boolean isVisible(int[] arr, int lowIndex, int highIndex) {
        if (arr[lowIndex] > arr[highIndex]) { // “大找小”的情况直接返回false
            return false;
        }
        int size = arr.length;
        boolean walkNext = true;
        int mid = nextIndex(lowIndex, size);
        // lowIndex通过next方向走到highIndex，沿途不能出现比arr[lowIndex]大的数
        while (mid != highIndex) {
            if (arr[mid] > arr[lowIndex]) {
                walkNext = false;// next方向失败
                break;
            }
            mid = nextIndex(mid, size);
        }
        boolean walkLast = true;
        mid = lastIndex(lowIndex, size);
        // lowIndex通过last方向走到highIndex，沿途不能出现比arr[lowIndex]大的数
        while (mid != highIndex) {
            if (arr[mid] > arr[lowIndex]) {
                walkLast = false; // last方向失败
                break;
            }
            mid = lastIndex(mid, size);
        }
        return walkNext || walkLast; // 有一个成功就是能相互看见
    }


    public static void main(String[] args) {
        int size = 30;
        int max = 20;
        int testTimes = 3000000;

        for (int i = 0; i < testTimes; i++) {
            int[] arr = LeetCodes.getRandomArray(size, max);
            if (rightWay(arr) != getVisibleNum(arr)) {
                LeetCodes.printArray(arr);
                System.out.println(rightWay(arr));
                System.out.println(getVisibleNum(arr));
                System.out.println("FUCK!");
                break;
            }
        }

    }


}
