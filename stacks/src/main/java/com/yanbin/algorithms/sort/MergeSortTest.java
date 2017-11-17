package com.yanbin.algorithms.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 给定 两个有序数组，合并成新的有序数组
 * @author yanbin
 * @date 2017/11/17 17:50
 */
public class MergeSortTest {

    public static void main(String[] args) {
        int[] arrA = {1,3,7,9};

        int[] arrB = {2,4,5,8,10,12};


        int[] arrC = mergeSort(arrA, arrA.length, arrB, arrB.length);

        System.out.println(Arrays.toString(arrC));
    }

    private static int[] mergeSort(int[] arrA, int sizeA, int[] arrB, int sizeB) {

        int sizeC = sizeA + sizeB;
        int[] arrC = new int[sizeC];

        int aDex=0, bDex=0, cDex=0;

        while (aDex < sizeA && bDex < sizeB) {
            if (arrA[aDex] < arrB[bDex]) {
                arrC[cDex++] = arrA[aDex++];
            } else {
                arrC[cDex++] = arrB[bDex++];
            }
        }

        while (aDex < sizeA) {
            arrC[cDex++] = arrA[aDex++];
        }
        while (bDex < sizeB) {
            arrC[cDex++] = arrB[bDex++];
        }
        return arrC;
    }

}
