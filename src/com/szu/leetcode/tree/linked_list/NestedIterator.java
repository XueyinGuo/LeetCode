package com.szu.leetcode.tree.linked_list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/*
* 341. 扁平化嵌套列表迭代器
* */

public class NestedIterator implements Iterator<Integer> {

    LinkedList<NestedInteger> list;

    public NestedIterator(List<NestedInteger> nestedList) {
        //创建一个链表， 把每个 nestedList 中的内容变成存储在一个链表中
        list = new LinkedList(nestedList);
    }

    @Override
    public boolean hasNext() {
        // 只有当前链表不为空， 而且 链表第一个元素不是数字的时候进入循环
        while( ! list.isEmpty() && !list.get(0).isInteger()){
            // 已经确定第一个元素不是数字，直接调用 getList 获取到 列表
            List<NestedInteger> head = list.remove(0).getList();
            // 遍历整个list，把每个元素拿出来放到一开始声明的链表中，并且是按照下标存放。
            // 为了让之后的遍历可以按照正确的顺序获取值
            for(int i = 0; i < head.size(); i++){
                list.add(i, head.get(i));
            }
        }
        return !list.isEmpty();
    }

    @Override
    public Integer next() {
        // hasNext 已经保证了链表第一个元素肯定是一个数字
        return list.remove(0).getInteger();
    }
}

class NestedInteger {
    private Integer val;
    private List<NestedInteger> list;

    public NestedInteger(Integer val) {
        this.val = val;
        this.list = null;
    }
    public NestedInteger(List<NestedInteger> list) {
        this.list = list;
        this.val = null;
    }

    // 如果其中存的是一个整数，则返回 true，否则返回 false
    public boolean isInteger() {
        return val != null;
    }

    // 如果其中存的是一个整数，则返回这个整数，否则返回 null
    public Integer getInteger() {
        return this.val;
    }

    // 如果其中存的是一个列表，则返回这个列表，否则返回 null
    public List<NestedInteger> getList() {
        return this.list;
    }
}