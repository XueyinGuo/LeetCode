package com.szu.leetcode;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      331. 验证二叉树的前序序列化
 *       序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 *
 *               _9_
 *              /   \
 *             3     2
 *            / \   / \
 *           4   1  #  6
 *          / \ / \   / \
 *          # # # #   # #
 *          例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 *
 *          给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 *
 *          每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 *
 *          你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如"1,,3" 。
 *
 *          来源：力扣（LeetCode）
 *          链接：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree
 *          著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/3/20 11:51
 */

public class L331_IsValidSerialization {

    public boolean isValidSerialization(String preorder) {
        int len = preorder.length();
        if(preorder == null || len  == 0) return true;
        /*
         * 可以定义一个概念，叫做槽位。
         * 一个槽位可以被看作「当前二叉树中正在等待被节点填充」的那些位置。
         * 二叉树的建立也伴随着槽位数量的变化。每当遇到一个节点时：
         *
         *     1. 如果遇到了空节点，则要消耗一个槽位；
         *
         *     2. 如果遇到了非空节点，则除了消耗一个槽位外，还要再补充两个槽位。
         *
         */
        int slots = 1;
        int i = 0;
        while(i < len){
            // 如果还没遍历完字符串，此时就已经没有任何位置填充了，直接返回 false
            if(slots == 0) return false;
            char c = preorder.charAt(i);
            if( c == ',')
                // 不做任何操作，直接跳去下一个字符
                i++;
            else if( c == '#') {
                // 空节点，不增加待填充的节点，只消耗一个节点
                slots--;
                i++;
            }
            else{
                // 找到这个数字的结尾
                while( c != ','){
                    i++;
                    // 字符串最后一个数是数字的话会溢出，所以判断有没有越界
                    if (i >= len) break;
                    c = preorder.charAt(i);
                }
                // 是一个数字，则这个节点是一个真实的节点，消耗一个填充，补充两个子节点待填充， -1 + 2 = +1
                slots++;
            }
        }
        // 最后是否所有的节点已经填充完毕
        return slots == 0;
    }

}
