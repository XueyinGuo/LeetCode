package com.szu;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class T2 {

    public static void main(String[] args) throws Exception {
        /*
        * 读取磁盘的上文件
        * */
        File file = new File("f:\\\\1.txt");
        // 用文件对象创建一个输入流，输入流可以把文件的内容读到内存
        FileInputStream inputStream = new FileInputStream(file);

        // 把读入的内容需要读到一个 byte 数组中，用来暂存数据
        byte[] bytes = new byte[1024];
        // 每次读入的 byte，通过上一行的byte数组倒一下手，最终放到 stringBuffer 中
        StringBuffer sb = new StringBuffer();

        /*
        * length  用来记录这次读取的byte数量
        * read() 方法就是把 文件读入流中文件的内容读到 byte数组中    length = inputStream.read(bytes)
        *
        * 当read() 返回值为 -1，说明到了文件末尾
        * */
        int length = 0;
        while ((length = inputStream.read(bytes)) != -1){
            // 只要不是返回值为-1，就把 本次读入的 byte 数组中的内容，转化成一个字符串
            String s = new String(bytes, 0, length);
            // 把字符串 追加到 stringBuffer
            sb.append(s);
        }

        // 读取文件完毕，所有内容都已经在 stringBuffer 中了，把 sb转换为一个 大的字符串
        String fileString = sb.toString();
        // 文件中每行代表一个单词，Windows中换行符为 "\r\n"，为啥是两个斜线，是因为第一个 \ 是转义字符
        // 这样切割好之后，split字符串数组就是每行的单词了
        String[] split = fileString.split("\\r\\n");

        // 遍历个单词，使用HashMap统计出现次数
        HashMap<String, Integer> timesMap = new HashMap<>();
        for (String str : split) {
            Integer times = timesMap.get(str);
            if (times == null){
                timesMap.put(str, 1);
            }else {
                timesMap.put(str, times + 1);
            }
        }
    }

}