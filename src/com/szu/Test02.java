package com.szu;

import java.math.BigDecimal;
import java.util.*;

public class Test02 {
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<>();
        int restAmount = totalAmount * 100;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            //随机范围：[1，剩余人均金额的两倍)，左闭右开
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return amountList;
    }

    public static void main(String[] args) {
        List<Integer> amountList = divideRedPackage(5000, 30);
        for (Integer amount : amountList) {
            System.out.println("抢到金额：" + new BigDecimal(amount).divide(new BigDecimal(100)));
        }
        BigDecimal sum = new BigDecimal(0);
        for (Integer amount : amountList) {
            sum = sum.add( new BigDecimal(amount).divide(new BigDecimal(100)) );
        }
        System.out.println(sum);


    }
}