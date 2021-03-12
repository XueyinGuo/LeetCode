package com.szu.leetcode;
/*
* 剑指 Offer 04. 二维数组中的查找
*
* 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，
* 每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，
* 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
*
* 来源：力扣（LeetCode）
* 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
* 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
public class Offer_4_FindNumberIn2DArray {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {


        if(matrix.length == 0 || matrix[0].length == 0){
            return false;
        }

        int curRow = 0;
        int curCol = matrix[0].length - 1;
        // 可以发现一个规律就是，左上角为顶点的话， 这是一个左树递减，右树递增 的 二叉树
        while( curRow < matrix.length && curCol >= 0 ){
            int cur = matrix[curRow][curCol];
            if(matrix[curRow][curCol] == target ){
                return true;
            }
            // 发现现在的元素已经开始大于 target 了
            // 我们可以继续去左子树上找
            if(matrix[curRow][curCol] > target){
                curCol--;
                continue;
            }
            // 元素小于 target 了
            // 去右子树上找
            if(matrix[curRow][curCol] < target){
                curRow++;
                continue;
            }
        }
        return false;
    }

}
