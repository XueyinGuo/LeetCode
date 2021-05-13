package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 632. 最小区间
你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。

我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 * @Date 2021/5/13 18:41
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class L632_SmallestRange {
    /*
    * 思路：
    * TreeSet中 放入自定义节点 Node类型
    *
    * 每个Node在创建的时候都存储 每个 每个数字的两个额外信息
    *
    * 1.来自哪个数组： arrId
    * 2.位于哪个下边： index
    * 3.存储 value 值
    *
    * 开始时把每个 list 开头位置的数字加到 有序表中
    * 然后计算出 当前表中的最大值最小值的差值
    * 如果比之前记录的 差值小，更新差值， 并记录两个差值数字 待返回
    *
    * 比较完之后，把比较小的元素从有序变中删除
    *  由于我们有 value 的三维信息
    * 我们完全可以知道这个value来自那一个数组 的 哪一个下标
    *
    * 然后我们把下一个下标位置的数字 加到 有序表中， 继续这个过程
    *
    * 直到有一个list到结尾为止
    * */
    public int[] smallestRange(List<List<Integer>> numsList) {

//        List<List<Node>> nodesList = new ArrayList<>();
//        for (int arrId = 0; arrId < numsList.size(); arrId++) {
//            List<Integer> nums = numsList.get(arrId);
//            List<Node> list = new ArrayList<>();
//            for (int i = 0; i < nums.size(); i++) {
//                list.add(new Node(nums.get(i), i, arrId));
//            }
//            nodesList.add(list);
//        }
        TreeSet<Node> treeSet = new TreeSet<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                /*
                * ==================================
                * ==================================
                * 一定要加上 这个if 语句，如果碰巧下一个想要加到有序表中的值现在已经被其他数组加到有序表中
                * 只判断 value 值 就会加入失败
                * ==================================
                * ==================================
                * */
                if (o1.value == o2.value)
                    return o1.arrayId - o2.arrayId;
                return o1.value - o2.value;
            }
        });

        for (int i = 0; i < numsList.size(); i++) {
            treeSet.add(new Node(numsList.get(i).get(0), 0, i));
        }
        int minDif = Integer.MAX_VALUE;
        int[] res = new int[2];
        while (true) {

            Node curSmallest = treeSet.first();
            int lastValue = treeSet.last().value;
            int curDif = lastValue - curSmallest.value;
            if (curDif < minDif) {
                minDif = curDif;
                res[0] = curSmallest.value;
                res[1] = lastValue;
            }
            int arrayId = curSmallest.arrayId;
            int nextIndex = curSmallest.index + 1;
            if (nextIndex == numsList.get(arrayId).size())
                break;
            treeSet.remove(curSmallest);
            treeSet.add( new Node( numsList.get(arrayId).get(nextIndex), nextIndex, arrayId ) );
        }
        return res;
    }


    class Node {
        int value;
        int index;
        int arrayId;

        public Node(int value, int index, int arrayId) {
            this.value = value;
            this.index = index;
            this.arrayId = arrayId;
        }
    }




    public static void main(String[] args) {
        int[][] inputMatrix = {
                LeetCodes.getInputArray("[69,89]"),
                LeetCodes.getInputArray("[66,69]"),
                LeetCodes.getInputArray("[88,89]"),
        };
        List<List<Integer>> numsList = new ArrayList<>();
        for (int i = 0; i < inputMatrix.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < inputMatrix[i].length; j++) {
                list.add(inputMatrix[i][j]);
            }
            numsList.add(list);
        }
        L632_SmallestRange test = new L632_SmallestRange();
        test.smallestRange(numsList);
    }
}
