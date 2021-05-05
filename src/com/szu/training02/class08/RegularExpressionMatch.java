package com.szu.training02.class08;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description

   TODO 动态规划版本

   10. 正则表达式匹配
    给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

    '.' 匹配任意单个字符
    '*' 匹配零个或多个前面的那一个元素
    所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。


    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/regular-expression-matching
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 *
 * @Date 2021/5/4 9:53
 */

public class RegularExpressionMatch {

    private static boolean isValid(char[] str, char[] exp) {
        /*
         * 字符有效性检查，原串中不能包含 . *
         * 表达式中不能有连续的两个 *
         * */
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '.' || str[i] == '*')
                return false;
        }
        if (exp[0] == '*')
            return false;
        for (int i = 1; i < exp.length; i++) {
            if (exp[i - 1] == '*' && exp[i] == '*')
                return false;
        }
        return true;
    }

    public static boolean isMatchViolence(String s, String e) {
        // if (s == null || s.length() == 0)
        //     return true;
        if (e == null || e.length() == 0)
            return s == null || s.length() == 0;

        char[] str = s.toCharArray();
        char[] exp = e.toCharArray();
        if (!isValid(str, exp)) // 有效性检查
            return false;
        return violence(str, 0, exp, 0);
    }

    private static boolean violence(char[] str, int strIndex, char[] exp, int expIndex) {
        /*
         * e[ei....]  能否变成  s[si...]
         * 重要限制：e[ei]不能压中'*'
         * */

        // 表达式已经没了，一个空串 在 原串也已经耗尽的时候才能使匹配的
        if (expIndex >= exp.length)
            return strIndex == str.length;

        /*
         * exp[ei]有字符的   exp[ei] != "*" ？
         *
         * 可能性一，ei+1位置，不是 *
         * */
        if (expIndex + 1 == exp.length || exp[expIndex + 1] != '*') {
            /* ei + 1 不是 ‘*’，说明 表示后边还有东西，而且不是可以变成空串的东西，所以要求 原串必须有东西  */
            if (strIndex == str.length)
                return false;
            /* 后边不是 *， 这个地方必须对上 */
            if (str[strIndex] != exp[expIndex] && exp[expIndex] != '.')
                return false;
            /* 往后一个位置，你们去匹配吧 */
            return violence(str, strIndex + 1, exp, expIndex + 1);
        }

        /*
         * exp[expIndex + 1] == '*'
         * */

        int i = strIndex;
        for (; i < str.length; i++) {
            if (str[i] != exp[expIndex] && exp[expIndex] != '.')
                break;
            if (violence(str, i, exp, expIndex + 2))
                return true;
        }
        return violence(str, i, exp, expIndex + 2);
    }

//    private static boolean violence(char[] str, int strIndex, char[] exp, int expIndex) {
//
//        if (strIndex == str.length && expIndex == exp.length)
//            return true;
//        if (expIndex == exp.length)
//            return false;
//        if (strIndex == str.length) {
//            for (int i = expIndex; i < exp.length; i += 2) {
//                if (expIndex + 1 == exp.length)
//                    return false;
//                if (exp[expIndex + 1] != '*')
//                    return false;
//            }
//        }
//        /* 如果两个位置字符相等 */
//        if (str[strIndex] == exp[expIndex]) {
//            /* exp中下一个位置 是否是 * 字符 */
//            /* 是的话需要开始枚举尝试 从 变成0个当前str中字符----最后不等为止 */
//            if (expIndex + 1 < exp.length && exp[expIndex + 1] == '*') {
//                // a* 的形式
//                char c = str[strIndex];
//                int i = strIndex;
//                for (; i < str.length; i++) {
//                    if (str[i] != c)
//                        return false;
//                    if (violence(str, i, exp, expIndex + 2))
//                        return true;
//                }
//                return violence(str, i, exp, expIndex + 2); // 继续写一个递归是因为 没有上边的循环中少尝试一种情况
//            } else {
//                /* 不是 * 字符，两个index都进到下一个位置继续判断 */
//                return violence(str, strIndex + 1, exp, expIndex + 1);
//            }
//        }
//        /* 两个位置字符不等 */
//        /* 这个位置是否是 . 字符 */
//        /* 是 . ，下一个字符是 * 吗？ */
//        if (exp[expIndex] == '.' && expIndex + 1 < exp.length && exp[expIndex + 1] == '*') {
//            // .* 的形式
//            int i = strIndex;
//            for (; i < str.length; i++) {
//                if (violence(str, i, exp, expIndex + 2))
//                    return true;
//            }
//            return violence(str, i, exp, expIndex + 2); // 继续写一个递归是因为 没有上边的循环中少尝试一种情况
//        } else if (exp[expIndex] == '.') {
//            /* 这个位置 是.字符， 但下个位置不是 *字符 */
//            return violence(str, strIndex + 1, exp, expIndex + 1);
//        }
//        /* 也不是点， 直接 return false */
//        return false;
//    }

    public static void main(String[] args) {
        String s = "";
        String e = ".";
        System.out.println(isMatchViolence(s, e));
    }
}


/*
    示例 1：

    输入：s = "aa" p = "a"
    输出：false
    解释："a" 无法匹配 "aa" 整个字符串。
    示例 2:

    输入：s = "aa" p = "a*"
    输出：true
    解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
    示例3：

    输入：s = "ab" p = ".*"
    输出：true
    解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。

* */