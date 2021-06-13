package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 278. 第一个错误的版本
你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。

假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。

你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 *
 * @Date 2021/6/13 13:41
 */

public class L278_FirstBadVersion extends VersionControl {

    public L278_FirstBadVersion(int bad) {
        super(bad);
    }
    public int firstBadVersion(int n){
        return (int)firstBadVersion((long)n);
    }
    /*
    * 二分！
    * 但是注意数据溢出
    * */
    public long firstBadVersion(long n) {
        long l = 1;
        long r = n;
        int m;
        while (l < r){
            m = (int)((l + r) >> 1);
            if (isBadVersion(m)){
                r = m - 1;
            }else
                l = m + 1;
        }
        return isBadVersion((int) l) ? l : l+1;
    }


    public static void main(String[] args) {
        L278_FirstBadVersion test = new L278_FirstBadVersion(1702766719);
        System.out.println(test.firstBadVersion(2126753390));
    }
}

class VersionControl {
    int bad;

    public VersionControl(int bad) {
        this.bad = bad;
    }

    boolean isBadVersion(int version) {
        if (version < bad)
            return false;
        return true;
    }


}