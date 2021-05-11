package com.szu.training03.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *
 * 956. 最高的广告牌
你正在安装一个广告牌，并希望它高度最大。这块广告牌将有两个钢制支架，两边各一个。每个钢支架的高度必须相等。

你有一堆可以焊接在一起的钢筋 rods。举个例子，如果钢筋的长度为 1、2 和 3，则可以将它们焊接在一起形成长度为 6 的支架。

返回广告牌的最大可能安装高度。如果没法安装广告牌，请返回 0。


示例 1：

输入：[1,2,3,6]
输出：6
解释：我们有两个不相交的子集 {1,2,3} 和 {6}，它们具有相同的和 sum = 6。
示例 2：

输入：[1,2,3,4,5,6]
输出：10
解释：我们有两个不相交的子集 {2,3,5} 和 {4,6}，它们具有相同的和 sum = 10。
示例 3：

输入：[1,2]
输出：0
解释：没法安装广告牌，所以返回 0。
*
 *
 * @Date 2021/5/11 15:01
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TallestBillBoard {

    public int tallestBillboard(int[] rods) {
        /*
        * 哈希表的意义：
        *
        * key ： 差值  ->  value ： 两个形成差值的集合所有元素的和中比较小的那个
        * */
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int x : rods) {

            HashMap<Integer, Integer> cur = new HashMap<>(map);

            Iterator<Map.Entry<Integer, Integer>> iterator = cur.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> next = iterator.next();
                /* 遍历表中所有的 差值 */
                Integer dif = next.getKey();
                /* 表中存储的 形成这个差值的数据对中的比较小的那个值 */
                Integer minValue = next.getValue();
                Integer maxValue = minValue + dif; // 得到比较大的那个值

                /* 先把 数组中遍历到的这个数，加到比较小的集中 */
                int newMin = minValue + x;
                int newDifAddToMin = Math.abs(newMin - maxValue); // 再次计算两个数字的差值
                Integer oldNewMin = map.get(newDifAddToMin);
                if (oldNewMin == null){
                    map.put(newDifAddToMin, Math.min(newMin, maxValue));
                }else
                    map.put(newDifAddToMin, Math.max( oldNewMin, Math.min(newMin, maxValue) ));

                int newMax = maxValue + x;

                int newDifAddToMax = Math.abs(newMax - minValue);
                Integer oldNewMax = map.get(newDifAddToMax);
                if (oldNewMax == null){
                    map.put(newDifAddToMax, Math.min(newMax, minValue));

                }else {
                    map.put(newDifAddToMax, Math.max(oldNewMax, Math.min(newMax, minValue)));
                }

            }
        }
        return map.get(0);
    }

}
