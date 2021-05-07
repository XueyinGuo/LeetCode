package com.szu.training03.class01;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 460. LFU 缓存
请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。

实现 LFUCache 类：

LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
int get(int key) - 如果键存在于缓存中，则获取键的值，否则返回 -1。
void put(int key, int value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。
当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。
在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
注意「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。

为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。

当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 *
 * @Date 2021/5/5 17:36
 */

import java.util.HashMap;

public class LFUCache {

    int capacity;
    int curSize;
    HashMap<Integer, Node> keyToNodeMap;
    HashMap<Node, Bucket> nodeToBucketMap;
    Bucket firstBucket;
    Bucket lastBucket;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.curSize = 0;
        this.keyToNodeMap = new HashMap<>();
        this.nodeToBucketMap = new HashMap<>();
        firstBucket = new Bucket();
        lastBucket = firstBucket;
    }

    public int get(int key) {
        /* 没有直接返回 -1 */
        Node node = keyToNodeMap.get(key);
        if (node == null)
            return -1;
        getOrUpdateNode(node);
        return node.val;
    }

    private void getOrUpdateNode(Node node) {
        /*
         * 有，返回 val， 并且要把操作次数 + 1 ,
         * 加一 的过程中 需要把当前节点从原 bucket 中挪出
         *
         * 从新桶中插入当前节点，如果当前桶的下一个桶 存放的节点的 操作次数 与当前节点不桶
         * 则就需要新插入一个新桶了
         * */
        Bucket bucket = nodeToBucketMap.get(node);
        bucket.remove(node);
        node.opTimes++;
        if (bucket.next != null && bucket.next.peek().opTimes == node.opTimes){
            bucket.next.add(node);
            nodeToBucketMap.put(node, bucket.next); // 节点换了桶之后，map 中也要更新
        }
        else {
            Bucket newBucket = Bucket.insertNewBucket(bucket, bucket.next);
            newBucket.add(node);
            nodeToBucketMap.put(node, newBucket);
        }
        /* 如果这是最后一个元素被移除了，桶的容量为 0，删除这个桶 */
        if (bucket.bucketSize == 0){
            if (bucket == firstBucket)
                firstBucket = firstBucket.next;
            bucket.releaseBucket();
        }
    }

    public void put(int key, int value) {
        if(capacity == 0) {
            return;
        }
        /* 查看有没有，有的话直接改值， 修改次数 + 1 */
        Node node = keyToNodeMap.get(key);
        if (node != null) {
            node.val = value;
            getOrUpdateNode(node);
            return;
        }
        /* 没有当前 key ,真的插入一条记录 */
        Node newNode = new Node(key, value);
        /*
        * 容量已经足够了，需要删除 频次最低的中，最先加进来的元素
        *
        * 删除之后记得 两个map也要删除掉对应的元素
        * */
        if (curSize == capacity) {
            curSize--;
            Node rem = firstBucket.deleteHead();
            nodeToBucketMap.remove(rem);
            keyToNodeMap.remove(rem.key);
        }
        if (firstBucket.peek() != null && firstBucket.peek().opTimes != 1)
            firstBucket = Bucket.insertNewHeadBucket(firstBucket);

        firstBucket.add(newNode);
        curSize++;
        keyToNodeMap.put(key, newNode);
        nodeToBucketMap.put(newNode, firstBucket);
    }

    static class Node {
        int key;
        int val;
        int opTimes;
        Node up;
        Node down;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.opTimes = 1;
        }
    }

    static class Bucket {

        Node head;
        Node tail;
        Bucket pre;
        Bucket next;
        int bucketSize;

        public Bucket() {
            this.bucketSize = 0;
        }

        /*
         * 两个桶之间插入一个桶
         * */
        public static Bucket insertNewBucket(Bucket pre, Bucket next) {
            Bucket bucket = new Bucket();
            if (pre != null)
                pre.next = bucket;
            bucket.pre = pre;
            if (next != null)
                next.pre = bucket;
            bucket.next = next;
            return bucket;
        }

        public static Bucket insertNewHeadBucket(Bucket curFirstBucket) {
            Bucket newBucket = new Bucket();
            newBucket.next = curFirstBucket;
            curFirstBucket.pre = newBucket;
            return newBucket;
        }

        /*
         * 返回桶顶部元素
         * */
        public Node peek() {
            return head;
        }

        /*
         * 把一个节点从桶中删除
         * */
        public void remove(Node node) {
            bucketSize--;
            if (node.up != null)
                node.up.down = node.down;
            if (node.down != null)
                node.down.up = node.up;
            if (head == node)
                head = head.down;
            if (tail == node)
                tail = node.up;
            node.up = null;
            node.down = null;
        }

        /*
         * 把一个节点加进桶中
         * */
        public void add(Node node) {
            if (bucketSize == 0) {
                head = node;
                tail = node;
            } else {
                node.up = tail;
                tail.down = node;
                tail = node;
            }
            bucketSize++;
        }

        /*
         * 当一个桶中已经没有元素的时候，释放这个桶
         * */
        public void releaseBucket() {
            if (this.pre != null)
                this.pre.next = this.next;

            if (this.next != null)
                this.next.pre = this.pre;

            this.pre = null;
            this.next = null;
        }

        /*
         * 删除本桶中最先加进来的元素
         * */
        public Node deleteHead() {
            Node ret = head;
            bucketSize--;
            head = head.down;
            if (bucketSize != 0)
                head.up = null;

            return ret;
        }
    }

}


class Test{
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);
        cache.put(3,3);
        cache.get(2);
        cache.get(3);
        cache.put(4,4);
        cache.get(1);
        cache.get(3);
        cache.get(4);
        System.out.println();
    }
}
