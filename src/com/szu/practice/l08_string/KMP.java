package com.szu.practice.l08_string;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/21 14:18
 */

public class KMP {

    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Ur code is a piece of shit!!!!!");
            }
        }
        System.out.println("test finish");
    }


    private static int getIndexOf(String originalStr, String wanted) {
        if (originalStr == null || wanted == null || originalStr.length() == 0 || wanted.length() == 0
            || wanted.length() > originalStr.length())
            return -1;
        char[] match = wanted.toCharArray();
        char[] str = originalStr.toCharArray();
        /* 获取next数组的方法 */
        int next[] = getNextArray(match);
        /*
        * i 为 originalStr 的位置
        * j 为 match 的位置
        * */
        int i = 0;
        int j = 0;
        while (i < str.length && j < match.length){
            // 如果当前位置两个字符都相等， 同时往后移动一个位置，比较下一个位置
            if (str[i] == match[j]){
                i++;
                j++;
            // 如果发生了不一样的情况，j 回退到 match 前缀与后缀相等的位置
            // 此时可以保证 前缀中所有的字符已经与 originalStr 中 i 位置往前数相同位置的字符肯定是相等的
            /*
            * originalStr： a b c  d  a b c t a b c t a b c f
                                  |
                                  i
            * 当 i 来到 d 位置时， 此时 j 也来到自己的 t 位置，此时发现不等，由于 next 记录着该位置之前字符中【不包括当前位置】的
            * 最长前缀和后缀相等的长度，所以直接回退到记录位置可以保证 originalStr 字符串中 i 位置往前数 match 回退到的位置的字符个数
            * 一定是相等的
            *
            *                      j
            *                      |
            * match:        a b c  t  a b c f
            * next数组尾:   -1 0 0  0  0 1 2 3
            * next下标：     0 1 2  3  4 5 6 7
            * */
            // j 只要回退位置不是第一个位置，就一直通过 next 数组回退下去
            }else if (next[j] != -1){
                j = next[j];
            }else {
                // 实在是回退不动了，让originalStr往下动一下
                // i 永远不回退
                i++;
            }
        }
        return j == match.length ? i - j : -1;
    }

    private static int[] getNextArray(char[] match) {
        if (match.length == 1)
            return new int[]{-1};
        int next[] = new int[match.length];
        /* 默认 前两个为 -1  0 */
        next[0] = -1;
        next[1] = 0;
        // 此时开始计算next数组
        // cn代表，cn位置的字符，当前和i-1位置比较的字符
        int cn = 0;
        int i = 2;
        while(i < next.length){
            // 此时是给 i 位置的 字符赋值，i位置的数值跟 i 位置本身没关系，比较的是 i-1位置
            // 如果 cn 和 i-1 位置的字符相等，两个都 +1
            if (match[i-1] == match[cn]){
                next[i] = cn+1;
                i++;
                cn++;
            }else if (cn > 0){
                /* 如果i-1位置和cn位置字符不等，cn就需要回跳到 cn 位置的字符标记的 最长前缀后缀相等的位置！*/

                cn = next[cn];
            }else
                // cn = 0 的情况下 已经不能有 最长前缀后缀相等的事了，直接去赋值0
                /* 也不再把 cn + 1 了，就让 下一个 i-1 位置从当前cn开始计算 */
                next[i++] = 0;
        }
        return next;
    }

}
