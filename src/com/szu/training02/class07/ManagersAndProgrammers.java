package com.szu.training02.class07;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 项目有四个信息：
 * 1.哪个项目经理提出的
 * 2.被项目经理提出的时间
 * 3.项目优先级
 * 4.项目花费的时间
 * 例如：[1,3,2,2]
 *      【被1号项目经理提出的，3号时间点写完项目计划书，优先级是2，做完项目耗费的时间为 2】
 * 给定一个正数 managers，表示经理的数量，每个经理只负责自己的项目，并且一次只能提交一个项目给程序员们，这个项目做完了才能提交。
 *
 * 经理对项目的喜好是这样：
 *          项目优先级越高，越先提交项目
 *          优先级一样的项目，花费时间越少，越先被提交
 *          还一样的越早写完计划书的，越先被提交
 *
 * 给定一个正数 programmers，表示程序员的数量，所有经理提交的项目中，程序员会按照自己的喜好来挑选项目来完成，每个人做完一个才能做下一个
 *          花费时间越少，越先被选择
 *          花费时间一样的，负责人编号小的，先选出来
 *
 * 提交一个长度为 N 的 projects 二维数组
 * 返回一个 长度为 N 的一位数组，表示这些项目结束的时间
 *
 * @Date 2021/5/4 9:51
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ManagersAndProgrammers {

    public static void main(String[] args) {
        int managers = 4;
        int programmers = 4;
        int[][] projects = {
                { 1, 1, 1, 2 },
                { 1, 2, 1, 1 },
                { 1, 3, 2, 2 },
                { 2, 1, 1, 2 },
                { 2, 3, 3, 5 },
                { 2, 5, 6, 7 },
                { 3, 3, 2, 4 },
                { 3, 6, 5, 6 },
                { 4, 6, 4, 2 },
                { 4, 7, 5, 7 },
                { 4, 3, 7, 8 },
        };

        int[] res = getFinishTime(managers, programmers, projects);
        Arrays.stream(res).forEach(
                n -> System.out.println(n)
        );
    }


    static class ManagerLikesRule implements Comparator<Project> {
        /*
         * 项目优先级越高，越先提交项目
         * 优先级一样的项目，花费时间越少，越先被提交
         * 还一样的越早写完计划书的，越先被提交
         * */
        @Override
        public int compare(Project o1, Project o2) {
            if (o1.priority != o2.priority)
                return o1.priority - o2.priority;
            if (o1.timeCost != o2.timeCost)
                return o1.timeCost - o2.timeCost;
            return o1.comeUpTime - o2.comeUpTime;
        }
    }

    private static int[] getFinishTime(int managers, int programmers, int[][] projects) {
        PriorityQueue<Project>[] managersQueue = new PriorityQueue[managers + 1];
        PriorityQueue<Project> lockedProjects = new PriorityQueue<>(new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o1.comeUpTime - o2.comeUpTime;
            }
        });
        for (int i = 1; i <= managers; i++) {
            managersQueue[i] = new PriorityQueue<>(new ManagerLikesRule());
        }

        /* 先把所有的项目封装成对象,放到暂时都被锁住的项目堆中，只有到了提出时间，才会被放入每个经理自己的堆中 */
        for (int i = 0; i < projects.length; i++) {
            lockedProjects.add(new Project(i, projects[i][0], projects[i][1], projects[i][2], projects[i][3]));
//            managersQueue[projects[i][0]].add(project);
        }
        /* 每个程序员都在 1 时间点看看有没有项目可以做 */
        PriorityQueue<Integer> programmersQueue = new PriorityQueue<>();
        for (int i = 0; i < programmers; i++) {
            programmersQueue.add(1);
        }

        ProgrammersChoicesQueue programmersChoicesQueue = new ProgrammersChoicesQueue(managers);
        int[] finishTime = new int[projects.length];
        int finished = 0;
        while (finished != projects.length) {

            int timeLine = programmersQueue.peek();
            while (!lockedProjects.isEmpty() && lockedProjects.peek().comeUpTime <= timeLine) {
                Project unlocked = lockedProjects.poll();
                managersQueue[unlocked.managerId].add(unlocked);
            }
            /* 每个项目经理更换自己的项目，需要快速找到自己项目在程序员堆中的位置，然后替换之后，维持堆的结构合理性 */
            for (int i = 1; i <= managers; i++) {
                if (!managersQueue[i].isEmpty())
                    programmersChoicesQueue.add(managersQueue[i].peek());
            }


            /*
             * 每个项目经理操作完，轮到程序员出来选项目做了
             * 程序员应该怎么弹出呢？
             * >= programmersChoicesQueue comeUpTime 的弹出
             * */
            if (!programmersChoicesQueue.isEmpty()) {
                Project doable = programmersChoicesQueue.pop();
                int programmerNextFreeTime = programmersQueue.poll() + doable.timeCost;
                programmersQueue.add(programmerNextFreeTime);
                finishTime[doable.index] = programmerNextFreeTime;
                managersQueue[doable.managerId].poll();
                finished++;
            }

            /*
             * 有可能操作一圈，程序员开始选活干的时候没有任何项目，这种情况的时候，避免陷入死循环，
             * 把程序员堆顶的元素设置为 lock项目的 comeUpTime
             * */
            if (programmersChoicesQueue.isEmpty() && !lockedProjects.isEmpty()) {
                Integer lastFinishTime = programmersQueue.peek();
                int nextUnlockTime = lockedProjects.peek().comeUpTime;
                if (lastFinishTime < nextUnlockTime) {
                    programmersQueue.poll();
                    programmersQueue.add(nextUnlockTime);
                }
            }
        }
        return finishTime;
    }

    static class ProgrammersChoicesQueue {
        /*
         * 自定义堆实现，快速定位 每个项目经理提出的项目位于 程序员选择堆上的哪个位置
         *
         * 当前堆大小 curSize
         * index[] 存放每个项目经理的项目所在位置，避免遍历行为的发生
         *
         * lastIndex 用来做 维持堆结构的变量，总是等于 当前堆大小 - 1
         * */
        public int maxSize;
        public Project[] committedProjects;
        int curSize;
        //        int lastIndex;
        int[] index;

        public ProgrammersChoicesQueue(int managerNums) {
            this.maxSize = managerNums;
            this.committedProjects = new Project[managerNums];
            this.curSize = 0;
            this.index = new int[managerNums + 1];
            for (int i = 1; i <= managerNums; i++) {
                index[i] = -1; // 初始化的时候每个项目经理都没有项目提交
            }
//            lastIndex = -1;
        }

        /*
         * 添加项目，提交过的话 index[id] 不为 -1， 需要替换项目
         * 如果为 -1， 直接堆尾插入
         * */
        public void add(Project project) {
            int id = project.managerId;
            if (index[id] == -1) {
                committedProjects[curSize] = project;
                index[project.managerId] = curSize;
                heapInsert(curSize++);
//                setLastIndex(curSize);
            } else {
                int position = this.index[id];
                committedProjects[position] = project;
                heapInsert(position);
                heapify(position);
            }
        }

        private void heapify(int position) {
            int leftSon = position * 2 + 1;
//            while (leftSon <= lastIndex){
            while (leftSon <= curSize - 1) {

                int rightSon = leftSon + 1;
                int smallIndex = leftSon;
//                if (rightSon <= lastIndex) {
                if (rightSon <= curSize - 1) {
                    smallIndex = getSmallIndex(leftSon, rightSon);
                }
                /* 如果当前元素，比自己两个孩子都小，不用交换了，直接 break */
                if (programmersLikesRule(committedProjects[position], committedProjects[smallIndex]))
                    break;
                else {
                    swap(position, smallIndex);
                }

                position = smallIndex;
                leftSon = position * 2 + 1;
            }
        }

        /*
         * 获取左右两个孩子更小的那个下标
         * */
        private int getSmallIndex(int leftSon, int rightSon) {
            Project left = committedProjects[leftSon];
            Project right = committedProjects[rightSon];
            ;

            if (programmersLikesRule(left, right))
                return leftSon;
            return rightSon;
        }

        private void heapInsert(int insertIndex) {
            int father = (insertIndex - 1) / 2;
            while (programmersLikesRule(committedProjects[insertIndex], committedProjects[father])) {
                swap(insertIndex, father);
                insertIndex = father;
                father = (insertIndex - 1) / 2;
            }
        }

        /*
         * 比较两个提交的项目在程序员堆中的大小
         * 返回 true， p1 更小
         * 返回 false， p2 更小
         * */
        private boolean programmersLikesRule(Project p1, Project p2) {
            /*
             * 花费时间越少，越先被选择
             * 花费时间一样的，负责人编号小的，先选出来
             * */
            if (p1.timeCost < p2.timeCost)
                return true;
            if (p1.managerId < p2.managerId)
                return true;
            return false;
        }

        private void swap(int i1, int i2) {
            /*
             * 按照堆排序规则进行交换，但是交换的时候不要忘了 index 数组记录的每个经理的元素的位置也要交换
             * */
            Project p1 = committedProjects[i1];
            Project p2 = committedProjects[i2];
            committedProjects[i1] = p2;
            committedProjects[i2] = p1;

            index[p1.managerId] = i2;
            index[p2.managerId] = i1;

        }

//        private void setLastIndex(int curSize) {
//            this.lastIndex = curSize - 1;
//        }

        public boolean isEmpty() {
            return curSize == 0;
        }

        /* 调用时请自己手动保证堆不为空 */
        public Project pop() {
            Project ret = committedProjects[0];
            swap(0, curSize - 1);
            index[ret.managerId] = -1;
            curSize--;
//            setLastIndex(curSize);

            heapify(0);
            return ret;
        }
    }


    static class Project {
        int index;
        int managerId;
        int comeUpTime;
        int priority;
        int timeCost;

        public Project(int index, int managerId, int comeUpTime, int priority, int timeCost) {
            this.index = index;
            this.managerId = managerId;
            this.comeUpTime = comeUpTime;
            this.priority = priority;
            this.timeCost = timeCost;
        }
    }
}
