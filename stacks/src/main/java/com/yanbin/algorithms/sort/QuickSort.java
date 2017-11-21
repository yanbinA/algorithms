package com.yanbin.algorithms.sort;

/**
 * 快速排序
 * 将数组一指定中枢 划分小于中枢 的一类或者在数组前半段，大于中枢在数组后半段，的一组更有序的数组
 * 以此类推，将已划分的两端再通过新中枢划分，直到划分出没有元素或只有一个元素的数组位置为止
 * 中枢值(pivot)的选择:可以选择任意元素作为中枢项，但一般将待划分的数组最右端的元素作为中枢，划分结束后，如果中枢位于左右数组分界处，那么中枢就落在数组排序的最终位置上
 * @author yanbin
 * @date 2017/11/21 17:10
 */
public class QuickSort {
    
    private int[] arr;
    
    public QuickSort(int[] arr) {
        this.arr = arr;
        quickSort(0, arr.length - 1);
    }

    private void quickSort(int left, int right) {
    }

    /**
     * 划分函数
     * @param arr 待划分数组
     * @param left  数组左端
     * @param right 数组右端
     * @param pivot 中枢值，数组最右端元素
     * @return  分界点
     */
    public int partitionIt(int[] arr, int left, int right, int pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {
            while (arr[++leftPtr] < pivot) {
                //在左端找到大于或等于中枢值的元素，跳出循环
                //从左往右一定存在大于或等于中枢值的元素，因为pivot在最右端
            }
            while (left < rightPtr && arr[--rightPtr] > pivot) {
                //从右往左遍历，但没有比较最右端元素(pivot),可能越界
            }
            //在左端找到大于中枢值的数，也在右端找到小于中枢值的数，交换位置
            if (leftPtr >= rightPtr) {
               //当位置相同跳出循环
                break;
            } else {
                swap(leftPtr, rightPtr);
            }
        }
        //将中枢值与分界值交换
        swap(leftPtr, rightPtr);
        return leftPtr;
    }

    private void swap(int leftPtr, int rightPtr) {
        int temp = arr[leftPtr];
        arr[leftPtr] = arr[rightPtr];
        arr[rightPtr] = temp;
    }
}
