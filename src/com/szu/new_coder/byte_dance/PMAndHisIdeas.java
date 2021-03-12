package com.szu.new_coder.byte_dance;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description

        https://www.nowcoder.com/question/next?pid=8537279&qid=141058&tid=41922278
        拉鸡巴倒吧
 * @Date 2021/3/12 13:19
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PMAndHisIdeas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int PMs = sc.nextInt();
        int programmers = sc.nextInt();
        int num = sc.nextInt();
        Idea[] ideas = new Idea[num];
        for (int i = 0; i < num; i++) {
            int PM = sc.nextInt();
            int time = sc.nextInt();
            int priority = sc.nextInt();
            int costTime = sc.nextInt();
            ideas[i] = new Idea(PM, time, priority, costTime);
        }
        int[] finishTime = finishIdea(ideas, programmers);
        for (int i = 0; i < finishTime.length; i++) {
            System.out.println(finishTime[i]);
        }

    }

    private static int[] finishIdea(Idea[] ideas, int programmers) {
        int length = ideas.length;
        int res[] = new int[length];
        Arrays.sort(ideas, new Comparator<Idea>() {
            @Override
            public int compare(Idea o1, Idea o2) {
                return  o1.priority != o2.priority ? o1.priority - o2.priority :
                                o1.costTime != o2.costTime ? o1.costTime - o2.costTime :
                                        o1.time != o2.time ? o1.time - o2.time : o1.PM != o2.PM ? o1.PM - o2.PM : 0;

            }
        });
        PriorityQueue<Programmer> workQueue = new PriorityQueue<>(new Comparator<Programmer>() {
            @Override
            public int compare(Programmer o1, Programmer o2) {
                return o1.nextFreeTime - o2.nextFreeTime;
            }
        });
        for (int i = 0; i < programmers; i++) {
            workQueue.add(new Programmer());
        }
        int ideaIndex = 0;
        while(ideaIndex < length){

                Programmer p = workQueue.poll();
                int pn = p.nextFreeTime ;
            int i = pn > ideas[ideaIndex].time ?
                    (pn += ideas[ideaIndex].costTime) :
                    (pn += ideas[ideaIndex].time - pn + ideas[ideaIndex].costTime);
            p.nextFreeTime = i;
            workQueue.add( p );
                res[ideaIndex++] = p.nextFreeTime;

        }
        return res;


    }

}
class Idea{
    int PM;
    int time;
    int priority;
    int costTime;

    public Idea(int PM, int time, int priority, int costTime) {
        this.PM = PM;
        this.time = time;
        this.priority = priority;
        this.costTime = costTime;
    }
}
class Programmer{
    int nextFreeTime;
}
/*
* 产品经理(PM)有很多好的idea，而这些idea需要程序员实现。现在有N个PM，在某个时间会想出一个 idea，每个 idea 有提出时间、所需时间和优先等级。
* 对于一个PM来说，最想实现的idea首先考虑优先等级高的，相同的情况下优先所需时间最小的，还相同的情况下选择最早想出的，没有 PM 会在同一时刻提出两个 idea。

同时有M个程序员，每个程序员空闲的时候就会查看每个PM尚未执行并且最想完成的一个idea,然后从中挑选出所需时间最小的一个idea独立实现，
* 如果所需时间相同则选择PM序号最小的。直到完成了idea才会重复上述操作。如果有多个同时处于空闲状态的程序员，那么他们会依次进行查看idea的操作。

求每个idea实现的时间。

输入第一行三个数N、M、P，分别表示有N个PM，M个程序员，P个idea。随后有P行，每行有4个数字，分别是PM序号、提出时间、优先等级和所需时间。
* 输出P行，分别表示每个idea实现的时间点。


输入描述:
输入第一行三个数N、M、P，分别表示有N个PM，M个程序员，P个idea。随后有P行，每行有4个数字，分别是PM序号、提出时间、优先等级和所需时间。全部数据范围 [1, 3000]。

输出描述:
输出P行，分别表示每个idea实现的时间点。

输入例子1:
2 2 5
1 1 1 2
1 2 1 1
1 3 2 2
2 1 1 2
2 3 5 5

输出例子1:
3
4
5
3
9
*
* */