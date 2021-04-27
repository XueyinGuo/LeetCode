package com.szu.training02.class04;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
 * 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
 * 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * 四个参数：arr, n, a, b
 * 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 *
 * @Date 2021/4/27 20:32
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class CoffeeMachine {

    static class CoffeeNode {
        public int timePoint;
        public int workTime;

        public CoffeeNode(int start, int workCost) {
            this.timePoint = start;
            this.workTime = workCost;
        }
    }

    static Comparator<CoffeeNode> comparator = (node1, node2) ->{
            return node1.timePoint + node1.workTime - node2.workTime - node2.timePoint;
    };
    /*
    * 贪心，利用 咖啡机的开始工作时间点 和 咖啡机的工作消耗 做比较器构建小根堆
    *
    * 每次堆里弹出一个当前可以最早让顾客结束等待的咖啡机
    *
    * 这个咖啡机下次开始工作的时间点就是 当前顾客拿到咖啡的时间
    *
    * 所以修改 咖啡机的 开始工作时间，放回堆里
    *
    * drinks[i] = poll.timePoint+ poll.workTime;
    * poll.timePoint = drinks[i];
    * queue.add(poll);
    * */
    private static int[] myBestChoices(int[] machine, int people) {
        int drinks[] = new int[people];
        PriorityQueue<CoffeeNode> queue = new PriorityQueue<>(comparator);
        for (int i = 0; i < machine.length; i++) {
            queue.add(new CoffeeNode(0, machine[i]));
        }
        for (int i = 0; i < people; i++) {
            CoffeeNode poll = queue.poll();
            drinks[i] = poll.timePoint+ poll.workTime;
            poll.timePoint = drinks[i];
            queue.add(poll);
        }
        return drinks;
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int[] machine = LeetCodes.getRandomArray(10, 20);
            int people = random.nextInt(500);
            int[] choicesRight = bestChoicesRight(machine, people);
            int[] myBestChoices = myBestChoices(machine, people);
            for (int j = 0; j < people; j++) {
                if (choicesRight[j] != myBestChoices[j])
                    System.out.println("FUCK");

            }
        }

    }




    public static class CoffeeMachineComparator implements Comparator<CoffeeNode>{

        @Override
        public int compare(CoffeeNode o1, CoffeeNode o2) {
            return o1.timePoint + o1.workTime - o2.timePoint - o2.workTime;
        }

    }




    public static int[] bestChoicesRight(int[] arr, int M) {
        int[] ans = new int[M];
        PriorityQueue<CoffeeNode> heap = new PriorityQueue<>(new CoffeeMachineComparator());
        for(int coffeWork : arr) {
            heap.add(new CoffeeNode(0, coffeWork));
        }
        for(int i = 0; i< M; i++) {
            CoffeeNode cur = heap.poll();
            ans[i] = cur.timePoint + cur.workTime;
            cur.timePoint = ans[i];
            heap.add(cur);
        }
        return ans;
    }

}
