package com.example.mybatis.demomybatis.algorithms;

import lombok.extern.slf4j.Slf4j;

/**
 * 归并排序的核心思想是：
 * 1、先将集合拆成一个个元素只有1个的子集合
 * 2、再将子集合一个一个【按照原路有序合并】
 *
 * 按照这个逻辑自己写的最普通的归并排序.其中比较难处理的部分是将所有原有元素全部递归分开成一个、一个独立的数组
 *
 * 归并排序算法稳定.时间复杂度为nlogn
 * @author jacksparrow414
 * @date 2021/3/6
 */
@Slf4j
public class MergeSort {
    
    /**
     * 第一步：按照/2的形式，把每个元素彻底分隔开
     * 【分隔开的思想是,每次确定分隔点之后，新建两个数组，分别把分隔点之后和分隔点之前的元素分别赋值给两个数组】(有两个辅助数组)
     *
     * 这里并没有采用网上的方法，通过传low，和 high位置
     *
     * 是因为，原始数组进来之后，可以计算出原始数组的最大长度，就可以算出high的值，同理也可以算出mid的值,(根本原因是使用了辅助数组)
     * 这两个值一算出来，那么对两个数组的赋值就可以确定了，
     * 第一个数组循环到mid位置
     * 第二个数组从原始数组的mid+1的位置到high的位置取数据就可以了
     * 那么这两个数组就分隔好了，接下来，递归就可以了
     */
    public int[] mergeNewArray(int[] sourceNums) {
        // 对应第一步,如果最后的数组长度为1，说明，已经分隔到了最后
        if (sourceNums.length == 1) {
            return sourceNums;
        }else {
            // 按照收尾相加除以2的形式递归分隔数组
            int mid = (sourceNums.length-1) / 2;
            int high = sourceNums.length-1;
            // 使用两个数组来放置分隔之后的元素，假设原始数组一共9个元素,high是8,low是0，则mid=4
            // 所以第一个数组是0-4,一共5个元素，所以要加1
            int[] arrayOne = new int[mid+1];
            // 第二个数组是5-8，一共4个元素
            int[] arrayTwo = new int[high-mid];
            // 分别将划分好的元素放到数组中，这里使用一共需要循环5次，mid-low+1
            for (int i = 0; i < mid +1; i++) {
                // 分别将原始数组的前几项赋值给数组1
                arrayOne[i] = sourceNums[i];
            }
            // 一共循环4次
            for (int i = 0; i < high-mid; i++) {
                // 分别将原始数组从【mid+1的位置开始】直到末尾的几项赋值给第二个数组
                arrayTwo[i] = sourceNums[mid+1+i];
            }
            // 将数组1和数组2，分别当做原始数组传入，再继续递归
            int[] first = mergeNewArray(arrayOne);
            int[] second = mergeNewArray(arrayTwo);
            // 当上面的两个递归都完成时,则开始自底向上开始有序合并集合
            return doNewMerge(first, second);
        }
    }
    
    /**
     * 第二步：合并两个数组，并组装成新的数组返回
     */
    public int[] doNewMerge(int[] arrayOne,  int[] arrayTwo) {
        // 使用新数组接收
        int[] result = new int[arrayOne.length+arrayTwo.length];
        int oneStart = 0;
        int twoStart = 0;
        int resultStart = 0;
        // 分别从两个数组的开始位置，依次比较
        while (oneStart <= arrayOne.length-1 && twoStart <=arrayTwo.length-1) {
            // 如果数组1index位置的数据 > 数组2index位置的数据
            if (arrayOne[oneStart] > arrayTwo[twoStart]) {
                // 那么新数组的位置上应该放数组2的数据，复制完毕，各个index递增，进入下一次循环
                result[resultStart++] = arrayTwo[twoStart++];
            }else {
                // 和上面同理
                result[resultStart++] = arrayOne[oneStart++];
            }
        }
        // 如果都比较完了，发现数组1中还有元素，那么就将数组1中剩余的元素加到新数组的末尾
        // 这里为什么第一个数组在前面？因为上面的比较保证了数组1和数组2起码有一个被完全比较过
        if (oneStart <=arrayOne.length-1) {
            result[resultStart++] = arrayOne[oneStart++];
        }
        // 同上
        if (twoStart <=arrayTwo.length-1) {
            result[resultStart++] = arrayTwo[twoStart++];
        }
        log.info("本次归并的结果为{}", result);
       return result;
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{5,1,9,3,7,4,8,6,2};
        MergeSort mergeSort = new MergeSort();
        int[] ints = mergeSort.mergeNewArray(nums);
        log.info("归并排序的最终结果是{}", ints);
    }
}
