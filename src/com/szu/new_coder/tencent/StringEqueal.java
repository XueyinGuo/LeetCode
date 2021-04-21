package com.szu.new_coder.tencent;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/18 20:52
 */

import java.util.Scanner;

public class StringEqueal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testTimes = scanner.nextInt();
        for (int t = 0; t < testTimes; t++) {
            String s1 = scanner.next();
            String s2 = scanner.next();
            if (isEqual(s1, s2))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    private static boolean isEqual(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        if (s1.length() % 2 == 0){
            if (s1.equals(s2))
                return true;
            char[] str1 = s1.toCharArray();
            char[] str2 = s2.toCharArray();
            int mid = str1.length / 2 - 1;
            boolean p1 = isEqual(str1, 0, mid, str2, mid + 1, str2.length - 1);
            boolean p2 = isEqual(str1, mid + 1, str1.length-1, str2, 0, mid);
            return p1 && p2;
        }else
            return s1.equals(s2);
    }

    private static boolean isEqual(char[] str1, int l1, int r1, char[] str2, int l2, int r2) {

        if (l1 == r1)
            return str1[l1] == str2[l2];
        int j = l2;
        boolean equal = true;
        for (int i = l1; i <= r1 ; i++) {
            if (str1[i] != str2[j++]){
                equal = false;
                break;
            }
        }

        if (!equal){
            int mid1 = l1 + (r1 - l1) / 2;
            int mid2 = l2 + (r2 - l2) / 2;
            boolean p1 = isEqual(str1, l1, mid1, str2, mid2 + 1, r2);
            boolean p2 = isEqual(str1, mid1 + 1, r1, str2, l2, mid2);
            return p1 && p2;
        }
        return true;
    }

}
