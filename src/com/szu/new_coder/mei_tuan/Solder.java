package com.szu.new_coder.mei_tuan;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/18 10:54
 */

import java.util.Scanner;

public class Solder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int As = sc.nextInt();
        int Bs = sc.nextInt();
        int min = sc.nextInt();
        Node[] ANodes = new Node[As];
        Node[] BNodes = new Node[Bs];

        for (int i = 0; i < As; i++) {
            int nums = sc.nextInt();
            int fight = sc.nextInt();
            ANodes[i] = new Node(nums, fight);
        }
        for (int i = 0; i < As; i++) {
            int nums = sc.nextInt();
            int fight = sc.nextInt();
            BNodes[i] = new Node(nums, fight);
        }
        long AFightALL = 0;
        long BFightALL = 0;
        for (int i = 0; i < As; i++) {
            if (ANodes[i].fight >= min)
                AFightALL += ANodes[i].fight * ANodes[i].num;
        }

        for (int i = 0; i < As; i++) {
            if (BNodes[i].fight >= min)
                BFightALL += BNodes[i].fight * BNodes[i].num;
        }
        System.out.print(AFightALL + " " + BFightALL);
        System.out.println();
        if (BFightALL > AFightALL)
            System.out.println("B");
        else
            System.out.println("A");
    }

    static class Node{
        int num;
        int fight;

        public Node(int num, int fight) {
            this.num = num;
            this.fight = fight;
        }
    }
}
