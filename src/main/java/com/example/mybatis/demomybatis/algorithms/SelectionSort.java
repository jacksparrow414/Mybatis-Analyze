package com.example.mybatis.demomybatis.algorithms;

import lombok.extern.slf4j.Slf4j;

/**
 * 选择排序：
 * 每一轮循环之后，能够确定对应index的值
 *
 * 个人认为，和冒泡排序的最大区别是，冒泡排序可以提前结束循环
 * @author jacksparrow414
 * @date 2021/3/4 14:47
 */
@Slf4j
public class SelectionSort {

    public void selectionSort(int ...nums) {
        for (int i = 0; i < nums.length -1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                // 以此判断给定位置上和本次循环的位置上的数据的大小,不交换数据，只记录本次循环的最小index
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            // 如果index和外层循环不一致，则将当前index和最外层index互换位置
            if (minIndex!=i) {
                int tmp = nums[minIndex];
                nums[minIndex] = nums[i];
                nums[i] = tmp;
            }
            log.info("本次排序结果为{}", nums);
        }
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        int[] nums = new int[]{9,1,5,8,3,7,4,6,2};
        selectionSort.selectionSort(nums);
        log.info("选择排序排序结果为{}", nums);
    }
}
