package com.szu.new_coder.tencent;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      小Q想要给他的朋友发送一个神秘字符串，但是他发现字符串的过于长了，于是小Q发明了一种压缩算法对字符串中重复的部分进行了压缩，
 *      对于字符串中连续的m个相同字符串S将会压缩为[m|S](m为一个整数且1<=m<=100)，例如字符串ABCABCABC将会被压缩为[3|ABC]，
 *      现在小Q的同学收到了小Q发送过来的字符串，你能帮助他进行解压缩么？
 *
 *         https://www.nowcoder.com/test/question/c27561e5b7e0441493adb9a54071888d
 *
 * @Date 2021/3/12 21:32
 */

import java.util.Scanner;

public class DecodeString {

    public static void main(String[] args) {
        String s = new Scanner(System.in).next();
        char[] chars = s.toCharArray();
        // 接收到字符串开始解压缩
        String res = deCompose(chars, 0);
        System.out.println(res);
    }
    public static String deCompose(char[] chars, int index){
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while(i<chars.length){
            // 当遍历到 '[' 时意味着要开始解压了
            if(chars[i] == '['){
                /* 传入当前的位置的下一个位置 */
                Info info = deComposeCycle(chars, i+1);
                sb.append(info.append);
                /* 而且i要从已经解压完的位置继续往下遍历 */
                i = info.index;
            }else{
                // 普通字符，追加上即可
                sb.append(chars[i]);
                i++;
            }

        }
        return sb.toString();
    }


    public static Info deComposeCycle(char[] chars, int index){
        /* 传入位置就直接是数字的开始位置了 */
        StringBuffer numBuffer = new StringBuffer();
        // 取出所有的数字，加到 buffer 中
        while(chars[index] - '0' >= 0 && chars[index] - '0' <= 9){
            numBuffer.append(chars[index]);
            index++;
        }
        // 得到数字，遍历的次数
        int num = Integer.valueOf(numBuffer.toString());
        StringBuffer sb = new StringBuffer();
        int returnIndex = 0;
        // 继续把位置跳到下一个位置，因为当前位置已经是 ‘|’
        int i = index+1;
        while(i<chars.length){
            // 遇到这个字符意味着当前的这次解压结束，需要把现在解析到的字符重复刚刚解析到的数字遍
            if(chars[i] == ']'){
                StringBuffer dupSb = new StringBuffer();
                while(num > 0){
                    dupSb.append(sb);
                    num--;
                }
                returnIndex = i+1;
                // 返回自己的构造的类型，返回解析到的字符串，和应该从哪个位置继续遍历原字符串下去
                return new Info(dupSb.toString(), returnIndex);
            }else if(chars[i] == '['){
                // 递归解压
                Info info = deComposeCycle(chars, i + 1);
                // 将解压回来的字符追加到本次的字符中，
                sb.append(info.append);
                /* 而且i要从已经解压完的位置继续往下遍历 */
                i = info.index;
            }else{
                // 普通字符，追加上即可
                sb.append(chars[i]);
                i++;
            }

        }
        return null;
    }

}
class Info{
    String append;
    int index;
    public Info(String append, int index){
        this.append = append;
        this.index = index;
    }
}