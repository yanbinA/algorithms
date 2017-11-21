package com.yanbin.algorithms.sort;

/**
 * 插入排序
 * @author yanbin
 * @date 2017/11/18 17:38
 */
public class InsertSort {

    public InsertSort(int[] arr) {
        int in,out;
        for (out = 1; out < arr.length; out++) {
            int temp = arr[out];
            in = out;
            while (in > 0 && arr[in - 1] >= temp) {
                arr[in] = arr[--in];
            }
            arr[in] = temp;
        }
    }


}
