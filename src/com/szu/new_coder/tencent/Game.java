package com.szu.new_coder.tencent;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/18 20:35
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testTimes = scanner.nextInt();
        for (int tii = 0; tii < testTimes; tii++) {
            int group = scanner.nextInt();
            int[] timeCost = new int[group];
            int[] countDown = new int[group];
            for (int g = 0; g < group; g++) {
                timeCost[g] = scanner.nextInt();
            }
            for (int g = 0; g < group; g++) {
                countDown[g] = scanner.nextInt();
            }

            int less = chose(timeCost, countDown);
            System.out.println(less);
        }
    }

    private static int chose(int[] timeCost, int[] countDown) {
        Node[] nodes = new Node[timeCost.length];
        for (int i = 0; i < timeCost.length; i++) {

            nodes[i] = new Node(timeCost[i], countDown[i]);

        }
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.cost == o2.cost)
                    return o2.down - o1.down;
                return o1.cost - o2.cost;
            }
        });
        int down = 0;
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i].cost == nodes[i-1].cost)
                down += nodes[i].down;
        }
        return down;

    }

    static class Node{
        int cost;
        int down;

        public Node(int cost, int down) {
            this.cost = cost;
            this.down = down;
        }
    }


}
