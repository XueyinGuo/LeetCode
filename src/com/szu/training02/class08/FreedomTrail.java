package com.szu.training02.class08;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 514. 自由之路
    电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。

    给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。

    最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。

    旋转 ring 拼出 key 字符 key[i] 的阶段中：

    您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
    如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 *
 * @Date 2021/5/4 9:53
 */

import java.util.ArrayList;
import java.util.HashMap;

public class FreedomTrail {

    public int findRotateSteps(String ring, String key) {
        if (key == null || key.length() == 0)
            return 0;
        char[] ringChars = ring.toCharArray();
        char[] keyChars = key.toCharArray();
        /* 检查是否存在 key 中有 ring中没有的字符，如果存在，直接返回 -1，但是题目要求中说 key 一定可以拼出，故注释掉 */
//        int[] oriMap = new int[26];
//        for (int i = 0; i < ori.length; i++) {
//            oriMap[ori[i] - 'a'] = 1;
//        }
//        for (int i = 0; i < des.length; i++) {
//            if (oriMap[des[i]-'a'] == 0)
//                return -1;
//        }

        /*
         * 记录每个字符在 ring 中的位置，加到有序表 ArrayList 中
         * */
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < ringChars.length; i++) {
            ArrayList<Integer> list = map.get(ringChars[i]);
            if (list == null) {

                ArrayList<Integer> newList = new ArrayList<>();
                newList.add(i); // add 的是 i+1，表示的是在数组中的第几个位置
                map.put(ringChars[i], newList);

            } else
                list.add(i); // add 的是 i+1，表示的是在数组中的第几个位置
        }

        int[][] dp = new int[ringChars.length][keyChars.length + 1];

        for (int i = 0; i < ringChars.length; i++) {
            for (int j = 0; j <= keyChars.length; j++) {
                dp[i][j] = -1;
            }
        }

        /*
         * 正点 位置 在 ori 的第一个字符位置，所以lock设置为1
         * */
        return getMinOp(keyChars, 0, 0, ringChars.length, map, dp);

    }

    private int getMinOp(char[] des, int keyStrIndex, int preStrIndex, int oriLen, HashMap<Character, ArrayList<Integer>> map, int[][] dp) {

        if (keyStrIndex == des.length){
            dp[preStrIndex][keyStrIndex] = 0;
            return 0;
        }

        if (dp[preStrIndex][keyStrIndex] != -1)
            return dp[preStrIndex][keyStrIndex];
        /*
         * 枚举每个想要旋转到的位置
         * 找到组合最短的那个
         *
         * PS : 最后这个 + 1 是按钮那一下操作
         * */
        ArrayList<Integer> curStrIndexList = map.get(des[keyStrIndex]);
        int ans = Integer.MAX_VALUE;
        for (Integer rotateToIndex : curStrIndexList) {
            /*
            * 每次的操作次数 是 旋转的距离 + 按按钮一下 + 后续的操作
            * 枚举找到最小的
            * 使用记忆化搜索的方式加速
            * */
            int midDistance = getMinDistance(preStrIndex, rotateToIndex, oriLen) +
                    getMinOp(des, keyStrIndex + 1, rotateToIndex, oriLen, map, dp) + 1; // 最后这个 + 1 是按钮那一下操作

            if (midDistance < ans)
                ans = midDistance;
        }
        dp[preStrIndex][keyStrIndex] = ans;
        return ans;
    }

    /*
     * 获取两个点的 直线距离 还是 转一圈再回来的距离 中 最短的那个
     * */
    private int getMinDistance(int clock, Integer curIndex, int oriLen) {

        int lineDistance = Math.abs(curIndex - clock);

        int rotateDistance = Math.min(curIndex, clock) + oriLen - Math.max(curIndex, clock);

        return lineDistance > rotateDistance ? rotateDistance : lineDistance;
    }



    public static void main(String[] args) {
        String ori = "godding";
        String key = "gng";
        FreedomTrail test = new FreedomTrail();
        test.findRotateSteps(ori, key);
    }
}
