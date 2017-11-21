package com.yanbin.algorithms.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 归并排序
 * 给定 两个有序数组，合并成新的有序数组
 * @author yanbin
 * @date 2017/11/17 17:50
 */
public class MergeSortTest {
    private static final int LENGTH = 100000;
    private static final int RANDOM = 1000000;

    public static void main(String[] args) {


        int[] arr = new int[LENGTH];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = -(RANDOM - i);//;new Random().nextInt(RANDOM);
        }
        System.out.println(Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        new MergeSort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(arr));
        System.out.println("归并排序用时---：" + (endTime - startTime) + "毫秒");

        /*for(int i = 0; i < arr.length; i++) {
            arr[i] = RANDOM - i;
        }
        //System.out.println(Arrays.toString(arr));
        startTime = System.currentTimeMillis();
        new InsertSort(arr);
        endTime = System.currentTimeMillis();
       // System.out.println(Arrays.toString(arr));
        System.out.println("插入排序用时---：" + (endTime - startTime) + "毫秒");*/

        for(int i = 0; i < arr.length; i++) {
            arr[i] = -(RANDOM - i);
        }
        System.out.println(Arrays.toString(arr));
        startTime = System.currentTimeMillis();
        new ShellSort(arr);
        endTime = System.currentTimeMillis();
         System.out.println(Arrays.toString(arr));
        System.out.println("希尔排序用时---：" + (endTime - startTime) + "毫秒");
       /* int[] arrC = mergeSort(arrA, arrA.length, arrB, arrB.length);

        System.out.println(Arrays.toString(arrC));*/
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
