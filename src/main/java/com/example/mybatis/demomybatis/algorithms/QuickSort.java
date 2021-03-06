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

    /**
     * 普通快速-递归排序
     * @param nums 待排序数组
     */
    public void quickSort(int ...nums) {
        recursionCall(nums, 0, nums.length-1);
    }

    /**
     * 普通快速-尾递归排序.
     * @param nums 待排序数组
     */
    public void quickSortTail(int ...nums) {
        tailRecursionCall(nums, 0, nums.length-1);
    }

    public void quickSortTailFromMiddle(int... nums) {
        tailRecursionCallFromMiddle(nums, 0, nums.length -1);
    }
    /**
     * 普通递归快排.
     * 当递归深度足够大的时候，容易出现堆栈空间不足的情况
     */
    private void recursionCall(int[] nums, int low, int high) {
        if(low < high) {
            int pivot = partitionSmallToBig(nums, low, high);
            recursionCall(nums, low, pivot-1);
            // 一次递归->两个递归了
            recursionCall(nums, pivot+1, high);
        }
    }

    /**
     * 尾递归快排.
     */
    private void tailRecursionCall(int[] nums, int low, int high) {
        // 使用while迭代,一次递归两次迭代
        while (low < high) {
            int pivot = partitionBigToSmall(nums, low, high);
            tailRecursionCall(nums, low, pivot-1);
            low = pivot + 1;
        }
    }

    private void tailRecursionCallFromMiddle(int[] nums, int left, int right) {
        while (left < right) {
            int pivot = partitionSmallToBigFromMiddle(nums, left, right);
            tailRecursionCallFromMiddle(nums, left, pivot-1 );
//            tailRecursionCallFromMiddle(nums, pivot, right);
            left = pivot;
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
     * 固定基准：从中间值开始.
     * 从小到大排.即比中间值小的在左边，比中间值大的在右边.
     * 不再选取固定位置0作为中间位置，【选取中间位置的值作为枢轴】
     */
    private int partitionSmallToBigFromMiddle(int[] nums, int left, int right) {
        // 不论是从头选基准值还是从中间位置选基准值，都是不太稳定
        int middle = (left+right) /2 ;
        // 可以使用随机数，随机一个left、right之间的值，然后这个值和left或者right交换，这样很大程度上解决了不稳定的问题
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        // 根据randomIndex的数值最终是跟left交换还是right交换
        // 更高级的是三数取中
        int pivotKey = nums[middle];
        while (left <= right) {
            while (nums[right] > pivotKey) {
                right--;
            }
            // 条件判断为<，如果是<=，则指针会继续前移
            while ( nums[left] < pivotKey) {
                left++;
            }

            if (left <= right) {
                SortUtil.swap(nums, left, right);
                left++;
                right--;
            }
        }
        log.info("本次排序结果为{}", nums);
        return left;
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
        quickSort.quickSortTailFromMiddle(nums);
        log.info("快速排序的最终结果是{}", nums);
    }
}
