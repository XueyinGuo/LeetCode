package com.szu.training03.class04;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
    给定整数 power，给定一个数组 arr，给定一个数组 reverse。含义如下：
    arr的长度一定是2的power次方，reverse中的每个值一定都在0~power范围。
    例如power = 2, arr = {3, 1, 4, 2}，reverse = {0, 1, 0, 2}
    任何一个在前的数字可以和任何一个在后的数组，构成一对数。可能是升序关系、相等关系或者降序关系。
    比如arr开始时有如下的降序对：(3,1)、(3,2)、(4,2)，一共3个。
    接下来根据reverse对arr进行调整：
    reverse[0] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成
    [3,1,4,2]，此时有3个逆序对。
    reverse[1] = 1, 表示在arr中，划分每2(2的1次方)个数一组，然后每个小组内部逆序，那么arr变成
    [1,3,2,4]，此时有1个逆序对
    reverse[2] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成
    [1,3,2,4]，此时有1个逆序对。
    reverse[3] = 2, 表示在arr中，划分每4(2的2次方)个数一组，然后每个小组内部逆序，那么arr变成
    [4,2,3,1]，此时有4个逆序对。
    所以返回[3,1,1,4]，表示每次调整之后的逆序对数量。
    输入数据状况：
    power的范围[0,20]
    arr长度范围[1,10的7次方]
    reverse长度范围[1,10的6次方]
 *
 * @Date 2021/5/11 22:13
 */

import com.szu.leetcode.utils.LeetCodes;

public class MergeRecord {


    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int[] arr = LeetCodes.getRandomArray(128, 100);
//            int[] arr = {1, 4, 5, 2, 7, 3, 5, 8};
            int power = 7;
            int[] reverse = {1, 0, 2, 1, 3};
            int[] copy = LeetCodes.copyArray(arr);
            int[] my = getReversePairViolence(arr, reverse, power);
            int[] awesome = awesome(copy, reverse, power);
            for (int j = 0; j < reverse.length; j++) {
                if (my[j] != awesome[j])
                    System.out.println("FUCK");
            }
        }

    }

    /*
     * up[] 数组的含义：
     *     up[i] 的值为：  以 2^i 次方 为一个 team 的情况下， 一个数来自 前 2^(i-1) 次方长度 ，另一个来自后边的 2^(i-1) 长度
     *       所组成的 升序对为多少个
     *
     * 譬如：
     * 1,4,5,2,7,3,5,8
     *
     * up[0] = 0
     *         |--|   |--|   |--|   |--|
     * up[1] = 1, 4 | 5, 2 | 7, 3 | 5, 8 ， 每一组长度为 2^1, 一个数来自前 2^0 次方长度 ，另一个来自后边的 2^0 长度
     *                                       所组成的顺序堆的数量 ： 2  分别为【1, 4】和【5, 8】
     *
     * up[2] = |----↓----|   |----↓----|
     *         1, 4,↓ 5, 2 | 7, 3,↓ 5, 8
     *        每一组长度为 2^2, 一个数来自前 2^1 次方长度 ，另一个来自后边的 2^1 长度 所组成的顺序堆的数量 ： 2
     *   分别为【1, 5】         【7, 8】
     *        【1, 2】         【3, 8】
     *        【4, 5】         【3, 5】
     *
     * down[] 表示同样含义下的逆序对，我们可使数组整体全部逆序之后，求逆序之后的顺序对数量 得到
     * */
    private static int[] awesome(int[] arr, int[] reverse, int power) {
        int[] copy = LeetCodes.copyArray(arr);
        reverse(arr, (int) Math.pow(2, power));
        int[] down = new int[power + 1];
        int[] up = new int[power + 1];
        /*
         * 使用归并排序进行顺序对求解，类似数组小和问题求解过程
         * */
        process(copy, 0, arr.length - 1, power, up);
        process(arr, 0, arr.length - 1, power, down);
        int[] ans = new int[reverse.length];
        /*
         * 每次 进行数组分组逆转的时候，
         * 交换两个 down up 中 小于等于 reverse[i]【逆转的时候分组的大小的位置】
         * 累加逆序数组的个数记为所求
         * */
        for (int i = 0; i < reverse.length; i++) {
            for (int j = 0; j <= reverse[i]; j++) {
                int tem = up[j];
                up[j] = down[j];
                down[j] = tem;
            }
            int num = 0;
            for (int j = 0; j < down.length; j++) {
                num += down[j];
            }
            ans[i] = num;
        }
        return ans;
    }

    private static void process(int[] arr, int left, int right, int power, int[] record) {

        if (left == right)
            return;

        int mid = left + ((right - left) >> 1);
        process(arr, left, mid, power - 1, record);
        process(arr, mid + 1, right, power - 1, record);
        record[power] += merge(arr, left, mid, right);
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int p1 = left;
        int p2 = mid + 1;
        int[] help = new int[right - left + 1];
        int index = 0;
        int ans = 0;
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] < arr[p2]) {

                ans += right - p2 + 1;
                help[index++] = arr[p1++];
            } else {
                help[index++] = arr[p2++];
            }

        }
        while (p1 <= mid)
            help[index++] = arr[p1++];

        while (p2 <= right)
            help[index++] = arr[p2++];

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
        return ans;
    }


    /*
     * 暴力求解
     * */
    public static int[] getReversePairViolence(int[] arr, int[] reverse, int power) {
        if (reverse == null || reverse.length == 0)
            return new int[]{};

        int[] res = new int[reverse.length];
        for (int i = 0; i < reverse.length; i++) {
            reverse(arr, (int) Math.pow(2, reverse[i])); // 按组逆序
            res[i] = getReversePairNumViolence(arr);
        }
        return res;
    }

    private static int getReversePairNumViolence(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j])
                    ans++;
            }
        }
        return ans;
    }

    private static void reverse(int[] arr, int team) {
        if (team == 1 || team == 0)
            return;
        int i = 0;
        int j = team - 1;
        while (i < arr.length) {
            int nextStart = j + 1;
            while (i < j)
                swap(arr, i++, j--);
            i = nextStart;
            j = i + team - 1;
        }
    }

    private static void swap(int[] arr, int i1, int i2) {
        int tem = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tem;
    }


}
