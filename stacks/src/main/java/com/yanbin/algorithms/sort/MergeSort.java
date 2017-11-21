package com.yanbin.algorithms.sort;

/**
 * 归并排序
 * 核心:将两组有序数组合并成一组有序数组，
 * 将一组无序数组分成两组，再排序后合并数组，同理将1/2数组分成1/4...直到数组中只有个元素时排序，返回有序数组
 * 算法复杂度：O(N*logN)
 * 缺点:需要两倍数组大小的内存空间
 * @author yanbin
 * @date 2017/11/18 16:58
 */
public class MergeSort {

    private int[] workSpace;

    public MergeSort(int[] arr) {
        workSpace = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int lowerBound, int upperBound) {
        if (upperBound == lowerBound) {
            return;
        } else {
            int mid = (lowerBound + upperBound) / 2;
            mergeSort(arr, lowerBound, mid);
            mergeSort(arr, mid + 1, upperBound);
            merge(arr, lowerBound, mid, mid + 1, upperBound);
        }
    }

    private void merge(int[] arr, int lowerBound, int mid, int mid1, int upperBound) {
        int start = lowerBound;
        int lowerDex = lowerBound;
        int upperDex = upperBound;
        while (lowerBound <= mid && mid1 <= upperBound) {
            if (arr[lowerBound] < arr[mid1]) {
                workSpace[start++] = arr[lowerBound++];
            } else {
                workSpace[start++] = arr[mid1++];
            }
        }
        while (lowerBound <= mid) {
            workSpace[start++] = arr[lowerBound++];
        }
        while (mid1 <= upperBound) {
            workSpace[start++] = arr[mid1++];
        }

        while (lowerDex <= upperDex) {
            arr[lowerDex] = workSpace[lowerDex++];
        }
    }

}
