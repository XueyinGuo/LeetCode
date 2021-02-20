package com.szu.leetcode.greedy;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *              860. 柠檬水找零
 *
 *          在柠檬水摊上，每一杯柠檬水的售价为 5 美元。

            顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。

            每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。

            注意，一开始你手头没有任何零钱。

            如果你能给每位顾客正确找零，返回 true ，否则返回 false 。

            来源：力扣（LeetCode）
            链接：https://leetcode-cn.com/problems/lemonade-change
            著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @Date 2021/2/19 23:44
 */

public class LemonadeChange_860 {

    public boolean lemonadeChange(int[] bills) {
        return doChange(bills, 0, 0, 0);
    }
    /*
    * index : 来到第几个人买柠檬水
    * fives : 当前手头上5元张数
    * tens  : 10元的张数
    * */
    public boolean doChange(int[] bills, int index, int fives, int tens) {
        if(index == bills.length)
            return true;
        // 如果这个顾客手上拿着5元来买，不用找钱
        if(bills[index] == 5)
            fives += 1;
        // 不是 5 元 我就得找钱了
        else{
            // 十块的话， 10元张数 + 1
            // 如果当前没有 5 元了，直接返回false
            // 如果当前有5元的，找钱一张 5 元
            if(bills[index] == 10){
                tens++;
                if(fives == 0){
                    return false;
                }
                fives--;
            // 顾客手头上是 20 的
            }else{
                // 找钱策略是： 有10的先用10的，找人 10 + 5
                // 否则找人 5 + 5 + 5
                // 如果没有10, 而且5 不够三张，false
                // 怎么滴也要用 5 的，如果当前没 5 ， false
                if ((tens == 0 && fives < 3 ) || fives == 0) {
                    return false;
                }
                // 先用 10， 减掉一张 10 一张 5， 否则减掉 3个5
                if(tens != 0){
                    tens--;
                    fives--;
                }else{
                    fives -= 3;
                }

            }
        }
        return doChange(bills, index + 1, fives, tens);
    }

}
