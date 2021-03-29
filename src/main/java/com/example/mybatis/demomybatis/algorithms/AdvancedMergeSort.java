package com.example.mybatis.demomybatis.algorithms;

import lombok.extern.slf4j.Slf4j;

/**
 * 归并排序是稳定的：时间复杂度nlogn
 * 相较于{@link MergeSort},不使用辅助数组
 * @author jacksparrow414
 * @date 2021/3/6
 */
@Slf4j
public class AdvancedMergeSort {
    
    /**
     * 不允许使用辅助数组，如何判断到了递归的终止条件处？
     * 只能根据现有条件来找思路。
     * 现在只有一个数组，能找到可用的东西数组元素本身，数组的两个下标(数组的最大下标和最小下标)
     *
     * @see MergeSort#mergeNewArray(int[]) 方法中使用辅助数组可以清晰的知道原始数组被分成了什么样子？
     * 不让用辅助数组，不让用辅助数组......那可以和下面的方法采取一样的思考方法，可以通过标记位来标记成一个假的辅助数组
     * 原始数组进来，必然要在中间被分，为什么？废话，归并算法的思想就是分成两路
     * 既然在中间被分了，那么low->mid就可以视作arrayOne,mid+1->high就可以视作arrayTwo
     * 按照这个思路继续递归去分隔，那么接下来递归的应该是刚才被拆的第一个伪数组，low还是不变，那么此数组的最大长度也就到mid
     * 同理，第二个数组递归时，mid+1的位置视为第二个数组开始位
     *
     * 就这样，一直递归递归下去，到最后，肯定不能判断nums.length==1的方式了，因为nums在这里就是原始数组，里面的元素根本没变过
     * 随着两个low和high指针的不断靠近，最终会重合
     */
    public void mergeSort(int[] nums, int low, int high) {
        // 也就是上面所说的重合条件，也可以换成 low < high
        if (low < high) {
            // 这里为什么不使用( low + high ) /2 有可能出现数字越界，超过int最大值
            int mid  = low + (high-low) / 2;
            mergeSort(nums, low, mid);
            mergeSort(nums, mid+1, high);
            // 最终归并之前，需要传入三个标记位来判断两个伪数组的界限
            doMerge(nums, low, mid, high);
        }
    }
    
    /**
     * 既然没有辅助数组，那么比较就不能再两个数组之间比较了.就需要在原始数组上比较
     * 如何在原始数组上比较呢?一开始我也是没思路，既然没思路，莫不如造点数据
     * 假设，经过递归，最后一次合并之前，此时的数组变为了{1,3,5,7,9}和{2,4,6,8}
     * 面对这样的数组怎么处理？要想实现和{@link MergeSort#doNewMerge(int[], int[])}一样的效果
     * 那么就要确定这两个部分的开始、结束位置。而两个数组入参的情况的方法，直接分别从两个数组下标为0的位置开始即可
     *
     * 但是在这里只有一个数组，如何把这两部分区分开？用标记位判断
     * low到mid的位置就可以被视为第一个数组
     * mid+1到high的位置可以被视为第二个数组
     *
     * 根据这个想法，那么实现和{@link MergeSort#doNewMerge(int[], int[])}方法的思想，就简单了
     *
     * 可以看到，大部分代码都是基本相同的
     */
    public void doMerge(int[] nums, int low, int mid, int high) {
        // 这里为什么要high-low，直接使用high不可以吗？
        // 不可以。因为这个方法是基于一个数组来的，根据MergeSort类的思想，要最终分成一个一个的元素，
        // 假设一开始第三个位置和第四个位置开始向上合并，那么此时的high是3，low是2,而这时新的数组其实只有两个元素，
        // 如果不把low前面的部分减掉的话，那就会多出来元素
        int[] result = new int[high-low+1];
        int resultStart = 0;
        // 相当于第一个数组的起始位
        int lowStart = low;
        // 相当于第二个数组的起始位
        int highStart = mid+1;
        while (lowStart <=mid && highStart<=high) {
            if (nums[lowStart] > nums[highStart]) {
                result[resultStart++] = nums[highStart++];
            }else {
                result[resultStart++] = nums[lowStart++];
            }
        }
        while (lowStart<=mid) {
            result[resultStart++] = nums[lowStart++];
        }
        while (highStart<= high) {
            result[resultStart++] = nums[highStart++];
        }
        // 因为始终要在原始数据上进行比较，所以在每次归并之后，要替换原始数组的内容
        System.arraycopy(result, 0, nums, low, result.length);
        log.info("本次归并结果为{}", result);
    }
    
    public static void main(String[] args) {
        AdvancedMergeSort advancedMergeSort = new AdvancedMergeSort();
        int[] nums = new int[]{9,1,5,8,3,7,4,6,2};
        advancedMergeSort.mergeSort(nums, 0,  nums.length-1);
    }
}
