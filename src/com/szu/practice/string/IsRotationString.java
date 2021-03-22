package com.szu.practice.string;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *     判断两个字符串是否互为旋转串
 *
 * @Date 2021/3/21 17:52
 */

public class IsRotationString {

    public static void main(String[] args) {
        String a = "abcdef";
        String b = "efabcd";
        int flag = isRotationString(a, b);
        if (flag == -1){
            System.out.println( false );
        }else
            System.out.println(true);
    }

    private static int  isRotationString(String a, String b) {
        /*
        * 把 a 串当成后边贴上一个 a，当做 大字符串
        * 判断 b 是否是 a 的字串， 如果是，肯定为旋转字符串
        * */
        String aa = a + a;
        char[] str = aa.toCharArray();
        char[] match = b.toCharArray();

        int[] next = getNext(match);
        int x = 0;
        int y = 0;
        while(x < str.length && y < match.length){

            if (str[x] == match[y]){
                x++;
                y++;
            }else if ( next[y]!= -1 ){
                y = next[y];
            }else
                x++;

        }
        return y == match.length ? x - y : -1;
    }

    private static int[] getNext(char[] match) {

        if (match.length == 1)
            return new int[]{-1};
        int next[] = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int index = 2;
        int cn = 0;
        while (index < match.length){
            if (match[index] == match[cn]){
                next[index] = cn + 1;
                index++;
                cn++;
            }else if ( cn > 0){
                cn = next[cn];
            }else
                next[index++] = 0;

        }
        return next;
    }

}
