package com.szu;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 目标和：给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
 * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面，返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * @Date 2021/5/10 15:16
 */

import com.szu.leetcode.utils.LeetCodes;
import com.szu.leetcode.utils.ListNode;
import com.szu.training02.class03.KTimesOneTime;

import java.math.BigDecimal;
import java.util.*;

public class Test {

    public static Test HOOK;

    public void isAlive(){
        System.out.println("I'm Fucking Alive!");
    }

    public static void isDied(){
        System.out.println("I have been Fucked!");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("function finalize() has been called!");
        HOOK = this;
    }



    public static void main(String[] args) throws InterruptedException {
        HOOK = new Test();

        HOOK = null;
        System.gc();
        Thread.sleep(1000);
        if (HOOK != null){
            HOOK.isAlive();
        }else
           isDied();

        HOOK = null;
        System.gc();
        Thread.sleep(1000);
        if (HOOK != null){
            HOOK.isAlive();
        }else
            isDied();



        {
            String s0 = new String("1") + new String("1");
            String s2 = s0.intern();
            String s1 = "11";
			System.out.println(s2 ==s0);//false
			System.out.println(s2 ==s1);//true
			System.out.println(s0 ==s1);//false
			System.out.println("====");
        };
        String s0 = new String("1") + new String("x");
		String s2 = s0.intern();
		String s1 = "1x";
		System.out.println(s2 == s0);//true
		System.out.println(s2 == s1);//true
		System.out.println(s0 == s1);//true
		System.out.println("===");
		String s3 = new String("1") + new String("x");
		String s5 = s3.intern();
		String s4 = "1x";
		System.out.println(s5 == s3);//false//前面已经存在字符串常量1x指向s0，s5指向的就是s0
		System.out.println(s5 == s4);//true
		System.out.println(s3 == s4);//flase//前面已经存在字符串常量1x指向s0，s4指向的就是s0
		System.out.println(s5 == s0);//true
		System.out.println(s4 == s0);//true
		System.out.println("===");

        System.out.println(Integer.MAX_VALUE);
    }


}
