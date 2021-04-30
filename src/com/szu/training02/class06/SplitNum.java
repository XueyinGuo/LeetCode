package com.szu.training02.class06;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/30 12:25
 */

public class SplitNum {

    public static int violence(int n) {
        return violence(1, n);
    }

    public static int violence(int start, int rest) {

        if (rest == 0)
            return 1;
        /* 因为原题中说了，所有的分裂方式全部是升序【允许相等】，所以一旦start > rest 直接返回 0 种 */
        if (start > rest)
            return 0;
        int ways = 0;
        for (int i = start; i <= rest; i++) {
            ways += violence(i, rest - i);
        }
        return ways;
    }

    public static void main(String[] args) {
        int n = 100;
        int violence = violence(n);
        int right = ways1(n);
//        if (violence != right){
        System.out.println(violence);
        System.out.println(right);
//        }
    }

    /*
     * Right
     * */
    public static int ways1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int i = pre; i <= rest; i++) {
            ways += process(i, rest - i);
        }
        return ways;
    }
}
