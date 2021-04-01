package com.example.mybatis.demomybatis.algorithms;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Scanner;

/**
 * 优化使用list的方式,不是每次都排序，采用插入排序的思想，假设目前list已经有序，只要在有序数组中找到第一个大于输入的数字的index，插入即可
 * <a href="https://leetcode-cn.com/problems/find-median-from-data-stream/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--54/">解答地址<a/>
 * @author jacksparrow414
 * @date 2021/4/1 9:51
 */
public class OptimizeMiddleNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = Lists.newArrayList();
        while (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            int j = 0;
            // 找到第一个大于输入数字的index的位置
            for (; j < list.size(); j++) {
                if (i < list.get(j)) {
                    break;
                }
            }
            list.add(j, i);
            int size = list.size();
            if (size % 2 == 0) {
                double middle = (list.get(size / 2 -1) + list.get(size / 2)) / 2.0;
                System.out.println("中位数是:"+ middle);
            }else {
                int middle = list.get(size / 2);
                System.out.println("中位数是:"+ middle);
            }
        }
        System.out.println("程序结束");
        scanner.close();
    }
}
