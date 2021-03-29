package com.szu.practice.l17_sorted_table;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 *      亲  手撸一个傻逼树代码吧
 *
 * @Date 2021/3/28 20:04
 */

import java.util.Random;

public class SizeBalancedTreeMap<K extends Comparable<K>, V> {
    public SBNode<K,V> root;

    public void put(K key, V value){
        if (key == null)
            return;
        SBNode<K,V> lastInsert = getLastInsert(root, key);
        if (lastInsert != null && lastInsert.key.compareTo(key) == 0){
            lastInsert.value = value;
            return;
        }
        root = add(root, key, value);
    }

    private SBNode<K, V> add(SBNode<K, V> cur, K key, V value) {
        if (cur == null)
            return new SBNode<>(key, value);
        else {
            cur.size++;
            if (key.compareTo(cur.key) > 0)
                cur.right = add(cur.right, key, value);
            else
                cur.left = add(cur.left, key, value);
        }
        return maintain(cur);
    }

    private SBNode<K, V> maintain(SBNode<K, V> cur) {
        if (cur == null) return null;
        int leftSize = cur.left == null ? 0 : cur.left.size;
        int rightSize = cur.right == null ? 0 : cur.right.size;

        int leftLeftSize = cur.left == null || cur.left.left == null ? 0 : cur.left.left.size;
        int leftRightSize = cur.left == null || cur.left.right == null ? 0 : cur.left.right.size;

        int rightLeftSize = cur.right == null || cur.right.left == null ? 0 : cur.right.left.size;
        int rightRightSize = cur.right == null || cur.right.right == null ? 0 : cur.right.right.size;
        /* LL型 */
        if (leftLeftSize > rightSize){
            cur = rightRotate(cur);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        }
        /*
        * 一定要加 else 啊 卧槽尼玛的
        * 三个小时浪费在这里debug了
        * 卧槽尼玛
        * */
        /* LR型 */
        else if (leftRightSize > rightSize){
            cur.left = leftRotate(cur.left);
            cur = rightRotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        }
        /* RL型 */
        else if (rightLeftSize > leftSize){
            cur.right = rightRotate(cur.right);
            cur = leftRotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        }
        /* RR型 */
        else if (rightRightSize > leftSize){
            cur = leftRotate(cur);
            cur.left = maintain(cur.left);
            cur = maintain(cur);
        }
        return cur;
    }

    /* 普通右转一下 */
    private SBNode<K, V> rightRotate(SBNode<K, V> cur) {
        SBNode<K, V> left = cur.left;
        cur.left = left.right;
        left.right = cur;
        left.size = cur.size;
        cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
        return left;
    }
    /* 普通左转 */
    private SBNode<K, V> leftRotate(SBNode<K, V> cur) {
        SBNode<K, V> right = cur.right;
        cur.right = right.left;
        right.left = cur;
        right.size = cur.size;
        cur.size = (cur.left == null ? 0 : cur.left.size) + ( cur.right == null ? 0 : cur.right.size ) + 1;
        return right;
    }

    private SBNode<K, V> getLastInsert(SBNode<K, V> cur, K key) {
        SBNode<K, V> pre = null;
        while (cur != null){
            pre = cur;
            if (key.compareTo(cur.key) > 0)
                cur = cur.right;
            else if (key.compareTo(cur.key) < 0)
                cur = cur.left;
            else
                return cur;
        }
        return pre;
    }

    public void remove(K key) {
        if (key == null)
            return;
        SBNode<K,V> lastInsert = getLastInsert(root, key);
        if (lastInsert != null && lastInsert.key.compareTo(key) == 0){
//            System.out.println("delete "+ key);
            root = delete(root, key);
        }
    }

    private SBNode<K, V> delete(SBNode<K, V> cur, K key) {
        // 涉及删除节点的时候，一定做好size维护
        cur.size--;
        if (key.compareTo(cur.key) > 0)
            cur.right = delete(cur.right, key);
        else if (key.compareTo(cur.key) < 0)
            cur.left = delete(cur.left, key);
        else {
            if (cur.left == null && cur.right == null)
                cur = null;
            else if (cur.left == null && cur.right != null)
                cur = cur.right;
            else if (cur.left != null && cur.right == null)
                cur = cur.left;
            else {
                /* 删除节点左右子树都非空的节点 */
                /* 此时节点不再是cur了，所以接下来的节点的size都要 --  */
                SBNode<K, V> successor = cur.right;
                successor.size--;
                /* pre 节点用来指向 后继节点的父节点 */
                SBNode<K, V> pre = null;
                while (successor.left != null){
                    pre = successor; // 沿途一路记录下来
                    successor = successor.left;
                    successor.size--;
                }
                // 如果后继节点就是 cur 的 右子节点，此时pre 为空
                if (pre != null){
                    pre.left = successor.right;
                    /*
                    * ======================================================================
                    * ======================================================================
                    * 这句话一定写在里边！！！！！！！
                    * 因为如果写在 if 外边，当 successor.right 不为空的时候，直接用空值覆盖了存在的节点
                    * ======================================================================
                    * ======================================================================
                    * */
                    successor.right = pre;
                }
                successor.left = cur.left;
                successor.size = successor.left.size + (successor.right == null ? 0 : successor.right.size) + 1;
                cur = successor;
            }
        }
        return cur;
    }
}

class SBNode<K extends Comparable<K>, V>{
    public K key;
    public V value;
    public int size;
    public SBNode<K,V> left;
    public SBNode<K,V> right;

    public SBNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.size = 1;
    }
}


class TestSBTree{
    public static void main(String[] args) {

        SizeBalancedTreeMap<Integer, Integer> sbTree = new SizeBalancedTreeMap<>();

        sbTree.put(62 ,1);
        sbTree.put(49 ,1);
        sbTree.put(11 ,1);
        sbTree.put(76 ,1);
        sbTree.put(3 ,1);
        sbTree.put(32 ,1);
        sbTree.put(12 ,1);
        sbTree.put(2 ,1);
        sbTree.put(98 ,1);
        sbTree.put(31 ,1);
        sbTree.put(31 ,1);
        sbTree.put(13 ,1);
        sbTree.put(53 ,1);
        sbTree.put(84 ,1);
        sbTree.put(63 ,1);
        sbTree.remove(98);
        sbTree.put(30 ,1);
        sbTree.put(71 ,1);

        Random random = new Random();

        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 10000; j++) {
                int op = random.nextInt(100);
                if (op <= 50){
                    int key = random.nextInt(100);
//                    System.out.println("put " + key);
                    sbTree.put(key, random.nextInt(100));

                }else{

                    sbTree.remove(random.nextInt(100));
                }
            }
            for (int j = 0; j < 10000; j++) {
                sbTree.put(random.nextInt(100), random.nextInt(100));
            }
            int leftSize = sbTree.root.left == null ? 0 : sbTree.root.left.size;
            int rightSize = sbTree.root.right == null ? 0 : sbTree.root.right.size;
            int leftLeftSize = sbTree.root.left == null || sbTree.root.left.left == null ? 0 : sbTree.root.left.left.size;
            int leftRightSize = sbTree.root.left == null || sbTree.root.left.right == null ? 0 : sbTree.root.left.right.size;
            int rightLeftSize = sbTree.root.right == null || sbTree.root.right.left == null ? 0 : sbTree.root.right.left.size;
            int rightRightSize = sbTree.root.right == null || sbTree.root.right.right == null ? 0 : sbTree.root.right.right.size;
            if (rightLeftSize > leftSize)
                System.out.println("FUCK");
            if (rightRightSize > leftSize)
                System.out.println("FUCK");
            if (leftRightSize > rightSize)
                System.out.println("FUCK");
            if (leftLeftSize > rightSize)
                System.out.println("FUCK");
        }
    }
}