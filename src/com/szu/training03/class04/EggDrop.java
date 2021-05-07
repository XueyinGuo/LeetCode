package com.szu.training03.class04;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 887. 鸡蛋掉落
给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。

已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。

每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。
如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。

请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 *
 * @Date 2021/5/7 13:53
 */

public class EggDrop {

    public int superEggDrop(int eggs, int floor) {
        return violence(eggs, floor);
    }

    public int violence(int eggs, int floor) {
        if (eggs == 1)
            return floor;
        if (floor == 0)
            return 0;

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= floor; i++) {
            int cur = Math.max(
                    violence(eggs - 1, i - 1),
                    violence(eggs, floor - i)
            ) + 1;
            if (cur < ans)
                ans = cur;
        }
        return ans;
    }

}
