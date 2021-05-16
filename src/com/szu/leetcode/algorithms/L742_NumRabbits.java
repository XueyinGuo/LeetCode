package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 781. 森林中的兔子
    森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。

    返回森林中兔子的最少数量。

    示例:
    输入: answers = [1, 1, 2]
    输出: 5
    解释:
    两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
    之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
    设回答了 "2" 的兔子为蓝色。
    此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
    因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。

    输入: answers = [10, 10, 10]
    输出: 11
 *
 * @Date 2021/5/16 23:38
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class L742_NumRabbits {
    /*
    *
    *
        这里大家可能不好理解
        这里是为了求报相同数字的兔子最少不同的颜色种类数，报相同数字的兔子有可能为一种颜色类，
        但是如果有多只兔子报同一个数字，并且兔子的总数大于报的这个数字 + 1，
        那么肯定应该是颜色种类要大于一类的，明白了这里本题就没啥难度了

        那么报相同数字的兔子最少种类数是多少呢？
        如果刚好除尽，则说明报相同数字的兔子的颜色有value // (key + 1)种
        否则相同数字的兔子的颜色有value // (key + 1) + 1种，
        多出来报相同数字的兔子数的要单独用一种颜色，即比value // (key + 1)多一种
        即向上取整：math.ceil()函数


    *
    * */
    public int numRabbits(int[] answers) {

        if (answers == null || answers.length == 0)
            return 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < answers.length; i++) {

            Integer times = map.get(answers[i]);
            if (times != null)
                map.put(answers[i], times + 1);
            else
                map.put(answers[i], 1);

        }

        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        int ans = 0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();

            Integer key = next.getKey();
            Integer value = next.getValue();

            if (key >= value + 1)
                ans += key + 1;
            else {

                ans += Math.ceil((double) value / (key + 1)) * (key + 1);

            }
        }
        return ans;

    }

    /*
    * Hash表替换成 map 数组，执行之间骤降
    * */
    public int numRabbits2(int[] answers) {
        if (answers == null || answers.length == 0)
            return 0;
        int[] map = new int[1000];
        for (int i = 0; i < answers.length; i++)
            map[answers[i]]++;
        int ans = 0;
        for (int i = 0; i < 1000; i++) {
            if (map[i] == 0)
                continue;
            if (i >= map[i] + 1)
                ans += i + 1;
            else
                ans += Math.ceil((double) map[i] / (i + 1)) * (i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] randomArray = LeetCodes.getRandomArray(1000, 100);
            L742_NumRabbits test = new L742_NumRabbits();
            int i1 = test.numRabbits(randomArray);
            int i2 = test.numRabbits2(randomArray);
            if (i1 != i2)
                System.out.println("FUCK");
        }
    }

}
