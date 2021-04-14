package com.szu.practice.l17_sorted_table;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  480. 滑动窗口中位数
    中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。

    例如：

    [2,3,4]，中位数是 3
    [2,3]，中位数是 (2 + 3) / 2 = 2.5
    给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。
    你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。

    示例：

    给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。

    窗口位置                      中位数
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       1
     1 [3  -1  -3] 5  3  6  7      -1
     1  3 [-1  -3  5] 3  6  7      -1
     1  3  -1 [-3  5  3] 6  7       3
     1  3  -1  -3 [5  3  6] 7       5
     1  3  -1  -3  5 [3  6  7]      6
     因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 *
 * @Date 2021/3/28 22:33
 */

import java.util.HashMap;

public class SlidingWindowMedian {

    SBNode root;
    HashMap<Integer, Integer> map;

    public SlidingWindowMedian() {
        this.root = null;
        map = new HashMap<>();
    }

    public void add(int value){
        Integer times = map.get(value);
        if (times == null)
            root = add(root, value, false);
        else
            root = add(root, value, true);
        map.put(value, times == null ? 0 : times + 1);
    }

    public SBNode add(SBNode cur, int value, boolean contains){
        if (cur == null)
            cur = new SBNode(value);
        else {
            cur.all++;
            if (!contains)
                cur.size++;
            if (cur.value > value)
                cur.left = add(cur.left, value, contains);
            else if (cur.value < value)
                cur.right = add(cur.right, value, contains);

        }
        return maintain(cur);
    }

    public void delete(int value){
        Integer times = map.get(value);
        if (times == 1)
            root = delete(root, value, true);
        else{

            root = delete(root, value, false);
            map.put(value, times - 1);
        }
    }

    public SBNode delete(SBNode cur, int value, boolean onlyOne){
        if (!onlyOne){
            countDownAllOnly(cur, value);
        }else {
            cur.all--;
            cur.size--;
            if (cur.value > value)
                cur.left = delete(cur.left, value, false);
            else if (cur.value < value)
                cur.right = delete(cur.right, value, false);
            else {
                  if (cur.left == null && cur.right == null)
                      cur = null;
                  else if (cur.left != null && cur.right == null)
                      cur = cur.left;
                  else if (cur.right != null && cur.left == null)
                      cur = cur.right;
                  else {
                      SBNode successor = cur.right;
                      successor.size--;
                      successor.all--;
                      SBNode pre = null;
                      while (successor.left != null){
                          pre = successor;
                          successor = successor.left;
                          successor.size--;
                          successor.all--;
                      }
                      if (pre != null){
                          pre.left = successor.right;
                          successor.right = pre;
                      }
                      successor.left = cur.left;
                      successor.size = cur.size;
                      successor.all = cur.all;
                      cur = successor;
                  }
            }
        }
        return maintain(cur);
    }

    private void countDownAllOnly(SBNode cur, int value) {
        cur.all--;
        cur.size--;
        if (cur.value > value)
            cur.left = delete(cur.left, value, false);
        else if (cur.value < value)
            cur.right = delete(cur.right, value, false);
    }

    private SBNode maintain(SBNode cur) {

        if (cur == null)
            return null;
        int ls = cur.left == null ? 0 : cur.left.size;
        int rs = cur.right == null ? 0 : cur.right.size;
        int lls = cur.left == null || cur.left.left == null ? 0 : cur.left.left.size;
        int lrs =  cur.left == null || cur.left.right == null ? 0 : cur.left.right.size;
        int rls = cur.right == null || cur.right.left == null ? 0 : cur.right.left.size;
        int rrs = cur.right == null || cur.right.right == null ? 0 : cur.right.right.size;

        if (ls < rls){
            cur.right = rightRotate(cur.right);
            cur = leftRotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        }else if (ls < rrs){

            cur = leftRotate(cur);
            cur.left = maintain(cur.left);
            cur = maintain(cur);

        }else if (rs < lls){

            cur = rightRotate(cur);
            cur.right = maintain(cur.right);
            cur = maintain(cur);

        }else if (rs < lrs){
            cur.left = leftRotate(cur.left);
            cur = rightRotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        }
        return cur;
    }

    private SBNode rightRotate(SBNode cur) {
        /*
         * 对于树节点的更新策略： 先算出 本身重复添加的的次数； 然后在计算新的 all 值得时候，两边的 all + 重复次数
         * */
        int same = cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
        SBNode left = cur.left;
        left.size = cur.size;
        left.all = cur.all;

        cur.left = left.right;
        left.right = cur;
        cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null? 0 : cur.right.size) + 1;
        cur.all = (cur.left == null ? 0 : cur.left.all) + (cur.right == null? 0 : cur.right.all) + same;
        return left;
    }

    private SBNode leftRotate(SBNode cur) {
        // 当前节点有多少个重复值
        int same = cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
        SBNode right = cur.right;

        right.size = cur.size;
        right.all = cur.all;

        cur.right = right.left;
        right.left = cur;
        cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null? 0 : cur.right.size) + 1;
        cur.all = (cur.left == null ? 0 : cur.left.all) + (cur.right == null? 0 : cur.right.all) + same;
        return right;
    }

    class SBNode{
        int value;
        SBNode left;
        SBNode right;
        int all;
        int size;

        public SBNode(int value) {
            this.value = value;
            all = 1;
            size = 1;
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int ans[] = new int[nums.length - k + 1];
        SlidingWindowMedian tree = new SlidingWindowMedian();
        for (int i = 0; i < k; i++) {

            tree.add(nums[i]);
        }
        int index = 0;
        for (int i = k; i < nums.length; i++) {
            int size = tree.root.all;
            if (size % 2 == 0){
                int first = tree.getKthNum(root,size / 2);
                int second = tree.getKthNum(root, size / 2 + 1);
                ans[index++] = (first + second ) / 2;
            }else {
                ans[index++] = tree.getKthNum(root, size / 2);
            }
        }
        return ans;
    }

    private int getKthNum(SBNode cur ,int k) {


        int leftAndHeadCount = cur.all - (cur.right == null ? 0 : cur.right.all);
        if (leftAndHeadCount < k)
            return getKthNum(cur.right, k - leftAndHeadCount);
        else if (leftAndHeadCount > k){
            int leftCount = cur.left == null ? 0 : cur.left.all;
            if (leftCount > k){
                return getKthNum(cur.left, k);
            }

        }
        return cur.value;
    }

    public static void main(String[] args) {

    }
}
