package com.szu.training.class06;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个只有 0 和 1 、 & 、| 、^ 组成的表达式，在给定一个布尔值 desired
 * 返回 express 能有多少种方式 达到 desired 的结果
 *
 * @Date 2021/4/20 19:55
 */

import java.util.ArrayList;
import java.util.List;

public class ExpressionDesired {

    public static void main(String[] args) {
        String express = "1^0&0|1&1^0&0^1|0|1&1";
        boolean desired = true;
//        System.out.println(veryViolence(express, desired));
        System.out.println(violenceALittle(express, desired));
        System.out.println(dp(express, desired));
    }

    private static int dp(String express, boolean desired) {
        char[] str = isValid(express);
        if (str == null)
            return 0;
        int length = str.length;
        int[][] trueDp = new int[length][length];
        int[][] falseDp = new int[length][length];
        for (int i = 0; i < length; i += 2) {
            trueDp[i][i] = str[i] == '1' ? 1 : 0;
            falseDp[i][i] = str[i] == '1' ? 0 : 1;
        }


        return desired ? trueDp[0][length-1] : falseDp[0][length-1];
    }

    private static int violenceALittle(String express, boolean desired) {
        char[] str = isValid(express);
        if (str == null)
            return 0;

        return violenceALittle(str, 0, str.length - 1, desired);
    }


    private static int violenceALittle(char[] str, int low, int high, boolean desired) {
        /* 如果我此时只有一个位置，看看我想要的值是 true 还是 false */
        if (low == high) {
            /* 如果是 true，而我现在所处的位置也是 1，返回 1 中结果 */
            if (desired)
                return str[low] == '1' ? 1 : 0;
            else
                return str[low] == '0' ? 1 : 0;
        }
        int res = 0;
        if (desired) {
            for (int i = low + 1; i < high; i += 2) {
                switch (str[i]) {
                    /* 如果我现在想要的结果是 true，我现在的操作符是 或 */
                    case '|':
                        /* 那么我两侧只要有一个为 true 就好了，两侧都是 true 好上加好 */
                        res += violenceALittle(str, low, i - 1, false) * violenceALittle(str, i + 1, high, true);
                        res += violenceALittle(str, low, i - 1, true) * violenceALittle(str, i + 1, high, false);
                        res += violenceALittle(str, low, i - 1, true) * violenceALittle(str, i + 1, high, true);
                        break;
                    case '^':
                        /* 此时操作符为 ^ ，我只需要两侧不一样即可 */
                        res += violenceALittle(str, low, i - 1, false) * violenceALittle(str, i + 1, high, true);
                        res += violenceALittle(str, low, i - 1, true) * violenceALittle(str, i + 1, high, false);
                        break;
                    case '&':
                        /* 两侧都是 true 才行 */
                        res += violenceALittle(str, low, i - 1, true) * violenceALittle(str, i + 1, high, true);
                        break;
                }
            }
        } else {
            /*
             * 想要的结果 为 false
             * */
            for (int i = low + 1; i < high; i += 2) {
                switch (str[i]) {
                    case '|':
                        /* 两侧都得是 false */
                        res += violenceALittle(str, low, i - 1, false) * violenceALittle(str, i + 1, high, false);
                        break;
                    case '^':
                        /* 两侧为一样的 就为 false */
                        res += violenceALittle(str, low, i - 1, false) * violenceALittle(str, i + 1, high, false);
                        res += violenceALittle(str, low, i - 1, true) * violenceALittle(str, i + 1, high, true);
                        break;
                    case '&':
                        /* 两侧只要有一个 false ，那么就可以是 false， 两个false 是好上加好 */
                        res += violenceALittle(str, low, i - 1, false) * violenceALittle(str, i + 1, high, true);
                        res += violenceALittle(str, low, i - 1, true) * violenceALittle(str, i + 1, high, false);
                        res += violenceALittle(str, low, i - 1, false) * violenceALittle(str, i + 1, high, false);
                        break;
                }
            }
        }
        /* 返回结果数 */
        return res;
    }


    /* 为什么如此暴力？？？？？？？？？？？ */
    private static int veryViolence(String express, boolean desired) {
        char[] str = isValid(express);
        if (str == null)
            return 0;
        List<Integer> violence = violence(str, 0, str.length - 1, true);
        return desired ? violence.get(1) : violence.get(0);
    }

    private static List<Integer> violence(char[] str, int start, int end, boolean isFirst) {
        ArrayList<Integer> res = new ArrayList<>();
        if (start == end) {

            res.add(str[start] - '0');
            return res;
        }
        for (int i = start; i <= end; i++) {
            if (str[i] != '1' || str[i] != '0') {

                List<Integer> leftRes = violence(str, start, i - 1, false);
                List<Integer> rightRes = violence(str, i + 1, end, false);

                if (str[i] == '|') {
                    for (int j = 0; j < leftRes.size(); j++) {
                        for (int k = 0; k < rightRes.size(); k++)
                            res.add(leftRes.get(j) | rightRes.get(k));

                    }
                }
                if (str[i] == '^') {
                    for (int j = 0; j < leftRes.size(); j++) {
                        for (int k = 0; k < rightRes.size(); k++)
                            res.add(leftRes.get(j) ^ rightRes.get(k));

                    }
                }
                if (str[i] == '&') {
                    for (int j = 0; j < leftRes.size(); j++) {
                        for (int k = 0; k < rightRes.size(); k++)
                            res.add(leftRes.get(j) & rightRes.get(k));
                    }
                }
            }
        }
        if (isFirst) {
            int count = 0;
            for (Integer i : res) {
                if (i == 0)
                    count++;
            }
            ArrayList<Integer> firstRes = new ArrayList<>();
            firstRes.add(count);
            firstRes.add(res.size() - count);
            return firstRes;
        }
        return res;
    }

    private static char[] isValid(String express) {
        char[] str = express.toCharArray();
        int i = 0;
        while (i < str.length) {
            if (i % 2 == 0 && (str[i] != '1' && str[i] != '0'))
                return null;
            if (i % 2 == 1 && (str[i] != '^' && str[i] != '|' && str[i] != '&'))
                return null;
            i++;
        }
        return str;
    }

}
