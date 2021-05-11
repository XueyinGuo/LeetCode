package com.szu.training03.class03;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/5/11 15:01
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TallestBillBoard {

    public int tallestBillboard(int[] rods) {
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
