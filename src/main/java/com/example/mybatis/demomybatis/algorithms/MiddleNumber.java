package com.example.mybatis.demomybatis.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 求连续输入数字的中位数，解题思路:<a href= "https://leetcode-cn.com/problems/find-median-from-data-stream/solution/shu-ju-liu-de-zhong-wei-shu-by-leetcode/">leetcode题解</a>
 * @author jacksparrow414
 * @date 2021/3/31
 */
public class MiddleNumber {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入非数字，程序终止");
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            list.add(i);
            list.sort(Integer::compareTo);
            int size = list.size();
            if ( size % 2 == 0 ) {
                double middle = (list.get(size/2 -1) + list.get(size/2)) / 2.0;
                System.out.println("当前集合的中位数为：" + middle);
            }else {
                int i1 = size / 2;
                int middle = list.get(i1);
                System.out.println("当前集合的中位数为：" + middle);
            }
        }
        System.out.println("程序结束");
        scanner.close();
    }
}
