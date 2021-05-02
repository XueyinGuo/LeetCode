package com.szu.leetcode.contest;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * 
 * 5747. 将字符串拆分为递减的连续值
给你一个仅由数字组成的字符串 s 。

请你判断能否将 s 拆分成两个或者多个 非空子字符串 ，使子字符串的 数值 按 降序 排列，且每两个 相邻子字符串 的数值之 差 等于 1 。

例如，字符串 s = "0090089" 可以拆分成 ["0090", "089"] ，数值为 [90,89] 。这些数值满足按降序排列，且相邻值相差 1 ，这种拆分方法可行。
另一个例子中，字符串 s = "001" 可以拆分成 ["0", "01"]、["00", "1"] 或 ["0", "0", "1"] 。然而，所有这些拆分方法都不可行，因为对应数值分别是 [0,1]、[0,1] 和 [0,0,1] ，都不满足按降序排列的要求。
如果可以按要求拆分 s ，返回 true ；否则，返回 false 。

子字符串 是字符串中的一个连续字符序列。
 *
 * @Date 2021/5/2 10:28
 */

import java.util.ArrayList;
import java.util.Random;

public class L5747_SplitString {



    public boolean splitString(String s) {

        char[] str = s.toCharArray();
        ArrayList<Integer> list = new ArrayList<>();
        int k = 0;
        for (; k < str.length; k++) {
            if (str[k] != '0')
                break;
        }

        for (int j = k; j < str.length; j++) {
            list.add(str[j] - '0');
        }
        /*
        * 用 long 防止越界啊臭傻逼
        * */
        long cur = 0;
        for (int i = 0; i < list.size(); i++) {
            cur = cur * 10 + list.get(i);
            if (violence(list, i + 1, cur, true))
                return true;
        }
        return false;
    }

    private boolean violence(ArrayList<Integer> list, int index, long lastNum, boolean isDirect) {
        if (index == list.size() && !isDirect)
            return true;
        long cur = 0;
        for (int i = index; i < list.size(); i++) {
            cur = cur * 10 + list.get(i);
            if (cur + 1 > lastNum)
                break;
            if (lastNum - cur == 1 && violence(list, i + 1, cur, false))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {

        L5747_SplitString t2 = new L5747_SplitString();
        long start = System.currentTimeMillis();
//        for (int i = 0; i < 1000; i++) {
        String s = "21474836482147483647";
        boolean b = t2.splitString(s);
        System.out.println(b);

//        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public String generate() {
        Random random = new Random();
        int start = random.nextInt(1000) + 30;
        int weiShu = 0;
        int dup = start;
        while (dup != 0) {
            dup /= 10;
            weiShu++;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 30; i += weiShu) {
            start--;
            sb.append(start);
        }
        return sb.toString();
    }
}
