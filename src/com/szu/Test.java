package com.szu;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/21 14:58
 */

import java.math.BigDecimal;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        BigDecimal one = new BigDecimal(1);
        BigDecimal three = new BigDecimal(3);
        BigDecimal two = new BigDecimal(2);
        BigDecimal six = new BigDecimal(6);

        System.out.println(one.divide(three));
        System.out.println(two.divide(six));
        System.out.println(one.divide(three) == two.divide(six));
    }
}
