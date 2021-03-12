package com.szu;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 13:19
 */

import java.lang.reflect.Field;

/**
 *
 * @author EX-QINCIDONG001
 *
 */
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Person p = new Person();
        p.setId(0);
        p.setName("张三");
        reflect(p);
    }

    public static void reflect(Object obj) {
        if (obj == null) return;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int j = 0; j < fields.length; j++) {
            fields[j].setAccessible(true);
            // 字段名
            System.out.print(fields[j].getName() + ",");
            // 字段值
            if (fields[j].getType().getName().equals(
                    java.lang.String.class.getName())) {
                // String type
                try {
                    System.out.print(fields[j].get(obj));
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (fields[j].getType().getName().equals(
                    java.lang.Integer.class.getName())
                    || fields[j].getType().getName().equals("int")) {
                // Integer type
                try {
                    System.out.println(fields[j].getInt(obj));
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // 其他类型。。。
        }
        System.out.println();
    }
}

class Person {
    private int id;
    private String name;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}