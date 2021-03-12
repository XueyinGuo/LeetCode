package com.szu.new_coder.byte_dance;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *          TODO 有问题
 * @Date 2021/3/11 21:30
 */

import java.util.Scanner;

public class Terrorist {





    public static long Cn2(long n) {
        if (n < 2) {
            return 0L;
        }
        return (n * (n - 1)) / 2;
    }

    public static void run2(int n, int d, int[] position) {
        long result = 0L;
        int lastj = 1;
        for (int i = 0; i < n; i++) {
            int j;
            for (j = lastj; j < n; j++) {
                if (position[j] - position[i] <= d) {
                    result += Cn2(j - i);
                } else {
                    break;
                }
            }
            lastj = j;
        }
        long mod = 99997867;
        System.out.println(result % mod);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int d = scanner.nextInt();
            int[] position = new int[n];
            scanner.nextLine();
            for (int i = 0; i < n; i++) {
                position[i] = scanner.nextInt();
            }
            run2(n, d, position);
        }
    }
}
