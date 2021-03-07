package com.example.mybatis.demomybatis.algorithms;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

/**
 * 冒泡排序属于交换排序-时间复杂度为,n*n
 * 冒泡排序的核心思想是冒泡：两两比较
 * 冒泡有两种方式，一种是从上往下冒泡，一种是从下往上冒泡，两个for循环，最主要的是里面的第二个for循环
 * 每次循环必能得到集合中的最大或者最小值
 *
 * 举例说明：假设数组元素有5个，选择从下往上冒泡
 * 第二个for循环如何设计呢？
 * 第一次要比较的两个元素就是第5个元素和第4个元素，如果第5个元素比第4个元素小，则交换二者的位置
 * 接着比较第4个和第3个，以此类推，那么循环的结束条件是什么呢？
 * 结束条件是目前已经排好的元素的位置(即第一个for循环每次循环的变量的值)，
 * 因为第二个for循环每经过一次完整的循环，就必定能找到最小值，
 * 那么第二次循环只要循环到刚才的最小值的index+1的位置即可
 *
 * 按照普通的冒泡排序，不论原始数组是否有序，则都要经过两层完整的for循环，更进一步，
 * 假如能提前判断到哪个位置已经是有序的了，则可以省去后面的循环
 *
 * 还是按照自底向上的冒泡来看，假设本就是一个有序数组，那么肯定不会进第二个for循环的if里的，
 * 一直没进去，就说明，遍历过来的元素都是有序的
 * 所以，当有元素发生交换时才认为有循环的必要，否则，就没必要循环了
 *
 * 为什么flag的判断要放在最外面的for循环？
 * 因为，里面的for循环遍历的就是到外面for循环i的位置，
 * 如果自低向上，一直到你都发生过元素交换，那么可以肯定，从i到最底，则就是有序的
 *
 * 再进一步，目前都是直接改变的原数组，能直接返回一个新数组吗？
 * @author jacksparrow414
 * @date 2021/3/2
 */
@Slf4j
public class BubbleSort {
    
    /**
     * 从下往上冒泡.
     */
    public void bubbleSortDownToUp(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length -1 ; j > i; j--) {
                if (nums[j] < nums[j-1]) {
                    int tmp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = tmp;
                }
            }
            log.info("本次排序结果为{}", Arrays.toString(nums));
        }
    }
    
    /**
     * 从上往下冒泡.
     */
    public void bubbleSortUpToDown(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length -1 - i; j++) {
                if (nums[j] > nums[j+1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                }
            }
            log.info("本次排序结果为{}", Arrays.toString(nums));
        }
    }
    
    /**
     * 优化从下往上冒泡.
     */
    public void bubbleSortDownToUpOptimize(int[] nums) {
        boolean flag = true;
        for (int i = 0; i < nums.length && flag; i++) {
            flag = false;
            for (int j = nums.length -1 ; j > i ; j--) {
                if (nums[j] < nums[j-1]) {
                    int tmp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
            log.info("本次排序结果为{}", nums);
        }
    }
    
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        
        int[] nums = new int[]{9,1,5,8,3,7,4,6,2};
        bubbleSort.bubbleSortDownToUp(nums);
        log.info("自下由上的最终排序结果为{}", nums);
        
        int[] finalUpToDownNums = new int[]{9,1,5,8,3,7,4,6,2};
        bubbleSort.bubbleSortUpToDown(finalUpToDownNums);
        log.info("自上由下的最终排序结果为{}", finalUpToDownNums);
        
        int[] optimizeNums = new int[]{2,1,3,4,5,6,7,8,9};
        bubbleSort.bubbleSortDownToUpOptimize(optimizeNums);
        log.info("优化之后的排序结果为{}", optimizeNums);
    }
}
