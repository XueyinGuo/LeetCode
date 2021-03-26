package com.szu.practice.l11_print_skill;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *          牛羊吃草，不论谁吃，总是只能吃 4的整数次方，1  4  8 16 64...
 *          谁先把草吃完谁就赢了，
 *          假设牛羊决定聪明，返回先手吃赢还是后手吃赢
 *
 * @Date 2021/3/26 17:21
 */

import java.util.Random;

public class EatGrass {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
//            int grass = random.nextInt(i);
            String winner = getWinnerViolence(i);
            System.out.println(winner);
        }

    }

    private static String getWinnerViolence(int grass) {
        // 0  1  2  3 4
        // 后 先 后 先 先
        if (grass < 5)
            return (grass == 0 || grass == 2) ? "后手" : "先手";
        int eat = 1;
//        String winner = "";
        while(eat <= grass){
            // 当前一共n份草，先手吃掉的是base份，n - base 是留给后手的草
            // 母过程 先手 在子过程里是 后手
            /* 不断尝试，如果一直为后手赢，则不断尝试下一种吃草数量 */
            /* 之所以不用像扑克牌那个题那样写两个方法是因为，每次调用这个方法的姿态都会变化，
            * 每个状态都希望自己赢，而第一次的姿态找到一种赢的方案，就会返回结果，如果找不到任何方案可以赢，则没机会赢 */
            String winnerViolence = getWinnerViolence(grass - eat);
            if (winnerViolence.equals("后手"))
                return "先手";

            if(eat > grass >> 2) // 防止 base * 4 之后成为负数，然后永远跳不出循环了
                break;
            eat = eat << 2;
        }
        return "后手";
    }

    /*
    * 找到规律：后先后先先，直接写code
    * */
    public static String winnerAwesome(int grass){
        if (grass % 5 == 0 || grass % 5 == 2)
            return "后手";
        return "先手";
    }

}
