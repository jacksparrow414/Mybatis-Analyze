package com.example.mybatis.demomybatis.algorithms;

import lombok.extern.slf4j.Slf4j;

/**
 * 插入排序：
 * 每次循环确定当前index前的元素顺序有序
 * @author duhongbo
 * @date 2021/3/4 15:09
 */
@Slf4j
public class InsertionSort {

    public void insertionSort(int ...nums) {
        // 标识在哪个index开始向前循环
        for (int i = 1; i < nums.length; i++) {
            // 每次处理一个元素
            int change = nums[i];
            for (int j = i-1; j >=0 && nums[j] > change; j--) {
                int tmp = nums[j+1];
                nums[j+1] = nums[j];
                nums[j] = tmp;
            }
            log.info("本次排序结果{}", nums);
        }
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] nums = new int[]{9,1,5,8,3,7,4,6,2};
        insertionSort.insertionSort(nums);
        log.info("直接插入排序最终结果为{}", nums);
    }
}
