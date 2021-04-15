package com.szu.training.class04;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *  二叉树的前序和中序的遍历数组给定，返回后续遍历的数组
 *  pre[] = {1,2,4,5,3,6,7}
 *  in[] = {4,2,5,1,6,3,7}
 *  返回
 *  post = {4,5,2,6,7,3,1};
 *
 *
 * @Date 2021/4/15 17:11
 */

public class PreAndInArrayToPostArray {

    public static int[] preInToPos(int[] pre, int[] in) {
        int length = pre.length;
        int[] post = new int[length];

        preInToPos(pre, 0, length - 1,
                in, 0, length - 1,
                post, 0 , length - 1);
        return post;
    }


    public static void preInToPos(int[] pre, int preStart, int preEnd,
                                    int[] in, int inStart, int inEnd,
                                    int[] post, int postStart, int postEnd) {
        if (preStart > preEnd)
            return;
        if (preStart == preEnd){

            post[postStart] = pre[preStart];
            return;
        }

        int mid = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == pre[preStart]) {
                mid = i;
                break;
            }
        }
        int leftSize = mid - inStart;
        post[postEnd] = pre[preStart];
        /*
        * 1,    2,    4,    5,                 3            (pre)
        *      |--liftSize---|             |rightSize|
        *
        * |--liftSize---|      mid         |rightSize|
         *                     ↓
        * 4,    2,    5,       1,              3            (in)
        *
        * |--liftSize---|    |rightSize|
        * |-------------|    |--|              1            (post)
        * */
        preInToPos(pre, preStart + 1, preStart + leftSize,
                   in, inStart, mid - 1,
                   post, postStart, postStart + leftSize - 1);
        preInToPos(pre, preStart + leftSize + 1, preEnd,
                    in, mid + 1, inEnd,
                    post, postStart + leftSize, postEnd - 1);
    }

    public static void main(String[] args) {

        PreAndInArrayToPostArray test = new PreAndInArrayToPostArray();
        int[] pre = {1,2,4,5,3,6,7};
        int[] in = {4,2,5,1,6,3,7};
        int[] post = preInToPos(pre, in);
        System.out.println();
    }
}
