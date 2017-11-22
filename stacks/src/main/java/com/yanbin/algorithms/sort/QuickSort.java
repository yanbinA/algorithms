package com.yanbin.algorithms.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 将数组一指定中枢 划分小于中枢 的一类或者在数组前半段，大于中枢在数组后半段，的一组更有序的数组
 * 以此类推，将已划分的两端再通过新中枢划分，直到划分出没有元素或只有一个元素的数组位置为止
 * 中枢值(pivot)的选择:可以选择任意元素作为中枢项，但一般将待划分的数组最右端的元素作为中枢，划分结束后，如果中枢位于左右数组分界处，那么中枢就落在数组排序的最终位置上
 * 算法复杂度:N*logN
 * 缺点:稳定性不如 希尔排序。在极端情况下，快速排序的效率极差，性能O(N^2);由于中枢值去最右端数，导致划分结果为1 和 N-1,可采用三项数据取中(左、中、右排序，取中值作为枢纽) 划分的方式，
 * 三值取中，依然存在小划分问题(子数组过小)。对于子数组小于定值(切割点)时可以直接使用插入排序，以提高效率
 * 当子数组长度小于10(不同的值有不同的效率)时，直接采用插入排序
 * @author yanbin
 * @date 2017/11/21 17:10
 */
public class QuickSort {
    
    private int[] arr;
    
    public QuickSort(int[] arr) {
        this.arr = arr;

    }

    public void startQuickSort() {
        quickSort(0, arr.length - 1);
    }

    public void startPowerSort() {
        powerQuickSort(0, arr.length - 1);
    }

    public void startPowerSort(int minSize) {
        powerQuickSort(0, arr.length - 1, minSize);
    }

    private void quickSort(int left, int right) {

        if (left >= right) {
            return;
        }
        int it = partitionIt(left, right, arr[right]);
        quickSort(left, it - 1);
        quickSort(it + 1, right);
    }
    /**
     * 三项数据取中(左、中、右排序，取中值作为枢纽) 划分方式
     * @param left  数组左端
     * @param right 数组右端
     *
     */
    private void powerQuickSort(int left, int right) {
        int size = right - left + 1;
        //如果数组长度小于4，无法使用三项数据取中，另行处理
        if (size < 4) {
            //小划分进行手动排序
            manualSort(left, right);
        } else {
            int center = (left + right) / 2;
            //排序 左中右
            if (arr[left] > arr[center]) {
                swap(left, center);
            }
            if (arr[left] > arr[right]) {
                swap(left, right);
            }
            if (arr[center] > arr[right]) {
                swap(center, right);
            }
            //将中值作为枢纽放在最右端-1
            swap(center, right - 1);
            int partitionIt = powerPartitionIt(left, right - 1, arr[right - 1]);
            powerQuickSort(left, partitionIt - 1);
            powerQuickSort(partitionIt + 1, right);

        }
    }

    /**
     * 三项数据取中(左、中、右排序，取中值作为枢纽) 划分方式
     * 自定义 将小数组采用插入排序
     * @param left  数组左端
     * @param right 数组右端
     * @param minSize 子数组长度小于该值采用插入排序
     */
    private void powerQuickSort(int left, int right, int minSize) {
        int size = right - left + 1;
        //如果数组长度小于4，无法使用三项数据取中，另行处理
        if (size < minSize) {
            //采用插入排序
            insertSort(left, right);
        } else {
            int center = (left + right) / 2;
            //排序 左中右
            if (arr[left] > arr[center]) {
                swap(left, center);
            }
            if (arr[left] > arr[right]) {
                swap(left, right);
            }
            if (arr[center] > arr[right]) {
                swap(center, right);
            }
            //将中值作为枢纽放在最右端-1
            swap(center, right - 1);
            int partitionIt = powerPartitionIt(left, right - 1, arr[right - 1]);
            powerQuickSort(left, partitionIt - 1, minSize);
            powerQuickSort(partitionIt + 1, right, minSize);

        }
    }

    private void insertSort(int left, int right) {

        int in,out;
        for (out = left + 1; out <= right; out++) {
            int temp = arr[out];
            in = out;
            while (in > 0 && arr[in - 1] >= temp) {
                arr[in] = arr[--in];
            }
            arr[in] = temp;
        }

    }

    /**
     * 子数组长度小于4，不能在使用划分函数，需要另行排序
     * 当子数组小于一定值时，采用插入排序 效率得到提升
     * @param left
     * @param right
     */
    private void manualSort(int left, int right) {
        int size = right - left + 1;
        if (size <= 1)  {
            return;
        } else if (size == 2) {
            if (arr[left] > arr[right]) {
                swap(left, right);
            }
        } else {
            if (arr[left] > arr[left + 1]) {
                swap(left, left + 1);
            }
            if (arr[left] > arr[right]) {
                swap(left, right);
            }
            if (arr[left + 1] > arr[right]) {
                swap(left + 1, right);
            }
        }

    }


    /**
     * 三值取中划分函数
     * 该枢纽值的选择方式可以确定从右往左的while不会越界;
     * 最左端元素比枢纽值小的元素，不必比较
     * @param left  数组左端
     * @param right 数组右端
     * @param pivot 中枢值，数组最右端元素
     * @return  分界点
     */
    private int powerPartitionIt(int left, int right, int pivot) {
        int leftPtr = left;
        int rightPtr = right;
        while (true) {
            while (arr[++leftPtr] < pivot) {
                //在左端找到大于或等于中枢值的元素，跳出循环
                //从左往右一定存在大于或等于中枢值的元素，因为pivot在最右端
            }
            while ( arr[--rightPtr] > pivot) {

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
        swap(leftPtr, right);
        return leftPtr;
    }

    /**
     * 划分函数
     * @param left  数组左端
     * @param right 数组右端
     * @param pivot 中枢值，数组最右端元素
     * @return  分界点
     */
    private int partitionIt(int left, int right, int pivot) {
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
        swap(leftPtr, right);
        return leftPtr;
    }

    private void swap(int leftPtr, int rightPtr) {
        int temp = arr[leftPtr];
        arr[leftPtr] = arr[rightPtr];
        arr[rightPtr] = temp;
    }
}
