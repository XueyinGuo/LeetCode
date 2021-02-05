package com.szu.leetcode.tree.binary_tree;

import com.szu.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PathSum_113 {
    List<List<Integer>> res;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        doFindPathSum(root, 0, targetSum, cur);
        return res;
    }

    public void doFindPathSum(TreeNode root, int sum, int targetSum, List<Integer> cur){

        if(root == null){
            return;
        }
        // 每次到一层就先加到列表中
        cur.add(root.val);
        sum = sum + root.val;
        if(sum == targetSum && root.left == null && root.right == null){
            // 如果找到了相等的值，而且这个节点也是根节点，则把当前的列表加入到结果集中
            res.add(new ArrayList<>(cur));
        }
        doFindPathSum(root.left, sum, targetSum, cur);
        doFindPathSum(root.right, sum, targetSum, cur);
        // 因为从头到尾都是用的同一个列表，所以每次新加完本层之后，都要回复之前的状态
        cur.remove(cur.size() - 1);
    }

}
