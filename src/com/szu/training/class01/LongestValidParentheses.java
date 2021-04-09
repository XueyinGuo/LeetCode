package com.szu.training.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/4/9 10:09
 */

public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        if(s == null || s.length() < 2) return 0;

        char[] str = s.toCharArray();
        int n = str.length;
        int max = 0;
        /*
         * 辅助数组中记录了 以每个位置结尾时，最长合法括号的长度
         * */
        int[] help = new int[n];
        // 第一个肯定为 0，所以从 1 开始遍历
        for(int i = 1; i < n ; i++ ){
            // 每个字符串如果合法 必须 以 ） 结尾，如果字符串以 （ 结尾， 必不合法
            if( str[i] == ')' ){
                /*
                 * 从当前位置往前推多少个位置可以不用看，如果 计算完【自己应该观察的位置】已经来到了字符串开头甚至之前
                 *
                 * */
                int watchIndex = i - help[i - 1];
                if (watchIndex <= 0){
                    help[i] = 0;
                    continue;
                }
                /*
                 * 因为观察的位置 是计算完的位置之后的前一个字符为什么，所以当计算出的位置 <= 0 的时候，当前位置肯定没有合法串
                 *
                 * 进入到此处，肯定会有待观察的位置， 如果待观察位置 为 ‘（’，则匹配成功，直接为当前的前边一个位置的长度 + 2
                 * */
                help[i] = str[ watchIndex - 1] == '(' ?  help[i - 1] + 2 : 0;
                /*
                 *  但是 + 2 之后不算完结，因为还要看一下自己观察的位置的前一个位置，是否有合法的括号长度，如果有，加上自己这段仍然是合法的
                 * 则这样才是最长长度
                 *
                 * 但是为了不要误看，只有在自己的位置有合法长度的时候才去加上之前的长度，还要防止数组越界
                 * */
                if (watchIndex > 2 && help[i] >= 2)
                    help[i] += help[ watchIndex - 2 ];
                max = Math.max(max, help[i]);
            }

        }
        return max;
    }

}
