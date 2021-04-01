package com.example.mybatis.demomybatis.algorithms;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 采用优先队列的方式.<a href="https://leetcode-cn.com/problems/find-median-from-data-stream/solution/you-xian-dui-lie-python-dai-ma-java-dai-ma-by-liwe/">解题思路</a>
 * 优先队列的时间复杂度是logn，<a href="https://www.cxyxiaowu.com/5417.html">具体文章</a>
 * @author jacksparrow414
 * @date 2021/4/1 10:15
 */
public class MiddleNumberWithPriorityQueue {

    public static void main(String[] args) {
        int size = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) ->y - x);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            size += 1;
            // maxHeap起到的作用是：为什么每次都要入maxHeap?保证在maxHeap到当前为止的所有元素都是经过排序的，从大到小
            maxHeap.offer(num);
            // 将maxHeap中最大的元素拿出放到后序数组中，然后进行排序，保证minHeap也是有序的，从小到大
            minHeap.add(maxHeap.poll());
            if (size % 2 == 0) {
                // 如果是偶数，直接取两边的第一个元素，做平均数即可
                double middle = (minHeap.peek() + maxHeap.peek()) / 2.0;
                System.out.println("中位数是："+ middle);
            }else {
                // 如果是奇数，要取maxHeap中最大的，那么需要将minHeap中的第一数返回给maxHeap
                maxHeap.add(minHeap.poll());
                int middle = maxHeap.peek();
                System.out.println("中位数是："+ middle);
            }
        }
        System.out.println("程序结束");
        scanner.close();
    }
}
