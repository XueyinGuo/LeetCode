package com.szu.practice.dp;
/*
* 规定1 和 A对应， 2 和 B对应， 3 和 C对应
* 那么一个数字字符串比如 “111” 可以转换为 AAA 或者 KA 、 AK
* 给定一个数字字符串，返回有多少种转换结果
* */
public class ConvertToLetterString {

    public static void main(String[] args) {
        String nums = "123456793213254651321544531358765432154689754132548764621549879465431654987946132165467987456463213245648351321";
        int sum = count(nums);
        int sumdp = countDp(nums.toCharArray());
        System.out.println(sum);
        System.out.println(sumdp);
    }

    private static int count(String nums) {

        if (nums == null || nums.length() == 0){
            return 0;
        }
        char[] chars = nums.toCharArray();
        return doCount(chars, 0);
    }

    private static int doCount(char[] chars, int index) {
        if (index == chars.length){
            return 1;
        }
        if (chars[index] == '0'){
            return 0;
        }
        // 只用当前一个数字
        int sum = doCount(chars, index + 1);
        // 用当前加后边一个,但是需要判断当前两位数字加起来是否 大于 26
        if (index + 1 < chars.length && chars[index] - '0' < 3 && chars[index + 1] - '0' < 7){
            sum += doCount(chars, index + 2);
        }
        return sum;
    }

    public static int countDp(char[] chars){
        int length = chars.length;
        int dp[] = new int[length + 1];
        dp[length] = 1;
        for (int i = length-1; i >= 0; i--) {
            int sum = dp[i+1];
            if (i + 1 < chars.length && chars[i] - '0' < 3 && chars[i + 1] - '0' < 7){
                sum += dp[i+2];
            }
            dp[i] = sum;
        }
        return dp[0];
    }
}
