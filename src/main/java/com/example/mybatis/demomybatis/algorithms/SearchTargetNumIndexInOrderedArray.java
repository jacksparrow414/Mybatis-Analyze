package com.example.mybatis.demomybatis.algorithms;

import lombok.extern.slf4j.Slf4j;

/**
 * 在给定的有序数组中查找目标数字的第一次出现的位置和最后一次出现的位置
 * 实现思路：二分查找
 * <a href="https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/yi-wen-dai-ni-gao-ding-er-fen-cha-zhao-j-ymwl/">题解</a>
 * @author jacksparrow414
 * @date 2021/4/1 11:25
 */
@Slf4j
public class SearchTargetNumIndexInOrderedArray {

    public int[] doSearch(int target, int... nums) {
        int firstIndex = firstIndex(target, nums);
        int lastIndex = lastIndex(target, nums);
        if (lastIndex < firstIndex) {
            return new int[]{-1,-1};
        }
        return new int[]{firstIndex, lastIndex};
    }

    public int firstIndex(int target, int... nums) {
        int left = 0;
        int right = nums.length -1;
        while (left <= right) {
            int mid = left + (right - left) /2;
            if (nums[mid] >= target) {
                right = mid -1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }
        }
        return left;
    }

    public int lastIndex(int target, int... nums) {
        int left = 0;
        int right = nums.length -1;
        while (left <= right) {
            int mid = left + (right - left) /2;
            if (nums[mid] <= target) {
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid -1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        SearchTargetNumIndexInOrderedArray searchTargetNumIndexInOrderedArray = new SearchTargetNumIndexInOrderedArray();
        int[] array = new int[]{5,7,7,8,8,10};
        int[] ints = searchTargetNumIndexInOrderedArray.doSearch(6, array);
        log.info("结果为{}", ints);
    }
}
