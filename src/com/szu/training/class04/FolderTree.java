package com.szu.training.class04;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个字符串类型的数组arr ,比如：[b\c, d\ , a\d\e]
 * 打印出路径中包含的目录结构，比如
 * a
 *      d
 *          e
 * b
 *      c
 * d
 * 而且需要按字典序输出哦！ 比如 a 开头的 路径就在最开始输出的，但是他不是数组中的第一个。
 * 同一级的顺序不能乱序！
 *
 * @Date 2021/4/14 15:21
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class FolderTree {

    class TrieTreeNode{
        String folder;
        TreeMap<String, TrieTreeNode> pathMap;

        public TrieTreeNode(String folder) {
            this.folder = folder;
            pathMap = new TreeMap<>();
        }
    }

    TrieTreeNode root;

    public FolderTree() {
        this.root = new TrieTreeNode(null);
    }
    /*
    * 解题思路：
    * 因为同级目录需要按照字典序排序
    * 所以使用 TreeMap来存放各个字符（串）
    * 建立前缀树的时候不再使用数组，此处改成使用 TreeMap 建立前缀树
    *  */
    public void insert(String location){
        String[] path = location.split("\\\\");
        TrieTreeNode node = root;
        for (int i = 0; i < path.length; i++) {
            TrieTreeNode curPathNode = node.pathMap.get(path[i]);
            if (curPathNode == null){
                TrieTreeNode newNode = new TrieTreeNode(path[i]);
                node.pathMap.put(path[i], newNode);
                curPathNode = newNode;
            }
            node = curPathNode;
        }
    }

    /* 递归实现一条路径彻底遍历结束，再开始下一条路径 */
    public ArrayList<String> getFolderTree(ArrayList<String> resultList, int level, TrieTreeNode node){
        Iterator<Map.Entry<String, TrieTreeNode>> iterator = node.pathMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, TrieTreeNode> next = iterator.next();
            StringBuffer pathSB = new StringBuffer();
            for (int i = 0; i < level; i++) {
                pathSB.append("      ");
            }
            pathSB.append(next.getKey());
            resultList.add(pathSB.toString());
            getFolderTree(resultList, level + 1, next.getValue());
        }
        return resultList;
    }

    public static void main(String[] args) {
        FolderTree folderTree = new FolderTree();
        folderTree.insert("a\\b\\cfgf");
        folderTree.insert("a\\b\\c\\ds");
        folderTree.insert("b\\c\\d");
        folderTree.insert("b\\c\\dtr");
        ArrayList<String> res = folderTree.getFolderTree(new ArrayList<String>(), 0, folderTree.root);
        for (String s:res             ) {
            System.out.println(s);
        }
    }



}
