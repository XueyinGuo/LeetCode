package com.szu.new_coder.mei_tuan;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/18 11:41
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Carbin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int optimes = sc.nextInt();
        ArrayList<Node> list = new ArrayList<>();
        HashMap<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < optimes; i++) {
            int op = sc.nextInt();
            if (op == 1){
                int index = sc.nextInt();
                String s = sc.next();
                Node newNode = new Node(s, index);
                list.add(newNode);
                indexMap.put(s, list.size() - 1);
            }else{

            }
        }

        int[] arr= {3, 1, 5, 2, 4};
        for (int j = 0; j < arr.length; j++) {
            if (j != arr.length - 1) {
                System.out.print(arr[j] + " ");
            } else
                System.out.print(arr[j]);
        }
    }

    static class Node{
        String s;
        int index;

        public Node(String s, int index) {
            this.s = s;
            this.index = index;
        }
    }
}
