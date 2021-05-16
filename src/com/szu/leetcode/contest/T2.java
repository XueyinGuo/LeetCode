package com.szu.leetcode.contest;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/5/16 10:27
 */

public class T2 {

    public int minSwaps(String s) {

        char[] str = s.toCharArray();
        int oneNum = 0;
        int zeroNum = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '1')
                oneNum++;
            else
                zeroNum++;
        }

        if (oneNum > zeroNum + 1 || zeroNum > oneNum + 1)
            return -1;

        return getMinSwap(str, 0);
    }

    private int getMinSwap(char[] str, int index) {

        if (index + 1 == str.length)
            return 0;

        if (str[index] != str[index + 1])
            return getMinSwap(str, index + 1);
        else {
            int nextIlegal;
            if (str[index] == '0')
                nextIlegal = findNextIlegal(str, index + 2, '1');
            else
                nextIlegal = findNextIlegal(str, index + 2, '0');

            if ((index + 2 < str.length && str[index + 1] == str[index + 2]) || nextIlegal == str.length - 1)
                swap(str, index + 1, nextIlegal);
            else
                swap(str, index, nextIlegal);

            return getMinSwap(str, index + 1) + 1;
        }
    }

    private void swap(char[] str, int i1, int i2) {
        char tem = str[i1];
        str[i1] = str[i2];
        str[i2] = tem;
    }

    private int findNextIlegal(char[] str, int start, char c) {

        for (int i = start; i < str.length - 1; i++) {
            if (str[i] == str[i + 1] && str[i] == c)
                return i + 1;
        }
        return str.length - 1;
    }


    public static void main(String[] args) {
        T2 test = new T2();
        System.out.println(test.minSwaps("110000110"));
    }
}
