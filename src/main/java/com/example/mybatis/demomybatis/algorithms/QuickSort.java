package com.example.mybatis.demomybatis.algorithms;

import lombok.extern.slf4j.Slf4j;

/**
 * 快速排序是冒泡排序的升级版
 * 快排的基本思想是：
 * 通过一次递归，找到一个中间值
 * 这个中间值满足，【在中间值的左边都比中间值小，在中间值的右边都比中间值大】
 * @author jacksparrow414
 * @date 2021/3/4
 */
@Slf4j
public class QuickSort {
    
    public void quickSort(int ...nums) {
        recursionCall(nums, 0, nums.length-1);
    }
    
    private void recursionCall(int[] nums, int low, int high) {
        if(low < high) {
            int pivot = partitionBigToSmall(nums, low, high);
            recursionCall(nums, low, pivot-1);
            recursionCall(nums, pivot+1, high);
        }
    }
    
    /**
     * 从小到大排.即比中间值小的在左边，比中间值大的在右边
     */
    private int partitionSmallToBig(int[] nums, int low, int high) {
        int pivotKey = nums[low];
        while (low < high) {
            // 当nums[high] >=pivotKey的时候，
            // 说明此时nums[high]位于pivotKey的右边，比pivotKey大，满足中间值右边比中间值大的情况，
            // 所以继续移动右边的指针
            while (low < high && nums[high] >= pivotKey) {
             high--;
            }
            // 一旦条件不满足了，说明，此时右边的数比左边小，所以要交换二者的值
            SortUtil.swap(nums, low, high);
            // 对于左边的确定同理
            while (low < high && nums[low] <= pivotKey) {
                low++;
            }
            SortUtil.swap(nums, low, high);
        }
        // 当左边不小于右边时，说明，此时二者走到了一起，则该位置的index就是本次的中间值
        return low;
    }
    
    /**
     * 从大到小排.即比中间值小的在右边，比中间值大的在左边
     */
    public int partitionBigToSmall(int[] nums, int low, int high) {
        int pivotKey = nums[low];
        while (low < high) {
            while (low < high && nums[high] <= pivotKey) {
                high--;
            }
            SortUtil.swap(nums, low, high);
            while (low < high && nums[low] >=pivotKey) {
                low++;
            }
            SortUtil.swap(nums, low, high);
        }
        return low;
    }
    
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = new int[]{50,10,90,30,70,40,80,60,20};
        quickSort.quickSort(nums);
        log.info("快速排序的最终结果是{}", nums);
    }
}
