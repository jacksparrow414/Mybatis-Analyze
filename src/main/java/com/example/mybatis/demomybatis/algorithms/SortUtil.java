package com.example.mybatis.demomybatis.algorithms;

/**
 * @author jacksparrow414
 * @date 2021/3/4
 */
public class SortUtil {
    
    public static void swap(int[] nums, int low, int high) {
        int tmp = nums[low];
        nums[low] = nums[high];
        nums[high] = tmp;
    }
}
