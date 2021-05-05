package com.szu.training02.class08;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个整形数组arr，其中只含有1/2/3，代表所有圆盘目前的状态，1代表左柱，2中柱，3右柱
 * arr[i]代表第 i + 1 个圆盘在什么位置
 * 比如[3,3,2,1] 第一个圆盘在右柱上，第二个圆盘在右柱上，第3 个圆盘在中柱上，第四个圆盘在左柱上
 *
 * 如果 arr 代表状态是最优移动轨迹过程中出现的状态，返回arr这种状态是最优移动轨迹的第步骤
 * 如果 arr 代表的状态不是最优移动轨迹，返回 -1
 *
 * @Date 2021/5/4 9:53
 */

public class HanoiProblem {

    public static void main(String[] args) {

        int[] arr = {3, 3, 2, 1};
        System.out.println(step1(arr));
        System.out.println(step2(arr));

    }

    /*
     * 我自己的递归方法
     * */
    public static int step1(int[] arr) {

        int from = 1;
        int other = 2;
        int to = 3;

        int index = arr.length - 1;
        return process(arr, index, from, other, to);
    }

    private static int process(int[] arr, int i, int from, int other, int to) {

        if (i == -1)
            return 0;
        /*
        * 我们的目标是 把 最后一个圆盘移动到 to 这根柱子上，
        * 这个盘子的最优解只有两种情况：
        * 1. 大盘子 在 from 上，还没动
        * 2. 大盘子已经在 to 上了，已经动完了
        *
        * 所以无论如何，大盘子在最优解中不应该去中间的 柱子上，如果在了，直接返回 false
        * */
        if (arr[i] == other)
            return -1;
        /*
        * 第一步还没搞完，继续搞第一步，
        * 让 上边的 i-1 个盘子 从 from 挪到 other 上，给最后一个盘子腾地方
        * */
        if (arr[i] == from)
            return process(arr, i-1, from, to, other);

        /*
        * 第一大步骤已经搞完了，大盘子已经在 to 上了
        * 其他盘子肯定在 other 上，才能让 大盘子 挪到 to上
        * 所以剩下的步骤就是 把其他盘子 从 other 挪到 to 上，
        * 不应该回 from 了
        * */
        int rest = process(arr, i - 1, other, from, to);
        if (rest == -1)
            return -1;
        /*
        * 如果所有过程合法：
        * 经典汉诺塔问题的最优解肯定是 (2 ^ n) - 1
        * 所以此处是 (2^(n-1)) + rest 剩下的步骤
        * */
        return (1 << i) + rest;
    }

    /*
    *
    * // 目标是:  把0~i的圆盘，从from全部挪到to上
	    // 返回，根据arr中的状态arr[0..i]，它是最优解的第几步？
	    // f(i, 3 , 2, 1)    f(i, 1, 3, 2)    f(i, 3, 1, 2)
	    public static int process(int[] arr, int i, int from, int other, int to) {
	    	if (i == -1) {
	    		return 0;
	    	}
	    	if (arr[i] != from && arr[i] != to) {
	    		return -1;
	    	}
	    	if (arr[i] == from) { // 第一大步没走完
	    		return process(arr, i - 1, from, to, other);
	    	} else { // arr[i] == to
	    		// 已经走完1，2两步了，
	    		int rest = process(arr, i - 1, other, from, to); // 第三大步完成的程度
	    		if (rest == -1) {
	    			return -1;
	    		}
	    		return (1 << i) + rest;
	    	}
	    }
    * */

    /*
     * 大神的 额外空间 O(1) 的牛逼方法
     * */
    public static int step2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int from = 1;
        int mid = 2;
        int to = 3;
        int i = arr.length - 1;
        int res = 0;
        int tmp = 0;
        while (i >= 0) {
            if (arr[i] != from && arr[i] != to) {
                return -1;
            }
            if (arr[i] == to) {
                res += 1 << i;
                tmp = from;
                from = mid;
            } else {
                tmp = to;
                to = mid;
            }
            mid = tmp;
            i--;
        }
        return res;
    }

}
