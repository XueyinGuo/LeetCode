package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 剑指 Offer 37. 序列化二叉树
请实现两个函数，分别用来序列化和反序列化二叉树。

你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * @Date 2021/7/4 23:13
 */

import com.szu.leetcode.utils.LeetCodes;
import com.szu.leetcode.utils.TreeNode;

public class Offer_37_SerializeBinTree {

    StringBuffer sb = new StringBuffer();
    int index = 0;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        encode(root);
        return sb.toString();
    }

    public void encode(TreeNode root){
        if(root == null){
            sb.append('#');
            return;
        }
        sb.append(root.val);
        sb.append(' ');
        serialize(root.left);
        serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        char[] str = data.toCharArray();
        return deserialize(str);
    }

    public TreeNode deserialize(char[] str){
        if(index >= str.length || str[index] == '#'){
            index++;
            return null;
        }

        int value = getNextNum(str);
        TreeNode node = new TreeNode(value);
        node.left = deserialize(str);
        node.right = deserialize(str);
        return node;
    }

    public int getNextNum(char[] str){
        int val = 0;
        boolean negative = false;
        if(str[index] == '-'){
            index++;
            negative = true;
        }

        while(index < str.length && str[index] != ' '){
            val = val * 10 + str[index] - '0';
            index++;
        }
        index++;
        return negative?  -val : val;
    }

    public static void main(String[] args) {
        Integer[] integers = {1, -2, 30, null, null, -544, 5};
        TreeNode treeNode = LeetCodes.integerArrayToTree(integers);
        Offer_37_SerializeBinTree test = new Offer_37_SerializeBinTree();
        String serialize = test.serialize(treeNode);
        TreeNode node = test.deserialize(serialize);
        System.out.println();
    }
}
