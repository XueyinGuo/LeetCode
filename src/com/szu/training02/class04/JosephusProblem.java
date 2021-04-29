package com.szu.training02.class04;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 约瑟夫环问题
 *
 * @Date 2021/4/29 15:17
 */

import com.szu.leetcode.utils.LeetCodes;
import com.szu.leetcode.utils.ListNode;

import java.util.Random;

public class JosephusProblem {


    private static int josephus(ListNode head, int m) {
        if (head == null || head.next == head || m < 1)
            return -1;
        ListNode last = head;
        /* 数出所有节点的个数 */
        int size = 1;
        while (last.next != head) {
            last = last.next;
            size++;
        }
        int live = getLive(size, m);
        while (--live != 0){
            head = head.next;
        }
        return head.val;
    }

    private static int getLive(int size, int m) {
        if (size == 1)
            return 1;
        /*
        * 公式推理过程：
        * 首先 从最开始的 y = x % i 的函数图像开始：
        * 假设现在有 8 个人围成一圈， m = 3；也就是说没报数到 3，那个人就会被杀
        *
        * 首先在旧的链表中【暂时人不被处死的时候】人的编号 与 报数的对应情况 如下
        * 1   2   3   4   1   2   3   4   1    【标号】
        * 1   2   3   4   5   6   7   8   9    【报数】
        * 化成函数图像【1 , 1】 【2,2】 【3,3】 【4,4】 【1 ,  5】...
        *                                           |     |
        *                                          标号   报数
        *
        * 会发现这个图像是 y = x % i 向右移一位 向上移动一位
        * 得到  y = (x - 1) % i + 1
        * ==========================================
        * ==========================================
        * 【 标号 = （报数 - 1） % 长度 + 1 】
        * ==========================================
        * ==========================================
        *
        * 杀掉之后重新编号：
        * 1   2   3   4   5   6   7   8 【旧标号】
        * 6   7   ×   1   2   3   4   5 【新标号】
        * 当我们使用 【新】->【旧】的时候，再对这个模型建模，【新】作为 x， 【旧】作为 y
        * 得到的函数图像为： 【1  ,  4】  【2,3】 【3,6】 【7,4】 【8,5】 【6,1】 【7,2】
        *                  |     |
        *                 新     旧
        *
        * 此时得到的函数图像 为 ：
        * y = (x + k - 1) % i + 1
        * 即：【旧标号】 = (【新标号】 + 【被杀掉的号】 + 1 ) % 旧链表长度 + 1
        *
        * 然而【被杀掉的号】 我们可以通过   【 标号 = （报数 - 1） % 旧链表长度 + 1 】这个公式算出
        *
        * 所以带入可得
        *
        * 旧标号 = (新标号 + ((报数 - 1) % 旧链表长度 + 1 ) -1 ) % i + 1
        * */
        return (getLive(size - 1, m) + (m - 1) % size) % size + 1;
    }

    public static void main(String[] args) {
        int[] arr = LeetCodes.getIncreasingArray(100);

        ListNode josephusHead = LeetCodes.arrayToListNode(arr);
        ListNode josephusLast = josephusHead;
        while (josephusLast.next != null) {
            josephusLast = josephusLast.next;
        }
        josephusLast.next = josephusHead;
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            /* 串成环 */
            ListNode violenceHead = LeetCodes.arrayToListNode(arr);
            ListNode violenceLast = violenceHead;
            while (violenceLast.next != null) {
                violenceLast = violenceLast.next;
            }
            violenceLast.next = violenceHead;

            int kill = random.nextInt(300) + 5;
            int violence = violence(violenceHead, kill);
            int josephus = josephus(josephusHead, kill);
            if (violence != josephus)
                System.out.println("FUCK");
        }
    }

    /*
     * 正确的暴力解
     * */
    public static int violence(ListNode head, int m) {
        if (head == null || head.next == head || m < 1) {
            return -1;
        }
        ListNode last = head;
        while (last.next != head) {
            last = last.next;
        }
        int count = 0;
        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = last.next;
        }
        return head.val;
    }
}
