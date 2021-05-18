package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。

    函数 myAtoi(string s) 的算法如下：

    读入字符串并丢弃无用的前导空格
    检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
    读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
    将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
    如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。
    具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
    返回整数作为最终结果。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/string-to-integer-atoi
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/5/18 21:53
 */

public class L8_Atoi {
    /*
     * ==========================================================
     * ==========================================================
     * 整个题目一定要用负数操作！！！！！！！！！！！！
     * 因为负数 是 -2^(31) ~~ 0    【-2147483648】
     *    正数 是 0 ~~ 2^(31)     【2147483647】
     *
     * 负数溢出了 正数一定溢出！
     * 正数溢出了 负数可不一定溢出！
     * 因为 负数比正数多一个
     * ==========================================================
     * ==========================================================
     * */
    public int myAtoi(String s) {
        if (s == null || s.length() == 0)
            return 0;

        char[] str = s.toCharArray();
        /*
         * 去除先导的空格
         * 并且判断是否合法，能变成一个数字
         * 返回的 info 中记录着
         * 1.是否合法
         * 2.合法的话，是否是一个负数
         * 3.真正的数字开始的位置
         * */
        Info info = getInfo(str);
        if (!info.isValid)
            return 0;
        /*
         * i 从数字开始的位置开始计算
         * */
        int i = info.startIndex;
        int ans = 0;
        /*
         * ==========================================================
         * ==========================================================
         * 标记是否溢出的三个变量
         * 如果这个数字已经 小于 m了， 而且这个数字要继续进行 * 10 ，那么必定溢出
         * 如果这个数组 == m， 但是每个数字都要加上 一个数字，
         * 如果加上的那个数字 比 n 小， 那么加上之后还是会溢出
         * ==========================================================
         * ==========================================================
         * */
        boolean overFlow = false;
        int m = Integer.MIN_VALUE / 10;
        int n = Integer.MIN_VALUE % 10;
        while (i < str.length && str[i] >= '0' && str[i] <= '9') {
            int cur = -(str[i] - '0');
            if (ans < m || (ans == m && cur < n)) {
                overFlow = true;
                break;
            }
            ans = ans * 10 + cur;
            i++;
        }
        if (overFlow)
            return info.isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        if (!info.isNegative && ans == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;

        return info.isNegative ? ans : -ans;
    }

    private Info getInfo(char[] str) {
        /* 去除先导空格 */
        int i = 0;
        while (i < str.length) {
            if (str[i] == ' ')
                i++;
            else
                break;
        }
        /*
        * 判断是正是负
        * */
        boolean negative = false;
        boolean valid = true;
        while (i < str.length) {
            if (str[i] == '-') {
                i++;
                negative = true;
                break;
            } else if (str[i] == '+') {
                i++;
                break;
            } else
                break;
        }
        /*
        * 是否合法
        * */
        if (i < str.length && (str[i] > '9' || str[i] < '0')) {
            valid = false;
        }
        return new Info(negative, valid, i);
    }

    class Info {
        boolean isNegative;
        boolean isValid;
        int startIndex;

        public Info(boolean isNegative, boolean isValid, int startIndex) {
            this.isNegative = isNegative;
            this.isValid = isValid;
            this.startIndex = startIndex;
        }
    }


    public static void main(String[] args) {
//        String s = "2147483647";
//        String s = "2147483648";
//        String s = "-2147483648";
        String s = "-21474836481";
        L8_Atoi test = new L8_Atoi();
        System.out.println(test.myAtoi(s));
    }
}
