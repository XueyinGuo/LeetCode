package com.szu.practice.l17_sorted_table;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      AVL树代码完整版
 *
 * 亲 手撸一遍平衡树代码吧
 *
 * @Date 2021/3/28 20:03
 */


import java.util.Random;

public class AVLTreeMap<K extends Comparable<K>, V> {

    public int size;
    public AVLNode<K, V> root;

    public AVLTreeMap() {
        root = null;
        size = 0;
    }



    /*
    * 找到合适的插入位置，如果存在当前key 则返回当前key 的节点
    * 否则 返回合适的插入位置的节点
    * */
    private AVLNode<K, V> findLastIndex(K key) {
        AVLNode<K, V> pre = root;
        AVLNode<K, V> cur = root;
        while (cur != null){
            pre = cur;
            if (cur.key.compareTo(key) == 0)
                break;
            else if (cur.key.compareTo(key) > 0)
                cur = cur.left;
            else
                cur = cur.right;
        }
        return pre;
    }

    private AVLNode<K, V> add(AVLNode<K, V> cur, AVLNode<K, V> node) {
        if (cur == null)
            return node;
        if (node.key.compareTo(cur.key) < 0)
            cur.left = add(cur.left, node);
        else
            cur.right = add(cur.right, node);
        cur.high = Math.max(
                cur.left == null ? 0 : cur.left.high ,
                cur.right == null ? 0 : cur.right.high
        ) + 1;
        return maintain(cur);
    }

    /*
    * 保持平衡，让左右的高度差值不高于 1
    * */
    private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
        if (cur == null)
            return null;
        // 计算左右子树的高度
        int leftHeight = cur.left == null ? 0 : cur.left.high;
        int rightHeight = cur.right == null ? 0 : cur.right.high;
        // 如果左右子树的高度差大于 1
        if (Math.abs( leftHeight - rightHeight ) > 1){
            // 左边比较高的话
            if (leftHeight > rightHeight){
                int leftLeftHeight = cur.left == null || cur.left.left == null ? 0 : cur.left.left.high;
                int leftRightHeight = cur.left == null || cur.left.right == null ? 0 : cur.left.right.high;
                // 判断那种形式的不满足： if LL型 else LR型
                if (leftLeftHeight >= leftRightHeight){
                    cur = rotateRight(cur);
                }else {
                    cur.left = rotateLeft(cur.left);
                    cur = rotateRight(cur);
                }
            }else {
            // 右边比较高
                int rightLeftHeight = cur.right == null || cur.right.left == null ? 0 : cur.right.left.high;
                int rightRightHeight = cur.right == null || cur.right.right == null ? 0 : cur.right.right.high;
                // if RR 型 else RL型
                if (rightRightHeight >= rightLeftHeight)
                    cur = rotateLeft(cur);
                else {
                    cur.right = rotateRight(cur.right);
                    cur = rotateLeft(cur);
                }
            }
        }
        return cur;
    }

    /* 向左旋转 */
    private AVLNode<K, V> rotateLeft(AVLNode<K, V> cur) {
        AVLNode<K, V> right = cur.right;
        cur.right = right.left;
        right.left = cur;
        // 交换引用之后，重新计算高度，后边需要加上 1 因为 自己也算一层
        cur.high = Math.max(cur.left == null ? 0 : cur.left.high, cur.right == null ? 0 : cur.right.high) + 1;
        right.high = Math.max(right.left == null ? 0:right.left.high , right.right == null ? 0 : right.right.high) + 1;
        return right;
    }

    /* 向右旋转 */
    private AVLNode<K, V> rotateRight(AVLNode<K, V> cur) {
        AVLNode<K, V> left = cur.left;
        cur.left = left.right;
        left.right = cur;
        // 交换引用之后，重新计算高度，后边需要加上 1 因为 自己也算一层
        cur.high = Math.max(cur.left == null ? 0 : cur.left.high , cur.right == null ? 0 : cur.right.high) + 1;
        left.high = Math.max( left.left == null ? 0 : left.left.high, left.right == null ? 0 : left.right.high ) + 1;
        return left;
    }

    /*
     * 放置元素
     * */
    public void put(K key, V value){
        if (key == null){
            System.out.println("Key 为 空");
            return;
        }
        // 找到应该插入的位置
        AVLNode<K, V> lastNode = findLastIndex(key);
        // 如果位置节点的key 值 和 想要插入的key 值一样，则视为一个修改 value 操作
        if (lastNode != null && lastNode.key.compareTo(key) == 0){
            lastNode.value = value;
            return;
        }
        // 没找到该key，需要真的插入这个节点
        size++;
        root = add(root, new AVLNode<K, V>(key, value));
    }

    public void remove(K key) {
        if (key == null){
            System.out.println("Key 为 空");
            return;
        }
        // 找到应该插入的位置
        AVLNode<K, V> lastNode = findLastIndex(key);
        if (lastNode == null || lastNode.key.compareTo(key) != 0){
//            System.out.println("您要删除的key不存在");
            return;
        }
        size--;
        root = delete(root, key);
    }

    private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
        if (cur == null)
            return null;
        if (key.compareTo(cur.key) > 0)
            cur.right = delete(cur.right, key);
        else if (key.compareTo(cur.key) < 0)
            cur.left = delete(cur.left, key);
        else {
            // 叶子结点
            if (cur.left == null && cur.right == null){
                cur = null;
            // 右树不为空
            }else if(cur.left == null && cur.right != null){
                cur = cur.right;

            // 左树不为空
            }else if (cur.left != null && cur.right == null){
                cur = cur.left;
            // 左右子树都不为空
            }else {
                /*
                * 二叉搜索树删除一个左右子树都不为空的节点
                * */
                // 找到后继节点
                AVLNode<K, V> successor = cur.right;
                while (successor.left != null)
                    successor = successor.left;
                // 在当前节点右树上删除后继节点
                cur.right = delete( cur.right, successor.key );
                // 然后用后继节点顶替当前节点位置
                successor.left = cur.left;
                successor.right = cur.right;
                cur = successor;
            }
        }
        // 重新计算高度
        if (cur != null)
            cur.high = Math.max(cur.left == null ? 0 : cur.left.high, cur.right == null ? 0 : cur.right.high) + 1;
        return maintain(cur);
    }

    public V get(K key) {
        if (key == null){
            System.out.println("Key 为 空");
            return null;
        }
        // 找到应该插入的位置
        AVLNode<K, V> lastNode = findLastIndex(key);
        if (lastNode != null && lastNode.key.compareTo(key) == 0){

            return lastNode.value;
        }
        System.out.println("您要获取的key不存在");
        return null;
    }

    public K firstKey() {
        if (root == null) {
            return null;
        }
        AVLNode<K, V> cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.key;
    }

    public K lastKey() {
        if (root == null) {
            return null;
        }
        AVLNode<K, V> cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.key;
    }

    public K floorKey(K key) {
        if (key == null) {
            return null;
        }
        AVLNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
        return lastNoBigNode == null ? null : lastNoBigNode.key;
    }

    private AVLNode<K, V> findLastNoBigIndex(K key) {
        AVLNode<K, V> cur = root;
        AVLNode<K, V> ans = null;
        while (cur != null){

            if (key.compareTo(cur.key) > 0){
                ans = cur;
                cur = cur.right;
            }
            else if (key.compareTo(cur.key) < 0)
                cur = cur.left;
            else{
                return cur;
            }
        }
        return ans;
    }

    public K ceilingKey(K key) {
        if (key == null) {
            return null;
        }
        AVLNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
        return lastNoSmallNode == null ? null : lastNoSmallNode.key;
    }

    /* 找到比当前key第一个不小的节点，可以相等 */
    private AVLNode<K, V> findLastNoSmallIndex(K key) {
        AVLNode<K, V> cur = root;
        AVLNode<K, V> ans = null;
        while (cur != null){
            // 右滑的时候不记录答案
            if (key.compareTo(cur.key) > 0){
                cur = cur.right;
            }
            else if (key.compareTo(cur.key) < 0){
                ans = cur;
                cur = cur.left;
            }
            else{
                return cur; // 相等
            }
        }
        return ans;
    }
}

class AVLNode<K extends Comparable<K>, V>{
    public K key;
    public V value;
    public AVLNode<K, V> left;
    public AVLNode<K, V> right;
    public int high;

    public AVLNode(K k, V v) {
        this.key = k;
        this.value = v;
        this.high = 1;
    }
}

class TestAVL{
    public static void main(String[] args) {
        Random random = new Random();
        AVLTreeMap<Integer, Integer> avlTree = new AVLTreeMap<>();
        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 10000; j++) {
                int op = random.nextInt(100);
                if (op <= 50){
                    avlTree.put(random.nextInt(100), random.nextInt(100));
                }else
                    avlTree.remove(random.nextInt(100));
            }
            int leftHeight = avlTree.root.left.high;
            int rightHeight = avlTree.root.right.high;
            if (Math.abs(leftHeight - rightHeight) > 1)
                System.out.println("FUCK");
        }
    }
}


